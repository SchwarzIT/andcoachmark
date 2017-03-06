package kaufland.com.coachmarklibrary.renderer.buttonrenderer;

import android.content.Context;
import android.view.View;

import kaufland.com.coachmarklibrary.CoachmarkClickListener;
import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;


public class OkAndCancelAtRightCornerButtonRenderer implements ButtonRenderer {

    private OkAndCancelAtRightCornersButtonRendererView mView;

    private OkAndCancelAtRightCornerButtonRenderer(OkAndCancelAtRightCornersButtonRendererView view) {
        mView = view;
    }

    @Override
    public void render(final CoachmarkViewLayout layout) {
        mView.setDismissListener(layout);

        if (mView.getParent() == null) {
            layout.addView(mView);
        }

    }

    @Override
    public void makeButtonsVisible(boolean visible) {
        mView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public static class Builder {

        private OkAndCancelAtRightCornerButtonRenderer renderer;

        public Builder(Context context) {
            renderer = new OkAndCancelAtRightCornerButtonRenderer(OkAndCancelAtRightCornersButtonRendererView_.build(context));
        }

        public Builder withOkButton(String text, CoachmarkClickListener listener) {
            renderer.mView.setOkButton(text, listener);
            return this;
        }

        public Builder withCancelButton(String text, CoachmarkClickListener listener) {
            renderer.mView.setCancelButton(text, listener);
            return this;
        }

        public OkAndCancelAtRightCornerButtonRenderer build() {
            return renderer;
        }

    }
}
