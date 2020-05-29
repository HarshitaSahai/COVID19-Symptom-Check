package com.harshita.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class screeningtool extends AppCompatActivity {

    //WebView wv ;
    //String url = "https://covid-19.ada.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screeningtool);
        //this.wv = (WebView) findViewById(R.id.webView);

        //wv.setWebViewClient(new WebViewClient());
        //wv.getSettings().setJavaScriptEnabled(true);
        //wv.loadUrl(url);
        Toast.makeText(screeningtool.this, "Please unselect all the symptoms you don't have", Toast.LENGTH_SHORT).show();
        addListenerOnButtonClick();
    }
    private void addListenerOnButtonClick()
    {
        final CheckBox first,second,third,fourth,fifth,sixth,seventh,eight;

        first = (CheckBox)findViewById(R.id.first);
        second = (CheckBox)findViewById(R.id.second);
        third= (CheckBox)findViewById(R.id.third);
        fourth = (CheckBox)findViewById(R.id.forth);
        fifth = (CheckBox)findViewById(R.id.fifth);
        sixth = (CheckBox)findViewById(R.id.sixth);
        seventh = (CheckBox)findViewById(R.id.seventh);
        eight = (CheckBox)findViewById(R.id.eigth);
        Button getstarted = (Button)findViewById(R.id.Idnhaots);
        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                int counter = 0;
                if(!first.isChecked())
                    counter = counter + 1;
                if(!second.isChecked())
                    counter = counter + 1;
                if(!third.isChecked())
                    counter = counter+1;
                if(!fourth.isChecked())
                    counter = counter+1;
                if(!fifth.isChecked())
                    counter = counter+1;
                if(!sixth.isChecked())
                    counter = counter+1;
                if(!seventh.isChecked())
                    counter = counter+1;
                if(!eight.isChecked())
                    counter = counter+1;
                if(counter == 8)
                    startActivity(new Intent(screeningtool.this,datandprivacy.class));
                else
                    Toast.makeText(screeningtool.this, "Please recheck if you have the selected symptoms and if yes you cannot be assessed using this test", Toast.LENGTH_SHORT).show();
            }
        });
    }



}

