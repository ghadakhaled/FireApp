package com.example.ghada.fireapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.PrivateKey;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    FirebaseDatabase database2;
    EditText name,email,password,phone,username;
    Button insert;
    DatabaseReference  myRef2;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
       final ListView listView = findViewById(R.id.listtest);
        database = FirebaseDatabase.getInstance();
        myRef = database.getInstance().getReference();
       final ArrayList<String> arrayList = new ArrayList<>();
      final   ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,arrayList);
        String[] x =new String[]{"z","y"};
        for (String c :x)
        {
            arrayList.add(c);
        }
        listView.setAdapter(stringArrayAdapter);
*/
        name = findViewById(R.id.Name);
        email = findViewById(R.id.Email);
        password=findViewById(R.id.password);
        username=findViewById(R.id.username);
        phone =findViewById(R.id.Phone);
        insert=findViewById(R.id.insert);
        database2 = FirebaseDatabase.getInstance();
        //DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("");//anthor-Way
        myRef2 = database2.getInstance().getReference("User");
        user = new User();

   }

   private void getvalue()
   {
       user.setName(name.getText().toString());
       user.setEmail(email.getText().toString());
       user.setPhone(phone.getText().toString());
       user.setUsername(username.getText().toString());
       user.setPassword(password.getText().toString());
   }



    public void Inserdata(View view)
    {
        myRef2.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {   getvalue();
                String x="User"+phone.getText().toString();
                myRef2.child(x).setValue(user);
                Toast.makeText(MainActivity.this, "Database insert.....", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Log.w("", "onCancelled", databaseError.toException());
            }
        });

    }
}
