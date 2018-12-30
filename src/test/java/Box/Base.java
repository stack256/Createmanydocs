package Box;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.*;

import static Box.Objects.*;
import static Box.About.*;

class Base {

    @Step("Авторизоваться пользователем {0}")
    static void auth(String report, String login, String pass) {
        settext("Имя пользователя", AuthPage.USERNAME, login);
        settext("Пароль", AuthPage.PASSWORD, pass);
        String currenturl = driver.getCurrentUrl();
        int count = 100;
        click("Войти", AuthPage.LOGIN);
        while ((currenturl.equals(driver.getCurrentUrl())) && (count >= 0)) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count--;
        }
    }

    @Step("Заполнить атрибут {0} значением {2}")
    private static void settext(String report, String xpath, String text) {
        waitForLoad(driver);
        try {
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception e) {
            softassertfail("Не найден элемент " + xpath);
        }
        driver.findElement(By.xpath(xpath)).sendKeys(text);
        waitForLoad(driver);
    }


    @Step("Нажать кнопку {0}")
    private static void click(String report, String xpath) {
        waitForLoad(driver);
        try {
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception e) {
            softassertfail("Не найден элемент " + xpath);
        }
        driver.findElement(By.xpath(xpath)).click();
        waitForLoad(driver);
    }
}
