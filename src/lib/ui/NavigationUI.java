package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {
    private static final String
            SAVED_LINKS = "//android.widget.FrameLayout[@content-desc=\"Saved\"]";

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickSavedLists() {
        this.waitForSavedElementToAppear(SAVED_LINKS);
        this.waitForElementAndClick(
                By.xpath(SAVED_LINKS),
                "Cannot find navigation button to reading list",
                5);
    }

    public void waitForSavedElementToAppear(String savedButtonXpath) {
        this.waitForElementPresent(
                By.xpath(savedButtonXpath),
                "Cannot find saved article with title " + savedButtonXpath,
                15);
    }


}
