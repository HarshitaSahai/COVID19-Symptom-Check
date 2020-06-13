package com.harshita.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


import java.util.Random;

public class MainActivity extends AppCompatActivity {


    //WebView wv ;
    //String url = "https://covid-19.ada.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            iunderstandfunction();
    }
    private void  iunderstandfunction()
    {
        Button getstarted = (Button)findViewById(R.id.getstarted);
        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(MainActivity.this,iunderstand.class));
            }
        });
    }


}
