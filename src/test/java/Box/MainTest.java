package Box;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static Box.About.*;

@Title("Это класс")
public class MainTest {

    static WebDriver driver;
    private String baseUrl;


    @BeforeTest
    public void setUp() {
        Allure.LIFECYCLE.addListener(AllureStepListener.getInstance());
        stack = new ArrayList<Stack>();
        stack.add(new Stack());
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        baseUrl = "http://demo.guru99.com/test/newtours/";
    }

    @AfterTest
    public void tiredDown() {
        driver.close();
    }


    /*@Description("Some detailed test description")
    @Severity(SeverityLevel.CRITICAL)
    @Title("Имя теста")
    @Feature("Имя фичи")
    @Story("Имя истории")
    @Test*/
    @Title("3.2\tСоздание черновика документа")
    @Test
    public void test1() {
        driver.get(baseUrl);
        String expectedTitle = "Welcome: Me1rcury Tours";
        String actualTitle = driver.getTitle();
        softassertfail(actualTitle.equals(expectedTitle),"Не эквивалентны");
        //Assert.assertEquals(actualTitle, expectedTitle);
    }
}