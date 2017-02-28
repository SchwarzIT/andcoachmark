package kaufland.com.coachmarklibrary.renderer.actiondescription;

import android.graphics.RectF;
import android.view.View;

import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;


public class RightOfCircleActionDescriptionRenderer implements ActionDescriptionRenderer {

    @Override
    public void render(CoachmarkViewLayout layout, View actionDescription, View actionArrow) {

        RectF circleRectangle = layout.calcCircleRectF();

        actionDescription.setX((int) (circleRectangle.right + actionArrow.getWidth()));
        actionDescription.setY(circleRectangle.centerY() - (actionDescription.getHeight() / 2));

        actionArrow.setRotation(180);
        actionArrow.setX(circleRectangle.right);
        actionArrow.setY(circleRectangle.centerY() - (actionArrow.getHeight() / 2));
    }

    @Override
    public boolean isRenderingPossible(CoachmarkViewLayout layout) {
        RectF circleRectangle = layout.calcCircleRectF();
        RectF actionDescriptionRectangle = layout.calcActionDescriptionRect();
        RectF actionArrowRectangle = layout.calcActionArrowRect();
        RectF screenRectangle = layout.calcScreenRectF();

        return (actionDescriptionRectangle.width() + actionArrowRectangle.width()) < (screenRectangle.width() - circleRectangle.right);
    }
}
