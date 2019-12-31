package OAuthTest;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Api;
import pojo.Courses;
import pojo.GetCourse;
import pojo.WebAutomation;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static java.lang.Thread.sleep;

public class OAuthTest {

    WebDriver driver = new ChromeDriver();

    @Test
    public void testAuth() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Kostya\\chromedriver_win32\\chromedriver.exe");

    String[] courseTitles = {"Selenium Webdriver Java", "Cypress", "Protractor"};
//         Properties config = Config.loadProperties("env.properties");
//        System.setProperty("webdriver.chrome.driver", "chromedriver.version");
//        WebDriverManager.chromedriver().version(config.getProperty("chrome.version"));
        driver.get("https://accounts.google.com/signin/oauth/oauthchooseaccount?client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&as=WjFu46WfO2wR4osIsNl7kw&destination=https%3A%2F%2Frahulshettyacademy.com&approval_state=!ChQtQkNVakllVmtUTEM5RHlORTVvehIfNHlPZDliTmxBN29ZOERFdWhZOThQYzlsT2JweDlSWQ%E2%88%99AJDr988AAAAAXgtUesu6LPJrNwoxJTTHTucYWK6pUZtO&oauthgdpr=1&xsrfsig=ChkAeAh8TxNBUP1TE8RC-Oz51EVH9oo8JEzpEg5hcHByb3ZhbF9zdGF0ZRILZGVzdGluYXRpb24SBXNvYWN1Eg9vYXV0aHJpc2t5c2NvcGU&flowName=GeneralOAuthFlow");
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("kk1748774@gmail.com");
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
        sleep(3000);
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("Test12345!");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
        sleep(4000);
        String url = driver.getCurrentUrl();
        String parcialcode = url.split("code=")[1];
        String code = parcialcode.split("&scope")[0];
        System.out.println(code);

      String accessTokenResponse =  given().
                urlEncodingEnabled(false)
                .queryParams("code", code)
                .queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_url","https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type","authorization_code")
              .queryParams("state", "verifyfjdss")
              .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
              .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();
        JsonPath js = new JsonPath(accessTokenResponse);
        String accessToken = js.get("access_token");


        GetCourse gc = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);

        System.out.println(gc.getLinkedIn());
        System.out.println(gc.getInstructor());
        System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());

        List<Api> apiCourses = gc.getCourses().getApi();
        for(int i=0;i<apiCourses.size();i++)
        {
            if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
            {
                System.out.println(apiCourses.get(i).getPrice());
            }
        }

       List<Api> apiPrice = gc.getCourses().getApi();
        for(int i =0;i<apiPrice.size();i++){
            System.out.println(apiPrice.get(i).getCourseTitle()+" price: "+apiPrice.get(i).getPrice());
        }

        ArrayList<String> a = new ArrayList<String>();

        List<WebAutomation> automationTitle = gc.getCourses().getWebAutomation();
        for(int i=0;i<automationTitle.size();i++){

            a.add(automationTitle.get(i).getCourseTitle());
        }
        List<String> expectedList =  Arrays.asList(courseTitles);

        Assert.assertTrue(a.equals(expectedList));





        //System.out.println(response);

    }
}
