package com.example.handin_week8_my_notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private ListView listView;

    public static final String messageKey = "MESSAGE_KEY";
    public static ArrayList<String> notesList = new ArrayList<>();

    private int clickCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // registers which note item was pressed
                Log.i("note selected ", notesList.get(i));
                // displays toast message when item in list is pressed
                Toast.makeText(getApplicationContext(), "pressed " + notesList.get(i), Toast.LENGTH_SHORT).show();
                // opens new activity when note item is pressed
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                //intent.putExtra("Title", listView.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });
    }

    // handles button '+' that adds a new note to list
    public void addNote(View view) {
        String s = "Note ";
        notesList.add(s + clickCounter++);
        adapter.notifyDataSetChanged();
    }
}
