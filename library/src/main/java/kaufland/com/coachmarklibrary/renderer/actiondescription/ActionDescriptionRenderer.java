package kaufland.com.coachmarklibrary.renderer.actiondescription;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by sbra0902 on 13.02.17.
 */

public interface ActionDescriptionRenderer {

    void render(RectF screenRectangle, RectF circleRectangle, View actionDescription, View actionArrow);

    boolean isRenderingPossible(RectF screenRectangle, RectF circleRectangle, Rect actionDescriptionRectangle, Rect actionArrowRectangle);

}
