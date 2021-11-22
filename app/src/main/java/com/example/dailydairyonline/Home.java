package com.example.dailydairyonline;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {

    TextView  textName;
    TextView  textEmail;
    TextView  textAddress;
    TextView  textBday;

    DatabaseReference ref;
    FirebaseAuth mAuth;

    FloatingActionButton fabEdit;
    FloatingActionButton fabFeed;

    Button btnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textName = findViewById(R.id.viewName);
        textEmail = findViewById(R.id.viewEmail);
        textAddress =  findViewById(R.id.viewAddress);
        textBday = findViewById(R.id.viewBday);


        ref = FirebaseDatabase.getInstance().getReference("Profile");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("Name").getValue().toString();
                String email = snapshot.child("Email").getValue().toString();
                String address = snapshot.child("Address").getValue().toString();
                String bday = snapshot.child("Bday").getValue().toString();

                textName.setText(name);
                textAddress.setText(address);
                textEmail.setText(email);
                textBday.setText(bday);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        fabFeed = findViewById(R.id.flotingFeed);
        mAuth = FirebaseAuth.getInstance();

        fabFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, FeedActivity.class);
                startActivity(intent);
            }
        });

        fabEdit = findViewById(R.id.flotingEdit);

        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, EditProfile.class);
                startActivity(intent);
            }
        });

        btnLogout = findViewById(R.id.btnSignout);
        mAuth = FirebaseAuth.getInstance();

        btnLogout.setOnClickListener(view ->{
            mAuth.signOut();
            Toast.makeText(Home.this, "Logged Out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Home.this, MainActivity.class));
        });

    }
    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(Home.this, MainActivity.class));

        }
    }

}
