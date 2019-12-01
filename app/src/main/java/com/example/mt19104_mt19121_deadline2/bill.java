package com.example.mt19104_mt19121_deadline2;



import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class bill extends treatment {
    private String pat_id;
    private ArrayList<String> selected_med;
    private ArrayList<String> selected_test;
    public bill(){

    }



    private String med_price;
    private String test_price;
    public String getMed_price(){
        final String[] p = new String[1];
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("medicine");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int temp=0;
                for (DataSnapshot da: dataSnapshot.getChildren()){
                    if(selected_med.contains(da.getKey())){
                        temp = temp+Integer.parseInt(da.child("cost").getValue().toString());
                    }

                }
                p[0] = Integer.toString(temp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return p[0];
    }
    public String getTest_price(){
        final String[] p = new String[1];
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("tests");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int temp=0;
                for (DataSnapshot da: dataSnapshot.getChildren()){
                    if(selected_test.contains(da.getKey())){
                        temp = temp+Integer.parseInt(da.child("cost").getValue().toString());
                    }

                }
                p[0] = Integer.toString(temp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return p[0];
    }


}

