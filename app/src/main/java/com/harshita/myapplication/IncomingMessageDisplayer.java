package com.harshita.myapplication;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class IncomingMessageDisplayer extends Worker {

    private JSONObject apiResponse;

    public IncomingMessageDisplayer(@NonNull Context context, @NonNull WorkerParameters workerParams) {

        super(context, workerParams);

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
