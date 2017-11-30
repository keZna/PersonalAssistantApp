package com.example.codetride.personalassistantapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by CodeTride on 2017/10/11.
 */

public class ScheduleSQLHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;
    ContentValues values;

    public static final String DATABASE_NAME = "Schedules.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Schedule";
    public static final String _ID = "ID";
    public static final String NAME = "Name";
    public static final String DATE = "Date";
    public static final String DESCRIPTION = "Description";
    public static final String NOTIFY_ME = "Notify";
    public static final String Selection = _ID + " = ? ";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME + " TEXT NOT NULL,"
            + DESCRIPTION + " TEXT NOT NULL,"
            + DATE + " TEXT NOT NULL,"
            + NOTIFY_ME + " TEXT NOT NULL);";

    public ScheduleSQLHelper(Context context) {
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

    public void ADD(Contact contact) {
        db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(NAME, contact.getName());
        values.put(DESCRIPTION, contact.getDesc());
        values.put(DATE, contact.getDate());
        values.put(NOTIFY_ME, contact.getNotify());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public int Delete(Long delete) {
        int count = 0;
        db = this.getWritableDatabase();
        String[] selectionArgs = {String.valueOf(delete)};

        count = db.delete(TABLE_NAME,
                Selection,
                selectionArgs
        );
        return count;
    }

    public void DeleteAll() {
        db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    public ArrayList<Contact> getAll() {
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
                contact.setName(cursor.getString(1));
                contact.setDesc(cursor.getString(2));
                contact.setDate(cursor.getString(3));
                contact.setNotify(cursor.getString(4));
                contacts.add(contact);
            }
        }
        cursor.close();
        db.close();
        return contacts;
    }
}
