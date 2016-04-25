package com.example.mandisi.myassign7.ResultsRepository.ResultsRepositoryImp;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mandisi.myassign7.EventsObjects.Results;
import com.example.mandisi.myassign7.ResultsRepository.ResultsRepositories;
import com.example.mandisi.myassign7.conf.databases.DBConstants;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nkuli on 2016-04-22.
 */
public class ResultsRepositoryImp extends SQLiteOpenHelper implements ResultsRepositories{
    public static final String TABLE_NAME = "results";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ANSWEAR = "answear";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " TEXT  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT  NOT NULL , "
            + COLUMN_ANSWEAR + " TEXT NOT NULL );";

    public ResultsRepositoryImp(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Results findById(String id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_ANSWEAR},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Results Results = new Results.Builder()
                    .resultsID(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                    .questionName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .corrects(cursor.getString(cursor.getColumnIndex(COLUMN_ANSWEAR)))
                    .build();
            return Results;
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
    public Results save(Results entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getResultsID());
        values.put(COLUMN_NAME, entity.getQuestionName());
        values.put(COLUMN_ANSWEAR, entity.getCorrects());
        String id = "db.insertOrThrow(TABLE_NAME, null, values)";
        Results insertedEntity = new Results.Builder()
                .copy(entity)
                .resultsID(new String(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Results update(Results entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getResultsID());
        values.put(COLUMN_NAME, entity.getQuestionName());
        values.put(COLUMN_ANSWEAR, entity.getCorrects());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getResultsID())}
        );
        return entity;
    }

    @Override
    public Results delete(Results entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getResultsID())});
        return entity;
    }

    @Override
    public Set<Results> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Results> Results = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Results setting = new Results.Builder()
                        .resultsID(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                        .questionName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .corrects(cursor.getString(cursor.getColumnIndex(COLUMN_ANSWEAR)))
                        .build();
                Results.add(setting);
            } while (cursor.moveToNext());
        }
        return Results;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
