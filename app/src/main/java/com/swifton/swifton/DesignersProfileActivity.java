package com.swifton.swifton;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.swifton.swifton.Helpers.AppSingleton;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;
import java.util.Map;

import static com.swifton.swifton.Helpers.ImageFilePath.getPath;

public class DesignersProfileActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST =1;
    private Uri filepath;
    EditText companyname, companyregno, companyaddress, country, companystate, companycity, emailaddress, companyphone, website, companyzipcode;
    Button cmdDone;
    ImageView companyLogo, editcompLogo;

    ProgressDialog progressDialog;

    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 2;

    //String URL= "http:192.168.43.53/swiftonbe/app/create_company.php";

    //Live testing server url
    String URL = "https://swiftontest.000webhostapp.com/swiftonbe/app/create_company.php";

    private static final String TAG = "RegisterActivity";

    private static final String TAG_SUCCESS = "success";

    JSONParser jsonParser=new JSONParser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designers_profile);

        // Progress dialog
        progressDialog = new ProgressDialog(DesignersProfileActivity.this);
        progressDialog.setCancelable(false);

        companyname = findViewById(R.id.editcomname);
        companyregno = findViewById(R.id.editcomregno);
        emailaddress = findViewById(R.id.editcomemail);
        companyphone = findViewById(R.id.editcomphone);
        companyzipcode = findViewById(R.id.editcomzipcode);
        companyaddress = findViewById(R.id.editcomaddress);
        companycity = findViewById(R.id.editcomcity);
        companystate = findViewById(R.id.editcomstate);
        country = findViewById(R.id.editcomcountry);
        website = findViewById(R.id.editcomwebsite);
        companyLogo = findViewById(R.id.comprofileLogo);
        editcompLogo = findViewById(R.id.editcomplogobtn);
        cmdDone = findViewById(R.id.editprofileBTN);


        cmdDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String compname = companyname.getText().toString().trim();
                final String compregno = companyregno.getText().toString().trim();
                final String compemail = emailaddress.getText().toString().trim();
                final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                final String compphone = companyphone.getText().toString().trim();
                final String comopzip = companyzipcode.getText().toString().trim();
                final String compaddress = companyaddress.getText().toString().trim();
                final String compcity = companycity.getText().toString().trim();
                final String compstate = companystate.getText().toString().trim();
                final String compcountry = country.getText().toString().trim();
                final String compwebsite = website.getText().toString().trim();
                final Bitmap complogo  = ((BitmapDrawable)companyLogo.getDrawable()).getBitmap();

                if(compname.isEmpty() || compemail.isEmpty() || compphone.isEmpty() || compaddress.isEmpty() || !compemail.matches(emailPattern)){
                    Toast.makeText(getApplicationContext(), "Please make sure you enter all fields correctly!", Toast.LENGTH_LONG).show();
                }else if(!compname.isEmpty() && !compemail.isEmpty() && !compaddress.isEmpty() && !compphone.isEmpty() && compemail.matches(emailPattern)){

                    //Call the profile creation method
                    createCompanyProfile(compname, compregno, compemail, compphone,
                            comopzip, compaddress, compcity, compstate,
                            compcountry, compwebsite, complogo);
                    Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Cannot create the company profile at the moment", Toast.LENGTH_LONG).show();
                }
            }
        });

        editcompLogo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(checkPermissionREAD_EXTERNAL_STORAGE(DesignersProfileActivity.this)) {
                    //Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    Intent galleryIntent = new Intent();
                    galleryIntent.setType("image/*");
                    galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(galleryIntent, "Select Company Logo"), PICK_IMAGE_REQUEST);
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null){
            Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // Get the path from the Uri
                    Toast.makeText(this, "image is gotten!", Toast.LENGTH_SHORT).show();
                    String path =  getPath(this,selectedImageUri);
                    Log.i(TAG, "Image Path : " + path);
                    companyLogo.setImageURI(selectedImageUri);
                    // Set the image in ImageView
                    //Bitmap bitmap = BitmapFactory.decodeFile(path);
                    //companyLogo.setImageBitmap(bitmap);
                }
        }
    }

    public boolean checkPermissionREAD_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context,
                            Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }


    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[] { permission },
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // do your stuff
                } else {
                    Toast.makeText(this, "GET_ACCOUNTS Denied",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions,
                        grantResults);
        }
    }

    private void createCompanyProfile(final String compname, final String compregno, final String compemail, final String compphone,
                              final String comopzip, final String compaddress, final String compcity, final String compstate,
                              final String compcountry, final String compwebsite, final Bitmap complogo) {
        //Tag used to cancel the request
        String cancel_req_tag = "signup";
        progressDialog.setMessage("Registering you...");
        showDialog();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                hideDialog();
                Log.i("tagconvertstr", "["+response+"]");
                try {
                    JSONObject jObj = new JSONObject(response);
                    Integer result = jObj.getInt("statuscode");

                    if(result == 1){
                        String message = jObj.getString("status");
                        //String user = jObj.getString("email");
                        Toast.makeText(getApplicationContext(), "Hi " +message, Toast.LENGTH_LONG).show();
                        Intent mainIntent = new Intent(DesignersProfileActivity.this, DesignersDashboardActivity.class);
                        startActivity(mainIntent);
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        finish();
                    }else if(result == 2){
                        String message = jObj.getString("status");
                        //String user = jObj.getString("email");
                        Toast.makeText(getApplicationContext(), "Hi " +message, Toast.LENGTH_LONG).show();
                    }else{
                        String errorMsg = jObj.getString("status");
                        //Toast.makeText(getApplicationContext(), name+ " "+ email, Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "Sorry " + errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (final JSONException e){
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(DesignersProfileActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),"Sorry registration failed! "+error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("compname", compname);
                params.put("compregno", compregno);
                params.put("compemail", compemail);
                params.put("compphone", compphone);
                params.put("compzip", comopzip);
                params.put("compaddress", compaddress);
                params.put("compcity", compcity);
                params.put("compstate", compstate);
                params.put("compcountry", compcountry);
                params.put("compwebsite", compwebsite);
                params.put("complogo", String.valueOf(complogo));
                return params;
            }
        };
        // Adding request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, cancel_req_tag);
    }

    private void showDialog(){
        if (!progressDialog.isShowing())
            progressDialog.show();
    }
    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

}
