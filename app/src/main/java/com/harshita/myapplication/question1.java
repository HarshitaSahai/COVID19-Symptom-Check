
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


import sdk.chat.app.firebase.ChatSDKFirebase;
import sdk.chat.core.session.ChatSDK;
import sdk.chat.firebase.adapter.module.FirebaseModule;
import sdk.chat.firebase.push.FirebasePushModule;
import sdk.chat.firebase.ui.FirebaseUIModule;
import sdk.chat.ui.module.UIModule;


import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;

public class question1  extends AppCompatActivity {


    //WebView wv ;
    //String url = "https://covid-19.ada.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.question1);
        Context context = getApplicationContext();
        try {

//            // The configuration object contains all the Chat SDK settings. If you want to see a full list of the settings
//            // you should look inside the `Configuration` object (CMD+Click it in Android Studio) then you can see every
//            // setting and the accompanying comment
//            Configuration.Builder config = new Configuration.Builder(context);
//
//            // Perform any configuration steps
//            // The root path is an optional setting that allows you to run multiple Chat SDK instances on one Realtime database.
//            // For example, you could have one root path for "test" and another for "production"
//            config.firebaseRootPath("prod");
//
//            // Start the Chat SDK and pass in the interface adapter and network adapter. By subclassing either
//            // of these classes you could modify deep functionality withing the Chat SDK
//
//            ChatSDK.initialize(config.build(), new FirebaseNetworkAdapter(), new BaseInterfaceAdapter(context));


            ChatSDK.builder()
                    .setGoogleMaps("Your Google Static Maps API key")
                    .setPublicChatRoomLifetimeMinutes(TimeUnit.HOURS.toMinutes(24))
                    .build()

                    // Add the Firebase network adapter module
                    .addModule(
                            FirebaseModule.builder()
                                    .setFirebaseRootPath("pre_1")
                                    .setDevelopmentModeEnabled(true)
                                    .build()
                    )

                    // Add the UI module
                    .addModule(UIModule.builder()
                            .setPublicRoomCreationEnabled(true)
                            .setPublicRoomsEnabled(true)
                            .build()
                    )

                    // Add modules to handle file uploads, push notifications
                    .addModule(sdk.chat.firebase.ui.FirebaseUIModule.shared())
                    .addModule(FirebasePushModule.shared())

                    // Enable Firebase UI with phone and email auth
                    .addModule(FirebaseUIModule.builder()
                            .setProviders(EmailAuthProvider.PROVIDER_ID, PhoneAuthProvider.PROVIDER_ID)
                            .build()
                    )


                    // Activate
                    .build()
                    .activate(this);
            ChatSDK.ui().startMainActivity(this);

        }
        catch (Exception e) {
            e.printStackTrace();
        }


        //InterfaceManager.shared().a.startLoginActivity(context, true);

        //wv.setWebViewClient(new WebViewClient());
        //wv.getSettings().setJavaScriptEnabled(true);
        //wv.loadUrl(url);


//       addListenerOnButtonClick();
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

