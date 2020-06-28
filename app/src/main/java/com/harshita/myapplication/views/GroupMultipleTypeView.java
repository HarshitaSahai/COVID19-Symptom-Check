package com.harshita.myapplication.views;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import org.json.JSONArray;

public class GroupMultipleTypeView extends LinearLayout {

    private JSONArray itemsArray;
    private LinearLayout checkboxHolder;

    private Button bgmp ;
    private Context context;

    public GroupMultipleTypeView(Context context) {
        super(context);
        this.context = context;

        initiateView();
    }

    private void initiateView(){

        checkboxHolder = new LinearLayout(context);
        checkboxHolder.setOrientation(LinearLayout.VERTICAL);

        bgmp = new Button(context);
        bgmp.setText("Next"); // Button for storing value in evidence[]

    }

    public void setItemsArray(String items){
        try{
             itemsArray = new JSONArray(items);

            for(int i=0; i<itemsArray.length();i++){
                CheckBox selectableCheckBox = new CheckBox(context);
                selectableCheckBox.setText(itemsArray.getJSONObject(i).getString("name"));
                checkboxHolder.addView(selectableCheckBox,i);
            }

            checkboxHolder.addView(bgmp,itemsArray.length()); // Adding button to view

            invalidate();
        }catch (Exception ignore){}
    }

    public Button getBgmp(){return bgmp;}

    public LinearLayout getCheckboxHolder(){return checkboxHolder;}
}
