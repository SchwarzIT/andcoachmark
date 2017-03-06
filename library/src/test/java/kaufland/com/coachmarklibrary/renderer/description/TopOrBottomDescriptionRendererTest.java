package kaufland.com.coachmarklibrary.renderer.description;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.view.View;

import org.junit.Test;
import org.mockito.Mockito;

import kaufland.com.coachmarklibrary.R;
import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sbra0902 on 06.03.17.
 */

public class TopOrBottomDescriptionRendererTest {

    @Test
    public void renderTestTopLayout(){

        View descriptionViewMock = Mockito.mock(View.class);


        Context mContextMock = Mockito.mock(Context.class);
        when(descriptionViewMock.getContext()).thenReturn(mContextMock);
        Resources resMock = Mockito.mock(Resources.class);
        when(mContextMock.getResources()).thenReturn(resMock);
        when(resMock.getDimension(R.dimen.description_padding)).thenReturn(10f);


        CoachmarkViewLayout layout = Mockito.mock(CoachmarkViewLayout.class);
        RectF mCircleRectFMock = Mockito.mock(RectF.class);
        RectF mScreenRectFMock = Mockito.mock(RectF.class);
        when(layout.calcCircleRectF()).thenReturn(mCircleRectFMock);
        when(layout.calcScreenRectF()).thenReturn(mScreenRectFMock);

        when(mScreenRectFMock.height()).thenReturn(100f);
        when(mCircleRectFMock.centerY()).thenReturn(51f);


        new TopOrBottomDescriptionRenderer().render(layout, descriptionViewMock);

        verify(descriptionViewMock).setY(10f);
    }

    @Test
    public void renderTestBottomLayout(){

        View descriptionViewMock = Mockito.mock(View.class);


        Context mContextMock = Mockito.mock(Context.class);
        when(descriptionViewMock.getContext()).thenReturn(mContextMock);
        Resources resMock = Mockito.mock(Resources.class);
        when(mContextMock.getResources()).thenReturn(resMock);
        when(resMock.getDimension(R.dimen.description_padding)).thenReturn(10f);

        when(descriptionViewMock.getHeight()).thenReturn(5);


        CoachmarkViewLayout layout = Mockito.mock(CoachmarkViewLayout.class);
        RectF mCircleRectFMock = Mockito.mock(RectF.class);
        RectF mScreenRectFMock = Mockito.mock(RectF.class);
        when(layout.calcCircleRectF()).thenReturn(mCircleRectFMock);
        when(layout.calcScreenRectF()).thenReturn(mScreenRectFMock);

        when(mScreenRectFMock.height()).thenReturn(100f);
        when(mCircleRectFMock.centerY()).thenReturn(50f);


        new TopOrBottomDescriptionRenderer().render(layout, descriptionViewMock);

        verify(descriptionViewMock).setY(85f);
    }

}
