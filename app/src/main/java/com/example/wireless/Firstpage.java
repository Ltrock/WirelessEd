package com.example.wireless;


import android.os.Bundle;


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.rd.PageIndicatorView;

//show viewpager to slide between each fragment
// adapt from https://github.com/romandanylyk/PageIndicatorView

public class Firstpage extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);

        PagerAdapter a = new PagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(a);

        PageIndicatorView pageIndicatorView = (PageIndicatorView)findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setViewPager(viewPager);
    }
    //menu option when swipe
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}
