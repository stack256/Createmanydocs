package Box;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.events.*;
import ru.yandex.qatools.allure.experimental.LifecycleListener;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static Box.Base.*;
import static Box.Users.*;

class About {

    @BeforeMethod
    public void setUp() {
        current_login = null;
        doc = new HashMap<String, String[]>();
        items = new HashMap<String, HashMap<String, String[]>>();
        item = new HashMap<String, String[]>();
        errands = new HashMap<String, HashMap<String, String[]>>();
        errand = new HashMap<String, String[]>();
        usersinitial();
        timeoutlnseconds = 10;
        Allure.LIFECYCLE.addListener(About.AllureStepListener.getInstance());
        stack = new ArrayList<About.Stack>();
        stack.add(new About.Stack());
        removedoc = new ArrayList<>();
        if (System.getProperty("remote.grid") != null) {
            try {
                driver = new RemoteWebDriver(new URL(System.getProperty("remote.grid")), new ChromeOptions());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        String baseUrl = System.getProperty("stend.url");
        driver.get(baseUrl);
    }

    @AfterMethod
    public void tiredDown() {
        doc.clear();
        items.clear();
        item.clear();
        errands.clear();
        errand.clear();
        stack.clear();
        removedoc.clear();
        users.clear();
        driver.quit();
    }

    public class Retry implements IRetryAnalyzer {

        private int count = 0;
        private int maxTry = 3;

        @Override
        public boolean retry(ITestResult iTestResult) {
            if (!iTestResult.isSuccess()) {                      //Check if test not succeed
                if (count < maxTry) {                            //Check if maxtry count is reached
                    count++;                                     //Increase the maxTry count by 1
                    iTestResult.setStatus(ITestResult.FAILURE);  //Mark test as failed
                    return true;                                 //Tells TestNG to re-run the test
                } else {
                    iTestResult.setStatus(ITestResult.FAILURE);  //If maxCount reached,test marked as failed
                }
            } else {
                iTestResult.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
            }
            return false;
        }

    }



    @Step("Поиск письма на почте {0}")
    static void ReadEmail(String email, String pass, String message){
        String currenturl = driver.getCurrentUrl();
        driver.get("http://mail.alf.datateh.ru/#/mailbox/INBOX");
        boolean t = false;
        int i = 1;
        timeoutlnseconds = 5;
        settext("Почта","//input[@name='RainLoopEmail']",email);
        settext("Пароль","//input[@name='RainLoopPassword']",pass);
        click("Войти","//button[@type='submit' and contains(@class,'submit')]");
        driver.get("http://mail.alf.datateh.ru/#/mailbox/INBOX");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!t && i <= 10){
            click("Письмо" + i,"//div[contains(@class,'messageListItem')][" + i + "]");
            t = waitelement("//div[contains(@id,'mgs')]//div[contains(.,'" + message + "')]", false) || waitelement("//td[contains(.,'" + message + "')]", false);
            i++;
        }
        saveAllureText(message);
        softassertfail(t,"Письмо не найдено");
        driver.get(currenturl);
        timeoutlnseconds = 10;
    }



    static RemoteWebDriver driver = null;
    static Integer timeoutlnseconds;
    static HashMap<String, String[]> doc;
    static HashMap<String, HashMap<String, String[]>> approval;
    static HashMap<String, String[]> approvalitem;
    static HashMap<String, HashMap<String, String[]>> items;
    static HashMap<String, String[]> item;
    static HashMap<String, HashMap<String, String[]>> errands;
    static HashMap<String, String[]> errand;
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
            //stack1.clear();
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
    private static String saveAllureText(LogEntries logEntries) {
        return logEntries.toString();
    }

    static void report(String report) {
        saveAllureScreenshot();
        saveAllureText(report);
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        saveAllureText(logEntries);
        //for (LogEntry entry : logEntries)
        //    System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
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
        System.out.println("Есть неблокирующая ошибка");
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
        System.out.println("Есть блокирующая ошибка");
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

    static String sd_reporter_tableadd(String selectorValue) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String dynamicXPath = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[@class='form-fields']/div[(contains(@id,'employee') or contains(@id,'representative')) and not(contains(@class,'hidden'))]//td[contains(.,'%s')]/ancestor::tr//a[@title='Добавить']";
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

    static String righblocktitle(String selectorValue) {
        String dynamicXPath = "//div[contains(@class,'widget')]//h2[contains(.,'%s')]";
        return String.format(dynamicXPath, selectorValue);
    }

    static String righblockopen(String selectorValue) {
        String dynamicXPath = "//div[contains(@class,'widget')]//h2[contains(.,'%s')]//*[contains(@title,'Развернуть')]";
        return String.format(dynamicXPath, selectorValue);
    }

    static String righaction(String selectorValue) {
        String dynamicXPath = "//div[contains(@id,'right-part')]//*[contains(text(),'Действия')]//ancestor::div[contains(@class,'widget')]//*[text()='%s']";
        return String.format(dynamicXPath, selectorValue);
    }

    static String approveaction(String selectorValue) {
        String dynamicXPath = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'%s')]";
        return String.format(dynamicXPath, selectorValue);
    }

