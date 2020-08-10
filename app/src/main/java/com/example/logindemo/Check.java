package com.example.logindemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Check extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check);
    }

    //추출값 g->N으로 바꾸는것 필요
    //막대그래프

    //저장
    public void saveData(View v) {
        Toast.makeText(this, "save", Toast.LENGTH_SHORT).show();
    }

    //로그아웃
    public void logout(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent itBack = new Intent(this, MainActivity.class);
        startActivity(itBack);
        finish();
    }
}
