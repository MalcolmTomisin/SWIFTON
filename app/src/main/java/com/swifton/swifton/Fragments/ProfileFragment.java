package com.swifton.swifton.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.swifton.swifton.R;
import com.swifton.swifton.UserDashboardActivity;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener {
     ImageView profileImageEdit;
     EditText textName, textPhone, textMail, textBio;
     CircleImageView profileImage;
     Button btnFinish;
     Spinner spinner;
     private SharedPreferences mPreferences;
     private String prefFile =
             "com.skillslevel.swifton.sharedprefs";
     private String PREF_DISPLAY, PREF_EMAIL,PREF_ID, PREF_PHONE, PREF_BIO, PREF_SPINNER ;
     private String personEmail, personName, personId, personPhone, personBio, personState;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    GoogleSignInAccount acct;
    String mCurrentPhotoPath;

    @Override
    public void onStart() {
        super.onStart();
        if (mPreferences != null){
            personEmail = mPreferences.getString(PREF_EMAIL, "");
            personName = mPreferences.getString(PREF_DISPLAY, "");
            personId = mPreferences.getString(PREF_ID, "");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {

            if (mPreferences != null)
            textName.setText(personName);
            textMail.setText(PREF_EMAIL);
        }

    }

    @Override
    public void onPause() {
        super.onPause();

        if (personName != null || personId != null || personEmail != null ) {
            SharedPreferences.Editor prefEditor = mPreferences.edit();
            prefEditor.putString(PREF_DISPLAY, personName);
            prefEditor.putString(PREF_EMAIL, personEmail);
            prefEditor.putString(PREF_ID, personId);

            prefEditor.apply();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profileImage = getActivity().findViewById(R.id.profileDDI);
        profileImageEdit = getActivity().findViewById(R.id.ddiEdit);
        textName = getActivity().findViewById(R.id.fullName);
        textMail = getActivity().findViewById(R.id.mail);
        textPhone = getActivity().findViewById(R.id.phone);
        textBio = getActivity().findViewById(R.id.bio);
        btnFinish = getActivity().findViewById(R.id.btnFinish);
        spinner = getActivity().findViewById(R.id.state_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.states, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {
             personName = acct.getDisplayName();
             personEmail = acct.getEmail();
             personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            textName.setText(personName);
            profileImage.setImageURI(personPhoto);
            textMail.setText(personEmail);

        } else {
            personName = textName.getText().toString().trim();
            personEmail = textMail.getText().toString().trim();
            personPhone = textPhone.getText().toString().trim();
            personBio = textBio.getText().toString().trim();
            personState = spinner.toString();
        }
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.ddiEdit:
                dispatchTakePictureIntent();
                break;
            case R.id.btnFinish:
                setmPreferences();


        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            profileImage.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if ( actionId == EditorInfo.IME_ACTION_SEND || event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                && event.getAction() == KeyEvent.ACTION_DOWN){
            setmPreferences();
            return true;
        }
        return false;
    }

    public void setmPreferences(){
            SharedPreferences.Editor prefEditor = mPreferences.edit();
            if (personName != null)
                prefEditor.putString(PREF_DISPLAY, personName);
            if (personEmail != null)
                prefEditor.putString(PREF_EMAIL, personEmail);
            if (personId != null)
                prefEditor.putString(PREF_ID, personId);
            if (personPhone != null)
                prefEditor.putString(PREF_PHONE, personPhone);
            if (personState != null)
                prefEditor.putString(PREF_SPINNER, personState);
            if (personBio != null)
                prefEditor.putString(PREF_BIO, personBio);

                prefEditor.apply();
        }
    }

