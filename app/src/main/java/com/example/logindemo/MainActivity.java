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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LoginOpenHelper helper;
    SQLiteDatabase db;

    Cursor cursor;
    private EditText edt_ID;
    private EditText edt_Ps;
    private String sql;
    private ArrayList<Login> loginInfo;

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
        loginInfo = helper.loadLgoinList();

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
        //로그인 하기
        int i = 0;
        for (i = 0; i < loginInfo.size(); i++) {
            //로그인 완료
            if(str_login_id.equals(loginInfo.get(i).loginId) &&
                str_login_pw.equals(loginInfo.get(i).loginPw)) {
                Intent it = new Intent(this, Check.class);
                startActivity(it);
                finish();
                break;
            }
        }

        if(i == loginInfo.size()) {
            Toast.makeText(this, "아이디나 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
        }


    }

    public void signUp(View v) {
        Intent i = new Intent(this, SignUp.class);
        startActivity(i);
    }

}