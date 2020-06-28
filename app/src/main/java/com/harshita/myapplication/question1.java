
package com.harshita.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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

import java.util.Objects;

import static com.harshita.myapplication.models.ChatMessage.Type.RECEIVED;
import static com.harshita.myapplication.models.ChatMessage.Type.SENT;

public class question1  extends AppCompatActivity implements View.OnClickListener {

    private ChatView chatView1;
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

    private void displayQuestions(){
        WorkManager workManager = WorkManager.getInstance(this);
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
    private void getAPIJson(){
        try {
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://api.infermedica.com/covid19/diagnosis";
            Log.wtf("covid obejct", covidObject.toString());

            JsonObjectRequest getRequest = new JsonObjectRequest(
                    Request.Method.POST
                    ,url,covidObject, new com.android.volley.Response.Listener<JSONObject>() {
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
                        JSONObject evidence_subJson = new JSONObject();
                        try{
                            evidence_subJson.put("id",itemsArray.getJSONObject(i).getString("id"));
                            evidence_subJson.put("choice_id",((CheckBox)checkboxHolder.getChildAt(i)).isChecked()? "present": "absent");

                            evidence.put(evidence_subJson);

                        }catch (Exception e){e.printStackTrace();}
                    }
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
            RadioGroup radiobuttonHolder = new RadioGroup(this);
            radiobuttonHolder.setOrientation(LinearLayout.VERTICAL);

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
                case R.id.genderNextButton:
                    RadioButton m =findViewById(R.id.male);
                    RadioButton f =findViewById(R.id.female);
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
            getAPIJson();
        }catch (Exception e){e.printStackTrace();}
    }
}

