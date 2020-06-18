package com.example.a2ayakolyome;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class AboutFragment extends Fragment {


    public AboutFragment() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about, container, false);
        Button backBTN = view.findViewById(R.id.back_BTN_about);
        ImageView facebookView = view.findViewById(R.id.facebook);
        ImageView twitterView = view.findViewById(R.id.twitter);
        ImageView instagramView = view.findViewById(R.id.instagram);

        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , MainActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }
}