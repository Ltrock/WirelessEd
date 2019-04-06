package com.example.wireless;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wireless.R;
import com.example.wireless.SQLiteHelper;

public class Todoselect extends AppCompatActivity {
    private SQLiteHelper mSQLite;
    private SQLiteDatabase mDb;

    private String[] mMonths = {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todoselect);
        View v = getLayoutInflater().inflate(R.layout.activity_todoselect, null);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.todo));
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Todoselect.this,Todoselect2.class));
            }
        });

        mSQLite = SQLiteHelper.getInstance(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        LayoutInflater inflater = getLayoutInflater();
        LinearLayout root = (LinearLayout) findViewById(R.id.main_layout);
        root.removeAllViewsInLayout();

        mDb = mSQLite.getReadableDatabase();
        String sql = "SELECT * FROM important_day ORDER BY month, date";
        final Cursor cursor = mDb.rawQuery(sql, null);

        int i = 0;
        while(cursor.moveToNext()) {
            View item = inflater.inflate(R.layout.item_layout, null);
            item.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            String str = "";
            if(cursor.getInt(1) < 10) {
                str += "0";
            }
            str += cursor.getString(1) + "  " + mMonths[cursor.getInt(2) - 1];
            TextView textDate = (TextView) item.findViewById(R.id.text_date);
            textDate.setText(str);

            TextView textDayName = (TextView) item.findViewById(R.id.text_day_name);
            textDayName.setText(cursor.getString(3));

            final ImageButton btUpdate = (ImageButton) item.findViewById(R.id.button_update);
            btUpdate.setTag(cursor.getInt(0));
            btUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickUpdate(btUpdate.getTag().toString());
                }
            });

            final ImageButton btDelete = (ImageButton) item.findViewById(R.id.button_delete);
            btDelete.setTag(cursor.getInt(0));
            btDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickDelete(Integer.valueOf(btDelete.getTag().toString()));
                }
            });

            root.addView(item, i);
            i++;
        }
        cursor.close();
    }

    private void onClickUpdate(String _id) {
        Intent intent = new Intent(Todoselect.this, Todoselect2.class);
        intent.putExtra("_id", _id);
        startActivity(intent);
    }

    private void onClickDelete(final int _id) {
        new AlertDialog.Builder(Todoselect.this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Confirm delete")
                .setMessage("Do you want to delete this?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sql = "DELETE FROM important_day WHERE _id = " + _id;
                        mDb = mSQLite.getWritableDatabase();
                        mDb.execSQL(sql);
                        onStart();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                })
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

        return super.onOptionsItemSelected(item);
    }
}

