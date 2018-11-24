package com.example.alessander.movieapp;

import android.content.Context;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MoviesFragment extends Fragment {
    static GridView gridview;
    static int width;
    static ArrayList<String> posters;
    static boolean sortByPop;
    public MoviesFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        if (MainActivity.TABLET) {

            width = size.x/6;
        }
        else width = size.x/3;
        if (getActivity() != null) {

            ArrayList<String> array = new ArrayList<>();
            ImageAdapter adapter = new ImageAdapter(getActivity(),array, width);
            gridview = (GridView)rootView.findViewById(R.id.gridview);

            gridview.setColumnWidth(width);
            gridview.setAdapter(adapter);
        }

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {

        super.onStart();
        getActivity().setTitle("Most Popular Movies");

        if (isNetWorkAvailable()) {

            gridview.setVisibility(GridView.VISIBLE);
            new ImageLoadTask().execute();
        }
        else {
            TextView textView1 = new TextView(getActivity());
            RelativeLayout layout1 = (RelativeLayout)getActivity().findViewById(R.id.relativeLayout);
            textView1.setText("You are not connected to the Internet");
            if (layout1.getChildCount() == 1) {

                layout1.addView(textView1);
            }
            gridview.setVisibility(GridView.GONE);
        }
    }

    public boolean isNetWorkAvailable() {

        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public class ImageLoadTask extends AsyncTask<Void, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            while (true) {
                try {
                    posters = new ArrayList<>(Arrays.asList(getPathsFromAPI(sortByPop)));
                    return posters;
                }
                catch (Exception e) {

                    continue;
                }
            }
        }

        public String[] getPathsFromAPI(boolean sort) {

            String[] array = new String[15];
            for (int i = 0; i < array.length; i++) {

                array[i] = "/kqjL17yufvn9OVLyXYpvtyrFfak.jpg";
            }
            return array;
        }
    }
}