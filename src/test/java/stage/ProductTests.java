package stage;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.RandomUtils;

import java.io.File;
import java.net.MalformedURLException;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class ProductTests {
    public final ProductPage productPage = new ProductPage();
    public final AuthPage authPage = new AuthPage();

    @BeforeTest
    public static void setupClass () throws MalformedURLException {
        SetupPage.setupClass();
    }

    /******************************************************************/
    /*Тест добавления нового продукта с заполнением обязательных полей*/
    /******************************************************************/
    @Test
    public void addNewProductWithRequiredFields() {

        System.out.println("Тест addNewProductWithRequiredFields начат");

        //Открываем url приложения
        EnvPage.openStageUrl();
        //Авторизация пользователя
        authPage.authOnSite();

        // Переход в раздел продуктов и добавление нового продукта
        productPage.clickByLinkProducts();
        productPage.addProductManually();

        // Проверка нахождения на шаге 1 (информация о продукте)
        $("#steps-uid-1-p-0>div.steps-form--name").shouldHave(Condition.text("Product info"));
        // Заполнение обязательных полей на шаге 1
        String ProdName = "Product_"+ RandomUtils.getRandomLetters();
        $("#product_name").setValue(ProdName);
        $("#product_cateogry").selectOptionContainingText("- Chairs");
        $("#product_increment").setValue("10");
        $("#product_terms").selectOptionByValue("13");
        $("#product_material").setValue("Wood");
        $("#product_color").setValue("Green");
        productPage.clickButtonNext();

        // Проверка нахождения на шаге 2
        $("#steps-uid-1-p-1 > div.steps-form--name").shouldHave(Condition.text("Product unique ID"));
        // Заполнение обязательных полей на шаге 2
        $("#product_sku").setValue("201");
        $(By.name("product[ean]")).setValue("202");
        productPage.clickButtonNext();

        // Проверка нахождения на шаге 3
        $("#steps-uid-1-p-2>div.steps-form--name").shouldHave(Condition.text("Product dimensions"));
        // Заполнение обязательных полей на шаге 3
        $("#product_height").setValue("350");
        $("#product_weight").setValue("300");
        productPage.clickButtonNext();

        // Проверка нахождения на шаге 4
        $("#steps-uid-1-p-3>div.steps-form--name").shouldHave(Condition.text("Packing info"));
        // Заполнение обязательных полей на шаге 4
        $("#product_number_item_article").setValue("40");
        $("#inner_box").setValue("41");
        $("#inner_box_length").setValue("42");
        $("#inner_box_height").setValue("43");
        $("#inner_box_width").setValue("44");
        $("#inner_box_weight").setValue("45");
        $("#inner_box_volume").setValue("46");
        $(By.name("product[outer_box_quantity]")).setValue("47");
        $(By.name("product[length_outer_box]")).setValue("48");
        $(By.name("product[height_outer_box]")).setValue("49");
        $(By.name("product[width_outer_box]")).setValue("50");
        $(By.name("product[weight_outer_box]")).setValue("51");
        $(By.name("product[volume_outer_box]")).setValue("52");
        productPage.clickButtonNext();

        // Проверка нахождения на шаге 5
        $("#steps-uid-1-p-4>div.steps-form--name").shouldHave(Condition.text("Manufacturing info"));
        // Заполнение обязательных полей на шаге 5
        $(By.name("product[supplier_number]")).setValue("500");
        $("#production_coo").selectOptionByValue("BD");
        $("#product_lead_time").setValue("10");
        $(By.name("product[moq]")).setValue("505");
        $(By.name("product[htc]")).setValue("510");
        productPage.clickButtonNext();

        // Проверка нахождения на шаге 6
        $("#steps-uid-1-p-5>div.steps-form--name").shouldHave(Condition.text("Pricing"));
        // Заполнение обязательных полей на шаге 6
        $(By.name("product[rrp][]")).setValue("600");
        $(By.name("product[ddp]")).setValue("610");
        $(By.name("product[fob]")).setValue("620");

        productPage.clickButtonNext();

        // Проверка нахождения на шаге 7
        $("#steps-uid-1-p-6>div.steps-form--name").shouldHave(Condition.text("Product images"));
        // Загрузка фото товара на шаге 7
        // Тестовый файл для загрузки должен находится в указанной директории
        $("#file-1").uploadFile(new File("C:\\Test\\test.jpg"));
        productPage.clickButtonFinish();

        //Переход в раздел Products и проверка наличия добавленного продукта
        productPage.clickByLinkProducts();
        productPage.checkAddNewProduct(ProdName);

        //Выход из аккаунта
        $(".account").click();
        $(By.linkText("Log out")).click();
        $(By.xpath("//h1[contains(text(),'Log In')]")).shouldBe(Condition.visible);
        //authPage.logOut();

        System.out.println("Тест addNewProductWithRequiredFields завершен");
    }

    /**********************************************************************/
    /* Тест добавления нового продукта с заполнением всех возможных полей */
    /**********************************************************************/
    @Test
    public void addNewProductWithAllFields() {

        System.out.println("Тест addNewProductWithAllFields начат");

        //Открываем url приложения
        EnvPage.openStageUrl();
        //Авторизация пользователя
        authPage.authOnSite();

        // Переход в раздел продуктов и добавление нового продукта
        productPage.clickByLinkProducts();
        productPage.addProductManually();

        // Проверка нахождения на шаге 1 (информация о продукте)
        $("#steps-uid-1-p-0>div.steps-form--name").shouldHave(Condition.text("Product info"));
        // Заполнение всех возможных полей на шаге 1
        String ProdName = "Product_all"+ RandomUtils.getRandomLetters();
        $("#product_name").setValue(ProdName);
        $("#product_season").setValue("All season");
        $("#product_collection").setValue("All collection");
        $(By.name("product[line]")).setValue("All product line");;
        $("#product_cateogry").selectOptionContainingText("- Chairs");
        $("#product_style").selectOptionContainingText("Classic");
        $(By.name("product[item_status]")).selectOptionContainingText("New");
        $("#product_increment").setValue("10");
        $("#product_terms").selectOptionByValue("13");
        $("#description_1").setValue("Description new product...");
        $("div.add-more").click();
        $(By.name("description_2")).setValue("Addition description 2");
        $("#product_material").setValue("Wood");
        $(By.name("product[detailed_material]")).setValue("Information about product material");
        $("#product_color").setValue("White");
        productPage.clickButtonNext();

        // Проверка нахождения на шаге 2
        $("#steps-uid-1-p-1 > div.steps-form--name").shouldHave(Condition.text("Product unique ID"));
        // Заполнение всех возможных полей на шаге 2
        $("#product_sku").setValue("201");
        $(By.name("product[ean]")).setValue("202");
        productPage.clickButtonNext();

        // Проверка нахождения на шаге 3
        $("#steps-uid-1-p-2>div.steps-form--name").shouldHave(Condition.text("Product dimensions"));
        // Заполнение всех возможных полей на шаге 3
        $(By.name("product[measurement]")).selectRadio("49");
        $("#product_height").setValue("300");
        $("#product_width").setValue("310");
        $("#product_depth").setValue("320");
        $("#product_diameter").setValue("330");
        $("#product_volume").setValue("340");
        $("#product_weight").setValue("350");
        productPage.clickButtonNext();

        // Проверка нахождения на шаге 4
        $("#steps-uid-1-p-3>div.steps-form--name").shouldHave(Condition.text("Packing info"));
        // Заполнение всех возможных полей на шаге 4
        $("#product_number_item_article").setValue("40");
        $(By.name("product[gift_box]")).click();
        $("#inner_box").setValue("41");
        $("#inner_box_length").setValue("42");
        $("#inner_box_height").setValue("43");
        $("#inner_box_width").setValue("44");
        $("#inner_box_weight").setValue("45");
        $("#inner_box_volume").setValue("46");
        $(By.name("product[outer_box_quantity]")).setValue("47");
        $(By.name("product[length_outer_box]")).setValue("48");
        $(By.name("product[height_outer_box]")).setValue("49");
        $(By.name("product[width_outer_box]")).setValue("50");
        $(By.name("product[weight_outer_box]")).setValue("51");
        $(By.name("product[volume_outer_box]")).setValue("52");
        productPage.clickButtonNext();

        // Проверка нахождения на шаге 5
        $("#steps-uid-1-p-4>div.steps-form--name").shouldHave(Condition.text("Manufacturing info"));
        // Заполнение всех возможных полей на шаге 5
        $(By.name("product[supplier_number]")).setValue("500");
        $("#production_coo").selectOptionContainingText("China");
        //$("#production_port").selectOptionContainingText("Honkong"); //список портов не всегда доступен

        $("#production_coo").selectOptionByValue("BD");
        $("#product_lead_time").setValue("10");
        $(By.name("product[moq]")).setValue("505");
        $(By.name("product[htc]")).setValue("510");
        $(By.name("product[over_moq]")).click();
        productPage.clickButtonNext();

        // Проверка нахождения на шаге 6
        $("#steps-uid-1-p-5>div.steps-form--name").shouldHave(Condition.text("Pricing"));
        // Заполнение всех возможных полей на шаге 6
        $(By.name("product[rrp][]")).setValue("600");
        $("#rrp_currency").selectOptionContainingText("EUR");
        $(By.name("product[ddp]")).setValue("610");
        $("#ddp_currency").selectOptionContainingText("EUR");
        $("div.add-more").click();
        $("#rrp_price_2").setValue("615");
        $(By.name("product[fob]")).setValue("620");
        $("#fob_currency").selectOptionContainingText("EUR");
        productPage.clickButtonNext();

        // Проверка нахождения на шаге 7
        $("#steps-uid-1-p-6>div.steps-form--name").shouldHave(Condition.text("Product images"));
        // Заполнение всех возможных полей на шаге 7
        // Тестовый файл для загрузки должен находится в указанной директории
        $("#file-1").uploadFile(new File("C:\\Test\\test.jpg"));
        //$("#file-2").uploadFile(new File("C:\\Test\\test2.jpg")); //при загрузке 2 и 3 файла не нажимается кнопка Finish
        //$("#file-3").uploadFile(new File("C:\\Test\\test3.jpg"));
        productPage.clickButtonFinish();

        //Переход в раздел Products и проверка наличия добавленного продукта
        productPage.clickByLinkProducts();
        productPage.checkAddNewProduct(ProdName);

        //Выход из аккаунта
        authPage.logOut();

        System.out.println("Тест addNewProductWithAllFields пройден");
    }

    /********************************************************************************/
    /* Проверка необходимости заполнения обязательных полей при добавлении продукта */
    /********************************************************************************/
    @Test
    public void checkRequiredFieldsAddProductForm (){

        System.out.println("Тест checkRequiredFieldsAddProductForm начат");

        //Открываем url приложения
        EnvPage.openStageUrl();
        //Авторизация пользователя
        authPage.authOnSite();

        // Переход в раздел продуктов и добавление нового продукта
        productPage.clickByLinkProducts();
        productPage.addProductManually();

        // Проверка нахождения на шаге 1
        $("#steps-uid-1-p-0>div.steps-form--name").shouldHave(Condition.text("Product info"));
        // Нажатие на кнопку перехода на следующий шаг без заполнения обязательных полей
        productPage.clickButtonNext();
        // Проверка наличия валидации обязательных полей
        $("#product_name-error").shouldHave(Condition.text("This is a required field."));
        $("#product_cateogry-error").shouldHave(Condition.text("This is a required field."));
        $("#product_increment-error").shouldHave(Condition.text("This is a required field."));
        $("#product_terms-error").shouldHave(Condition.text("This is a required field."));
        $("#product_material-error").shouldHave(Condition.text("This is a required field."));
        $("#product_color-error").shouldHave(Condition.text("This is a required field."));

        // Заполнение обязательных полей на шаге 1
        String ProdName = "Product_"+ RandomUtils.getRandomLetters();
        $("#product_name").setValue(ProdName);
        $("#product_cateogry").selectOptionContainingText("- Chairs");
        $("#product_increment").setValue("10");
        $("#product_terms").selectOptionByValue("13");
        $("#product_material").setValue("Wood");
        $("#product_color").setValue("Green");
        productPage.clickButtonNext();

        // Проверка нахождения на шаге 2
        $("#steps-uid-1-p-1 > div.steps-form--name").shouldHave(Condition.text("Product unique ID"));
        // Нажатие на кнопку перехода на следующий шаг без заполнения обязательных полей
        productPage.clickButtonNext();
        // Проверка наличия валидации обязательных полей
        $("#product_sku-error").shouldHave(Condition.text("This is a required field."));
        //$("#product[ean]-error").shouldHave(Condition.text("This is a required field."));

        // Заполнение обязательных полей на шаге 2
        $("#product_sku").setValue("201");
        $(By.name("product[ean]")).setValue("202");
        productPage.clickButtonNext();

        // Проверка нахождения на шаге 3
        $("#steps-uid-1-p-2>div.steps-form--name").shouldHave(Condition.text("Product dimensions"));
        // Нажатие на кнопку перехода на следующий шаг без заполнения обязательных полей
        productPage.clickButtonNext();
        // Проверка наличия валидации обязательных полей
        $("#product_height-error").shouldHave(Condition.text("This is a required field."));
        $("#product_weight-error").shouldHave(Condition.text("This is a required field."));

        // Заполнение обязательных полей на шаге 3
        $("#product_height").setValue("350");
        $("#product_weight").setValue("300");
        productPage.clickButtonNext();

        // Проверка нахождения на шаге 4
        $("#steps-uid-1-p-3>div.steps-form--name").shouldHave(Condition.text("Packing info"));
        // Нажатие на кнопку перехода на следующий шаг без заполнения обязательных полей
        productPage.clickButtonNext();
        // Проверка наличия валидации обязательных полей
        $("#product_number_item_article-error").shouldHave(Condition.text("This is a required field."));
        $("#inner_box-error").shouldHave(Condition.text("This is a required field."));
        $("#inner_box_length-error").shouldHave(Condition.text("This is a required field."));
        $("#inner_box_height-error").shouldHave(Condition.text("This is a required field."));
        $("#inner_box_width-error").shouldHave(Condition.text("This is a required field."));
        $("#inner_box_weight-error").shouldHave(Condition.text("This is a required field."));
        $("#inner_box_volume-error").shouldHave(Condition.text("This is a required field."));
        //$("#product[outer_box_quantity]-error").shouldHave(Condition.text("This is a required field."));
        //$("#product[length_outer_box]-error").shouldHave(Condition.text("This is a required field."));
        //$("#product[height_outer_box]-error").shouldHave(Condition.text("This is a required field."));
        //$("#product[width_outer_box]-error").shouldHave(Condition.text("This is a required field."));
        //$("#product[weight_outer_box]-error").shouldHave(Condition.text("This is a required field."));
        //$("#product[volume_outer_box]-error").shouldHave(Condition.text("This is a required field."));

        // Заполнение обязательных полей на шаге 4
        $("#product_number_item_article").setValue("40");
        $("#inner_box").setValue("41");
        $("#inner_box_length").setValue("42");
        $("#inner_box_height").setValue("43");
        $("#inner_box_width").setValue("44");
        $("#inner_box_weight").setValue("45");
        $("#inner_box_volume").setValue("46");
        $(By.name("product[outer_box_quantity]")).setValue("47");
        $(By.name("product[length_outer_box]")).setValue("48");
        $(By.name("product[height_outer_box]")).setValue("49");
        $(By.name("product[width_outer_box]")).setValue("50");
        $(By.name("product[weight_outer_box]")).setValue("51");
        $(By.name("product[volume_outer_box]")).setValue("52");
        productPage.clickButtonNext();

        // Проверка нахождения на шаге 5
        $("#steps-uid-1-p-4>div.steps-form--name").shouldHave(Condition.text("Manufacturing info"));
        // Нажатие на кнопку перехода на следующий шаг без заполнения обязательных полей
        productPage.clickButtonNext();
        // Проверка наличия валидации обязательных полей
        //$("#product[supplier_number]-error").shouldHave(Condition.text("This is a required field."));
        $("#product_lead_time-error").shouldHave(Condition.text("This is a required field."));
        //$("#product[moq]-error").shouldHave(Condition.text("This is a required field."));
        //$("#product[htc]-error").shouldHave(Condition.text("This is a required field."));

        // Заполнение обязательных полей на шаге 5
        $(By.name("product[supplier_number]")).setValue("500");
        $("#production_coo").selectOptionByValue("BD");
        $("#product_lead_time").setValue("10");
        $(By.name("product[moq]")).setValue("505");
        $(By.name("product[htc]")).setValue("510");
        productPage.clickButtonNext();

        // Проверка нахождения на шаге 6
        $("#steps-uid-1-p-5>div.steps-form--name").shouldHave(Condition.text("Pricing"));
        // Нажатие на кнопку перехода на следующий шаг без заполнения обязательных полей
        productPage.clickButtonNext();
        // Проверка наличия валидации обязательных полей
        //$("#product[rrp][]-error").shouldHave(Condition.text("This is a required field."));
        //$("#product[ddp]-error").shouldHave(Condition.text("This is a required field."));
        //$("#product[fob]-error").shouldHave(Condition.text("This is a required field."));

        // Заполнение обязательных полей на шаге 6
        $(By.name("product[rrp][]")).setValue("600");
        $(By.name("product[ddp]")).setValue("610");
        $(By.name("product[fob]")).setValue("620");

        productPage.clickButtonNext();

        // Проверка нахождения на шаге 7
        $("#steps-uid-1-p-6>div.steps-form--name").shouldHave(Condition.text("Product images"));

        $("#file-1").uploadFile(new File("C:\\Test\\test.jpg"));
        productPage.clickButtonFinish();

        //Переход в раздел Products и проверка наличия добавленного продукта
        productPage.clickByLinkProducts();
        productPage.checkAddNewProduct(ProdName);

        //Выход из аккаунта
        authPage.logOut();

        System.out.println("Тест checkRequiredFieldsAddProductForm начат");
    }

    @AfterTest
    public static void endSession() {
        closeWebDriver();
    }
}
