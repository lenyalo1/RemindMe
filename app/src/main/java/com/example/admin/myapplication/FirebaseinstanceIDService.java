package com.example.admin.myapplication;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


public class FirebaseinstanceIDService extends FirebaseInstanceIdService {

    public static final String TAG ="Notification";
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token =FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Token:" + token );

    }
}




