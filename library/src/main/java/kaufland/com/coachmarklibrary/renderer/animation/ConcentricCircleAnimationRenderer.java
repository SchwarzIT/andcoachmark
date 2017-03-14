package kaufland.com.coachmarklibrary.renderer.animation;


import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.ViewGroup;

import kaufland.com.coachmarklibrary.CoachmarkView;
import kaufland.com.coachmarklibrary.renderer.circle.CircleView;

public class ConcentricCircleAnimationRenderer implements AnimationRenderer {


    @Override
    public void animate(final CoachmarkView coachmarkView) {

        final CircleView wholeScreenCircle = coachmarkView.getCircleView();

        int startWidth = coachmarkView.getMeasuredWidth();
        int startHeight = coachmarkView.getMeasuredHeight();
        int targetWidth = (int) coachmarkView.calcCircleRectF().width();
        int targetHeight = (int) coachmarkView.calcCircleRectF().height();

        ValueAnimator animatorX = ValueAnimator.ofInt(startWidth,targetWidth);
        ValueAnimator animatorY = ValueAnimator.ofInt(startHeight,targetHeight);
        animatorX.setDuration(1000);
        animatorY.setDuration(1000);
        animatorX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = wholeScreenCircle.getLayoutParams();
                layoutParams.width = val;
                wholeScreenCircle.setLayoutParams(layoutParams);
                coachmarkView.requestLayout();
            }
        });
        animatorY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = wholeScreenCircle.getLayoutParams();
                layoutParams.height = val;
                wholeScreenCircle.setLayoutParams(layoutParams);
                coachmarkView.requestLayout();
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(1000);
        animatorSet.playTogether(animatorX,animatorY);
        animatorSet.start();
    }
}
