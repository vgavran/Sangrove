package stage;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class ProfileTests {
        public final AuthPage authPage = new AuthPage();
        public final ProfilePage profilePage = new ProfilePage();

    @BeforeTest
    public void setupClass() throws MalformedURLException {
        SetupPage.setupClass();
        }


    @Test
    public void addProfileInfo() {

        System.out.println("Тест addProfileInfo завершен");

        EnvPage.openStageUrl();
        //Авторизация на сайте
        authPage.authOnSite();

        //Переход в профиль
        $(".bar-header--item").hover();
        $(By.xpath("//a[normalize-space()='My profile']")).click();
        $(By.xpath("//a[normalize-space()='Edit profile']")).click();

        //Заоплнение данных на табе Account info
        $(By.id("company_name")).setValue("Your Brand, Inc");
        $(By.id("company_city")).setValue("Aartselaar");
        $(By.id("postal_code")).setValue("23462");
        $(By.id("contact_number")).setValue("0234685475");
        $(By.id("website")).setValue("www.sangrove.com");
        $(By.id("country")).selectOptionContainingText("Belgium");
        $(By.id("region")).setValue("Selandia");
        $(By.id("address")).setValue("123 Hillwood st");
        $(By.id("taxvat")).setValue("25");
        $(By.xpath("//input[@value='Save']")).click();

        //Проверка сохранения
        $(By.xpath("//div[@class='notification-text']")).shouldHave(text("Profile information was successfully saved"));

        //Выход из аккаунта
        authPage.logOut();

        System.out.println("Тест changePassword завершен");

    }

    @Test
    public void addAddressInfo() {

        System.out.println("Тест addAddressInfo завершен");

        EnvPage.openStageUrl();
        //Авторизация на сайте
        authPage.authOnSite();

        //Переход в профиль
        $(".bar-header--item").hover();
        $(By.xpath("//a[normalize-space()='My profile']")).click();
        $(By.xpath("//a[normalize-space()='Shipping address']")).click();

        //Заполнение данных на табе Shipping address
         if ($(By.xpath("//a[@role='delete-address']")).exists()) {
             $("trash delete").click();
             $(By.xpath("//span[normalize-space()='OK']")).click();
             $(By.xpath("//div[@class='notification-text']")).shouldHave(text("The address has been deleted"));
         }

        $(By.id("street_1")).setValue("124");
        $(By.id("street_2")).setValue("Hillwood st");
        $(By.id("country")).selectOptionContainingText("Belgium");
        $(By.id("region")).setValue("Selandia");
        $(By.id("city")).setValue("Aartselaar");
        $(By.id("zip1")).setValue("125478");
        $(By.id("telephone")).setValue("09442587412");
        $(By.id("email")).setValue("test@sangrove.com");

        $(By.xpath("//button[@title='Save']")).click();
        $(By.xpath("//div[@class='notification-text']")).shouldHave(text("The address has been saved"));

        //Выход из аккаунта
        authPage.logOut();

        System.out.println("Тест addAddressInfo завершен");

    }

    /******************************************************************/
    /*Тест изменения пароля в профиле*/
    /******************************************************************/
    @Test
    public void changePassword() {

        System.out.println("Тест changePassword начат");

        //Открытие url приложения
        EnvPage.openStageUrl();
        //Авторизация на сайте
        authPage.authOnSite();
        //Смена пароля
        profilePage.changePassword();
        //Выход из аккаунта
        authPage.logOut();

        System.out.println("Тест changePassword завершен");
    }

    @AfterTest
    public static void endSession() {
        closeWebDriver();
    }

}
