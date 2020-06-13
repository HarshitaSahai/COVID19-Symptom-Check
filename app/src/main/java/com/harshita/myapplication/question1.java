
package com.harshita.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

public class question1  extends AppCompatActivity {
    protected MessagesListAdapter<Message> messagesAdapter;
    private MessagesList messagesList;

    //WebView wv ;
    //String url = "https://covid-19.ada.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question1);

        messagesList = findViewById(R.id.messagesList);

        Context context = getApplicationContext();
        try {
            messagesAdapter = new MessagesListAdapter<>("1",null);
            messagesAdapter.addToStart(getTextMessage("welcome!"),true);
            messagesList.setAdapter(messagesAdapter);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Author getAuthor(){
        return new Author("0","Covid Bot", null,true);
    }

    public static Message getTextMessage(String text) {
        return new Message("0", getAuthor(), text);
    }
}

