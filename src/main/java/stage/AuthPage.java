package stage;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class AuthPage {

    public void authOnSite (){
        // Ввод логина-пароля и аутентификация на сайте
        $("#email").setValue("test@sangrove.com");  //
        $("#pass").setValue("uHcgV2iGR");  //
        $(By.xpath("//*[@id='login-form']/div[5]/input")).click();
    }

    public void authOnSiteWithLoginPass (String userEmail, String userPass) {
        // Ввод логина-пароля и аутентификация на сайте
        $("#email").setValue(userEmail);
        $("#pass").setValue(userPass);
        $(By.xpath("//*[@id='login-form']/div[5]/input")).click();
    }

    public void logOut(){
        //Выход из аккаунта
        $(".bar-header--item").hover();
        $(By.xpath("//a[normalize-space()='Log out']")).click();
    }

    public void isProfileDisplayed(){
        //Проверяем отображение иконки профиля
        $(".bar-header--item").isDisplayed();
    }

    public void checkValidationEmailMask (){
        // Ввод некорректного логина и попытка аутентификации
        $("#email").setValue("test@sangrove");
        $("#pass").setValue("uHcgV2iGRg");
        $(By.xpath("//*[@id='login-form']/div[5]/input")).click();
        //Проверка вывода сообщения после неуспешной авторизации
        $(By.xpath("//div[@class='notification-text']"))
                .shouldHave(Condition.text("The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later."));
    }

    public void checkAuthWrongPassword(){
        // Ввод неправильного пароля и попытка аутентификации
        $("#email").setValue("test@sangrove.com");
        $("#pass").setValue("123*56");
        $(By.xpath("//*[@id='login-form']/div[5]/input")).click();
        //Проверка вывода сообщения после неуспешной авторизации
        $(By.xpath("//div[@class='notification-text']"))
                .shouldHave(Condition.text("The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later."));
    }

}
