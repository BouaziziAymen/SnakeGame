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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextMail;
    private EditText editTextPassword;
    private EditText editTextLogin;
private TextView textViewSignIn;
private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
        }

        progressDialog = new ProgressDialog(this);
buttonRegister = findViewById(R.id.button);
editTextMail = findViewById(R.id.emailInput);
editTextPassword = findViewById(R.id.passwordInput);
editTextLogin = findViewById(R.id.loginInput);
textViewSignIn = findViewById(R.id.textViewSignIn);
buttonRegister.setOnClickListener(this);
textViewSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
if(v==buttonRegister){
registerUser();
} else if(v==textViewSignIn) {
startActivity(new Intent(this,LoginActivity.class));
}
    }

    private void registerUser() {
        String email = editTextMail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
      final  String login = editTextLogin.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            return;
        }
        if (TextUtils.isEmpty(password)) {
            return;
        }
progressDialog.setMessage("Registering User "+login+"...");
        progressDialog.show();
firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        progressDialog.dismiss();
if(task.isSuccessful()){
    Toast.makeText(SignUpActivity.this,"Registered Succesfully!",Toast.LENGTH_SHORT).show();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(login).build();
    user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            finish();
            startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
        }
    });


} else {
    Toast.makeText(SignUpActivity.this,"Failed To Register!",Toast.LENGTH_SHORT).show();

}
    }
});

    }
}
