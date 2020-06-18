package com.harshita.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class question5ifyesforall extends AppCompatActivity {

    private JSONArray evidence;
    //WebView wv ;
    //String url = "https://covid-19.ada.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question5ifyesforall);
        //this.wv = (WebView) findViewById(R.id.webView);

        //wv.setWebViewClient(new WebViewClient());
        //wv.getSettings().setJavaScriptEnabled(true);
        //wv.loadUrl(url);
        addListenerOnButtonClick2();
    }
    private void  addListenerOnButtonClick2()
    {

        Button getstarted2 = (Button)findViewById(R.id.from5subtoq6);
        getstarted2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i5all= getIntent();
                String jsonString = i5all.getStringExtra("jsonObject");
                try{
                    JSONObject object = new JSONObject(i5all.getStringExtra("jsonObject"));

                    evidence = object.getJSONArray("evidence");
                }catch(Exception e){e.printStackTrace();}
                JSONObject obj5all = null;

                CheckBox y1all,y2all,y3all,n1all,n2all,n3all;
                y1all = (CheckBox)findViewById(R.id.yall1);
                y2all = (CheckBox)findViewById(R.id.yall2);
                y3all = (CheckBox)findViewById(R.id.yall3);
                n1all = (CheckBox)findViewById(R.id.nall1);
                n2all = (CheckBox)findViewById(R.id.nall2);
                n3all = (CheckBox)findViewById(R.id.nall3);

                JSONObject evidence_subJson10ally1 = new JSONObject();
                JSONObject evidence_subJson10ally2 = new JSONObject();
                JSONObject evidence_subJson10ally3 = new JSONObject();
                JSONObject evidence_subJson10alln1 = new JSONObject();
                JSONObject evidence_subJson10alln2 = new JSONObject();
                JSONObject evidence_subJson10alln3 = new JSONObject();
                int counter = 0;
                int counterforyes = 0;
                if(y1all.isChecked())
                {

                    counterforyes = counterforyes + 1;
                    counter = counter + 1;
                    try {
                        evidence_subJson10ally1.put("id","s_12");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson10ally1.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson10ally1);

                }
                if(n1all.isChecked())
                {
                    counter = counter + 1;
                    try {
                        evidence_subJson10alln1.put("id","s_12");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson10alln1.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    evidence.put(evidence_subJson10alln1);

                }
                if(y2all.isChecked())
                {

                    counterforyes = counterforyes + 1;
                    counter = counter + 1;
                    try {
                        evidence_subJson10ally2.put("id","s_13");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson10ally2.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson10ally2);

                }
                if(n2all.isChecked())
                {
                    counter = counter + 1;
                    try {
                        evidence_subJson10alln2.put("id","s_13");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson10alln2.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    evidence.put(evidence_subJson10alln2);

                }
                if(y3all.isChecked())
                {

                    counterforyes = counterforyes + 1;
                    counter = counter + 1;
                    try {
                        evidence_subJson10ally3.put("id","s_14");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson10ally3.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson10ally3);

                }
                if(n3all.isChecked())
                {
                    counter = counter + 1;
                    try {
                        evidence_subJson10alln3.put("id","s_14");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson10alln3.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    evidence.put(evidence_subJson10alln3);

                }

                if(counter != 3)
                    Toast.makeText(question5ifyesforall.this, "Please select one option from each category", Toast.LENGTH_SHORT).show();
                else if(counterforyes > 0 )
                {
                    try {
                        obj5all = new JSONObject(jsonString);
                        obj5all.put("evidence", evidence);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    Intent i52 = new Intent(question5ifyesforall.this, result.class).putExtra("jsonObject", obj5all.toString());
                    startActivity(i52);


                }
                else {
                    try {
                        obj5all = new JSONObject(jsonString);
                        obj5all.put("evidence", evidence);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    Intent i52 = new Intent(question5ifyesforall.this, question6.class).putExtra("jsonObject", obj5all.toString());
                    startActivity(i52);
                }


            }
        });
    }


}

