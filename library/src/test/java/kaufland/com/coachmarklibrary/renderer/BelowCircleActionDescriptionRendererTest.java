package kaufland.com.coachmarklibrary.renderer;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.robolectric.RobolectricTestRunner;

import kaufland.com.coachmarklibrary.CoachmarkView_;
import kaufland.com.coachmarklibrary.renderer.actiondescription.ActionDescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.actiondescription.BelowCircleActionDescriptionRenderer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sbra0902 on 13.02.17.
 */
@RunWith(RobolectricTestRunner.class)
public class BelowCircleActionDescriptionRendererTest {

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

        new BelowCircleActionDescriptionRenderer().render(screenRectangle, circleRectangle, actionDescription, actionArrow);

        verify(actionDescription, times(1)).setX(52.5f);
        verify(actionDescription, times(1)).setY(85);

        verify(actionArrow, times(1)).setX(57.5f);
        verify(actionArrow, times(1)).setY(75);
    }


    @Test
    public void isRenderingPossibleTest() {

        RectF screenRectangle = new RectF(0, 0, 300, 300);
        RectF circleRectangle = new RectF(50, 50, 75, 75);
        Rect actionDescriptionRectangle = new Rect(0, 0, 50, 20);
        Rect actionArrowRectangle = new Rect(0,0, 10, 10);

        Assert.assertTrue(new BelowCircleActionDescriptionRenderer().isRenderingPossible(screenRectangle, circleRectangle, actionDescriptionRectangle, actionArrowRectangle));

    }

    @Test
    public void renderingNotPossibleNotEnoughtLeftSpaceTest() {

        RectF screenRectangle = new RectF(0, 0, 300, 300);
        RectF circleRectangle = new RectF(50, 50, 75, 75);
        Rect actionDescriptionRectangle = new Rect(0, 0, 126, 20);
        Rect actionArrowRectangle = new Rect(0,0, 10, 10);

        Assert.assertFalse(new BelowCircleActionDescriptionRenderer().isRenderingPossible(screenRectangle, circleRectangle, actionDescriptionRectangle, actionArrowRectangle));

    }

    @Test
    public void renderingNotPossibleNotEnoughtBottomSpaceTest() {

        RectF screenRectangle = new RectF(0, 0, 300, 95);
        RectF circleRectangle = new RectF(50, 50, 75, 75);
        Rect actionDescriptionRectangle = new Rect(0, 0, 20, 5);
        Rect actionArrowRectangle = new Rect(0,0, 10, 15);

        Assert.assertFalse(new BelowCircleActionDescriptionRenderer().isRenderingPossible(screenRectangle, circleRectangle, actionDescriptionRectangle, actionArrowRectangle));

    }

}
