package com.example.user.ski_gv.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.ski_gv.R;


public class WeatherFragment extends Fragment {
    private static final int LAYOUT = R.layout.weather_activity;
    private View view;
    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,null);
        return view;
    }
}
