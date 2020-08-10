package com.example.logindemo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity {

    private ArrayList<Login> loginArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        loginArrayList = new LoginOpenHelper(this).loadLgoinList();
    }

    public void saveLogin(View v) {
        //이름값 변수에 저장
        EditText edt_name = (EditText) findViewById(R.id.edit_name);
        String str_name = edt_name.getText().toString();

        //나이값 변수에 저장
        EditText edt_age = (EditText) findViewById(R.id.age);
        String str_age = edt_age.getText().toString();

        //성별값 변수에 저장
        EditText edt_gender = (EditText) findViewById(R.id.gender);
        String str_gender = edt_gender.getText().toString();

        //성별값 변수에 저장
        EditText edt_id = (EditText) findViewById(R.id.edit_id);
        String str_id = edt_id.getText().toString();

        //성별값 변수에 저장
        EditText edt_pw = (EditText) findViewById(R.id.edit_pw);
        String str_pw = edt_pw.getText().toString();

        ContentValues contentValues = new ContentValues();

        //빈 곳이 있으면 작성하게함, 다채워지면 DB에 저
        if (str_id.length() == 0) {
            Toast.makeText(this, "id 입력해주세요", Toast.LENGTH_SHORT).show();
        } else if (str_pw.length() == 0) {
            Toast.makeText(this, "pw 입력해주세요", Toast.LENGTH_SHORT).show();
        } else if (str_name.length() == 0) {
            Toast.makeText(this, "name 입력해주세요", Toast.LENGTH_SHORT).show();
        } else if (str_age.length() == 0) {
            Toast.makeText(this, "age 입력해주세요", Toast.LENGTH_SHORT).show();
        } else if (str_gender.length() == 0) {
            Toast.makeText(this, "gender 입력해주세요", Toast.LENGTH_SHORT).show();
        } else {
            //아이디 중복체
            if(chekId(str_id)) {
                Toast.makeText(this, "같은 ID가 있습니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            //DB 저장하기;
            contentValues.put(LoginOpenHelper._ID, str_id);
            contentValues.put(LoginOpenHelper.PW, str_pw);
            contentValues.put(LoginOpenHelper.NAME, str_name);
            contentValues.put(LoginOpenHelper.AGE, str_age);
            contentValues.put(LoginOpenHelper.GENDER, str_gender);

            SQLiteDatabase db = LoginOpenHelper.getInstance(this).getWritableDatabase();
            long newRowID = db.insert(LoginOpenHelper.tableName, null, contentValues);

            if (newRowID == -1) {
                Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }

    }

    public boolean chekId(String id) {
        for(int i = 0; i < loginArrayList.size(); i++) {
            if(id.equals(loginArrayList.get(i).loginId)){
               return true;
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        Intent itBack = new Intent(this, MainActivity.class);
        startActivity(itBack);
        finish();
    }
}
