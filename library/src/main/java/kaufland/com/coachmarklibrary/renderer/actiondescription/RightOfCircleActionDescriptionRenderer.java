package kaufland.com.coachmarklibrary.renderer.actiondescription;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by sbra0902 on 13.02.17.
 */

public class RightOfCircleActionDescriptionRenderer implements ActionDescriptionRenderer {

    @Override
    public void render(RectF screenRectangle, RectF circleRectangle, View actionDescription, View actionArrow) {

        actionDescription.setX((int) (circleRectangle.right + actionArrow.getWidth()));
        actionDescription.setY(circleRectangle.centerY() - (actionDescription.getHeight() / 2));

        actionArrow.setRotation(180);
        actionArrow.setX(circleRectangle.right);
        actionArrow.setY(circleRectangle.centerY() - (actionArrow.getHeight() / 2));
    }

    @Override
    public boolean isRenderingPossible(RectF screenRectangle, RectF circleRectangle, Rect actionDescriptionRectangle, Rect actionArrowRectangle) {
        return (actionDescriptionRectangle.width() + actionArrowRectangle.width()) < (screenRectangle.width() - circleRectangle.right);
    }
}
