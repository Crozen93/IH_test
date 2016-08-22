package com.example.smk.Obj_get;

/**
 * Created by Ihor on 06.08.2016.
 */
public class Coments {
    private int id;
    private int prodid;

    private String rate;
    private String text;

    public Coments(int id, String rate, String text) {
        this.id = id;
        this.prodid = prodid;
        this.rate = rate;
        this.text = text;
    }

    public int getId() {
        return id;
    }


    public String getRate() {
        return rate;
    }

    public String getText() {
        return text;
    }

   // @Override
    public String toString() {
        return "Product [title=" + text + "]";
    }
}


