package stage;

import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
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

    public void addProductFromFile() {
        $(".add-product--btn").click();
        $(By.name("massupload_csv")).uploadFile(new File("C:\\Test\\ProductDM21.xlsx"));
    }

    public void waitProgressBar() {
        $(By.xpath("//div[@class='loader-line']//span")).waitUntil(visible, 10000).should(disappear);
    }

    public void checkdSuccessfullyAddedProducts() {
        $(By.xpath("//a[contains(text(), 'Save')]")).waitUntil(visible, 5000).click();
        $(By.xpath("//div[@class='notification-text']")).waitUntil(visible, 5000).shouldHave(text("You have successfully added"));
    }

    public void addProductToBasket() {
        $(By.xpath("//button[@type='submit'][normalize-space()='Buy']")).click();
    }


}
