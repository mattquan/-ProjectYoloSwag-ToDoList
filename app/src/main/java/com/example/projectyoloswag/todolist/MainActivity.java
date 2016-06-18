package com.example.projectyoloswag.todolist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferenceSettings;
    private SharedPreferences.Editor preferenceEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferenceSettings = getPreferences(0);
        preferenceEditor = preferenceSettings.edit();

        Set<String> myTasks = new HashSet();

        if(preferenceSettings.getStringSet("myTasks", null) == null) {
            preferenceEditor.putStringSet("myTasks", myTasks);
            preferenceEditor.apply();
        }

        Intent intent = getIntent();
        if(intent.getStringExtra(NewTask.EXTRA_MESSAGE) != null) {
            String message = intent.getStringExtra(NewTask.EXTRA_MESSAGE);
            myTasks = preferenceSettings.getStringSet("myTasks", null);
            myTasks.add(message);
            preferenceEditor.putStringSet("myTasks", myTasks);
            preferenceEditor.apply();
        }

        myTasks = preferenceSettings.getStringSet("myTasks", null);

        ListView listView = (ListView) findViewById(R.id.list);

        String[] newArray = myTasks.toArray(new String[myTasks.size()]);

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, newArray);
        listView.setAdapter(itemsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createNewTask(View view){
        Intent intent = new Intent(this, NewTask.class);
        startActivity(intent);
    }
}