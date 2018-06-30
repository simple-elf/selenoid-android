package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.By;

public class iOSSearchPageObject extends SearchPageObject {

    private static final String
            searchIcon = "//XCUIElementTypeImage[@name='search']",
            searchResultsParent = searchIcon + "/../..";

    static {
        SEARCH_INIT_ELEMENT = By.xpath("//XCUIElementTypeSearchField[@name='Search Wikipedia']"); //By.id("Search Wikipedia");
        SEARCH_INPUT = By.xpath("//XCUIElementTypeSearchField"); // в нашей версии приложения у этого поля нет атрибута value
        SEARCH_CANCEL_BUTTON = By.id("Close");
        SEARCH_EMPTY_MESSAGE = By.xpath("//XCUIElementTypeStaticText[@name='No results found']");
        EMPTY_RESULT_LABEL = By.xpath("//*[@name='No results found']");

        SEARCH_RESULTS_LIST = By.xpath(searchResultsParent + "//XCUIElementTypeCollectionView");
        SEARCH_RESULTS_LIST_ITEM = By.xpath("//XCUIElementTypeLink");
        SEARCH_RESULTS_LIST_ITEM_TITLE = SEARCH_RESULTS_LIST_ITEM;
        SEARCH_RESULTS_ELEMENT = By.xpath(searchResultsParent + "//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeLink");

        SEARCH_INPUT_CLEAR_BUTTON = By.xpath("//XCUIElementTypeButton[@name='clear mini']");

        SEARCH_EMPTY_MESSAGE_TEXT = "";
        SEARCH_RESULT_BY_SUBSTRING_TPL =
                "//XCUIElementTypeLink[contains(@name, '{SUBSTRING}')]";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION =
                "//XCUIElementTypeLink[contains(@name, '{TITLE}')][contains(@name, '{DESCRIPTION}')]";
    }

    public iOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }

}
