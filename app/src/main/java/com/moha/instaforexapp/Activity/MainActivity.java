package com.moha.instaforexapp.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.moha.instaforexapp.Adapter.ViewPagerAdapter;
import com.moha.instaforexapp.R;

public class MainActivity extends AppCompatActivity {
    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       ViewPager viewPager = findViewById(R.id.view_pager_id);
       TabLayout tabLayout = findViewById(R.id.table_layout);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this.getSupportFragmentManager());
       viewPager.setAdapter(adapter);
       tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}
