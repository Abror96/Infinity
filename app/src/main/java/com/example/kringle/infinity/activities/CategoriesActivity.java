package com.example.kringle.infinity.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kringle.infinity.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_logout)
    TextView btn_logout;

    @BindView(R.id.tv_sad)
    Button tv_sad;

    @BindView(R.id.tv_upset)
    Button tv_upset;

    @BindView(R.id.tv_usual)
    Button tv_usual;

    @BindView(R.id.tv_aerial)
    Button tv_aerial;

    @BindView(R.id.tv_angry)
    Button tv_angry;

    @BindView(R.id.tv_decisive)
    Button tv_decisive;

    @BindView(R.id.tv_happy)
    Button tv_happy;

    Intent intent;

    String[] musics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);

        tv_sad.setOnClickListener(this);
        tv_upset.setOnClickListener(this);
        tv_usual.setOnClickListener(this);
        tv_aerial.setOnClickListener(this);
        tv_angry.setOnClickListener(this);
        tv_decisive.setOnClickListener(this);
        tv_happy.setOnClickListener(this);

        musics = new String[]{"http://imeditating.ru/api/music/1",
                "http://imeditating.ru/api/music/2",
                "http://imeditating.ru/api/music/3",
                "http://imeditating.ru/api/music/4",
                "http://imeditating.ru/api/music/5",
                "http://imeditating.ru/api/music/6",
                "http://imeditating.ru/api/music/7"};


        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.prefConfig.writeLoginStatus(false);
                Intent intent = new Intent(CategoriesActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.tv_sad:
                intent = new Intent(CategoriesActivity.this, PlayerActivity.class);
                intent.putExtra("link", musics[0]);
                break;
            case R.id.tv_upset:
                intent = new Intent(CategoriesActivity.this, PlayerActivity.class);
                intent.putExtra("link", musics[1]);
                break;
            case R.id.tv_usual:
                intent = new Intent(CategoriesActivity.this, PlayerActivity.class);
                intent.putExtra("link", musics[2]);
                break;
            case R.id.tv_aerial:
                intent = new Intent(CategoriesActivity.this, PlayerActivity.class);
                intent.putExtra("link", musics[3]);
                break;
            case R.id.tv_angry:
                intent = new Intent(CategoriesActivity.this, PlayerActivity.class);
                intent.putExtra("link", musics[4]);
                break;
            case R.id.tv_decisive:
                intent = new Intent(CategoriesActivity.this, PlayerActivity.class);
                intent.putExtra("link", musics[5]);
                break;
            case R.id.tv_happy:
                intent = new Intent(CategoriesActivity.this, PlayerActivity.class);
                intent.putExtra("link", musics[6]);
                break;

        }

        startActivity(intent);
    }
}
