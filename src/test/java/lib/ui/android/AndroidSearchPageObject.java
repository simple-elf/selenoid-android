package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.By;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = By.xpath("//*[contains(@text, 'Search Wikipedia')]");
        SEARCH_INPUT = By.id("org.wikipedia:id/search_src_text");
        SEARCH_CANCEL_BUTTON = By.id("org.wikipedia:id/search_close_btn");
        SEARCH_EMPTY_MESSAGE = By.id("org.wikipedia:id/search_empty_message");
        SEARCH_RESULTS_LIST = By.id("org.wikipedia:id/search_results_list");
        SEARCH_RESULTS_LIST_ITEM = By.id("org.wikipedia:id/page_list_item_container");
        SEARCH_RESULTS_LIST_ITEM_TITLE = By.id("org.wikipedia:id/page_list_item_title");
        EMPTY_RESULT_LABEL = By.xpath("//*[@text='No results found']");
        SEARCH_RESULTS_ELEMENT = By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']" +
                "/*[@resource-id='org.wikipedia:id/page_list_item_container']");

        SEARCH_EMPTY_MESSAGE_TEXT = "Search and read the free encyclopedia in your language";
        SEARCH_RESULT_BY_SUBSTRING_TPL =
                "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION =
                "//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE}']/" +
                "../*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{DESCRIPTION}']";
    }

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }

}
