package stage;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class RetailerPage {

    public void clickByLinkRetailers() {
        $(By.linkText("Retailers")).click();
        $(".profile-title").shouldBe(Condition.visible);
    }
}
