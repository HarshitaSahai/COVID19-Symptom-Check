package com.harshita.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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
