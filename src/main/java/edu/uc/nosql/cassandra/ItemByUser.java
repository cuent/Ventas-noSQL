/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uc.nosql.cassandra;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author cuent
 */
@ManagedBean(name = "itemByUser")
@SessionScoped
public class ItemByUser {

    private List<String> items = new ArrayList<>();
    private List<String> users = new ArrayList<>();
    private String usuario;

    @PostConstruct
    public void init() {
        items.add("1");
        items.add("2");
        items.add("3");

        users.add("Juan");
        users.add("Carlos");
        users.add("Pepe");
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public void listar() {
        System.out.println("Listar..." + usuario);
    }
}
