package stage;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

import java.net.MalformedURLException;

public class SetupPage {
    public static void setupClass() throws MalformedURLException {
        Configuration.browser = WebDriverRunner.CHROME;
        Configuration.timeout = 15000;
        Configuration.browserPosition = "1x1";
        Configuration.browserSize = "1200x800";

    }
}

