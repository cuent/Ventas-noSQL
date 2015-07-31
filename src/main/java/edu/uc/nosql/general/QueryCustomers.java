/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uc.nosql.general;

import edu.uc.nosql.cassandra.ItemUserFacade;

/**
 *
 * @author cuent
 */
public class QueryCustomers {

    public boolean isCustomer(String cutomer) {
        ItemUserFacade iuf = new ItemUserFacade();
        return iuf.queryCustomer(cutomer);
    }
}
