package com.example.mandisi.myassign7.AddrecRepository.AddrecRepositoryImp;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mandisi.myassign7.AddrecRepository.AddrecRepositories;
import com.example.mandisi.myassign7.ValuesObjects.Addrec;
import com.example.mandisi.myassign7.conf.databases.DBConstants;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mandisi on 2016-04-24.
 */
public class AddrecRepositoryImp extends SQLiteOpenHelper implements AddrecRepositories{
    public static final String TABLE_NAME = "addrec";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "codeId";
    public static final String COLUMN_STREET = "street";
    public static final String COLUMN_TOWN = "town";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_COUNTRY = "country";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " LONG  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_STREET + " TEXT  NOT NULL, "
            + COLUMN_TOWN + " TEXT  NOT NULL, "
            + COLUMN_CODE + " TEXT  NOT NULL, "
            + COLUMN_COUNTRY + " TEXT  NOT NULL );";


    public AddrecRepositoryImp(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Addrec findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_STREET,
                        COLUMN_TOWN,
                        COLUMN_CODE,
                        COLUMN_COUNTRY},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Addrec Addrec = new Addrec.Builder()
                    .addressId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .street(cursor.getString(cursor.getColumnIndex(COLUMN_STREET)))
                    .town(cursor.getString(cursor.getColumnIndex(COLUMN_TOWN)))
                    .postCode(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                    .country(cursor.getString(cursor.getColumnIndex(COLUMN_COUNTRY)))
                    .build();
            return Addrec;
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
    public Addrec save(Addrec entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getAddressId());
        values.put(COLUMN_STREET, entity.getStreet());
        values.put(COLUMN_TOWN, entity.getTown());
        values.put(COLUMN_CODE, entity.getPostCode());
        values.put(COLUMN_COUNTRY, entity.getCountry());
        Long id = db.insertOrThrow(TABLE_NAME, null, values);
        Addrec insertedEntity = new Addrec.Builder()
                .copy(entity)
                .addressId(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Addrec update(Addrec entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getAddressId());
        values.put(COLUMN_STREET, entity.getStreet());
        values.put(COLUMN_TOWN, entity.getTown());
        values.put(COLUMN_CODE, entity.getPostCode());
        values.put(COLUMN_COUNTRY, entity.getCountry());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getAddressId())}
        );
        return entity;
    }

    @Override
    public Addrec delete(Addrec entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getAddressId())});
        return entity;
    }

    @Override
    public Set<Addrec> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Addrec> Addrec = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Addrec setting = new Addrec.Builder()
                        .addressId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .street(cursor.getString(cursor.getColumnIndex(COLUMN_STREET)))
                        .town(cursor.getString(cursor.getColumnIndex(COLUMN_TOWN)))
                        .postCode(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                        .country(cursor.getString(cursor.getColumnIndex(COLUMN_COUNTRY)))
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

