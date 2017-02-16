package kaufland.com.coachmarklibrary;

import android.content.Context;
import android.view.View;

import kaufland.com.coachmarklibrary.renderer.actiondescription.ActionDescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.description.DescriptionRenderer;

/**
 * Created by sbra0902 on 15.02.17.
 */

public class CoachmarkViewBuilder {

    private CoachmarkView mCoachmarkView;

    public CoachmarkViewBuilder(Context context) {
        mCoachmarkView = CoachmarkView_.build(context);
    }

    public CoachmarkViewBuilder withOkButton(String okText, CoachmarkClickListener clickListener) {
        mCoachmarkView.setOkButton(okText, clickListener);
        return this;
    }

    public CoachmarkViewBuilder withCancelButton(String cancelText, CoachmarkClickListener clickListener){
        mCoachmarkView.setCancelButton(cancelText, clickListener);
        return this;
    }

    public CoachmarkView buildAroundView(View view){
        mCoachmarkView.setView(view);
        return mCoachmarkView;
    }

    public CoachmarkViewBuilder withBackgroundColor(int color){
        mCoachmarkView.setBackColor(color);
        return this;
    }

    public CoachmarkViewBuilder dismissOnTouch(){
        mCoachmarkView.setDismissOnClick(true);
        return this;
    }

    public CoachmarkViewBuilder withActionDescriptionRenderers(ActionDescriptionRenderer... renderers){
        mCoachmarkView.setActionDescriptionRenderer(renderers);
        return this;
    }

    public CoachmarkViewBuilder withDescriptionRenderer(DescriptionRenderer renderer){
        mCoachmarkView.setDescriptionRenderer(renderer);
        return this;
    }

    public CoachmarkViewBuilder withActionDescription(View actionDescription){
        mCoachmarkView.setActionDescription(actionDescription);
        return this;
    }

    public CoachmarkViewBuilder withDescription(View description){
        mCoachmarkView.setDescription(description);
        return this;
    }

    public CoachmarkViewBuilder withPaddingAroundCircle(int padding){
        mCoachmarkView.setPaddingAroundCircle(padding);
        return this;
    }

}
