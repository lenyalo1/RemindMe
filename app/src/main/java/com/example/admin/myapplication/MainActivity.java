package com.example.admin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.firebase.ui.auth.AuthUI;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int RC_SIGN_IN=0;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         auth=FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!=null){
            //user already signed in
            Toast.makeText(MainActivity.this,"You're now signed in.Welcome to Remind Me!", Toast.LENGTH_SHORT).show();

            Log.d("Auth", auth.getCurrentUser().getEmail());

        }else {  startActivityForResult(AuthUI.getInstance()

                .createSignInIntentBuilder()
                .setLogo(R.mipmap.remind3_orig)
                .setIsSmartLockEnabled(false)
                .setProviders(
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