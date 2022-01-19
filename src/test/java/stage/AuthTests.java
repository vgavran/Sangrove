package stage;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class AuthTests {
    public final AuthPage authPage = new AuthPage();

    @BeforeTest
    public static void setupClass () throws MalformedURLException {
        SetupPage.setupClass();
    }

    /******************************************************************/
    /*Тест успешной авторизации*/
    /******************************************************************/
    @Test
    public void sucсessAuth() {

        System.out.println("Тест sucсessAuth начат");

        //Открытие url приложения
        EnvPage.openStageUrl();
        //Авторизация пользователя
        authPage.authOnSite();
        //Проверка отображения меню профиля
        authPage.isProfileDisplayed();
        authPage.logOut();

        System.out.println("Тест sucсessAuth завершен");
    }

    /******************************************************************/
    /*Тест неуспешной авторизации (некорректный email)*/
    /******************************************************************/
    @Test
    public void checkValidationEmailAuth() {

        System.out.println("Тест checkValidationEmailAuth начат");

        //Открытие url приложения
        EnvPage.openStageUrl();
        //Попытка авторизации с некорректным email
        authPage.checkValidationEmailMask();
        Selenide.sleep(1000);

        System.out.println("Тест checkValidationEmailAuth завершен");
    }


    /******************************************************************/
    /*Тест неуспешной авторизации (неправильный пароль)*/
    /******************************************************************/
    @Test
    public void checkWrongPasswordAuth() {

        System.out.println("Тест checkWrongPasswordAuth начат");

        //Открытие url приложения
        EnvPage.openStageUrl();
        //Попытка авторизации с неправильным паролем
        authPage.checkAuthWrongPassword();
        Selenide.sleep(1000);

        System.out.println("Тест checkWrongPasswordAuth завершен");
    }

    /******************************************************************/
    /*Тест выхода из аккаунта*/
    /******************************************************************/
    @Test
    public void successLogOut() {

        System.out.println("Тест successLogOut начат");

        //Открытие url приложения
        EnvPage.openStageUrl();
        //Авторизация на сайте
        authPage.authOnSite();
        //Выход из аккаунта
        authPage.logOut();

        System.out.println("Тест successLogOut завершен");
    }

    @AfterTest
    public static void endSession() {
        closeWebDriver();
    }

}
