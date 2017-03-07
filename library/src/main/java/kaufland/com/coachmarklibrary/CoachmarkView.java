package kaufland.com.coachmarklibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;
import org.androidannotations.annotations.res.DimensionPixelSizeRes;

import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;
import kaufland.com.coachmarklibrary.renderer.actiondescription.ActionDescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.actiondescription.BelowCircleActionDescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.actiondescription.LeftOfCircleActionDescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.actiondescription.RightOfCircleActionDescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.actiondescription.TopOfCircleActionDescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.buttonrenderer.ButtonRenderer;
import kaufland.com.coachmarklibrary.renderer.description.DescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.description.TopOrBottomDescriptionRenderer;

@EViewGroup(resName = "coachmark_view")
class CoachmarkView extends FrameLayout implements CoachmarkViewLayout {

    @DimensionPixelSizeRes(resName = "default_margin_circle")
    protected int marginArroundCircle;
    @ColorRes(resName = "default_backcolor")
    protected int defaultBackColor;
    @SystemService
    WindowManager mWindowManager;
    @ViewById(resName = "circle")
    FrameLayout mFrame;
    @ViewById(resName = "iv_action_arrow")
    ImageView mIvActionArrow;
    private Bitmap bitmap;

    private View view;

    private int windowHeight;

    private View mActionDescription;

    private View mDescription;


    private ActionDescriptionRenderer[] mActionDescriptionRenderer = new ActionDescriptionRenderer[]{
            new LeftOfCircleActionDescriptionRenderer(),
            new TopOfCircleActionDescriptionRenderer(),
            new BelowCircleActionDescriptionRenderer(),
            new RightOfCircleActionDescriptionRenderer()
    };

    private DescriptionRenderer mDescriptionRenderer = new TopOrBottomDescriptionRenderer();

    private ButtonRenderer mButtonRenderer;



    public CoachmarkView(Context context) {
        super(context);
    }

    public CoachmarkView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CoachmarkView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CoachmarkView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (bitmap == null) {
            createWindowFrame();
        }
        canvas.drawBitmap(bitmap, 0, 0, null);
        super.dispatchDraw(canvas);
    }

    protected void createWindowFrame() {
        bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas osCanvas = new Canvas(bitmap);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(defaultBackColor);
        osCanvas.drawRect(calcScreenRectF(), paint);

        paint.setColor(Color.TRANSPARENT);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

        if (view != null) {

            if (mDescription != null) {
                mDescriptionRenderer.render(this, mDescription);
                mDescription.setVisibility(VISIBLE);
            }


            if (mActionDescription != null && mActionDescriptionRenderer != null) {
                renderActionDescription();
                mActionDescription.setVisibility(VISIBLE);
            }

            if (mButtonRenderer != null) {
                mButtonRenderer.render(CoachmarkView.this);
            }

            mIvActionArrow.setVisibility(VISIBLE);

            RectF circle = calcCircleRectF();

            osCanvas.drawCircle(circle.centerX(), circle.centerY(), circle.width() / 2, paint);
        }

    }

    public void show() {
        DisplayMetrics metrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(metrics);

        windowHeight = metrics.heightPixels;

        WindowManager.LayoutParams mWindowParams = new WindowManager.LayoutParams();
        mWindowParams.gravity = Gravity.TOP;
        mWindowParams.x = 0;
        mWindowParams.y = 0;
        mWindowParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        mWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mWindowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_LAYOUT_ATTACHED_IN_DECOR;
        mWindowParams.format = PixelFormat.TRANSLUCENT;
        mWindowParams.windowAnimations = 0;


        mWindowManager.addView(this, mWindowParams);
        requestLayout();
    }


    private void renderActionDescription() {

        for (ActionDescriptionRenderer renderer : mActionDescriptionRenderer) {
            if (renderer.isRenderingPossible(this)) {
                renderer.render(this, mActionDescription, mIvActionArrow);
                return;
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        WindowManager.LayoutParams params = (WindowManager.LayoutParams) getLayoutParams();
        params.horizontalMargin = -1000;
        params.verticalMargin = -1000;
        setLayoutParams(params);

    }

    @NonNull
    public RectF calcActionArrowRect() {
        return new RectF(mIvActionArrow.getX(), mIvActionArrow.getY(), mIvActionArrow.getX() + mIvActionArrow.getWidth(), mIvActionArrow.getY() + mIvActionArrow.getHeight());
    }

    public RectF calcActionDescriptionRect() {
        return new RectF(mActionDescription.getX(), mActionDescription.getY(), mActionDescription.getX() + mActionDescription.getWidth(), mActionDescription.getY() + mActionDescription.getHeight());
    }

    public RectF calcDescriptionRect() {
        return new RectF(mDescription.getX(), mDescription.getY(), mDescription.getX() + mDescription.getWidth(), mDescription.getY() + mDescription.getHeight());
    }

    public RectF calcScreenRectF() {
        return new RectF(0, 0, getWidth(), getHeight());
    }

    public RectF calcCircleRectF() {
        float radius = marginArroundCircle + view.getWidth() / 2;
        int[] xy = new int[2];
        view.getLocationInWindow(xy);
        float centerX = xy[0] + view.getWidth() / 2;
        float centerY = xy[1] - (windowHeight - getHeight()) + view.getHeight() / 2;
        return new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
    }

    @Override
    public boolean isInEditMode() {
        return true;
    }


    public void setActionDescription(View actionDescription) {
        if (mActionDescription != null) {
            removeView(actionDescription);
        }

        mActionDescription = actionDescription;
        if (mActionDescription == null) {
            return;
        }
        mActionDescription.setVisibility(INVISIBLE);
        mActionDescription.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(mActionDescription);
    }

    public void setDescription(View description) {

        if (mDescription != null) {
            removeView(description);
        }
        mDescription = description;
        if (mDescription == null) {
            return;
        }
        mDescription.setVisibility(INVISIBLE);
        mDescription.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(mDescription);
    }

    public void setActionDescriptionRenderer(ActionDescriptionRenderer... actionDescriptionRenderer) {
        mActionDescriptionRenderer = actionDescriptionRenderer;
    }

    public void setDescriptionRenderer(DescriptionRenderer descriptionRenderer) {
        mDescriptionRenderer = descriptionRenderer;
    }

    public void setView(View view) {
        this.view = view;
        bitmap = null;
    }

    public void setButtonRenderer(ButtonRenderer buttonRenderer) {
        mButtonRenderer = buttonRenderer;
    }

    public void setBackColor(int backColor) {
        this.defaultBackColor = backColor;
    }

    public void setPaddingAroundCircle(int paddingAroundCircle) {
        this.marginArroundCircle = paddingAroundCircle;
    }



    @Override
    public void dismiss() {
        mWindowManager.removeView(CoachmarkView.this);
    }
}
