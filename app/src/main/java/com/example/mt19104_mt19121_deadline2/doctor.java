package com.example.mt19104_mt19121_deadline2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class doctor extends AppCompatActivity {
    Spinner sp;
    ListView lv;
    bill b = new bill();
    EditText ed;
    Context c = this;
    final String[] names = new String[20];
    final String[] p_ids = new String[20];
    final String[] pat_id = new String[1];
    String use = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
    DatabaseReference fd = FirebaseDatabase.getInstance().getReference().child("users").child("doctor").child(use);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        fd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                if (dataSnapshot.child("Assigned_Patients").getChildrenCount()<=0){
                    TextView t = findViewById(R.id.no_pat);
                    t.setVisibility(View.VISIBLE);
                    return;
                }
                for(DataSnapshot d  : dataSnapshot.child("Assigned_Patients").getChildren()){
                    names[i]=d.getValue().toString();
                    String temp =d.getKey();
                    String[] w = temp.split("_");
                    temp = w[1]+"_"+w[2];
                    p_ids[i]=temp;
                    i = i+1;
                }
                String[] n = new String[i];
                for(int j=0;j<i;j++){
                    n[j] = names[j];
                }
                sp = findViewById(R.id.patientspinner);
                ArrayAdapter<String> spinnerD = new ArrayAdapter<String>(c, R.layout.spinner, n);
                spinnerD.setDropDownViewResource(R.layout.spinner);
                sp.setAdapter(spinnerD);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Button bt1 = findViewById(R.id.start_treat);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp = findViewById(R.id.patientspinner);
                String pat = sp.getSelectedItem().toString();

                for(int i = 0;i<names.length;i++){
                    if(pat.equals(names[i])){
                        pat_id[0] = p_ids[i];
                        break;
                    }
                }
                final ArrayList<String> med = b.getMed_list();
                final ArrayList<String> test = b.getTest();
                lv = findViewById(R.id.me_list);
                lv.setAdapter(new ArrayAdapter<>(c,android.R.layout.simple_list_item_1,med));
                lv.setVisibility(View.VISIBLE);
                lv = findViewById(R.id.te_list);
                lv.setAdapter(new ArrayAdapter<>(c,android.R.layout.simple_list_item_1,test));
                lv.setVisibility(View.VISIBLE);
                Button bt = findViewById(R.id.pre_med);
                bt.setVisibility(View.VISIBLE);
                TextView T = findViewById(R.id.testsel);
                T.setVisibility(View.VISIBLE);
                T = findViewById(R.id.medsel);
                T.setVisibility(View.VISIBLE);
                ed = findViewById(R.id.e_med);
                ed.setVisibility(View.VISIBLE);
                ed = findViewById(R.id.e_test);
                ed.setVisibility(View.VISIBLE);
                Button b = findViewById(R.id.pre_med);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ed = findViewById(R.id.e_med);
                        String med_names="";
                        String medecines = ed.getText().toString();
                        String[] ind = medecines.split(",");
                        for(int i=0;i<ind.length;i++){
                            if(i == 0){
                                med_names = med.get(Integer.parseInt(ind[i]));

                            }
                            else {
                                med_names = med_names.concat(",");
                                med_names = med_names.concat(med.get(Integer.parseInt(ind[i])));
                            }
                        }
                        FirebaseDatabase.getInstance().getReference().child("users").child("patient").child(pat_id[0]).child("Medicines_refered").setValue(med_names);


                    }
                });
                b = findViewById(R.id.pre_test);
                b.setVisibility(View.VISIBLE);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ed = findViewById(R.id.e_med);
                        String test_names="";
                        String medecines = ed.getText().toString();
                        String[] ind = medecines.split(",");
                        for(int i=0;i<ind.length;i++){
                            if(i == 0){
                                test_names = test.get(Integer.parseInt(ind[i]));

                            }
                            else {
                                test_names = test_names.concat(",");
                                test_names = test_names.concat(test.get(Integer.parseInt(ind[i])));
                            }
                        }
                        FirebaseDatabase.getInstance().getReference().child("users").child("patient").child(pat_id[0]).child("Tests_refered").setValue(test_names);


                    }
                });
            }
        });

    }
}
