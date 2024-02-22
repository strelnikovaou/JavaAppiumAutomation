package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.NavigationUI;
import lib.ui.SavedListsPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList() {
        String articleTitle = "Java (programming language)";
        String folderName = "TestFolderName";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.clickOnboardingSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleBySubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.addArticletoSavedListAndCreateFolder(folderName);
        ArticlePageObject.clickExploreButton();
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickSavedLists();
        SavedListsPageObject SavedListsPageObject = new SavedListsPageObject(driver);
        SavedListsPageObject.openFolderByName(folderName);
        SavedListsPageObject.swipeByArticleToDelete(articleTitle);
    }

    @Test
    public void testSaveTwoArticlesAndRemove() {
        String folderName = "testFolderName";
        String searchLine = "Java";
        String firstArticleTitle = "Java (programming language)";
        String firstArticleTitleDescription = "Object-oriented programming language";
        String secondArticleTitle = "Java version history";
        String secondArticleTitleDescription = "List of versions of the Java programming language";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.clickOnboardingSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.clickByArticleBySubstring(firstArticleTitleDescription);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.addArticletoSavedListAndCreateFolder(folderName);
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickUpButton();
        SearchPageObject.clickByArticleBySubstring(secondArticleTitleDescription);
        ArticlePageObject.addArticletoSavedListToCreatedFolder(folderName);
        ArticlePageObject.clickExploreButton();
        NavigationUI.clickSavedLists();
        SavedListsPageObject SavedListsPageObject = new SavedListsPageObject(driver);
        SavedListsPageObject.openFolderByName(folderName);
        SavedListsPageObject.swipeByArticleToDelete(firstArticleTitle);
        SavedListsPageObject.waitForArticleToDisappearByTitle(firstArticleTitle);
        SavedListsPageObject.waitForArticleToAppearByTitle(secondArticleTitle);
    }
}
