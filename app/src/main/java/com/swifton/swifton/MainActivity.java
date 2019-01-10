package com.swifton.swifton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.swifton.swifton.Helpers.AppSingleton;
import com.swifton.swifton.Helpers.NetCheck;
import com.swifton.swifton.ToolBars.CollapsingToolbarFabs;
import com.swifton.swifton.ToolBars.CollapsingToolbarTabs;
import com.swifton.swifton.ToolBars.ToolbarAndFab;
import com.swifton.swifton.ToolBars.ToolbarTabs;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressDialog progressDialog;
    TextView SignupLabel,ForgotLabel, deviceUDIDS;
    Button LoginBtn,SignupBtn;
    EditText txtloginEmail,txtloginPassword, txtsignupEmail, txtsignupPassword;
    String UDIDS;

    protected String enteredUsername;

   // String LoginURL= "http:10.11.32.28/swiftonbe/app/login_designers.php";
//    String SignupURL = "http:192.168.43.53/swiftonbe/app/create_designers.php";

    //Live testing server url
    String LoginURL = "https://swiftontest.000webhostapp.com/swiftonbe/app/login_designers.php";
    String SignupURL = "https://swiftontest.000webhostapp.com/swiftonbe/app/create_designers.php";

    private static final String TAG = "Requesting...";

    private static final String TAG_SUCCESS = "success";

    JSONParser jsonParser=new JSONParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonToolbarFab = findViewById(R.id.buttonToolbarFab);
        Button buttonLogin = findViewById(R.id.cmdLogin);
        Button buttonSignup = findViewById(R.id.cmdSignup);
        TextView signuptext = findViewById(R.id.designerSignup);
        TextView signintext = findViewById(R.id.designerLogin);


        signuptext.setOnClickListener(this);
        signintext.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);
        buttonSignup.setOnClickListener(this);
        buttonToolbarFab.setOnClickListener(this);
        Button buttonCollapsingToolbarFab = findViewById(R.id.buttonCollapsingToolbarFab);
        buttonCollapsingToolbarFab.setOnClickListener(this);
        Button buttonCollapsingToolbarTabs = findViewById(R.id.buttonCollapsingToolbarTabs);
        buttonCollapsingToolbarTabs.setOnClickListener(this);
        Button buttonToolbarTabs = findViewById(R.id.buttonToolbarTabs);
        buttonToolbarTabs.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        final LayoutInflater mlayoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View signupView = mlayoutInflater.inflate(R.layout.designer_signup_layout, null);
        final PopupWindow mpopupWindow = new PopupWindow(signupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);

        GradientDrawable mdrawable = (GradientDrawable) signupView.getResources().getDrawable(R.drawable.popup_shape);
        mdrawable.setColor(Color.parseColor("#e6000000"));
        signupView.setBackground(mdrawable);

        final LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View loginView = layoutInflater.inflate(R.layout.designer_login_layout, null);
        final PopupWindow popupWindow = new PopupWindow(loginView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);

        GradientDrawable drawable = (GradientDrawable) loginView.getResources().getDrawable(R.drawable.popup_shape);
        drawable.setColor(Color.parseColor("#e6000000"));
        loginView.setBackground(drawable);
        //final PopupWindow popupWindow = new PopupWindow(loginView, ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        deviceUDIDS = signupView.findViewById(R.id.deviceID);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);

        UDIDS = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        deviceUDIDS.setText(UDIDS);

        switch (view.getId()) {
            case R.id.buttonToolbarFab:
                Intent ToolbarAndFabIntent = new Intent(this, ToolbarAndFab.class);
                startActivity(ToolbarAndFabIntent);
                overridePendingTransition(R.anim.righttranslate, R.anim.fade_out);
                break;
            case R.id.buttonCollapsingToolbarFab:
                Intent CollapsingToolbarFabIntent = new Intent(this, CollapsingToolbarFabs.class);
                startActivity(CollapsingToolbarFabIntent);
                overridePendingTransition(R.anim.righttranslate, R.anim.fade_out);
                break;
            case R.id.buttonToolbarTabs:
                Intent ToolbarTabsIntent = new Intent(this, ToolbarTabs.class);
                startActivity(ToolbarTabsIntent);
                overridePendingTransition(R.anim.righttranslate, R.anim.fade_out);
                break;
            case R.id.buttonCollapsingToolbarTabs:
                Intent CollapsingToolbarTabsIntent = new Intent(this, CollapsingToolbarTabs.class);
                startActivity(CollapsingToolbarTabsIntent);
                overridePendingTransition(R.anim.righttranslate, R.anim.fade_out);
                break;
            case R.id.cmdLogin:
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                overridePendingTransition(R.anim.righttranslate, R.anim.fade_out);
                break;
            case R.id.cmdSignup:
                Intent signUpIntent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(signUpIntent);
                overridePendingTransition(R.anim.righttranslate, R.anim.fade_out);
                break;
            case R.id.designerSignup:
                mpopupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                SignupBtn = signupView.findViewById(R.id.dsignupBtn);
                txtsignupEmail = signupView.findViewById(R.id.txtdsemail);
                txtsignupPassword = signupView.findViewById(R.id.txtdspassword);


                SignupBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        final String email = txtsignupEmail.getText().toString().trim();
                        final String password = txtsignupPassword.getText().toString().trim();
                        final String userdevice = deviceUDIDS.getText().toString().trim();
                        //final String request = "createdesigners";
                        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                        if(NetCheck.isConnectedToInternet(getBaseContext())){
                            if (email.isEmpty() || password.isEmpty() || userdevice.isEmpty()) {
                                Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_LONG).show();
                            } else if (!email.matches(emailPattern)) {
                                Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                            } else if (email.matches(emailPattern) && password.length() >= 6) {
                                signupUsers(email, password, userdevice);
                                Toast.makeText(getApplicationContext(), "hello checking signup error "+ userdevice, Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Could not signup...", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "There is no internet connection!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
                break;

            case R.id.designerLogin:
//
                popupWindow.showAtLocation(view,Gravity.CENTER, 0, 0);
                LoginBtn = loginView.findViewById(R.id.dLoginBtn);
                txtloginEmail = loginView.findViewById(R.id.txtdemail);
                txtloginPassword = loginView.findViewById(R.id.txtdpassword);
                LoginBtn.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        final String email = txtloginEmail.getText().toString().trim();
                        final String paswrd = txtloginPassword.getText().toString().trim();
                        //final String request = "logindesigners";
                        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                        if (NetCheck.isConnectedToInternet(getBaseContext())){

                            if (email.isEmpty() || paswrd.isEmpty()) {
                                Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_LONG).show();
                            } else if (!email.matches(emailPattern)) {
                                Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                            } else if (email.matches(emailPattern) && paswrd.length() >= 6) {
                                loginUser(email, paswrd);
                                Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Could not login...", Toast.LENGTH_LONG).show();
                            }

                        }else {
                            Toast.makeText(MainActivity.this, "There is no internet connection", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;

            default:
                Toast.makeText(this, "Wonder Land User!", Toast.LENGTH_LONG).show();
                break;
        }

    }

    private void loginUser( final String email, final String password) {
        // Tag used to cancel the request
        String cancel_req_tag = "login";
        progressDialog.setMessage("Logging you in...");
        showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST, LoginURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.i("tagconvertstr", "["+response+"]");
                hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response);
                    Integer error = jObj.getInt("statuscode");

                    if (error == 1) {
                        //String user = jObj.getJSONObject("data").getString(("email"));
                        String user = jObj.getString("data");
                        // Launch User activity
                        Intent homeIntent = new Intent(getApplicationContext(), DesignersDashboardActivity.class);
                        homeIntent.putExtra("USERNAME", enteredUsername);
                        homeIntent.putExtra("MESSAGE", "Login Successful!");
                        startActivity(homeIntent);
                        overridePendingTransition(R.anim.righttranslate, R.anim.lefttrslate);
                        homeIntent.putExtra("email", user);
                        String errorMsg = jObj.getString("status");
                        Toast.makeText(getApplicationContext(), "Login " + errorMsg, Toast.LENGTH_LONG).show();
                        //finish();
                    } else {

                        String errorMsg = jObj.getString("status");
                        Toast.makeText(getApplicationContext(), "Unable to login at this time " + errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
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
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                //params.put("request", request);
                return params;
            }
        };
        // Adding request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq,cancel_req_tag);
    }
    private void signupUsers(final String email, final String password, final String userdevice) {
        //Tag used to cancel the request
        String cancel_req_tag = "Designer Registration";
        progressDialog.setMessage("Registration in Process...");
        showDialog();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SignupURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                hideDialog();
                Log.i("tagconvertstr", "["+response+"]");
                try {
                    JSONObject jObj = new JSONObject(response);
                    Integer result = jObj.getInt("statuscode");

                    if(result == 1){
                        String message = jObj.getString("status");
                        String user = jObj.getString("data");
                        Toast.makeText(getApplicationContext(), "Hi "+user +" " +message, Toast.LENGTH_LONG).show();
                        Intent mainIntent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }else if(result == 2){
                        String message = jObj.getString("status");
                        //String user = jObj.getString("email");
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }else{
                        String errorMsg = jObj.getString("status");
                        //Toast.makeText(getApplicationContext(), name+ " "+ email, Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "Sorry " + errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (final JSONException e){
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),"Sorry registration failed! 2"+error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                //params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                params.put("deviceuid", userdevice);
                //params.put("phone", phone);
                return params;
            }
        };
        // Adding request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, cancel_req_tag);
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
