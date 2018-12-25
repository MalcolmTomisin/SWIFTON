package com.swifton.swifton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.swifton.swifton.Helpers.AppSingleton;
import com.swifton.swifton.Helpers.NetCheck;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    TextView SignupLabel,ForgotLabel;
    Button cmd_LoginBtn;
    EditText txtloginEmail,txtloginPassword;

    protected String enteredUsername;

    String URL= "http:10.11.32.25/swiftonbe/app/login.php";
    private static final String TAG = "RegisterActivity";

    private static final String TAG_SUCCESS = "success";

    JSONParser jsonParser=new JSONParser();

    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setCancelable(false);

        SignupLabel = findViewById(R.id.signuplabel);
        cmd_LoginBtn = findViewById(R.id.loginbtn);
        ForgotLabel = findViewById(R.id.loginforgotlabel);
        txtloginEmail = findViewById(R.id.txtloginemail);
        txtloginPassword = findViewById(R.id.txtloginpswrd);

        cmd_LoginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String email = txtloginEmail.getText().toString().trim();
                final String paswrd = txtloginPassword.getText().toString().trim();
                final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (NetCheck.isConnectedToInternet(getBaseContext())){

                    if (email.isEmpty() || paswrd.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_LONG).show();
                    } else if (!email.matches(emailPattern)) {
                        Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                    } else if (email.matches(emailPattern) && paswrd.length() >= 6) {
                        loginUser(email, paswrd);
                    } else {
                        Toast.makeText(getApplicationContext(), "Could not login...", Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(LoginActivity.this, "There is no internet connection", Toast.LENGTH_SHORT).show();
                }


            }
        });

        SignupLabel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent signupIntent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(signupIntent);
                overridePendingTransition(R.anim.righttranslate, R.anim.lefttrslate);
            }
        });
    }

    private void loginUser( final String email, final String password) {
        // Tag used to cancel the request
        String cancel_req_tag = "login";
        progressDialog.setMessage("Logging you in...");
        showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.i("tagconvertstr", "["+response+"]");
                hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response);
                    Integer error = jObj.getInt("error");

                    if (error == 1) {
                        //String user = jObj.getJSONObject("response").getString("email");
                        String user = jObj.getString("email");
                        // Launch User activity
                        Intent homeIntent = new Intent(getApplicationContext(), UserDashboardActivity.class);
                        //homeIntent.putExtra("USERNAME", enteredUsername);
                        //homeIntent.putExtra("MESSAGE", "Login Successful!");
                        startActivity(homeIntent);
                        overridePendingTransition(R.anim.righttranslate, R.anim.lefttrslate);
                        //homeIntent.putExtra("email", user);
                        finish();
                    } else {

                        String errorMsg = jObj.getString("error");
                        Toast.makeText(getApplicationContext(), "Unable to login at this time " + errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        // Adding request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq,cancel_req_tag);
    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }
    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
