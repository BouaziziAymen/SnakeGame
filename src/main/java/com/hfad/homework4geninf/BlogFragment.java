package com.hfad.homework4geninf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class BlogFragment extends Fragment {
    private FirebaseDatabaseHelper helper;
    private RecyclerView blogRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       blogRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_blog, container, false);

        helper = new FirebaseDatabaseHelper();
        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        helper.readMessages(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(final List<BlogMessage> messages) {

                BlogCardAdapter adapter = new BlogCardAdapter((ArrayList<BlogMessage>) messages,firebaseAuth.getUid());
                blogRecycler.setAdapter(adapter);
                adapter.setListener(new BlogCardAdapter.Listener() {
                    public void onClick(int position) {
                                Intent intent = new Intent(getActivity(), BlogDetailActivity.class);
                                intent.putExtra(BlogDetailActivity.EXTRA_MESSAGE, messages.get(position));
                                getActivity().startActivity(intent);

                    }
                });
            }

            @Override
            public void DataIsDeleted() {

            }
        });




        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        blogRecycler.setLayoutManager(layoutManager);


        return blogRecycler;
    }

    @Override
    public void onResume() {
        super.onResume();
        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        helper.readMessages(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(final List<BlogMessage> messages) {
                BlogCardAdapter adapter = new BlogCardAdapter((ArrayList<BlogMessage>) messages,firebaseAuth.getUid());
                blogRecycler.setAdapter(adapter);
                adapter.setListener(new BlogCardAdapter.Listener() {
                    public void onClick(int position) {
                        Intent intent = new Intent(getActivity(), BlogDetailActivity.class);
                        intent.putExtra(BlogDetailActivity.EXTRA_MESSAGE, messages.get(position));
                        getActivity().startActivity(intent);

                    }
                });
            }

            @Override
            public void DataIsDeleted() {

            }
        });




    }
}