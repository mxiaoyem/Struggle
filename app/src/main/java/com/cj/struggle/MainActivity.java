package com.cj.struggle;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    private MyView myView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        myView = (MyView) findViewById(R.id.myView);

        ObjectAnimator objectAnimator1 = ObjectAnimator.ofInt(myView, "degressY", 0, -40);
        objectAnimator1.setDuration(800);


        ObjectAnimator objectAnimator2 = ObjectAnimator.ofInt(myView, "degressRotate", 0, 270);
        objectAnimator2.setDuration(1500);

        ObjectAnimator objectAnimator3 = ObjectAnimator.ofInt(myView, "endDegressY", 0, 30);
        objectAnimator3.setDuration(800);

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(objectAnimator1, objectAnimator2, objectAnimator3);
        animatorSet.start();


        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                myView.reset();
                                animatorSet.start();
                            }
                        });
                    }
                }, 2000);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });


    }
}
