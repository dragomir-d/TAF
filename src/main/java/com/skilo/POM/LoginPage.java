package com.skilo.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends Iskilo {

    //const
    public static final String LOGIN_PAGE_URL = "http://training.skillo-bg.com:4300/users/login";

    //WebElements or other  UI Map
    @FindBy(css = "p.h4")
    private WebElement loginPageHeaderTitle;
    @FindBy(id = "defaultLoginFormUsername")
    private WebElement usernameInputField;
    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordInputField;
    @FindBy(xpath = "//span[contains(text(),'Remember me')]")
    private WebElement rememberMeLabelText;
    @FindBy(xpath = "//input[contains(@formcontrolname,'rememberMe')]")
    private WebElement rememberMeCheckBox;
    @FindBy(css = "#sign-in-button")
    private WebElement loginFormSubmitButton;
    @FindBy(xpath = "//a[contains(.,'Register')]")
    private WebElement loginFormRegistrationLink;

    //Create a constructor
    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    //User Actions
    public void provideUserName(String userName) {
        waitAndTypeTextInField(usernameInputField, userName);
    }

    public void providePassword(String userPassword) {
        waitAndTypeTextInField(passwordInputField, userPassword);
    }

    public void clickOnLoginSubmitButton() {
        waitAndClickOnWebElement(loginFormSubmitButton);
    }

    public void clickOnRegistrationButton() {
        waitAndClickOnWebElement(loginFormRegistrationLink);
    }

    public void loginWithUSerAndPassword(String userName, String password) {
        provideUserName(userName);
        providePassword(password);
        clickOnLoginSubmitButton();
    }

    //getters
    public String getUserNamePlaceHolder() {
        wait.until(ExpectedConditions.visibilityOf(usernameInputField));
        return usernameInputField.getAttribute("value");
    }

    public boolean isUserNamePlaceHolderCorrect(String expectedUserNamePlaceHolder) {
        boolean isPerRequirments = false;
        try {
            String actualUserNamePlaceHolder = getUserNamePlaceHolder();
            isPerRequirments = expectedUserNamePlaceHolder.equals(actualUserNamePlaceHolder);

        } catch (NoSuchElementException e) {
            log.error("ERROR ! The username placeHolder is not correct");
            isPerRequirments = false;
        }
        return isPerRequirments;
    }

    public boolean isSignInButtonShown() {
        boolean isBUttonShown = false;
        log.info(" ACTION @ The user is verifying if the Sign In button is presented");
        try {
            wait.until(ExpectedConditions.visibilityOf(loginFormSubmitButton));
            log.info("CONFIRM # Navigation logout button is presented to the user");
            isBUttonShown = true;
        } catch (TimeoutException e) {
            log.error("ERROR ! The navigation logout button was not presented to the user");
            isBUttonShown = false;
        }
        return isBUttonShown;
    }

}
