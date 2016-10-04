package com.example.jaspe.contact_card;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class PersonsDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "contacten.db";
    private static final int DATABASE_VERSION = 1;

    public PersonsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Hier de CRUD methoden
    public Cursor getPersons() {
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT id, naam, email, telefoon, adres, postcode, stad, plaatje FROM contact";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        db.close();
        return c;
    }
}