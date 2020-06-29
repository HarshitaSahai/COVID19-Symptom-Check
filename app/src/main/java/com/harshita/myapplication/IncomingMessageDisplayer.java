package com.harshita.myapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import org.json.JSONException;
import org.json.JSONObject;


public class IncomingMessageDisplayer extends Worker {

    private JSONObject apiResponse,questions;
    public IncomingMessageDisplayer(@NonNull Context context, @NonNull WorkerParameters workerParams) {

        super(context, workerParams);
        try{
            apiResponse = new JSONObject(getInputData().getString("apiResponse"));
            if(!shouldStop()){
                questions = apiResponse.getJSONObject("question");
            }
        }catch (Exception e){e.printStackTrace();}

    }

    @NonNull
    @Override
    public Result doWork() {
        Data outputData = new Data.Builder()
                .putString("nextQuestion",questionsExtractor())
                .putString("type",shouldStop()? null : typeExtractor())
                .putString("items",shouldStop() ? null: itemsExtractor())
                .putBoolean("shouldStop", shouldStop())
                .build();
        return  Result.success(outputData);
    }

    private Boolean shouldStop(){
        try {
            return apiResponse.getBoolean("should_stop");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
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
