package kaufland.com.coachmarklibrary.renderer.buttonrenderer;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import kaufland.com.coachmarklibrary.CoachmarkClickListener;
import kaufland.com.coachmarklibrary.R;
import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;

/**
 * Created by sbra0902 on 21.02.17.
 */

public class OkBelowDescriptionButtonRenderer implements ButtonRenderer {


    private int mBorder = 0;

    private String mOkText = "";

    private CoachmarkClickListener mListener;
    private Integer mColor;

    private View inflated;

    private OkBelowDescriptionButtonRenderer() {
        //hide constructor should be used only with Builder
    }

    @Override
    public void render(final CoachmarkViewLayout layout) {

        if (inflated == null) {
            LayoutInflater inflater = (LayoutInflater) layout.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflated = inflater.inflate(R.layout.ok_below_description_button_view, null);
            layout.addView(inflated);
        }


        TextView mTxtOkBtn = (TextView) inflated.findViewById(R.id.txt_ok_btn);
        LinearLayout mGroupOk = (LinearLayout) inflated.findViewById(R.id.group_ok);

        mTxtOkBtn.setText(mOkText);
        mGroupOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener == null || mListener.onClicked()) {
                    layout.dismiss();
                }
            }
        });

        if (mBorder != 0) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setStroke(mBorder, mColor != null ? mColor : layout.getContext().getColor(R.color.default_border_color));
            mGroupOk.setBackground(gradientDrawable);

        }

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        inflated.setLayoutParams(params);


        inflated.post(new Runnable() {
            @Override
            public void run() {
                RectF descriptionRectangle = layout.calcDescriptionRect();
                inflated.setX(descriptionRectangle.centerX() - ((float)inflated.getWidth() / 2));
                inflated.setY(descriptionRectangle.bottom + inflated.getContext().getResources().getDimension(R.dimen.button_padding));
                inflated.setVisibility(View.VISIBLE);
            }
        });


    }

    public static class Builder {

        private OkBelowDescriptionButtonRenderer renderer;

        public Builder() {
            renderer = new OkBelowDescriptionButtonRenderer();
        }

        public Builder withBorder(int border, Integer color) {
            renderer.mBorder = border;
            renderer.mColor = color;
            return this;
        }

        public Builder withOkButton(String text, CoachmarkClickListener listener) {
            renderer.mOkText = text;
            renderer.mListener = listener;
            return this;
        }

        public OkBelowDescriptionButtonRenderer build() {
            return renderer;
        }

    }
}
