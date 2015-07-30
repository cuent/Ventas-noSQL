/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenesCompra;

import com.google.gson.JsonObject;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import org.lightcouch.CouchDbClient;
import org.lightcouch.Response;

/**
 *
 * @author hduser
 */
@ManagedBean(name = "ordenesCompraController")
@javax.faces.bean.SessionScoped
public class OrdenesCompraController implements Serializable {

    /**
     * Creates a new instance of OrdenesCompraController
     */
    List<Producto> productos;
    CouchDbClient dbClient2;
    int cantidadVisualizacion;
    Producto productoSeleccionado;

    public OrdenesCompraController() {
        dbClient2 = new CouchDbClient("productos", true, "http", "192.168.0.3", 5984, null, null);
        productos = new ArrayList<>();
        cantidadVisualizacion = 0;
        productoSeleccionado=new Producto();
    }

    public int getCantidadVisualizacion() {
        return cantidadVisualizacion;
    }

    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(Producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

   

    public void setCantidadVisualizacion(int cantidadVisualizacion) {
        this.cantidadVisualizacion = cantidadVisualizacion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void crossPersistence() {
        try {
///SENTENCIAS DE PERSISTENCIA ENTRE MASTERS Y ESCLAVOS

            //REPLICACION A master
            Process p = null;
            String egsCrear = String.format("curl -X POST -H 'Content-Type: application/json' http://192.168.0.3:5984/_replicate -d '{\"source\":\"ventas\", \"target\":\"http://192.168.0.4:5984/ventas\", \"continuous\":true}'");
            p = Runtime.getRuntime().exec(egsCrear);
            p.waitFor();
            p.destroy();

            egsCrear = String.format("curl -X POST -H 'Content-Type: application/json' http://192.168.0.3:5984/_replicate -d '{\"source\":\"ventas\", \"target\":\"http://192.168.0.5:5984/ventas\", \"continuous\":true}'");
            p = Runtime.getRuntime().exec(egsCrear);
            p.waitFor();
            p.destroy();

            //REPLICACION esclavos
            egsCrear = String.format("curl -X POST -H 'Content-Type: application/json' http://192.168.0.4:5984/_replicate -d '{\"source\":\"ventas\", \"target\":\"http://192.168.0.5:5984/ventas\", \"continuous\":true}'");
            p = Runtime.getRuntime().exec(egsCrear);
            p.waitFor();
            p.destroy();
            egsCrear = String.format("curl -X POST -H 'Content-Type: application/json' http://192.168.0.5:5984/_replicate -d '{\"source\":\"ventas\", \"target\":\"http://192.168.0.4:5984/ventas\", \"continuous\":true}'");
            p = Runtime.getRuntime().exec(egsCrear);
            p.waitFor();
            p.destroy();

            //REPLICACION esclavos a master
            egsCrear = String.format("curl -X POST -H 'Content-Type: application/json' http://192.168.0.4:5984/_replicate -d '{\"source\":\"ventas\", \"target\":\"http://192.168.0.3:5984/ventas\", \"continuous\":true}'");
            p = Runtime.getRuntime().exec(egsCrear);
            p.waitFor();
            p.destroy();
            egsCrear = String.format("curl -X POST -H 'Content-Type: application/json' http://192.168.0.5:5984/_replicate -d '{\"source\":\"ventas\", \"target\":\"http://192.168.0.3:5984/ventas\", \"continuous\":true}'");
            p = Runtime.getRuntime().exec(egsCrear);
            p.waitFor();
            p.destroy();

        } catch (IOException ex) {
            Logger.getLogger(OrdenesCompraController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            System.out.println("Error en establlecer la persistencia");
        }

    }

    public void cargarProductos() {
//        productos = (List<Producto>) dbClient2.view("_all_docs").includeDocs(true).query(Producto.class);

//String uri = dbClient2.getBaseUri() + "_fti/local/mydb/_design/example/by_title?q=hello";
//
//JsonObject result = dbClient2.findAny(JsonObject.class, uri);
//        String baseURI = dbClient2.getBaseUri().toString();
//        String dbURI = dbClient2.getDBUri().toString();
//        String uri = dbURI + "/productos/0000495977114d0ea5eb85c5e9af07b5";
//        JsonObject stats = dbClient2.findAny(JsonObject.class, uri);
//        String aux=stats.toString();
        Producto foo = dbClient2.find(Producto.class, "0026879077");
        List<Producto> p = (List<Producto>) dbClient2.view("_all_docs").includeDocs(true).limit(cantidadVisualizacion).query(Producto.class);

        productos.addAll(p);
//foo = dbClient2.find(Producto.class, "doc-id", "doc-rev");

    }

    public void anadirACarrito(String id) {
    }

}
