package com.example.smk.Obj_get;

/**
 * Created by Ihor on 30.07.2016.
 */

import java.util.List;

public class GetClass {
    private static final String API_GET_PRODUCTS = "/api/products/";
    private static final String API_GET_COMENTS1 = "/api/reviews/1";
    private static final String API_GET_COMENTS2 = "/api/reviews/2";

    private JSONClass json;
    private List<Product> list;
    private List<Coments> listComents;
    private List<Coments> listComents2;

    public List<Product> getProducts() {
       json = new JSONClass();
        list = json.getProducts(API_GET_PRODUCTS);
        return list;
    }

    public List<Coments> getComents() {
        json = new JSONClass();
        listComents = json.getComents(API_GET_COMENTS1);
        return listComents;
    }

    public List<Coments> getComents2() {
        json = new JSONClass();
        listComents2 = json.getComents2(API_GET_COMENTS2);
        return listComents2;
    }

    public String[] getTitlesOfProducts() {
        if (list == null) {
            this.getProducts();
        }
        String[] titles = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            titles[i] = list.get(i).getTitle();
        }
        return titles;
    }

    public String[] getTextesOfComents() {
        if (list == null) {
            this.getComents();
        }
        String[] texts = new String[listComents.size()];
        for (int i = 0; i < listComents.size(); i++) {
            texts[i] = listComents.get(i).getText();
        }
        return texts;
    }

    public String[] getTextesOfComents2() {
        if (list == null) {
            this.getComents2();
        }
        String[] texts2 = new String[listComents2.size()];
        for (int i = 0; i < listComents2.size(); i++) {
            texts2[i] = listComents2.get(i).getText();
        }
        return texts2;
    }

}
