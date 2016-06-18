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
import com.example.mandisi.myassign7.Persons.PersonAddress;
import com.example.mandisi.myassign7.Repositories.Person.PersonAddressRepository;

public class PersonAddressRepositoryImpl extends SQLiteOpenHelper implements PersonAddressRepository {
    public static final String TABLE_NAME = "paddress";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_POSTALCODE = "postalCode";
    public static final String COLUMN_ADDRESSTYPE = "addressTypeId";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_STATE = "state";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " TEXT  PRIMARY KEY , "
            + COLUMN_DESCRIPTION + " TEXT NOT NULL , "
            + COLUMN_POSTALCODE + " TEXT NOT NULL , "
            + COLUMN_ADDRESSTYPE + " TEXT  NOT NULL , "
            + COLUMN_STATUS + " TEXT  NOT NULL , "
            + COLUMN_DATE + " TEXT  NOT NULL , "
            + COLUMN_STATE + " TEXT NOT NULL );";


    public PersonAddressRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public PersonAddress findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_DESCRIPTION,
                        COLUMN_POSTALCODE,
                        COLUMN_ADDRESSTYPE,
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
            final PersonAddress personalAddress = new PersonAddress.Builder()
                    .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                    .addressTypeId(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESSTYPE)))
                    .date(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE))))
                    .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                    .status(cursor.getString(cursor.getColumnIndex(COLUMN_STATUS)))
                    .postalCode(cursor.getString(cursor.getColumnIndex(COLUMN_POSTALCODE)))
                    .state(cursor.getString(cursor.getColumnIndex(COLUMN_STATE)))
                    .build();

            return personalAddress;
        } else {
            return null;
        }
    }

    @Override
    public PersonAddress save(PersonAddress entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_ADDRESSTYPE, entity.getAddressTypeId());
        values.put(COLUMN_DATE, entity.getDate().toString());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_POSTALCODE, entity.getPostalCode());
        values.put(COLUMN_STATE, entity.getState());
        values.put(COLUMN_STATUS, entity.getStatus());
        db.insertOrThrow(TABLE_NAME, null, values);
        return entity;
    }

    @Override
    public PersonAddress update(PersonAddress entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_ADDRESSTYPE, entity.getAddressTypeId());
        values.put(COLUMN_DATE, entity.getDate().toString());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_POSTALCODE, entity.getPostalCode());
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
    public PersonAddress delete(PersonAddress entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<PersonAddress> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<PersonAddress> personAddresses = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final PersonAddress personAddress = new PersonAddress.Builder()
                        .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                        .addressTypeId(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESSTYPE)))
                        .date(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE))))
                        .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                        .status(cursor.getString(cursor.getColumnIndex(COLUMN_STATUS)))
                        .postalCode(cursor.getString(cursor.getColumnIndex(COLUMN_POSTALCODE)))
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

