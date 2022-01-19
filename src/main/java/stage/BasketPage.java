package stage;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BasketPage {

    public void goToBasketAndSubmitOrder() {
        //Переход в корзину и подтверждение заказа
        $(".icon-basket").click();
        $(By.xpath("//a[normalize-space()='Place order']")).click();
        $(By.xpath("//div[@class='payment-method--item nodisable']")).click();
        $(By.id("submit_order")).click();
        /* Old
        //$(By.xpath("//div[@data-fcont='wire_transfer']")).click();
        $(By.xpath("//a[normalize-space()='Next']")).click();

        //Нажимаем на странице Make a Payment кнопку подтверждения
        $(By.xpath("//a[normalize-space()='Next']")).click();
        if ($("#account_region_id-error").isDisplayed()) {
            $("#account_region_id").selectOptionByValue("19");
            $(By.xpath("//a[normalize-space()='Next']")).click();
        }
        $(By.xpath("//a[normalize-space()='Submit']")).click();
        $(By.xpath("//img[@alt='Loading...']")).should(disappear); */
    }

    public void checkOrderIsCreated() {
        //Проверка наличия сообщения "Congratulations"
        $(By.xpath("//p[@class='popup-title']")).isDisplayed(); //
        $(By.xpath("//p[contains(text(),'Your order number')]")).shouldBe();
        Selenide.sleep(1000);
        //Закрытие сообщения и переход в детали заказа
        $(By.xpath("//a[normalize-space()='OK']")).click();
        $(By.xpath("//p[normalize-space()='Order details']")).shouldBe();
        Selenide.sleep(2000);
    }

}
