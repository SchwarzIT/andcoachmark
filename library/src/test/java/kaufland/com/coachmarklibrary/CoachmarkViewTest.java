package kaufland.com.coachmarklibrary;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import kaufland.com.coachmarklibrary.renderer.actiondescription.ActionDescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.animation.AnimationRenderer;
import kaufland.com.coachmarklibrary.renderer.animation.NoAnimationRenderer;
import kaufland.com.coachmarklibrary.renderer.buttonrenderer.ButtonRenderer;
import kaufland.com.coachmarklibrary.renderer.circle.CircleView;
import kaufland.com.coachmarklibrary.renderer.description.DescriptionRenderer;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sbra0902 on 15.03.17.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22)
public class CoachmarkViewTest {

    private Activity mActivity;
    private CoachmarkView mCoachmarkView;
    private AttributeSet mAttributeSet;

    @Before
    public void init() {

        mActivity = Robolectric.setupActivity(Activity.class);
        mCoachmarkView = new CoachmarkView(mActivity);
        mCoachmarkView.mWindowManager = mock(WindowManager.class);
        mAttributeSet = Robolectric.buildAttributeSet().build();
    }

    @Test
    public void testIsCoachmarkShown() {

        CoachmarkView coachmarkView = mock(CoachmarkView.class);
        DisplayMetrics displayMetrics = mock(DisplayMetrics.class);
        Display display = mock(Display.class);
        when(coachmarkView.mWindowManager.getDefaultDisplay()).thenReturn(display);
        display.getMetrics(displayMetrics);
        verify(display).getMetrics(displayMetrics);

        ArgumentCaptor<WindowManager.LayoutParams> params = ArgumentCaptor.forClass(WindowManager.LayoutParams.class);
        WindowManager.LayoutParams windowManagerMock = mock(WindowManager.LayoutParams.class);


        windowManagerMock.gravity = Gravity.TOP;
        windowManagerMock.x = 0;
        windowManagerMock.y = 0;
        windowManagerMock.height = WindowManager.LayoutParams.MATCH_PARENT;
        windowManagerMock.width = WindowManager.LayoutParams.MATCH_PARENT;
        windowManagerMock.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN;
        windowManagerMock.format = PixelFormat.TRANSLUCENT;

        Assert.assertFalse(coachmarkView.isIsInitialized());
    }

    @Test
    public void testScreenParams() throws Exception {
        DisplayProvider display = Mockito.mock(DisplayProvider.class);
        BuildProvider build = mock(BuildProvider.class);
        DeviceInfoProvider deviceInfoProvider = new DeviceInfoProvider(display, build);

        when(display.getWidth()).thenReturn(1000);
        when(display.getHeight()).thenReturn(2000);
        when(build.getModel()).thenReturn(Build.MODEL);

        DeviceInfoProvider.DeviceInfo deviceInfo = deviceInfoProvider.getScreenParams();

        assertThat(deviceInfo.mScreenWidth, equalTo(1000));
        assertThat(deviceInfo.mScreenHeight, equalTo(2000));
        assertThat(deviceInfo.mModel, equalTo(Build.MODEL));
    }

    @Test
    public void testIsInEditMode() {
        Assert.assertTrue(mCoachmarkView.isInEditMode());
    }

    @Test
    public void testIsCoachmarkDismissed() {
        mCoachmarkView.dismiss();
        Assert.assertTrue(mCoachmarkView.getWindowToken() == null);
    }

    @Test
    public void testCoachmarkInitialization() {
        Assert.assertNotNull(new CoachmarkView(mActivity, mAttributeSet));
        Assert.assertNotNull(new CoachmarkView(mActivity, mAttributeSet, 0, 0));
        Assert.assertNotNull(new CoachmarkView(mActivity, mAttributeSet, 0));
    }

    @Test
    public void testCalcCircleRectF() {

        View mClickedViewMock = mock(View.class);
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

        mCoachmarkView.setView(mClickedViewMock);
        mCoachmarkView.marginAroundCircle = 2;
        RectF mRectF = mCoachmarkView.calcCircleRectF();
        Assert.assertEquals(34.0f, mRectF.width());
        Assert.assertEquals(34.0f, mRectF.height());
        Assert.assertEquals(1.0f, mRectF.left);
        Assert.assertEquals(-10.0f, mRectF.top);
    }

    @Test
    public void testCalcScreenRectF() {

        mActivity.addContentView(mCoachmarkView, new LinearLayout.LayoutParams(200, 200));
        RectF mRectF = mCoachmarkView.calcScreenRectF();
        Assert.assertEquals(200.0f, mRectF.width());
        Assert.assertEquals(200.0f, mRectF.height());
        Assert.assertEquals(0.0f, mRectF.left);
        Assert.assertEquals(0.0f, mRectF.top);
    }

