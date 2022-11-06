package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class AddNoteActivity extends AppCompatActivity {
    EditText etTitle, etNoteContent;
    Button saveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        etTitle = findViewById(R.id.etTitle);
        etNoteContent = findViewById(R.id.etNoteContent);
        saveButton = findViewById(R.id.btnSave);
        saveButton.setOnClickListener(view -> {
                String title = etTitle.getText().toString();
                String content = etNoteContent.getText().toString();
                long createdTime = System.currentTimeMillis();

                DatabaseHelper dbHE = new DatabaseHelper(AddNoteActivity.this);
                dbHE.addNote(title, content, createdTime);

        });
    }

}