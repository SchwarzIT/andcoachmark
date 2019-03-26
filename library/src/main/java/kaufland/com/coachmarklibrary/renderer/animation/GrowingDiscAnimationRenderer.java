package kaufland.com.coachmarklibrary.renderer.animation;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.RectF;

import kaufland.com.coachmarklibrary.R;
import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;
import kaufland.com.coachmarklibrary.renderer.circle.CircleView;

public class GrowingDiscAnimationRenderer implements AnimationRenderer {

    private int mAnimationDuration = 500;

    private Integer mWithRadius;
    private Integer mWithTransparentRadius;

    private GrowingDiscAnimationRenderer(){
        // just hidding the Contructor
    }

    public static class Builder {

        private GrowingDiscAnimationRenderer renderer;

        public Builder() {
            renderer = new GrowingDiscAnimationRenderer();
        }

        public GrowingDiscAnimationRenderer.Builder withDuration(int duration) {
            renderer.mAnimationDuration = duration;
            return this;
        }

        public GrowingDiscAnimationRenderer.Builder withRadius(int radius) {
            renderer.mWithRadius = radius;
            return this;
        }

        public GrowingDiscAnimationRenderer.Builder withTransparentRadius(int radius) {
            renderer.mWithTransparentRadius = radius;
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
        view.setTransparentRadius(mWithTransparentRadius != null ? mWithTransparentRadius : mCircleRectF.width());

        ValueAnimator animatorX = ValueAnimator.ofFloat(0, mWithRadius != null ? mWithRadius : view.getContext().getResources().getDimension(R.dimen.default_reverse_concentric_radius));
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
