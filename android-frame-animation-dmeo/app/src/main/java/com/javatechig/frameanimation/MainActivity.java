package com.javatechig.frameanimation;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity implements OnClickListener {

    private ImageView view;
    private AnimationDrawable frameAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Type casting the Image View
        view = (ImageView) findViewById(R.id.imageView);

        // Setting animation_list.xml as the background of the image view
        view.setBackgroundResource(R.drawable.frame_animation_list);

        // Type casting the Animation drawable
        frameAnimation = (AnimationDrawable) view.getBackground();

        //set true if you want to animate only once
        frameAnimation.setOneShot(true);

        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.stop).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.start){
            frameAnimation.start();
        }else if(id==R.id.stop){
            frameAnimation.stop();
        }
    }
}
