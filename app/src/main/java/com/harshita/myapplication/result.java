package com.harshita.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class result extends AppCompatActivity {

    private JSONArray evidence;

    //String url = "https://covid-19.ada.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        //this.wv = (WebView) findViewById(R.id.webView);

        //wv.setWebViewClient(new WebViewClient());
        //wv.getSettings().setJavaScriptEnabled(true);
        //wv.loadUrl(url);
        addListenerOnButtonClick2();
    }
    private void  addListenerOnButtonClick2()
    {
        Intent i7 = getIntent();

        String jsonString = i7.getStringExtra("jsonObject");
        try{
            JSONObject object = new JSONObject(i7.getStringExtra("jsonObject"));

            evidence = object.getJSONArray("evidence");
        }catch(Exception e){e.printStackTrace();}
        JSONObject obj8 = null;
        try {
            obj8 = new JSONObject(jsonString);

            Log.wtf("object here",obj8.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getvr(obj8);

    }
    public <name> void getvr(JSONObject obj)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.infermedica.com/covid19/triage";

        JsonObjectRequest getRequest = new JsonObjectRequest(
                Request.Method.POST
                ,  url,obj, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {

                try {
                    Process process = Runtime.getRuntime().exec("logcat -d");
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(process.getInputStream()));

                    StringBuilder log=new StringBuilder();
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        log.append(line);
                    }
                    TextView tv = (TextView)findViewById(R.id.result);
                    tv.setText(response.getString("description"));
                } catch (IOException e) {
                    // Handle Exception
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("description", response.toString());
                //textView.setText(response);

                try {
                    String text = response.getString("text");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("ERROR","error => "+error.toString());
                error.printStackTrace();

            }
        }){


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                params.put("App-Id", "fd9740f0");
                params.put("App-Key", "369c63fe2ab752b87ac60189d368a7e1");

                return params;
            }
        };
        queue.add(getRequest);
    }

}