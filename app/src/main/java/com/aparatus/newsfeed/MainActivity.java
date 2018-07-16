package com.aparatus.newsfeed;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.aparatus.newsfeed.Fragments.BasketballFragment;
import com.aparatus.newsfeed.Fragments.CybersportFragment;
import com.aparatus.newsfeed.Fragments.FootballFragment;
import com.aparatus.newsfeed.Fragments.HockeyFragment;
import com.aparatus.newsfeed.Fragments.TennisFragment;
import com.aparatus.newsfeed.Fragments.VolleyballFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pager = findViewById(R.id.pager);
        setupViewPager(pager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);
        fillTabInfo();
    }


    private void setupViewPager(ViewPager pager) {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
            adapter.addingFrag(new FootballFragment(), (String) getResources().getText(R.string.football_tab));
            adapter.addingFrag(new HockeyFragment(), (String) getResources().getText(R.string.hockey_tab));
            adapter.addingFrag(new TennisFragment(), (String) getResources().getText(R.string.tennis_tab));
            adapter.addingFrag(new BasketballFragment(), (String) getResources().getText(R.string.basketball_tab));
            adapter.addingFrag(new VolleyballFragment(), (String) getResources().getText(R.string.volleyball_tab));
            adapter.addingFrag(new CybersportFragment(), (String) getResources().getText(R.string.cybersport_tab));
            pager.setAdapter(adapter);
    }


    private void fillTabInfo() {
        int[] imageResId = {
                R.mipmap.icon_football,
                R.mipmap.icon_hockey,
                R.mipmap.icon_tennis,
                R.mipmap.icon_basketball,
                R.mipmap.icon_volleyball,
                R.mipmap.icon_games};
        String tempString;

            TextView tv_football = (TextView) LayoutInflater.from(this).inflate(R.layout.my_tablayout, null);
            tempString = setManualText(R.string.football_tab);
            tv_football.setText(tempString);
            tv_football.setCompoundDrawablesWithIntrinsicBounds(0, imageResId[0], 0, 0);
            tabLayout.getTabAt(0).setCustomView(tv_football);

            TextView tv_hockey = (TextView) LayoutInflater.from(this).inflate(R.layout.my_tablayout, null);
            tempString = setManualText(R.string.hockey_tab);
            tv_hockey.setText(tempString);
            tv_hockey.setCompoundDrawablesWithIntrinsicBounds(0, imageResId[1], 0, 0);
            tabLayout.getTabAt(1).setCustomView(tv_hockey);

            TextView tv_tennis = (TextView) LayoutInflater.from(this).inflate(R.layout.my_tablayout, null);
            tempString = setManualText(R.string.tennis_tab);
            tv_tennis.setText(tempString);
            tv_tennis.setCompoundDrawablesWithIntrinsicBounds(0, imageResId[2], 0, 0);
            tabLayout.getTabAt(2).setCustomView(tv_tennis);

            TextView tv_basketball = (TextView) LayoutInflater.from(this).inflate(R.layout.my_tablayout, null);
            tempString = setManualText(R.string.basketball_tab);
            tv_basketball.setText(tempString);
            tv_basketball.setCompoundDrawablesWithIntrinsicBounds(0, imageResId[3], 0, 0);
            tabLayout.getTabAt(3).setCustomView(tv_basketball);

            TextView tv_volleyball = (TextView) LayoutInflater.from(this).inflate(R.layout.my_tablayout, null);
            tempString = setManualText(R.string.volleyball_tab);
            tv_volleyball.setText(tempString);
            tv_volleyball.setCompoundDrawablesWithIntrinsicBounds(0, imageResId[4], 0, 0);
            tabLayout.getTabAt(4).setCustomView(tv_volleyball);

            TextView tv_games = (TextView) LayoutInflater.from(this).inflate(R.layout.my_tablayout, null);
            tempString = setManualText(R.string.cybersport_tab);
            tv_games.setText(tempString);
            tv_games.setCompoundDrawablesWithIntrinsicBounds(0, imageResId[5], 0, 0);
            tabLayout.getTabAt(5).setCustomView(tv_games);
    }

    private String setManualText(int tabName) {
        if ((findViewById(R.id.frag_container) == null) && (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)) {
            return "";
        }else {
            return getString(tabName);
        }
    }

    public class  SectionPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragList = new ArrayList<>();
        private final List<String> mFragTitleList = new ArrayList<>();

        private SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragList.get(position);
        }

        @Override
        public int getCount() {
            return mFragList.size();
        }

        private void addingFrag(Fragment fragment, String title) {
            mFragList.add(fragment);
            mFragTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragTitleList.get(position);
        }

    }
}
