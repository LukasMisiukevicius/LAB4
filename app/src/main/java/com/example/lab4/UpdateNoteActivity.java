package com.example.lab4;

import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateNoteActivity extends AppCompatActivity {

    EditText etTitle, etContent;
    Button btnUpdate;

    String id, title, content;
    Long timeNew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        etTitle = findViewById(R.id.etTitleUpdate);
        etContent = findViewById(R.id.etNoteContentUpdate);
        btnUpdate = findViewById(R.id.btnUpdate);

        getAndSetIntentData();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dbHE = new DatabaseHelper(UpdateNoteActivity.this);
                title = etTitle.getText().toString();
                content = etContent.getText().toString();
                dbHE.updateData(id, title, content);
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("content")){
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            content = getIntent().getStringExtra("content");

            etTitle.setText(title);
            etContent.setText(content);
        }else{
            makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }
    }
}