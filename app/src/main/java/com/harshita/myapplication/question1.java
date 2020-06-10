
package com.harshita.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class question1  extends AppCompatActivity {


    //WebView wv ;
    //String url = "https://covid-19.ada.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question1);
        //this.wv = (WebView) findViewById(R.id.webView);

        //wv.setWebViewClient(new WebViewClient());
        //wv.getSettings().setJavaScriptEnabled(true);
        //wv.loadUrl(url);


        addListenerOnButtonClick();
    }

    public void addListenerOnButtonClick() {
        final RadioButton Male, Female;

        Male = (RadioButton) findViewById(R.id.male);
        Female = (RadioButton) findViewById(R.id.female);

        Button getstarted1 = (Button) findViewById(R.id.toq2);
        getstarted1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                


                if (!Male.isChecked() && !Female.isChecked())
                    Toast.makeText(question1.this, "Please Select Gender", Toast.LENGTH_SHORT).show();

                else {
                    final String gender;
                    if (Male.isChecked())
                        gender = "male";
                    else
                        gender = "female";
                    JSONObject obj1;
                    try {
                        obj1 = new JSONObject();
                        obj1.put("sex", gender);
                    } catch (Exception e) {
                        e.printStackTrace();
                        obj1 = null;
                    }
                    Intent i1 = new Intent(question1.this,question2.class).putExtra("jsonObject",obj1.toString());
                    startActivity(i1);
                }
            }

        });
    }
}

