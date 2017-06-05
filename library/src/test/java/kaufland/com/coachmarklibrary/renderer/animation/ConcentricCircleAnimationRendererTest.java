package kaufland.com.coachmarklibrary.renderer.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.RectF;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import kaufland.com.coachmarklibrary.CoachmarkView_;
import kaufland.com.coachmarklibrary.ReflectionUtil;
import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;
import kaufland.com.coachmarklibrary.renderer.circle.CircleView;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

/**
 * Created by sbra0902 on 14.03.17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ConcentricCircleAnimationRenderer.class, ValueAnimator.class})
public class ConcentricCircleAnimationRendererTest {

    @Test
    public void testAnimate() {


        CoachmarkViewLayout layoutMock = Mockito.mock(CoachmarkViewLayout.class);

        AnimationListener animationListenerMock = Mockito.mock(AnimationListener.class);

        CircleView circleViewMock = Mockito.mock(CircleView.class);

        RectF rectFMock = Mockito.mock(RectF.class);
        RectF screenRectFMock = Mockito.mock(RectF.class);

        when(rectFMock.width()).thenReturn(10f);
        when(rectFMock.centerX()).thenReturn(3f);
        when(rectFMock.centerY()).thenReturn(3f);

        when(screenRectFMock.height()).thenReturn(100f);

        when(layoutMock.calcScreenRectF()).thenReturn(screenRectFMock);

        ValueAnimator valueAnimatorMock = Mockito.mock(ValueAnimator.class);
        PowerMockito.mockStatic(ValueAnimator.class);
        PowerMockito.when(ValueAnimator.ofFloat(100f, 5f)).thenReturn(valueAnimatorMock);

        when(valueAnimatorMock.getAnimatedValue()).thenReturn(2f);

        ArgumentCaptor<ValueAnimator.AnimatorUpdateListener> updateCaptor = ArgumentCaptor.forClass(ValueAnimator.AnimatorUpdateListener.class);

        ArgumentCaptor<Animator.AnimatorListener> listenerCaptor = ArgumentCaptor.forClass(Animator.AnimatorListener.class);



        when(layoutMock.calcCircleRectF()).thenReturn(rectFMock);

        new ConcentricCircleAnimationRenderer.Builder().build().animate(layoutMock, circleViewMock, animationListenerMock);

        verify(valueAnimatorMock).addUpdateListener(updateCaptor.capture());
        verify(valueAnimatorMock).addListener(listenerCaptor.capture());
        verify(layoutMock, times(1)).calcCircleRectF();
        verify(layoutMock, times(1)).calcScreenRectF();

        verify(circleViewMock).setCenterX(3f);
        verify(circleViewMock).setCenterY(3f);

        listenerCaptor.getValue().onAnimationEnd(null);

        verify(animationListenerMock, times(1)).onAnimationFinished();

        updateCaptor.getValue().onAnimationUpdate(valueAnimatorMock);

        verify(circleViewMock, times(1)).setRadius(2f);
        verify(circleViewMock, times(1)).bringToFront();
        verify(circleViewMock, times(1)).forceLayout();

    }

    @Test
    public void testBuilder() throws NoSuchFieldException, IllegalAccessException {

        ConcentricCircleAnimationRenderer.Builder mBuilder = new ConcentricCircleAnimationRenderer.Builder();

        mBuilder.withDuration(300);

        ConcentricCircleAnimationRenderer mRenderer = ReflectionUtil.fieldGet(ConcentricCircleAnimationRenderer.Builder.class, mBuilder, "renderer");


        junit.framework.Assert.assertEquals(300, ReflectionUtil.fieldGet(ConcentricCircleAnimationRenderer.class, mRenderer, "mAnimationDuration"));

    }
}
