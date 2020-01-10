package com.hfad.homework4geninf;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;

    private EditText titleInput;
    private EditText contentInput;
    private Button postButton;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        //Set the toolbar as the activity's app bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        user = firebaseAuth.getCurrentUser();


        titleInput =(EditText) findViewById(R.id.postTitle);
        contentInput =(EditText) findViewById(R.id.postContent);
        postButton =(Button) findViewById(R.id.postButton);
        postButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

if(v== postButton){
    postMessage();
}
    }

    private void postMessage() {
        String postTitle = titleInput.getText().toString();
        String postContent = contentInput.getText().toString();


     final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("blogMessages");
        BlogMessage blogMessage = new BlogMessage(ref.push().getKey(),postTitle,postContent,user.getDisplayName(),user.getUid());


ref.child(blogMessage.getId()).setValue(blogMessage).addOnCompleteListener(new OnCompleteListener<Void>() {
    @Override
    public void onComplete(@NonNull Task<Void> task) {
        Toast.makeText(CreateActivity.this,"Message Posted",Toast.LENGTH_SHORT).show();
        finish();
        Intent intent = new Intent(CreateActivity.this,MainActivity.class);
        intent.putExtra("Section",1);
        startActivity(intent);
    }
});



    }
}
