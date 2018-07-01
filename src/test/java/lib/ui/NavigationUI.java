package lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static By MY_LISTS_LINK;

    public NavigationUI(WebDriver driver) {
        super(driver);
    }

    public void openMyLists() {
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot find button 'My lists'",
                5);
    }

}
