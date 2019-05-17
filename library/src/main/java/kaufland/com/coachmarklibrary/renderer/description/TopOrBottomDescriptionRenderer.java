package kaufland.com.coachmarklibrary.renderer.description;

import android.graphics.RectF;
import android.view.View;

import kaufland.com.coachmarklibrary.R;
import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;

public class TopOrBottomDescriptionRenderer implements DescriptionRenderer {

    @Override
    public void render(CoachmarkViewLayout layout, View description) {

        RectF circle = layout.calcCircleRectF();
        RectF screenRectangle = layout.calcScreenRectF();

        if (circle.centerY() > screenRectangle.height() / 2) {
            description.setY(description.getContext().getResources().getDimension(R.dimen.description_padding));
        } else {
            description.setY(screenRectangle.height() - description.getContext().getResources().getDimension(R.dimen.description_padding) - description.getHeight());
        }
    }
}
