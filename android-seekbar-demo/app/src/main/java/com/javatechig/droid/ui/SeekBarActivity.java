package com.javatechig.droid.ui;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.daimajia.easing.BaseEasingMethod;
import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;
import com.nineoldandroids.animation.AnimatorSet;

public class SeekBarActivity extends Activity {

    @InjectView(R.id.volume_bar)
    SeekBar volumeControl;
    @InjectView(R.id.snap_bar)
    SeekBar snapBarControl;

    private static final int SNAP_MIN = 0;
    private static final int SNAP_MIDDLE = 50;
    private static final int SNAP_MAX = 100;

    private static final int LOWER_HALF = (SNAP_MIN + SNAP_MIDDLE) / 2;
    private static final int UPPER_HALF = (SNAP_MIDDLE + SNAP_MAX) / 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);

        ButterKnife.inject(this);

        setVolumeControlListener();
        setSnapBarControl();
    }

    private void setVolumeControlListener() {
        volumeControl.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int progressChanged = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(SeekBarActivity.this, "seek bar progress: " + progressChanged, Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private void setSnapBarControl() {
        snapBarControl.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(final SeekBar seekBar) {

                final int duration = 750;
                int progress = seekBar.getProgress();
                if (progress >= SNAP_MIN && progress <= LOWER_HALF)
                    setProgressAnimated(seekBar, progress, SNAP_MIN, Skill.ElasticEaseOut, duration);
                if (progress > LOWER_HALF && progress <= UPPER_HALF)
                    setProgressAnimated(seekBar, progress, SNAP_MIDDLE, Skill.ElasticEaseOut, duration);
                if (progress > UPPER_HALF && progress <= SNAP_MAX) {
                    setProgressAnimated(seekBar, progress, SNAP_MAX, Skill.ElasticEaseOut, duration);
                }
            }
        });
    }

    private static void setProgressAnimated(final SeekBar seekBar, int from, int to, Skill skill, final int duration) {
        final AnimatorSet set = new AnimatorSet();
        set.playTogether(Glider.glide(skill, duration, com.nineoldandroids.animation.ValueAnimator.ofInt().ofFloat(from, to), new BaseEasingMethod.EasingListener() {
            @Override
            public void on(float t, float result, float v2, float v3, float v4) {
                seekBar.setProgress((int) result);
            }
        }));
        set.setDuration(duration);
        set.start();
    }

    private static void setProgressAnimatedJdk(final SeekBar seekBar, int from, int to) {
        ValueAnimator anim = ValueAnimator.ofInt(from, to);
        anim.setDuration(100);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animProgress = (Integer) animation.getAnimatedValue();
                seekBar.setProgress(animProgress);
            }
        });
        anim.start();
    }
}
