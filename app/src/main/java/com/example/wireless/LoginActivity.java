package com.example.wireless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.akexorcist.localizationactivity.ui.LocalizationActivity;
// for students to login to the app

public class LoginActivity extends LocalizationActivity {

    private EditText email;

    private EditText password;

    private FirebaseAuth mAuth;

    private FirebaseUser currentUser;

    private Button button;


    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.LOGIN));

        email = (EditText)findViewById(R.id.login_email_input);

        password = (EditText)findViewById(R.id.login_password_input);

        mAuth = FirebaseAuth.getInstance();

        currentUser = mAuth.getCurrentUser();

        button = (Button)findViewById(R.id.login);

        button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                if (v == button){

                    LoginUser();

                }

            }

        });

    }
    // when click on login button, it will let students to input email and password that already registered
    public void LoginUser(){

        String Email = email.getText().toString().trim();

        String Password = password.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(Email, Password)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            currentUser = mAuth.getCurrentUser();

                            finish();

                            startActivity(new Intent(getApplicationContext(),

                                    Firstpage.class));

                        }else {

                            Toast.makeText(LoginActivity.this, "couldn't login",

                                    Toast.LENGTH_SHORT).show();

                        }

                    }

                });

    }

}