package Box;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.model.*;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static Box.Base.*;
import static Box.About.*;

public class MainTest {

    @BeforeMethod
    public void setUp() {
        timeoutlnseconds = 30;
        Allure.LIFECYCLE.addListener(About.AllureStepListener.getInstance());
        stack = new ArrayList<About.Stack>();
        stack.add(new About.Stack());
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        String baseUrl = System.getProperty("stend.url");
        driver.get(baseUrl);
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
        doc.put("Заголовок", new String[]{"Заголовок"});
        auth("jd1","jd1","123");
        createincoming(doc);
    }
}