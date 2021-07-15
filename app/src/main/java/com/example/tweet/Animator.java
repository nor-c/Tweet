package com.example.tweet;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

public class Animator {
    private static  Animator instance;

    private Animator() {}

    public static Animator getInstance() {
        if(instance == null) instance = new Animator();

        return instance;
    }

    public Animation rotateRight(View view) {
        RotateAnimation anim = new RotateAnimation(0f, 350f, 15f, 15f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.ABSOLUTE);
        anim.setDuration(700);

        view.startAnimation(anim);

        return anim;
    }
}
