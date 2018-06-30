package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.By;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE = By.xpath("//XCUIElementTypeWebView//XCUIElementTypeOther[@name]/XCUIElementTypeStaticText[@name]"); // By.id("Appium"); // By.id("Java (programming language)");
        WEB_VIEW = By.xpath("//XCUIElementTypeOther/XCUIElementTypeWebView"); // //XCUIElementTypeOther[@name]/XCUIElementTypeStaticText[@name]
        FOOTER_ELEMENT = By.id("View article in browser");
        ADD_TO_READING_LIST_BUTTON = By.xpath("//XCUIElementTypeToolbar//XCUIElementTypeButton[@name='Save for later']");
        ADD_TO_READING_LIST_BUTTON_SAVED = By.xpath("//XCUIElementTypeToolbar//XCUIElementTypeButton[@name='Saved. Activate to unsave.']");
        CLOSE_ARTICLE_BUTTON = By.id("Back");
        SAVED_BACK_LINK = By.id("Saved");
        LOADING_PROGRESS = By.xpath("//XCUIElementTypeProgressIndicator[@name='Progress']");

        TITLE_TPL = "//XCUIElementTypeWebView//XCUIElementTypeOther[@name='{TITLE}']/XCUIElementTypeStaticText[@name='{TITLE}']";
    }

    public iOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

}
