package com.example.mt19104_mt19121_deadline2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProvider;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class receptionist extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    Button b1,b2,b3;
    Spinner  sp;
    FirebaseDatabase fd;
    private FirebaseAuth mAuth;
    Context c=this;
    private DatabaseReference mDatabase,mDatabase1;
    final String[] names = new String[10];
    final String[] u_ids = new String[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fd=FirebaseDatabase.getInstance();
        mDatabase1=fd.getReference("users").child("doctor");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptionist);
        e1= (EditText) findViewById(R.id.symptom);
        e2= (EditText) findViewById(R.id.pname);
        e3= (EditText) findViewById(R.id.age);
        e4= (EditText) findViewById(R.id.wardedit);
        mAuth = FirebaseAuth.getInstance();
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
                sp = findViewById(R.id.Departmentspinner);
                if(sp.getSelectedItem() == null){
                    Log.d("dekho", "null aa gya");
                }
                else{
                    Log.d("dekho", sp.getSelectedItem().toString());
                }

                final String a= sp.getSelectedItem().toString();


                mDatabase1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int i=0;
                        for(DataSnapshot da:dataSnapshot.getChildren())
                        {
                            Log.d("dekho",da.getKey());
                            if(da.child("department").getValue().toString().equals(a))
                            {
                                names[i]= da.child("username").getValue().toString();
                                u_ids[i] = da.getKey();
                                i++;

                            }
                        }
                        Spinner d = findViewById(R.id.doctorspinner);
                        String[] n = new String[i];
                        for(int j=0;j<i;j++){
                            n[j] = names[j];
                        }
                        ArrayAdapter<String> spinnerC = new ArrayAdapter<String>(c, R.layout.spinner, n);
                        spinnerC.setDropDownViewResource(R.layout.spinner);
                        d.setAdapter(spinnerC);
                        d.setVisibility(View.VISIBLE);
                        TextView t = findViewById(R.id.Selectdoc);
                        t.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        b2 = findViewById(R.id.addpat);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String symp = e1.getText().toString();
                String pn = e2.getText().toString();
                String page = e3.getText().toString();
                String wardtype = e4.getText().toString();
                sp = findViewById(R.id.doctorspinner);
                String doca = sp.getSelectedItem().toString();

                String id = pn.concat("_");
                id = id.concat(page);
                String em = id.concat("@gmail.com");
                String pas = id;
                DatabaseReference fk =  FirebaseDatabase.getInstance().getReference().child("users").child("patient").child(id);
                fk.child("Name").setValue(pn);
                fk.child("Symptoms").setValue(symp);
                fk.child("Age").setValue(page);
                fk.child("ward").setValue(wardtype);
                fk.child("Doctor_assigned").setValue(doca);
                fk.child("Email").setValue(em);
                fk.child("password").setValue(pas);
                for(int i = 0;i<names.length;i++){
                    if(doca.equals(names[i])){
                        FirebaseDatabase.getInstance().getReference().child("users").child("doctor").child(u_ids[i]).child("Assigned_Patients").setValue(pn);
                    }
                }
                mAuth.createUserWithEmailAndPassword(em,pas);
                Toast.makeText(c, "Patient Got Registered", Toast.LENGTH_SHORT).show();
            }
        });
    }
}