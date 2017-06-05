package kaufland.com.coachmarklibrary.renderer.circle;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.mockito.AdditionalMatchers.eq;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by sbra0902 on 15.03.17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest= Config.NONE, sdk = 22)
public class CircleViewTest {

    @Test
    public void testDispatchDraw() {

        ActivityController<Activity> activityController = Robolectric.buildActivity(Activity.class).create().start().resume()
                .visible();

        Activity activity = activityController.get();

        CircleView mView = new CircleView(activity);

        mView.setCenterY(2);
        mView.setCenterX(3);
        mView.setRadius(22);

        Canvas canvasMock = Mockito.mock(Canvas.class);

        mView.dispatchDraw(canvasMock);

        verify(canvasMock).drawCircle(Matchers.eq(3.0f), Matchers.eq(2.0f), Matchers.eq(22.0f), any(Paint.class));

    }

}
