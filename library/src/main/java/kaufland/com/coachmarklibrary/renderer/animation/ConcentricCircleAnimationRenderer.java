package kaufland.com.coachmarklibrary.renderer.animation;


import android.graphics.RectF;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;

import kaufland.com.coachmarklibrary.CoachmarkView;
import kaufland.com.coachmarklibrary.renderer.circle.CircleView;

public class ConcentricCircleAnimationRenderer implements AnimationRenderer {


    @Override
    public void animate(CoachmarkView coachmarkView) {

        if(coachmarkView !=null && coachmarkView.getCircleView()!=null){

            CircleView circleView = coachmarkView.getCircleView();

            RectF startRectF = circleView.getStartRectF();
            RectF endRectF = circleView.getClickedViewRectF();

            Animation animation = new ScaleAnimation(1.0f,0f,1.0f,0f,0.5f,0.5f);
            animation.setInterpolator(new LinearInterpolator());
            animation.setDuration(1000);
            animation.setFillAfter(true);
            circleView.setAnimation(animation);
            coachmarkView.startAnimation(animation);
        }

    }

    @Override
    public boolean isAnimationBeforeRendering() {
        return true;
    }
}
