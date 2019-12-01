package com.example.mt19104_mt19121_deadline2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class patient extends AppCompatActivity {
    FirebaseAuth mAuth;
    bill b = new bill();
    TextView t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        mAuth = FirebaseAuth.getInstance();
        t1 = findViewById(R.id.medp);
        t2 = findViewById(R.id.test_p);
        String temp = mAuth.getCurrentUser().getEmail();
        String[] temp2 = temp.split("@");
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("users").child("patient").child(temp2[0]);
        ValueEventListener vl = db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ward = dataSnapshot.child("ward").getValue().toString();
                int w_fee = Integer.parseInt(ward.split(" : ", 3)[1]);
                String stay = dataSnapshot.child("Num_days_stay").getValue().toString();
                TextView to = findViewById(R.id.stay);
                to.setText(stay);
                w_fee = w_fee*Integer.parseInt(stay);

                String doc_assign = dataSnapshot.child("Doctor_assigned").getValue().toString();
                TextView tr = findViewById(R.id.docassigned);

                tr.setText(doc_assign);
                tr = findViewById(R.id.ward_f);
                tr.setText(Integer.toString(w_fee));
                String[] temp1 = dataSnapshot.child("Medicines_refered").getValue().toString().split(",",5);
                String[] med_ind = new String[temp1.length];
                int t = 0;
                for(int i=0;i<temp1.length;i++){
                    String[] temp2 = temp1[i].split(" : ", 5);
                    med_ind[i] = temp2[1];
                    t = t+Integer.parseInt(temp2[2]);
                    if(i == temp1.length-1){
                        // String r = b.getMed_price(med_ind);
                        t1.setText(Integer.toString(t));
                    }

                }
                String md = "";
                for(int i = 0;i<med_ind.length;i++){
                    if(i==0){
                        md = md+med_ind[i];

                    }
                    else{
                        md = md+",";
                        md = md+med_ind[i];
                    }
                }
                tr = findViewById(R.id.medassigned);
                tr.setText(md);
                String te="";
                temp1 = dataSnapshot.child("Tests_refered").getValue().toString().split(",",5);
                t = 0;
                for(int i=0;i<temp1.length;i++){
                    String[] temp2 = temp1[i].split(" : ", 5);
                    t = t+Integer.parseInt(temp2[2]);
                    if(i == 0){
                        te=temp2[1];

                    }
                    else{
                        te = te+",";
                        te = te+temp2[1];
                    }
                    if(i == temp1.length-1){
                        t2.setText(Integer.toString(t));
                    }

                }
                tr = findViewById(R.id.testassigned);
                tr.setText(te);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void generate(View view) {
        int total=0;
        t1 = findViewById(R.id.test_p);
        total = Integer.parseInt(t1.getText().toString())+total;
        t1 = findViewById(R.id.ward_f);
        total = total+Integer.parseInt(t1.getText().toString());
        t1 = findViewById(R.id.medp);
        total = total+Integer.parseInt(t1.getText().toString());
        t1 = findViewById(R.id.total);
        t1.setVisibility(View.VISIBLE);
        t1.setText(Integer.toString(total));
        t1 = findViewById(R.id.tm);
        t1.setVisibility(View.VISIBLE);

    }
}
