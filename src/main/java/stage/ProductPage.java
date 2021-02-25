package stage;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ProductPage {

    public void clickButtonNext() {
        $("#steps-uid-1 > div.actions.clearfix > ul > li:nth-child(2) > a").click();
    }

    public void clickButtonBack() {
        $("#steps-uid-1 > div.actions.clearfix > ul > li.prev-step-btn > a").click();
    }

    public void clickButtonFinish() {
        $("#steps-uid-1 > div.actions.clearfix > ul > li:nth-child(3) > a").click();
    }

    public void clickByLinkProducts() {
        $(By.linkText("Products")).click();
    }

    public void checkAddNewProduct (String ProdName) {
        $("div.card-title > a").shouldHave(text(ProdName));
    }

    public void addProductManually() {
        $(By.linkText("Add new product")).click();
        $(By.linkText("Add manually")).click();
    }
}
