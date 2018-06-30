package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.By;

public class iOSMyListsPageObject extends MyListsPageObject {

    static {
        MY_LISTS_HEADER = By.xpath("//XCUIElementTypeOther[@name='Saved']");

        ARTICLE_BY_TITLE_TPL = "//XCUIElementTypeLink[contains(@name,'{TITLE}')]";
    }

    public iOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

}
