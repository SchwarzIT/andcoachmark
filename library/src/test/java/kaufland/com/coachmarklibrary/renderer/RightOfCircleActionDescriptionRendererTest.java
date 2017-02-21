package kaufland.com.coachmarklibrary.renderer;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

import kaufland.com.coachmarklibrary.renderer.actiondescription.LeftOfCircleActionDescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.actiondescription.RightOfCircleActionDescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.actiondescription.TopOfCircleActionDescriptionRenderer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by sbra0902 on 13.02.17.
 */
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


        RectF screenRectangle = new RectF(0, 0, 300, 300);
        RectF circleRectangle = new RectF(50, 50, 75, 75);

        new RightOfCircleActionDescriptionRenderer().render(screenRectangle, circleRectangle, actionDescription, actionArrow);

        verify(actionDescription, times(1)).setX(105.0f);
        verify(actionDescription, times(1)).setY(52.5f);

        verify(actionArrow, times(1)).setX(75);
        verify(actionArrow, times(1)).setY(57.5f);
    }


    @Test
    public void isRenderingPossibleTest() {

        RectF screenRectangle = new RectF(0, 0, 300, 300);
        RectF circleRectangle = new RectF(50, 50, 75, 75);
        Rect actionDescriptionRectangle = new Rect(0, 0, 39, 20);
        Rect actionArrowRectangle = new Rect(0,0, 10, 10);

        Assert.assertTrue(new RightOfCircleActionDescriptionRenderer().isRenderingPossible(screenRectangle, circleRectangle, actionDescriptionRectangle, actionArrowRectangle));

    }

    @Test
    public void renderingNotPossibleNotEnoughRightSpaceTest() {

        RectF screenRectangle = new RectF(0, 0, 95, 300);
        RectF circleRectangle = new RectF(50, 50, 75, 75);
        Rect actionDescriptionRectangle = new Rect(0, 0, 10, 20);
        Rect actionArrowRectangle = new Rect(0,0, 10, 10);

        Assert.assertFalse(new RightOfCircleActionDescriptionRenderer().isRenderingPossible(screenRectangle, circleRectangle, actionDescriptionRectangle, actionArrowRectangle));

    }

}
