package com.example.mt19104_mt19121_deadline2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

public class admin extends AppCompatActivity {
    View Dialog_reg;
    final Context context = this;
    AlertDialog alertDialog;
    boolean is_Reg_active = false,is_for_active = false,is_cpass_active;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void registerdoc(View view) {
     Intent i=new Intent(admin.this,registerdoctor.class);
     startActivity(i);

    }


    public void registerpatient(View view) {


    }
}

