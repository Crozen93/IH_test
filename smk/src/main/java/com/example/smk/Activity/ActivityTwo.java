package com.example.smk.Activity;

/**
 * Created by Ihor on 01.08.2016.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smk.Adapters.Coments2ArrayAdapter;
import com.example.smk.Adapters.ComentsArrayAdapter;
import com.example.smk.R;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ActivityTwo extends Activity {

    String b = "product1";

    Context c;
    EditText Etext;
    EditText Erate;
    Button sendRevBtn;
    String textRev;
    String rateRev;
    String base_url = null;
    String url1 = "http://smktesting.herokuapp.com/api/reviews/1";
    String url2 = "http://smktesting.herokuapp.com/api/reviews/2";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_sign);

        TextView productName1 = (TextView) findViewById(R.id.productListName1);
        TextView productText = (TextView) findViewById(R.id.productListText1);
        ImageView image = (ImageView) findViewById(R.id.thumbnailImage1);
        Intent intent = getIntent();
        Intent intent1 = getIntent();
        String fName = intent.getStringExtra("fname");
        String text1 = intent.getStringExtra("text");
        final String token = intent1.getExtras().getString("token");

        productName1.setText(fName);
        productText.setText(text1);

        if(getIntent().hasExtra("byteArray")) {
            Bitmap bitmam = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("byteArray"), 0, getIntent().getByteArrayExtra("byteArray").length);
            image.setImageBitmap(bitmam);
        }
     //Лист для коментов
        ListView listV = (ListView) findViewById(R.id.comentsList);
        ComentsArrayAdapter  adapter;
        Coments2ArrayAdapter adapter2;
        adapter = new ComentsArrayAdapter(this);
        adapter2 = new Coments2ArrayAdapter(this);
       if (productName1.getText().equals(b)) {
           listV.setAdapter(adapter);
              base_url = url1;
       }else listV.setAdapter(adapter2);
             // base_url = url2;

         //тело Пост запроса коментариев
         //
        c = this;
        Etext = (EditText) findViewById(R.id.input_text);
        Erate = (EditText) findViewById(R.id.input_rate);
        sendRevBtn = (Button) findViewById(R.id.send_rev);

        sendRevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                textRev = Etext.getText() + "";
                rateRev = Erate.getText() + "";

                if ( textRev .length() == 0 || rateRev.length() == 0) {
                    Toast.makeText(c, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if ( textRev.length() > 0 && rateRev.length() > 0) {
                    //Do networking
                    Networking n = new Networking();
                    if (base_url == url1){
                    n.execute(url1, Networking.NETWORK_STATE_COMENTS);}
                    else {n.execute(url2, Networking.NETWORK_STATE_COMENTS);}

                    // Проверка на авторизацыю
                    if ( token == null){
                        Toast.makeText(c, "Please login", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(c, "Review send", Toast.LENGTH_SHORT).show();
                        onCreate(savedInstanceState);
                        onStart();
                        onResume();
                        onPause();
                        onStop();
                        onDestroy();
                    }
                }
            }
        });
    }
    //AsyncTask good for long running tasks
    public class Networking extends AsyncTask {

        public static final int NETWORK_STATE_COMENTS = 1;

        @Override
        protected Object doInBackground(Object[] params) {

            getJson((String) params[0], (Integer) params[1]);
            return null;
        }
    }

    private void getJson(String url, int state) {
        //Do a HTTP POST, more secure than GET

        HttpClient httpClient = new DefaultHttpClient();
        if (base_url == url1){base_url = url1;}else{base_url = url2;}
        HttpPost request = new HttpPost(base_url);

        // Получение токена
        Intent intent1 = getIntent();
        String token = intent1.getExtras().getString("token");
        if(token != null){
            request.addHeader("Authorization" , "Token " + token);
        }
        List<NameValuePair> postParameters = new ArrayList<NameValuePair>();

        boolean valid = false;

        switch (state) {
            case Networking.NETWORK_STATE_COMENTS:
                //Building key value pairs to be accessed on web
                postParameters.add(new BasicNameValuePair("text", textRev));
                postParameters.add(new BasicNameValuePair("rate", rateRev));

                valid = true;
                break;
            default:
                Toast.makeText(c, "Unknown state", Toast.LENGTH_SHORT).show();

        }

        if (valid == true) {
            //Reads everything that comes from server

            BufferedReader bufferedReader = null;
            StringBuffer stringBuffer = new StringBuffer("");
            try {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postParameters);
                request.setEntity(entity);

                //Send off to server
                HttpResponse response = httpClient.execute(request);


                //Reads response and gets content
                bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String line = "";
                String LineSeparator = System.getProperty("line.separator");
                //Read back server output
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line + LineSeparator);
                }

                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            decodeResultIntoJson(stringBuffer.toString());
        } else {
        }
    }

    private void decodeResultIntoJson(String response) {
        if (response.contains("error")) {
            try {
                JSONObject jo = new JSONObject(response);
                String error = jo.getString("error");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            JSONObject jo = new JSONObject(response);

            String success = jo.getString("success");
            String message = jo.getString("message");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}


