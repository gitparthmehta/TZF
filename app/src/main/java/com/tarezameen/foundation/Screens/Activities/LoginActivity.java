package com.tarezameen.foundation.Screens.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.tarezameen.foundation.R;
import com.tarezameen.foundation.Screens.GlobalClasses.Constants;
import com.tarezameen.foundation.Screens.restApi.ApiManager;
import com.tarezameen.foundation.Screens.restApi.ApiResponseInterface;
import com.tarezameen.foundation.Screens.restApi.AppConstant;
import com.tarezameen.foundation.Screens.restApi.Response.BaseReponseBody;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.tarezameen.foundation.Screens.GlobalClasses.Constants.emailPattern;
import static com.tarezameen.foundation.Screens.GlobalClasses.Constants.passwordPattern;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.edt_Email)
    TextInputEditText edt_Email;
    @BindView(R.id.edt_Password)
    TextInputEditText edt_Password;
    @BindView(R.id.btn_Login)
    Button btn_Login;
    @BindView(R.id.txt_Forgotpassword)
    TextView txt_Forgotpassword;
    @BindView(R.id.txt_Signup)
    TextView txt_Signup;
    @BindView(R.id.layoutEmail)
    TextInputLayout layoutEmail;
    @BindView(R.id.layoutPassword)
    TextInputLayout layoutPassword;
    private ApiManager mApiManager;
    private ApiResponseInterface mInterFace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

//        edt_Email.setText("anil@gmail.com");
//        edt_Password.setText("123456");
//        setupNetwork();
        idClickListner();
    }

    private void idClickListner() {
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                makeRegister();
                if (edt_Email.getText().toString().isEmpty()) {
                    layoutEmail.setError(getResources().getString(R.string.emailerror_msg));
                } else if (edt_Password.getText().toString().isEmpty()) {
                    layoutPassword.setError(getResources().getString(R.string.passworderror_msg));
                } else if (!edt_Email.getText().toString().trim().matches(emailPattern)) {
                    layoutEmail.setError(getResources().getString(R.string.notvalidemail_msg));

                }else if (!edt_Password.getText().toString().trim().matches(passwordPattern)) {
                    layoutPassword.setError(getResources().getString(R.string.notvalidpassword_msg));

                } else {

//                    makeRegister();
                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }

            }
        });
        txt_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);

            }
        });
        txt_Forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);

            }
        });
        edt_Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutEmail.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
        edt_Password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutPassword.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_Email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((InputMethodManager) LoginActivity.this.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(LoginActivity.this.edt_Email, 2);
                    LoginActivity.this.edt_Email.setHint(LoginActivity.this.getResources().getString(R.string.emailhint_text));
                }
                else
                    edt_Email.setHint("");
            }
        });
        edt_Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((InputMethodManager) LoginActivity.this.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(LoginActivity.this.edt_Password, 2);
                }
                if (hasFocus)
                    edt_Password.setHint(getResources().getString(R.string.passwordhint_text));
                else
                    edt_Password.setHint("");
            }
        });

    }
    public void makeRegister() {
        if (Constants.checkInternet(LoginActivity.this)) {
            Map<String, String> params = new HashMap<String, String>();
            params.put("email", edt_Email.getText().toString());
            params.put("password", edt_Password.getText().toString());
            mApiManager.makeCommonRequest(params, AppConstant.LOGIN);
        }
    }

    private void setupNetwork() {
        mInterFace = new ApiResponseInterface() {

            @Override
            public void isError(String errorMsg, int errorCode) {
                if (errorCode == AppConstant.ERROR_CODE) {
                    // error from server
                    //showStatusDialog(errorMsg);
                } else if (errorCode == AppConstant.NO_NETWORK_ERROR_CODE) {
                    // show no network screen with refresh button
                    Constants.showNoInternetDialog(LoginActivity.this);
                }
            }

            @Override
            public void isUserDisabled(String errorMsg, int errorCode) {

            }

            @Override
            public void isSuccess(Object response, int ServiceCode) {
                if (ServiceCode == AppConstant.LOGIN) {
                    System.out.println("LOGIN Response:" + String.valueOf(response.toString()));
                    BaseReponseBody res = (BaseReponseBody) response;
                    Toast.makeText(LoginActivity.this, res.getMsg().toString(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                    intent.putExtra("from", "login");
                    startActivity(intent);
                }
            }
        };
        mApiManager = new ApiManager(this, mInterFace);
    }

}