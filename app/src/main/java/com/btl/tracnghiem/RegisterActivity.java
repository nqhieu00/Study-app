package com.btl.tracnghiem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.login.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class RegisterActivity extends AppCompatActivity {

    EditText mName,mEmail,mPhone,mPassword;
    FirebaseAuth auth;
    Button mRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mName=(EditText)findViewById(R.id.editTextName);
        mEmail=(EditText)findViewById(R.id.editTextEmail);
        mPhone=(EditText)findViewById(R.id.editTextMobile);
        mPassword=(EditText)findViewById(R.id.editTextPassword);
        mRegisterBtn=(Button)findViewById(R.id.cirRegisterButton);

        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            overridePendingTransition(R.transition.stay,R.transition.slide_in_right);
            finish();
        }
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=mEmail.getText().toString();
                String password=mPassword.getText().toString();
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mEmail.setError("Password is required");
                    return;
                }
                if(password.length()<6){
                    mPassword.setError("Password must >= 6 characters");
                    return;
                }
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"User created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            overridePendingTransition(R.transition.stay,R.transition.slide_in_right);
                        }
                        else {
                            Toast.makeText(RegisterActivity.this,task.getException().toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }

    public void OnLoginClick(View view) {
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.transition.slide_in_left,R.transition.stay);
    }
}