    static String currentdate() {
        Date currentdate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(currentdate);
    }

    static String currentdate(int i) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, i);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(cal.getTime());
    }

    static String incoming_header(String[] type, String[] regnum) {
        return type[0] + ", № " + regnum[0] + " от " + currentdate();
    }

    public static String incoming_header(String[] type, String[] regnum, String[] date) {
        return type[0] + ", № " + regnum[0] + " от " + date[0];
    }

    static String historystandartcreate(Map<String, String[]> doc){
        return current_user + " создал(а) новый документ \"" + incoming_header(doc.get("Вид документа"), doc.get("Номер")) + "\" в статусе \"" + doc.get("Статус")[0] + "\"";
    }

    static String historystandartchangestatus(Map<String, String[]> doc){
        if (doc.get("Статус")[0].equals("Проект"))
            return current_user + " перевел(а) документ \"" + incoming_header(doc.get("Вид документа"), new String[]{"Не присвоено"}) + "\" в статус \"" + doc.get("Статус")[0] + "\"";
        else
            return current_user + " перевел(а) документ \"" + incoming_header(doc.get("Вид документа"), doc.get("Номер")) + "\" в статус \"" + doc.get("Статус")[0] + "\"";
    }

    private static String errand_header(String[] title, String[] regnum, String[] executor, String[] limitdate) {
        for (User user:users)
            if (user.full.equals(executor[0]))
                executor[0] = user.famio;
        return "№ " + regnum[0] + ", " + title[0] + ", Исполнитель " + executor[0] + ", срок: " + limitdate[0];
    }

    static String historystandartcreateerrand(Map<String, String[]> doc){
        return current_user + " создал(а) новый документ \"" + errand_header(doc.get("Заголовок"), doc.get("Номер"), doc.get("Исполнитель"), doc.get("Срок исполнения")) + "\" в статусе \"" + doc.get("Статус")[0] + "\"";
    }

    private static String resolutions_header(String[] regnum, String[] date, String[] limitdate) {
        if (date == null)
            return "№ " + regnum[0] + " от , срок: " + limitdate[0];
        else
            return "№ " + regnum[0] + " от " + date[0] + ", срок: " + limitdate[0];
    }

    static String historystandartcreateresolutions(Map<String, String[]> doc){
        if (doc.get("Статус")[0].contains("Черновик"))
            return current_user + " создал(а) новый документ \"" + resolutions_header(doc.get("Номер"), doc.get("Дата"), doc.get("Срок исполнения")) + "\" в статусе \"" + doc.get("Статус")[0] + "\"";
        else
            return current_user + " создал(а) новый документ \"" + resolutions_header(doc.get("Номер_old"), doc.get("Дата"), doc.get("Срок исполнения_old")) + "\" в статусе \"" + doc.get("Статус")[0] + "\"";
    }

    private static String protocol_header(String[] type, String[] regnum, String[] date) {

        return type[0] + ", № " + regnum[0] + " от " + date[0];
    }

    static String historystandartcreateprotocol(Map<String, String[]> doc){
        return current_user + " создал(а) новый документ \"" + protocol_header(doc.get("Вид документа"), doc.get("Номер"), doc.get("Дата")) + "\" в статусе \"" + doc.get("Статус")[0] + "\"";
    }
}
