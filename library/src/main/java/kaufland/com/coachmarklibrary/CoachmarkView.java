package kaufland.com.coachmarklibrary;

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
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.DimensionPixelSizeRes;

import kaufland.com.coachmarklibrary.renderer.actiondescription.ActionDescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.actiondescription.RenderWhereSpaceActionDescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.description.DescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.description.TopOrBottomDescriptionRenderer;

/**
 * Created by sbra0902 on 01.02.17.
 */
@EViewGroup(resName = "coachmark_view")
public class CoachmarkView extends FrameLayout {

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


    private Bitmap bitmap;

    private View view;

    private int backColor;

    private int paddingAroundCircle;

    private int windowHeight;

    private View mActionDescription;
    private View mDescription;

    private ActionDescriptionRenderer mActionDescriptionRenderer = new RenderWhereSpaceActionDescriptionRenderer();

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
        paint.setColor(backColor);
        osCanvas.drawRect(outerRectangle, paint);

        paint.setColor(Color.TRANSPARENT);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

        if (view != null) {
            float radius = paddingAroundCircle + view.getWidth() / 2;
            int[] xy = new int[2];
            view.getLocationInWindow(xy);
            float centerX = xy[0] + view.getWidth() / 2;
            float centerY = xy[1] - (windowHeight - getHeight()) + view.getHeight() / 2;
            if(mDescription != null){
                mDescriptionRenderer.render(outerRectangle, centerY, mDescription);
            }

            if(mActionDescription != null){
                RectF circle = new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
                mActionDescriptionRenderer.render(outerRectangle, circle, mActionDescription, mIvActionArrow);
            }

            osCanvas.drawCircle(centerX, centerY, radius, paint);
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

    public void setActionDescriptionRenderer(ActionDescriptionRenderer actionDescriptionRenderer) {
        mActionDescriptionRenderer = actionDescriptionRenderer;
    }

    public void setDescriptionRenderer(DescriptionRenderer descriptionRenderer) {
        mDescriptionRenderer = descriptionRenderer;
    }

    public void setCoachMarkTextData(CoachMarkTextData coachMarkTextData) {

        mTxtOkBtn.setText(coachMarkTextData.getOkText());
        mTxtCancelBtn.setText(coachMarkTextData.getCancelText());
    }

    public void setView(View view) {
        this.view = view;
        bitmap = null;
    }

    public void setBackColor(int backColor) {
        this.backColor = backColor;
    }

    public void setPaddingAroundCircle(int paddingAroundCircle) {
        this.paddingAroundCircle = paddingAroundCircle;
    }

    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    public LinearLayout getOkGroup() {
        return mGroupOk;
    }

    public LinearLayout getCancelGroup() {
        return mGroupCancel;
    }
}
