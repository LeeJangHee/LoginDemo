package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseOpenHelper helper;
    SQLiteDatabase db;

    Cursor cursor;
    private EditText edt_ID;
    private EditText edt_Ps;
    private String sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        edt_ID = (EditText) findViewById(R.id.login_id);
        edt_Ps = (EditText) findViewById(R.id.login_pw);

        //버튼 변수 선언
        Button signupBtn = (Button) findViewById(R.id.SignupBtn);
        Button loginBtn = (Button) findViewById(R.id.LoginBtn);

        helper = new DatabaseOpenHelper(MainActivity.this);
        db = helper.getWritableDatabase();
    }

    public void login(View v) {
        //ID값 변수에 저장
        String str_login_id = edt_ID.getText().toString();

        //Password값 변수에 저장
        String str_login_pw = edt_Ps.getText().toString();

        //아이디와 비밀번호가 입력 안되어있을 때
        if (str_login_id.length() == 0 || str_login_pw.length() == 0) {
            Toast.makeText(this, "아이디와 패스워드를 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }

//        //아이디를 찾는 부분
//        sql = "select id from" + helper.tableName + "where id = '" + str_login_id + "'";
//        cursor = db.rawQuery(sql, null);
//
//        if (cursor.getCount() != 0) {
//            //존재하지 않는 아이디
//            Toast.makeText(this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT).show();
//        }


        //로그인 완료 (아직 완성 ㄴ)
        Intent i = new Intent(this, Check.class);
        startActivity(i);
        finish();

    }

    public void signUp(View v) {
        Intent i = new Intent(this, SignUp.class);
        startActivity(i);
    }

//    아이디를 검색해서 거기에 맞는 비밀번호를 찾아야함
//    void DBSearch(String id) {
//        cursor = null;
//
//        try {
//            cursor = db.query(DatabaseOpenHelper.tableName,
//                    null, "AGE" + " < ?",
//                    new String[]{age.toString()}, null, null, "NAME");
//
//            if (cursor != null) {
//                while (cursor.moveToNext()) {
//                    String id = cursor.getString(cursor.getColumnIndex("ID"));
//                    String name = cursor.getString(cursor.getColumnIndex("NAME"));
//                    String age2 = cursor.getString(cursor.getColumnIndex("AGE"));
//                    String phone = cursor.getString(cursor.getColumnIndex("PHONE"));
//                }
//            }
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//        }
//    }
}