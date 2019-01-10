package com.swifton.swifton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
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


public class SignupActivity extends AppCompatActivity {
    TextView loginLabel;
    Button cmd_signupbtn;
    EditText txtFullName,txtSignupEmail,txtSignupPassword, txtsignupconfirmpass;
    ProgressDialog progressDialog;
    private static String TAG = "Update" ;



    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        loginLabel=findViewById(R.id.loginlabel);
        cmd_signupbtn = findViewById(R.id.signupbtn);
        txtSignupEmail = findViewById(R.id.txtsignupemail);
        txtSignupPassword = findViewById(R.id.txtsignuppswrd);
        txtsignupconfirmpass = findViewById(R.id.txtsignupconfirmpass);


        loginLabel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent loginIntent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                overridePendingTransition(R.anim.righttranslate, R.anim.lefttrslate);
            }
        });

        cmd_signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptSignUp();
            }
        });

        mAuth = FirebaseAuth.getInstance();
    }

    private void attemptSignUp(){
        //reset errors
        txtSignupEmail.setError(null);
        txtSignupPassword.setError(null);
        txtsignupconfirmpass.setError(null);

        String email = txtSignupEmail.getText().toString().trim();
        String password = txtSignupPassword.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            txtSignupPassword.setError(getString(R.string.password_error));
            focusView = txtSignupPassword = txtsignupconfirmpass;
            cancel = true;
            focusView.requestFocus();
//            txtSignupPassword.getText().clear();
//            txtsignupconfirmpass.getText().clear();
        } else if (!isEmailValid(email)) {
            txtSignupEmail.setError(getString(R.string.invalid_email_error));
            focusView = txtSignupEmail;
          //  cancel = true;
            focusView.requestFocus();
        } else {
            createFirebaseUser();
        }

    }

    //todo

    private void createFirebaseUser() {
        String email = txtSignupEmail.getText().toString().trim();
        String password = txtSignupPassword.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();

                        if (!task.isSuccessful()){
                            Log.d("SwiftOn", "user creation failed");
                            showErrorDialog("Registration attempt failed");
                        } else {
                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                            finish();
                            startActivity(intent);
                        }
                    }
                });
    }

    private boolean isEmailValid (String email){
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }

    private boolean isPasswordValid (String password){
        String confirmPassword = txtsignupconfirmpass.getText().toString().trim();
        return confirmPassword.equals(password) && password.length() > 6;
    }

    private void createUser (){
        String email = txtSignupEmail.getText().toString();
        String password = txtSignupPassword.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(getApplicationContext(), "Create User Completed" + task.isSuccessful(),Toast.LENGTH_SHORT ).show();

                if (!task.isSuccessful()){
                   showErrorDialog("Sign up attempt failed");
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
