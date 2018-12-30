package Box;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.Title;

import static Box.About.*;

@Epic("Это какой то эпик")

public class MainTest {

    static WebDriver driver;
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


    @Description("Some detailed test description")
    @Severity(SeverityLevel.CRITICAL)
    @Title("Имя теста")
    @Feature("Имя фичи")
    @Story("Имя истории")
    @Test
    public void test1() {
        driver.get(baseUrl);
        String expectedTitle = "Welcome: Me1rcury Tours";
        String actualTitle = driver.getTitle();
        softassertfail(actualTitle.equals(expectedTitle),"Не эквивалентны");
        //Assert.assertEquals(actualTitle, expectedTitle);
    }
}