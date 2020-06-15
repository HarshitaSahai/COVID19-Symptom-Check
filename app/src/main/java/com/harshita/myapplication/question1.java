
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
    private ChatView chatView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.question1);

       chatView1 = findViewById(R.id.chat_view);

       //TODO: Replace all messages with a function that extracts the question from the json
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
                //TODO: This function will get triggered everytime someone presses send button. Every time, the questions and responses will be different.
                    //TODO: Use a switch case to know which question's answers you're getting here.
                String gender = null;
                //TODO: don't use ==, use equalsIgnoreCase functions. Hint - see below example.
                if(gen.equalsIgnoreCase("male") || gen == "m"){
                    gender = "male";
                }
                else if(gen == "female" || gen == "f"){
                    gender = "female";
                }
                // User can input anything apart from male/female. Stop that from getting sent
                else{
                    Toast.makeText(question1.this, "Please type male or female", Toast.LENGTH_SHORT).show();
                    return false;
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
                question2(chatView1);

                return true;
            }


        });

    }
    public void question2(ChatView chatView1){
//        chatView1.addMessage(new ChatMessage("What is your age?", System.currentTimeMillis(), ChatMessage.Type.RECEIVED));
        chatView1.addMessage(new ChatMessage(questionsExtractor(1), System.currentTimeMillis(), ChatMessage.Type.RECEIVED));

    }

    private String questionsExtractor(int index){
        String question=null;
        //TODO: Write this function to extract questions from the json provided by the infermedica API
        //TODO: Hint - checkout JSONArray and JSONObject manipulation. Do small experiments and understand how to extract questions
        return question;
    }
}

