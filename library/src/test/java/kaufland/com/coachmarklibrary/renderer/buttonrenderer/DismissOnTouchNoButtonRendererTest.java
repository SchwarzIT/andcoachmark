package kaufland.com.coachmarklibrary.renderer.buttonrenderer;

import android.view.View;
import android.widget.FrameLayout;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import kaufland.com.coachmarklibrary.CoachmarkClickListener;
import kaufland.com.coachmarklibrary.ReflectionUtil;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by sbra0902 on 03.03.17.
 */
public class DismissOnTouchNoButtonRendererTest {

    @Test
    public void testBuilder() throws NoSuchFieldException, IllegalAccessException {

        DismissOnTouchNoButtonRenderer.Builder mBuilder = new DismissOnTouchNoButtonRenderer.Builder();

        CoachmarkClickListener clickMock = Mockito.mock(CoachmarkClickListener.class);

        mBuilder.withListener(clickMock);
        DismissOnTouchNoButtonRenderer mBuild = mBuilder.build();

        Assert.assertEquals(clickMock, ReflectionUtil.getField(DismissOnTouchNoButtonRenderer.class, mBuild, "mListener"));

    }

    @Test
    public void renderTest() throws NoSuchFieldException, IllegalAccessException {

        View mMock = Mockito.mock(View.class);
        DismissOnTouchNoButtonRenderer renderer = new DismissOnTouchNoButtonRenderer.Builder().build();
        ReflectionUtil.setField(DismissOnTouchNoButtonRenderer.class, renderer, "inflated", mMock);


        renderer.render(null);


        verify(mMock, times(1)).setOnClickListener(any(View.OnClickListener.class));
        verify(mMock).setLayoutParams(any(FrameLayout.LayoutParams.class));
        verify(mMock).setVisibility(View.VISIBLE);


    }

}
