package com.tarezameen.foundation.Screens.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tarezameen.foundation.R;

import butterknife.BindView;

public class TenureCompletionActivity extends AppCompatActivity {

    @BindView(R.id.yesBtn)
    Button yesBtn;
    @BindView(R.id.noBtn)
    Button noBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenure_completion);

        ClickListner();
    }

    private void ClickListner() {

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        
    }
}