package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String nameForSavedReadingList = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput("Appium"); // Java
        searchPageObject.clickByArticleWithSubstring("Appium"); // Object-oriented programming language

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);;
        //articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToNewReadingList(nameForSavedReadingList);
        } else {
            articlePageObject.addArticleToMySaved();
        }

        articlePageObject.closeOpenedArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        myListsPageObject.openFolderByName(nameForSavedReadingList);

        myListsPageObject.swipeByArticleToDelete(articleTitle);
    }

    @Test
    public void testSaveTwoArticlesToMyListAndRemove() {
        String firstSearch = "Selenium";
        String firstSearchTitle = "Selenium (software)";
        String secondSearch = "Appium";
        String nameForSavedReadingList = "Learning mobile automation";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput(firstSearch);
        searchPageObject.clickByArticleWithSubstring(firstSearchTitle);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        //articlePageObject.waitForTitleElement();
        String firstArticleTitle = articlePageObject.getArticleTitle();
        System.out.println(firstArticleTitle);
        assertEquals("Element text is not expected!", firstSearchTitle, firstArticleTitle);

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToNewReadingList(nameForSavedReadingList);
        } else {
            articlePageObject.addArticleToMySaved();
        }
        articlePageObject.closeOpenedArticle();

        searchPageObject.initSearchInput();
        searchPageObject.clearSearchInput();
        searchPageObject.typeSearchInput(secondSearch);
        searchPageObject.clickByArticleWithSubstring(secondSearch);

        //articlePageObject.waitForTitleElement();
        String secondArticleTitle = articlePageObject.getArticleTitle();
        System.out.println(secondArticleTitle);
        assertEquals("Element text is not expected!", secondSearch, secondArticleTitle);

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToExistingReadingList(nameForSavedReadingList);
        } else {
            articlePageObject.addArticleToMySaved();
        }
        articlePageObject.closeOpenedArticle();


        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        myListsPageObject.openFolderByName(nameForSavedReadingList);
        myListsPageObject.swipeByArticleToDelete(firstSearchTitle);

        // Интересно что сработал тот же метод, который использовался в результатах поиска
        searchPageObject.clickByArticleWithSubstring(secondSearch);

        //articlePageObject.waitForTitleElement();
        String savedArticleTitle = articlePageObject.getArticleTitle();
        System.out.println(savedArticleTitle);
        assertEquals("Element text is not expected!", secondSearch, savedArticleTitle);

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.closeOpenedArticle();
        } else {
            articlePageObject.closeOpenedArticleReturnToSaved(); // Кнопка для перехода назад отличается в случае открытия статьи из поиска, и из списка Saved
        }

    }

}
