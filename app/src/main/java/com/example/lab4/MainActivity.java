package com.example.lab4;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    Button newButton;
    DatabaseHelper dbHE;
    RecyclerView recycleView;
    ArrayList<String> noteId, title, content, time;
    CustomAdapter customAdapter;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycleView = findViewById(R.id.rvMain);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        dbHE = new DatabaseHelper(MainActivity.this);
        noteId = new ArrayList<>();
        title = new ArrayList<>();
        content = new ArrayList<>();
        time = new ArrayList<>();
        customAdapter = new CustomAdapter(MainActivity.this, noteId, title, content, time);
        recycleView.setAdapter(customAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }


    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.secondary_menu, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        storeDataInArrays();
        customAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_note:
                Intent i = new Intent(this, AddNoteActivity.class);
                startActivity(i);
                return true;

        }
        return true;
    }


    void storeDataInArrays(){
        Cursor cursor = dbHE.readAllData();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data",Toast.LENGTH_SHORT).show();
        }else
        {
            noteId.clear();
            title.clear();
            content.clear();
            time.clear();
            while(cursor.moveToNext()){
                noteId.add(cursor.getString(0));
                title.add(cursor.getString(1));
                content.add(cursor.getString(2));
                time.add(cursor.getString(3));
            }
        }
    }
}




