package com.example.projectyoloswag.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class NewTask extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.tinawu.myfirstapp.Message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
    }

    public void addTask(View view){
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(message);
        startActivity(intent);
    }
}