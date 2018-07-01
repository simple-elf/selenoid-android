package lib.ui.ios;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class iOSMyListsPageObject extends MyListsPageObject {

    static {
        MY_LISTS_HEADER = By.xpath("//XCUIElementTypeOther[@name='Saved']");

        ARTICLE_BY_TITLE_TPL = "//XCUIElementTypeLink[contains(@name,'{TITLE}')]";
    }

    public iOSMyListsPageObject(WebDriver driver) {
        super(driver);
    }

}