    @Test
    public void testCalcActionArrowRectF() {

        ImageView mMock = mock(ImageView.class);
        when(mMock.getHeight()).thenReturn(20);
        when(mMock.getWidth()).thenReturn(30);
        when(mMock.getX()).thenReturn(2f);
        when(mMock.getY()).thenReturn(3f);
        mCoachmarkView.mIvActionArrow = mMock;
        RectF mRectF = mCoachmarkView.calcActionArrowRect();
        Assert.assertEquals(30.0f, mRectF.width());
        Assert.assertEquals(20.0f, mRectF.height());
        Assert.assertEquals(2.0f, mRectF.left);
        Assert.assertEquals(3.0f, mRectF.top);
    }

    @Test
    public void testCalcDescriptionRectF() {

        TextView mMock = mock(TextView.class);
        when(mMock.getHeight()).thenReturn(20);
        when(mMock.getWidth()).thenReturn(30);
        when(mMock.getX()).thenReturn(2f);
        when(mMock.getY()).thenReturn(3f);

        mCoachmarkView.setDescription(mMock);

        RectF mRectF = mCoachmarkView.calcDescriptionRect();

        Assert.assertEquals(mCoachmarkView.getChildAt(0), mMock);
        Assert.assertEquals(30.0f, mRectF.width());
        Assert.assertEquals(20.0f, mRectF.height());
        Assert.assertEquals(2.0f, mRectF.left);
        Assert.assertEquals(3.0f, mRectF.top);
    }

    @Test
    public void testCalcActionDescriptionRectF() {
        TextView mMock = mock(TextView.class);
        when(mMock.getHeight()).thenReturn(20);
        when(mMock.getWidth()).thenReturn(30);
        when(mMock.getX()).thenReturn(2f);
        when(mMock.getY()).thenReturn(3f);

        mCoachmarkView.setActionDescription(mMock);
        RectF mRectF = mCoachmarkView.calcActionDescriptionRect();

        Assert.assertEquals(mCoachmarkView.getChildAt(0), mMock);
        Assert.assertEquals(30.0f, mRectF.width());
        Assert.assertEquals(20.0f, mRectF.height());
        Assert.assertEquals(2.0f, mRectF.left);
        Assert.assertEquals(3.0f, mRectF.top);
    }

    @Test
    public void testDispatchDraw() throws NoSuchFieldException, IllegalAccessException {

        mActivity.addContentView(mCoachmarkView, new LinearLayout.LayoutParams(200, 200));
        Canvas canvasMock = mock(Canvas.class);
        mCoachmarkView.setView(mock(View.class));
        mCoachmarkView.mCircleView = mock(CircleView.class);
        mCoachmarkView.dispatchDraw(canvasMock);
        AnimationRenderer animRenderer = ReflectionUtil.getField(CoachmarkView.class, mCoachmarkView, "mAnimationRenderer");
        Assert.assertTrue(animRenderer instanceof NoAnimationRenderer);
        Bitmap bitmap = ReflectionUtil.getField(CoachmarkView.class, mCoachmarkView, "mBitmap");
        Assert.assertEquals(200, bitmap.getWidth());
        Assert.assertEquals(200, bitmap.getHeight());
    }

    @Test
    public void testOnAnimationFinished() throws NoSuchFieldException, IllegalAccessException {

        TextView desc = mock(TextView.class);
        TextView actionDesc = mock(TextView.class);

        mCoachmarkView.mIvActionArrow = mock(ImageView.class);

        DescriptionRenderer descRenderer = mock(DescriptionRenderer.class);
        ActionDescriptionRenderer actDescRenderer = mock(ActionDescriptionRenderer.class);
        when(actDescRenderer.isRenderingPossible(mCoachmarkView)).thenReturn(true);
        ButtonRenderer buttonRenderer = mock(ButtonRenderer.class);

        mCoachmarkView.setActionDescription(actionDesc);
        mCoachmarkView.setDescription(desc);
        mCoachmarkView.setButtonRenderer(buttonRenderer);
        mCoachmarkView.setActionDescriptionRenderer(actDescRenderer);
        mCoachmarkView.setDescriptionRenderer(descRenderer);

        mCoachmarkView.onAnimationFinished();

        verify(descRenderer, times(1)).render(mCoachmarkView, desc);
        verify(actDescRenderer, times(1)).render(mCoachmarkView, actionDesc, mCoachmarkView.mIvActionArrow);
        verify(actDescRenderer, times(1)).isRenderingPossible(mCoachmarkView);
        verify(buttonRenderer, times(1)).render(mCoachmarkView);
    }
}
