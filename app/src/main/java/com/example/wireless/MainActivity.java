package com.example.wireless;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;
import com.akexorcist.localizationactivity.ui.LocalizationActivity;

// Allow students to select between login and register
public class MainActivity extends LocalizationActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private EditText password;
    private EditText email;
    private Button button_register;
    private Button button_login;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.Regis));
        email = (EditText) findViewById(R.id.signup_email_input);
        password =(EditText) findViewById(R.id.signup_password_input);
        button_register = (Button)findViewById(R.id.button_register);
        button_login = (Button)findViewById(R.id.button_login);
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.btn_TH).setOnClickListener(this);
        findViewById(R.id.btn_EN).setOnClickListener(this);

        button_register.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                if (v == button_register){
                    RegisterUser();
                }
            }

        });

        button_login.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                if (v == button_login){
                    startActivity(new Intent(getApplicationContext(),
                            LoginActivity.class));

                }
            }

        });
    }
    // students can register by typing email and password
    public void RegisterUser(){

        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        if (TextUtils.isEmpty(Email)){
            Toast.makeText(this, "A Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Password)){
            Toast.makeText(this, "A Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        // when input correct email and password, go to firstpage, if not try again
        mAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        try {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "successful",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(getApplicationContext(), Firstpage.class));
                            }else{
                                Toast.makeText(MainActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                });
    }
    //Select between English and Thai language
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_EN) {
            setLanguage("en");
        } else if (id == R.id.btn_TH) {
            setLanguage("th");
        }
    }

}
