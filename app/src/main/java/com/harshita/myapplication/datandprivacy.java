package com.harshita.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class datandprivacy extends AppCompatActivity {

    //WebView wv ;
    //String url = "https://covid-19.ada.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yourdataandprivacy);
        //this.wv = (WebView) findViewById(R.id.webView)
        //wv.setWebViewClient(new WebViewClient());
        //wv.getSettings().setJavaScriptEnabled(true);
        //wv.loadUrl(url);
        addListenerOnButtonClick();

    }

    private void addListenerOnButtonClick()
    {
        final CheckBox first,second,third;

        first = (CheckBox)findViewById(R.id.first);
        second = (CheckBox)findViewById(R.id.second);
        third= (CheckBox)findViewById(R.id.third);
        Button getstarted = (Button)findViewById(R.id.cont);
        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                int counter = 0;
                if(first.isChecked())
                    counter = counter + 1;
                if(second.isChecked())
                    counter = counter + 1;
                if(third.isChecked())
                     counter = counter+1;
                if(counter == 3)
                    startActivity(new Intent(datandprivacy.this,question1.class));
                 else
                    Toast.makeText(datandprivacy.this, "Please Select All", Toast.LENGTH_SHORT).show();
            }
        });
    }






}
