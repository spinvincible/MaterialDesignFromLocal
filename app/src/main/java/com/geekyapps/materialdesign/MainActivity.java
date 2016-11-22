package com.geekyapps.materialdesign;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geekyapps.materialdesign.tabs.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, drawerLayout, toolbar);

        viewPager = (ViewPager) findViewById(R.id.pagerView);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
        slidingTabLayout.setCustomTabView(R.layout.custom_tabs_layout, R.id.customTabsText);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent, getApplicationContext().getTheme()));
            slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent, getApplicationContext().getTheme()));

        }
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SlidingTabsLibraryActivity.class);
            startActivity(intent);
        }
        if (id == R.id.navigate) {
            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    class MyPagerAdapter extends FragmentPagerAdapter {
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



// Day and Night and Bottom Sheet Dialog Box
// Demo App : https://github.com/liaohuqiu/android-support-23.2-sample/blob/master/app/src/main/java/in/srain/demos/vector/MainActivity.java