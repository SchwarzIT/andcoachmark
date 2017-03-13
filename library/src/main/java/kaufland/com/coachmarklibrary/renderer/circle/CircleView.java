package kaufland.com.coachmarklibrary.renderer.circle;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.androidannotations.annotations.EViewGroup;

@EViewGroup(resName = "circle_view")
public class CircleView extends FrameLayout {

    private RectF startRectF;
    private RectF clickedViewRectF;

    private int defaultColor = Color.TRANSPARENT;

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if(startRectF!=null && clickedViewRectF !=null){
            Paint paint = new Paint();
            paint.setColor(defaultColor);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
            canvas.drawCircle(clickedViewRectF.centerX(), clickedViewRectF.centerY(),startRectF.width()/2,paint);
        }
        super.dispatchDraw(canvas);
    }

    public void setStartRectF(RectF startRectF) {
        this.startRectF = startRectF;
    }

    public void setClickedViewRectF(RectF clickedViewRectF) {
        this.clickedViewRectF = clickedViewRectF;
    }

    public RectF getStartRectF() {
        return startRectF;
    }

    public RectF getClickedViewRectF() {
        return clickedViewRectF;
    }

    public void setColor(int color){
        defaultColor = color;
    }
}
