package Box;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.model.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static Box.About.*;

public class MainTest {

    static WebDriver driver;
    private String baseUrl;


    @BeforeMethod
    public void setUp() {
        Allure.LIFECYCLE.addListener(AllureStepListener.getInstance());
        stack = new ArrayList<Stack>();
        stack.add(new Stack());
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        baseUrl = "http://demo.guru99.com/test/newtours/";
    }

    @AfterMethod
    public void tiredDown() {
        driver.close();
    }

    @Description("Какое то описание")
    @Severity(SeverityLevel.CRITICAL)
    @Title("Имя теста")
    @Features("Имя фичи")
    @Stories("Имя истории")
    @Test
    public void test1() {
        driver.get(baseUrl);
        String expectedTitle = "Welcome: Mercury Tours";
        String actualTitle = driver.getTitle();
        softassertfail(expectedTitle, actualTitle);
    }
}