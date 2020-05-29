package com.harshita.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

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

public class question6 extends AppCompatActivity {

    private JSONArray evidence;
    //WebView wv ;
    //String url = "https://covid-19.ada.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question6);
        //this.wv = (WebView) findViewById(R.id.webView);

        //wv.setWebViewClient(new WebViewClient());
        //wv.getSettings().setJavaScriptEnabled(true);
        //wv.loadUrl(url);
        addListenerOnButtonClick2();
    }
    private void  addListenerOnButtonClick2()
    {

        Button getstarted2 = (Button)findViewById(R.id.toq7);
        getstarted2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i5= getIntent();
                String jsonString = i5.getStringExtra("jsonObject");
                try{
                    JSONObject object = new JSONObject(i5.getStringExtra("jsonObject"));

                    evidence = object.getJSONArray("evidence");
                }catch(Exception e){e.printStackTrace();}
                JSONObject obj6 = null;

                CheckBox first6,second6,third6,fourth6,fifth6,sixth6,seventh6,eigth6;
                first6 = (CheckBox)findViewById(R.id.first6);
                second6 = (CheckBox)findViewById(R.id.second6);
                third6 = (CheckBox)findViewById(R.id.third6);
                fourth6 = (CheckBox)findViewById(R.id.fourth6);
                fifth6 = (CheckBox)findViewById(R.id.fifth6);
                sixth6 = (CheckBox)findViewById(R.id.sixth6);
                seventh6 = (CheckBox)findViewById(R.id.seventh6);
                eigth6 = (CheckBox)findViewById(R.id.eigth6);
                JSONObject evidence_subJson13 = new JSONObject();
                JSONObject evidence_subJson14 = new JSONObject();
                JSONObject evidence_subJson15 = new JSONObject();
                JSONObject evidence_subJson16 = new JSONObject();
                JSONObject evidence_subJson17 = new JSONObject();
                JSONObject evidence_subJson18 = new JSONObject();
                JSONObject evidence_subJson19 = new JSONObject();
                JSONObject evidence_subJson20 = new JSONObject();


                if(first6.isChecked())
                {
                    try {
                        evidence_subJson13.put("id","s_15");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson13.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson13);



                }else{
                    try {
                        evidence_subJson13.put("id","s_15");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson13.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson13);

                }
                if(second6.isChecked())
                {
                    try {
                        evidence_subJson14.put("id","s_16");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson14.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson14);
                }else{

                    try {
                        evidence_subJson14.put("id","s_16");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson14.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson14);
                }
                if(third6.isChecked()){
                    try {
                        evidence_subJson15.put("id","s_17");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson15.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson15);

                }
                else{
                    try {
                        evidence_subJson15.put("id","s_17");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson15.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson15);

                }
                if(fourth6.isChecked()){
                    try {
                        evidence_subJson16.put("id","s_18");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson16.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson16);

                }
                else{
                    try {
                        evidence_subJson16.put("id","s_18");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson16.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson16);


                }
                if(fifth6.isChecked()){
                    try {
                        evidence_subJson17.put("id","s_19");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson17.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson17);


                }
                else{
                    try {
                        evidence_subJson17.put("id","s_19");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson17.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson17);

                }
                if(sixth6.isChecked()){
                    try {
                        evidence_subJson18.put("id","s_20");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson18.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson18);

                }
                else{
                    try {
                        evidence_subJson18.put("id","s_20");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson18.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson18);

                }
                if(seventh6.isChecked()){
                    try {
                        evidence_subJson19.put("id","s_21");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson19.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson19);

                }
                else{
                    try {
                        evidence_subJson19.put("id","s_21");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson19.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson19);

                }
                if(eigth6.isChecked()){
                    try {
                        evidence_subJson20.put("id","s_24");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson20.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson20);


                }
                else{
                    try {
                        evidence_subJson20.put("id","s_24");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson20.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson20);

                }






                try {
                    obj6 = new JSONObject(jsonString);
                    obj6.put("evidence", evidence);
                    Log.wtf("object here",obj6.toString());
                } catch (JSONException e) {
                    e.printStackTrace();

                }

                Intent i6 = new Intent(question6.this,question7.class).putExtra("jsonObject",obj6.toString());
                startActivity(i6);


            }
        });
    }


}