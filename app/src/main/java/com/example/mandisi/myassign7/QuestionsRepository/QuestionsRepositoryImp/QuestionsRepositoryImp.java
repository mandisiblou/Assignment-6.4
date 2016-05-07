package com.example.mandisi.myassign7.QuestionsRepository.QuestionsRepositoryImp;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mandisi.myassign7.QuestionsRepository.QuestionsRepositories;
import com.example.mandisi.myassign7.ValuesObjects.Questions;
import com.example.mandisi.myassign7.conf.databases.DBConstants;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nkuli on 2016-04-23.
 */
public class QuestionsRepositoryImp extends SQLiteOpenHelper implements QuestionsRepositories{
    public static final String TABLE_NAME = "questions";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "questionID";
    public static final String COLUMN_NAME = "questionName";
    public static final String COLUMN_QUESTIONS = "questions";
    public static final String COLUMN_CORRECTS = "corrects";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " LONG  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT  NOT NULL, "
            + COLUMN_QUESTIONS + " TEXT  NOT NULL, "
            + COLUMN_CORRECTS + " TEXT  NOT NULL );";


    public QuestionsRepositoryImp(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Questions findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_QUESTIONS,
                        COLUMN_CORRECTS},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Questions questions = new Questions.Builder()
                    .questionID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .questionName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .questions(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTIONS)))
                    .corrects(cursor.getString(cursor.getColumnIndex(COLUMN_CORRECTS)))
                    .build();
            return questions;
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
    public Questions save(Questions entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getQuestionID());
        values.put(COLUMN_NAME, entity.getQuestionName());
        values.put(COLUMN_QUESTIONS, entity.getQuestions());
        values.put(COLUMN_CORRECTS, entity.getCorrects());
        Long id = db.insertOrThrow(TABLE_NAME, null, values);
        Questions insertedEntity = new Questions.Builder()
                .copy(entity)
                .questionID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Questions update(Questions entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getQuestionID());
        values.put(COLUMN_NAME, entity.getQuestionName());
        values.put(COLUMN_QUESTIONS, entity.getQuestions());
        values.put(COLUMN_CORRECTS, entity.getCorrects());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getQuestionID())}
        );
        return entity;
    }

    @Override
    public Questions delete(Questions entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getQuestionID())});
        return entity;
    }

    @Override
    public Set<Questions> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Questions> questions = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Questions setting = new Questions.Builder()
                        .questionID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .questionName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .questions(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTIONS)))
                        .corrects(cursor.getString(cursor.getColumnIndex(COLUMN_CORRECTS)))
                        .build();
                questions.add(setting);
            } while (cursor.moveToNext());
        }
        return questions;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
