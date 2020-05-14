package com.example.wafil.Wafil.chefCook.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.wafil.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class YellowFragment extends Fragment {

    String ARG_PARAM1 = "param1";
    String ARG_PARAM2 = "param2";

    public YellowFragment() {
        // Required empty public constructor
    }

    public YellowFragment newInstance(String param1, String param2){
        YellowFragment fragment = new YellowFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_yellow, container, false);
//        Button buttonInFragment1 = "rootView".findViewById(R.id.button_1);
        return rootView;
    }
}
