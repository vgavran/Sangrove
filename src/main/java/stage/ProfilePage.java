package stage;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {

    public void changePassword(){
        $(".bar-header--item").hover();
        $(By.xpath("//a[normalize-space()='Change password']")).click();
        $(By.id("current-password")).setValue("uHcgV2iGRg");
        $(By.id("password")).setValue("uHcgV2iGRg");
        $(By.id("password-confirmation")).setValue("uHcgV2iGRg");
        $(By.xpath("//input[@value='Save']")).click();
        $(By.xpath("//div[@class='notification-text']")).waitUntil(visible, 10000).shouldHave(text("Your password has been changed"));
    }
}
