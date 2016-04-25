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
 * Created by Nkuli on 2016-04-22.
 */
public class PersonRepositoryImpl extends SQLiteOpenHelper implements PersonRepositories{
    public static final String TABLE_NAME = "person";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_YEAR = "year";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " TEXT  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT  NOT NULL , "
            + COLUMN_YEAR + " INTEGER NOT NULL );";


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
    public Person findById(String id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_YEAR},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Person Person = new Person.Builder()
                    .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .yearOfBirth(cursor.getInt(cursor.getColumnIndex(COLUMN_YEAR)))
                    .build();
            return Person;
        } else {
            return null;
        }
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
        String id = "db.insertOrThrow(TABLE_NAME, null, values)";
        Person insertedEntity = new Person.Builder()
                .copy(entity)
                .id(new String(id))
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
        Set<Person> Person = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Person setting = new Person.Builder()
                        .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .yearOfBirth(cursor.getInt(cursor.getColumnIndex(COLUMN_YEAR)))
                        .build();
                Person.add(setting);
            } while (cursor.moveToNext());
        }
        return Person;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}

