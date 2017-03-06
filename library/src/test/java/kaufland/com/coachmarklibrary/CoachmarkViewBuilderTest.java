package kaufland.com.coachmarklibrary;

import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import kaufland.com.coachmarklibrary.renderer.CoachmarkViewLayout;
import kaufland.com.coachmarklibrary.renderer.buttonrenderer.ButtonRenderer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({CoachmarkView_.class})
public class CoachmarkViewBuilderTest {

    @Mock
    private CoachmarkView mCoachmarkViewMock;


    CoachmarkViewBuilder mBuilderToTest;

    @Before
    public void init() {

        PowerMockito.mockStatic(CoachmarkView_.class);
        PowerMockito.when(CoachmarkView_.build(null)).thenReturn(mCoachmarkViewMock);

        mBuilderToTest = new CoachmarkViewBuilder(null);
    }

    @Test
    public void withButtonRendererTest() throws Exception {

        CoachmarkClickListener mListener = Mockito.mock(CoachmarkClickListener.class);

        ButtonRenderer rendererMock = Mockito.mock(ButtonRenderer.class);
        mBuilderToTest.withButtonRenderer(rendererMock);

        verify(mCoachmarkViewMock, times(1)).setButtonRenderer(rendererMock);
    }

    @Test
    public void withBackgroundColorTest() throws Exception {

        mBuilderToTest.withBackgroundColor(0);

        verify(mCoachmarkViewMock, times(1)).setBackColor(0);
    }

    @Test
    public void withActionDescriptionRenderers() throws Exception {

        mBuilderToTest.withActionDescriptionRenderers(null);

        verify(mCoachmarkViewMock, times(1)).setActionDescriptionRenderer(null);
    }

    @Test
    public void withDescriptionRenderer() throws Exception {

        mBuilderToTest.withDescriptionRenderer(null);

        verify(mCoachmarkViewMock, times(1)).setDescriptionRenderer(null);
    }

    @Test
    public void withActionDescriptionTest() throws Exception {

        mBuilderToTest.withActionDescription(null);

        verify(mCoachmarkViewMock, times(1)).setActionDescription(null);
    }

    @Test
    public void buildAroundViewTest() {


        View mMock = Mockito.mock(View.class);
        mBuilderToTest.buildAroundView(mMock);

        verify(mCoachmarkViewMock, times(1)).setView(mMock);
    }

    @Test
    public void withDescriptionTest() throws Exception {

        mBuilderToTest.withDescription(null);

        verify(mCoachmarkViewMock, times(1)).setDescription(null);
    }

    @Test
    public void withPaddingAroundCircle() throws Exception {

        mBuilderToTest.withPaddingAroundCircle(2);

        verify(mCoachmarkViewMock, times(1)).setPaddingAroundCircle(2);
    }
}