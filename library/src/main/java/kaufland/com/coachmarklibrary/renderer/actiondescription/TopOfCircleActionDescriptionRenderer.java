package kaufland.com.coachmarklibrary.renderer.actiondescription;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;

/**
 * Created by sbra0902 on 13.02.17.
 */

public class TopOfCircleActionDescriptionRenderer implements ActionDescriptionRenderer {

    @Override
    public void render(CoachmarkViewLayout layout, View actionDescription, View actionArrow) {

        RectF circleRectangle = layout.calcCircleRectF();

        actionDescription.setX(circleRectangle.centerX() - (actionDescription.getWidth() / 2));
        actionDescription.setY(circleRectangle.top - actionArrow.getHeight() - actionDescription.getHeight());

        actionArrow.setRotation(90);
        actionArrow.setX(circleRectangle.centerX() - (actionArrow.getWidth() / 2));
        actionArrow.setY(circleRectangle.top - actionArrow.getHeight());
    }

    @Override
    public boolean isRenderingPossible(CoachmarkViewLayout layout) {
        RectF circleRectangle = layout.calcCircleRectF();
        RectF actionDescriptionRectangle = layout.calcActionDescriptionRect();
        RectF actionArrowRectangle = layout.calcActionArrowRect();
        RectF screenRectangle = layout.calcScreenRectF();

        return circleRectangle.top > actionDescriptionRectangle.height() + actionArrowRectangle.height() && (actionDescriptionRectangle.width() / 2) < circleRectangle.centerX() && (actionDescriptionRectangle.width() / 2) < (screenRectangle.width() - circleRectangle.centerX());
    }
}
