package com.example.mt19104_mt19121_deadline2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void logindoctor(View view) {
      Intent intent = new Intent(this,doctorlogin.class);
      startActivity(intent);
    }

    public void loginreceptionist(View view) {
        Intent intent = new Intent(this,receptionlogin.class);
        startActivity(intent);
    }

    public void loginpatient(View view) {
        Intent intent = new Intent(this,patientlogin.class);
        startActivity(intent);
    }

    public void loginadmin(View view) {
        Intent intent = new Intent(this,adminlogin.class);
        startActivity(intent);
    }
}
