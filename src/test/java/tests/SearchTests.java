package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearchAndCheck() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    /**
     * Написать тест, который:
     * 1. Ищет какое-то слово
     * 2. Убеждается, что найдено несколько статей
     * 3. Отменяет поиск (ТУТ НАВЕРНОЕ ИМЕЕТСЯ ВВИДУ ОЧИСТКА ПОЛЯ ПОИСКА, А НЕ ОТМЕНА ПО НАЖАТИЮ КРЕСТИКА)
     * 4. Убеждается, что результат поиска пропал
     */
    @Test
    public void testCancelSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchInput("Java");
        searchPageObject.waitForSearchResultsListNotEmpty();

        searchPageObject.clearSearchInput();
        searchPageObject.waitForSearchEmptyMessage();

        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        String searchString = "Avril Lavigne discography";
        searchPageObject.typeSearchInput(searchString);
        int amountOfSearchResults = searchPageObject.getAmountOfFoundArticles();

        assertTrue("We found zero results", amountOfSearchResults > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        String searchString = "Selenium Selenide Appium";
        searchPageObject.typeSearchInput(searchString);

        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultOfSearch();
    }

    /**
     * Написать тест, который:
     * 1. Ищет какое-то слово
     * 2. Убеждается, что в каждом результате поиска есть это слово.
     */
    @Test
    public void testSearchAndCheckResults() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.searchAndCheckResults("Java");
        searchPageObject.searchAndCheckResults("Selenium");
        searchPageObject.searchAndCheckResults("Android"); // fixed

        //searchPageObject.searchAndCheckResults("Appium"); // fails because second item is AppImage
    }

    @Test
    public void testSearchAndCheckResultsByTitleAndDescription() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();

        searchPageObject.typeSearchInput("Java");
        searchPageObject.waitForElementByTitleAndDescription(
                "Java",
                "Island of Indonesia");
        searchPageObject.waitForElementByTitleAndDescription(
                "Java (programming language)",
                "Object-oriented programming language");
        searchPageObject.waitForElementByTitleAndDescription(
                "JavaScript",
                "Programming language");
        searchPageObject.clearSearchInput();

        searchPageObject.typeSearchInput("Apollo");
        searchPageObject.waitForElementByTitleAndDescription(
                "Apollo",
                "God in Greek mythology");
        searchPageObject.waitForElementByTitleAndDescription(
                "Apollo 11",
                "First spaceflight that landed humans on the Moon");
        searchPageObject.waitForElementByTitleAndDescription(
                "Apollo program",
                "American human spaceflight program");
        searchPageObject.clearSearchInput();

        searchPageObject.typeSearchInput("Selenium");
        searchPageObject.waitForElementByTitleAndDescription(
                "Selenium",
                "Chemical element with atomic number of 34");
        searchPageObject.waitForElementByTitleAndDescription(
                "Selenium (software)",
                "Testing framework for web applications");
        // Тут второй результат поиска с пустым description, но при этом элмента вообще нет
        //searchPageObject.waitForElementByTitleAndDescription(
        //        "Selenium in biology",
        //        "");
        searchPageObject.clearSearchInput();
    }

}
