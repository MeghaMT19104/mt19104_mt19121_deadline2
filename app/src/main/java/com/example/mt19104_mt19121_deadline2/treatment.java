package com.example.mt19104_mt19121_deadline2;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class treatment {
    private ArrayList<String> med_list = new ArrayList<String>();
    private ArrayList<String> price = new ArrayList<String>();
    private ArrayList<String> test = new ArrayList<String>();
    private ArrayList<String> t_price = new ArrayList<String>();
    private ArrayList<String> selected_med = new ArrayList<String>();
    private ArrayList<String> selected_test = new ArrayList<String>();
    private int ward_fee;
    public void setWard_fee(int w){
        this.ward_fee = w;
    }

    public void setPrice(ArrayList<String> price) {
        this.price = price;
    }

    public void setT_price(ArrayList<String> t_price) {
        this.t_price = t_price;
    }
    public void get_ward_fee(final String w){

        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("ward");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot da: dataSnapshot.getChildren()){
                    if(w.equals(da.getKey())){
                        setWard_fee(Integer.parseInt(da.child("costperday").getValue().toString()));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public int getWard_fee(String w){
        get_ward_fee(w);
        return this.ward_fee;
    }
    public ArrayList<String> getTest() {
        final ArrayList<String> temp = new ArrayList<>();
        final ArrayList<String> temp2 = new ArrayList<>();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("tests");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot da : dataSnapshot.getChildren()) {
                    String med_name = i + " : " + da.getKey() + " : " + da.child("cost").getValue().toString();
                    temp.add(med_name);
                    temp2.add(da.child("cost").getValue().toString());
                    i++;
                }
                setT_price(temp2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return temp;

    }

    public ArrayList<String> getT_price() {
//        final ArrayList<String > temp = new ArrayList<>();
//        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("tests");
//        db.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot da : dataSnapshot.getChildren()){
//                    String med_price =  dataSnapshot.child(da.getKey()).child("cost").getValue().toString();
//                    temp.add(med_price);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        return this.t_price;
    }
    public ArrayList<String> getMed_list () {
        final ArrayList<String> temp = new ArrayList<>();
        final ArrayList<String> temp2 = new ArrayList<>();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("medicine");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot da : dataSnapshot.getChildren()) {

                    String med_name = i + " : " + da.getKey() + " : " + da.child("cost").getValue().toString();
                    temp.add(med_name);
                    temp2.add(da.child("cost").getValue().toString());
                    i++;
                }
                setPrice(temp2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return temp;
    }
    public ArrayList<String> getPrice (){
//        final ArrayList<String > temp = new ArrayList<>();
//        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("medicine");
//        db.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot da : dataSnapshot.getChildren()){
//                    String med_price =  dataSnapshot.child(da.getKey()).child("cost").getValue().toString();
//                    temp.add(med_price);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        return this.price;
    }

}
