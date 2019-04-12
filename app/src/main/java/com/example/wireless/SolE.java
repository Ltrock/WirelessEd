package com.example.wireless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;

// read pdf file from the selected chapter on webview and see embedded file by google doc
// the file is retrieved from firebase storage

public class SolE extends LocalizationActivity {
    private WebView readsolqpdfView;

    @Override
   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sol_e);
        View v = getLayoutInflater().inflate(R.layout.activity_sol_e, null);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.solutionE));
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //use GG doc, download and display solution on webview
        WebView webview = (WebView) findViewById(R.id.readsolEpdfView);
        webview.getSettings().setJavaScriptEnabled(true);
        String pdf = "https://firebasestorage.googleapis.com/v0/b/wireless-17535.appspot.com/o/Exercise_solution2.pdf?alt=media&token=13c19142-1088-48b9-b60c-8117ab76ca38";
        webview.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf);
    }
}


