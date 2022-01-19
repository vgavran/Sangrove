package stage;

import static com.codeborne.selenide.Selenide.open;

public class EnvPage {
    public static void openStageUrl () {
        // Открытие тестового хоста с Basic Auth https://testnode.sangrove.com/
        //open("http://lappartement.sangrove.com/");
        open("https://stage01.sangrove.com/","","vptnusr","nwP4hsNqqYT7bMCn4ixX");
    }

    public static void openAdminStageUrl() {
        open("https://admin.testnode.sangrove.com/admin_thn46i/","","vptnusr","nwP4hsNqqYT7bMCn4ixX");
    }

    public static void openStage01Url () {
        // Открытие тестового хоста Stage01
        open("https://stage01.sangrove.com/");
    }

    public static void openStage02Url () {
        // Открытие тестового хоста Stage02
        open("https://stage02.sangrove.com/");
    }

    public static void openGmail() {
        //Открытие Gmail
        open("https://gmail.com/");
    }
}
