package kaufland.com.coachmarklibrary.renderer.circlerenderer;


import android.graphics.RectF;
import android.view.View;

import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;

public interface CircleRenderer {

    void render(CoachmarkViewLayout layout, View clickedView);

    RectF calcCircleRect();
}
