package kaufland.com.coachmarklibrary.renderer.animation;

import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;

import kaufland.com.coachmarklibrary.CoachmarkView;
import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;
import kaufland.com.coachmarklibrary.renderer.circle.CircleView;

/**
 * Created by sbra0902 on 14.03.17.
 */

public class NoAnimationRenderer implements AnimationRenderer {

    @Override
    public void animate(final CoachmarkViewLayout layout, final CircleView view, final AnimationListener animationListener) {


        RectF mCircleRectF = layout.calcCircleRectF();

        view.setRadius(mCircleRectF.width() / 2);
        view.setCenterX(mCircleRectF.centerX());
        view.setCenterY(mCircleRectF.centerY());

        animationListener.onAnimationFinished();


    }
}
