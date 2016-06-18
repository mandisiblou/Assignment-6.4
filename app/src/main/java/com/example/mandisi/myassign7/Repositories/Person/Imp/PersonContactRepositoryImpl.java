package com.example.mandisi.myassign7.Repositories.Person.Imp;

/**
 * Created by Mandisi on 2016-06-09.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import com.example.mandisi.myassign7.conf.databases.DBConstants;
import com.example.mandisi.myassign7.conf.util.AppUtil;
import com.example.mandisi.myassign7.Persons.PersonContact;
import com.example.mandisi.myassign7.Repositories.Person.PersonContactRepository;

public class PersonContactRepositoryImpl extends SQLiteOpenHelper implements PersonContactRepository {
    public static final String TABLE_NAME = "pcontacts";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CONTACTTYPE = "contactTypeId";
    public static final String COLUMN_CONTACTVALUE = "contactValue";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_STATE = "state";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " TEXT  PRIMARY KEY , "
            + COLUMN_CONTACTTYPE + " TEXT UNIQUE NOT NULL , "
            + COLUMN_CONTACTVALUE + " TEXT  NOT NULL , "
            + COLUMN_STATUS + " TEXT  NOT NULL , "
            + COLUMN_DATE + " TEXT  NOT NULL , "
            + COLUMN_STATE + " TEXT NOT NULL );";


    public PersonContactRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public PersonContact findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_CONTACTTYPE,
                        COLUMN_CONTACTVALUE,
                        COLUMN_STATUS,
                        COLUMN_DATE,
                        COLUMN_STATE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final PersonContact personalAddress = new PersonContact.Builder()
                    .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                    .contactTypeId(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACTTYPE)))
                    .date(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE))))
                    .status(cursor.getString(cursor.getColumnIndex(COLUMN_STATUS)))
                    .contactValue(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACTVALUE)))
                    .state(cursor.getString(cursor.getColumnIndex(COLUMN_STATE)))
                    .build();

            return personalAddress;
        } else {
            return null;
        }
    }

    @Override
    public PersonContact save(PersonContact entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CONTACTTYPE, entity.getContactTypeId());
        values.put(COLUMN_DATE, entity.getDate().toString());
        values.put(COLUMN_CONTACTVALUE, entity.getContactValue());
        values.put(COLUMN_STATE, entity.getState());
        values.put(COLUMN_STATUS, entity.getStatus());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        return entity;
    }

    @Override
    public PersonContact update(PersonContact entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CONTACTTYPE, entity.getContactTypeId());
        values.put(COLUMN_DATE, entity.getDate().toString());
        values.put(COLUMN_CONTACTVALUE, entity.getContactValue());
        values.put(COLUMN_STATE, entity.getState());
        values.put(COLUMN_STATUS, entity.getStatus());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public PersonContact delete(PersonContact entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<PersonContact> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<PersonContact> personAddresses = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final PersonContact personAddress = new PersonContact.Builder()
                        .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                        .contactTypeId(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACTTYPE)))
                        .date(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE))))
                        .status(cursor.getString(cursor.getColumnIndex(COLUMN_STATUS)))
                        .contactValue(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACTVALUE)))
                        .state(cursor.getString(cursor.getColumnIndex(COLUMN_STATE)))
                        .build();
                personAddresses.add(personAddress);
            } while (cursor.moveToNext());
        }
        return personAddresses;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

