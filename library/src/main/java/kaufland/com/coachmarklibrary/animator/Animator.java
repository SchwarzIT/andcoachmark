package kaufland.com.coachmarklibrary.animator;


import android.view.View;
import android.view.animation.Animation;

public interface Animator {

    void animate(View view,Animation animation);

    Animation getAnimation();

}
