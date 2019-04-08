package com.example.wireless;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.akexorcist.localizationactivity.ui.LocalizationActivity;

// show additional resources link related to wireless class in selected browser on device
public class OtherRes extends LocalizationActivity {
    private FirebaseAuth mAuth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_res);
        View v = getLayoutInflater().inflate(R.layout.activity_module, null);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.Resources));
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mAuth = FirebaseAuth.getInstance();

        TextView t1 = (TextView) findViewById(R.id.link1);
        t1.setMovementMethod(LinkMovementMethod.getInstance());

        TextView t2 = (TextView) findViewById(R.id.link2);
        t2.setMovementMethod(LinkMovementMethod.getInstance());

        TextView t3 = (TextView) findViewById(R.id.link3);
        t3.setMovementMethod(LinkMovementMethod.getInstance());

        TextView t4 = (TextView) findViewById(R.id.link4);
        t4.setMovementMethod(LinkMovementMethod.getInstance());
    }

    // menu options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    //select options between additional resources and note
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.otherres) {
            Intent intent = new Intent(this,OtherRes.class);
            this.startActivity(intent);
            return true;
        }
        if (id == R.id.note) {
            Intent intent = new Intent(this,NotePage.class);
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
