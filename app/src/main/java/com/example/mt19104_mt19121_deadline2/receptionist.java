package com.example.mt19104_mt19121_deadline2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProvider;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class receptionist extends AppCompatActivity {
    EditText e1,e2,e3;
    Button b1,b2,b3;
    Spinner  sp;
    FirebaseDatabase fd;
    Context c=this;
    private DatabaseReference mDatabase,mDatabase1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fd=FirebaseDatabase.getInstance();
        mDatabase1=fd.getReference("users").child("doctor");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptionist);
        e1= (EditText) findViewById(R.id.symptom);
        e2= (EditText) findViewById(R.id.pname);
        e3= (EditText) findViewById(R.id.age);
        sp= findViewById(R.id.Departmentspinner);
        String[] session = new String[]{
                "gynae",
                "ortho",
                "neuro",
               "general"
        };
        ArrayAdapter<String> spinnerB = new ArrayAdapter<String>(this, R.layout.spinner, session);
        spinnerB.setDropDownViewResource(R.layout.spinner);
        sp.setAdapter(spinnerB);
       b1=(Button)findViewById(R.id.getdoc);
       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               final String a= sp.getSelectedItem().toString();
               final String[] names = new String[10];

               mDatabase1.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       int i=0;
                       for(DataSnapshot da:dataSnapshot.getChildren())
                       {
                           if(da.child("departmeñt").getValue().toString().equals(a))
                           {
                             names[i]= da.child("username").getValue().toString();
                             i++;
                           }
                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });
               Spinner d = findViewById(R.id.doctorspinner);
               ArrayAdapter<String> spinnerC = new ArrayAdapter<String>(c, R.layout.spinner, names);
               spinnerC.setDropDownViewResource(R.layout.spinner);
               d.setAdapter(spinnerC);
               d.setVisibility(View.VISIBLE);
           }
       });

    }
}
