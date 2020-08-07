package com.example.logindemo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
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
            //DB 저장하기;
            contentValues.put(DatabaseOpenHelper._ID, str_id);
            contentValues.put(DatabaseOpenHelper.PW, str_pw);
            contentValues.put(DatabaseOpenHelper.NAME, str_name);
            contentValues.put(DatabaseOpenHelper.AGE, str_age);
            contentValues.put(DatabaseOpenHelper.GENDER, str_gender);

            SQLiteDatabase db = DatabaseOpenHelper.getInstance(this).getWritableDatabase();
            long newRowID = db.insert(DatabaseOpenHelper.tableName, null, contentValues);

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
}
