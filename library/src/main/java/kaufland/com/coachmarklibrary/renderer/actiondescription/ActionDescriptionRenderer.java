package kaufland.com.coachmarklibrary.renderer.actiondescription;

import android.view.View;

import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;


public interface ActionDescriptionRenderer {

    void render(CoachmarkViewLayout layout, View actionDescription, View actionArrow);

    boolean isRenderingPossible(CoachmarkViewLayout layout);

}
