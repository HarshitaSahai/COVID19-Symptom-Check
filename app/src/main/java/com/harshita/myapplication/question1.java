
package com.harshita.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.harshita.myapplication.models.ChatMessage;
import com.harshita.myapplication.views.ChatView;

import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.harshita.myapplication.models.ChatMessage.Type.RECEIVED;
import static com.harshita.myapplication.models.ChatMessage.Type.SENT;

public class question1  extends AppCompatActivity {


    //WebView wv ;
    //String url = "https://covid-19.ada.com";
    //JSONObject obj;
    private ChatView chatView1;
    private int questionIndex =1;
    String jsonString = " ";
    JSONObject obj;

    {
        try {
            obj = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.question1);

       chatView1 = findViewById(R.id.chat_view);
       //TODO: Replace all messages with a function that extracts the question from the json

        //Sample view given here
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.sample_layout, null);

        chatView1.addMessage(new ChatMessage("Please Select Your Gender",System.currentTimeMillis(), RECEIVED));
        chatView1.addMessage(new ChatMessage(view, System.currentTimeMillis(), RECEIVED));
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


              switch (questionIndex) {
                  case 1:         String gen = chatMessage.getMessage();
                                  //TODO: This function will get triggered everytime someone presses send button. Every time, the questions and responses will be different.
                                  //TODO: Use a switch case to know which question's answers you're getting here.
                                  String gender = null;
                                  //TODO: don't use ==, use equalsIgnoreCase functions. Hint - see below example.
                                  if (gen.equalsIgnoreCase("male") || gen == "m") {
                                      gender = "male";
                                  } else if (gen.equalsIgnoreCase("female") || gen == "f") {
                                      gender = "female";
                                  }
                                  // User can input anything apart from male/female. Stop that from getting sent
                                  else {
                                      Toast.makeText(question1.this, "Please type male or female", Toast.LENGTH_SHORT).show();
                                      return false;
                                  }

                                  //JSONObject obj1;
                                  try {
                                      //obj1 = new JSONObject();
                                      obj.put("sex", gender);
                                      Log.wtf("object here", obj.toString());

                                      //obj.optJSONObject(obj1.toString());

                                  } catch (Exception e) {
                                      e.printStackTrace();
                                      // obj1 = null;

                                  }
                                  Log.wtf("object here", String.valueOf(obj));
                                  questionIndex = questionIndex + 1;
                                  displayQuestions(questionIndex);
                                  break;
                  case  2:        String age = chatMessage.getMessage();
                                  //JSONObject obj2;
                                  try {
                                     // obj2 = new JSONObject();
                                      obj.put("age", Integer.parseInt(age));
                                       //obj.optJSONObject(obj2.toString());
                                      Log.wtf("object here", obj.toString());
                                  } catch (JSONException e) {
                                      e.printStackTrace();
                                  }
                                  questionIndex = questionIndex + 1;
                                  displayQuestions(questionIndex);
                                  break;



              }


                return true;
            }

        });
    }


    private void displayQuestions(int questionIndex){
        //WorkManager workManager = WorkManager.getInstance(this);
        switch (questionIndex) {

            case 2:     messagehandler("What is your age?",null);
                        break;
            case 3:     messagehandler(null,null);
                        break;
        }

    }

    public void messagehandler(final String str, View v){

        Handler handler = new Handler();
        if(v == null){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                chatView1.addMessage(new ChatMessage(str, System.currentTimeMillis(), ChatMessage.Type.RECEIVED));
            }
        }, 5000);
        chatView1.setTypingListener(new ChatView.TypingListener() {
            @Override
            public void userStartedTyping() {
            }

            @Override
            public void userStoppedTyping() {

            }

        });}
        else{
            
        }

    }
    public <name> void getvr(JSONObject obj)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.infermedica.com/covid19/diagnosis/";

        JsonObjectRequest getRequest = new JsonObjectRequest(
                Request.Method.POST
                ,  url,obj, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {


                    Log.d("question",response.toString());

                  //  TextView tv = (TextView)findViewById(R.id.result);
                 //   tv.setText(response.getString("description"));

                //Log.d("description", response.toString());
                //textView.setText(response);



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
                params.put("Content-Type","application/json");
                params.put("sex", "male");
                params.put("age", "21");
                params.put("evidence","[ ]");


                return params;
            }
        };
        queue.add(getRequest);
    }

}

