package com.example.smk.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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


public class RegisterActivity extends AppCompatActivity {

    Context c;
    EditText regLogin;
    EditText passwordText;
    Button regloginBtn;
    String password;
    String rLogin;
    String url = "http://smktesting.herokuapp.com/api/register/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        c = this;
        regLogin = (EditText) findViewById(R.id.input_login_register);
        passwordText = (EditText) findViewById(R.id.input_password_register);
        regloginBtn = (Button) findViewById(R.id.btn_register);

        regloginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  _("Login button hit");

                rLogin = regLogin.getText() + "";
                password = passwordText.getText() + "";

                if ( rLogin.length() == 0 || password.length() == 0) {
                    Toast.makeText(c, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if ( rLogin.length() > 0 && password.length() > 0) {
                    //Do networking
                    Networking n = new Networking();
                    n.execute(url, Networking.NETWORK_STATE_REGISTER);
                    Toast.makeText(c, "Register Done", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }


    //AsyncTask good for long running tasks
    public class Networking extends AsyncTask {

        public static final int NETWORK_STATE_REGISTER = 1;

        @Override
        protected Object doInBackground(Object[] params) {

            getJson((String) params[0], (Integer) params[1]);
            return null;
        }
    }

    private void getJson(String url, int state) {
        //Do a HTTP POST, more secure than GET
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost request = new HttpPost(url);
        List<NameValuePair> postParameters = new ArrayList<NameValuePair>();

        boolean valid = false;

        switch (state) {
            case Networking.NETWORK_STATE_REGISTER:
                //Building key value pairs to be accessed on web
                postParameters.add(new BasicNameValuePair("username", rLogin));
                postParameters.add(new BasicNameValuePair("password", password));

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