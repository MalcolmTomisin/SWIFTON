package com.swifton.swifton;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class LoginActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener, View.OnClickListener{
    TextView SignupLabel, ForgotLabel;
    Button cmd_LoginBtn;
    EditText txtloginEmail, txtloginPassword;
    ImageView googleButton;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;

    //private SignInButton googleButton;




    private static final String TAG = "RegisterActivity";

    private static final String TAG_SUCCESS = "success";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        SignupLabel = findViewById(R.id.signuplabel);
        cmd_LoginBtn = findViewById(R.id.loginbtn);
        ForgotLabel = findViewById(R.id.loginforgotlabel);
        txtloginEmail = findViewById(R.id.txtloginemail);
        txtloginPassword = findViewById(R.id.txtloginpswrd);
        googleButton = findViewById(R.id.imageView5);

        cmd_LoginBtn.setOnClickListener(this);
        SignupLabel.setOnClickListener(this);
        googleButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();



    }

    private void signUpIntent() {
        Intent signupIntent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(signupIntent);
        overridePendingTransition(R.anim.righttranslate, R.anim.lefttrslate);
    }


    public void signInExistingUser(View v)   {
        // TODO: Call attemptLogin() here
        logUserIn();

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

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            Intent intent = new Intent(LoginActivity.this, UserDashboardActivity.class);
            finish();
            startActivity(intent);
        }
       return;
    }

    private void showErrorDialog(String message){
        new AlertDialog.Builder(this)
                .setTitle("Error Signing up!!")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(this, "Sign In Failed", Toast.LENGTH_SHORT).show();
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGooogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                        FirebaseUser user = mAuth.getCurrentUser();
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(LoginActivity.this, UserDashboardActivity.class));
                            finish();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageView5:
                signIn();
                break;
            case R.id.loginbtn:
                logUserIn();
                break;
            case R.id.signuplabel:
                signUpIntent();
                break;
                default:
                    return;

        }
    }
}
