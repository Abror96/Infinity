package com.example.kringle.infinity.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kringle.infinity.R;
import com.example.kringle.infinity.sharedPrefs.PrefConfig;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.sign_in_main)
    Button sign_in_main;

    @BindView(R.id.sign_up_main)
    Button sign_up_main;

    public static PrefConfig prefConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        sign_in_main.setOnClickListener(this);
        sign_up_main.setOnClickListener(this);

        prefConfig = new PrefConfig(this);
        if (savedInstanceState != null) {
            return;
        }

        // if user is already logged in
        if (prefConfig.readLoginStatus()) {
            Intent category_intent = new Intent(MainActivity.this, CategoriesActivity.class);
            startActivity(category_intent);
            finish();
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_main:
                Intent intent_login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent_login);
                break;
            case R.id.sign_up_main:
                Intent intent_reg = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent_reg);
                break;
        }
    }
}
