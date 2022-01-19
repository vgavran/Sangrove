package stage;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MailPage {
    public void GmailLogin(){
        EnvPage.openGmail();
        $("#identifierId").setValue("fwisozk5@gmail.com");
        $(By.xpath("//*[@id='identifierNext']/div/button/div[2]")).click();
        $(By.name("password")).setValue("Aqws12345+");
        $(By.xpath("//*[@id='passwordNext']/div/button/div[2]")).click();
        if ($("//span[contains(text(),'Подтвердите свою личность')]").isDisplayed()) {
            System.out.println("Требуется подтверждение для аккаунта Gmail");
        }
    }

}
