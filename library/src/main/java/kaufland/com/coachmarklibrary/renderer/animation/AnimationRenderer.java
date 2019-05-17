package kaufland.com.coachmarklibrary.renderer.animation;


import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;
import kaufland.com.coachmarklibrary.renderer.circle.CircleView;

public interface AnimationRenderer {

    void animate(CoachmarkViewLayout layout, CircleView view, AnimationListener animationListener);
}
