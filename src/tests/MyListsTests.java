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

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.clickOnboardingSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleBySubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String articleTitle = "Java (programming language)";
        String folderName = "TestFolderName";
        ArticlePageObject.addArticleToMyList(folderName);
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickSavedLists();
        SavedListsPageObject SavedListsPageObject = new SavedListsPageObject(driver);
        SavedListsPageObject.openFolderByName(folderName);
        SavedListsPageObject.swipeByArticleToDelete(articleTitle);
    }
}
