package com.tarezameen.foundation.Screens.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tarezameen.foundation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RefferalCodeActivity extends AppCompatActivity {


    @BindView(R.id.sucessfulParticipantLayout)
    RelativeLayout sucessfulParticipantLayout;

    @BindView(R.id.activeParticipantLayout)
    RelativeLayout activeParticipantLayout;

    @BindView(R.id.inactiveParticipantLayout)
    RelativeLayout inactiveParticipantLayout;
    @BindView(R.id.secondBack)
    ImageView secondBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refferal_code);
        ButterKnife.bind(this);

        ClickListners();
    }

    private void ClickListners() {

        secondBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RefferalCodeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        sucessfulParticipantLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RefferalCodeActivity.this, SuccessfulParticipantActivity.class);
                startActivity(intent);
            }
        });
        inactiveParticipantLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RefferalCodeActivity.this, InActiveParticipantActivity.class);
                startActivity(intent);
            }
        });
        activeParticipantLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RefferalCodeActivity.this, ActiveParticipantActivity.class);
                startActivity(intent);
            }
        });


    }
}