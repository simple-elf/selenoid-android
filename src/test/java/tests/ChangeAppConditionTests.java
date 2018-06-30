package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenRotationOnSearchResults() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput("Selenium");
        searchPageObject.clickByArticleWithSubstring("Selenium (software)");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);;
        String titleBeforeRotation = articlePageObject.getArticleTitle();

        this.rotateScreenLandscape();
        String titleAfterRotation = articlePageObject.getArticleTitle();
        assertEquals(
                "Article title have been change after screen rotation",
                titleBeforeRotation,
                titleAfterRotation);

        this.rotateScreenPortrait();
        String titleAfterSecondRotation = articlePageObject.getArticleTitle();
        assertEquals(
                "Article title have been change after screen rotation",
                titleBeforeRotation,
                titleAfterSecondRotation);
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");

        this.backgroundApp(3);
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

}
