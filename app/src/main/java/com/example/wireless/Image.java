package com.example.wireless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import com.akexorcist.localizationactivity.ui.LocalizationActivity;

public class Image extends LocalizationActivity {
    private RecyclerView aRecyclerView;
    private ImageAdapter aAdapter;
    private ProgressBar aProgressCircle;
    private DatabaseReference aDatabaseRef;
    private List<UploadNote> aUploads;
    private FirebaseAuth mAuth;
    @Override
    // retrieve data from Firebase database
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        View v = getLayoutInflater().inflate(R.layout.activity_image, null);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.Img));
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mAuth = FirebaseAuth.getInstance();
        aRecyclerView = findViewById(R.id.recycler_viewer);
        aRecyclerView.setHasFixedSize(true);
        aRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        aProgressCircle = findViewById(R.id.progress_circle);
        aUploads = new ArrayList<>();
        aDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        aDatabaseRef.addValueEventListener(new ValueEventListener() {
            // This method is called once with the initial value and again
            // whenever data at this location is updated
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UploadNote upload = postSnapshot.getValue(UploadNote.class);
                    aUploads.add(upload);
                }

                aAdapter = new ImageAdapter(Image.this, aUploads);

                aRecyclerView.setAdapter(aAdapter);
                aProgressCircle.setVisibility(View.INVISIBLE);
            }
            //show message when it has error
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Image.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                aProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }
    // menu options
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
