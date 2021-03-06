package com.tarezameen.foundation.Screens.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputEditText;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.tarezameen.foundation.R;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.img_Profile)
    ImageView img_Profile;

    @BindView(R.id.firstBack)
    ImageView firstBack;

    @BindView(R.id.secondBack)
    ImageView secondBack;
    @BindView(R.id.firstLayout)
    LinearLayout firstLayout;
    @BindView(R.id.secondLyaout)
    LinearLayout secondLyaout;

    @BindView(R.id.thirdLayout)
    LinearLayout thirdLayout;

    @BindView(R.id.btnNext1)
    Button btnNext1;
    @BindView(R.id.btnNext2)
    Button btnNext2;

    private static final int SELECT_PICTURE = 1;

    @BindView(R.id.edtDateOfBirth)
    TextInputEditText edtDateOfBirth;

    @BindView(R.id.edtEndDate)
    TextInputEditText edtEndDate;
    @BindView(R.id.edtStartDate)
    TextInputEditText edtStartDate;
    private String selectedImagePath;
    int datePickerInput;

    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        clickListner();
        edtStartDate.setText(new SimpleDateFormat("MM/dd/yyyy").format(new Date()));

    }

    private void requestPermission() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
//                            init();
                            selectImage();
//                            updateLocationUI();

//                            Toast.makeText(getApplicationContext(), "All permissions are granted!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_PICTURE);
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivityForResult(intent, 101);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }


    private void clickListner() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        this.edtDateOfBirth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    ((InputMethodManager) ProfileActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(ProfileActivity.this.getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                }
                Calendar c = Calendar.getInstance();
                new SpinnerDatePickerDialogBuilder().context(ProfileActivity.this).callback(ProfileActivity.this).spinnerTheme(R.style.NumberPickerStyle).showTitle(true).showDaySpinner(true).defaultDate(c.get(1), c.get(2), c.get(5)).build().show();
            }
        });
        this.edtEndDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int unused = ProfileActivity.this.datePickerInput = v.getId();
                try {
                    ((InputMethodManager) ProfileActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(ProfileActivity.this.getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                }
                Calendar c = Calendar.getInstance();
                new SpinnerDatePickerDialogBuilder().context(ProfileActivity.this).callback(ProfileActivity.this).spinnerTheme(R.style.NumberPickerStyle).showTitle(true).showDaySpinner(true).defaultDate(c.get(1), c.get(2), c.get(5)).build().show();
            }
        });
        this.edtDateOfBirth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                datePickerInput = v.getId();
                try {
                    ((InputMethodManager) ProfileActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(ProfileActivity.this.getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                }
                Calendar c = Calendar.getInstance();
                new SpinnerDatePickerDialogBuilder().context(ProfileActivity.this).callback(ProfileActivity.this).spinnerTheme(R.style.NumberPickerStyle).showTitle(true).showDaySpinner(true).defaultDate(c.get(1), c.get(2), c.get(5)).build().show();
            }
        });
        img_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                requestPermission();

            }
        });

        btnNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                secondLyaout.setVisibility(View.VISIBLE);
                firstLayout.setVisibility(View.GONE);
            }
        });
        firstBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                thirdLayout.setVisibility(View.GONE);
                secondLyaout.setVisibility(View.GONE);
                firstLayout.setVisibility(View.VISIBLE);
            }
        });
        secondBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                thirdLayout.setVisibility(View.GONE);
                secondLyaout.setVisibility(View.VISIBLE);
                firstLayout.setVisibility(View.GONE);
            }
        });

        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                thirdLayout.setVisibility(View.VISIBLE);
                secondLyaout.setVisibility(View.GONE);
                firstLayout.setVisibility(View.GONE);
            }
        });


    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(
                        selectedImageUri, filePathColumn, null, null, null);
                cursor.moveToFirst();
                selectedImagePath = getPath(selectedImageUri);
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String filePath = cursor.getString(columnIndex);
                cursor.close();

                Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
            }
        }
    }

    public String getPath(Uri uri) {
        // just some safety built in
        if (uri == null) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        // this is our fallback here
        return uri.getPath();
    }


    @Override
    public void onDateSet(com.tsongkha.spinnerdatepicker.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy,MM,dd").parse(String.valueOf(year) + "," + String.valueOf(monthOfYear + 1) + "," + String.valueOf(dayOfMonth));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String finalDate = new SimpleDateFormat("MM/dd/yyyy").format(date);
        switch (this.datePickerInput) {
            case R.id.edtDateOfBirth:
                this.edtDateOfBirth.setText(finalDate);
                return;
            case R.id.edtEndDate:
                this.edtEndDate.setText(finalDate);
                return;
            default:
                return;
        }
    }
}