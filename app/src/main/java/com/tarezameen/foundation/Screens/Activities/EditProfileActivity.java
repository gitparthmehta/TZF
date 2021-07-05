package com.tarezameen.foundation.Screens.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.tarezameen.foundation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditProfileActivity extends AppCompatActivity {

    @BindView(R.id.edtMobile)
    TextInputEditText edtMobile;

    @BindView(R.id.layoutMobile)
    TextInputLayout layoutMobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);

        init();

    }

    private void init() {
        edtMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutMobile.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

        edtMobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((InputMethodManager) EditProfileActivity.this.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(EditProfileActivity.this.edtMobile, 2);
                    EditProfileActivity.this.edtMobile.setHint(EditProfileActivity.this.getResources().getString(R.string.mobile));
                }
                else
                    edtMobile.setHint("");
            }
        });
    }
}