package kaufland.com.coachmarklibrary.renderer.animation;

import android.graphics.RectF;

import org.junit.Test;
import org.mockito.Mockito;

import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;
import kaufland.com.coachmarklibrary.renderer.circle.CircleView;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sbra0902 on 14.03.17.
 */

public class NoAnimationRendererTest {

    @Test
    public void testAnimate() {


        CoachmarkViewLayout layoutMock = Mockito.mock(CoachmarkViewLayout.class);

        AnimationListener animationListenerMock = Mockito.mock(AnimationListener.class);

        CircleView circleViewMock = Mockito.mock(CircleView.class);

        RectF rectFMock = Mockito.mock(RectF.class);

        when(rectFMock.width()).thenReturn(10f);
        when(rectFMock.centerX()).thenReturn(3f);
        when(rectFMock.centerY()).thenReturn(3f);

        when(layoutMock.calcCircleRectF()).thenReturn(rectFMock);

        new NoAnimationRenderer().animate(layoutMock, circleViewMock, animationListenerMock);

        verify(layoutMock, times(1)).calcCircleRectF();

        verify(circleViewMock).setRadius(5f);
        verify(circleViewMock).setCenterX(3f);
        verify(circleViewMock).setCenterY(3f);

        verify(animationListenerMock, times(1)).onAnimationFinished();



    }
}
