package com.example.dailydairyonline;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfile extends AppCompatActivity {

    EditText txtName, txtEmail, txtAddress, txtBday;
    Button btnSave, btnAbout;
    DatabaseReference ref;
    FirebaseDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtAddress = findViewById(R.id.txtAddress);
        txtBday = findViewById(R.id.txtBday);
        btnSave = findViewById(R.id.button);
        btnAbout = findViewById(R.id.back);


        mDatabase = FirebaseDatabase.getInstance();
        ref = mDatabase.getReference().child("Profile");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString();
                String email = txtEmail.getText().toString();
                String address = txtAddress.getText().toString();
                String birthday = txtBday.getText().toString();

                if(name.isEmpty() || email.isEmpty() || address.isEmpty() || birthday.isEmpty()){
                    Toast.makeText(EditProfile.this, "Please fill this Field!", Toast.LENGTH_SHORT).show();
                }
                else{
                    DatabaseReference mRef=ref;
                    mRef.child("Name").setValue(name);
                    mRef.child("Email").setValue(email);
                    mRef.child("Bday").setValue(birthday);
                    mRef.child("Address").setValue(address).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(EditProfile.this, "Profile Information Save Successfully!", Toast.LENGTH_SHORT).show();
                                txtName.setText("");
                                txtEmail.setText("");
                                txtAddress.setText("");
                                txtBday.setText("");

                            }
                            else{
                                Toast.makeText(EditProfile.this, "Profile Information Failed to Change!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    btnAbout.setOnClickListener(view ->{
    Intent intent = new Intent(EditProfile.this, Home.class);
    startActivity(intent);});

    }
}