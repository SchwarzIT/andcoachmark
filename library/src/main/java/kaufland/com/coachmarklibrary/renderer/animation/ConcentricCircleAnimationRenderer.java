package kaufland.com.coachmarklibrary.renderer.animation;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.RectF;
import android.view.ViewGroup;

import kaufland.com.coachmarklibrary.CoachmarkClickListener;
import kaufland.com.coachmarklibrary.CoachmarkView;
import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;
import kaufland.com.coachmarklibrary.renderer.buttonrenderer.OkAndCancelAtRightCornerButtonRenderer;
import kaufland.com.coachmarklibrary.renderer.buttonrenderer.OkAndCancelAtRightCornersButtonRendererView_;
import kaufland.com.coachmarklibrary.renderer.circle.CircleView;

public class ConcentricCircleAnimationRenderer implements AnimationRenderer {

    private int mAnimationDuration = 500;

    private ConcentricCircleAnimationRenderer(){
        // just hidding the Contructor
    }

    public static class Builder {

        private ConcentricCircleAnimationRenderer renderer;

        public Builder() {
            renderer = new ConcentricCircleAnimationRenderer();
        }

        public ConcentricCircleAnimationRenderer.Builder withDuration(int duration) {
            renderer.mAnimationDuration = duration;
            return this;
        }

        public AnimationRenderer build() {
            return renderer;
        }
    }


    @Override
    public void animate(CoachmarkViewLayout layout, final CircleView view, final AnimationListener animationListener) {


        RectF mCircleRectF = layout.calcCircleRectF();

        view.setCenterX(mCircleRectF.centerX());
        view.setCenterY(mCircleRectF.centerY());

        ValueAnimator animatorX = ValueAnimator.ofFloat(layout.calcScreenRectF().height(), mCircleRectF.width() / 2);
        animatorX.setDuration(mAnimationDuration);
        animatorX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float val = (Float) valueAnimator.getAnimatedValue();

                view.setRadius(val);
                view.bringToFront();
                view.forceLayout();
            }

        });
        animatorX.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animationListener.onAnimationFinished();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorX.start();




    }
}
