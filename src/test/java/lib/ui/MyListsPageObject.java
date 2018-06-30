package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;

abstract public class MyListsPageObject extends MainPageObject {

    protected static By
            MY_LISTS_HEADER;
    protected static String
            READING_LIST_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL;

    private static By getFolderLocatorByName(String nameOfFolder) {
        return By.xpath(READING_LIST_BY_NAME_TPL.replace("{READING_LIST_NAME}", nameOfFolder));
    }

    private static By getSavedArticleLocatorByTitle(String title) {
        return By.xpath(ARTICLE_BY_TITLE_TPL.replace("{TITLE}", title));
    }

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String nameOfReadingList) {
        if (Platform.getInstance().isIOS())
            return;
        this.waitForMyListsPageHeader();
        this.waitForElementAndClick(
                getFolderLocatorByName(nameOfReadingList),
                "Cannon find saved reading list by name: " + nameOfReadingList,
                5);
    }

    public void waitForMyListsPageHeader() {
        this.waitForElementPresent(
                MY_LISTS_HEADER,
                "Cannot find saved lists header",
                5);
    }

    public void waitForArticleToAppearByTitle(String articleTitle) {
        this.waitForElementPresent(
                getSavedArticleLocatorByTitle(articleTitle),
                "Cannot find saved article: " + articleTitle,
                5);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle) {
        this.waitForElementNotPresent(
                getSavedArticleLocatorByTitle(articleTitle),
                "Cannot delete saved article: " + articleTitle,
                5);
    }

    public void swipeByArticleToDelete(String articleTitle) {
        this.waitForArticleToAppearByTitle(articleTitle);

        this.swipeElementToLeft(
                getSavedArticleLocatorByTitle(articleTitle),
                "Cannot swipe saved article: " + articleTitle);

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(
                    getSavedArticleLocatorByTitle(articleTitle),
                    "Cannot find and delete saved article");
        }

        this.waitForArticleToDisappearByTitle(articleTitle);
    }

}
