package com.example.mt19104_mt19121_deadline2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class doctor extends AppCompatActivity {
    Spinner sp;
    ListView lv;
    bill b = new bill();
    EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        Spinner sp = findViewById(R.id.medlist);
        ArrayList<String> med = b.getMed_list();
        ArrayList<String> test = b.getTest();
        ArrayAdapter<String> spinnerB = new ArrayAdapter<String>(this, R.layout.spinner, med);
        spinnerB.setDropDownViewResource(R.layout.spinner);
        lv = findViewById(R.id.me_list);
        lv.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,med));
        lv.setVisibility(View.VISIBLE);
        sp.setAdapter(spinnerB);
        sp.setVisibility(View.VISIBLE);
        sp = findViewById(R.id.testlist);
        ArrayAdapter<String> spinnerC = new ArrayAdapter<String>(this, R.layout.spinner, test);
        spinnerB.setDropDownViewResource(R.layout.spinner);
        sp.setAdapter(spinnerC);
        lv = findViewById(R.id.te_list);
        lv.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,test));
        lv.setVisibility(View.VISIBLE);
        sp.setVisibility(View.VISIBLE);
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
                FirebaseDatabase.getInstance().getReference().child("patient");
            }
        });
    }
}
