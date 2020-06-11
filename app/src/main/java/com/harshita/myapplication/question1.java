
package com.harshita.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.PhoneAuthProvider;

import co.chatsdk.core.session.Configuration;
import co.chatsdk.core.session.InterfaceManager;
import co.chatsdk.firebase.file_storage.FirebaseFileStorageModule;
import co.chatsdk.firebase.FirebaseNetworkAdapter;
import co.chatsdk.core.error.ChatSDKException;
import co.chatsdk.core.session.ChatSDK;
import co.chatsdk.ui.manager.BaseInterfaceAdapter;
import co.chatsdk.firebase.ui.FirebaseUIModule;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class question1  extends AppCompatActivity {


    //WebView wv ;
    //String url = "https://covid-19.ada.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question1);
        Context context = getApplicationContext();
        try {

            // The configuration object contains all the Chat SDK settings. If you want to see a full list of the settings
            // you should look inside the `Configuration` object (CMD+Click it in Android Studio) then you can see every
            // setting and the accompanying comment
            Configuration.Builder config = new Configuration.Builder(context);

            // Perform any configuration steps
            // The root path is an optional setting that allows you to run multiple Chat SDK instances on one Realtime database.
            // For example, you could have one root path for "test" and another for "production"
            config.firebaseRootPath("prod");

            // Start the Chat SDK and pass in the interface adapter and network adapter. By subclassing either
            // of these classes you could modify deep functionality withing the Chat SDK
            ChatSDK.initialize(config.build(), new FirebaseNetworkAdapter(), new BaseInterfaceAdapter(context));
        }
        catch (ChatSDKException e) {
        }

        // File storage is needed for profile image upload and image messages
        FirebaseFileStorageModule.activate();
        FirebaseUIModule.activate(EmailAuthProvider.PROVIDER_ID, PhoneAuthProvider.PROVIDER_ID);
        //InterfaceManager.shared().a.startLoginActivity(context, true);

        //wv.setWebViewClient(new WebViewClient());
        //wv.getSettings().setJavaScriptEnabled(true);
        //wv.loadUrl(url);


       addListenerOnButtonClick();
    }

    public void addListenerOnButtonClick() {
        final RadioButton Male, Female;

        Male = (RadioButton) findViewById(R.id.male);
        Female = (RadioButton) findViewById(R.id.female);

        Button getstarted1 = (Button) findViewById(R.id.toq2);
        getstarted1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                if (!Male.isChecked() && !Female.isChecked())
                    Toast.makeText(question1.this, "Please Select Gender", Toast.LENGTH_SHORT).show();

                else {
                    final String gender;
                    if (Male.isChecked())
                        gender = "male";
                    else
                        gender = "female";
                    JSONObject obj1;
                    try {
                        obj1 = new JSONObject();
                        obj1.put("sex", gender);
                    } catch (Exception e) {
                        e.printStackTrace();
                        obj1 = null;
                    }
                    Intent i1 = new Intent(question1.this,question2.class).putExtra("jsonObject",obj1.toString());
                    startActivity(i1);
                }
            }

        });
    }
}

