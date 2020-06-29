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

    private JSONObject apiResponse,questions;
    public IncomingMessageDisplayer(@NonNull Context context, @NonNull WorkerParameters workerParams) {

        super(context, workerParams);
        try{
            apiResponse = new JSONObject(getInputData().getString("apiResponse"));
            questions = apiResponse.getJSONObject("question");
        }catch (Exception e){e.printStackTrace();}

    }

    @NonNull
    @Override
    public Result doWork() {
        Data outputData = new Data.Builder()
                .putString("nextQuestion",questionsExtractor())
                .putString("type",typeExtractor())
                .putString("items",itemsExtractor())
                .putString("should_stop",resExtractor())
                .build();
        return  Result.success(outputData);
    }

    private String resExtractor(){
        try {
            return questions.getString("should_stop");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    private String itemsExtractor() {
        try {
            return questions.getJSONArray("items").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


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
