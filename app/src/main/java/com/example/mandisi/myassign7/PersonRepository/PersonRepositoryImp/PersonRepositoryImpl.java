package com.example.mandisi.myassign7.PersonRepository.PersonRepositoryImp;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.HashSet;
import java.util.Set;

import com.example.mandisi.myassign7.EntityObjects.Person;
import com.example.mandisi.myassign7.PersonRepository.PersonRepositories;
import com.example.mandisi.myassign7.conf.databases.DBConstants;

/**
 * Created by Mandisi on 2016-04-22.
 */
public class PersonRepositoryImpl extends SQLiteOpenHelper implements PersonRepositories{
    public static final String TABLE_NAME = "person";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_YEAR = "yearOfBirth";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " LONG  PRIMARY KEY, "
            + COLUMN_NAME + " TEXT  NOT NULL, "
            + COLUMN_YEAR + " INTEGER  NOT NULL );";


    public PersonRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Person findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Person addres = new Person();
        open();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_YEAR},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},null,null,null,null);
        if (cursor.moveToFirst()) {
            final Person addrec = new Person.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .yearOfBirth(cursor.getInt(cursor.getColumnIndex(COLUMN_YEAR)))
                    .build();
            return addres = addrec;
        }
        return addres;
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

    @Override
    public Person save(Person entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_YEAR, entity.getYearOfBirth());
        Long id = db.insertOrThrow(TABLE_NAME, null, values);
        Person insertedEntity = new Person.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Person update(Person entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_YEAR, entity.getYearOfBirth());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Person delete(Person entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Person> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Person> addrec = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Person setting = new Person.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .yearOfBirth(cursor.getInt(cursor.getColumnIndex(COLUMN_YEAR)))
                        .build();
                addrec.add(setting);
            } while (cursor.moveToNext());
        }
        return addrec;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}