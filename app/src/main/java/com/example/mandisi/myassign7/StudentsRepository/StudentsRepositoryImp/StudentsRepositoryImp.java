package com.example.mandisi.myassign7.StudentsRepository.StudentsRepositoryImp;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mandisi.myassign7.EntityObjects.Students;
import com.example.mandisi.myassign7.StudentsRepository.StudentsRepositories;
import com.example.mandisi.myassign7.conf.databases.DBConstants;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by Mandisi on 2016-04-22.
 */
public class StudentsRepositoryImp extends SQLiteOpenHelper implements StudentsRepositories{
    public static final String TABLE_NAME = "student";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "studentID";
    public static final String COLUMN_YEAR = "yearOfBirth";
    public static final String COLUMN_NAME = "name";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " LONG  PRIMARY KEY, "
            + COLUMN_YEAR + " TEXT  NOT NULL, "
            + COLUMN_NAME + " TEXT  NOT NULL );";


    public StudentsRepositoryImp(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Students findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_YEAR,
                        COLUMN_NAME},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Students students = new Students.Builder()
                    .studentID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .yearOfBirth(cursor.getInt(cursor.getColumnIndex(COLUMN_YEAR)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .build();
            return students;
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
    public Students save(Students entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getSID());
        values.put(COLUMN_YEAR, entity.getYearOfBirth());
        values.put(COLUMN_NAME, entity.getName());
        Long id = db.insertOrThrow(TABLE_NAME, null, values);
        Students insertedEntity = new Students.Builder()
                .copy(entity)
                .studentID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Students update(Students entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getSID());
        values.put(COLUMN_YEAR, entity.getYearOfBirth());
        values.put(COLUMN_NAME, entity.getName());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getSID())}
        );
        return entity;
    }

    @Override
    public Students delete(Students entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getSID())});
        return entity;
    }

    @Override
    public Set<Students> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Students> students = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Students setting = new Students.Builder()
                        .studentID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .yearOfBirth(cursor.getInt(cursor.getColumnIndex(COLUMN_YEAR)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .build();
                students.add(setting);
            } while (cursor.moveToNext());
        }
        return students;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
