package com.example.mandisi.myassign7.settings.Imp;

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
import com.example.mandisi.myassign7.domain.ContactType;
import com.example.mandisi.myassign7.settings.ContactTypeRespository;

/**
 * Created by Mandisi on 2016/04/16.
 */
public class ContactTypeRespositoryImpl extends SQLiteOpenHelper implements ContactTypeRespository {
    public static final String TABLE_NAME = "contacttype";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_STATE = "state";
    public static final String COLUMN_SERVERID = "serverid";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT  NOT NULL , "
            + COLUMN_STATE + " TEXT NOT NULL, "
            + COLUMN_SERVERID + " TEXT NOT NULL );";


    public ContactTypeRespositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public ContactType findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_STATE,
                        COLUMN_SERVERID},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final ContactType contactTypes = new ContactType.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .state(cursor.getString(cursor.getColumnIndex(COLUMN_STATE)))
                    .serverId(cursor.getString(cursor.getColumnIndex(COLUMN_SERVERID)))
                    .build();

            return contactTypes;
        } else {
            return null;
        }
    }

    @Override
    public ContactType save(ContactType entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_STATE, entity.getName());
        values.put(COLUMN_SERVERID, entity.getServerId());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        ContactType insertedEntity = new ContactType.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public ContactType update(ContactType entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_STATE, entity.getState());
        values.put(COLUMN_SERVERID, entity.getServerId());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public ContactType delete(ContactType entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<ContactType> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<ContactType> contactTypes = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                final ContactType contactType = new ContactType.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .state(cursor.getString(cursor.getColumnIndex(COLUMN_STATE)))
                        .serverId(cursor.getString(cursor.getColumnIndex(COLUMN_SERVERID)))
                        .build();
                contactTypes.add(contactType);
            } while (cursor.moveToNext());
        }
        return contactTypes;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME, null, null);
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
