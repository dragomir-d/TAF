package gui.tests.registration;

import com.skilo.POM.HomePage;
import com.skilo.POM.LoginPage;
import com.skilo.POM.RegistrationPage;
import gui.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends TestBase {

    public static final int WAIT = 3333;

    @Test
    public void verifyUserCanRegisterWithValidData() throws InterruptedException {

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
        registrationPage.fullRegistrationInputsAndActions("6r4go", "wtf@gmail.com", "123456");

        log.info("STEP 4: Verify the user is successfully logged in after registration");
        Assert.assertTrue(homePage.isLogOutButtonShown(), "The logout button is not shown!");
    }
}
