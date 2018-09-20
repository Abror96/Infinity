package com.example.kringle.infinity.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kringle.infinity.R;
import com.example.kringle.infinity.model.User;
import com.example.kringle.infinity.retrofit.ApiClient;
import com.example.kringle.infinity.retrofit.IUser;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_question)
    TextView tv_question;

    @BindView(R.id.btn_yes)
    Button btn_yes;

    @BindView(R.id.btn_no)
    Button btn_no;

    @BindView(R.id.number_picker)
    NumberPicker numberPicker;

    @BindView(R.id.nextButton)
    Button btn_next;

    @BindView(R.id.answer_container)
    LinearLayout answer_container;

    private IUser iUser;

    private String picked_number = "0";
    private String name;
    private String email;
    private String password;
    private String[] answers;
    private int question_number = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        ButterKnife.bind(this);

        // init retrofit
        iUser = ApiClient.getApiClient().create(IUser.class);

        // data from prev activity to save in DB
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");

        // setting question
        tv_question.setText("Question 1");

        answers = new String[3];

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                picked_number = String.valueOf(newVal);
            }
        });

        btn_no.setOnClickListener(this);
        btn_yes.setOnClickListener(this);
        btn_next.setOnClickListener(this);

    }

    public void performRegistration() {

        Call<User> userCall = iUser.performRegistration(
                name,
                email,
                password,
                answers[0],
                answers[1],
                answers[2]
        );

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getResponse().equals("ok")) {
                    MainActivity.prefConfig.displayToast("Registration success");

                    Intent intent = new Intent(QuestionsActivity.this, CategoriesActivity.class);
                    startActivity(intent);
                    finish();

                } else if (response.body().getResponse().equals("error")){
                    MainActivity.prefConfig.displayToast("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("LOGGER Registration", t.getMessage());
            }
        });

    }

    public void nextQuestionCall(String answer) {
        switch (question_number) {


            case 0:
                tv_question.setText("Question 2");
                break;
            case 1:
                tv_question.setText("Question 3");
                btn_next.setVisibility(View.VISIBLE);
                numberPicker.setVisibility(View.VISIBLE);
                answer_container.setVisibility(View.GONE);
                break;
        }

        answers[question_number] = answer;
        question_number++;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_yes:
                nextQuestionCall("yes");
                break;
            case R.id.btn_no:
                nextQuestionCall("no");
                break;
            case R.id.nextButton:
                answers[question_number] = picked_number;
                performRegistration();
        }
    }
}
