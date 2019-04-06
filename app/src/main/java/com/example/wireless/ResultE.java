package com.example.wireless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// show result of exercise
public class ResultE extends AppCompatActivity {

    TextView t1,t2,t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_e);
        View v = getLayoutInflater().inflate(R.layout.activity_result_e, null);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Exercise Evaluation");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        t1 = (TextView)findViewById(R.id.textv4);
        t2 = (TextView)findViewById(R.id.textv5);
        t3 = (TextView)findViewById(R.id.textv6);

        Intent i =getIntent();
        String questions = i.getStringExtra("Total:");
        String correct = i.getStringExtra("Correct:");
        String wrong = i.getStringExtra("Wrong:");

        t1.setText(questions);
        t2.setText(correct);
        t3.setText(wrong);

        Button se =findViewById(R.id.sole);
        se.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),SolE.class);
                startActivity(intent);

            }
        });
    }
    //menu options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }
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

        return super.onOptionsItemSelected(item);

    }
}
