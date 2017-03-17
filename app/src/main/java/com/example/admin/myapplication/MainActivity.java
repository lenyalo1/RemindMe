package com.example.admin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.ui.auth.AuthUI;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


import static android.app.Activity.RESULT_OK;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int RC_SIGN_IN = 0;
    private FirebaseAuth auth;

    private TextView mSchool;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView Exam_TT = (TextView) findViewById(R.id.Exam_TT);
        mSchool = (TextView) findViewById(R.id.tv_school);



        mSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, SchoolWorkActivity.class));

            }
        });

//        school_work.setOnClickListener(new View.OnClickListener() {
//            // The code in this method will be executed when the school_work category is clicked on.
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


        TextView exam_TT = (TextView) findViewById(R.id.Exam_TT);

        Exam_TT.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Exam_TT category is clicked on.
            @Override
            public void onClick(View view) {
                Intent Exam_TTIntent = new Intent(MainActivity.this,Exam_TT.class);
                startActivity(Exam_TTIntent);
            }
        });
        TextView announce = (TextView) findViewById(R.id.Announcements);

        announce.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Exam_TT category is clicked on.
            @Override
            public void onClick(View view) {
                Intent announceIntent = new Intent(MainActivity.this,AnnouncementActivity.class);
                startActivity(announceIntent);
            }
        });
            // The code in this method will be executed when the numbers category is clicked on.

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {

            //user already signed in
            Toast.makeText(MainActivity.this, "You're now signed in.Welcome to Remind Me!", Toast.LENGTH_SHORT).show();

            Log.d("Auth", auth.getCurrentUser().getEmail());

        } else {
            startActivityForResult(AuthUI.getInstance()

                    .createSignInIntentBuilder()
                    .setLogo(R.drawable.remind_me_logos)
                    .setTheme(R.style.AppTheme)
                    .setIsSmartLockEnabled(false)
                    .setProviders(
                            AuthUI.GOOGLE_PROVIDER,
                            AuthUI.EMAIL_PROVIDER)
                    .build(), RC_SIGN_IN);
        }
        findViewById(R.id.logout_btn).setOnClickListener(this);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==RC_SIGN_IN){
            if (resultCode ==RESULT_OK){
                //user logged in
                Log.d("Auth", auth.getCurrentUser().getEmail());

            }
            else{
                //user not authenticated
                Log.d("Auth", "NOT AUTHENTICATED");
            }
        }
    }

    @Override
    public void onClick(View view) {
       if (view.getId() == R.id.logout_btn){
           AuthUI.getInstance()
                   .signOut(this)
                   .addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           Log.d("Auth", "USER LOGGED OUT");
                           finish();
                       }
                   });
       }

    }

}