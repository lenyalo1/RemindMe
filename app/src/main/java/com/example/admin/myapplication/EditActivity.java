package com.example.admin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.R.attr.id;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do);
    }
    public void saveButtonClicked(View v){
        String messageText = ((EditText)findViewById(R.id.editInformation)).getText().toString();
        if (messageText.equals("")){

        }else {
            Intent intent =new Intent();
            intent.putExtra(Intent_Constance.INTENT_MESSAGE_FIELD,messageText);
            setResult(Intent_Constance.INTENT_RESULTS_CODE,intent);
            finish();
        }


    }
}
