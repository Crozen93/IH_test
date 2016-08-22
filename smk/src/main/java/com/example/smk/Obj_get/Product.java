package com.example.smk.Obj_get;

/**
 * Created by Ihor on 30.07.2016.
 */
import android.graphics.Bitmap;

public class Product {
    private int id;
    private Bitmap img;
    private String text;
    private String title;

    public Product(int id, Bitmap img, String text, String title) {
        this.id = id;
        this.img = img;
        this.text = text;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public Bitmap getImg() {
        return img;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Product [title=" + title + "]";
    }
}
