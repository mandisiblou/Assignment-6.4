package com.example.mandisi.myassign7.CodeRepository.CodeRepositoryImp;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mandisi.myassign7.CodeRepository.CodeRepositories;
import com.example.mandisi.myassign7.ValuesObjects.Code;
import com.example.mandisi.myassign7.conf.databases.DBConstants;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mandisi on 2016-04-23.
 */
public class CodeRepositoryImp extends SQLiteOpenHelper implements CodeRepositories{
    public static final String TABLE_NAME = "code";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "codeId";
    public static final String COLUMN_NAME = "name";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " LONG  PRIMARY KEY, "
            + COLUMN_NAME + " TEXT  NOT NULL );";


    public CodeRepositoryImp(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Code findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Code code = new Code.Builder()
                    .codeId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .build();

            return code;
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
    public Code save(Code entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getCodeId());
        values.put(COLUMN_NAME, entity.getName());
        Long id = db.insertOrThrow(TABLE_NAME, null, values);
        Code insertedEntity = new Code.Builder()
                .copy(entity)
                .codeId(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Code update(Code entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getCodeId());
        values.put(COLUMN_NAME, entity.getName());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getCodeId())}
        );
        return entity;
    }

    @Override
    public Code delete(Code entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getCodeId())});
        return entity;
    }

    @Override
    public Set<Code> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Code> code = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Code setting = new Code.Builder()
                        .codeId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .build();
                code.add(setting);
            } while (cursor.moveToNext());
        }
        return code;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
