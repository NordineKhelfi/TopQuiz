package com.example.norma.topquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView iv_mosquees = (ImageView) findViewById(R.id.ivMosques);
        ImageView iv_quran = (ImageView) findViewById(R.id.iv_quran);
        final TextView tv_TopQuiz = (TextView) findViewById(R.id.tv_TopQuiz);

        Animation down_to_up = AnimationUtils.loadAnimation(this, R.anim.down_to_up);
        Animation up_to_down = AnimationUtils.loadAnimation(this, R.anim.up_to_down);
        final Animation fade_in = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        down_to_up.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tv_TopQuiz.startAnimation(fade_in);
                tv_TopQuiz.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        iv_mosquees.startAnimation(down_to_up);
        iv_quran.startAnimation(up_to_down);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(SplashScreenActivity.this, MainMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        thread.start();

    }
}
