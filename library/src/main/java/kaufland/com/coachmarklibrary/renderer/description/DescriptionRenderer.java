package kaufland.com.coachmarklibrary.renderer.description;

import android.graphics.RectF;
import android.view.View;

/**
 * Created by sbra0902 on 13.02.17.
 */

public interface DescriptionRenderer {

    void render(RectF screenRectangle, float circleCenterY, View description);

}
