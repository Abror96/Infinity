package com.example.kringle.infinity.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.kringle.infinity.R;
import com.example.kringle.infinity.model.User;
import com.example.kringle.infinity.retrofit.ApiClient;
import com.example.kringle.infinity.retrofit.IUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    @BindView(R.id.input_email_reg)
    TextInputEditText input_email_reg;

    @BindView(R.id.input_password_reg)
    TextInputEditText input_password_reg;

    @BindView(R.id.input_name_reg)
    TextInputEditText input_name_reg;

    @BindView(R.id.btn_sign_up)
    Button btn_sign_up;

    private IUser iUser;
    private String name;
    private String email;
    private String password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        // init retrofit
        iUser = ApiClient.getApiClient().create(IUser.class);


        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = input_name_reg.getText().toString().trim();
                email = input_email_reg.getText().toString().trim();
                password = input_password_reg.getText().toString().trim();
                if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    isUserExist();
                } else {
                    MainActivity.prefConfig.displayToast("Please fill all the fields");
                }

            }
        });
    }

    private void isUserExist() {
        Call<User> userCall = iUser.checkUser(email);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getResponse().equals("ok")) {
                    MainActivity.prefConfig.writeLoginStatus(true);
                    Intent intent = new Intent(RegistrationActivity.this, QuestionsActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                } else if (response.body().getResponse().equals("exist")) {
                    MainActivity.prefConfig.displayToast("User is already exist");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("LOGGER Registration", t.getMessage());
            }
        });
    }

}
