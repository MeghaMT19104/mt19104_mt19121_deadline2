package com.example.mt19104_mt19121_deadline2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
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

    public void registerdoctor(View view) {
        LayoutInflater li = LayoutInflater.from(context);
        final View promptsView = li.inflate(R.layout.registerdoctor_dialog, null);
        Dialog_reg = promptsView;
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(admin.this);
        builder.setTitle("Register");
        builder.setCancelable(false);
        builder.setView(promptsView);
        builder
                .setPositiveButton(
                        "Submit",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {
                                is_Reg_active=false;
                                //Save data
                            }
                        });

        // Set the Negative button with No name
        // OnClickListener method is use
        // of DialogInterface interface.
        builder
                .setNegativeButton(
                        "Cancel",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {

                                // If user click no
                                // then registerdoctor_dialog box is canceled.
                                is_Reg_active = false;
                                dialog.cancel();
                            }
                        });
        alertDialog = builder.create();
        alertDialog.show();
        Log.d("Value", "Re Bhaiya!!  Dialog bnayaa bada maza aya");
        is_Reg_active = alertDialog.isShowing();

        Log.d("Value", "acha chalta hu duao m yaad rakhna......I hate u");
    }


    public void registerpatient(View view) {
        LayoutInflater li = LayoutInflater.from(context);
        final View promptsView = li.inflate(R.layout.registerpatient_dialog, null);
        Dialog_reg = promptsView;
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(admin.this);
        builder.setTitle("Register");
        builder.setCancelable(false);
        builder.setView(promptsView);
        builder
                .setPositiveButton(
                        "Submit",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {
                                is_Reg_active=false;
                                //Save data
                            }
                        });

        // Set the Negative button with No name
        // OnClickListener method is use
        // of DialogInterface interface.
        builder
                .setNegativeButton(
                        "Cancel",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {

                                // If user click no
                                // then registerdoctor_dialog box is canceled.
                                is_Reg_active = false;
                                dialog.cancel();
                            }
                        });
        alertDialog = builder.create();
        alertDialog.show();

        is_Reg_active = alertDialog.isShowing();


    }
}

