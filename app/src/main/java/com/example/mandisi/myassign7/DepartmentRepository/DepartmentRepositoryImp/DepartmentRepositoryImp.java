package com.example.mandisi.myassign7.DepartmentRepository.DepartmentRepositoryImp;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mandisi.myassign7.DepartmentRepository.DepartmentRepositories;
import com.example.mandisi.myassign7.ValuesObjects.Department;
import com.example.mandisi.myassign7.conf.databases.DBConstants;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nkuli on 2016-04-23.
 */
public class DepartmentRepositoryImp extends SQLiteOpenHelper implements DepartmentRepositories{
    public static final String TABLE_NAME = "departments";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "SID";
    public static final String COLUMN_NAME = "name";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " TEXT  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT  NOT NULL );";


    public DepartmentRepositoryImp(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Department findById(String id) {

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
            final Department Department = new Department.Builder()
                    .SID(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .build();
            return Department;
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
    public Department save(Department entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getSID());
        values.put(COLUMN_NAME, entity.getName());
        String id = "db.insertOrThrow(TABLE_NAME, null, values)";
        Department insertedEntity = new Department.Builder()
                .copy(entity)
                .SID(new String(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Department update(Department entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getSID());
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
    public Department delete(Department entity)
    {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getSID())});
        return entity;
    }

    @Override
    public Set<Department> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Department> Department = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Department setting = new Department.Builder()
                        .SID(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .build();
                Department.add(setting);
            } while (cursor.moveToNext());
        }
        return Department;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
