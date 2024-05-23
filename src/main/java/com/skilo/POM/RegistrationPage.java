package com.skilo.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
public class RegistrationPage extends Iskilo{

    public static final String REGISTER_PAGE_URL = "http://training.skillo-bg.com:4200/users/register";

//WebElements or other  UI Map
    @FindBy (xpath = "/html/body/app-root/div[2]/app-register/div/div/form/h4")
    private WebElement registerPageHeaderTitle;

    @FindBy (xpath = "/html/body/app-root/div[2]/app-register/div/div/form/div[1]/input")
    private WebElement usernameInputField;

    @FindBy (xpath = "/html/body/app-root/div[2]/app-register/div/div/form/div[2]/input")
    private WebElement emailInputField;

    @FindBy (xpath = "//*[@id=\"defaultRegisterFormPassword\"]")
    private WebElement passwordInputField;

    @FindBy (xpath = "//*[@id=\"defaultRegisterPhonePassword\"]")
    private WebElement confirmPasswordInputField;

    @FindBy (css = "#sign-in-button")
    private WebElement registrationSignInButton;

    public RegistrationPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver,this);
    }

//User Actions

    public void provideUsername (String username) {
        waitAndTypeTextInField(usernameInputField, username);
    }

    public void provideEmail (String email) {
        waitAndTypeTextInField(emailInputField, email);
    }

    public void providePassword (String password) {
        waitAndTypeTextInField(passwordInputField, password);
    }

    public void provideConfirmPassword (String password) {
        waitAndTypeTextInField(confirmPasswordInputField, password);
    }

    public void clickOnSignInButton() {
        waitAndClickOnWebElement(registrationSignInButton);
    }

    public void fullRegistrationInputsAndActions (String username, String email, String password) {
        provideUsername(username);
        provideEmail(email);
        providePassword(password);
        provideConfirmPassword(password);
        clickOnSignInButton();
    }

//Getters
    public String getUserNamePlaceHolder () {
        wait.until(ExpectedConditions.visibilityOf(usernameInputField));
        return usernameInputField.getAttribute("value");
    }

    public boolean isUserNamePlaceHolderCorrect(String expectedUserNamePlaceHolder) {
        boolean isPerRequirments = false;
        try {
            String actualUserNamePlaceHolder = getUserNamePlaceHolder();
            isPerRequirments = expectedUserNamePlaceHolder.equals(actualUserNamePlaceHolder);

        }catch (NoSuchElementException e){
            log.error("ERROR ! The username placeHolder is not correct");
            isPerRequirments = false;
        }
        return isPerRequirments;
    }


}
