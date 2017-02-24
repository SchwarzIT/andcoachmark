package kaufland.com.coachmarklibrary.renderer.buttonrenderer;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import kaufland.com.coachmarklibrary.CoachmarkClickListener;
import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;

/**
 * Created by sbra0902 on 21.02.17.
 */
@EViewGroup(resName = "ok_cancel_button_view")
public class OkAndCancelAtRightCornersButtonRendererView extends FrameLayout {

    @ViewById(resName = "txt_ok_btn")
    TextView mTxtOkBtn;

    @ViewById(resName = "txt_cancel_btn")
    TextView mTxtCancelBtn;

    @ViewById(resName = "group_ok")
    LinearLayout mGroupOk;

    @ViewById(resName = "group_cancel")
    LinearLayout mGroupCancel;

    private CoachmarkViewLayout mDismissListener;

    public OkAndCancelAtRightCornersButtonRendererView(Context context) {
        super(context);
    }

    public OkAndCancelAtRightCornersButtonRendererView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OkAndCancelAtRightCornersButtonRendererView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public OkAndCancelAtRightCornersButtonRendererView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void setOkButton(String okText, final CoachmarkClickListener clickListener) {

        mGroupOk.setVisibility(VISIBLE);
        mTxtOkBtn.setText(okText);
        mGroupOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDismissListener.dismiss();
                clickListener.onClicked();
            }
        });
    }

    public void setCancelButton(String okText, final CoachmarkClickListener clickListener) {

        mGroupCancel.setVisibility(VISIBLE);
        mTxtCancelBtn.setText(okText);
        mGroupCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickListener == null || clickListener.onClicked()){
                    mDismissListener.dismiss();
                }
            }
        });
    }

    public void setDismissListener(CoachmarkViewLayout dismissListener) {
        mDismissListener = dismissListener;
    }

}
