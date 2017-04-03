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
import kaufland.com.coachmarklibrary.renderer.animation.AnimationListener;
import kaufland.com.coachmarklibrary.renderer.animation.AnimationRenderer;
import kaufland.com.coachmarklibrary.renderer.animation.NoAnimationRenderer;
import kaufland.com.coachmarklibrary.renderer.buttonrenderer.ButtonRenderer;
import kaufland.com.coachmarklibrary.renderer.circle.CircleView;
import kaufland.com.coachmarklibrary.renderer.description.DescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.description.TopOrBottomDescriptionRenderer;

@EViewGroup(resName = "coachmark_view")
public class CoachmarkView extends FrameLayout implements CoachmarkViewLayout, AnimationListener {

    @DimensionPixelSizeRes(resName = "default_margin_circle")
    protected int marginAroundCircle;

    @ColorRes(resName = "default_backcolor")
    protected int defaultBackColor;

    @SystemService
    WindowManager mWindowManager;

    @ViewById(resName = "circle")
    FrameLayout mFrame;

    @ViewById(resName = "iv_action_arrow")
    ImageView mIvActionArrow;

    @ViewById(resName = "circle_view")
    CircleView mCircleView;

    private Bitmap mBitmap;

    private View mView;

    private int windowHeight;

    private View mActionDescription;

    private View mDescription;

    private View mButtonsView;

    private boolean mIsInitialized;

    private ActionDescriptionRenderer[] mActionDescriptionRenderer = new ActionDescriptionRenderer[]{
            new LeftOfCircleActionDescriptionRenderer(),
            new TopOfCircleActionDescriptionRenderer(),
            new BelowCircleActionDescriptionRenderer(),
            new RightOfCircleActionDescriptionRenderer()
    };

    private DescriptionRenderer mDescriptionRenderer = new TopOrBottomDescriptionRenderer();

    private ButtonRenderer mButtonRenderer;

    private AnimationRenderer mAnimationRenderer;

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
        if (mBitmap == null) {
            createWindowFrame();
        }
        canvas.drawBitmap(mBitmap, null, calcScreenRectF(), null);

        super.dispatchDraw(canvas);

        if (!mIsInitialized) {
            mIsInitialized = true;
            if (mView != null) {

                if (mAnimationRenderer == null) {
                    mAnimationRenderer = new NoAnimationRenderer();
                }

                mAnimationRenderer.animate(CoachmarkView.this, mCircleView, CoachmarkView.this);
            }
        }
    }

    protected void createWindowFrame() {
        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas osCanvas = new Canvas(mBitmap);

        Paint paint = new Paint();
        paint.setColor(defaultBackColor);
        osCanvas.drawRect(calcScreenRectF(), paint);

        paint.setColor(Color.TRANSPARENT);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

    }

    @Override
    public void onAnimationFinished() {
        if (mDescription != null) {
            mDescriptionRenderer.render(this, mDescription);
            mDescription.setVisibility(VISIBLE);
        }

        if (mActionDescription != null && mActionDescriptionRenderer != null) {
            renderActionDescription();
            mActionDescription.setVisibility(VISIBLE);
            mIvActionArrow.setVisibility(VISIBLE);
        }

        if (mButtonRenderer != null) {
            mButtonsView = mButtonRenderer.render(CoachmarkView.this);
        }
    }

    public CoachmarkView show() {
        DisplayMetrics metrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(metrics);
        windowHeight = metrics.heightPixels;

        WindowManager.LayoutParams mWindowParams = new WindowManager.LayoutParams();
        mWindowParams.gravity = Gravity.TOP;
        mWindowParams.x = 0;
        mWindowParams.y = 0;
        mWindowParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        mWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mWindowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN;
        mWindowParams.format = PixelFormat.TRANSLUCENT;

        mIsInitialized = false;
        if (getWindowToken() == null) {
            mWindowManager.addView(this, mWindowParams);
        }

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            // drawing not working in SDK <= KITKAT
            setLayerType(LAYER_TYPE_SOFTWARE, null);
        }
        requestLayout();
        return this;
    }

    private void renderActionDescription() {

        for (ActionDescriptionRenderer renderer : mActionDescriptionRenderer) {
            if (renderer.isRenderingPossible(this)) {
                renderer.render(this, mActionDescription, mIvActionArrow);
                return;
            }
        }
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
        float radius = marginAroundCircle + mView.getWidth() / 2;
        int[] xy = new int[2];
        mView.getLocationInWindow(xy);
        float centerX = xy[0] + mView.getWidth() / 2;
        float centerY = xy[1] - (windowHeight - getHeight()) + mView.getHeight() / 2;
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

    public void setView(View mView) {
        this.mView = mView;
        mBitmap = null;
    }

    public void setButtonRenderer(ButtonRenderer buttonRenderer) {
        mButtonRenderer = buttonRenderer;
    }

    public void setBackColor(int backColor) {
        this.defaultBackColor = backColor;
    }

    public void setPaddingAroundCircle(int paddingAroundCircle) {
        this.marginAroundCircle = paddingAroundCircle;
    }

    @Override
    public void dismiss() {
        mWindowManager.removeView(CoachmarkView.this);
    }

    public void setAnimationRenderer(AnimationRenderer animationRenderer) {
        mAnimationRenderer = animationRenderer;
    }

    public boolean isIsInitialized() {
        return mIsInitialized;
    }
}
