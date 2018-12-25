package com.swifton.swifton;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.swifton.swifton.ToolBars.CollapsingToolbarFabs;
import com.swifton.swifton.ToolBars.CollapsingToolbarTabs;
import com.swifton.swifton.ToolBars.ToolbarAndFab;
import com.swifton.swifton.ToolBars.ToolbarTabs;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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

        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View loginView = layoutInflater.inflate(R.layout.designer_login_layout, null);
        //final PopupWindow popupWindow = new PopupWindow(loginView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        GradientDrawable drawable = (GradientDrawable) loginView.getResources().getDrawable(R.drawable.popup_shape);
        drawable.setColor(Color.parseColor("#d8450078"));
        loginView.setBackground(drawable);
        final PopupWindow popupWindow = new PopupWindow(loginView, ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);

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

                break;

            case R.id.designerLogin:
//                Intent designerIntent = new Intent(MainActivity.this, DesignersDashboardActivity.class);
//                startActivity(designerIntent);
//                overridePendingTransition(R.anim.righttranslate, R.anim.lefttrslate);
//                finish();

                popupWindow.showAtLocation(view,Gravity.CENTER, 0, 0);
                break;

            default:
                Toast.makeText(this, "Wonder Land User!", Toast.LENGTH_LONG).show();
                break;
        }

    }
}
