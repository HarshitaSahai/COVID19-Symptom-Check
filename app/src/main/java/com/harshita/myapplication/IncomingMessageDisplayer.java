package com.harshita.myapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import org.json.JSONObject;


public class IncomingMessageDisplayer extends Worker{

    private JSONObject apiResponse;
    public IncomingMessageDisplayer(@NonNull Context context, @NonNull WorkerParameters workerParams) {

        super(context, workerParams);
        try{
            //TODO: this api response is a sample from the API. Load the actual api and pass it on as data to this class. Refer comment below this variable
            apiResponse = new JSONObject("{\"conditions\":[],\"extras\":{}," +
                    "\"question\":{\"explanation\":null," +
                    "\"extras\":{}," +
                    "\"items\":[{\"choices\":[{\"id\":\"present\",\"label\":\"Yes\"},{\"id\":\"absent\",\"label\":\"No\"}],\"explanation\":null,\"id\":\"p_18\",\"name\":\"Current cancer\"},{\"choices\":[{\"id\":\"present\",\"label\":\"Yes\"},{\"id\":\"absent\",\"label\":\"No\"}],\"explanation\":\"A weakened immune system can be caused by many factors, e.g., cancer treatment, bone marrow or organ transplantation, poorly controlled HIV/AIDS or some congenital diseases. Also, it may be caused by prolonged use of immunosuppressant drugs such as corticosteroids, or drugs used for rheumatoid arthritis, psoriasis, and other autoimmune illnesses.\",\"id\":\"p_19\",\"name\":\"Diseases or drugs that weaken immune system\"},{\"choices\":[{\"id\":\"present\",\"label\":\"Yes\"},{\"id\":\"absent\",\"label\":\"No\"}],\"explanation\":\"A person is considered obese when his or her body mass index (BMI) exceeds 30.\",\"id\":\"p_24\",\"name\":\"Obesity\"},{\"choices\":[{\"id\":\"present\",\"label\":\"Yes\"},{\"id\":\"absent\",\"label\":\"No\"}],\"explanation\":null,\"id\":\"p_22\",\"name\":\"Long-term stay at a care facility or nursing home\"}],\"text\":\"Please select all statements that apply to you\",\"type\":\"group_multiple\"},\"should_stop\":false}");
            //below comment is an example of how the variable should look
//            apiResponse = new JSONObject(getInputData().getString("apiResponse"));

        }catch (Exception e){e.printStackTrace();}

    }

    @NonNull
    @Override
    public Result doWork() {
        return  Result.success(new Data.Builder().putString("nextQuestion",questionsExtractor(getInputData().getInt("questionNumber",0))).build());
    }

    //All questions are inside a json array called items. After each question's response, you can increase the index, and call this class to display the next question
    private String questionsExtractor(int index){
        try{
            JSONObject individualQuestion = apiResponse.getJSONObject("question").getJSONArray("items").getJSONObject(index);
            String question = individualQuestion.getString("name");
            return question;
        }catch (Exception e){e.printStackTrace(); return null;}
        //TODO: Write this function to extract questions from the json provided by the infermedica API
        //TODO: Hint - checkout JSONArray and JSONObject manipulation. Do small experiments and understand how to extract questions

    }
}
