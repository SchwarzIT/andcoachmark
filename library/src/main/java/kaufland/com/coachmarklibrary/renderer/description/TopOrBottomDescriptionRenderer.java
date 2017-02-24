package kaufland.com.coachmarklibrary.renderer.description;

import android.graphics.RectF;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import kaufland.com.coachmarklibrary.R;
import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;

/**
 * Created by sbra0902 on 13.02.17.
 */

public class TopOrBottomDescriptionRenderer implements DescriptionRenderer {

    @Override
    public void render(CoachmarkViewLayout layout, View description) {

        RectF circle = layout.calcCircleRectF();
        RectF screenRectangle = layout.calcScreenRectF();

        if(circle.centerY() > screenRectangle.height() / 2){
            description.setY(description.getContext().getResources().getDimension(R.dimen.description_padding));
        }else{
            description.setY(screenRectangle.height() - description.getContext().getResources().getDimension(R.dimen.description_padding) - description.getHeight());
        }

    }
}
