package com.hfad.homework4geninf;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class BlogDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE ="message" ;
    private BlogMessage message;
    private FirebaseDatabaseHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_detail);
//Set the toolbar as the activity's app bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


         message = (BlogMessage)getIntent().getExtras().get(EXTRA_MESSAGE);
          helper = new FirebaseDatabaseHelper();

         TextView detailTitle = findViewById(R.id.detail_title);
        TextView detailContent = findViewById(R.id.detail_content);
        TextView detailAuthor = findViewById(R.id.detailAuthor);
        TextView detailDate = findViewById(R.id.detailDate);
         detailTitle.setText(message.getTitle());
        detailContent.setText(message.getContent());
        detailAuthor.setText("Written by "+message.getUserLogin());
        detailDate.setText(message.getDate());


        Button editButton = findViewById(R.id.editButton);
        Button deleteButton = findViewById(R.id.deleteButton);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if(!auth.getUid().equals(message.getUserId())){
            editButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.GONE);
        }

    }

    public void deletePost(View view) {

        helper.deleteMessage(message.getId(),new FirebaseDatabaseHelper.DataStatus() {

            @Override
            public void DataIsLoaded(List<BlogMessage> messages) {

            }

            @Override
            public void DataIsDeleted() {
                finish();
                Intent intent = new Intent(BlogDetailActivity.this,MainActivity.class);
                intent.putExtra("Section",1);
             startActivity(intent);
            }
        });

        }

    public void editPost(View view) {
        Intent intent = new Intent(BlogDetailActivity.this,UpdateActivity.class);
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }
}