package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class NavigationUI extends MainPageObject {

    protected static By MY_LISTS_LINK;

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void openMyLists() {
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot find button 'My lists'",
                5);
    }

}
