package com.example.programming;

import static android.app.ProgressDialog.show;
import static android.provider.ContactsContract.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.net.PasswordAuthentication;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername, edEmail,edPassword,edConfirm;
    Button btn;
    TextView tv;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        edUsername = findViewById(R.id.editTextRegUsername);
        edPassword = findViewById(R.id.editTextRegPassword);
         edEmail = findViewById(R.id.editTextRegEmail);
        edConfirm = findViewById(R.id.editTextRegConfirmPassword);
        btn = findViewById(R.id.buttonRegister);
        tv = findViewById(R.id.textViewExistingUser);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

            }
        });
          btn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  String Username = edUsername.getText().toString();
                  String Password = edPassword.getText().toString();
                  String Email = edEmail.getText().toString();
                  String Confirm = edConfirm.getText().toString();
                  DataBase db = new DataBase(getApplicationContext(),"Healthcare",null,1);
               if(Username.length()==0 || Email.length()==0 || Password.length()==0 || Confirm.length()==0) {
                   Toast.makeText(getApplicationContext(), "Please Fill All Detail", Toast.LENGTH_SHORT).show();

               }
                  else {
                   if (Password.compareTo(Confirm) == 0) {
                       if(isValid(Password)){
                           db.register(Username,Email,Password);
                           Toast.makeText(getApplicationContext(), "record Inserted", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                   }
                   else {
                       Toast.makeText(RegisterActivity.this, "Password must contain at least characters, having letter ,digit and special symbol ", Toast.LENGTH_SHORT).show();
                   }
               }else{
                       Toast.makeText(RegisterActivity.this, "Password and Confirm didn't match ", Toast.LENGTH_SHORT).show();
                   }
               }
              }


          });
     }
    @SuppressLint("SuspiciousIndentation")
    public static boolean isValid(String Passwordhere) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (Passwordhere.length() < 8) {
            return false;
        } else {
            for (int p = 0; p < Passwordhere.length(); p++) {
                if (Character.isLetter(Passwordhere.charAt(p))) {
                    f1 = 1;
                }
            }
            for (int r = 0; r < Passwordhere.length(); r++) {
                if (Character.isLetter(Passwordhere.charAt(r))) {
                    f2 = 1;
                }
            }
            for (int s = 0; s < Passwordhere.length(); s++) {
                if (Character.isLetter(Passwordhere.charAt(s))) {
                    f3 = 1;
                }
            }
            if (f1 == 1 && f2 == 1 && f3 == 1)
                return true;
                return false;
            }

        }

}
