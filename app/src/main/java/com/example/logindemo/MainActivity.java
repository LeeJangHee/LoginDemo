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

    LoginOpenHelper helper;
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

        helper = new LoginOpenHelper(MainActivity.this);
        db = helper.getWritableDatabase();
    }

    public void login(View v) {
        //ID값 변수에 저장
        String str_login_id = edt_ID.getText().toString();

        //Password값 변수에 저장
        String str_login_pw = edt_Ps.getText().toString();

        String[] userInfo = {
                LoginOpenHelper._ID,
                LoginOpenHelper.PW,
                LoginOpenHelper.NAME,
                LoginOpenHelper.AGE,
                LoginOpenHelper.GENDER};
        cursor = db.query(LoginOpenHelper.tableName, userInfo,
                null, null, null, null, null);


        //아이디와 비밀번호가 입력 안되어있을 때
        if (str_login_id.length() == 0 || str_login_pw.length() == 0) {
            Toast.makeText(this, "아이디와 패스워드를 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        //TODO: 아이디를 확인
        sql = "select id from" + helper.tableName + "where id = '" + str_login_id + "'";
        cursor = db.rawQuery(sql, null);

        if (cursor.getCount() != 0) {
            //존재하지 않는 아이디
            Toast.makeText(this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT).show();
        } else {
            //존재 한다면
            Log.d("로그인 레코드 확인", cursor.getColumnName(cursor.getPosition()));
        }

        //TODO: 비밀번호 확인


        //로그인 완료 (아직 완성 ㄴ)
        Intent i = new Intent(this, Check.class);
        startActivity(i);
        finish();

    }

    public void signUp(View v) {
        Intent i = new Intent(this, SignUp.class);
        startActivity(i);
    }

}