package com.example.mandisi.myassign7.StaffsRepository.StaffsRepositoryImp;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mandisi.myassign7.EntityObjects.Staffs;
import com.example.mandisi.myassign7.StaffsRepository.StaffsRepositories;
import com.example.mandisi.myassign7.conf.databases.DBConstants;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mandisi on 2016-04-22.
 */
public class StaffsRepositoryImp extends SQLiteOpenHelper implements StaffsRepositories{
    public static final String TABLE_NAME = "staffs";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "SID";
    public static final String COLUMN_YEAR = "yearOfBirth";
    public static final String COLUMN_NAME= "name";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " LONG  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_YEAR + " TEXT  NOT NULL, "
            + COLUMN_NAME + " TEXT  NOT NULL );";


    public StaffsRepositoryImp(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Staffs findById(Long id) {

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
            final Staffs staffs = new Staffs.Builder()
                    .SID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .yearOfBirth(cursor.getInt(cursor.getColumnIndex(COLUMN_YEAR)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .build();
            return staffs;
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
    public Staffs save(Staffs entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getSID());
        values.put(COLUMN_YEAR, entity.getYearOfBirth());
        values.put(COLUMN_NAME, entity.getName());
        Long id = db.insertOrThrow(TABLE_NAME, null, values);
        Staffs insertedEntity = new Staffs.Builder()
                .copy(entity)
                .SID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Staffs update(Staffs entity) {
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
    public Staffs delete(Staffs entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getSID())});
        return entity;
    }

    @Override
    public Set<Staffs> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Staffs> Addrec = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Staffs setting = new Staffs.Builder()
                        .SID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .yearOfBirth(cursor.getInt(cursor.getColumnIndex(COLUMN_YEAR)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .build();
                Addrec.add(setting);
            } while (cursor.moveToNext());
        }
        return Addrec;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}