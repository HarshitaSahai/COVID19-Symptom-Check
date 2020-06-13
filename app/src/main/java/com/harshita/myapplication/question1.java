
package com.harshita.myapplication;

import android.content.Context;
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
import android.widget.RadioGroup;
import android.widget.Toast;




import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;

public class question1  extends AppCompatActivity {


    //WebView wv ;
    //String url = "https://covid-19.ada.com";
    JSONObject obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.question1);

       final ChatView chatView1 = (ChatView) findViewById(R.id.chat_view);

        chatView1.addMessage(new ChatMessage("Are you a male or female?", System.currentTimeMillis(), ChatMessage.Type.RECEIVED));
        chatView1.setTypingListener(new ChatView.TypingListener() {
            @Override
            public void userStartedTyping() {
            }

            @Override
            public void userStoppedTyping() {

            }

        });
        chatView1.setOnSentMessageListener(new ChatView.OnSentMessageListener() {
                @Override
            public boolean sendMessage(ChatMessage chatMessage) {
                String gen = chatMessage.getMessage();
                String gender = null;
                if(gen == "male" || gen == "m"){
                    gender = "male";
                }
                else if(gen == "female" || gen == "f"){
                    gender = "female";
                }


                JSONObject obj1;
                try {
                    obj1 = new JSONObject();
                    obj1.put("sex", gen);
                   // obj.optJSONObject(obj1.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                    obj1 = null;

                }
                Log.wtf("object here", String.valueOf(obj1));


//                Toast.makeText(question1.this, "Please type male or female", Toast.LENGTH_SHORT).show();
                return true;
            }


        });




       //addListenerOnButtonClick();
    }
    public void question2(ChatView chatView1){
        chatView1.addMessage(new ChatMessage("What is your age?", System.currentTimeMillis(), ChatMessage.Type.RECEIVED));

    }
   /* public void addListenerOnButtonClick() {
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
    }*/
}

