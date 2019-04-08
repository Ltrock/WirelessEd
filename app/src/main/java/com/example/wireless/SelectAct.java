package com.example.wireless;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ui.email.SignInActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


// students can select activity by choosing between module, exercise, and quiz

public class SelectAct extends LocalizationActivity {
    private FirebaseAuth mAuth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        View v = getLayoutInflater().inflate(R.layout.activity_select, null);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ImageView mod= (ImageView) findViewById(R.id.module);
        ImageView ex= (ImageView) findViewById(R.id.exer);
        ImageView q= (ImageView) findViewById(R.id.quiz);

        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),Module.class);
                startActivity(intent);

            }
        });

        ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),exer.class);
                startActivity(intent);

            }
        });
        q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),Quiz.class);
                startActivity(intent);

            }
        });
    }
    // menu options

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();

        if (id == R.id.otherres) {
            Intent intent = new Intent(this, OtherRes.class);
            this.startActivity(intent);
            return true;
        }
        if (id == R.id.note) {
            Intent intent = new Intent(this, NotePage.class);
            this.startActivity(intent);
            return true;
        }
        if (id == R.id.todo) {
            Intent intent = new Intent(this, Todoselect.class);
            this.startActivity(intent);
            return true;
        }
        if (id == R.id.logout) {
            mAuth.signOut();
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
