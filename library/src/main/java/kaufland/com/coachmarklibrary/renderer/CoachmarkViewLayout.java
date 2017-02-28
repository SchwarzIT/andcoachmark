package kaufland.com.coachmarklibrary.renderer;

import android.content.Context;
import android.graphics.RectF;
import android.view.View;

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
