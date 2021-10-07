package by.broker.http.runner;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalRunner {
    public static void main(String[] args) {
        Locale locale=new Locale("ru","RU");
        System.out.println(Locale.US);
        System.out.println(Locale.getDefault());

        ResourceBundle translations = ResourceBundle.getBundle("translations_ru_RU");
        System.out.println(translations.getString("menu.main"));
    }
}
