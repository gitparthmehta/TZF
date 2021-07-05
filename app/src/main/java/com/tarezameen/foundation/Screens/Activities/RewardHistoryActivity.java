package com.tarezameen.foundation.Screens.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.tarezameen.foundation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RewardHistoryActivity extends AppCompatActivity {

    @BindView(R.id.secondBack)
    ImageView secondBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_history);

        ButterKnife.bind(this);
        ClickListner();

    }

    private void ClickListner() {
        secondBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RewardHistoryActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}