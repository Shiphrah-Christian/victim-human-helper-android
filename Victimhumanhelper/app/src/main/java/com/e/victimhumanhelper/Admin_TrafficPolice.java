package com.e.victimhumanhelper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class Admin_TrafficPolice extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__traffic_police);
        getSupportActionBar().hide();

        tabLayout = findViewById(R.id.Tab);
        viewPager = findViewById(R.id.tab_pager);

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        FragmentManager manager = getSupportFragmentManager();
        CustomAdapter adapter = new CustomAdapter(manager);
        viewPager.setAdapter(adapter);
    }

    private class CustomAdapter extends FragmentPagerAdapter {

        public CustomAdapter(FragmentManager fm) {
            super(fm);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position)
            {
                case 0:
                    return "Request";
                case 1:
                    return "History";
            }
            return null;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    return new Trafficpolice_request();
                case 1:
                    return new Trafficpolice_history();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
