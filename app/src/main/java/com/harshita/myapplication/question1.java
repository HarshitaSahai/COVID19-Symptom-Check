
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

        chatView1.addMessage(new ChatMessage("Please Select Your Gender",System.currentTimeMillis(), RECEIVED));
        chatView1.addMessage(new ChatMessage(question_1(), System.currentTimeMillis(), SENT));
        chatView1.setOnSentMessageListener(new ChatView.OnSentMessageListener() {
            @Override
            public boolean sendMessage(ChatMessage chatMessage) {
                //TODO: this function is still incomplete, need to think through the implementation
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
        Data inputData = new Data.Builder().putString("apiResponse",getAPIJson().toString()).build();

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
    private JSONObject getAPIJson(){
        try {
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://api.infermedica.com/covid19/diagnosis/";

            JsonObjectRequest getRequest = new JsonObjectRequest(
                    Request.Method.POST
                    ,  url,covidObject, new com.android.volley.Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response)
                {


                    Log.d("question", response.toString());
                    //textView.setText(response);

                    try {
                        String text = response.getString("text");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

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

                    return params;
                }
            };
           // return getRequest;
            queue.add(getRequest);
            //return getRequest;
            return new JSONObject("{\"conditions\":[],\"extras\":{}," +
                    "\"question\":{\"explanation\":null," +
                  "\"extras\":{}," +
                    "\"items\":[{\"choices\":[{\"id\":\"present\",\"label\":\"Yes\"},{\"id\":\"absent\",\"label\":\"No\"}],\"explanation\":null,\"id\":\"p_18\",\"name\":\"Current cancer\"},{\"choices\":[{\"id\":\"present\",\"label\":\"Yes\"},{\"id\":\"absent\",\"label\":\"No\"}],\"explanation\":\"A weakened immune system can be caused by many factors, e.g., cancer treatment, bone marrow or organ transplantation, poorly controlled HIV/AIDS or some congenital diseases. Also, it may be caused by prolonged use of immunosuppressant drugs such as corticosteroids, or drugs used for rheumatoid arthritis, psoriasis, and other autoimmune illnesses.\",\"id\":\"p_19\",\"name\":\"Diseases or drugs that weaken immune system\"},{\"choices\":[{\"id\":\"present\",\"label\":\"Yes\"},{\"id\":\"absent\",\"label\":\"No\"}],\"explanation\":\"A person is considered obese when his or her body mass index (BMI) exceeds 30.\",\"id\":\"p_24\",\"name\":\"Obesity\"},{\"choices\":[{\"id\":\"present\",\"label\":\"Yes\"},{\"id\":\"absent\",\"label\":\"No\"}],\"explanation\":null,\"id\":\"p_22\",\"name\":\"Long-term stay at a care facility or nursing home\"}],\"text\":\"Please select all statements that apply to you\",\"type\":\"group_single\"},\"should_stop\":false}");
            //return new JSONObject("\"question\":{\"tverype\":\"group_single\",\"text\":\"How high is your fever?\",\"items\":[{\"id\":\"s_3\",\"name\":\"Between 37.5\u00b0C and 40\u00b0C (99.5\u00b0F and 104\u00b0F)\",\"choices\":[{\"id\":\"present\",\"label\":\"Yes\"},{\"id\":\"absent\",\"label\":\"No\"}]},{\"id\":\"s_4\",\"name\":\"Greater than 40\u00b0C (104\u00b0F)\",\"choices\":[{\"id\":\"present\",\"label\":\"Yes\"},{\"id\":\"absent\",\"label\":\"No\"}]},{\"id\":\"s_5\",\"name\":\"I haven\u2019t measured\",\"choices\":[{\"id\":\"present\",\"label\":\"Yes\"},{\"id\":\"absent\",\"label\":\"No\"}]}],\"extras\":{}}");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returns empty object in case the above creation of json object fails
        return new JSONObject();
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
                case R.id.male:
                    covidObject.put("sex", "male");
                    covidObject.put("age", "21");
                    covidObject.put("evidence", "[ ]");
                    break;
                case R.id.female:
                    covidObject.put("sex","female");
                    covidObject.put("age", "21");
                    covidObject.put("evidence", "[ ]");
                    break;

            }
            questionIndex+=1;
            displayQuestions();
        }catch (Exception e){e.printStackTrace();}
    }
}

