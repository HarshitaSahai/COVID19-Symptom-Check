package com.harshita.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class question3 extends AppCompatActivity {

    //WebView wv ;
    //String url = "https://covid-19.ada.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question3);
        //this.wv = (WebView) findViewById(R.id.webView);

        //wv.setWebViewClient(new WebViewClient());
        //wv.getSettings().setJavaScriptEnabled(true);
        //wv.loadUrl(url);
        addListenerOnButtonClick2();
    }
    private void  addListenerOnButtonClick2()
    {

        Button getstarted2 = (Button)findViewById(R.id.toq4);
        getstarted2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {       Intent i2 = getIntent();
                    String jsonString = i2.getStringExtra("jsonObject");
                    JSONObject obj3 = null;
                    JSONArray evidence = new JSONArray();
                    CheckBox first3,second3,third3,fourth3;
                    first3 = (CheckBox)findViewById(R.id.first3);
                    second3 = (CheckBox)findViewById(R.id.second3);
                    third3 = (CheckBox)findViewById(R.id.third3);
                    fourth3 = (CheckBox)findViewById(R.id.fourth3);
                    JSONObject evidence_subJson1 = new JSONObject();
                    JSONObject evidence_subJson2 = new JSONObject();
                    JSONObject evidence_subJson3 = new JSONObject();
                    JSONObject evidence_subJson4 = new JSONObject();
                    if(first3.isChecked())
                    {
                        try {
                        evidence_subJson1.put("id","p_18");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            evidence_subJson1.put("choice_id","present");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        evidence.put(evidence_subJson1);


                    }else{

                        try {
                            evidence_subJson1.put("id","p_18");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            evidence_subJson1.put("choice_id","absent");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        evidence.put(evidence_subJson1);
                    }
                    if(second3.isChecked())
                    {
                        try {
                            evidence_subJson2.put("id","p_19");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            evidence_subJson2.put("choice_id","present");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        evidence.put(evidence_subJson2);

                    }else{
                        try {
                            evidence_subJson2.put("id","p_19");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            evidence_subJson2.put("choice_id","absent");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        evidence.put(evidence_subJson2);


                    }
                    if(third3.isChecked()){
                        try {
                            evidence_subJson3.put("id","p_22");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            evidence_subJson3.put("choice_id","present");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        evidence.put(evidence_subJson3);


                    }
                    else{
                        try {
                            evidence_subJson3.put("id","p_22");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            evidence_subJson3.put("choice_id","absent");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        evidence.put(evidence_subJson3);


                    }
                    if(fourth3.isChecked()){
                        try {
                            evidence_subJson4.put("id","p_24");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            evidence_subJson4.put("choice_id","present");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        evidence.put(evidence_subJson4);


                    }
                    else{
                        try {
                            evidence_subJson4.put("id","p_24");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            evidence_subJson4.put("choice_id","absent");
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        evidence.put(evidence_subJson4);




                    }





                try {
                        obj3 = new JSONObject(jsonString);
                        obj3.put("evidence", evidence);
                        Log.wtf("object here",obj3.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Intent i3 = new Intent(question3.this,question4.class).putExtra("jsonObject",obj3.toString());
                    startActivity(i3);


            }
        });
    }




}