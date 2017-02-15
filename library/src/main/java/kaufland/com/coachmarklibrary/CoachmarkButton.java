package kaufland.com.coachmarklibrary;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by sbra0902 on 07.02.17.
 */

public class CoachmarkButton {

    private String mButtonText = "";

    private View.OnClickListener mOnClickListener;


    public CoachmarkButton(String buttonText, View.OnClickListener onClickListener) {
        mButtonText = buttonText;
        mOnClickListener = onClickListener;
    }

    public String getButtonText() {
        return mButtonText;
    }

    public void setButtonText(String buttonText) {
        mButtonText = buttonText;
    }

    public View.OnClickListener getOnClickListener() {
        return mOnClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }
}
