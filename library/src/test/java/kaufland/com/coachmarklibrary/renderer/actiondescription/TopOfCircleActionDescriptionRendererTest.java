package kaufland.com.coachmarklibrary.renderer.actiondescription;

import android.graphics.RectF;
import android.view.View;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;
import kaufland.com.coachmarklibrary.renderer.actiondescription.TopOfCircleActionDescriptionRenderer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(RobolectricTestRunner.class)
public class TopOfCircleActionDescriptionRendererTest {

    @Test
    public void renderTest() {

        View actionDescription = Mockito.mock(View.class);
        View actionArrow = Mockito.mock(View.class);

        Mockito.when(actionArrow.getWidth()).thenReturn(10);
        Mockito.when(actionDescription.getWidth()).thenReturn(20);
        Mockito.when(actionArrow.getHeight()).thenReturn(10);
        Mockito.when(actionDescription.getHeight()).thenReturn(20);

        CoachmarkViewLayout layoutMock = Mockito.mock(CoachmarkViewLayout.class);

        Mockito.when(layoutMock.calcScreenRectF()).thenReturn(new RectF(0, 0, 300, 300));
        Mockito.when(layoutMock.calcCircleRectF()).thenReturn(new RectF(50, 50, 75, 75));


        new TopOfCircleActionDescriptionRenderer().render(layoutMock, actionDescription, actionArrow);

        verify(actionDescription, times(1)).setX(52.5f);
        verify(actionDescription, times(1)).setY(20);

        verify(actionArrow, times(1)).setX(57.5f);
        verify(actionArrow, times(1)).setY(40);
    }


    @Test
    public void isRenderingPossibleTest() {

        CoachmarkViewLayout layoutMock = Mockito.mock(CoachmarkViewLayout.class);

        Mockito.when(layoutMock.calcScreenRectF()).thenReturn(new RectF(0, 0, 300, 300));
        Mockito.when(layoutMock.calcCircleRectF()).thenReturn(new RectF(50, 50, 75, 75));
        Mockito.when(layoutMock.calcActionDescriptionRect()).thenReturn(new RectF(0, 0, 50, 20));
        Mockito.when(layoutMock.calcActionArrowRect()).thenReturn(new RectF(0, 0, 10, 10));

        Assert.assertTrue(new TopOfCircleActionDescriptionRenderer().isRenderingPossible(layoutMock));

    }

    @Test
    public void renderingNotPossibleNotEnoughLeftSpaceTest() {

        CoachmarkViewLayout layoutMock = Mockito.mock(CoachmarkViewLayout.class);

        Mockito.when(layoutMock.calcScreenRectF()).thenReturn(new RectF(0, 0, 300, 300));
        Mockito.when(layoutMock.calcCircleRectF()).thenReturn(new RectF(50, 50, 75, 75));
        Mockito.when(layoutMock.calcActionDescriptionRect()).thenReturn(new RectF(0, 0, 126, 20));
        Mockito.when(layoutMock.calcActionArrowRect()).thenReturn(new RectF(0, 0, 10, 10));

        Assert.assertFalse(new TopOfCircleActionDescriptionRenderer().isRenderingPossible(layoutMock));
    }

    @Test
    public void renderingNotPossibleNotEnoughTopSpaceTest() {

        CoachmarkViewLayout layoutMock = Mockito.mock(CoachmarkViewLayout.class);

        Mockito.when(layoutMock.calcScreenRectF()).thenReturn(new RectF(0, 0, 300, 95));
        Mockito.when(layoutMock.calcCircleRectF()).thenReturn(new RectF(50, 20, 75, 75));
        Mockito.when(layoutMock.calcActionDescriptionRect()).thenReturn(new RectF(0, 0, 20, 5));
        Mockito.when(layoutMock.calcActionArrowRect()).thenReturn(new RectF(0, 0, 10, 15));

        Assert.assertFalse(new TopOfCircleActionDescriptionRenderer().isRenderingPossible(layoutMock));
    }

}
