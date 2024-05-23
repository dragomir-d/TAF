package com.skilo.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends Iskilo {
    public static final String HOME_PAGE_URL = "posts/all";

    @FindBy (xpath = "/html/body/app-root/div[2]/app-all-posts/div/div/div[1]/app-post-detail/div/div[2]/div/div[1]/i[1]")
    private WebElement likeButtonOfFirstImage;
    @FindBy (id = "nav-link-login")
    private WebElement navigationLoginButton;
    @FindBy (id = "nav-link-new-post")
    private WebElement navigationNewPostButton;

    @FindBy (xpath = "//ul[contains(@class,'navbar-nav my-ml d-none d-md-block')]")
    private WebElement navigationLogOutButton;

    @FindBy (id = "#nav-link-profile")
    private WebElement profileLoggedInButton;

    @FindBy (xpath = "//*[@id=\"nav-link-profile\"]")
    private WebElement goToProfileButton;



    public HomePage (WebDriver driver, Logger log)  {
        super(driver,log);

        PageFactory.initElements(driver,this);
    }

    public void openHomePage () {
        navigateTo(HOME_PAGE_URL);
    }

    public void navigateToLoginPageViaClickOnNavigationLoginButton(){
        waitAndClickOnWebElement(navigationLoginButton);
    }
    public void clickOnNavButtonForNewPost(){
        waitAndClickOnWebElement(navigationNewPostButton);
    }

    public void waitNewPostNavigationLinkToAppear(){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) navigationNewPostButton));
    }

    public boolean isLogOutButtonShown(){
        boolean isBUttonShown = false;
        log.info(" ACTION @ The user is verifying if the navigation log out button is presented");
        try {
            wait.until(ExpectedConditions.visibilityOf(navigationLogOutButton));
            log.info("CONFIRM # Navigation logout button is presented to the user");
            isBUttonShown= true;
        } catch ( TimeoutException e) {
            log.error("ERROR ! The navigation logout button was not presented to the user");
            isBUttonShown = false;
        }
        return isBUttonShown;
    }

    public void clickOnProfileButton() {
        waitAndClickOnWebElement(goToProfileButton);
    }

    public void likeFirstImage() {
        likeButtonOfFirstImage.click();
    }
}
