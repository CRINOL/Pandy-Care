package com.technet.pandy;

import android.content.Context;
import android.util.Log;
import android.view.*;
import android.view.animation.*;

public class AnimationHelper{

    View TARGET_VIEW = null;
    AnimationUtils HELPER_UTILS = null;
    Animation TARGET_ANIMATION_1= null;
    Animation TARGET_ANIMATION_2 = null;
    Context CTX = null;
    AlphaAnimation fadeIn;
    AlphaAnimation fadeOut;
    int anim = 0;
    int ret_anim = 0;
    int bounce_flip = 0;
    public AnimationHelper(View TARGET_VIEW, Context CTX,int anim){
        this.CTX = CTX;
        this.TARGET_VIEW = TARGET_VIEW;
        this.anim = anim;
    }

    public AnimationHelper(View TARGET_VIEW, Context CTX){
        this.CTX = CTX;
        this.TARGET_VIEW = TARGET_VIEW;
    }

    public void loadBouncy(){
        fadeIn = new AlphaAnimation(0.0f , 1.0f ) ;
        fadeOut = new AlphaAnimation( 1.0f , 0.0f ) ;
        TARGET_VIEW.startAnimation(fadeIn);
        TARGET_VIEW.startAnimation(fadeOut);
        fadeIn.setDuration(1200);
        fadeOut.setDuration(1200);
        fadeOut.setStartOffset(1200+fadeIn.getStartOffset()+1200);
        fadeOut.setRepeatCount(Animation.INFINITE);
    }

    public void fadeIn(){
        fadeIn = new AlphaAnimation(0.0f , 1.0f );
        TARGET_VIEW.startAnimation(fadeIn);
        fadeIn.setDuration(1200);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                TARGET_VIEW.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void fadeOut(){
        fadeOut = new AlphaAnimation(1.0f , 0.0f );
        TARGET_VIEW.startAnimation(fadeOut);
        fadeOut.setDuration(1200);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                TARGET_VIEW.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void outLeft(){
        Animation from_right = AnimationUtils.loadAnimation(CTX,R.anim.slide_out_left);
        TARGET_VIEW.startAnimation(from_right);
        from_right.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                TARGET_VIEW.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void inLeft(){
        Animation from_right = AnimationUtils.loadAnimation(CTX,R.anim.slide_in_left);
        TARGET_VIEW.startAnimation(from_right);
        from_right.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                TARGET_VIEW.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void outRight(){
        Animation to_right = AnimationUtils.loadAnimation(CTX,R.anim.slide_out_right);
        TARGET_VIEW.startAnimation(to_right);
        to_right.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                TARGET_VIEW.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void inRight(){
        Animation from_right = AnimationUtils.loadAnimation(CTX,R.anim.slide_in_right);
        TARGET_VIEW.startAnimation(from_right);
        from_right.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                TARGET_VIEW.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void zoomOut(){
        Animation from_right = AnimationUtils.loadAnimation(CTX,R.anim.view_out);
        TARGET_VIEW.startAnimation(from_right);
        from_right.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                TARGET_VIEW.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void zoomIn(){
        Animation from_right = AnimationUtils.loadAnimation(CTX,R.anim.view_in);
        TARGET_VIEW.startAnimation(from_right);
        from_right.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                TARGET_VIEW.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void killBouncy(){
        fadeIn.cancel();
        fadeOut.cancel();
        TARGET_VIEW.setAlpha(1f);
    }
}