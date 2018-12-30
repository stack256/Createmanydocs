import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.Title;

@Title("Имя класса")
@Feature("Имя фичи")
@Story("Имя истории")
public class MainTest {

    WebDriver driver;
    String baseUrl;

    @BeforeTest(description = "Configure something before test")
    @Title("Действия до теста")
    @Description("Действия до теста")
    public void setUp() {
        driver = new ChromeDriver();
        baseUrl = "http://demo.guru99.com/test/newtours/";
    }

    @AfterTest(description = "Configure something after test")
    @Title("Действия после теста")
    @Description("Действия после теста")
    public void tiredDown() {

        driver.close();
    }


    @Test(testName = "Имя теста")
    @Description("Some detailed test description")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Base support for bdd annotations")
    @Title("Имя теста")
    public void test1() {
        driver.get(baseUrl);
        String expectedTitle = "Welcome: Mercury Tours";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
}