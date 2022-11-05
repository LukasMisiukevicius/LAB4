package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class AddNoteActivity extends AppCompatActivity {
    EditText etTitle, etNoteContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        etTitle = findViewById(R.id.etTitle);
        etNoteContent = findViewById(R.id.etNoteContent);
    }

    public void onSaveButtonClick(View view) {
        String title = etTitle.getText().toString();
        String content = etNoteContent.getText().toString();

        Toast.makeText(getApplicationContext(),
                title, Toast.LENGTH_LONG).show();



    }
}