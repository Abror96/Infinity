package com.example.kringle.infinity.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kringle.infinity.R;
import com.example.kringle.infinity.model.User;
import com.example.kringle.infinity.retrofit.ApiClient;
import com.example.kringle.infinity.retrofit.IUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.input_email_auth)
    TextInputEditText input_email_auth;

    @BindView(R.id.input_password_auth)
    TextInputEditText input_password_auth;

     @BindView(R.id.btn_sign_in)
    Button btn_sign_in;

    private IUser iUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        // init retrofit
        iUser = ApiClient.getApiClient().create(IUser.class);

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

    }

    private void performLogin() {
        String email = input_email_auth.getText().toString().trim();
        String password = input_password_auth.getText().toString().trim();

        Call<User> userCall = iUser.performUserLogin(
                email,
                password
        );

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok")) {
                    MainActivity.prefConfig.writeLoginStatus(true);
                    Intent intent = new Intent(LoginActivity.this, CategoriesActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    MainActivity.prefConfig.displayToast("The email and password you entered did not match our records.");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("LOGGER Login", t.getMessage());
            }
        });
    }
}
