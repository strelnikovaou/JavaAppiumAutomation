package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SavedListsPageObject extends MainPageObject {
    public static final String
            FOLDER_BY_NAME_TPL = "//*[@text=\"{FOLDER_NAME}\"]",
            ARTICLE_BY_TITLE_TPL = "//*[@resource-id=\"org.wikipedia:id/page_list_item_title\"][@text=\"{TITLE}\"]";

    public SavedListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getFolderXpathByName(String folderName) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folderName);
    }

    private static String getSavedArticleXpathByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }

    public void openFolderByName(String folderName) {
        String folderNameXpath = getFolderXpathByName(folderName);
        this.waitForElementAndClick(
                By.xpath(folderNameXpath),
                "Cannot find folder by name " + folderName,
                5);
    }

    public void swipeByArticleToDelete(String articleTitle) {
        this.waitForArticleToAppearByTitle(articleTitle);
        String articleTitleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.swipeElementToLeft(
                By.xpath(articleTitleXpath),
                "Cannot delete saved article");
        this.waitForArticleToDisappearByTitle(articleTitle);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle) {
        String articleTitleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementNotPresent(
                By.xpath(articleTitleXpath),
                "Saved article still present with title " + articleTitle,
                15);
    }

    public void waitForArticleToAppearByTitle(String articleTitle) {
        String articleTitleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementPresent(
                By.xpath(articleTitleXpath),
                "Cannot find saved article with title " + articleTitle,
                15);
    }


}
