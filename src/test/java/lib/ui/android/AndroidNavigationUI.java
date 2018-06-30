package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.By;

public class AndroidNavigationUI extends NavigationUI {

    static {
        MY_LISTS_LINK = By.xpath("//android.widget.FrameLayout[@content-desc='My lists']");
    }

    public AndroidNavigationUI(AppiumDriver driver) {
        super(driver);
    }

}
