package com.example.codetride.personalassistantapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by CodeTride on 2017/10/02.
 */

public class SQLHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;
    ContentValues values;

    public static final String DATABASE_NAME = "Chat.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Chat";
    public static final String _ID = "ID";
    public static final String MESSAGE = "Message";
    public static final String selection = _ID + " = ? ";
    public static final String DATE_TIME = "Date";
    public static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME + " ("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MESSAGE + " TEXT NOT NULL,"
            + DATE_TIME + " TEXT NOT NULL);";

    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST" + TABLE_NAME);
        sqLiteDatabase.close();
    }

    public void AddChat(Contact contact) {
        db = this.getWritableDatabase();

        values = new ContentValues();
        values.put(MESSAGE, contact.getMessage());
        values.put(DATE_TIME, contact.getDate());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public int Delete(Long x) {
        int count = 0;
        db = this.getWritableDatabase();
        String[] selectionArgs = {String.valueOf(x)};

        count = db.delete(TABLE_NAME,
                selection,
                selectionArgs
        );
        return count;
    }

    public void DeleteAll() {
        db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    public ArrayList<Contact> getChats() {
        ArrayList<Contact> contacts = new ArrayList<>();
        db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        //Contact
        Contact contact;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                contact = new Contact();
                contact.setId(cursor.getLong(0));
                contact.setMessage(cursor.getString(1));
                contact.setDate((cursor.getString(2)));

                contacts.add(contact);
            }
        }
        cursor.close();
        db.close();
        return contacts;
    }

    public ArrayList<Contact> getCommand() {
        ArrayList<Contact> contacts = new ArrayList<>();
        db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        Contact contact;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                contact = new Contact();
                contact.setId(cursor.getLong(0));
                contact.setMessage(cursor.getString(1));
                contact.setDate((cursor.getString(2)));

                contacts.add(contact);
            }
        }
        cursor.close();
        db.close();
        return contacts;
    }
}
