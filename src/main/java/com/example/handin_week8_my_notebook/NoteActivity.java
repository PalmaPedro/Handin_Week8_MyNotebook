package com.example.handin_week8_my_notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.handin_week8_my_notebook.MainActivity.notesList;

public class NoteActivity extends AppCompatActivity {

    //TODO:
    // 1. create ArrayList of Strings to save notes
    // 2. evaluate which item from the MainActivity list started the current NoteActivity
    // 3. if note already exists, load corresponding saved note from the ArrayList; if not, create a new note

    private ArrayList<String> savedNotes = new ArrayList<>();

    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        editText = findViewById(R.id.editText);

        // evaluate which item from the notesList in MainActivity was pressed
        

        loadSavedPreferences();
    }

    private void loadSavedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        editText.setText(sharedPreferences.getString("editText", ""));
    }

    private void savePreferences(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private void saveData() {
        savePreferences("editText", editText.getText().toString());
    }

    @Override
    public void onBackPressed(){
        // displays toast message when item in list is pressed
        Toast.makeText(getApplicationContext(), "saved ", Toast.LENGTH_SHORT).show();
        saveData();
        super.onBackPressed();

    }
}
