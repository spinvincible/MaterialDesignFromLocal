package com.geekyapps.materialdesign;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.InterpolatorRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {
    public static final String SharedPrefFileName = "shared_file_name";
    public static final String SharedPrefKey = "shared_key";
    private View containerView;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private Boolean mDrawerLearned;
    private Boolean mFromSavedInsatanceState;

    private RecyclerAdapter adapter;


    private RecyclerView recyclerView;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDrawerLearned = Boolean.valueOf(readPreference(getActivity(), SharedPrefKey, "false"));

        if (savedInstanceState != null) {
            mFromSavedInsatanceState = true;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerListNavigation);
        adapter = new RecyclerAdapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClickListener(View v, int position) {
                Toast.makeText(getActivity(), "onClickListener was Called", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void oNLongClickListener(View v, int position) {
                Toast.makeText(getActivity(), "oNLongClickListener   was Called", Toast.LENGTH_SHORT).show();

            }
        }));
        return layout;
    }

    public static List<DataItems> getData() {
        List<DataItems> dataItem = new ArrayList<>();
        int[] icons = {R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car,
                R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car,
                R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car,
                R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car,
                R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car,
                R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car, R.drawable.car};
        String[] title = {"Item 1", "Item 2", "Item 3", "Item 1", "Item 2", "Item 3",
                "Item 1", "Item 2", "Item 3", "Item 1", "Item 2", "Item 3", "Item 1", "Item 2", "Item 3", "Item 1", "Item 2", "Item 3",
                "Item 1", "Item 2", "Item 3", "Item 1", "Item 2", "Item 3", "Item 1", "Item 2", "Item 3", "Item 1", "Item 2", "Item 3",
                "Item 1", "Item 2", "Item 3", "Item 1", "Item 2", "Item 3"};
        for (int i = 0; i < icons.length && i < title.length; i++) {
            DataItems daata = new DataItems();
            daata.setIconId(icons[i]);
            daata.setTitle(title[i]);
            dataItem.add(daata);
        }

        return dataItem;
    }

    public void setUp(int fragmentId, DrawerLayout dr, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = dr;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), dr, toolbar,
                R.string.drawer_open, R.string.drawer_closed) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mDrawerLearned) {
                    mDrawerLearned = true;
                    savePreference(getActivity(), SharedPrefKey, mDrawerLearned + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }


            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (slideOffset < 0.8) {
                    toolbar.setAlpha(1 - slideOffset);
                }
            }
        };
//        if (!mDrawerLearned && !mFromSavedInsatanceState) {
////           mDrawerToggle.onDrawerOpened(containerView);
//            mDrawerLayout.openDrawer(containerView);
//        }
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static void savePreference(Context context, String prefName, String prefValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPrefFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(prefName, prefValue);
        editor.apply();
    }

    public static String readPreference(Context context, String prefName, String deafaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPrefFileName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(prefName, deafaultValue);
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {

            this.clickListener = clickListener;
//            Log.d("RecyclerTouchListener", "RecyclerTouchListener in Constructor");
    gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
        @Override
        public void onLongPress(MotionEvent e) {
            Log.d("RecyclerTouchListener", "onLongPress " +e);
            View v = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if(v!=null && clickListener !=null)
            {
                clickListener.oNLongClickListener(v, recyclerView.getChildAdapterPosition(v));
            }
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.d("RecyclerTouchListener", "onSingleTapUp " +e);
            return true;
        }
    });


        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            Log.d("RecyclerTouchListener", "onInterceptTouchEvent " +gestureDetector.onTouchEvent(e)+ " " +e);
            View v = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if(v!=null && clickListener !=null && gestureDetector.onTouchEvent(e))
            {
                clickListener.onClickListener(v, recyclerView.getChildAdapterPosition(v));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
          //  Log.d("RecyclerTouchListener", "onTouchEvent " +e);

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
         //   Log.d("RecyclerTouchListener", "onRequestDisallowInterceptTouchEvent");

        }
    }

    public static interface ClickListener {
        public void onClickListener(View v, int position);

        public void oNLongClickListener(View v, int position);
    }


}
