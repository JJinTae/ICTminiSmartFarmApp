package com.example.smartfarm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class DbOpenHelper {
    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(DataBases.CreateDB._CREATE0);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+DataBases.CreateDB._TABLENAME0);
            onCreate(db);
        }
    }

    public DbOpenHelper(Context context){
        this.mCtx = context;
    }

    public DbOpenHelper open() throws SQLException{
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void create(){
        mDBHelper.onCreate(mDB);
    }

    // 데이터 베이스 닫기
    public void close(){
        mDB.close();
    }

    // 데이터 삽입
    public long insertColumn(String time, String grow, String wv , String nv, String light){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.TIME, time);
        values.put(DataBases.CreateDB.GROW, grow);
        values.put(DataBases.CreateDB.WV, wv);
        values.put(DataBases.CreateDB.NV, nv);
        values.put(DataBases.CreateDB.LIGHT, light);
        return mDB.insert(DataBases.CreateDB._TABLENAME0, null, values);
    }

    // 데이터 선택
    public Cursor selectColumns(){
        return mDB.query(DataBases.CreateDB._TABLENAME0, null, null, null, null, null, null);
    }

    // 데이터 갱신
    public boolean updateColumn(long id, String time, String grow, String wv , String nv, String light){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.TIME, time);
        values.put(DataBases.CreateDB.GROW, grow);
        values.put(DataBases.CreateDB.WV, wv);
        values.put(DataBases.CreateDB.NV, nv);
        values.put(DataBases.CreateDB.LIGHT, light);
        return mDB.update(DataBases.CreateDB._TABLENAME0, values, "_id=" + id, null) > 0;
    }

    // Delete All
    public void deleteAllColumns() {
        mDB.delete(DataBases.CreateDB._TABLENAME0, null, null);
    }

    // Delete Column
    public boolean deleteColumn(long id){
        return mDB.delete(DataBases.CreateDB._TABLENAME0, "_id="+id, null) > 0;
    }

    // sort by column
    public Cursor sortColumn(String sort){
        Cursor c = mDB.rawQuery( "SELECT * FROM usertable ORDER BY " + sort + ";", null);
        return c;
    }
}