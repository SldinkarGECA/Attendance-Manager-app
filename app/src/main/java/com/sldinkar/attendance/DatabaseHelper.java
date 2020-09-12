package com.sldinkar.attendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public final static String DATBASE_NAME = "new.db";
    public final static String TABLE_NAME = "Students";
    public final static String COL_1 = "ROLL";
    public final static String COL_2 = "NAME";
    public final static String COL_3 = "PRESENT";
    public final static String COL_4 = "ABSENT";
    public final static String COL_5 = "CLASS";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATBASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(ROLL INTEGER," +
                " NAME TEXT," +
                "PRESENT INTEGER, " +
                "ABSENT INTEGER, " +
                " CLASS TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertData(int roll, String name, String className) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int zero = 0;
        contentValues.put(COL_1, roll);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, zero);
        contentValues.put(COL_4, zero);
        contentValues.put(COL_5, className);


        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }


    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }

    public Cursor getClasses() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT(CLASS) FROM " + TABLE_NAME, null);
        return cursor;
    }

    public Cursor getAttendance() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT ROLL,NAME,CLASS FROM " + TABLE_NAME + " WHERE PRESENT=0 GROUP BY CLASS;", null);
        return cursor;
    }

    Cursor readAllData() {
        String query = "SELECT ROLL ,NAME ,PRESENT, ABSENT, CLASS FROM " + TABLE_NAME + " Order by CLASS";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) cursor = db.rawQuery(query, null);
        return cursor;
    }

    Cursor readStudentNames(String ClassName) {
        String classs = ClassName;
        String query = "SELECT ROLL,NAME FROM " + TABLE_NAME + " S WHERE S.CLASS='" + classs + "' ;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) cursor = db.rawQuery(query, null);
        return cursor;
    }

    public void markPresent(String className, int roll) {
        String query = "UPDATE " + TABLE_NAME + " SET PRESENT=PRESENT+1 WHERE CLASS='" + className + "' AND ROLL=" + roll;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);

    }

    public void markPresentUndo(String className, int roll) {
        String query = "UPDATE " + TABLE_NAME + " SET PRESENT=PRESENT-1 WHERE CLASS='" + className + "' AND ROLL=" + roll;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);

    }

    public void markAbsent(String className, int roll) {
        String query = "UPDATE " + TABLE_NAME + " SET ABSENT=ABSENT+1 WHERE CLASS='" + className + "' AND ROLL=" + roll;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);

    }

    public void markAbsentUndo(String className, int roll) {
        String query = "UPDATE " + TABLE_NAME + " SET ABSENT=ABSENT-1 WHERE CLASS='" + className + "' AND ROLL=" + roll;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);

    }

    double getAttendancePercentage(String className, int roll) {
        String classs = className;
        String query = "SELECT (S.PRESENT*1.0)/((S.PRESENT+S.ABSENT)*1.0) FROM " + TABLE_NAME + " S WHERE S.CLASS='" + className + "' AND S.ROLL=" + roll;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        return Double.parseDouble(cursor.getString(0));
    }

    public void Delete() {
        String query = "DELETE FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);

    }

    public void DeleteStudent(int roll, String name, String className) {
        String query = "DELETE FROM " + TABLE_NAME
                + " WHERE ROLL= " + roll + " and  NAME= '" + name +
                "' AND CLASS= '" + className + "' ;";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }

    public boolean CheckStudent(int roll, String name, String className) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE ROLL= " + roll + " and  NAME= '" + name +
                "' AND CLASS= '" + className + "' ;", null);
        if (cursor.getCount() == 0) {
            return false;
        } else return true;
    }

}
