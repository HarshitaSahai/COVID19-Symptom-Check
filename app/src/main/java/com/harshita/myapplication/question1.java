
package com.harshita.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;

public class question1  extends AppCompatActivity {


    //WebView wv ;
    //String url = "https://covid-19.ada.com";
    JSONObject obj;
    private ChatView chatView1;
    private int questionIndex =0;
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
                displayQuestions();

                return true;
            }

        });
    }


    private void displayQuestions(){
        WorkManager workManager = WorkManager.getInstance(this);
        //Add your API response just like how you did it for intents. -> Convert to String
        // e.g. code -> .putString("apiResponse",apiResponse.toString())
        Data inputData = new Data.Builder().putInt("questionNumber",questionIndex).build();

        @SuppressLint("RestrictedApi") OneTimeWorkRequest displayNextQuestionRequest =
                new OneTimeWorkRequest.Builder(IncomingMessageDisplayer.class)
                        .setInitialDelay(2, TimeUnit.SECONDS)
                        .setInputData(inputData)
                        .build();
        workManager.enqueueUniqueWork("displayNextQuestion",ExistingWorkPolicy.REPLACE, displayNextQuestionRequest);

        workManager.getWorkInfosForUniqueWorkLiveData("displayNextQuestion").observe(this, new Observer<List<WorkInfo>>() {
            @Override
            public void onChanged(List<WorkInfo> workInfos) {
                if(workInfos!=null && !workInfos.isEmpty()){
                    if(workInfos.get(0).getState().equals(WorkInfo.State.SUCCEEDED)){
                        chatView1.addMessage(new ChatMessage(workInfos.get(0).getOutputData().getString("nextQuestion"), System.currentTimeMillis(), ChatMessage.Type.RECEIVED));
                    }
                }
            }
        });

    }
}

