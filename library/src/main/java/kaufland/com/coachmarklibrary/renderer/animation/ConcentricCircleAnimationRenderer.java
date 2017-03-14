package kaufland.com.coachmarklibrary.renderer.animation;


import android.view.animation.LinearInterpolator;

import kaufland.com.coachmarklibrary.CoachmarkView;
import kaufland.com.coachmarklibrary.renderer.circle.CircleView;

public class ConcentricCircleAnimationRenderer implements AnimationRenderer {


    @Override
    public void animate(final CoachmarkView coachmarkView) {

        CircleView wholeScreenCircle = coachmarkView.getCircleView();

        ResizeAnimation resizeAnimation = new ResizeAnimation(wholeScreenCircle,(int)wholeScreenCircle.getRadius(),
                (int)coachmarkView.calcCircleRectF().width(),(int)wholeScreenCircle.getRadius(),(int)coachmarkView.calcCircleRectF().height());
        resizeAnimation.setDuration(1000);
        resizeAnimation.setInterpolator(new LinearInterpolator());
        wholeScreenCircle.startAnimation(resizeAnimation);
    }
}
