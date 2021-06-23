package com.tarezameen.foundation.Screens.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.tarezameen.foundation.R;
import com.tarezameen.foundation.Screens.fragments.HomeFragment;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    private static FragmentManager fragmentManager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.referalLayout)
    LinearLayout ReferalLayout;

    @BindView(R.id.rewardLayout)
    LinearLayout rewardLayout;

    @BindView(R.id.edtUserName)
    TextView edtUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        fragmentManager = getSupportFragmentManager();

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        GetDefault();
        ClickHandle();
    }

    private void ClickHandle() {
        ReferalLayout.setOnClickListener(this);
        edtUserName.setOnClickListener(this);
        rewardLayout.setOnClickListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        return false;
    }

    private void GetDefault() {

        Fragment fragment = null;

        fragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_framelayout, fragment);
        fragmentTransaction.commit();

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
        System.exit(0);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.referalLayout:
                Intent i1 = new Intent(HomeActivity.this, RefferalCodeActivity.class);
                startActivity(i1);
                break;
            case R.id.edtUserName:
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.rewardLayout:
                Intent reward = new Intent(HomeActivity.this, RewardCoinsActivity.class);
                startActivity(reward);
                break;
        }
    }
}