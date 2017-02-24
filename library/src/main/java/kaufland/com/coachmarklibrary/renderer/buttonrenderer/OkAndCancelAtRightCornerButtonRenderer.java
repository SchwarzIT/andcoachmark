package kaufland.com.coachmarklibrary.renderer.buttonrenderer;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;

import kaufland.com.coachmarklibrary.CoachmarkClickListener;
import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;

/**
 * Created by sbra0902 on 21.02.17.
 */

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
