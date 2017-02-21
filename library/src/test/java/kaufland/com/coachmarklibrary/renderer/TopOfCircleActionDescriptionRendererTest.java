package kaufland.com.coachmarklibrary.renderer;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

import kaufland.com.coachmarklibrary.renderer.actiondescription.BelowCircleActionDescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.actiondescription.TopOfCircleActionDescriptionRenderer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by sbra0902 on 13.02.17.
 */
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


        RectF screenRectangle = new RectF(0, 0, 300, 300);
        RectF circleRectangle = new RectF(50, 50, 75, 75);

        new TopOfCircleActionDescriptionRenderer().render(screenRectangle, circleRectangle, actionDescription, actionArrow);

        verify(actionDescription, times(1)).setX(52.5f);
        verify(actionDescription, times(1)).setY(20);

        verify(actionArrow, times(1)).setX(57.5f);
        verify(actionArrow, times(1)).setY(40);
    }


    @Test
    public void isRenderingPossibleTest() {

        RectF screenRectangle = new RectF(0, 0, 300, 300);
        RectF circleRectangle = new RectF(50, 50, 75, 75);
        Rect actionDescriptionRectangle = new Rect(0, 0, 50, 20);
        Rect actionArrowRectangle = new Rect(0,0, 10, 10);

        Assert.assertTrue(new TopOfCircleActionDescriptionRenderer().isRenderingPossible(screenRectangle, circleRectangle, actionDescriptionRectangle, actionArrowRectangle));

    }

    @Test
    public void renderingNotPossibleNotEnoughLeftSpaceTest() {

        RectF screenRectangle = new RectF(0, 0, 300, 300);
        RectF circleRectangle = new RectF(50, 50, 75, 75);
        Rect actionDescriptionRectangle = new Rect(0, 0, 126, 20);
        Rect actionArrowRectangle = new Rect(0,0, 10, 10);

        Assert.assertFalse(new TopOfCircleActionDescriptionRenderer().isRenderingPossible(screenRectangle, circleRectangle, actionDescriptionRectangle, actionArrowRectangle));

    }

    @Test
    public void renderingNotPossibleNotEnoughTopSpaceTest() {

        RectF screenRectangle = new RectF(0, 0, 300, 95);
        RectF circleRectangle = new RectF(50, 20, 75, 75);
        Rect actionDescriptionRectangle = new Rect(0, 0, 20, 5);
        Rect actionArrowRectangle = new Rect(0,0, 10, 15);

        Assert.assertFalse(new TopOfCircleActionDescriptionRenderer().isRenderingPossible(screenRectangle, circleRectangle, actionDescriptionRectangle, actionArrowRectangle));

    }

}
