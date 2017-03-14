package kaufland.com.coachmarklibrary.renderer.circle;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import org.androidannotations.annotations.EViewGroup;

@EViewGroup(resName = "circle_view")
public class CircleView extends FrameLayout {

    private float mCenterX;

    private float mCenterY;

    private float mRadius;

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
        Log.d("Dispatch draw","Dispatch draw");
        Paint paint = new Paint();
        paint.setColor(defaultColor);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawCircle(mCenterX, mCenterY, mRadius,paint);
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("On draw","On draw");
        super.onDraw(canvas);
    }

    public float getCenterX() {
        return mCenterX;
    }

    public void setCenterX(float mCenterX) {
        this.mCenterX = mCenterX;
    }

    public float getCenterY() {
        return mCenterY;
    }

    public void setCenterY(float mCenterY) {
        this.mCenterY = mCenterY;
    }

    public float getRadius() {
        return mRadius;
    }

    public void setRadius(float mRadius) {
        this.mRadius = mRadius;
    }

    public void setColor(int color){
        defaultColor = color;
    }
}
