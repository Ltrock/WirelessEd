package com.example.wireless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class Read extends AppCompatActivity {
    private WebView readpdfView;
    private String file_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        View v = getLayoutInflater().inflate(R.layout.activity_module, null);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Chapter");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //use embedded API of GG doc, download on webview
        Intent intent= getIntent();
        file_path = intent.getStringExtra("filePath");
        readpdfView = (WebView)findViewById(R.id.readpdfView);

        readpdfView.setVisibility(WebView.VISIBLE);
        WebSettings webSettings = readpdfView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        readpdfView.loadUrl("https://docs.google.com/gview?embedded=true&url="+file_path);
    }
}


