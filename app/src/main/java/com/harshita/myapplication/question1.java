
package com.harshita.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.harshita.myapplication.models.ChatMessage;
import com.harshita.myapplication.views.ChatView;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.harshita.myapplication.models.ChatMessage.Type.RECEIVED;
import static com.harshita.myapplication.models.ChatMessage.Type.SENT;

public class question1  extends AppCompatActivity {

    private ChatView chatView1;
    private JSONObject covidObject = new JSONObject();
    private JSONObject apiResponse = new JSONObject();
    private MutableLiveData<JSONObject> responseAlert = new MutableLiveData<>();
    private  JSONArray evidence = new JSONArray(); //Array for storing id and choice

    private final String TRIAGE_URL = BuildConfig.triage_url;
    private final String DIAGNOSIS_URL = BuildConfig.diagnosis_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question1);

        chatView1 = findViewById(R.id.chat_view);
//        chatView1.setOnClickListener(this);

        toggleInputVisibility(View.GONE);
        question_1();

        responseAlert.observe(this, new Observer<JSONObject>() {
            @Override
            public void onChanged(JSONObject jsonObject) {
                displayQuestions();
            }
        });
    }

    private void question_1(){

        try {
            JSONObject male = new JSONObject();
            male.put("id","m");
            male.put("name","Male");

            JSONObject female = new JSONObject();
            female.put("id","f");
            female.put("name","Female");

            JSONArray items = new JSONArray();
            items.put(male);
            items.put(female);

            Log.wtf("items",items.toString());
            chatView1.addMessage(new ChatMessage("Please Select Your Gender",System.currentTimeMillis(), RECEIVED));
            chatView1.addMessage(new ChatMessage(groupSingleTypeView(items.toString(),false), System.currentTimeMillis(), SENT));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void displayQuestions(){
        WorkManager workManager = WorkManager.getInstance(this);
        Data inputData = new Data.Builder().putString("apiResponse",apiResponse.toString()).build();

        @SuppressLint("RestrictedApi") OneTimeWorkRequest displayNextQuestionRequest =
                new OneTimeWorkRequest.Builder(IncomingMessageDisplayer.class)
                        .setInputData(inputData)
                        .build();
        workManager.enqueueUniqueWork("displayNextQuestion",ExistingWorkPolicy.REPLACE, displayNextQuestionRequest);

        workManager.getWorkInfoByIdLiveData(displayNextQuestionRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                if(workInfo!=null){
                    if(workInfo.getState().equals(WorkInfo.State.SUCCEEDED)){

                        Data outputDataFromWorker = workInfo.getOutputData();

                        if(!outputDataFromWorker.getBoolean("shouldStop",false)){
                            chatView1.addMessage(new ChatMessage(outputDataFromWorker.getString("nextQuestion"), System.currentTimeMillis(), RECEIVED));
                            String items = outputDataFromWorker.getString("items");
                            //Don't allow user to say anything
                            toggleInputVisibility(View.GONE);

                            switch(Objects.requireNonNull(outputDataFromWorker.getString("type"))){
                                case "group_multiple":
                                    chatView1.addMessage(new ChatMessage(groupMultipleTypeView(items),System.currentTimeMillis(), SENT));
                                    break;
                                case "single":
                                    chatView1.addMessage(new ChatMessage(singleTypeView(items),System.currentTimeMillis(), SENT));
                                    break;
                                case "group_single":
                                    chatView1.addMessage(new ChatMessage(groupSingleTypeView(items,true),System.currentTimeMillis(), SENT));
                                    break;
                            }
                        }
                        //If should stop is true, it means it's time to call the triage api
                        else{
                            getAPIJson(TRIAGE_URL,"triage");
                        }
                        
                    }
                }
            }

        });
    }
    private String idExtractor(JSONArray itemsArray, int index) throws Exception{
        return itemsArray.getJSONObject(index).getString("id");
    }
    private void getAPIJson(String url,final String type){
        try {
            RequestQueue queue = Volley.newRequestQueue(this);
            Log.wtf("covid obejct", covidObject.toString());

            JsonObjectRequest getRequest = new JsonObjectRequest(
                    Request.Method.POST
                    ,url,covidObject, new com.android.volley.Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response)
                {
                    switch (type){
                        case "diagnosis":
                            apiResponse = response;
                            responseAlert.setValue(apiResponse);

                            Log.wtf("question", response.toString());
                            break;
                        case "triage":
                            try {
                                if(response.getString("description") != null)
                                    chatView1.addMessage(new ChatMessage(response.getString("description"),System.currentTimeMillis(), RECEIVED));

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                    }

                }
            }, new com.android.volley.Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    chatView1.addMessage(new ChatMessage("Sorry! \nWe're not able to connect to our servers, please try again later",System.currentTimeMillis(), RECEIVED));
                    Log.wtf("ERROR","error => "+error.toString());
                    error.printStackTrace();

                }
            }){
                @Override
                public Map<String, String> getHeaders()
                {
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("Content-Type","application/json");
                    params.put("App-Id", "fd9740f0");
                    params.put("App-Key", "369c63fe2ab752b87ac60189d368a7e1");

                    return params;
                }
            };
            queue.add(getRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View groupMultipleTypeView(String items){
        try {
            final JSONArray itemsArray = new JSONArray(items);
            final LinearLayout checkboxHolder = new LinearLayout(this);
            checkboxHolder.setOrientation(LinearLayout.VERTICAL);

            Button bgmp = new Button(this);
            bgmp.setText("Next"); // Button for storing value in evidence[]

            for(int i=0; i<itemsArray.length();i++){
                CheckBox selectableCheckBox = new CheckBox(this);
                selectableCheckBox.setText(itemsArray.getJSONObject(i).getString("name"));
                checkboxHolder.addView(selectableCheckBox,i);
            }

            checkboxHolder.addView(bgmp,itemsArray.length()); // Adding button to view

            bgmp.setOnClickListener( new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    for(int i=0;i<itemsArray.length();i++){
                        try{
                            evidence.put(getEvidenceSubJson(idExtractor(itemsArray,i),
                                    ((CheckBox)checkboxHolder.getChildAt(i)).isChecked()? "present": "absent"));
                        }catch (Exception e){e.printStackTrace();}
                    }
                    addEvidence();
                    getAPIJson(DIAGNOSIS_URL,"diagnosis");
                }
            });

            return checkboxHolder;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addEvidence(){
        // question4 line 209
        try {
            covidObject.put("evidence",evidence);
            Log.wtf("object here",evidence.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private View singleTypeView(final String items){
        try{
            final JSONArray itemsArray = new JSONArray(items).getJSONObject(0).getJSONArray("choices");
            RadioGroup radiobuttonHolder = new RadioGroup(this);
            radiobuttonHolder.setOrientation(LinearLayout.VERTICAL);

            for(int i=0; i<itemsArray.length();i++){
                RadioButton selectableRadioButton = new RadioButton(this);
                selectableRadioButton.setId(i);
                selectableRadioButton.setText(itemsArray.getJSONObject(i).getString("label"));
                radiobuttonHolder.addView(selectableRadioButton,i);
            }
            radiobuttonHolder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    try{
                        String choice_id = ((RadioButton)group.getChildAt(checkedId)).getText().toString().toLowerCase().equals("yes") ? "present" : "absent";
                        evidence.put(getEvidenceSubJson(idExtractor(new JSONArray(items),0),choice_id));

                        addEvidence();
                        getAPIJson(DIAGNOSIS_URL,"diagnosis");

                    }catch (Exception e){e.printStackTrace();}
                }
            });
            return radiobuttonHolder;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }

    private View groupSingleTypeView(String items, final boolean appendToEvidence){
        try {
            final JSONArray itemsArray = new JSONArray(items);
            final RadioGroup radiobuttonHolder = new RadioGroup(this);
            radiobuttonHolder.setOrientation(LinearLayout.VERTICAL);

            for(int i=0; i<itemsArray.length();i++){
                RadioButton selectableRadioButton = new RadioButton(this);
                selectableRadioButton.setId(i);

                selectableRadioButton.setText(itemsArray.getJSONObject(i).getString("name"));
                radiobuttonHolder.addView(selectableRadioButton,i);
            }

            radiobuttonHolder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if(appendToEvidence){
                        for(int i=0;i<itemsArray.length();i++){
                            try{
                                evidence.put(getEvidenceSubJson(idExtractor(itemsArray,i),
                                        ((RadioButton)radiobuttonHolder.getChildAt(i)).isChecked()? "present": "absent"));
                            }catch (Exception e){e.printStackTrace();}
                        }
                        addEvidence();
                        getAPIJson(DIAGNOSIS_URL,"diagnosis");
                    }
                    else{
                        try {
                            covidObject.put("sex", ((RadioButton)radiobuttonHolder.getChildAt(checkedId)).getText().toString().toLowerCase());
                            ageQuestion();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            return radiobuttonHolder;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private JSONObject getEvidenceSubJson(String id, String choiceId) throws Exception{
        JSONObject evidence_subJson = new JSONObject();
        evidence_subJson.put("id",id);
        evidence_subJson.put("choice_id",choiceId);
        return evidence_subJson;
    }


    private int age;
    private void ageQuestion(){
        chatView1.addMessage(new ChatMessage("What is your age?", System.currentTimeMillis(), ChatMessage.Type.RECEIVED));
        Toast.makeText(question1.this, "Please enter a valid age (between 18 to 120)", Toast.LENGTH_SHORT).show();

        toggleInputVisibility(View.VISIBLE);
        chatView1.setInputType(EditorInfo.TYPE_CLASS_NUMBER);

        chatView1.setTypingListener(new ChatView.TypingListener(){
            @Override
            public void userStartedTyping(){
                // will be called when the user starts typing
            }

            @Override
            public void userStoppedTyping(){
                // will be called when the user stops typing

            }
        });
        chatView1.setOnSentMessageListener(new ChatView.OnSentMessageListener() {
            @Override
            public boolean sendMessage(ChatMessage chatMessage) {
                String message = ((TextView)chatMessage.getView()).getText().toString().toLowerCase();
                try{
                    age = Integer.parseInt(message);
                    if(age < 18 || age > 120)
                    {
                        Toast.makeText(question1.this, "Please enter a valid age (between 18 to 120)", Toast.LENGTH_SHORT).show();
                        ageQuestion();
                        return false;
                    }
                    covidObject.put("age",age);
                    covidObject.put("evidence", new JSONArray());

                    getAPIJson(DIAGNOSIS_URL,"diagnosis");

                    return true;
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(question1.this, "Please enter your age", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        });
    }

    private void toggleInputVisibility(int visibility){
        chatView1.setInputVisibility(visibility);
    }
}

