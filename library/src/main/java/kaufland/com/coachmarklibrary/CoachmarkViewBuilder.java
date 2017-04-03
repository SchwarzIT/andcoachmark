package kaufland.com.coachmarklibrary;

import android.content.Context;
import android.view.View;

import kaufland.com.coachmarklibrary.renderer.actiondescription.ActionDescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.animation.AnimationRenderer;
import kaufland.com.coachmarklibrary.renderer.buttonrenderer.ButtonRenderer;
import kaufland.com.coachmarklibrary.renderer.description.DescriptionRenderer;

public class CoachmarkViewBuilder {

    private CoachmarkView mCoachmarkView;

    public CoachmarkViewBuilder(Context context) {
        mCoachmarkView = CoachmarkView_.build(context);
    }

    public CoachmarkViewBuilder withButtonRenderer(ButtonRenderer renderer) {
        mCoachmarkView.setButtonRenderer(renderer);
        return this;
    }

    public CoachmarkView buildAroundView(View view) {
        mCoachmarkView.setView(view);
        return mCoachmarkView;
    }

    public CoachmarkViewBuilder withBackgroundColor(int color) {
        mCoachmarkView.setBackColor(color);
        return this;
    }

    public CoachmarkViewBuilder withActionDescriptionRenderers(ActionDescriptionRenderer... renderers) {
        mCoachmarkView.setActionDescriptionRenderer(renderers);
        return this;
    }

    public CoachmarkViewBuilder withDescriptionRenderer(DescriptionRenderer renderer) {
        mCoachmarkView.setDescriptionRenderer(renderer);
        return this;
    }


    public CoachmarkViewBuilder withActionDescription(View actionDescription) {
        mCoachmarkView.setActionDescription(actionDescription);
        return this;
    }

    public CoachmarkViewBuilder withDescription(View description) {
        mCoachmarkView.setDescription(description);
        return this;
    }

    public CoachmarkViewBuilder withPaddingAroundCircle(int padding) {
        mCoachmarkView.setPaddingAroundCircle(padding);
        return this;
    }

    public CoachmarkViewBuilder withAnimationRenderer(AnimationRenderer renderer){
        mCoachmarkView.setAnimationRenderer(renderer);
        return this;
    }
}
