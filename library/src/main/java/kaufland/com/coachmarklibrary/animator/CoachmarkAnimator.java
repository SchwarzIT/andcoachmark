package kaufland.com.coachmarklibrary.animator;

import android.view.View;
import android.view.animation.Animation;


public class CoachmarkAnimator implements Animator {

    private Animation mAnimation;

    @Override
    public void animate(View view,Animation animation) {
        if(view!=null && animation!=null){
            view.setAnimation(animation);
        }
    }

    @Override
    public Animation getAnimation() {
        return mAnimation;
    }


    public void setAnimation(Animation animation){
        mAnimation = animation;
    }
}
