package kaufland.com.coachmarklibrary;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import kaufland.com.coachmarklibrary.renderer.actiondescription.ActionDescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.animation.AnimationRenderer;
import kaufland.com.coachmarklibrary.renderer.animation.NoAnimationRenderer;
import kaufland.com.coachmarklibrary.renderer.buttonrenderer.ButtonRenderer;
import kaufland.com.coachmarklibrary.renderer.circle.CircleView;
import kaufland.com.coachmarklibrary.renderer.description.DescriptionRenderer;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sbra0902 on 15.03.17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, sdk = 22)
public class CoachmarkViewTest {

    private Activity mActivity;
    private CoachmarkView mView;

    @Before
    public void init() {
        ActivityController<Activity> activityController = Robolectric.buildActivity(Activity.class).create().start().resume()
                .visible();

        mActivity = activityController.get();

        mView = new CoachmarkView(mActivity);
    }

    @Test
    public void testCalcCircleRectF() {

        View mClickedViewMock = Mockito.mock(View.class);

        when(mClickedViewMock.getHeight()).thenReturn(10);
        when(mClickedViewMock.getWidth()).thenReturn(30);


        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                int[] mArg = (int[]) args[0];
                mArg[0] = 3;
                mArg[1] = 2;
                return null;
            }
        }).when(mClickedViewMock).getLocationInWindow(any(int[].class));

        mView.setView(mClickedViewMock);
        mView.marginArroundCircle = 2;


        RectF mRectF = mView.calcCircleRectF();

        Assert.assertEquals(34.0f, mRectF.width());
        Assert.assertEquals(34.0f, mRectF.height());
        Assert.assertEquals(1.0f, mRectF.left);
        Assert.assertEquals(-10.0f, mRectF.top);


    }

    @Test
    public void testCalcScreenRectF() {


        mActivity.addContentView(mView, new LinearLayout.LayoutParams(200, 200));

        RectF mRectF = mView.calcScreenRectF();

        Assert.assertEquals(200.0f, mRectF.width());
        Assert.assertEquals(200.0f, mRectF.height());
        Assert.assertEquals(0.0f, mRectF.left);
        Assert.assertEquals(0.0f, mRectF.top);


    }

    @Test
    public void testCalcActionArrowRectF() {


        ImageView mMock = Mockito.mock(ImageView.class);
        when(mMock.getHeight()).thenReturn(20);
        when(mMock.getWidth()).thenReturn(30);
        when(mMock.getX()).thenReturn(2f);
        when(mMock.getY()).thenReturn(3f);

        mView.mIvActionArrow = mMock;


        RectF mRectF = mView.calcActionArrowRect();

        Assert.assertEquals(30.0f, mRectF.width());
        Assert.assertEquals(20.0f, mRectF.height());
        Assert.assertEquals(2.0f, mRectF.left);
        Assert.assertEquals(3.0f, mRectF.top);


    }

    @Test
    public void testCalcDescriptionRectF() {


        TextView mMock = Mockito.mock(TextView.class);
        when(mMock.getHeight()).thenReturn(20);
        when(mMock.getWidth()).thenReturn(30);
        when(mMock.getX()).thenReturn(2f);
        when(mMock.getY()).thenReturn(3f);

        mView.setDescription(mMock);

        RectF mRectF = mView.calcDescriptionRect();

        Assert.assertEquals(mView.getChildAt(0), mMock);
        Assert.assertEquals(30.0f, mRectF.width());
        Assert.assertEquals(20.0f, mRectF.height());
        Assert.assertEquals(2.0f, mRectF.left);
        Assert.assertEquals(3.0f, mRectF.top);


    }

    @Test
    public void testCalcActionDescriptionRectF() {


        TextView mMock = Mockito.mock(TextView.class);
        when(mMock.getHeight()).thenReturn(20);
        when(mMock.getWidth()).thenReturn(30);
        when(mMock.getX()).thenReturn(2f);
        when(mMock.getY()).thenReturn(3f);

        mView.setActionDescription(mMock);

        RectF mRectF = mView.calcActionDescriptionRect();

        Assert.assertEquals(mView.getChildAt(0), mMock);
        Assert.assertEquals(30.0f, mRectF.width());
        Assert.assertEquals(20.0f, mRectF.height());
        Assert.assertEquals(2.0f, mRectF.left);
        Assert.assertEquals(3.0f, mRectF.top);


    }

    @Test
    public void testDispatchDraw() throws NoSuchFieldException, IllegalAccessException {

        mActivity.addContentView(mView, new LinearLayout.LayoutParams(200, 200));
        Canvas canvasMock = Mockito.mock(Canvas.class);
        mView.setView(Mockito.mock(View.class));

        mView.mCircleView = Mockito.mock(CircleView.class);
        mView.dispatchDraw(canvasMock);

        AnimationRenderer animaRenderer = ReflectionUtil.fieldGet(CoachmarkView.class, mView, "mAnimationRenderer");

        Assert.assertTrue(animaRenderer instanceof NoAnimationRenderer);

        Bitmap bitmap = ReflectionUtil.fieldGet(CoachmarkView.class, mView, "bitmap");

        Assert.assertEquals(200, bitmap.getWidth());
        Assert.assertEquals(200, bitmap.getHeight());
    }

    @Test
    public void testOnAnimationFinished() throws NoSuchFieldException, IllegalAccessException {

        TextView desc = Mockito.mock(TextView.class);
        TextView actionDesc = Mockito.mock(TextView.class);

        mView.mIvActionArrow = Mockito.mock(ImageView.class);

        DescriptionRenderer descRenderer = Mockito.mock(DescriptionRenderer.class);
        ActionDescriptionRenderer actDescRenderer = Mockito.mock(ActionDescriptionRenderer.class);
        when(actDescRenderer.isRenderingPossible(mView)).thenReturn(true);
        ButtonRenderer buttonRenderer = Mockito.mock(ButtonRenderer.class);

        mView.setActionDescription(actionDesc);
        mView.setDescription(desc);
        mView.setButtonRenderer(buttonRenderer);
        mView.setActionDescriptionRenderer(actDescRenderer);
        mView.setDescriptionRenderer(descRenderer);

        mView.onAnimationFinished();

        verify(descRenderer, times(1)).render(mView, desc);
        verify(actDescRenderer, times(1)).render(mView, actionDesc, mView.mIvActionArrow);
        verify(actDescRenderer, times(1)).isRenderingPossible(mView);
        verify(buttonRenderer, times(1)).render(mView);
    }


}
