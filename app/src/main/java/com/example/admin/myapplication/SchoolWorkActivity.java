package com.example.admin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SchoolWorkActivity extends AppCompatActivity  {

    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    String messageText;
    int position;


    @Override
    public void onBackPressed() {
        try {
            PrintWriter pw =new PrintWriter(openFileOutput("Todo.txt",Context.MODE_PRIVATE));
            for (String data : arrayList){
                pw.print(data);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_work2);


        listView = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(SchoolWorkActivity.this, EditmessageClass.class);
                intent.putExtra(Intent_Constance.INTENT_MESSAGE_DATA, arrayList.get(position).toString());
                intent.putExtra(Intent_Constance.INTENT_ITEM_POSITION, position);
                startActivityForResult(intent, Intent_Constance.INTENT_REQUEST_CODE_TWO);
            }
        });
        try {
            Scanner sc =new Scanner(openFileInput("Todo.txt"));
            while (sc.hasNextLine()){
                String data =sc.nextLine();
                arrayAdapter.add(data);
            }
            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
    public void onClick (View v){
        Intent intent =new Intent();
        intent.setClass(SchoolWorkActivity.this,EditmessageClass.class);
        startActivityForResult(intent,Intent_Constance.Intent_Request_Code);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==Intent_Constance.INTENT_RESULTS_CODE){
            messageText=data.getStringExtra(Intent_Constance.INTENT_MESSAGE_FIELD);
            arrayList.add(messageText);
            arrayAdapter.notifyDataSetChanged();

        }else if (resultCode==Intent_Constance.INTENT_REQUEST_CODE_TWO){
            messageText=data.getStringExtra(Intent_Constance.INTENT_CHANGED_MESSAGE);
            position= data.getIntExtra(Intent_Constance.INTENT_ITEM_POSITION,-1);
            arrayList.remove(position);
            arrayList.add(position,messageText);
            arrayAdapter.notifyDataSetChanged();


        }
        onBackPressed();
    }
}