package kaufland.com.coachmarklibrary.renderer.description;

import android.graphics.RectF;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by sbra0902 on 13.02.17.
 */

public class TopOrBottomDescriptionRenderer implements DescriptionRenderer {

    @Override
    public void render(RectF screenRectangle, float circleCenterY, View description) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = circleCenterY > screenRectangle.height() / 2 ? Gravity.TOP : Gravity.BOTTOM;
        description.setLayoutParams(params);
    }
}
