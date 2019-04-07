package com.example.wireless;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wireless.Model.QuestionEx;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class exer extends AppCompatActivity {

    Button b1, b2, b3, b4;
    TextView tq;
    int totalQ = 0;
    int corr = 0;
    int wrong = 0;
    DatabaseReference databaseReference;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exer);
        View v = getLayoutInflater().inflate(R.layout.activity_exer, null);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Exercise");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mAuth =FirebaseAuth.getInstance();
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);

        tq = (TextView) findViewById(R.id.exertext);
        updateQ();

    }

    private void updateQ() {
        totalQ+=1;
        if(totalQ > 10){
            //open new act
            Intent i = new Intent(exer.this,ResultE.class);
            i.putExtra("Total:", String.valueOf(totalQ-1));
            i.putExtra("Correct:",String.valueOf(corr));
            i.putExtra("Wrong:",String.valueOf(wrong));
            startActivity(i);
        }
        else{
            databaseReference= FirebaseDatabase.getInstance().getReference().child("Questionsexer").child(String.valueOf(totalQ));
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final QuestionEx question = dataSnapshot.getValue(QuestionEx.class);
                    tq.setText(question.getQ());
                    b1.setText(question.getOp1());
                    b2.setText(question.getOp2());
                    b3.setText(question.getOp3());
                    b4.setText(question.getOp4());
                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (b1.getText().toString().equals(question.getAns())) {
                                Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                                b1.setBackgroundColor(Color.GREEN);
                                corr += 1;

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#81D4F94"));
                                        updateQ();
                                    }
                                }, 1500);
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                                wrong += 1;
                                b1.setBackgroundColor(Color.RED);
                                if (b2.getText().toString().equals(question.getAns())) {
                                    b2.setBackgroundColor(Color.GREEN);
                                } else if (b3.getText().toString().equals(question.getAns())) {
                                    b3.setBackgroundColor(Color.GREEN);
                                } else if (b4.getText().toString().equals(question.getAns())) {
                                    b4.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#81D4F9"));
                                        b2.setBackgroundColor(Color.parseColor("#81D4F9"));
                                        b3.setBackgroundColor(Color.parseColor("#81D4F9"));
                                        b4.setBackgroundColor(Color.parseColor("#81D4F9"));
                                        updateQ();
                                    }
                                }, 1500);
                            }
                        }
                    });
                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (b2.getText().toString().equals(question.getAns())) {
                                Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                                b2.setBackgroundColor(Color.GREEN);
                                corr += 1;

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b2.setBackgroundColor(Color.parseColor("#81D4F9"));
                                        updateQ();
                                    }
                                }, 1500);
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                                wrong += 1;
                                b2.setBackgroundColor(Color.RED);
                                if (b1.getText().toString().equals(question.getAns())) {
                                    b1.setBackgroundColor(Color.GREEN);
                                } else if (b3.getText().toString().equals(question.getAns())) {
                                    b3.setBackgroundColor(Color.GREEN);
                                } else if (b4.getText().toString().equals(question.getAns())) {
                                    b4.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#81D4F9"));
                                        b2.setBackgroundColor(Color.parseColor("#81D4F9"));
                                        b3.setBackgroundColor(Color.parseColor("#81D4F9"));
                                        b4.setBackgroundColor(Color.parseColor("#81D4F9"));
                                        updateQ();
                                    }
                                }, 1500);
                            }


                        }
                    });

                    b3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (b3.getText().toString().equals(question.getAns())) {
                                Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                                b3.setBackgroundColor(Color.GREEN);
                                corr += 1;

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b3.setBackgroundColor(Color.parseColor("#81D4F9"));
                                        updateQ();
                                    }
                                }, 1500);
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                                wrong += 1;
                                b3.setBackgroundColor(Color.RED);
                                if (b1.getText().toString().equals(question.getAns())) {
                                    b1.setBackgroundColor(Color.GREEN);
                                } else if (b2.getText().toString().equals(question.getAns())) {
                                    b2.setBackgroundColor(Color.GREEN);
                                } else if (b4.getText().toString().equals(question.getAns())) {
                                    b4.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#81D4F9"));
                                        b2.setBackgroundColor(Color.parseColor("#81D4F9"));
                                        b3.setBackgroundColor(Color.parseColor("#81D4F9"));
                                        b4.setBackgroundColor(Color.parseColor("#81D4F9"));
                                        updateQ();
                                    }
                                }, 1500);
                            }

                        }
                    });
                    b4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (b4.getText().toString().equals(question.getAns())) {
                                Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                                b4.setBackgroundColor(Color.GREEN);
                                corr += 1;

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b4.setBackgroundColor(Color.parseColor("#81D4F9"));
                                        updateQ();
                                    }
                                }, 1500);
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                                wrong += 1;
                                b4.setBackgroundColor(Color.RED);
                                if (b1.getText().toString().equals(question.getAns())) {
                                    b1.setBackgroundColor(Color.GREEN);
                                } else if (b2.getText().toString().equals(question.getAns())) {
                                    b2.setBackgroundColor(Color.GREEN);
                                } else if (b3.getText().toString().equals(question.getAns())) {
                                    b3.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#81D4F9"));
                                        b2.setBackgroundColor(Color.parseColor("#81D4F9"));
                                        b3.setBackgroundColor(Color.parseColor("#81D4F9"));
                                        b4.setBackgroundColor(Color.parseColor("#81D4F9"));
                                        updateQ();
                                    }
                                }, 1500);
                            }
                        }
                    });
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }
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
