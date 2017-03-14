package kaufland.com.coachmarklibrary.renderer.circle;


import android.content.Context;

import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;

public class WholeScreenCircleRenderer implements CircleRenderer {

    private CircleView mCircleView;

    public WholeScreenCircleRenderer(Context context){
        mCircleView = CircleView_.build(context);
    }

    @Override
    public CircleView render(CoachmarkViewLayout layout) {

        mCircleView.setCenterX(layout.calcCircleRectF().centerX());
        mCircleView.setCenterY(layout.calcCircleRectF().centerY());
        mCircleView.setRadius(layout.calcScreenRectF().width()/2);

        if(mCircleView.getParent()==null){
            layout.addView(mCircleView);
        }

        return mCircleView;
    }
}
