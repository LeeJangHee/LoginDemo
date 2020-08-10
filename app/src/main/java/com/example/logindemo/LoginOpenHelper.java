package com.example.logindemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LoginOpenHelper extends SQLiteOpenHelper {
    private static LoginOpenHelper sInstaces;

    public static final String tableName = "Users";
    public static final String _ID = "id";
    public static final String PW = "pw";
    public static final String AGE = "age";
    public static final String GENDER = "gender";
    public static final String NAME = "name";
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Login.db";
    public static final String SQL_CREATE_ENTRIES =
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                    tableName, _ID, PW, NAME, AGE, GENDER);

    public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", tableName);

    public static LoginOpenHelper getInstance(Context context) {
        if (sInstaces == null) {
            sInstaces = new LoginOpenHelper(context);
        }
        return sInstaces;
    }

    public LoginOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public ArrayList<Login> loadLgoinList() {
        ArrayList<Login> loginArrayList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String[] userInfo = {
                LoginOpenHelper._ID,
                LoginOpenHelper.PW,
                LoginOpenHelper.NAME,
                LoginOpenHelper.AGE,
                LoginOpenHelper.GENDER};

        Cursor cursor = db.query(LoginOpenHelper.tableName, userInfo,
                null, null, null, null, null);

        //커서 처음~끝 조회
        while (cursor.moveToNext()) {
            Login login = new Login();
            //객체에 DB 저장
            login.loginId = cursor.getString(cursor.getColumnIndex(_ID));
            login.loginPw = cursor.getString(cursor.getColumnIndex(PW));
            login.loginName = cursor.getString(cursor.getColumnIndex(NAME));
            login.loginAge = cursor.getString(cursor.getColumnIndex(AGE));
            login.loginGender = cursor.getString(cursor.getColumnIndex(GENDER));

            loginArrayList.add(login);
        }

        return loginArrayList;
    }
}
