package com.harshita.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class question4 extends AppCompatActivity {

    //WebView wv ;
    //String url = "https://covid-19.ada.com";
    private JSONArray evidence;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question4);
        //this.wv = (WebView) findViewById(R.id.webView);

        //wv.setWebViewClient(new WebViewClient());
        //wv.getSettings().setJavaScriptEnabled(true);
        //wv.loadUrl(url);
        addListenerOnButtonClick2();
    }
    private void  addListenerOnButtonClick2()
    {

        Button getstarted2 = (Button)findViewById(R.id.toq5);
        getstarted2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {   Intent i3 = getIntent();
                String jsonString = i3.getStringExtra("jsonObject");
                try{
                    JSONObject object = new JSONObject(i3.getStringExtra("jsonObject"));

                    evidence = object.getJSONArray("evidence");
                }catch(Exception e){e.printStackTrace();}
                JSONObject obj4 = null;

                CheckBox first4,second4,third4,fourth4,fifth4;
                first4 = (CheckBox)findViewById(R.id.first4);
                second4 = (CheckBox)findViewById(R.id.second4);
                third4 = (CheckBox)findViewById(R.id.third4);
                fourth4 = (CheckBox)findViewById(R.id.fourth4);
                fifth4 = (CheckBox)findViewById(R.id.fifth4);
                JSONObject evidence_subJson5 = new JSONObject();
                JSONObject evidence_subJson6 = new JSONObject();
                JSONObject evidence_subJson7 = new JSONObject();
                JSONObject evidence_subJson8 = new JSONObject();
                JSONObject evidence_subJson9 = new JSONObject();

                if(first4.isChecked())
                {


                    try {
                        evidence_subJson5.put("id","p_23");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson5.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson5);

                }else{
                    try {
                        evidence_subJson5.put("id","p_23");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson5.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    evidence.put(evidence_subJson5);

                }
                if(second4.isChecked())
                {
                    try {
                        evidence_subJson6.put("id","p_17");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson6.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    evidence.put(evidence_subJson6);
                }else{
                    try {
                        evidence_subJson6.put("id","p_17");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson6.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    evidence.put(evidence_subJson6);

                }
                if(third4.isChecked()){
                    try {
                        evidence_subJson7.put("id","p_16");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson7.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    evidence.put(evidence_subJson7);
                }
                else{
                    try {
                        evidence_subJson7.put("id","p_16");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson7.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    evidence.put(evidence_subJson7);

                }
                if(fourth4.isChecked()){
                    try {
                        evidence_subJson8.put("id","p_20");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson8.put("choice_id","prsent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    evidence.put(evidence_subJson8);
                }
                else{
                    try {
                        evidence_subJson8.put("id","p_20");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson8.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    evidence.put(evidence_subJson8);

                }
                if(fifth4.isChecked()){
                    try {
                        evidence_subJson9.put("id","p_21");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson9.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    evidence.put(evidence_subJson5);

                }
                else{
                    try {
                        evidence_subJson9.put("id","p_21");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson9.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    evidence.put(evidence_subJson9);

                }





                try {
                    obj4 = new JSONObject(jsonString);
                    obj4.put("evidence", evidence);

                    Log.wtf("object here",obj4.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Intent i4 = new Intent(question4.this,question5.class).putExtra("jsonObject",obj4.toString());
                startActivity(i4);


            }
                
        });
    }



}
