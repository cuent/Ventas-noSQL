/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uc.nosql.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cuent
 */
public class ItemUserFacade {

    private Session session;
    private Cluster cluster;

    public ItemUserFacade() {
        connect("192.168.0.12");
    }

    public Session getSession() {
        return this.session;
    }

    private void connect(String node) {
        cluster = Cluster.builder()
                .addContactPoint(node)
                .build();
        Metadata metadata = cluster.getMetadata();
        System.out.printf("Connected to cluster: %s\n",
                metadata.getClusterName());
        for (Host host : metadata.getAllHosts()) {
            System.out.printf("Datatacenter: %s; Host: %s; Rack: %s\n",
                    host.getDatacenter(), host.getAddress(), host.getRack());
        }
        session = cluster.connect("user_product");
    }

    public List<ItemUser> queryGetUserByItem(String asin) {
        String query = String.format("SELECT * FROM user_product.user_by_item where asin='%s';", asin);
        ResultSet results = session.execute(query);
        List<ItemUser> listItemUser = new ArrayList<>();

        for (Row row : results) {
            Double d = row.getDouble("cutomer");
            listItemUser.add(new ItemUser(d.intValue(), row.getString("asin"), "", row.getString("name")));
        }
        return listItemUser;
    }

    public List<ItemUser> queryGetItemByUser(String cutomer) {
        String query = String.format("SELECT * from user_product.item_by_user WHERE cutomer=%s;", cutomer);
        ResultSet results = session.execute(query);
        List<ItemUser> listItemUser = new ArrayList<>();

        for (Row row : results) {
            Double d = row.getDouble("cutomer");
            listItemUser.add(new ItemUser(d.intValue(), row.getString("asin"), row.getString("title"), ""));
        }
        return listItemUser;
    }

    public void loadUserByProduct(String asin, int cutomer, String name) {
        String query = String.format("INSERT INTO user_product.user_by_item (asin, cutomer, name) VALUES ('%s',%s,'%s');", asin, cutomer, name);
        session.execute(query);
    }

    public void loadProductByUser(int cutomer, String asin, String title) {
        String query = String.format("INSERT INTO user_product.item_by_user (cutomer, asin, title) VALUES (%s,'%s','%s');", cutomer, asin, title);
        session.execute(query);
    }

    public void close() {
        session.close();
        cluster.close();
    }
}
