package gui.regression.registration;

import com.skilo.POM.HomePage;
import com.skilo.POM.LoginPage;
import com.skilo.POM.RegistrationPage;
import gui.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class HandleErrorsRegistrationTest extends TestBase {
    //  /html/body/app-root/div[2]/app-register/div/div/form/div[1]/span

    @Test
    public void verifyUserCannotRegisterWithOneCharUsername() {
        HomePage homePage = new HomePage(super.driver, log);

        log.info("STEP 1: Not logged in user has open the ISkilo HomePage.");
        homePage.openHomePage();
        boolean isLogOutButtonShown = homePage.isLogOutButtonShown();
        Assert.assertFalse(isLogOutButtonShown);

        log.info("STEP 2: The user has navigated to ISkilo LoginPage");
        homePage.navigateToLoginPageViaClickOnNavigationLoginButton();

        log.info("STEP 3: The user has clicked Register");
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.clickOnRegistrationButton();

        //Must add new valid data for registration. Email must be under 20 chars.
        log.info("STEP 4: The user provides registration data");
        RegistrationPage registrationPage = new RegistrationPage(super.driver, log);
        registrationPage.fullRegistrationInputsAndActions("t", "wtf@gmail.com", "123456");

        log.info("STEP 5: Verify the warning message is shown");
        String actualString = driver.findElement(By.xpath("/html/body/app-root/div[2]/app-register/div/div/form/div[1]/span")).getText();
        String expectedString = "Minimum 2 characters !";
        assertTrue(actualString.contains(expectedString));

        log.info("STEP 6: Click on Sign In button");
        registrationPage.clickOnSignInButton();

        log.info("STEP 7: Verify the user is not registered and logged in");
        Assert.assertFalse(homePage.isLogOutButtonShown(), "The logout button is not shown!");
    }

    @Test
    public void verifyUserCannotRegisterWithLessThanSixCharsPassword() {
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
        registrationPage.fullRegistrationInputsAndActions("tax", "wtf@gmail.com", "123");

        log.info("STEP 5: Verify the warning message is shown");
        String actualString = driver.findElement(By.xpath("/html/body/app-root/div[2]/app-register/div/div/form/div[3]/span")).getText();
        String expectedString = "Minimum 6 characters !";
        assertTrue(actualString.contains(expectedString));

        log.info("STEP 6: Click on Sign In button");
        registrationPage.clickOnSignInButton();

        log.info("STEP 7: Verify the user is not registered and logged in");
        Assert.assertFalse(homePage.isLogOutButtonShown(), "The logout button is not shown!");
    }
}
