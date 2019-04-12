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

public class SolQ extends LocalizationActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sol_q);
        View v = getLayoutInflater().inflate(R.layout.activity_sol_q, null);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.solutionQ));
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //use GG doc, download and display solution on webview
        WebView webview = (WebView) findViewById(R.id.readsolpdfView);
        webview.getSettings().setJavaScriptEnabled(true);
        String pdf = "https://firebasestorage.googleapis.com/v0/b/wireless-17535.appspot.com/o/Quiz_solution2.pdf?alt=media&token=e4707ae2-b9a3-49cb-b68e-f5ef86910165";
        webview.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf);
    }
}


