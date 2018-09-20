package com.example.kringle.infinity.activities;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kringle.infinity.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayerActivity extends AppCompatActivity {

    private boolean isPlaying = false;

    @BindView(R.id.play_pause_btn)
    ImageView play_pause_btn;
    AdaptiveTrackSelection adaptive;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ButterKnife.bind(this);

        String url_link = getIntent().getStringExtra("link");
        Uri uri = Uri.parse(url_link);

        mediaPlayer = MediaPlayer.create(this, uri);

        play_pause_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPlaying) {
                    mediaPlayer.start();
                } else {
                    mediaPlayer.pause();
                    play_pause_btn.set
                }

            }
        });

    }
}
