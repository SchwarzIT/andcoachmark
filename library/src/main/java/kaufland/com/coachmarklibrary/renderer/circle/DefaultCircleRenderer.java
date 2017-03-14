package kaufland.com.coachmarklibrary.renderer.circle;

import android.content.Context;
import android.graphics.RectF;

import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;


public class DefaultCircleRenderer implements CircleRenderer {

    private CircleView mCircleView;

    public DefaultCircleRenderer(Context context){
        mCircleView= CircleView_.build(context);
    }

    @Override
    public CircleView render(CoachmarkViewLayout layout) {

        mCircleView.setRadius(layout.calcCircleRectF().width()/2);
        mCircleView.setCenterX(layout.calcCircleRectF().centerX());
        mCircleView.setCenterY(layout.calcCircleRectF().centerY());

        if(mCircleView.getParent()==null) {
            layout.addView(mCircleView);
        }
        return mCircleView;
    }
}
