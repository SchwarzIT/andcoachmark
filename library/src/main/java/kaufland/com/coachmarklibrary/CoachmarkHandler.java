package kaufland.com.coachmarklibrary;

import android.content.Context;
import android.graphics.PixelFormat;
import android.support.annotation.ColorInt;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.res.ColorRes;
import org.androidannotations.annotations.res.DimensionPixelSizeRes;

/**
 * Created by sbra0902 on 01.02.17.
 */
@EBean
public class CoachmarkHandler {

    @SystemService
    WindowManager mWindowManager;

    @RootContext
    protected Context mContext;

    @DimensionPixelSizeRes(resName = "default_margin_circle")
    protected int marginArroundCircle;

    @ColorRes(resName = "default_backcolor")
    protected int defaultBackColor;

    public void showCoachmarkAround(View view, CoachMarkTextData textData, View actionDescription, View description){
        showCoachmarkAround(view, textData, defaultBackColor, actionDescription, description);
    }

    public void showCoachmarkAround(View view, CoachMarkTextData textData, int backgroundColor, View actionDescription, View description){

        final CoachmarkView coachmarkView = CoachmarkView_.build(mContext);
        coachmarkView.setCoachMarkTextData(textData);


        DisplayMetrics metrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(metrics);

        WindowManager.LayoutParams mWindowParams = new WindowManager.LayoutParams();
        mWindowParams.gravity = Gravity.TOP;
        mWindowParams.x = 0;
        mWindowParams.y = 0;
        mWindowParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        mWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mWindowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_LAYOUT_ATTACHED_IN_DECOR;
        mWindowParams.format = PixelFormat.TRANSLUCENT;
        mWindowParams.windowAnimations = 0;


        coachmarkView.setActionDescription(actionDescription);
        coachmarkView.setDescription(description);
        coachmarkView.setWindowHeight(metrics.heightPixels);
        coachmarkView.setView(view);
        coachmarkView.setBackColor(backgroundColor);
        coachmarkView.setPaddingAroundCircle(marginArroundCircle);

        coachmarkView.getCancelGroup().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWindowManager.removeView(coachmarkView);
            }
        });

        coachmarkView.getOkGroup().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWindowManager.removeView(coachmarkView);
            }
        });

        mWindowManager.addView(coachmarkView, mWindowParams);
        coachmarkView.requestLayout();
    }

}
