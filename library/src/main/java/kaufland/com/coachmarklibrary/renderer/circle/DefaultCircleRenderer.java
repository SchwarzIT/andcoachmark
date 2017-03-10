package kaufland.com.coachmarklibrary.renderer.circle;

import android.content.Context;
import android.graphics.RectF;

import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;


public class DefaultCircleRenderer implements CircleRenderer {

    CircleView mCircleView;

    public DefaultCircleRenderer(Context context){
        mCircleView= CircleView_.build(context);
    }

    @Override
    public RectF calcCircleStartRectF(CoachmarkViewLayout layout) {
        return layout.calcCircleRectF();
    }

    @Override
    public CircleView render(CoachmarkViewLayout layout) {

        mCircleView.setStartRectF(calcCircleStartRectF(layout));
        mCircleView.setClickedViewRectF(layout.calcCircleRectF());

        if(mCircleView.getParent()==null) {
            layout.addView(mCircleView);
        }
        return mCircleView;
    }
}
