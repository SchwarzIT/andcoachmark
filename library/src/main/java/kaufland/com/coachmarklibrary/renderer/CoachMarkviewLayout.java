package kaufland.com.coachmarklibrary.renderer;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by sbra0902 on 21.02.17.
 */

public interface CoachmarkViewLayout {

    void dismiss();

    void show();

    Context getContext();

    RectF calcActionArrowRect();

    RectF calcActionDescriptionRect();

    RectF calcDescriptionRect();

    RectF calcScreenRectF();

    RectF calcCircleRectF();

    void addView(View inflated);

    void removeView(View inflated);
}
