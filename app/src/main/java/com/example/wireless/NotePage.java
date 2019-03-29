package com.example.wireless;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import static com.rd.utils.DensityUtils.dpToPx;

public class NotePage extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_page);
        View v = getLayoutInflater().inflate(R.layout.activity_module, null);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Create Note");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
                    Toast.makeText(NotePage.this, "Uploading ...", Toast.LENGTH_SHORT).show();
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

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            aImageUri = data.getData();

            Picasso.get().load(aImageUri).resize(dpToPx(80), dpToPx(80)).centerCrop().into(aImageView);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

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

                            Toast.makeText(NotePage.this, "Upload successful", Toast.LENGTH_LONG).show();
                            UploadNote upload = new UploadNote(aEditTextFileName.getText().toString().trim(),
                                    taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());

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
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void openImagesActivity() {
        Intent intent = new Intent(this, Image.class);
        startActivity(intent);
    }


    }
