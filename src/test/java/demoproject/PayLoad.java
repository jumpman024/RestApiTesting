package demoproject;

public class PayLoad {

    public static String addBook(String isbn, String aisle) {

        String str = "{\n" +
                "\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\",\n" +
                "\"author\":\"John foe\"\n" +
                "}";

        return str;
    }

    }


