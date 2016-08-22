package com.example.smk.Obj_get;

/**
 * Created by Ihor on 30.07.2016.
 */
import android.graphics.Bitmap;
import android.util.Log;

import com.example.smk.Load_obj.LoadComentsDescTask;
import com.example.smk.Load_obj.LoadProductDescTask;
import com.example.smk.Load_obj.LoadProductImageTask;
import com.example.smk.Obj_get.Coments;
import com.example.smk.Obj_get.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class JSONClass {
    private LoadProductDescTask productDeskTask;
    private LoadProductImageTask productImageTask;
    private LoadComentsDescTask comentsDeskTask;

    public List<Product> getProducts(String api) {
        List<Product> listOfProducts = new ArrayList<Product>();
        productDeskTask = new LoadProductDescTask();
        productDeskTask.execute(api);
        JSONArray json = null;
        try {
            json = new JSONArray(productDeskTask.get());

            for (int i = 0; i < json.length(); i++) {
                JSONObject object = json.getJSONObject(i);
                listOfProducts.add(new Product(object.getInt("id"),
                        getImageOfProduct(object.getString("img")), object
                        .getString("text"), object.getString("title")));
            }

        } catch (InterruptedException e) {
            Log.e("ME", e.toString());
        } catch (ExecutionException e) {
            Log.e("ME", e.toString());
        } catch (JSONException e) {
            Log.e("ME", e.toString());
        }
        return listOfProducts;
    }
    public List<Coments> getComents(String api) {
        List<Coments> listOfComents = new ArrayList<Coments>();
        comentsDeskTask = new LoadComentsDescTask();
        comentsDeskTask.execute(api);
        JSONArray json = null;
        try {
            json = new JSONArray(comentsDeskTask.get());

            for (int i = 0; i < json.length(); i++) {
                JSONObject object = json.getJSONObject(i);
                listOfComents.add(new Coments(object.getInt("id"),
                         object.getString("rate"), object.getString("text")));
            }

        } catch (InterruptedException e) {
            Log.e("ME", e.toString());
        } catch (ExecutionException e) {
            Log.e("ME", e.toString());
        } catch (JSONException e) {
            Log.e("ME", e.toString());
        }
        return listOfComents;
    }
    public List<Coments> getComents2(String api) {
        List<Coments> listOfComents = new ArrayList<Coments>();
        comentsDeskTask = new LoadComentsDescTask();
        comentsDeskTask.execute(api);
        JSONArray json = null;
        try {
            json = new JSONArray(comentsDeskTask.get());

            for (int i = 0; i < json.length(); i++) {
                JSONObject object = json.getJSONObject(i);
                listOfComents.add(new Coments(object.getInt("id"),
                        object.getString("rate"), object.getString("text")));
            }

        } catch (InterruptedException e) {
            Log.e("ME", e.toString());
        } catch (ExecutionException e) {
            Log.e("ME", e.toString());
        } catch (JSONException e) {
            Log.e("ME", e.toString());
        }
        return listOfComents;
    }
    public Bitmap getImageOfProduct(String url) {
        productImageTask = new LoadProductImageTask();
        productImageTask.execute(url);
        Bitmap image = null;
        try {
            image = productImageTask.get();
        } catch (InterruptedException e) {
            Log.e("ME", e.toString());
        } catch (ExecutionException e) {
            Log.e("ME", e.toString());
        }
        return image;
    }

}