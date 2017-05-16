package kaufland.com.coachmarklibrary.renderer.buttonrenderer;

import android.content.Context;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import kaufland.com.coachmarklibrary.R;
import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;

public class UnderlyingActionButtonRenderer implements ButtonRenderer {

    private View inflated;

    private UnderlyingAction mAction;
    public interface UnderlyingAction{
        void onClick();
    }

    private UnderlyingActionButtonRenderer() {
        //hide constructor should be used only with Builder
    }

    @Override
    public View render(final CoachmarkViewLayout layout) {

        if (inflated == null) {
            LayoutInflater inflater = (LayoutInflater) layout.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflated = inflater.inflate(R.layout.underlying_action_button_view, null);
            layout.addView(inflated);
        }

        RectF circleRectF = layout.calcCircleRectF();

        inflated.setX(circleRectF.left);
        inflated.setY(circleRectF.top);

        FrameLayout.LayoutParams mLayoutParams = new FrameLayout.LayoutParams((int) circleRectF.width(), (int) circleRectF.height());

        inflated.setLayoutParams(mLayoutParams);




        inflated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAction != null) {
                   mAction.onClick();
                }
                    layout.dismiss();
            }
        });

//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
//        inflated.setLayoutParams(params);
//        inflated.setVisibility(View.VISIBLE);

        return inflated;

    }

    public static class Builder {

        private UnderlyingActionButtonRenderer renderer;

        public Builder() {
            renderer = new UnderlyingActionButtonRenderer();
        }


        public Builder withUnderlyingView(View clickView) {

            return this;
        }
        public Builder withUnderlyingAction(UnderlyingAction underlyingAction){
            renderer.mAction = underlyingAction;
            return this;
        }

        public UnderlyingActionButtonRenderer build() {
            return renderer;
        }

    }
}
