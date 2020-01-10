package com.hfad.homework4geninf;

import android.content.Intent;
import android.support.annotation.NonNull;
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

import static com.hfad.homework4geninf.BlogDetailActivity.EXTRA_MESSAGE;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;

    private EditText titleInput;
    private EditText contentInput;
    private Button updateButton;
    private FirebaseUser user;
    private BlogMessage message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //Set the toolbar as the activity's app bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        user = firebaseAuth.getCurrentUser();

         message= (BlogMessage)getIntent().getExtras().get(EXTRA_MESSAGE);
        titleInput =(EditText) findViewById(R.id.updateTitle);
        contentInput =(EditText) findViewById(R.id.updateContent);
        updateButton =(Button) findViewById(R.id.updateButton);
        updateButton.setOnClickListener(this);
        titleInput.setText(message.getTitle());
        contentInput.setText(message.getContent());
    }

    @Override
    public void onClick(View v) {

        if(v== updateButton){
            repostMessage();
        }
    }

    private void repostMessage() {
        String postTitle = titleInput.getText().toString();
        String postContent = contentInput.getText().toString();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("blogMessages");
        BlogMessage blogMessage = new BlogMessage(message.getId(),postTitle,postContent,user.getDisplayName(),user.getUid());


        ref.child(blogMessage.getId()).setValue(blogMessage).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(UpdateActivity.this,"Message Updated",Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
                intent.putExtra("Section",1);
                startActivity(intent);
            }
        });



    }
}
