package lib.ui.android;

import lib.ui.NavigationUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AndroidNavigationUI extends NavigationUI {

    static {
        MY_LISTS_LINK = By.xpath("//android.widget.FrameLayout[@content-desc='My lists']");
    }

    public AndroidNavigationUI(WebDriver driver) {
        super(driver);
    }

}
