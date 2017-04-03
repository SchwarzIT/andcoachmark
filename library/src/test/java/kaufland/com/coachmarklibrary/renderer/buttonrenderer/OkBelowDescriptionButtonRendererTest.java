package kaufland.com.coachmarklibrary.renderer.buttonrenderer;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import kaufland.com.coachmarklibrary.CoachmarkClickListener;
import kaufland.com.coachmarklibrary.R;
import kaufland.com.coachmarklibrary.ReflectionUtil;
import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sbra0902 on 03.03.17.
 */
public class OkBelowDescriptionButtonRendererTest {

    @Test
    public void testBuilder() throws NoSuchFieldException, IllegalAccessException {

        OkBelowDescriptionButtonRenderer.Builder mBuilder = new OkBelowDescriptionButtonRenderer.Builder();

        CoachmarkClickListener clickMock = Mockito.mock(CoachmarkClickListener.class);

        mBuilder.withOkButton("ok", clickMock);
        mBuilder.withBorder(2, 0);
        OkBelowDescriptionButtonRenderer mBuild = mBuilder.build();

        Assert.assertEquals(clickMock, ReflectionUtil.getField(OkBelowDescriptionButtonRenderer.class, mBuild, "mListener"));
        Assert.assertEquals(0, ReflectionUtil.getField(OkBelowDescriptionButtonRenderer.class, mBuild, "mColor"));
        Assert.assertEquals(2, ReflectionUtil.getField(OkBelowDescriptionButtonRenderer.class, mBuild, "mBorder"));
        Assert.assertEquals("ok", ReflectionUtil.getField(OkBelowDescriptionButtonRenderer.class, mBuild, "mOkText"));


    }

    @Test
    public void renderTest() throws NoSuchFieldException, IllegalAccessException {

        View mMock = Mockito.mock(View.class);

        OkBelowDescriptionButtonRenderer.Builder mBuilder = new OkBelowDescriptionButtonRenderer.Builder();
        mBuilder.withOkButton("ok", null);
        mBuilder.withBorder(2, 0);
        OkBelowDescriptionButtonRenderer renderer = mBuilder.build();

        ReflectionUtil.setField(OkBelowDescriptionButtonRenderer.class, renderer, "inflated", mMock);


        TextView okBtn = Mockito.mock(TextView.class);
        when(mMock.getWidth()).thenReturn(5);
        when(mMock.findViewById(R.id.txt_ok_btn)).thenReturn(okBtn);

        LinearLayout groupOk = Mockito.mock(LinearLayout.class);
        when(mMock.findViewById(R.id.group_ok)).thenReturn(groupOk);

        Context mContextMock = Mockito.mock(Context.class);
        when(mMock.getContext()).thenReturn(mContextMock);
        Resources resMock = Mockito.mock(Resources.class);
        when(mContextMock.getResources()).thenReturn(resMock);
        when(resMock.getDimension(R.dimen.button_padding)).thenReturn(0f);


        CoachmarkViewLayout layout = Mockito.mock(CoachmarkViewLayout.class);
        when(layout.calcDescriptionRect()).thenReturn(new RectF(0, 0, 0, 0));
        renderer.render(layout);


        verify(okBtn, times(1)).setText("ok");
        verify(groupOk, times(1)).setOnClickListener(any(View.OnClickListener.class));
        verify(groupOk, times(1)).setBackground(any(GradientDrawable.class));
        verify(mMock, times(1)).setLayoutParams(any(FrameLayout.LayoutParams.class));

        ArgumentCaptor<Runnable> fooCaptor = ArgumentCaptor.forClass(Runnable.class);

        verify(mMock).post(fooCaptor.capture());

        fooCaptor.getValue().run();

        verify(layout).calcDescriptionRect();

        verify(mMock).setX(-2.5F);
        verify(mMock).setY(0);
        verify(mMock).setVisibility(View.VISIBLE);


    }

}
