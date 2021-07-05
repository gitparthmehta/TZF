package com.tarezameen.foundation.Screens.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.tarezameen.foundation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FundraiserActivity extends AppCompatActivity {
    @BindView(R.id.secondBack)
    ImageView secondBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundraiser);
        ButterKnife.bind(this);

        ClickListner();

    }

    private void ClickListner() {
        secondBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FundraiserActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}