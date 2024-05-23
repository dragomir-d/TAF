package gui.regression.post;

import com.skilo.POM.*;
import gui.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class PostHappyTest extends TestBase {
    @DataProvider(name = "PostTestDataProvider")
    public Object[][] getUsers() {
        File postPicture = new File("src/test/resources/upload/testUpload.jpg");
        String caption = "Testing create post caption";

        return new Object[][]{{
            "testingDemos", "testing",
            "testingDemos", postPicture, caption},
            };
    }
    @Test(dataProvider = "PostTestDataProvider")
    public void verifyUserCanCreateNewPost(
            String user,
            String password,
            String username,
            File file,
            String caption)
                            {



    }
}
