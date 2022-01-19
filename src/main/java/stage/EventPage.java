package stage;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class EventPage {

    public void clickByLinkEvent() {
       // $(By.linkText("Events")).click();
        $(By.xpath("//a[normalize-space()='Event']")).click();
    }

    public void checkNoActiveEvents() {
        // $(By.linkText("Events")).click();
        $(By.xpath("//a[normalize-space()='Event']")).click();
    }

    public void checkEventIsActive() {
        $(By.xpath("//p[@class='ends_in']")).exists();
    }
}
