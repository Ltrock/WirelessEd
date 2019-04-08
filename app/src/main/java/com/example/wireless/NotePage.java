package com.example.wireless;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import static com.rd.utils.DensityUtils.dpToPx;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;

// The class for uploading note with photo and short name of photo to share content
// related to wireless content to everyone that has account to access the app

public class NotePage extends LocalizationActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private Button aButtonChooseImage;
    private Button aButtonUpload;
    private TextView aTextViewShowUploads;
    private EditText aEditTextFileName;
    private ImageView aImageView;
    private ProgressBar aProgressBar;
    private Uri aImageUri;
    private StorageReference aStorageRef;
    private DatabaseReference aDatabaseRef;
    private StorageTask aUploadTask;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_page);
        View v = getLayoutInflater().inflate(R.layout.activity_module, null);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.createnote));
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mAuth = FirebaseAuth.getInstance();
        aButtonChooseImage = findViewById(R.id.choose_image);
        aButtonUpload = findViewById(R.id.button_upload);
        aTextViewShowUploads = findViewById(R.id.show_uploads);
        aEditTextFileName = findViewById(R.id.file_name);
        aImageView = findViewById(R.id.image_view);
        aProgressBar = findViewById(R.id.progress_bar);

        aStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        aDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        aButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        aButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aUploadTask != null && aUploadTask.isInProgress()) {
                    Toast.makeText(NotePage.this, getString(R.string.uploading), Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                }
            }
        });

        aTextViewShowUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagesActivity();
            }
        });
    }

    // method to open recent images on device
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    //get image uri from firebase storage to save in imageview of layour

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            aImageUri = data.getData();


            Picasso.get().load(aImageUri).into(aImageView);
        }
    }

    //get file extention to match with type specified in uri of picture keep in firebase storage

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    // method to upload file and show progress bar while uploading
    private void uploadFile() {
        if (aImageUri != null) {
            StorageReference fileReference = aStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(aImageUri));

            aUploadTask = fileReference.putFile(aImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    aProgressBar.setProgress(0);
                                }
                            }, 500);

                            Toast.makeText(NotePage.this,getString(R.string.upsuccess), Toast.LENGTH_LONG).show();
                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();

                            while (!urlTask.isSuccessful());

                            Uri downloadUrl = urlTask.getResult();

//                            Log.d(TAG, "onSuccess: firebase download url: " + downloadUrl.toString());

                            UploadNote upload = new UploadNote(aEditTextFileName.getText().toString().trim(),downloadUrl.toString());



                            String uploadId = aDatabaseRef.push().getKey();

                            aDatabaseRef.child(uploadId).setValue(upload);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(NotePage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            aProgressBar.setProgress((int) progress);
                        }
                    });
        } else {
            Toast.makeText(this,getString(R.string.nofile), Toast.LENGTH_SHORT).show();
        }
    }

    // when click on show image, it will link to Image activity
    private void openImagesActivity() {
        Intent intent = new Intent(this, Image.class);
        startActivity(intent);
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
