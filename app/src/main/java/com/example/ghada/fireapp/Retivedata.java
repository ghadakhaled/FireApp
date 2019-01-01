package com.example.ghada.fireapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Retivedata extends AppCompatActivity {
    ListView listv;
    ArrayList<String>arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retivedata);
        listv =findViewById(R.id.list);
        arrayList=new ArrayList<>();
        DatabaseReference UserRef = FirebaseDatabase.getInstance().getReference("User");
        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot UserSnapshot: dataSnapshot.getChildren())
                {
                    Toast.makeText(Retivedata.this,UserSnapshot.child("name").getValue(String.class),Toast.LENGTH_SHORT).show();
                    arrayList.add(UserSnapshot.child("name").getValue(String.class));
                }
                final ArrayAdapter adapter = new ArrayAdapter(Retivedata.this,
                        android.R.layout.simple_list_item_1, arrayList);
                listv.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
}
