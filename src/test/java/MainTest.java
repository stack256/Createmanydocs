import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.Title;

@Title("Имя класса")
public class MainTest {

    WebDriver driver;
    String baseUrl;

    @BeforeTest(description = "Configure something before test")
    public void setUp() {
        driver = new ChromeDriver();
        baseUrl = "http://demo.guru99.com/test/newtours/";
    }

    @AfterTest(description = "Configure something after test")
    public void tiredDown() {
        driver = new ChromeDriver();
        baseUrl = "http://demo.guru99.com/test/newtours/";
    }


    @Test
    @Description("Some detailed test description")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Base support for bdd annotations")
    @Title("Имя теста")
    public void Test1() {
        driver.get(baseUrl);
        String expectedTitle = "Welcome: Mercury Tours";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);

        driver.close();
    }
}