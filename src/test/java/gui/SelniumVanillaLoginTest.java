package gui;

import gui.base.TestBase;
import org.testng.annotations.Test;

public class SelniumVanillaLoginTest extends TestBase {
    private static final int WAIT = 3333;
    private static final String PAGE_URL = "http://training.skillo-bg.com:4300/posts/all";

    @Test
    public void verifyBasicNavigationWorks() {

        sleepy(WAIT);

        driver.get(PAGE_URL);

        sleepy(WAIT);

    }

}
