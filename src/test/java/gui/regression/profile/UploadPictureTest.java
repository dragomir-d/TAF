package gui.regression.profile;

import com.skilo.POM.*;
import gui.base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class UploadPictureTest extends TestBase {

    public static final String LOGIN_PAGE_URL = "http://training.skillo-bg.com:4300/users/login";
    @DataProvider(name = "PostTestDataProvider")
    public Object[][] getUsers() {
        File file = new File("/Users/dmuser/Desktop/TAF/src/test/resources/upload/testUpload.jpg");

        return new Object[][]{{
                "gandalf", "thegray", file},
        };
    }


    @Test(dataProvider = "PostTestDataProvider")
    public void verifyUserCanUpdateProfilePicture(
            String USERNAME,
            String PASSWORD,
            File file) {

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

        log.info("STEP 9: Click on avatar");
        profilePage.clickAvatar();

        log.info("STEP 10: Change avatar");
        PostPage postPage = new PostPage(super.driver,log);
        postPage.uploadPicture(file);
//        profilePage.uploadPicture(file);

    }

}
