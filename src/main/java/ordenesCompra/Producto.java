/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenesCompra;

import java.util.List;
import java.util.TreeSet;
import org.lightcouch.Document;
import sun.reflect.generics.tree.Tree;

/**
 *
 * @author hduser
 */
public class Producto  extends Document{

    
    private String title;
    private String group;
    private long salesrank;
//    private List<String> similar;
//    private TreeSet<List<String>> categories;
//    private List<String> reviews;
//    private long totalreview;
//    private long downloaded;
//    private double avg_rating;

   
    public String getTitle() {
        return title;
    }

   

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public long getSalesrank() {
        return salesrank;
    }

    public void setSalesrank(long salesrank) {
        this.salesrank = salesrank;
    }
//
//    public long getTotalreview() {
//        return totalreview;
//    }
//
//    public void setTotalreview(long totalreview) {
//        this.totalreview = totalreview;
//    }
//
//    public long getDownloaded() {
//        return downloaded;
//    }
//
//    public void setDownloaded(long downloaded) {
//        this.downloaded = downloaded;
//    }
//
//    public double getAvg_rating() {
//        return avg_rating;
//    }
//
//    public void setAvg_rating(double avg_rating) {
//        this.avg_rating = avg_rating;
//    }

}
