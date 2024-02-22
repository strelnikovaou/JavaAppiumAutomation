package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionalTests extends CoreTestCase {
    @Test
    public void testChangeScreenOrientationOnSearchResults() {

        String searchLine = "Appium";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.clickOnboardingSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.clickByArticleBySubstring("Automation for Apps");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String titleBeforeRotation = "Appium";
        if (ArticlePageObject.isTitlePresent(titleBeforeRotation)) {
            this.rotateScreenLandscape();
            String titleAfterRotation = titleBeforeRotation;
            assertTrue(
                    "We see unexpected title after rotation",
                    ArticlePageObject.isTitlePresent(titleAfterRotation));
        } else System.out.println("We see unexpected title before rotation");
    }

    @Test
    public void testCheckSearchArticleInBackground() {

        String searchLine = "Appium";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.clickOnboardingSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.waitForSearchResult("Automation for Apps");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Automation for Apps");

    }
}
