package gui.tests.login;

import com.skilo.POM.HomePage;
import com.skilo.POM.LoginPage;
import gui.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    public static final int WAIT = 3333;

    @Test
    public void verifyUserCanNavigateToLoginPageViaNavigationLoginButton() throws InterruptedException {
        //const and vars that will be used in the test case
        final String USERNAME = "gandalf";
        final String PASSWORD = "thegray";

        HomePage homePage = new HomePage(super.driver, log);

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
    }
}
