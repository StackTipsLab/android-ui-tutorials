package com.javatechig.viewflipper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;

public class ViewFlipperSampleActivity extends Activity {

	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private ViewFlipper mViewFlipper;	
	private AnimationListener mAnimationListener;
	private Context mContext;
	
	
	@SuppressWarnings("deprecation")
	private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mContext = this;
		mViewFlipper = (ViewFlipper) this.findViewById(R.id.view_flipper);
		mViewFlipper.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(final View view, final MotionEvent event) {
				detector.onTouchEvent(event);
				return true;
			}
		});
		
		
		findViewById(R.id.play).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				//sets auto flipping
				mViewFlipper.setAutoStart(true);
				mViewFlipper.setFlipInterval(4000);
				mViewFlipper.startFlipping();
			}
		});

		findViewById(R.id.stop).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				//stop auto flipping 
				mViewFlipper.stopFlipping();
			}
		});
		
		
		//animation listener
		mAnimationListener = new Animation.AnimationListener() {
			public void onAnimationStart(Animation animation) {
				//animation started event
			}

			public void onAnimationRepeat(Animation animation) {
			}

			public void onAnimationEnd(Animation animation) {
				//TODO animation stopped event
			}
		};
	}


	class SwipeGestureDetector extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			try {
				// right to left swipe
				if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_in));
					mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));
					// controlling animation
					mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
					mViewFlipper.showNext();
					return true;
				} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
					mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext,R.anim.right_out));
					// controlling animation
					mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
					mViewFlipper.showPrevious();
					return true;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			return false;
		}
	}
}


