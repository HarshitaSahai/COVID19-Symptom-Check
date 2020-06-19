package com.harshita.myapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import org.json.JSONException;
import org.json.JSONObject;


public class IncomingMessageDisplayer extends Worker{

    private JSONObject apiResponse,questions;
    public IncomingMessageDisplayer(@NonNull Context context, @NonNull WorkerParameters workerParams) {

        super(context, workerParams);
        try{
            //TODO: this api response is a sample from the API. Load the actual api and pass it on as data to this class. Refer comment below this variable
            apiResponse = new JSONObject("{\"conditions\":[],\"extras\":{}," +
                    "\"question\":{\"explanation\":null," +
                    "\"extras\":{}," +
                    "\"items\":[{\"choices\":[{\"id\":\"present\",\"label\":\"Yes\"},{\"id\":\"absent\",\"label\":\"No\"}],\"explanation\":null,\"id\":\"p_18\",\"name\":\"Current cancer\"},{\"choices\":[{\"id\":\"present\",\"label\":\"Yes\"},{\"id\":\"absent\",\"label\":\"No\"}],\"explanation\":\"A weakened immune system can be caused by many factors, e.g., cancer treatment, bone marrow or organ transplantation, poorly controlled HIV/AIDS or some congenital diseases. Also, it may be caused by prolonged use of immunosuppressant drugs such as corticosteroids, or drugs used for rheumatoid arthritis, psoriasis, and other autoimmune illnesses.\",\"id\":\"p_19\",\"name\":\"Diseases or drugs that weaken immune system\"},{\"choices\":[{\"id\":\"present\",\"label\":\"Yes\"},{\"id\":\"absent\",\"label\":\"No\"}],\"explanation\":\"A person is considered obese when his or her body mass index (BMI) exceeds 30.\",\"id\":\"p_24\",\"name\":\"Obesity\"},{\"choices\":[{\"id\":\"present\",\"label\":\"Yes\"},{\"id\":\"absent\",\"label\":\"No\"}],\"explanation\":null,\"id\":\"p_22\",\"name\":\"Long-term stay at a care facility or nursing home\"}],\"text\":\"Please select all statements that apply to you\",\"type\":\"group_multiple\"},\"should_stop\":false}");
            questions = apiResponse.getJSONObject("question");
            //below comment is an example of how the variable should look
//            apiResponse = new JSONObject(getInputData().getString("apiResponse"));

        }catch (Exception e){e.printStackTrace();}

    }

    @NonNull
    @Override
    public Result doWork() {
        Data outputData = new Data.Builder()
                .putString("nextQuestion",questionsExtractor())
                .putString("type",typeExtractor())
                .putString("items",itemsExtractor())
                .build();
        return  Result.success(outputData);
    }

    private String itemsExtractor() {
        try {
            return questions.getJSONArray("items").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //All questions are inside a json array called items. After each question's response, you can increase the index, and call this class to display the next question
    private String questionsExtractor(){
        try{
            return questions.getString("text");
        }catch (Exception e){e.printStackTrace(); return null;}

    }

    private String typeExtractor(){
        try {
            return questions.getString("type");
        } catch (JSONException e) {
            e.printStackTrace();
            return "single";
        }
    }
}
