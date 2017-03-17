package com.example.admin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditmessageClass extends AppCompatActivity {
    String messageText;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do);
        Intent intent = getIntent();
        messageText = intent.getStringExtra(Intent_Constance.INTENT_MESSAGE_DATA);
        position = intent.getIntExtra(Intent_Constance.INTENT_ITEM_POSITION, -1);
        EditText messageData = (EditText) findViewById(R.id.editInformation);
        messageData.setText(messageText);
    }
    public void saveButtonClicked (View v){
        String ChangeMessageText = ((EditText)findViewById(R.id.editInformation)).getText().toString();
        Intent intent=new Intent();
        intent.putExtra(Intent_Constance.INTENT_CHANGED_MESSAGE,ChangeMessageText);
        intent.putExtra(Intent_Constance.INTENT_ITEM_POSITION,position);
        setResult(Intent_Constance.INTENT_RESULTS_CODE_TWO,intent);
        finish();

    }

    }

