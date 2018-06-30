package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.By;

public class AndroidMyListsPageObject extends MyListsPageObject {

    static {
        MY_LISTS_HEADER = By.xpath("//android.widget.TextView[@text='My lists']");

        READING_LIST_BY_NAME_TPL = "//*[@text='{READING_LIST_NAME}']";
        ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";
    }

    public AndroidMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

}
