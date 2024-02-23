package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
            TITLE_ELEMENT_BY_SUBSTRING_TPL = "//android.view.View[@content-desc=\"{SUBSTRING}\"]",
            FOOTER_ELEMENT = "//android.view.View[@content-desc=\"View article in browser\"]",
            MY_LIST_SAVE_BUTTON = "org.wikipedia:id/page_save",
            MY_LIST_ADD_TO_LIST_BUTTON = "org.wikipedia:id/snackbar_action",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[@text=\"OK\"]",
            MORE_OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc=\"More options\"]",
            MORE_OPTIONS_EXPLORE_BUTTON = "org.wikipedia:id/page_explore",
            FOLDER_TITLE_ELEMENT_BY_SUBSTRING_TPL = "//*[@resource-id=\"org.wikipedia:id/item_title\"][@text=\"{FOLDER_NAME}\"]";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    /*TEMPLATE METHODS*/
    public static String getResultTitleElement(String substring) {
        return TITLE_ELEMENT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public static String getResultFolderTitleElement(String substring) {
        return FOLDER_TITLE_ELEMENT_BY_SUBSTRING_TPL.replace("{FOLDER_NAME}", substring);
    }
    /*TEMPLATE METHODS*/

    public WebElement waitForTitleElement(String xPath) {
        return this.waitForElementPresent(
                By.xpath(xPath),
                "Cannot find article title on page",
                15);
    }

    public boolean isTitlePresent(String substring) {
        try {
            waitForTitleElement(getResultTitleElement(substring));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void assertElementPresent(String title, String errorMessage) {
        Assert.assertTrue(errorMessage, isTitlePresent(title));
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20);
    }

    public void addArticletoSavedListAndCreateFolder(String folderName) {
        this.waitForElementAndClick(
                By.id(MY_LIST_SAVE_BUTTON),
                "Cannot find \"Save\" button",
                5);

        this.waitForElementAndClick(
                By.id(MY_LIST_ADD_TO_LIST_BUTTON),
                "Cannot find \"Add to list\" button",
                5);

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                folderName,
                "Cannot put text into article folder input",
                5);

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot press \"OK\" button to save folder name",
                5);
    }

    public void addArticletoSavedListToCreatedFolder(String folderName) {
        this.waitForElementAndClick(
                By.id(MY_LIST_SAVE_BUTTON),
                "Cannot find \"Save\" button",
                5);

        this.waitForElementAndClick(
                By.id(MY_LIST_ADD_TO_LIST_BUTTON),
                "Cannot find \"Add to list\" button",
                5);

        String folderTitleElement = getResultFolderTitleElement(folderName);
        this.waitForElementAndClick(
                By.xpath(folderTitleElement),
                "Cannot find folder by " + folderTitleElement,
                5);
    }


    public void clickExploreButton() {
        this.waitForElementAndClick(
                By.xpath(MORE_OPTIONS_BUTTON),
                "Cannot find button to open article options",
                15);

        this.waitForElementAndClick(
                By.id(MORE_OPTIONS_EXPLORE_BUTTON),
                "Cannot find explore button",
                5);
    }


}
