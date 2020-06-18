package com.harshita.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class question5 extends AppCompatActivity {

    //WebView wv ;
    //String url = "https://covid-19.ada.com";
    private JSONArray evidence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question5);
        //this.wv = (WebView) findViewById(R.id.webView);

        //wv.setWebViewClient(new WebViewClient());
        //wv.getSettings().setJavaScriptEnabled(true);
        //wv.loadUrl(url);
        addListenerOnButtonClick2();
    }
    private void  addListenerOnButtonClick2()
    {

        Button getstarted2 = (Button)findViewById(R.id.toq6);
        getstarted2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {   Intent i4 = getIntent();
                String jsonString = i4.getStringExtra("jsonObject");
                try{
                    JSONObject object = new JSONObject(i4.getStringExtra("jsonObject"));

                    evidence = object.getJSONArray("evidence");
                }catch(Exception e){e.printStackTrace();}
                JSONObject obj5 = null;

                CheckBox y1,n1,y2,n2,y3,n3;
                y1 = (CheckBox)findViewById(R.id.y1);
                y2 = (CheckBox)findViewById(R.id.y2);
                y3 = (CheckBox)findViewById(R.id.y3);
                n1 = (CheckBox)findViewById(R.id.n1);
                n2 = (CheckBox)findViewById(R.id.n2);
                n3 = (CheckBox)findViewById(R.id.n3);
                JSONObject evidence_subJson10 = new JSONObject();
                JSONObject evidence_subJson11 = new JSONObject();
                JSONObject evidence_subJson12 = new JSONObject();

                int counter =0;

                if(y1.isChecked())
                {


                    try {
                        evidence_subJson10.put("id","s_0");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson10.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson10);
                    counter = counter + 1;

                }
                if(y2.isChecked())
                {
                    try {
                        evidence_subJson11.put("id","s_1");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson11.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson11);
                    counter = counter + 1;
                }
                if(y3.isChecked())
                {
                    try {
                        evidence_subJson12.put("id","s_2");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson12.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson12);
                    counter = counter + 1;
                }
                if(n1.isChecked())
                {
                    try {
                        evidence_subJson10.put("id","s_0");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson10.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson10);
                    counter = counter + 1;
                }
                if(n2.isChecked())
                {
                    try {
                        evidence_subJson11.put("id","s_1");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson11.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson11);
                    counter = counter + 1;
                }
                if(n3.isChecked())
                {
                    try {
                        evidence_subJson12.put("id","s_2");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson12.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson12);
                    counter = counter + 1;
                }
                if(counter != 3)
                    Toast.makeText(question5.this, "Please select option in each category", Toast.LENGTH_SHORT).show();
                else
                    {
                        try {
                    obj5 = new JSONObject(jsonString);
                    obj5.put("evidence", evidence);
                    Log.wtf("object here",obj5.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(y1.isChecked())
                {
                    Intent i51 = new Intent(question5.this,question5feveryes.class).putExtra("jsonObject",obj5.toString());
                    startActivity(i51);

                }
                else if(y2.isChecked()){
                    Intent i51 = new Intent(question5.this,question5ifyesforall.class).putExtra("jsonObject",obj5.toString());
                    startActivity(i51);


                }else if(y3.isChecked()){
                    Intent i51 = new Intent(question5.this,question5ifyesforall.class).putExtra("jsonObject",obj5.toString());
                    startActivity(i51);


                }
                else{

                Intent i5 = new Intent(question5.this,question6.class).putExtra("jsonObject",obj5.toString());
                startActivity(i5);}}


            }
        });
    }





}
