package kaufland.com.coachmarklibrary.renderer.buttonrenderer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import kaufland.com.coachmarklibrary.CoachmarkClickListener;
import kaufland.com.coachmarklibrary.R;
import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;

public class DismissOnTouchNoButtonRenderer implements ButtonRenderer {

    private CoachmarkClickListener mListener;

    private View inflated;

    private DismissOnTouchNoButtonRenderer() {
        //hide constructor should be used only with Builder
    }

    @Override
    public View render(final CoachmarkViewLayout layout) {

        if (inflated == null) {
            LayoutInflater inflater = (LayoutInflater) layout.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflated = inflater.inflate(R.layout.dismiss_no_button_view, null);
            layout.addView(inflated);
        }


        inflated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener == null || mListener.onClicked()) {
                    layout.dismiss();
                }
            }
        });

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        inflated.setLayoutParams(params);
        inflated.setVisibility(View.VISIBLE);

        return inflated;

    }

    public static class Builder {

        private DismissOnTouchNoButtonRenderer renderer;

        public Builder() {
            renderer = new DismissOnTouchNoButtonRenderer();
        }


        public Builder withListener(CoachmarkClickListener listener) {
            renderer.mListener = listener;
            return this;
        }

        public DismissOnTouchNoButtonRenderer build() {
            return renderer;
        }

    }
}
