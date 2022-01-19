package stage;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.*;

public class AdminPage {

    public void loginAsAdmin() {
        EnvPage.openAdminStageUrl();
        // Ввод логина-пароля и аутентификация в админке
        $("#username").setValue("vgavran");
        $("#login").setValue("IOKhsrrsed%H~1");
        $(By.xpath("//button[@class='action-login action-primary']")).click();
        $(By.xpath("//h1[normalize-space()='Dashboard']")).shouldBe(visible);
    }

    public void archiveEvents() {
        //Переход в меню Events в админке
        $("#menu-dowell-campaign-campaigns").click();
        $(By.linkText("Manage Events")).click();
        //Ожидаем пока исчезнет лоадер и подгрузятся записи
        $(By.xpath("//div[@class='admin__data-grid-loading-mask']")).should(disappear);
        //Проверка наличия ивентов не в архиве
        if ($(By.xpath("//div[normalize-space()='Finish' or normalize-space()='Preview brand' ]")).exists()) {
            $(By.xpath("//label[@for='27']")).click();
            $(By.xpath("//button[@title='Select Items']")).click();
            $(By.xpath("//span[normalize-space()='Archive']")).click();
            $(By.xpath("//button[@class='action-primary action-accept']")).click();
            //Проверка наличия сообщения об успешной архивации
            $(By.xpath("//div[@data-ui-id='messages-message-success']")).shouldHave(text("have been archived"));
            $(By.xpath("//div[normalize-space()='Finish']")).shouldNotBe();
        }

    }
}
