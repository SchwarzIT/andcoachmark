package kaufland.com.coachmarklibrary;

/**
 * Created by mpra27061 on 31.03.17.
 */
public class DeviceInfoProvider {

    private final DisplayProvider mDisplay;
    private final BuildProvider mBuild;

    public DeviceInfoProvider(DisplayProvider display, BuildProvider build) {
        this.mDisplay = display;
        this.mBuild = build;
    }

    public DeviceInfo getScreenParams() {
        int screenWidth = mDisplay.getWidth();
        int screenHeight = mDisplay.getHeight();
        String model = mBuild.getModel();
        return new DeviceInfo(screenWidth, screenHeight, model);
    }

    public class DeviceInfo {
        public final int mScreenWidth, mScreenHeight;
        public final String mModel;

        public DeviceInfo(int mScreenWidth, int mScreenHeight, String deviceModel) {
            this.mScreenWidth = mScreenWidth;
            this.mScreenHeight = mScreenHeight;
            this.mModel = deviceModel;
        }
    }
}
