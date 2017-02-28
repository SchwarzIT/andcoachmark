package kaufland.com.coachmarklibrary.renderer.actiondescription;

import android.graphics.RectF;
import android.view.View;

import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;

public class BelowCircleActionDescriptionRenderer implements ActionDescriptionRenderer {

    @Override
    public void render(CoachmarkViewLayout layout, View actionDescription, View actionArrow) {

        RectF circleRectangle = layout.calcCircleRectF();

        actionDescription.setX(circleRectangle.centerX() - (actionDescription.getWidth() / 2));
        actionDescription.setY(circleRectangle.bottom + actionArrow.getHeight());

        actionArrow.setRotation(270);
        actionArrow.setX(circleRectangle.centerX() - (actionArrow.getWidth() / 2));
        actionArrow.setY(circleRectangle.bottom);
    }

    @Override
    public boolean isRenderingPossible(CoachmarkViewLayout layout) {
        RectF circleRectangle = layout.calcCircleRectF();
        RectF actionDescriptionRectangle = layout.calcActionDescriptionRect();
        RectF actionArrowRectangle = layout.calcActionArrowRect();
        RectF screenRectangle = layout.calcScreenRectF();

        return (circleRectangle.bottom + actionDescriptionRectangle.height() + actionArrowRectangle.height()) < screenRectangle.height() && (actionDescriptionRectangle.width() / 2) < circleRectangle.centerX() && (actionDescriptionRectangle.width() / 2) < (screenRectangle.width() - circleRectangle.centerX());
    }
}
