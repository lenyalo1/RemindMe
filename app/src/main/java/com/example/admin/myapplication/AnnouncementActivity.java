package com.example.admin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.firebase.messaging.FirebaseMessaging;
public class AnnouncementActivity extends AppCompatActivity {

   private TextView informationTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        FirebaseMessaging.getInstance().subscribeToTopic("news");


     informationTextView=(TextView) findViewById(R.id.InformationText);

        if (getIntent().getExtras() != null){
            for (String key : getIntent().getExtras().keySet()){
              String value = getIntent().getExtras().getString(key);
                informationTextView.append("\n" + key + ":" + value);
            }


        }

    }

}


