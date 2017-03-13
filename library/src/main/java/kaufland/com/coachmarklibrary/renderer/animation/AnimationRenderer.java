package kaufland.com.coachmarklibrary.renderer.animation;


import kaufland.com.coachmarklibrary.CoachmarkView;

public interface AnimationRenderer {

    void animate(CoachmarkView coachmarkView);

    boolean isAnimationBeforeRendering();
}
