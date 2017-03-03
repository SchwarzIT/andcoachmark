package kaufland.com.coachmarklibrary.renderer.actiondescription;

import android.graphics.RectF;
import android.view.View;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;
import kaufland.com.coachmarklibrary.renderer.actiondescription.RightOfCircleActionDescriptionRenderer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(RobolectricTestRunner.class)
public class RightOfCircleActionDescriptionRendererTest {

    @Test
    public void renderTest() {

        View actionDescription = Mockito.mock(View.class);
        View actionArrow = Mockito.mock(View.class);

        Mockito.when(actionArrow.getWidth()).thenReturn(30);
        Mockito.when(actionDescription.getWidth()).thenReturn(15);
        Mockito.when(actionArrow.getHeight()).thenReturn(10);
        Mockito.when(actionDescription.getHeight()).thenReturn(20);


        CoachmarkViewLayout layoutMock = Mockito.mock(CoachmarkViewLayout.class);

        Mockito.when(layoutMock.calcScreenRectF()).thenReturn(new RectF(0, 0, 300, 95));
        Mockito.when(layoutMock.calcCircleRectF()).thenReturn(new RectF(50, 50, 75, 75));

        new RightOfCircleActionDescriptionRenderer().render(layoutMock, actionDescription, actionArrow);

        verify(actionDescription, times(1)).setX(105.0f);
        verify(actionDescription, times(1)).setY(52.5f);

        verify(actionArrow, times(1)).setX(75);
        verify(actionArrow, times(1)).setY(57.5f);
    }


    @Test
    public void isRenderingPossibleTest() {

        CoachmarkViewLayout layoutMock = Mockito.mock(CoachmarkViewLayout.class);

        Mockito.when(layoutMock.calcScreenRectF()).thenReturn(new RectF(0, 0, 300, 300));
        Mockito.when(layoutMock.calcCircleRectF()).thenReturn(new RectF(50, 50, 75, 75));
        Mockito.when(layoutMock.calcActionDescriptionRect()).thenReturn(new RectF(0, 0, 39, 20));
        Mockito.when(layoutMock.calcActionArrowRect()).thenReturn(new RectF(0, 0, 10, 10));

        Assert.assertTrue(new RightOfCircleActionDescriptionRenderer().isRenderingPossible(layoutMock));

    }

    @Test
    public void renderingNotPossibleNotEnoughRightSpaceTest() {

        CoachmarkViewLayout layoutMock = Mockito.mock(CoachmarkViewLayout.class);

        Mockito.when(layoutMock.calcScreenRectF()).thenReturn(new RectF(0, 0, 95, 300));
        Mockito.when(layoutMock.calcCircleRectF()).thenReturn(new RectF(50, 50, 75, 75));
        Mockito.when(layoutMock.calcActionDescriptionRect()).thenReturn(new RectF(0, 0, 10, 20));
        Mockito.when(layoutMock.calcActionArrowRect()).thenReturn(new RectF(0, 0, 10, 10));

        Assert.assertFalse(new RightOfCircleActionDescriptionRenderer().isRenderingPossible(layoutMock));

    }

}
