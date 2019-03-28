package kaufland.com.coachmarklibrary.renderer.buttonrenderer;

import android.content.Context;
import android.widget.TextView;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import kaufland.com.coachmarklibrary.CoachmarkClickListener;
import kaufland.com.coachmarklibrary.ReflectionUtil;
import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by sbra0902 on 03.03.17.
 */
public class OkAndCancelAtRightCornerButtonRendererTest {

    @Test
    public void testBuilder() throws NoSuchFieldException, IllegalAccessException {

        OkAndCancelAtRightCornerButtonRenderer.Builder mBuilder = new OkAndCancelAtRightCornerButtonRenderer.Builder(Mockito.mock(Context.class));

        CoachmarkClickListener clickMock = Mockito.mock(CoachmarkClickListener.class);
        OkAndCancelAtRightCornerButtonRendererView viewMock = Mockito.mock(OkAndCancelAtRightCornerButtonRendererView.class);
        ReflectionUtil.setField(OkAndCancelAtRightCornerButtonRenderer.class, mBuilder.build(), "mView", viewMock);

        OkAndCancelAtRightCornerButtonRenderer renderer = mBuilder.withOkButton("Ok", clickMock).withCancelButton("Cancel", clickMock).build();


        verify(viewMock).setOkButton("Ok", clickMock);
        verify(viewMock).setCancelButton("Cancel", clickMock);

        TextView btn = ReflectionUtil.getField(OkAndCancelAtRightCornerButtonRendererView.class, viewMock, "mTxtOkBtn");

        Assert.assertEquals(btn.getText(), "Ok");
    }

    @Test
    public void renderTest() throws NoSuchFieldException, IllegalAccessException {

        OkAndCancelAtRightCornerButtonRenderer.Builder mBuilder = new OkAndCancelAtRightCornerButtonRenderer.Builder(Mockito.mock(Context.class));
        OkAndCancelAtRightCornerButtonRenderer renderer = mBuilder.build();

        OkAndCancelAtRightCornerButtonRendererView viewMock = Mockito.mock(OkAndCancelAtRightCornerButtonRendererView.class);

        ReflectionUtil.setField(OkAndCancelAtRightCornerButtonRenderer.class, renderer, "mView", viewMock);


        CoachmarkViewLayout layout = Mockito.mock(CoachmarkViewLayout.class);
        renderer.render(layout);

        verify(viewMock, times(1)).setDismissListener(layout);
        verify(layout, times(1)).addView(viewMock);
    }
}
