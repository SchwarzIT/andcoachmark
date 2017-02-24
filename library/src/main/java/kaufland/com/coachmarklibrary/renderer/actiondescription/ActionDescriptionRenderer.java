package kaufland.com.coachmarklibrary.renderer.actiondescription;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;

/**
 * Created by sbra0902 on 13.02.17.
 */

public interface ActionDescriptionRenderer {

    void render(CoachmarkViewLayout layout, View actionDescription, View actionArrow);

    boolean isRenderingPossible(CoachmarkViewLayout layout);

}
