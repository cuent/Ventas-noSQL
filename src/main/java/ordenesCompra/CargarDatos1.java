/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenesCompra;

import com.google.gson.JsonObject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.lightcouch.CouchDbClient;

/**
 *
 * @author hduser
 */
public class CargarDatos1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        JSONParser parser = new JSONParser();

        try {
            FileReader fileReader = new FileReader("/opt/productos2.json");
            Object obj = parser.parse(fileReader);
//            JSONArray msg = (JSONArray) jsonObject.get("categories");

            JSONArray msg = (JSONArray) obj;
            Iterator<JSONObject> iterator = msg.iterator();
            System.out.println(msg.size());
            while (iterator.hasNext()) {
                JSONObject json = iterator.next();
                System.out.println(json.toJSONString());
                String jsonstr = json.toJSONString();
                CouchDbClient dbClient2 = new CouchDbClient("productos", true, "http", "192.168.0.3", 5984,null,null);
                JsonObject jsonobj = dbClient2.getGson().fromJson(jsonstr, JsonObject.class);
                dbClient2.save(jsonobj);

            }

//            String name = (String) jsonObject.get("asin");
//            System.out.println(name);
//          String name2 =  (String) jsonObject.get("categories");
//            System.out.println(name2);
            // loop array
//            JSONArray msg = (JSONArray) jsonObject.get("categories");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
