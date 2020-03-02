package com.example.notebook.storage;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.notebook.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "notedb";
    private static final String TABLE_NAME = "noteTable";

    public NoteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // declare table column names
    private static final String KEY_ID = "id"; //primary key
    private static final String KEY_HEADLINE = "headline";
    private static final String KEY_BODY = "body";

    // create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDB = "CREATE TABLE " + TABLE_NAME + " (" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_HEADLINE + " HEADLINE," +
                KEY_BODY + " BODY" + " )";
        db.execSQL(createDB);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_HEADLINE, note.getHeadline());
        values.put(KEY_BODY, note.getBody());

        // inserting data into db
        long ID = db.insert(TABLE_NAME, null, values);
        return ID;
    }

    public Note getNote(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] query = new String[]{KEY_ID, KEY_HEADLINE, KEY_BODY};
        Cursor cursor = db.query(TABLE_NAME, query, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return new Note(Long.parseLong(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2));
    }

    public List<Note> getAllNotes() {
        List<Note> allNotes = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + KEY_ID + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(Long.parseLong(cursor.getString(0)));
                note.setHeadline(cursor.getString(1));
                note.setBody(cursor.getString(2));
                allNotes.add(note);
            } while (cursor.moveToNext());
        }
        return allNotes;
    }

    public int editNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(KEY_HEADLINE, note.getBody());
        content.put(KEY_BODY, note.getBody());
        return db.update(TABLE_NAME, content, KEY_ID + "?", new String[]{String.valueOf(note.getId())});
    }

    void deleteNote(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
