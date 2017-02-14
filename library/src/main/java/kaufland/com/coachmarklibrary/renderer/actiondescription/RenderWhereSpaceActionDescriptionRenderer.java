package kaufland.com.coachmarklibrary.renderer.actiondescription;

import android.graphics.RectF;
import android.view.View;

/**
 * Created by sbra0902 on 13.02.17.
 */

public class RenderWhereSpaceActionDescriptionRenderer implements ActionDescriptionRenderer {

    @Override
    public void render(RectF screenRectangle, RectF circleRectangle, View actionDescription, View actionArrow) {


        int mWidth = actionDescription.getWidth() + actionArrow.getWidth();

        if (mWidth < circleRectangle.left) {

            renderLeftOfCircle(circleRectangle, actionDescription, actionArrow, mWidth);

        } else if (circleRectangle.top > actionDescription.getHeight() + actionArrow.getHeight() && actionDescription.getWidth() < circleRectangle.centerX()) {

            renderTopOfCircle(circleRectangle, actionDescription, actionArrow);

        } else if (actionDescription.getWidth() < circleRectangle.centerX()) {

            renderBelowCircle(circleRectangle, actionDescription, actionArrow);

        } else {

            renderRightOfCircle(circleRectangle, actionDescription, actionArrow);

        }
    }

    private void renderRightOfCircle(RectF circleRectangle, View actionDescription, View actionArrow) {
        actionDescription.setX((int) (circleRectangle.right + actionArrow.getWidth()));
        actionDescription.setY(circleRectangle.centerY() - (actionDescription.getHeight() / 2));

        actionArrow.setRotation(180);
        actionArrow.setX(circleRectangle.right);
        actionArrow.setY(circleRectangle.centerY() - (actionArrow.getHeight() / 2));
    }

    private void renderBelowCircle(RectF circleRectangle, View actionDescription, View actionArrow) {
        actionDescription.setX(circleRectangle.centerX() - (actionDescription.getWidth() / 2));
        actionDescription.setY(circleRectangle.bottom + actionArrow.getHeight());

        actionArrow.setRotation(270);
        actionArrow.setX(circleRectangle.centerX() - (actionArrow.getWidth() / 2));
        actionArrow.setY(circleRectangle.bottom);
    }

    private void renderTopOfCircle(RectF circleRectangle, View actionDescription, View actionArrow) {
        actionDescription.setX(circleRectangle.centerX() - (actionDescription.getWidth() / 2));
        actionDescription.setY(circleRectangle.top - actionArrow.getHeight() - actionDescription.getHeight());

        actionArrow.setRotation(90);
        actionArrow.setX(circleRectangle.centerX() - (actionArrow.getWidth() / 2));
        actionArrow.setY(circleRectangle.top - actionArrow.getHeight());
    }

    private void renderLeftOfCircle(RectF circleRectangle, View actionDescription, View actionArrow, int width) {
        actionDescription.setX((int) (circleRectangle.left - width));
        actionDescription.setY(circleRectangle.centerY() - (actionDescription.getHeight() / 2));

        actionArrow.setX(circleRectangle.left - actionArrow.getWidth());
        actionArrow.setY(circleRectangle.centerY() - (actionArrow.getHeight() / 2));
    }
}
