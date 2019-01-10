package com.swifton.swifton.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.swifton.swifton.DesignersProfileActivity;
import com.swifton.swifton.R;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class DesignerProfileFragment extends Fragment {
    FloatingActionButton editFab;
    TextView id, username, userpassword, companyname, companyregno,
    companyaddress, country, companystate, companycity, emailaddress, phone, website, zipcode,
    logo, companycode, approvalstatus, verified,level, created_at;

    public DesignerProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_designer_profile, container, false);

        editFab = view.findViewById(R.id.editProfileFAB);

        editFab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent editprofileIntent = new Intent(getActivity(), DesignersProfileActivity.class);
                startActivity(editprofileIntent);
                Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        });
        return view;
    }

}
