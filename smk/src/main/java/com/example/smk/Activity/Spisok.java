package com.example.smk.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smk.Adapters.CustomArrayAdapter;
import com.example.smk.R;

import java.io.ByteArrayOutputStream;

public class Spisok extends AppCompatActivity {

    final String LOG_TAG = "myLogs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Лист для продуктов
        ListView listV = (ListView) findViewById(R.id.productsList);
        CustomArrayAdapter adapter;
        adapter = new CustomArrayAdapter(this);
        listV.setAdapter(adapter);
        listV.setOnItemClickListener(new ItemList());
    }
        class ItemList implements AdapterView.OnItemClickListener{
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ViewGroup vg = (ViewGroup) view;

                TextView tv = (TextView)   vg.findViewById(R.id.productListName);
                TextView text = (TextView) vg.findViewById(R.id.productListText);
                ImageView img = (ImageView) vg.findViewById(R.id.thumbnailImage);

                //Toast.makeText(Spisok.this, tv.getText().toString(), Toast.LENGTH_LONG).show();

                Intent intent1 = getIntent();
                //String token =  intent1.getExtras().getString("token");
                String token= intent1.getStringExtra("token");
                Intent intent = new Intent(Spisok.this, ActivityTwo.class);

                Bitmap bitmap = ((BitmapDrawable)img.getDrawable()).getBitmap();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                img.setImageBitmap(bitmap);
                intent.putExtra("byteArray", bs.toByteArray());
                intent.putExtra("token", token);
                intent.putExtra("text", text.getText().toString());
                intent.putExtra("fname", tv.getText().toString());
                startActivity(intent);
            }
        }
}
