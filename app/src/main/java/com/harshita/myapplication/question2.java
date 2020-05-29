package com.harshita.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class question2 extends AppCompatActivity {

    //WebView wv ;
    //String url = "https://covid-19.ada.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question2);
        //this.wv = (WebView) findViewById(R.id.webView);

        //wv.setWebViewClient(new WebViewClient());
        //wv.getSettings().setJavaScriptEnabled(true);
        //wv.loadUrl(url);
        addListenerOnButtonClick2();
    }
    private void  addListenerOnButtonClick2()
    {

        Button getstarted2 = (Button)findViewById(R.id.toq3);
        getstarted2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                EditText etUserName = (EditText) findViewById(R.id.age);
                String strUserName = etUserName.getText().toString();
                if(TextUtils.isEmpty(strUserName))
                    Toast.makeText(question2.this, "Please Enter Age", Toast.LENGTH_SHORT).show();
                else
                {
                    Intent i1 = getIntent();

                    String jsonString = i1.getStringExtra("jsonObject");
                    JSONObject obj2 = null;

                    try {
                        obj2 = new JSONObject(jsonString);

                        obj2.put("age",Integer.parseInt(strUserName));
                        Log.wtf("object here",obj2.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Intent i2 = new Intent(question2.this,question3.class).putExtra("jsonObject",obj2.toString());
                    startActivity(i2);
                }

            }
        });
    }


}
