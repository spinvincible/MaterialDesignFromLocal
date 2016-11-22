package com.geekyapps.materialdesign;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class SlidingTabsLibraryActivity extends AppCompatActivity implements MaterialTabListener {
private Toolbar toolbar;
    private MaterialTabHost materialTabHost;
    private ViewPager pager;
    private MyPagerAdapter myPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_tabs_library);

        toolbar = (Toolbar) findViewById(R.id.toolBarSliding);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        materialTabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        pager = (ViewPager) findViewById(R.id.pagerViewSlidingLayout);
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(myPagerAdapter);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(getColor(R.color.colorPrimaryDark));
            }
        }
        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                materialTabHost.setSelectedNavigationItem(position);
            }
        });

        // insert all tabs from pagerAdapter data
        for (int i = 0; i < myPagerAdapter.getCount(); i++) {
            materialTabHost.addTab(
                    materialTabHost.newTab()
                            .setText(myPagerAdapter.getPageTitle(i))
                            .setTabListener(this)
            );
        }


    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        pager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {
        String[] tabsArray;
        int icons[] = {R.drawable.white_home, R.drawable.white_heart,
                R.drawable.white_star, R.drawable.white_heart, R.drawable.white_star};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            tabsArray = getResources().getStringArray(R.array.TABS);
        }


        @Override
        public CharSequence getPageTitle(int position) {
//            SpannableString spannableString = new SpannableString(" ");
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                Drawable drawable = getResources().getDrawable(icons[position], getApplicationContext().getTheme());
//                drawable.setBounds(0,0,24,24);
//                ImageSpan imageSpan = new ImageSpan(drawable);
//                spannableString.setSpan(imageSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            }
            return tabsArray[position];
        }

        @Override
        public Fragment getItem(int position) {
            MyFragment myFragment = MyFragment.getInstance(position);

            return myFragment;
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
