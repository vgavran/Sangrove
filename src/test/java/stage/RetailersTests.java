package stage;

import com.codeborne.selenide.Condition;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.RandomUtils;

import java.io.File;
import java.net.MalformedURLException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class RetailersTests {
    public final AuthPage authPage = new AuthPage();
    public final RetailerPage retailersPage = new RetailerPage();
    public final EventPage eventPage = new EventPage();
    public final ProductPage productPage = new ProductPage();
    public final BasketPage basketPage = new BasketPage();

    @BeforeTest
    public static void setupClass () throws MalformedURLException {
        SetupPage.setupClass();
    }

    /******************************************************************/
    /*Тест загрузки ритейлеров из файла*/
    /******************************************************************/
    @Test
    public void addNewRetailersFromFile() {

        System.out.println("Тест addNewRetailersFromFile начат");

        //Открываем url приложения
        EnvPage.openStageUrl();
        //Авторизация пользователя
        authPage.authOnSite();

        // Переход в раздел ритейлеров
        retailersPage.clickByLinkRetailers();

        //productPage.addProductFromFile();
        $("#import-file").uploadFile(new File("C:\\Test\\ProductDM21.xlsx"));

        //Выход из аккаунта
        authPage.logOut();

        System.out.println("Тест addNewRetailersFromFile завершен");
    }

    @Test
    public void buyWithOldRetailers() {
        System.out.println("Тест buyWithOldRetailers начат");
        EnvPage.openStageUrl();
        //Авторизация под существующим ритейлером
        authPage.authOnSiteWithLoginPass("vgavran@gmail.com", "123456");
        //Переходим в раздел Events
        eventPage.clickByLinkEvent();
        //Проверка что есть активный ивент
        eventPage.checkEventIsActive();
        //Добавление в корзину продукта
        productPage.addProductToBasket();

        //Переход в корзину и оформление заказа
        basketPage.goToBasketAndSubmitOrder();
        basketPage.checkOrderIsCreated();

        //Выход их аккаунта
        authPage.logOut();
        System.out.println("Тест buyWithOldRetailers пройден");
    }

    @AfterTest
    public static void endSession() {
        closeWebDriver();
    }

}
