package com.hfad.homework4geninf;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonLogin;
    private EditText editTextMail;
    private EditText editTextPassword;
    private TextView textViewSignUp;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
        progressDialog = new ProgressDialog(this);

        buttonLogin = findViewById(R.id.loginButton);
        editTextMail = findViewById(R.id.loginEmail);
        editTextPassword = findViewById(R.id.loginPassword);
        textViewSignUp = findViewById(R.id.textViewSignUp);

        buttonLogin.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
if(v==buttonLogin){
    userLogin();
} else if(v==textViewSignUp){
startActivity(new Intent(this,SignUpActivity.class));
}
    }

    private void userLogin() {
        String email = editTextMail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            return;
        }
        if (TextUtils.isEmpty(password)) {
            return;
        }

        progressDialog.setMessage("Logging In...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Login Succesfully!",Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));

                } else {
                    Toast.makeText(LoginActivity.this,"Failed To Login!",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
