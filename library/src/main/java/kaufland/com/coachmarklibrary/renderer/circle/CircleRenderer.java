package kaufland.com.coachmarklibrary.renderer.circle;


import android.graphics.RectF;

import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;

public interface CircleRenderer {

    RectF calcCircleStartRectF(CoachmarkViewLayout layout);

    CircleView render(CoachmarkViewLayout layout);
}
