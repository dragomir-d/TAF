package gui.regression.profile;

import com.skilo.POM.HomePage;
import com.skilo.POM.LoginPage;
import com.skilo.POM.ProfilePage;
import gui.base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateProfileInfoTest extends TestBase {

    public static final String LOGIN_PAGE_URL = "http://training.skillo-bg.com:4300/users/login";

    @Test
    public void verifyUserCanEditProfilePublicInfo() {
        final String USERNAME = "gandalf";
        final String PASSWORD = "thegray";

        HomePage homePage = new HomePage(driver, log);

        log.info("STEP 1: Not logged in user has open the ISkilo HomePage.");
        homePage.openHomePage();
        boolean isLogOutButtonShown = homePage.isLogOutButtonShown();
        Assert.assertFalse(isLogOutButtonShown);

        log.info("STEP 2: The user has navigated to ISkilo LoginPage");
        homePage.navigateToLoginPageViaClickOnNavigationLoginButton();

        log.info("STEP 3: The user has verified that the LoginPage is open as per requirements ");
        LoginPage loginPage = new LoginPage(super.driver,log);
        boolean isShownSignInButton = loginPage.isSignInButtonShown();

        log.info("STEP 4: The user has provided a valid username");
        loginPage.provideUserName(USERNAME);

        log.info("STEP 5: The user has provided a valid password");
        loginPage.providePassword(PASSWORD);

        log.info("STEP 6: The user has clicked on login submit button");
        loginPage.clickOnLoginSubmitButton();

        log.info("STEP 7: Verify user has logged in");
        boolean isShownLogOutButton = homePage.isLogOutButtonShown();
        Assert.assertTrue(isShownLogOutButton);

        log.info("STEP 8: Click on profile button");
        ProfilePage profilePage = new ProfilePage(super.driver, log);
        homePage.clickOnProfileButton();

        log.info("STEP 9: Click on edit profile button");
        profilePage.clickEditProfileButton();

        log.info("STEP 10: Add new text to public info");
        profilePage.provideTextInPublicInfoField("My new public info!");

        log.info("STEP 11: Click Save");
        profilePage.clickSave();

        log.info("STEP 11: Verify the profile is updated");
        String bodyText = driver.findElement(By.xpath("/html/body/app-root/div[2]/app-profile/div/div[1]/app-profile-section/div/div/div[2]/div/div[5]/p")).getText();
        Assert.assertTrue(bodyText.contains("My new public info!"), "Text not found!");
    }

}
