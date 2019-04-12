package com.example.wireless;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;



import com.akexorcist.localizationactivity.core.LocalizationApplicationDelegate;

// Class for method of localizationactivity derived from
// http://www.akexorcist.com/2015/07/localization-activity-best-way-to-handle-language.html

public class CustomApp extends Application {
    LocalizationApplicationDelegate localizationDelegate = new LocalizationApplicationDelegate(this);

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(localizationDelegate.attachBaseContext(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        localizationDelegate.onConfigurationChanged(this);
    }

    @Override
    public Context getApplicationContext() {
        return localizationDelegate.getApplicationContext(super.getApplicationContext());
    }

}
