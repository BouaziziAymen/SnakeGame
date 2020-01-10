package com.hfad.homework4geninf;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class TopFragment extends Fragment implements View.OnClickListener {


    private View logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, container, false);
         logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(this);

        return view;



    }

    @Override
    public void onClick(View v) {
        if(v==logout){

                FirebaseAuth.getInstance().signOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(),LoginActivity.class));

        }
    }
}