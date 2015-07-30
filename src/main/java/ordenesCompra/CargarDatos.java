/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenesCompra;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author hduser
 */
public class CargarDatos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        JSONParser parser = new JSONParser();

        try {
            FileReader fileReader = new FileReader("p.json");
            Object obj = parser.parse(fileReader);
//            JSONArray msg = (JSONArray) jsonObject.get("categories");

            JSONArray msg = (JSONArray) obj;
            Iterator<JSONObject> iterator = msg.iterator();
            System.out.println(msg.size());
            while (iterator.hasNext()) {
                JSONObject json = iterator.next();

                String name = (String) json.get("asin");
                System.out.println(name);

                name = (String) json.get("group");
                System.out.println(name);

                name = (String) json.get("title");
                System.out.println(name);

                JSONObject review = (JSONObject) json.get("review_info");
                JSONArray reviews = (JSONArray) review.get("reviews");

                Iterator<JSONObject> iteratorReview = reviews.iterator();

                while (iteratorReview.hasNext()) {
                    JSONObject reviewObject = iteratorReview.next();
                    System.out.println(reviewObject.get("customer"));
                    System.out.println(reviewObject.get("rating"));
                    System.out.println(reviewObject.get("votes"));
                    System.out.println(reviewObject.get("helpful"));

                }
                Long long1 = (Long) review.get("total");
                System.out.println(long1);
                Double double1 = (Double) review.get("avg");
                System.out.println(double1);

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
