package com.zubisofts.kickcounterex;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zubisofts.kickcounterex.ui.main.InfoFragment;
import com.zubisofts.kickcounterex.ui.main.KickFragment;
import com.zubisofts.kickcounterex.ui.main.KickHistoryFragment;
import com.zubisofts.kickcounterex.ui.main.KickStatisticsFragment;
import com.zubisofts.kickcounterex.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity implements KickFragment.KickSessionListener {

    private FloatingActionButton fab;
    private ViewPager viewPager;
    private boolean mIsActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Tabs and viewpager setup
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        sectionsPagerAdapter.addFragment(KickFragment.newInstance(this));
        sectionsPagerAdapter.addFragment(KickHistoryFragment.newInstance());
        sectionsPagerAdapter.addFragment(KickStatisticsFragment.newInstance());
        sectionsPagerAdapter.addFragment(InfoFragment.newInstance());
        tabs.getTabAt(0).setIcon(R.drawable.ic_foot);
        tabs.getTabAt(1).setIcon(R.drawable.ic_history);
        tabs.getTabAt(2).setIcon(R.drawable.ic_statistics);
        tabs.getTabAt(3).setIcon(R.drawable.ic_info);

        fab = findViewById(R.id.fab);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    if(mIsActive){
                        fab.hide();
                    }else{
                        fab.show();
                    }

                } else {
                    fab.hide();

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });
    }

    @Override
    public void onSessionChanged(boolean isActive) {
        mIsActive = isActive;
        if (mIsActive) {
            if (viewPager.getCurrentItem() == 1) {
                fab.hide();
            }
        } else {
            fab.hide();
        }
    }
}