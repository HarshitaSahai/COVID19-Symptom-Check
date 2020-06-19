
package com.harshita.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.harshita.myapplication.models.ChatMessage.Type.RECEIVED;
import static com.harshita.myapplication.models.ChatMessage.Type.SENT;

public class question1  extends AppCompatActivity implements View.OnClickListener {


    //WebView wv ;
    //String url = "https://covid-19.ada.com";
    //JSONObject obj;
    private ChatView chatView1;
    private int questionIndex =0;
    private JSONObject covidObject = new JSONObject();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.question1);

       chatView1 = findViewById(R.id.chat_view);
       //TODO: Replace all messages with a function that extracts the question from the json

        chatView1.addMessage(new ChatMessage("Please Select Your Gender",System.currentTimeMillis(), RECEIVED));
        chatView1.addMessage(new ChatMessage(question_1(), System.currentTimeMillis(), SENT));

        chatView1.setOnSentMessageListener(new ChatView.OnSentMessageListener() {
                @Override
            public boolean sendMessage(ChatMessage chatMessage) {
                    switch(questionIndex){
                        case 0:
                            return true;
                    }
                    return false;
            }

        });
        chatView1.setOnClickListener(this);
    }

    private View question_1(){
        //Sample view given here
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.sample_layout, null);
    }

    private void displayQuestions(){
        WorkManager workManager = WorkManager.getInstance(this);
        //Add your API response just like how you did it for intents. -> Convert to String
        // e.g. code -> .putString("apiResponse",apiResponse.toString())

        @SuppressLint("RestrictedApi") OneTimeWorkRequest displayNextQuestionRequest =
                new OneTimeWorkRequest.Builder(IncomingMessageDisplayer.class)
                        .setInitialDelay(20, TimeUnit.MILLISECONDS)
//                        .setInputData(inputData)
                        .build();
        workManager.enqueueUniqueWork("displayNextQuestion",ExistingWorkPolicy.REPLACE, displayNextQuestionRequest);

            handler.postDelayed(new Runnable()
        {
            @Override
            public void onChanged(List<WorkInfo> workInfos) {
                if(workInfos!=null && !workInfos.isEmpty()){
                    if(workInfos.get(0).getState().equals(WorkInfo.State.SUCCEEDED)){

                        Data outputDataFromWorker = workInfos.get(0).getOutputData();

                        chatView1.addMessage(new ChatMessage(outputDataFromWorker.getString("nextQuestion"), System.currentTimeMillis(), RECEIVED));
                        String items = outputDataFromWorker.getString("items");
                        switch(Objects.requireNonNull(outputDataFromWorker.getString("type"))){
                            case "group_multiple":
                                chatView1.addMessage(new ChatMessage(groupMultipleTypeView(items),System.currentTimeMillis(), SENT));
                                break;
                            case "group_single":
                                chatView1.addMessage(new ChatMessage(groupSingleTypeView(items),System.currentTimeMillis(), SENT));
                                break;
                            case "single":
                                break;
                        }
                    }
                }

                @Override
                public void userStoppedTyping() {

                }

            });


        }
        else if(questionIndex == 3) {
                final View view = inflater1.inflate(R.layout.question3, null);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        chatView1.addMessage(new ChatMessage(view, System.currentTimeMillis(), ChatMessage.Type.RECEIVED));
                    }
                }, 5000);

            }





    }

    private View groupMultipleTypeView(String items){
        try {
            JSONArray itemsArray = new JSONArray(items);
            LinearLayout checkboxHolder = new LinearLayout(this);
            checkboxHolder.setOrientation(LinearLayout.VERTICAL);
            for(int i=0; i<itemsArray.length();i++){
                CheckBox selectableCheckBox = new CheckBox(this);
                selectableCheckBox.setText(itemsArray.getJSONObject(i).getString("name"));
                checkboxHolder.addView(selectableCheckBox,i);
            }
            checkboxHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(question1.this, "Clicked pa clicked :laugh", Toast.LENGTH_SHORT).show();
                }
            });
            return checkboxHolder;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private View groupSingleTypeView(String items){
        return null;
    }

    @Override
    public void onClick(View v) {
        try{
            switch (v.getId()){
                case R.id.male:
                    covidObject.put("sex", "male");
                    break;
                case R.id.female:
                    covidObject.put("sex","female");
                    break;

            }
            questionIndex+=1;
            displayQuestions();
        }catch (Exception e){e.printStackTrace();}
    }
}

