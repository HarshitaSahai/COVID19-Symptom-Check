
package com.harshita.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    private JSONObject apiResponse = new JSONObject();
    private MutableLiveData<JSONObject> responseAlert = new MutableLiveData<>();
    //private String agebyuser;
    private  JSONArray evidence = new JSONArray(); //Array for storing id and choice

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question1);

        chatView1 = findViewById(R.id.chat_view);

        chatView1.addMessage(new ChatMessage("Please Select Your Gender",System.currentTimeMillis(), RECEIVED));
        chatView1.addMessage(new ChatMessage(question_1(), System.currentTimeMillis(), SENT));
        chatView1.setOnSentMessageListener(new ChatView.OnSentMessageListener() {
            @Override
            public boolean sendMessage(ChatMessage chatMessage) {
                //TODO: this function is still incomplete, need to think through the implementation
                switch(questionIndex){
                    case 0:
                        return true;

                    case 1:

                }
                return false;
            }

        });
        chatView1.setOnClickListener(this);

        responseAlert.observe(this, new Observer<JSONObject>() {
            @Override
            public void onChanged(JSONObject jsonObject) {
                displayQuestions();
            }
        });
    }

    private View question_1(){
        //Sample view given here
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.sample_layout, null);
    }
 /* private View question_2(){
        //Sample view given here
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.question2, null);
    }*/

    private void displayQuestions(){
        WorkManager workManager = WorkManager.getInstance(this);
        //Add your API response just like how you did it for intents. -> Convert to String
        Data inputData = new Data.Builder().putString("apiResponse",apiResponse.toString()).build();

        @SuppressLint("RestrictedApi") OneTimeWorkRequest displayNextQuestionRequest =
                new OneTimeWorkRequest.Builder(IncomingMessageDisplayer.class)
                        .setInputData(inputData)
                        .build();
        workManager.enqueueUniqueWork("displayNextQuestion",ExistingWorkPolicy.REPLACE, displayNextQuestionRequest);

        workManager.getWorkInfosForUniqueWorkLiveData("displayNextQuestion").observe(this, new Observer<List<WorkInfo>>() {
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
            }
        });

    }
    //This is a sample api response.
    // TODO : Use this function to call the actual api and return the response
    private void getAPIJson(){
        try {
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://api.infermedica.com/covid19/diagnosis";
            Log.wtf("covid obejct", covidObject.toString());

            JsonObjectRequest getRequest = new JsonObjectRequest(
                    Request.Method.POST
                    ,  url,covidObject, new com.android.volley.Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response)
                {
                    apiResponse = response;
                    responseAlert.setValue(apiResponse);

                    Log.wtf("question", response.toString());

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
                public Map<String, String> getHeaders()
                {
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("Content-Type","application/json");
                    params.put("App-Id", "fd9740f0");
                    params.put("App-Key", "369c63fe2ab752b87ac60189d368a7e1");

                    return params;
                }
            };
           //return getRequest;
            queue.add(getRequest);
            //return covidObject;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View groupMultipleTypeView(String items){
        try {
            final JSONArray itemsArray = new JSONArray(items);
            final LinearLayout checkboxHolder = new LinearLayout(this);
            checkboxHolder.setOrientation(LinearLayout.VERTICAL);
            final CheckBox[] cbs; // Checkbox array
            cbs = new CheckBox[itemsArray.length()]; // Size of array
            Button bgmp = new Button(this);
            bgmp.setText("Next"); // Button for storing value in evidence[]

            for(int i=0;i<itemsArray.length();i++)
            {
                cbs[i] = new CheckBox(this); //here i is iteration variable
                cbs[i].setText(itemsArray.getJSONObject(i).getString("name")); // Storing content
                checkboxHolder.addView(cbs[i]);
            }
            checkboxHolder.addView(bgmp); // Adding button to view


            /*checkboxHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(question1.this, "Clicked pa clicked :laugh", Toast.LENGTH_SHORT).show();
                }
            });*/


            bgmp.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JSONObject cjo[ ] = new  JSONObject[itemsArray.length()];// Array to store current checbox value (question4 line 55 - 59)

                    for(int i = 0;i<itemsArray.length();i++)
                    {
                        // TODO : cjo[] referring to null object prevent that

                        if(cbs[i].isChecked()){
                            try {
                                cjo[i].put("id",itemsArray.getJSONObject(i).getString("id")); // Getting the id from item
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                cjo[i].put("choice","present"); // Storing present if checked
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else{
                            try {
                                cjo[i].put("id",itemsArray.getJSONObject(i).getString("id")); // Getting the id for item
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                cjo[i].put("choice","absent"); // Storing absent if not checked

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        evidence.put(cjo[i]); // Appending the value in evidence "Can also be done after stroing value in every cjo" (question 4 line 77)

                    }

                    questionIndex+=1;
                    // question4 line 209
                    try {
                        covidObject.put("evidence",evidence);
                        Log.wtf("object here",evidence.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    getAPIJson();
                }
            });

            return checkboxHolder;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private View groupSingleTypeView(String items){
        //TODO: Need to write this view, with Radio group as only one of the options should be selected
        try {
            JSONArray itemsArray = new JSONArray(items);
            LinearLayout ll = new LinearLayout(this);
            //RadioGroup radiogroup = new RadioGroup(this);
            //radiogroup.setOrientation(ll.VERTICAL);
            RadioGroup radiobuttonHolder = new RadioGroup(this);
            radiobuttonHolder.setOrientation(ll.VERTICAL);
            for(int i=0; i<itemsArray.length();i++){
                RadioButton selectableRadioButton = new RadioButton(this);
                selectableRadioButton.setText(itemsArray.getJSONObject(i).getString("name"));
                radiobuttonHolder.addView(selectableRadioButton,i);
            }
            radiobuttonHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(question1.this, "Clicked pa clicked :laugh", Toast.LENGTH_SHORT).show();
                }
            });
            return radiobuttonHolder;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void onClick(View v) {
        try{
            switch (v.getId()){
                case R.id.toq2:
                    RadioButton m = (RadioButton)findViewById(R.id.male);
                    RadioButton f = (RadioButton)findViewById(R.id.female);
                    if(m.isChecked()){
                    covidObject.put("sex","male");
                    covidObject.put("age",21);
                        covidObject.put("evidence", new JSONArray());}
                    else if(f.isChecked()){
                        covidObject.put("sex","female");
                        covidObject.put("age",21);
                        covidObject.put("evidence", new JSONArray()); }
                    else{
                        Toast.makeText(question1.this, "Select male or female!", Toast.LENGTH_SHORT).show();

                    }
                    break;




            }

            questionIndex+=1;
            getAPIJson();
        }catch (Exception e){e.printStackTrace();}
    }
}

