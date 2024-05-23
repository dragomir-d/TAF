package com.skilo.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.File;
import java.util.List;

public class ProfilePage  extends Iskilo{

    @FindBy(xpath = "/html/body/ngb-modal-window/div/div/app-edit-profile-modal/div/form/div[6]/div/button")
    private WebElement saveButtonOnEditProfile;
    @FindBy(css = ".file[type='file']")
    private WebElement uploadField;

    @FindBy(xpath = "/html/body/app-root/div[2]/app-profile/div/div[1]/app-profile-section/div/div/div[2]/div/div[1]/i")
    private WebElement userEditButton;

    public ProfilePage (WebDriver driver, Logger log) {
        super(driver,log);
        PageFactory.initElements(driver,this);
    }

    public String getUsername() {
        WebElement username = driver.findElement(By.tagName("h2"));
        return username.getText();
    }

    public int getPostCount() {
        List<WebElement> posts = driver.findElements(By.tagName("app-post"));
        return posts.size();
    }

    public void clickAvatar() {
        WebElement avatar = driver.findElement(By.xpath("/html/body/app-root/div[2]/app-profile/div/div[1]/app-profile-section/div/div/div[1]/div[1]/img"));
        avatar.click();
    }

    public void uploadPicture(File file) {
        uploadField.sendKeys(file.getAbsolutePath());
        log.info("CONFIRMATION # The file was successfully uploaded");
    }

    public void clickPost(int postIndex) {
        List<WebElement> posts = driver.findElements(By.tagName("app-post"));
        posts.get(postIndex).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").equals("complete");
    }

    public void clickEditProfileButton() {
        userEditButton.click();
    }

    public void provideTextInPublicInfoField(String text) {
        WebElement publicInfoField = driver.findElement(By.xpath("/html/body/ngb-modal-window/div/div/app-edit-profile-modal/div/form/div[5]/div/textarea"));
        wait.until(ExpectedConditions.visibilityOf(publicInfoField));
        publicInfoField.clear();
        publicInfoField.sendKeys(text);
    }

    public void clickSave() {
        saveButtonOnEditProfile.click();
    }

}
