package com.stacktips.youtube;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.youtube.player.YouTubePlayerFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.basic_player_example).setOnClickListener(this);
        findViewById(R.id.minimal_player_example).setOnClickListener(this);
        findViewById(R.id.custom_player_control_example).setOnClickListener(this);
        findViewById(R.id.player_fragment_example).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.basic_player_example:
                startActivity(new Intent(this, BasicPlayerActivity.class));
                break;
            case R.id.minimal_player_example:
                startActivity(new Intent(this, MinimalPlayerActivity.class));
                break;
            case R.id.custom_player_control_example:
                startActivity(new Intent(this, CustomPlayerControlActivity.class));
                break;
            case R.id.player_fragment_example:
                startActivity(new Intent(this, YouTubePlayerFragmentActivity.class));
                break;

        }
    }
}