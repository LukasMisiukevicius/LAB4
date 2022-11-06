package com.example.lab4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "NotesApp.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "notes";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "note_title";
    private static final String COLUMN_CONTENT = "note_content";
    private static final String COLUMN_TIME = "create_time";

    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " + COLUMN_CONTENT + " TEXT, " + COLUMN_TIME + " LONG);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addNote(String title, String content, long time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_CONTENT, content);
        cv.put(COLUMN_TIME, time);
        long result;
        if(!title.isEmpty())
        {
            if(!content.isEmpty())
            {
                result = db.insert(TABLE_NAME, null, cv);
            }else
            {
                Toast.makeText(context, "Content can not be empty!", Toast.LENGTH_SHORT).show();
                result = -1;
            }
        }else
        {
            Toast.makeText(context, "Title can not be empty!", Toast.LENGTH_SHORT).show();
            result = -1;
        }
        if(result == -1)
        {
            Toast.makeText(context,"Error Creating New Note",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(context,"Successfully Created New Note",Toast.LENGTH_LONG).show();

        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String title, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_CONTENT,content);
        long time = System.currentTimeMillis();
        cv.put(COLUMN_TIME,time);
        long result;
        if(!title.isEmpty())
        {
            if(!content.isEmpty())
            {
                result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
            }else
            {
                Toast.makeText(context, "Content can not be empty!", Toast.LENGTH_SHORT).show();
                result = -1;
            }
        }else
        {
            Toast.makeText(context, "Title can not be empty!", Toast.LENGTH_SHORT).show();
            result = -1;
        }
        if(result == -1){
            Toast.makeText(context, "Error Updating", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();

        }
    }
}
