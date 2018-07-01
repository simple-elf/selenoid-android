package lib.ui.ios;

import lib.ui.NavigationUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class iOSNavigationUI extends NavigationUI {

    static {
        MY_LISTS_LINK = By.xpath("//XCUIElementTypeTabBar/XCUIElementTypeButton[@name='Saved']"); // в моей версии приложения нет id
    }

    public iOSNavigationUI(WebDriver driver) {
        super(driver);
    }

}
