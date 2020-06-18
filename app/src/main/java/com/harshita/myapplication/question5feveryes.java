package com.harshita.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

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

import java.util.HashMap;
import java.util.Map;

public class question5feveryes extends AppCompatActivity {

    private JSONArray evidence;
    //WebView wv ;
    //String url = "https://covid-19.ada.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question5feveryes);
        //this.wv = (WebView) findViewById(R.id.webView);

        //wv.setWebViewClient(new WebViewClient());
        //wv.getSettings().setJavaScriptEnabled(true);
        //wv.loadUrl(url);
        addListenerOnButtonClick2();
    }
    private void  addListenerOnButtonClick2()
    {

        Button getstarted2 = (Button)findViewById(R.id.toq52);
        getstarted2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i51= getIntent();
                String jsonString = i51.getStringExtra("jsonObject");
                try{
                    JSONObject object = new JSONObject(i51.getStringExtra("jsonObject"));

                    evidence = object.getJSONArray("evidence");
                }catch(Exception e){e.printStackTrace();}
                JSONObject obj51 = null;

                CheckBox y11,y12,y13,y14;
                y11 = (CheckBox)findViewById(R.id.first51);
                y12 = (CheckBox)findViewById(R.id.second51);
                y13 = (CheckBox)findViewById(R.id.third51);
                y14 = (CheckBox)findViewById(R.id.fourth51);
                JSONObject evidence_subJson101 = new JSONObject();
                JSONObject evidence_subJson102 = new JSONObject();
                JSONObject evidence_subJson103 = new JSONObject();
                JSONObject evidence_subJson104 = new JSONObject();
                int counter = 0;
                if(y11.isChecked())
                {
                    try {
                        evidence_subJson101.put("id","s_22");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson101.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson101);
                    counter = counter + 1;
                }else{
                    try {
                        evidence_subJson101.put("id","s_22");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson101.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    evidence.put(evidence_subJson101);

                }
                if(y12.isChecked())
                {
                    try {
                        evidence_subJson102.put("id","s_23");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson102.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson102);
                    counter = counter + 1;

                }else{
                    try {
                        evidence_subJson102.put("id","s_23");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson102.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson102);

                }
                if(y13.isChecked())
                {
                    try {
                        evidence_subJson103.put("id","s_4");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson103.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson103);
                    counter = counter + 1;

                }else{
                    try {
                        evidence_subJson103.put("id","s_4");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson103.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson103);

                }
                if(y14.isChecked())
                {
                    Toast.makeText(question5feveryes.this, "Please check your fever else the application won't give a result", Toast.LENGTH_SHORT).show();
                    try {
                        evidence_subJson104.put("id","s_5");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson104.put("choice_id","present");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson103);
                    counter = counter + 1;

                }else{
                    try {
                        evidence_subJson104.put("id","s_5");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        evidence_subJson104.put("choice_id","absent");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    evidence.put(evidence_subJson103);
                }
                if(counter != 1)
                    Toast.makeText(question5feveryes.this, "Please select one option from the following", Toast.LENGTH_SHORT).show();
                else {
                    try {
                        obj51 = new JSONObject(jsonString);
                        obj51.put("evidence", evidence);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    getvr(obj51);
                    Intent i52 = new Intent(question5feveryes.this, question5ifyesforall.class).putExtra("jsonObject", obj51.toString());
                    startActivity(i52);
                }


            }
        });
    }
    public <name> void getvr(JSONObject obj)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.infermedica.com/covid19/diagnosis";

        JsonObjectRequest getRequest = new JsonObjectRequest(
                Request.Method.POST
                ,  url,obj, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                Log.d("Response", response.toString());
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
