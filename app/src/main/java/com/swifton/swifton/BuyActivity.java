package com.swifton.swifton;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.swifton.swifton.Adpaters.LatestDesignsAdapter;

import java.util.ArrayList;

public class BuyActivity extends AppCompatActivity {
    ArrayList<LatestDesignsAdapter> getList;
    ImageView liked;
    Button btnBagain, btnBuy, btnAddToCart;
    FloatingActionButton cartNotify;
    EditText textBagain;
    TextInputLayout tilBagain;
    TextView txtTitle, txtDescription;
    int like = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        liked = findViewById(R.id.buyLike);
        btnBagain = findViewById(R.id.buyBagain);
        btnBuy = findViewById(R.id.buyBuy);
        btnAddToCart = findViewById(R.id.buyAddtoCart);
        textBagain = findViewById(R.id.txtBagain);
        cartNotify = findViewById(R.id.fabCart);
        tilBagain = findViewById(R.id.bagainCont);
        txtTitle =findViewById(R.id.buyTitle);
        txtDescription = findViewById(R.id.buyDesc);

        getList = new ArrayList<LatestDesignsAdapter>();
        //txtTitle.setText();

        btnBagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tilBagain.isShown()){
                    tilBagain.setVisibility(View.INVISIBLE);
                }else{
                    tilBagain.setVisibility(View.VISIBLE);
                }

            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                if(cartNotify.isOrWillBeHidden()) {
                    cartNotify.setVisibility(View.VISIBLE);
                }else{
                    cartNotify.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void likeClicked(View v) {

        if(like == 0){
            liked.setColorFilter(Color.RED);
            Toast.makeText(this, "Liked!", Toast.LENGTH_LONG).show();
            like=1;
        }else {
            liked.setColorFilter(Color.WHITE);
            like = 0;
        }
    }
}
