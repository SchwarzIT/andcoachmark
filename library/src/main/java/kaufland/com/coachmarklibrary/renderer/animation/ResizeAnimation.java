package kaufland.com.coachmarklibrary.renderer.animation;


import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ResizeAnimation extends Animation {
    final int startWidth;
    final int targetWidth;
    final int startHeight;
    final int targetHeight;
    View view;

    public ResizeAnimation(View view,int startWidth,int targetWidth,int startHeight,int targetHeight) {
        this.view = view;
        this.startWidth = startWidth;
        this.targetWidth = targetWidth;
        this.startHeight=startHeight;
        this.targetHeight=targetHeight;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newWidth = (int) (startWidth - ( startWidth-targetWidth) * interpolatedTime);
        view.getLayoutParams().width = newWidth;
        int newHeight = (int) (startHeight - ( startHeight-targetHeight) * interpolatedTime);
        view.getLayoutParams().height = newHeight;
        view.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}
