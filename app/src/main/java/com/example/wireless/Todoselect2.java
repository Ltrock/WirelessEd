package com.example.wireless;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class Todoselect2 extends AppCompatActivity {
    private SQLiteHelper mSQLite;
    private SQLiteDatabase mDb;

    private EditText mEditDate;
    private EditText mEditMonth;
    private EditText mEditDayName;
    private int _id = -1;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todoselect2);
        View v = getLayoutInflater().inflate(R.layout.activity_todoselect2, null);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("TodoSelect2");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mAuth = FirebaseAuth.getInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mSQLite = SQLiteHelper.getInstance(this);

        mEditDate = (EditText)findViewById(R.id.edit_date);
        mEditMonth = (EditText)findViewById(R.id.edit_month);
        mEditDayName = (EditText)findViewById(R.id.edit_day_name);

        Button button = (Button)findViewById(R.id.button_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImportantDay();
            }
        });

        Button buttonBack = (Button)findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Todoselect2.this, Todoselect.class));
            }
        });

        Button buttonClear = (Button)findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditDate.setText("");
                mEditMonth.setText("");
                mEditDayName.setText("");
            }
        });



    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String id = intent.getStringExtra("_id");
        if(id == null) {
            return;
        } else {
            _id = Integer.valueOf(id);
        }

        String sql =
                "SELECT * FROM important_day " +
                        "WHERE _id = " + _id;

        mDb = mSQLite.getReadableDatabase();
        Cursor cursor = mDb.rawQuery(sql, null);
        cursor.moveToNext();
        mEditDate.setText(cursor.getString(1));
        mEditMonth.setText(cursor.getString(2));
        mEditDayName.setText(cursor.getString(3));

        cursor.close();
    }

    private void addImportantDay() {
        int date = Integer.valueOf(mEditDate.getText().toString());
        int month = Integer.valueOf(mEditMonth.getText().toString());
        String dayName = mEditDayName.getText().toString().trim();

        if(!isDataComplete(date, month, dayName)) {
            return;
        }

        if(_id == -1) {
            insert(date, month, dayName);
        } else {
            update(date, month, dayName);
        }
    }

    private void insert(int date, int month, String dayName) {
        String sql =
                "INSERT INTO important_day(date, month, day_name) " +
                        "VALUES(?, ?, ?)";

        String[] args = {date+"", month+"", dayName};
        mDb = mSQLite.getWritableDatabase();
        mDb.execSQL(sql, args);
        showMessage("saved");
    }

    private void update(int date, int month, String dayName) {
        String sql =
                "UPDATE important_day " +
                        "SET date = ?, month = ?, day_name = ? " +
                        "WHERE _id = ?";

        String[] args = {date+"", month+"", dayName, _id+""};

        mDb = mSQLite.getWritableDatabase();
        mDb.execSQL(sql, args);
        showMessage("saved");
    }

    private boolean isDataComplete(int date, int month, String dayName) {
        if(date < 1 || date > 31) {
            showMessage("date should be 1-31");
            return false;
        }

        if(month < 1 || month > 12) {
            showMessage("Month should be 1 - 12");
            return false;
        }

        switch(month) {
            case 4: case 6: case 9: case 11:
                if(date == 31) {
                    showMessage("That month only has 30 days");
                    return false;
                }
                break;
            case 2:
                if(date > 29) {
                    showMessage("That month only has 28-29 days");
                    return false;
                }
        }

        if(dayName.equals("")) {
            showMessage("Not specify todo yet");
            return false;
        }

        return true;
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
    // menu options
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
