package kaufland.com.coachmarklibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;
import org.androidannotations.annotations.res.DimensionPixelSizeRes;

import kaufland.com.coachmarklibrary.renderer.actiondescription.ActionDescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.actiondescription.BelowCircleActionDescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.actiondescription.LeftOfCircleActionDescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.actiondescription.RightOfCircleActionDescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.actiondescription.TopOfCircleActionDescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.description.DescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.description.TopOrBottomDescriptionRenderer;

/**
 * Created by sbra0902 on 01.02.17.
 */
@EViewGroup(resName = "coachmark_view")
public class CoachmarkView extends FrameLayout {

    @SystemService
    WindowManager mWindowManager;

    @ViewById(resName = "txt_ok_btn")
    TextView mTxtOkBtn;

    @ViewById(resName = "txt_cancel_btn")
    TextView mTxtCancelBtn;

    @ViewById(resName = "group_ok")
    LinearLayout mGroupOk;

    @ViewById(resName = "group_cancel")
    LinearLayout mGroupCancel;

    @ViewById(resName = "circle")
    FrameLayout mFrame;

    @ViewById(resName = "iv_action_arrow")
    ImageView mIvActionArrow;

    @DimensionPixelSizeRes(resName = "default_margin_circle")
    protected int marginArroundCircle;

    @ColorRes(resName = "default_backcolor")
    protected int defaultBackColor;


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

        RectF outerRectangle = new RectF(0, 0, getWidth(), getHeight());

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(defaultBackColor);
        osCanvas.drawRect(outerRectangle, paint);

        paint.setColor(Color.TRANSPARENT);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

        if (view != null) {
            float radius = marginArroundCircle + view.getWidth() / 2;
            int[] xy = new int[2];
            view.getLocationInWindow(xy);
            float centerX = xy[0] + view.getWidth() / 2;
            float centerY = xy[1] - (windowHeight - getHeight()) + view.getHeight() / 2;
            if(mDescription != null){
                mDescriptionRenderer.render(outerRectangle, centerY, mDescription);
            }

            if(mActionDescription != null && mActionDescriptionRenderer != null){
                renderActionDescription(outerRectangle, radius, centerX, centerY);

            }

            osCanvas.drawCircle(centerX, centerY, radius, paint);
        }

    }

    public void show(){
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


    private void renderActionDescription(RectF outerRectangle, float radius, float centerX, float centerY) {
        RectF circle = new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        Rect actionDescriptionRec = new Rect(mActionDescription.getLeft(), mActionDescription.getTop(), mActionDescription.getRight(), mActionDescription.getBottom());
        Rect actionArrowRec = new Rect(mIvActionArrow.getLeft(), mIvActionArrow.getTop(), mIvActionArrow.getRight(), mIvActionArrow.getBottom());

        for(ActionDescriptionRenderer renderer : mActionDescriptionRenderer){
            if(renderer.isRenderingPossible(outerRectangle, circle, actionDescriptionRec, actionArrowRec)){
                renderer.render(outerRectangle, circle, mActionDescription, mIvActionArrow);
                return;
            }
        }
    }


    @Override
    public boolean isInEditMode() {
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        bitmap = null;
    }

    public void setActionDescription(View actionDescription) {
        if(mActionDescription != null){
            removeView(actionDescription);
        }

        mActionDescription = actionDescription;
        if(mActionDescription == null){
            return;
        }
        mActionDescription.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(mActionDescription);
    }

    public void setDescription(View description) {

        if(mDescription != null){
            removeView(description);
        }
        mDescription = description;
        if(mDescription == null){
            return;
        }
        addView(mDescription);
    }

    public void setActionDescriptionRenderer(ActionDescriptionRenderer[] actionDescriptionRenderer) {
        mActionDescriptionRenderer = actionDescriptionRenderer;
    }

    public void setDescriptionRenderer(DescriptionRenderer descriptionRenderer) {
        mDescriptionRenderer = descriptionRenderer;
    }

    public void setOkButton(String okText, final CoachmarkClickListener clickListener){

            mGroupOk.setVisibility(VISIBLE);
            mTxtOkBtn.setText(okText);
            mGroupOk.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mWindowManager.removeView(CoachmarkView.this);
                    clickListener.onClicked();
                }
            });
    }

    public void setCancelButton(String okText, final CoachmarkClickListener clickListener){

        mGroupCancel.setVisibility(VISIBLE);
        mTxtCancelBtn.setText(okText);
        mGroupCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mWindowManager.removeView(CoachmarkView.this);
                clickListener.onClicked();
            }
        });
    }

    public void setDismissOnClick(boolean dismissOnClick) {
        if(dismissOnClick){
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mWindowManager.removeView(CoachmarkView.this);
                }
            });
        }
    }

    public void setView(View view) {
        this.view = view;
        bitmap = null;
    }

    public void setBackColor(int backColor) {
        this.defaultBackColor = backColor;
    }

    public void setPaddingAroundCircle(int paddingAroundCircle) {
        this.marginArroundCircle = paddingAroundCircle;
    }

}
