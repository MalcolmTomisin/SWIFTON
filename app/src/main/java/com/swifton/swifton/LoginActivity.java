package com.swifton.swifton;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    TextView SignupLabel, ForgotLabel;
    Button cmd_LoginBtn;
    EditText txtloginEmail, txtloginPassword;
    private FirebaseAuth mAuth;


    private static final String TAG = "RegisterActivity";

    private static final String TAG_SUCCESS = "success";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        SignupLabel = findViewById(R.id.signuplabel);
        cmd_LoginBtn = findViewById(R.id.loginbtn);
        ForgotLabel = findViewById(R.id.loginforgotlabel);
        txtloginEmail = findViewById(R.id.txtloginemail);
        txtloginPassword = findViewById(R.id.txtloginpswrd);

        txtloginPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == R.integer.login || actionId == EditorInfo.IME_NULL){
                    logUserIn();
                    return true;
                }
                return false;
            }
        });


        SignupLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(signupIntent);
                overridePendingTransition(R.anim.righttranslate, R.anim.lefttrslate);
            }
        });

        mAuth = FirebaseAuth.getInstance();

    }

    private void logUserIn() {
       String email = txtloginEmail.getText().toString().trim();
       String password = txtloginPassword.getText().toString().trim();
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.isEmpty())
            if (email.equals("") || !email.matches(emailPattern) || password.equals("") ) return;
        Toast.makeText(this, "Login in progress...", Toast.LENGTH_SHORT).show();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               Log.d("SwiftOn", "Sign in:" + task.isSuccessful());

               if (!task.isSuccessful()){
                   Log.d("SwiftOn", "Problem Signing in" + task.getException());
                   showErrorDialog("There was a problem signing in");
               } else {
                   Intent intent = new Intent(LoginActivity.this, UserDashboardActivity.class);
                   finish();
                   startActivity(intent);
               }
            }
        });


    }

    private void showErrorDialog(String message){
        new AlertDialog.Builder(this)
                .setTitle("Error Signing up!!")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
