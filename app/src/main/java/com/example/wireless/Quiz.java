package com.example.wireless;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
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

import com.example.wireless.Model.Question;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


// Quiz class that show score and solution while doing quiz, it also set time limit
public class Quiz extends AppCompatActivity {

    Button bt1, bt2, bt3, bt4;
    TextView t_q, timer;
    int totalQ = 0;
    int corr = 0;
    int wrong = 0;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        View v = getLayoutInflater().inflate(R.layout.activity_quiz, null);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Quiz");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mAuth = FirebaseAuth.getInstance();
        bt1 = (Button) findViewById(R.id.button1);
        bt2 = (Button) findViewById(R.id.button2);
        bt3 = (Button) findViewById(R.id.button3);
        bt4 = (Button) findViewById(R.id.button4);

        t_q = (TextView) findViewById(R.id.qtext);
        timer = (TextView) findViewById(R.id.timeText);

        updateQ();
        timercount(15,timer);

    }
    // update question when finish last question, it link to the result page to show score
    private void updateQ() {
        totalQ+=1;
        if(totalQ > 5){
            //open new act
            Intent i = new Intent(Quiz.this,ResultQ.class);
            i.putExtra("Total:", String.valueOf(totalQ-1));
            i.putExtra("Correct",String.valueOf(corr));
            i.putExtra("Wrong",String.valueOf(wrong));
            startActivity(i);
        }
        else{
            databaseReference= FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(totalQ));
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Question question = dataSnapshot.getValue(Question.class);
                    t_q.setText(question.getQuestion());
                    bt1.setText(question.getOption1());
                    bt2.setText(question.getOption2());
                    bt3.setText(question.getOption3());
                    bt4.setText(question.getOption4());
                    bt1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (bt1.getText().toString().equals(question.getAnswer())) {
                                Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                                bt1.setBackgroundColor(Color.GREEN);
                                corr += 1;

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        bt1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQ();
                                    }
                                }, 1500);
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                                wrong += 1;
                                bt1.setBackgroundColor(Color.RED);
                                if (bt2.getText().toString().equals(question.getAnswer())) {
                                    bt2.setBackgroundColor(Color.GREEN);
                                } else if (bt3.getText().toString().equals(question.getAnswer())) {
                                    bt3.setBackgroundColor(Color.GREEN);
                                } else if (bt4.getText().toString().equals(question.getAnswer())) {
                                    bt4.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        bt1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        bt2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        bt3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        bt4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQ();
                                    }
                                }, 1500);
                            }
                        }
                    });
                    bt2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (bt2.getText().toString().equals(question.getAnswer())) {
                                Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                                bt2.setBackgroundColor(Color.GREEN);
                                corr += 1;

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        bt2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQ();
                                    }
                                }, 1500);
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                                wrong += 1;
                                bt2.setBackgroundColor(Color.RED);
                                if (bt1.getText().toString().equals(question.getAnswer())) {
                                    bt1.setBackgroundColor(Color.GREEN);
                                } else if (bt3.getText().toString().equals(question.getAnswer())) {
                                    bt3.setBackgroundColor(Color.GREEN);
                                } else if (bt4.getText().toString().equals(question.getAnswer())) {
                                    bt4.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        bt1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        bt2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        bt3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        bt4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQ();
                                    }
                                }, 1500);
                            }


                        }
                    });

                    bt3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (bt3.getText().toString().equals(question.getAnswer())) {
                                Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                                bt3.setBackgroundColor(Color.GREEN);
                                corr += 1;

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        bt3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQ();
                                    }
                                }, 1500);
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                                wrong += 1;
                                bt3.setBackgroundColor(Color.RED);
                                if (bt1.getText().toString().equals(question.getAnswer())) {
                                    bt1.setBackgroundColor(Color.GREEN);
                                } else if (bt2.getText().toString().equals(question.getAnswer())) {
                                    bt2.setBackgroundColor(Color.GREEN);
                                } else if (bt4.getText().toString().equals(question.getAnswer())) {
                                    bt4.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        bt1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        bt2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        bt3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        bt4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQ();
                                    }
                                }, 1500);
                            }

                        }
                    });
                    bt4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (bt4.getText().toString().equals(question.getAnswer())) {
                                Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                                bt4.setBackgroundColor(Color.GREEN);
                                corr += 1;

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        bt4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQ();
                                    }
                                }, 1500);
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                                wrong += 1;
                                bt4.setBackgroundColor(Color.RED);
                                if (bt1.getText().toString().equals(question.getAnswer())) {
                                    bt1.setBackgroundColor(Color.GREEN);
                                } else if (bt2.getText().toString().equals(question.getAnswer())) {
                                    bt2.setBackgroundColor(Color.GREEN);
                                } else if (bt3.getText().toString().equals(question.getAnswer())) {
                                    bt3.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        bt1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        bt2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        bt3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        bt4.setBackgroundColor(Color.parseColor("#03A9F4"));
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

        // timer setting
        public void timercount(int sec,final TextView t) {
            new CountDownTimer(sec * 1000 + 1000, 1000) {
                public void onTick(long milli) {
                    int sec = (int) (milli / 1000);
                    int minute = sec / 60;
                    sec %= 60;
                    t.setText(String.format("%02d", minute) + ":" + String.format("%02d", sec));

                }

                // when finish quiz, it shows score
                @Override
                public void onFinish() {
                    t.setText("Completed");
                    Intent myIntent = new Intent(Quiz.this,ResultQ.class);
                    myIntent.putExtra("Total:", String.valueOf(totalQ-1));
                    myIntent.putExtra("Correct:", String.valueOf(corr));
                    myIntent.putExtra("Wrong:", String.valueOf(wrong));
                    startActivity(myIntent);

                }
            }.start();

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
