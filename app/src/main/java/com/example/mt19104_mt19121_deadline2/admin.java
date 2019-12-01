package com.example.mt19104_mt19121_deadline2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class admin extends AppCompatActivity {
    View Dialog_reg;
    final Context context = this;
    Spinner  sp;
    Button b1,b2;
    AlertDialog alertDialog;
    final String[] names = new String[25];
    final String[] names1 = new String[25];
    Context c=this;
    private DatabaseReference mDatabase,mDatabase1;
    FirebaseDatabase fd;
    boolean is_Reg_active = false,is_for_active = false,is_cpass_active;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fd= FirebaseDatabase.getInstance();
        mDatabase1=fd.getReference("users").child("doctor");
mDatabase=fd.getReference("users").child("patient");

        setContentView(R.layout.activity_admin);
        b1=(Button)findViewById(R.id.viewdoc);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 sp = findViewById(R.id.doclistspinner);

                ValueEventListener vl = mDatabase1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int i = 0;
                        for(DataSnapshot da: dataSnapshot.getChildren()){
                            names[i] =da.child("username").getValue().toString();
                              i++;
                        }

                        ArrayAdapter<String> spinnerE = new ArrayAdapter<String>(c, R.layout.spinner, names);
                        sp.setAdapter(spinnerE);
                        spinnerE.setDropDownViewResource(R.layout.spinner);
                        sp.setVisibility(View.VISIBLE);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
        b2=(Button)findViewById(R.id.viewpat);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp = findViewById(R.id.patientlistspinner);

                ValueEventListener vl = mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int i = 0;
                        for(DataSnapshot da: dataSnapshot.getChildren()){
                            names1[i] =da.child("username").getValue().toString();
                            i++;
                        }

                        ArrayAdapter<String> spinnerF = new ArrayAdapter<String>(c, R.layout.spinner, names1);
                        sp.setAdapter(spinnerF);
                        spinnerF.setDropDownViewResource(R.layout.spinner);
                        sp.setVisibility(View.VISIBLE);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
    }

    public void registerdoc(View view) {
     Intent i=new Intent(admin.this,registerdoctor.class);
     startActivity(i);

    }


    public void registerpatient(View view) {


    }

    public void logout(View view) {
        Intent i=new Intent(admin.this,MainActivity.class);
        startActivity(i);
    }

    public void viewpatlist(View view) {
    }
}

