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

public class question7 extends AppCompatActivity {

    //WebView wv ;
    //String url = "https://covid-19.ada.com";
    private JSONArray evidence;




    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question7);
        //this.wv = (WebView) findViewById(R.id.webView);

        //wv.setWebViewClient(new WebViewClient());
        //wv.getSettings().setJavaScriptEnabled(true);
        //wv.loadUrl(url);
        addListenerOnButtonClick2();
    }
    private void  addListenerOnButtonClick2()
    {

        Button getstarted2 = (Button)findViewById(R.id.tor);
        getstarted2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {   Intent i6 = getIntent();

                String jsonString = i6.getStringExtra("jsonObject");
                try{
                    JSONObject object = new JSONObject(i6.getStringExtra("jsonObject"));

                    evidence = object.getJSONArray("evidence");
                }catch(Exception e){e.printStackTrace();}
                JSONObject obj7 = null;

                CheckBox first7,second7,third7,fourth7,fifth7;
                first7 = (CheckBox)findViewById(R.id.first7);
                second7 = (CheckBox)findViewById(R.id.second7);
                third7 = (CheckBox)findViewById(R.id.third7);
                fourth7 = (CheckBox)findViewById(R.id.fourth7);
                fifth7 = (CheckBox)findViewById(R.id.fifth7);
                JSONObject evidence_subJson21 = new JSONObject();
                JSONObject evidence_subJson22 = new JSONObject();
                JSONObject evidence_subJson23 = new JSONObject();
                JSONObject evidence_subJson24 = new JSONObject();
                JSONObject evidence_subJson25 = new JSONObject();

                int counter = 0;

                if(first7.isChecked())
                {
                    try {
                        evidence_subJson21.put("id","p_25");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson21.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson21);
                    counter = counter + 1;

                }else{
                    try {
                        evidence_subJson21.put("id","p_25");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson21.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson21);


                }
                if(second7.isChecked())
                {
                    try {
                        evidence_subJson22.put("id","p_26");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson22.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson22);
                    counter = counter + 1;
                }else{
                    try {
                        evidence_subJson22.put("id","p_26");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson22.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson22);

                }
                if(third7.isChecked()){
                    try {
                        evidence_subJson23.put("id","p_27");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson23.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson23);
                    counter = counter + 1;
                }
                else{
                    try {
                        evidence_subJson23.put("id","p_27");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson23.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson23);

                }
                if(fourth7.isChecked()){
                    try {
                        evidence_subJson24.put("id","p_11");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson24.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson24);

                    counter = counter + 1;
                }
                else{
                    try {
                        evidence_subJson24.put("id","p_11");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson24.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson24);


                }
                if(fifth7.isChecked()){
                    try {
                        evidence_subJson25.put("id","p_15");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson25.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson25);

                    counter = counter + 1;
                }
                else{
                    try {
                        evidence_subJson25.put("id","p_15");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson25.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson25);
                }
                if(counter > 1)
                    Toast.makeText(question7.this, "Please select only one option", Toast.LENGTH_SHORT).show();



                try {
                    obj7 = new JSONObject(jsonString);
                    obj7.put("evidence", evidence);
                    Log.wtf("object here",obj7.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Intent i7 = new Intent(question7.this,result.class).putExtra("jsonObject",obj7.toString());

                startActivity(i7);


            }
        });
    }

}