package com.harshita.myapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


public class IncomingMessageDisplayer extends Worker{

    public IncomingMessageDisplayer(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        return  Result.success(new Data.Builder().putString("nextQuestion",questionsExtractor(getInputData().getInt("questionNumber",0))).build());
    }

//    public void question2(ChatView chatView1){
////        chatView1.addMessage(new ChatMessage("What is your age?", System.currentTimeMillis(), ChatMessage.Type.RECEIVED));
//        chatView1.addMessage(new ChatMessage(questionsExtractor(1), System.currentTimeMillis(), ChatMessage.Type.RECEIVED));
//
//    }

    private String questionsExtractor(int index){
        String question="some question";
        //TODO: Write this function to extract questions from the json provided by the infermedica API
        //TODO: Hint - checkout JSONArray and JSONObject manipulation. Do small experiments and understand how to extract questions
        return question;
    }
}
