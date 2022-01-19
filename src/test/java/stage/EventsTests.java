package stage;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.RandomUtils;

import java.io.File;
import java.net.MalformedURLException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class EventsTests {

    public final AuthPage authPage = new AuthPage();
    public final AdminPage adminPage = new AdminPage();
    public final ProductPage productPage = new ProductPage();
    public final EventPage eventPage = new EventPage();

    @BeforeTest
    public static void setupClass () throws MalformedURLException {
        SetupPage.setupClass();
    }

    /******************************************************************/
    /*Тест покупки существующим ритейлером*/
    /******************************************************************/
    @Test
    public void buyWithOldRetailers() {
        System.out.println("Тест buyWithOldRetailers начат");
        EnvPage.openStageUrl();
        //Авторизация под существующим ритейлером
        authPage.authOnSiteWithLoginPass("vgavran@gmail.com", "123456");
        //Переходим в раздел Events
        eventPage.clickByLinkEvent();

        //проверка что ивент активен #remainingEvent text "min"
        $(By.xpath("//p[@class='ends_in']")).exists();
        $(By.xpath("//button[@type='submit'][normalize-space()='Buy']")).click();

        //Переход в корзину и оформление заказа
        $(".icon-basket").click();
        $(By.xpath("//a[normalize-space()='Place order']")).click();
        $(By.xpath("//div[@class='payment-method--item nodisable']")).click();
        $(By.id("submit_order")).click(); //all
        $(By.xpath("//a[normalize-space()='Next']")).click();

        //Нажимаем на странице Make a Payment кнопку подтверждения
        $(By.xpath("//a[normalize-space()='Next']")).click();
        if ($("#account_region_id-error").isDisplayed()) {
            $("#account_region_id").selectOptionByValue("19");
            $(By.xpath("//a[normalize-space()='Next']")).click();
        }
        $(By.xpath("//a[normalize-space()='Submit']")).click();
        $(By.xpath("//img[@alt='Loading...']")).should(disappear);
        $(By.xpath("//p[@class='popup-title']")).isDisplayed();
        $(By.xpath("//p[contains(text(),'Your order number')]")).shouldBe();
        $(By.xpath("//a[normalize-space()='OK']")).click();
        $(By.xpath("//p[normalize-space()='Order details']")).shouldBe();

        //Выход их аккаунта
        authPage.logOut();
        System.out.println("Тест buyWithOldRetailers пройден");
    }

    /******************************************************************/
    /*Тест создания нового ивента*/
    /******************************************************************/
    @Test
    public void startNewEvent(){
        System.out.println("Тест startNewEvent начат");
        EnvPage.openStageUrl();
        authPage.authOnSite();
        eventPage.clickByLinkEvent();

        $(By.xpath("//div[@id='maincontent']//p[contains(text(),'There are no active Events at this time')]")).shouldBe();
        productPage.clickByLinkProducts();

        //Выбрать все товары и нажать кнопку запуска ивента
        $(By.xpath("//label[normalize-space()='All']")).click();
        $(By.id("mass_create_event")).waitUntil(visible, 5000).click();
        $(By.xpath("//p[contains(text(),'Сreate Event')]")).shouldBe();

        //Настройка параметров ивента
        String nameEvent = "Test Event " + RandomUtils.getRandomLetters();
        $(By.name("name")).waitUntil(visible, 5000).setValue(nameEvent);
        $(By.id("begin_date")).waitUntil(visible, 5000).click();
        $(By.id("begin_date")).pressEnter();
        $(By.id("period_date_end")).pressEscape();

        $("#retailer_select_country").selectOptionContainingText("All");
        $(By.id("file-banner")).uploadFile(new File("C:\\Test\\bannerEv.jpg"));
        $(By.xpath("//input[@value='Preview event']")).scrollTo().click();
        $(By.xpath("//img[@alt='Loading...']")).shouldBe(disappear);
        Selenide.sleep(1000);
        $(By.xpath("//p[@class='popup-title']")).waitUntil(visible, 5000).shouldHave(text(nameEvent));
        Selenide.sleep(1000);
        $(By.xpath("//button[normalize-space()='Launch event']")).waitUntil(visible, 5000).click();
        $(By.xpath("//p[contains(text(),'event will be launched')]")).shouldBe();
        $(By.xpath("//a[normalize-space()='Ok']")).waitUntil(visible, 5000).click();
        Selenide.sleep(5000);
        System.out.println("Тест startNewEvent пройден");
    }

    /******************************************************************/
    /*Тест архивации ивента*/
    /******************************************************************/
    @Test
    public void archiveEvent() {
        System.out.println("Тест archiveEvent начат");

        EnvPage.openAdminStageUrl();
        //Авторизация под пользователем с админправами
        adminPage.loginAsAdmin();
        //Переход в раздел Events и архивация
        adminPage.archiveEvents();

        System.out.println("Тест archiveEvent пройден");
    }

    @AfterTest
    public static void endSession() {
        closeWebDriver();
    }

}
