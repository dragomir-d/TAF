package gui.e2e;

import com.skilo.POM.HomePage;
import com.skilo.POM.LoginPage;
import com.skilo.POM.RegistrationPage;
import gui.base.TestBase;
import gui.regression.registration.RegistrationTest;
import org.testng.Assert;

public class RegisterAndLoginTest extends TestBase {

    public static final int WAIT = 3333;

    public void registerWithValidDataAndLoginWithTheCredentials(String username, String email, String password) {

        HomePage homePage = new HomePage(super.driver, log);

        log.info("STEP 1: Not logged in user has open the ISkilo HomePage.");
        homePage.openHomePage();
        boolean isLogOutButtonShown = homePage.isLogOutButtonShown();
        Assert.assertFalse(isLogOutButtonShown);

        log.info("STEP 2: The user has navigated to ISkilo LoginPage");
        homePage.navigateToLoginPageViaClickOnNavigationLoginButton();

        log.info("STEP 3: The user has clicked Register");
        LoginPage loginPage = new LoginPage(super.driver,log);
        loginPage.clickOnRegistrationButton();


        //Must add new valid data for registration. Email must be under 20 chars.
        log.info("STEP 4: The user provides registration data");
        RegistrationPage registrationPage = new RegistrationPage(super.driver, log);
        registrationPage.fullRegistrationInputsAndActions(username, email, password);
        Assert.assertTrue(homePage.isLogOutButtonShown(), "The logout button is not shown!");
    }
}
