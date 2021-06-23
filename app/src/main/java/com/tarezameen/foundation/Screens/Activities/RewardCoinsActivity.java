package com.tarezameen.foundation.Screens.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tarezameen.foundation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RewardCoinsActivity extends AppCompatActivity {

    @BindView(R.id.btnDonate)
    Button btnDonate;
    @BindView(R.id.btnRedeem)
    Button btnRedeem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rwward_coins);
        ButterKnife.bind(this);

        ClickListner();

    }

    private void ClickListner() {
        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RewardCoinsActivity.this,DonateActivity.class);
                startActivity(intent);
            }
        });
        btnRedeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RewardCoinsActivity.this, RedeemActivity.class);
                startActivity(intent);
            }
        });
    }
}