package com.geekyapps.materialdesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Nixxmare on 11/17/2016.
 */

public class MyFragment extends Fragment {
    private TextView textView;

    public static MyFragment getInstance(int position) {
        MyFragment myFragment = new MyFragment();
        Bundle args = new Bundle();
        args.putInt("position", position + 1);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        textView = (TextView) view.findViewById(R.id.myFragmentTextView);
        Bundle bundle = getArguments();
//        if (bundle != null) {
//            textView.setText("The Page Currently Selected is " + bundle.getInt("position"));
//        }

        RequestQueue requestQueue = VolleySingleton.getInstance().getmRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://apuzz.com/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(getActivity(), " "+response, Toast.LENGTH_LONG).show();
                textView.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), " Error \n "+error.toString(), Toast.LENGTH_LONG).show();

            }
        });

        requestQueue.add(stringRequest);
        return view;
    }
}