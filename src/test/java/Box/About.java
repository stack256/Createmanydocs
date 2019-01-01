package Box;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.events.*;
import ru.yandex.qatools.allure.experimental.LifecycleListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static Box.Users.*;

class About {

    static WebDriver driver;
    static Integer timeoutlnseconds;
    static HashMap<String, String[]> doc;
    static HashMap<String, HashMap<String, String[]>> items;
    static HashMap<String, String[]> item;
    static String current_user, current_login;
    static ArrayList<String> removedoc;

    static class Stack {
        Integer number;
        Boolean value;

        Stack(){
            number = 0;
            value = true;
        }
    }
    static ArrayList<Stack> stack;

    //лисенер для мягких ошибок
    public static class AllureStepListener extends LifecycleListener {
        private static AllureStepListener instance;
        static synchronized LifecycleListener getInstance() {
            if (instance == null) {
                instance = new AllureStepListener();
            }
            return instance;
        }

        @Override
        public void fire(StepStartedEvent event) {
            Stack buf;
            buf = new Stack();
            buf.number = stack.size();
            stack.add(buf);
            //если понадобится логгирование прям в консоль дженкинса
            //то разблокировать строку ниже
            //System.out.println(event.getTitle());
        }

        @Override
        public void fire(StepFinishedEvent event) {
            if (!stack.get(stack.size()-1).value){
                Stack buf = stack.get(stack.size()-2);
                buf.value = false;
                stack.set(stack.size()-2,buf);
                Allure.LIFECYCLE.fire(new StepFailureEvent());
                Allure.LIFECYCLE.fire(new TestCaseFailureEvent().withThrowable(new RuntimeException("Есть неблокирующие ошибки")));
            }
            stack.remove(stack.size()-1);
        }
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private static byte[] saveAllureScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Вложение", type = "text/application")
    private static String saveAllureText(String report) {
        return report;
    }

    @Attachment(value = "Логи", type = "text/application")
    private static LogEntries saveAllureText(LogEntries logEntries) {
        return logEntries;
    }

    static void report(String report) {
        saveAllureScreenshot();
        saveAllureText(report);
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        saveAllureText(logEntries);
        for (LogEntry entry : logEntries)
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
    }


    static void softassertfail(Boolean value, String report) {
        if (!value)
            softassertfail(report);
    }

    static void softassertfail(String expectation, String reality) {
        if (!expectation.equals(reality))
            softassertfail("Ожидаемое значение: " + expectation + ". Фактический результат: " + reality);
    }

    @Step("{0}")
    static void softassertfail(String report) {
        Allure.LIFECYCLE.fire(new StepFailureEvent());
        Allure.LIFECYCLE.fire(new TestCaseFailureEvent().withThrowable(new RuntimeException("Есть неблокирующие ошибки")));
        Stack buf = stack.get(stack.size() - 1);
        buf.value = false;
        stack.set(stack.size() - 1, buf);
        report(report);
    }


    static void hardassertfail(Boolean value, String report) {
        if (!value)
            hardassertfail(report);
    }

    static void hardassertfail(String expectation, String reality) {
        if (!expectation.equals(reality))
            hardassertfail("Ожидаемое значение: " + expectation + ". Фактический результат: " + reality);
    }

    @Step("{0}")
    static void hardassertfail(String report) {
        report(report);
        Assert.fail(report);
    }

    static void waitForLoad() {
        /*ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
*/
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriver jsWaitDriver;
        jsWaitDriver = driver;
        WebDriverWait wait = new WebDriverWait(jsWaitDriver,15);
        JavascriptExecutor jsExec = (JavascriptExecutor) jsWaitDriver;

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) jsWaitDriver)
                .executeScript("return document.readyState").toString().equals("complete");

        //Get JS is Ready
        boolean jsReady =  (Boolean) jsExec.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if(!jsReady)
            wait.until(jsLoad);

    }

    static String sd_simple_tableadd(String selectorValue) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String dynamicXPath = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//td[contains(.,'%s')]/ancestor::tr//a[@title='Добавить']";
        return String.format(dynamicXPath, selectorValue);
    }

    static String sd_sender_tableadd(String selectorValue) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String dynamicXPath = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[@class='form-fields']/div[(contains(@id,'contractor') or contains(@id,'organisation') or contains(@id,'person')) and not(contains(@class,'hidden'))]//td[contains(.,'%s')]/ancestor::tr//a[@title='Добавить']";
        return String.format(dynamicXPath, selectorValue);
    }

    static String sd_recipient_tableadd(String selectorValue) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String dynamicXPath = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[@class='form-fields']/div[(contains(@id,'employee') or contains(@id,'organization')) and not(contains(@class,'hidden'))]//td[contains(.,'%s')]/ancestor::tr//a[@title='Добавить']";
        return String.format(dynamicXPath, selectorValue);
    }

    static String sd_filergistertree_tableadd(String selectorValue) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String dynamicXPath = "(//div[contains(@class,'container') and contains (@style,'visibility: visible')]//td//*[text()='%s']//ancestor::tr//a[@href='#'])[1]";
        return String.format(dynamicXPath, selectorValue);
    }

    private static String incoming_header(String[] type, String[] regnum) {
        Date currentdate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return type[0] + ", № " + regnum[0] + " от " + sdf.format(currentdate);
    }

    public static String incoming_header(String[] type, String[] regnum, String[] date) {
        return type[0] + ", № " + regnum[0] + " от " + date[0];
    }

    static String historystandartcreate(Map<String, String[]> doc){
        return current_user + " создал(а) новый документ \"" + incoming_header(doc.get("Вид документа"), doc.get("Номер")) + "\" в статусе \"" + doc.get("Статус")[0] + "\"";
    }

    private static String incoming_headererrand(String[] title, String[] regnum, String[] executor, String[] limitdate) {
        for (User user:users)
            if (user.full.equals(executor[0]))
                executor[0] = user.famio;
        return "№ " + regnum[0] + ", " + title[0] + ", Исполнитель " + executor[0] + ", срок: " + limitdate[0];

    }

    static String historystandartcreateerrand(Map<String, String[]> doc){
        return current_user + " создал(а) новый документ \"" + incoming_headererrand(doc.get("Заголовок"), doc.get("Номер"), doc.get("Исполнитель"), doc.get("Срок исполнения")) + "\" в статусе \"" + doc.get("Статус")[0] + "\"";
    }
}
