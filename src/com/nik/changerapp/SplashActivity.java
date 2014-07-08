package com.nik.changerapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Splash Activity.
 * 
 * @author Niket K
 */

public class SplashActivity extends Activity
{
	/* XML layout declarations */
	private TextView mTextViewTitle;
	private ImageView mImageViewLine;
	private ImageView mImageViewRound;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		/* Start - Finding XML layout views */
		mTextViewTitle = (TextView) findViewById(R.id.text_view_title);
		mImageViewLine = (ImageView) findViewById(R.id.image_view_line);
		mImageViewRound = (ImageView) findViewById(R.id.image_view_round);
		/* End - Finding XML layout views */

		mTextViewTitle.setTypeface(Typeface.createFromAsset(getAssets(), "font/tw_cen_mt.TTF"));
		mImageViewLine.setVisibility(View.GONE);
		mImageViewRound.setVisibility(View.GONE);

		new CountDownTimer(1000, 1000)
		{
			@Override
			public void onTick(long millisUntilFinished)
			{
			}

			@Override
			public void onFinish()
			{
				TranslateAnimation translateAnimation = new TranslateAnimation(-(getResources().getDisplayMetrics().widthPixels), 1.0f, 0.0f, 0.0f);

				AnimationSet animationSet = new AnimationSet(true);
				animationSet.addAnimation(translateAnimation);
				animationSet.setDuration(250);
				animationSet.setFillAfter(true);

				mImageViewLine.setAnimation(animationSet);
				mImageViewLine.setVisibility(View.VISIBLE);
				animationSet.start();

				mImageViewRound.setVisibility(View.VISIBLE);
				Animation fadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
				mImageViewRound.startAnimation(fadeInAnimation);

				fadeInAnimation.setAnimationListener(new AnimationListener()
				{
					@Override
					public void onAnimationStart(Animation animation)
					{
					}

					@Override
					public void onAnimationRepeat(Animation animation)
					{
					}

					@Override
					public void onAnimationEnd(Animation animation)
					{
						Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
						startActivity(intent);
						SplashActivity.this.finish();
						overridePendingTransition(R.anim.fade_in, 0);
					}
				});
			}
		}.start();
	}
}
