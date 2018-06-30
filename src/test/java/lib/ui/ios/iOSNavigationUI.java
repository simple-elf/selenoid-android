package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.By;

public class iOSNavigationUI extends NavigationUI {

    static {
        MY_LISTS_LINK = By.xpath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@name='Saved']"); // в моей версии приложения нет id
    }

    public iOSNavigationUI(AppiumDriver driver) {
        super(driver);
    }

}
