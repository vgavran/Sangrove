package stage;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthPage {

    public void authOnSite (){
        // Ввод логина-пароля и аутентификация на сайте
        $("#email").setValue("test@sangrove.com");
        $("#pass").setValue("uHcgV2iGRg");
        $(By.xpath("//*[@id='login-form']/div[5]/input")).click();
    }

    public void logOut (){
        //Log out
        open ("https://testnode.sangrove.com/customer/account/logout/");
        $(By.xpath("//h1[contains(text(),'Log In')]")).shouldBe(Condition.visible);
    }

}
