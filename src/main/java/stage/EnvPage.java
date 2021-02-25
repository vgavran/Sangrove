package stage;

import static com.codeborne.selenide.Selenide.open;

public class EnvPage {
    public static void openStageUrl (){
        // Открытие тестового хоста с Basic Auth
        open("https://testnode.sangrove.com/","","vptdevusr","ztcLgci3wVJWkpaj");
    }
}
