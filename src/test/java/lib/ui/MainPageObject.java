package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public String waitForElementAndGetAttr(By by, String attr, String errorText, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorText, timeoutInSeconds);
        return element.getAttribute(attr);
    }

    public void assertElementNotPresent(By by, String errorText) {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements > 0) {
            String defaultMessage = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(defaultMessage + " " + errorText);
        }
    }

    public int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void clickElementToTheRightUpperCorner(By by, String errorText) {
        by = By.xpath(by.toString().replace("By.xpath: ", "") + "/.."); // get parent
        WebElement element = this.waitForElementPresent(by, errorText);
        int rightX = element.getLocation().getX();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;
        int width = element.getSize().getWidth();

        int pointClickX = (rightX + width) - 3;
        int pointClickY = middleY;
        TouchAction action = new TouchAction(driver);
        action.tap(pointClickX, pointClickY).perform();
    }

    public void swipeElementToLeft(By by, String errorText) {
        WebElement element = waitForElementPresent(
                by,
                errorText,
                10);

        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(rightX, middleY).waitAction(150);

        if (Platform.getInstance().isAndroid()) {
            action.moveTo(leftX, middleY);
        } else {
            int offsetX = (-1 * element.getSize().getWidth());
            action.moveTo(offsetX, 0);
        }


        action.release().perform();
    }

    public void swipeUpToFindElement(By by, String errorText, int maxSwipes) {
        int alreadySwipe = 0;
        while (driver.findElements(by).size() == 0) {
            if (alreadySwipe > maxSwipes) {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + errorText, 0);
                return;
            }
            swipeUpQuick();
            alreadySwipe++;
        }
    }

    public void swipeUpToTillElementAppear(By by, String errorText, int maxSwipes) {
        int alreadySwipe = 0;
        while (!this.isElementLocatedOnTheScreen(by)) {
            if (alreadySwipe > maxSwipes) {
                Assert.assertTrue(errorText, this.isElementLocatedOnTheScreen(by));
            }
            swipeUpQuick();
            alreadySwipe++;
        }
    }

    public boolean isElementLocatedOnTheScreen(By by) {
        int elementLocationY = this.waitForElementPresent(by, "Cannot find element by locator", 5).getLocation().getY();
        int screenSizeY = driver.manage().window().getSize().height;

        return elementLocationY < screenSizeY;
    }

    protected void swipeUpQuick() {
        swipeUp(150);
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.7);
        int endY = (int) (size.height * 0.3);

        action
                .press(x, startY)
                .waitAction(timeOfSwipe)
                .moveTo(x, endY)
                .release()
                .perform();
    }

    public void assertElementPresent(By by, String errorText) {
        // По опыту, метод findElement все равно ждет какой то стандартный таймаут. Нам же нужно проверить моментально
        // Поэтому проверка идет по количеству найденных findElements
        // Тем более что у нас уже есть метод для проверки количества элементов
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements == 0) {
            String defaultMessage = "An element '" + by.toString() + "' supposed to be present";
            throw new AssertionError(defaultMessage + " " + errorText);
        }
    }

    /**
     * Ex 2: Написать функцию, которая проверяет наличие текста “Search…” в строке поиска
     * перед вводом текста и помечает тест упавшим, если такого текста нет.
     */
    public void checkElementText(WebElement element, String expectedText) {
        //String actualText = element.getAttribute("text");
        String actualText = element.getText();
        Assert.assertEquals("Element text is not expected!", expectedText, actualText);
    }

    public WebElement waitForElementAndClear(By by, String errorText, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorText, timeoutInSeconds);
        element.clear();
        return element;
    }

    public WebElement waitForElementAndClick(By by, String errorText, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorText, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String errorText, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorText, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementPresent(By by, String errorText) {
        return waitForElementPresent(by, errorText, 5);
    }

    public WebElement waitForElementPresent(By by, String errorText, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorText + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public boolean waitForElementNotPresent(By by, String errorText, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorText + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

}
