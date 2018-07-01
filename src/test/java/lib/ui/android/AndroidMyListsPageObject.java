package lib.ui.android;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {

    static {
        MY_LISTS_HEADER = By.xpath("//android.widget.TextView[@text='My lists']");

        READING_LIST_BY_NAME_TPL = "//*[@text='{READING_LIST_NAME}']";
        ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";
    }

    public AndroidMyListsPageObject(WebDriver driver) {
        super(driver);
    }

}
