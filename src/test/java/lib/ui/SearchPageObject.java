package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject {

    protected static By
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_EMPTY_MESSAGE,
            SEARCH_RESULTS_LIST,
            SEARCH_RESULTS_LIST_ITEM,
            SEARCH_RESULTS_LIST_ITEM_TITLE,
            EMPTY_RESULT_LABEL,
            SEARCH_RESULTS_ELEMENT,
            SEARCH_INPUT_CLEAR_BUTTON;
    protected static String
            SEARCH_EMPTY_MESSAGE_TEXT,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION;

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static By getResultSearchElement(String subString) {
        return By.xpath(SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", subString));
    }

    private static By getResultSearchElementByTitleAndDescription(String title, String description) {
        return By.xpath(SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION
                .replace("{TITLE}", title)
                .replace("{DESCRIPTION}", description));
    }
    /* TEMPLATE METHODS */

    public void initSearchInput() {
        this.waitForElementAndClick(
                SEARCH_INIT_ELEMENT,
                "Cannot find and click search button",
                5);
        WebElement searchInput = this.waitForElementPresent(
                SEARCH_INPUT,
                "Cannot find search input",
                5);
        if (Platform.getInstance().isAndroid()) {
            this.checkElementText(searchInput, "Search…");
        }
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(
                SEARCH_CANCEL_BUTTON,
                "Cannot find search cancel button",
                5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(
                SEARCH_CANCEL_BUTTON,
                "Search cancel button is still present",
                5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(
                SEARCH_CANCEL_BUTTON,
                "Cannot find and click search cancel button",
                5);
    }

    public void typeSearchInput(String searchString) {
        this.waitForElementAndSendKeys(
                SEARCH_INPUT,
                searchString,
                "Cannot find and type into search input",
                5);
    }

    public void clearSearchInput() {
        this.waitForElementAndClear(
                SEARCH_INPUT,
                "Cannot find and clear search input",
                5);
        if (Platform.getInstance().isIOS()) {
            this.waitForElementAndClick(SEARCH_INPUT_CLEAR_BUTTON, "Cannot find input clear button", 5);
        }
    }

    public void waitForSearchResult(String subString) {
        this.waitForElementPresent(
                getResultSearchElement(subString),
                "Cannon find search result with substring " + subString,
                15);
    }

    public void clickByArticleWithSubstring(String subString) {
        this.waitForElementAndClick(
                getResultSearchElement(subString),
                "Cannon find and click search result with substring " + subString,
                10);
    }

    public void waitForSearchResultsListNotEmpty() {
        List<WebElement> searchResults = getSearchResultsList();
        System.out.println("Size: " + searchResults.size());
        Assert.assertTrue("There is no search results", searchResults.size() > 0);
    }

    public List<WebElement> getSearchResultsList() {
        WebElement searchResultsList = this.waitForElementPresent(
                SEARCH_RESULTS_LIST,
                "Cannot find search results list",
                15);
        return searchResultsList.findElements(SEARCH_RESULTS_LIST_ITEM);
    }

    public String getSearchResultItemTitle(WebElement searchResultItem) {
        return searchResultItem.findElement(SEARCH_RESULTS_LIST_ITEM_TITLE).getText();
    }

    public void waitForSearchEmptyMessage() {
        if (Platform.getInstance().isIOS())
            return;
        WebElement emptyMessage = this.waitForElementPresent(
                SEARCH_EMPTY_MESSAGE,
                "Cannot find empty search message",
                5);
        this.checkElementText(emptyMessage, SEARCH_EMPTY_MESSAGE_TEXT);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                SEARCH_RESULTS_ELEMENT,
                "Cannot find search results list",
                15);

        return this.getAmountOfElements(SEARCH_RESULTS_ELEMENT);
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(
                EMPTY_RESULT_LABEL,
                "Cannot find empty result label",
                15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(
                SEARCH_RESULTS_ELEMENT,
                "We supposed not to find any results");
    }

    public void searchAndCheckResults(String searchString) {
        this.initSearchInput();
        this.typeSearchInput(searchString);
        this.waitForSearchResultsListNotEmpty();

        List<WebElement> searchResultsList = this.getSearchResultsList();
        searchResultsList = searchResultsList.subList(0, 6);
        //searchResultsList.remove(searchResultsList.size() - 1); // fix to remove last partly visible line
        System.out.println("Size: " + searchResultsList.size());

        for (WebElement searchItem : searchResultsList) {
            String searchItemTitle = this.getSearchResultItemTitle(searchItem);
            System.out.println("searchItemTitle: " + searchItemTitle);
            Assert.assertThat(
                    "Search result item doesn't contains search text",
                    searchItemTitle,
                    CoreMatchers.containsString(searchString));
        }

        this.clearSearchInput();
        this.waitForSearchEmptyMessage();

    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        this.waitForElementPresent(
                getResultSearchElementByTitleAndDescription(title, description),
                "Cannot find search result by title '" + title + "' and description '" + description + "'",
                5);
    }

}
