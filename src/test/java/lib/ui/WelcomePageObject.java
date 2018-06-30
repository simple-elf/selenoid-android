package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject {

    private static final By
            STEP_LEARN_MORE_LINK = By.id("Learn more about Wikipedia"),
            STEP_NEW_WAYS_TO_EXPLORE_TEXT = By.id("New ways to explore"),
            STEP_ADD_OR_EDIT_PREFERRED_LANG_TEXT = By.id("Add or edit preferred languages"),
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = By.id("Learn more about data collected"),
            NEXT_LINK = By.id("Next"),
            SKIP_LINK = By.id("Skip"),
            GET_STARTED_BUTTON = By.id("Get started");

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        this.waitForElementPresent(
                STEP_LEARN_MORE_LINK,
                "Cannot find 'Learn more about Wikipedia' link",
                10);
    }

    public void waitForNewWayToExplore() {
        this.waitForElementPresent(
                STEP_NEW_WAYS_TO_EXPLORE_TEXT,
                "Cannot find 'New ways to explore' text",
                10);
    }

    public void waitForAddOrEditPreferredLangText() {
        this.waitForElementPresent(
                STEP_ADD_OR_EDIT_PREFERRED_LANG_TEXT,
                "Cannot find 'Add or edit preferred languages' text",
                10);
    }

    public void waitForLearnMoreAboutDataCollectedText() {
        this.waitForElementPresent(
                STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,
                "Cannot find 'Learn more about data collected' text",
                10);
    }

    public void clickNextButton() {
        this.waitForElementAndClick(
                NEXT_LINK,
                "Cannot find and click 'Next' link",
                10);
    }

    public void clickSkipButton() {
        this.waitForElementAndClick(
                SKIP_LINK,
                "Cannot find and click 'Skip' link",
                10);
    }

    public void clickGetStartedButton() {
        this.waitForElementAndClick(
                GET_STARTED_BUTTON,
                "Cannot find and click 'Get started' button",
                10);
    }



}
