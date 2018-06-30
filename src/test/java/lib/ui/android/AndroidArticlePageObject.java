package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.By;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        TITLE = By.id("org.wikipedia:id/view_page_title_text");
        FOOTER_ELEMENT = By.xpath("//*[@text='View page in browser']");
        ADD_TO_READING_LIST_BUTTON = By.xpath("//android.widget.ImageView[@content-desc='Add this article to a reading list']");
        ONBOARDING = By.id("org.wikipedia:id/onboarding_button");
        MY_LIST_NAME_INPUT = By.id("org.wikipedia:id/text_input");
        MY_LIST_OK_BUTTON = By.xpath("//*[@text='OK']");
        CLOSE_ARTICLE_BUTTON = By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']");
        SAVED_READING_LIST_BY_NAME_TPL = "//android.widget.TextView[@text='{NAME}']";
    }

    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

}
