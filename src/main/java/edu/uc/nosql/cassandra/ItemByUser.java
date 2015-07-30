/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uc.nosql.cassandra;

import edu.uc.nosql.general.JsfUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author cuent
 */
@ManagedBean(name = "itemByUser")
@SessionScoped
public class ItemByUser implements Serializable {

    private List<ItemUser> datos = new ArrayList<>();
    private ItemUser itemUser;
    private String usuario;

    @PostConstruct
    public void init() {

    }

    public List<ItemUser> getDatos() {
        return datos;
    }

    public void setDatos(List<ItemUser> datos) {
        this.datos = datos;
    }

    public ItemUser getItemUser() {
        return itemUser;
    }

    public void setItemUser(ItemUser itemUser) {
        this.itemUser = itemUser;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void regresarPagina() {
        String toUrl = "/cassandra/product_by_user.xhtml";
        FacesContext ctx = FacesContext.getCurrentInstance();

        ExternalContext extContext = ctx.getExternalContext();
        String url = extContext.encodeActionURL(ctx.getApplication().
                getViewHandler().getActionURL(ctx, toUrl));

        try {
            extContext.redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(ItemByUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listar() {
        System.out.println("Listar..." + usuario);

        ItemUserFacade itemUserFacade = new ItemUserFacade();
        setDatos(itemUserFacade.queryGetItemByUser(this.getUsuario()));
        //itemUserFacade.close();

        if (getDatos().isEmpty()) {
            String mensaje = "Al usuario " + usuario + " le gusta 0 productos";
            JsfUtil.addErrorMessage(mensaje);
        } else {
            String toUrl = "/cassandra/list_product_by_user.xhtml";
            FacesContext ctx = FacesContext.getCurrentInstance();

            ExternalContext extContext = ctx.getExternalContext();
            String url = extContext.encodeActionURL(ctx.getApplication().
                    getViewHandler().getActionURL(ctx, toUrl));

            try {
                extContext.redirect(url);
            } catch (IOException ex) {
                Logger.getLogger(ItemByUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
