package com.harshita.myapplication.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.cardview.widget.CardView;

import com.harshita.myapplication.R;


public class ItemSentView extends MessageView {

    private CardView bubble;
    private TextView messageTextView, timestampTextView;
    private LinearLayout customView;


    /**
     * Method to set the messages text in the view so it can be displayed on the screen.
     * @param message   The message that you want to be displayed.
     */
    public void setMessage(String message) {

        if (messageTextView == null) {

            messageTextView = (TextView) findViewById(R.id.message_text_view);

        }

        messageTextView.setText(message);


    }

    public void setMessage(View message) {
        if(customView == null) {
            customView = (LinearLayout) findViewById(R.id.message_custom_view) ;
        }

        for(int i=0;i<customView.getChildCount();i++){
            ((ViewGroup)customView.getChildAt(i)).removeAllViews();
        }
        try{

            customView.addView(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method to set the timestamp that the message was received or sent on the screen.
     * @param timestamp The timestamp that you want to be displayed.
     */
    public void setTimestamp(String timestamp) {

        if (timestampTextView == null) {

            timestampTextView = (TextView) findViewById(R.id.timestamp_text_view);

        }

        timestampTextView.setText(timestamp);

    }

    /**
     * Method to set the background color that you want to use in your message.
     * @param background The background that you want to be displayed.
     */
    public void setBackground(@ColorInt int background) {

        if (bubble == null) {

            this.bubble = (CardView) findViewById(R.id.bubble);

        }

        bubble.setCardBackgroundColor(background);

    }

    /**
     * Method to set the elevation of the view.
     * @param elevation The elevation that you want the view to be displayed at.
     */
    public void setElevation(float elevation) {

        if (bubble == null) {

            this.bubble = (CardView) findViewById(R.id.bubble);

        }

        bubble.setCardElevation(elevation);

    }

    /**
     * Constructs a new message view.
     * @param context
     */
    public ItemSentView(Context context) {

        super(context);
        initializeView(context);

    }

    /**
     * Constructs a new message view with attributes, this constructor is used when we create a
     * message view using XML.
     * @param context
     * @param attrs
     */
    public ItemSentView(Context context, AttributeSet attrs) {

        super(context, attrs);
        initializeView(context);

    }

    /**
     * Inflates the view so it can be displayed and grabs any child views that we may require
     * later on.
     * @param context   The context that is used to inflate the view.
     */
    private void initializeView(Context context) {

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.chat_item_sent, this);

        this.bubble = (CardView) findViewById(R.id.bubble);
        this.messageTextView = (TextView) findViewById(R.id.message_text_view);
        this.timestampTextView = (TextView) findViewById(R.id.timestamp_text_view);

    }

}