package com.example.mt19104_mt19121_deadline2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class treatment extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);}
    private ArrayList<String> med_list = new ArrayList<String>();
    private ArrayList<String> price = new ArrayList<String>();
    private ArrayList<String> test = new ArrayList<String>();
    private ArrayList<String> t_price = new ArrayList<String>();
    public ArrayList<String> getTest(){
        final ArrayList<String> temp = new ArrayList<>();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("tests");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot da : dataSnapshot.getChildren()){
                    String med_name =  da.getKey().toString();
                    temp.add(med_name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return temp;

    }
    public ArrayList<String> getT_price(){
        final ArrayList<String > temp = new ArrayList<>();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("tests");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot da : dataSnapshot.getChildren()){
                    String med_price =  dataSnapshot.child(da.getKey()).child("cost").getValue().toString();
                    temp.add(med_price);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return temp;
    }
    public ArrayList<String> getMed_list(){
        final ArrayList<String > temp = new ArrayList<>();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("medicine");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot da : dataSnapshot.getChildren()){
                   String med_name =  da.getKey().toString();
                   temp.add(med_name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return temp;
    }
    public ArrayList<String> getPrice(){
        final ArrayList<String > temp = new ArrayList<>();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("medicine");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot da : dataSnapshot.getChildren()){
                    String med_price =  dataSnapshot.child(da.getKey()).child("cost").getValue().toString();
                    temp.add(med_price);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return temp;
    }


}
class doctor extends treatment {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

    }


}

