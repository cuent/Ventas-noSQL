/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uc.nosql.cassandra;

/**
 *
 * @author cuent
 */
public class ItemUser {

    private int cutomer;
    private String asin;
    private String title;
    private String name;

    public ItemUser(int cutomer, String asin, String title, String name) {
        this.cutomer = cutomer;
        this.asin = asin;
        this.title = title;
        this.name = name;
    }

    public double getCutomer() {
        return cutomer;
    }

    public void setCutomer(int cutomer) {
        this.cutomer = cutomer;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
