package Box;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
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
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static Box.Users.*;
import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

class About {



    private static HashMap<Long, RemoteWebDriver> drivermap = new HashMap<Long, RemoteWebDriver>();
    static HashMap<Long, ArrayList<String>> removedocmap = new HashMap<Long, ArrayList<String>>();
    private static HashMap<Long, ArrayList<Stack>> stackmap = new HashMap<Long, ArrayList<Stack>>();
    static HashMap<Long, String> current_usermap = new HashMap<Long, String>();
    static ArrayList<User> usersintests = new ArrayList<>();
    static HashMap<Long, String> current_loginmap = new HashMap<Long, String>();
    static HashMap<Long, Integer> timeoutlnsecondsmap = new HashMap<Long, Integer>();
    static Integer timeoutlnsecond = 60;

    @BeforeSuite
    void setAllureEnvironment() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Браузер", "Chrome")
                        .put("Стенд", System.getProperty("stend.url"))
                        .build());

        Allure.LIFECYCLE.addListener(About.AllureStepListener.getInstance());

        usersinitial();
    }

    @AfterSuite
    void closedrivers(){
        int i = 0;
        while (drivermap.size() > 0 && i < 6500) {
            if (drivermap.get(i) != null)
                drivermap.get(i).close();
            i++;
        }
    }

    @BeforeMethod
    public void setUp() {
        String current_login = null;
        current_loginmap.put(Thread.currentThread().getId(),current_login);


        timeoutlnsecondsmap.put(Thread.currentThread().getId(),timeoutlnsecond);

        ArrayList<Stack> stack = new ArrayList<Stack>();
        stack.add(new Stack());
        stackmap.put(Thread.currentThread().getId(),stack);

        ArrayList<String> removedoc = new ArrayList<>();
        removedocmap.put(Thread.currentThread().getId(),removedoc);

        RemoteWebDriver driver = null;
        boolean t = false;
        while (!t) {
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (System.getProperty("remote.grid") != null) {
                try {
                    driver = new RemoteWebDriver(new URL(System.getProperty("remote.grid")), new ChromeOptions());
                    t = true;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else {
                driver = new ChromeDriver();
                t = true;
            }
        }
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        try {
            Thread.sleep(10 * Thread.currentThread().getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.get(System.getProperty("stend.url"));
        drivermap.put(Thread.currentThread().getId(),driver);
    }

    int counttest = 9;

    @AfterMethod
    public void tiredDown() {
        //doc.clear();
        //items.clear();
        //item.clear();
        //errands.clear();
        //errand.clear();
        currentstack().clear();
        currentremovedoc().clear();
        //users.clear();
        currentdriver().quit();
        counttest--;
        System.out.println("Осталось тестов " + counttest);
    }

    static RemoteWebDriver currentdriver() {
        return drivermap.get(Thread.currentThread().getId());
    }

    static ArrayList<String> currentremovedoc() {
        return removedocmap.get(Thread.currentThread().getId());
    }

    private static ArrayList<Stack> currentstack() {
        return stackmap.get(Thread.currentThread().getId());
    }

    static String currentcurrent_user() {
        return current_usermap.get(Thread.currentThread().getId());
    }

    static String currentcurrent_login() {
        return current_loginmap.get(Thread.currentThread().getId());
    }

    static Integer currenttimeoutlnseconds() {
        return timeoutlnsecondsmap.get(Thread.currentThread().getId());
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

    static class Stack {
        Integer number;
        Boolean value;

        Stack(){
            number = 0;
            value = true;
        }
    }

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
            ArrayList<Stack> stackbuf = new ArrayList<>();
            stackbuf = currentstack();
            Stack buf;
            buf = new Stack();
            buf.number = stackbuf.size();
            stackbuf.add(buf);
            stackmap.put(Thread.currentThread().getId(),stackbuf);
            //если понадобится логгирование прям в консоль дженкинса
            //то разблокировать строку ниже
            //System.out.println(event.getTitle());
        }

        @Override
        public void fire(StepFinishedEvent event) {
            ArrayList<Stack> stackbuf = new ArrayList<>();
            if (!currentstack().get(currentstack().size()-1).value){
                stackbuf = currentstack();
                Stack buf = stackbuf.get(stackbuf.size()-2);
                buf.value = false;
                stackbuf.set(stackbuf.size()-2,buf);
                stackmap.put(Thread.currentThread().getId(),stackbuf);
                Allure.LIFECYCLE.fire(new StepFailureEvent().withThrowable(new AssertionError()));
                Allure.LIFECYCLE.fire(new TestCaseFailureEvent().withThrowable(new AssertionError("Есть неблокирующие ошибки")));
            }
            stackbuf = currentstack();
            stackbuf.remove(stackbuf.size()-1);
            stackmap.put(Thread.currentThread().getId(),stackbuf);
            //stack1.clear();
        }

    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private static byte[] saveAllureScreenshot() {
        return ((TakesScreenshot) currentdriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Вложение", type = "text/application")
    static String saveAllureText(String report) {
        return report;
    }

    @Attachment(value = "Логи", type = "text/application")
    private static String saveAllureText(LogEntries logEntries) {
        return logEntries.toString();
    }

    static void report(String report) {
        saveAllureScreenshot();
        saveAllureText(report);
        //LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        //saveAllureText(logEntries);
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
        Allure.LIFECYCLE.fire(new StepFailureEvent().withThrowable(new AssertionError()));
        Allure.LIFECYCLE.fire(new TestCaseFailureEvent().withThrowable(new AssertionError("Есть неблокирующие ошибки")));
        ArrayList<Stack> stackbuf = new ArrayList<>();
        stackbuf = currentstack();
        Stack buf = stackbuf.get(stackbuf.size() - 1);
        buf.value = false;
        stackbuf.set(stackbuf.size() - 1, buf);
        stackmap.put(Thread.currentThread().getId(),stackbuf);
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
        /*if (currentcurrent_login()!="admin")
            usersintests.remove(getuserbylogin(currentcurrent_login()));*/
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
        jsWaitDriver = currentdriver();
        WebDriverWait wait = new WebDriverWait(jsWaitDriver,currenttimeoutlnseconds());
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
        return currentcurrent_user() + " создал(а) новый документ \"" + incoming_header(doc.get("Вид документа"), doc.get("Номер")) + "\" в статусе \"" + doc.get("Статус")[0] + "\"";
    }

    static String historystandartchangestatus(Map<String, String[]> doc){
        if (doc.get("Статус")[0].equals("Проект"))
            return currentcurrent_user() + " перевел(а) документ \"" + incoming_header(doc.get("Вид документа"), new String[]{"Не присвоено"}) + "\" в статус \"" + doc.get("Статус")[0] + "\"";
        else
            return currentcurrent_user() + " перевел(а) документ \"" + incoming_header(doc.get("Вид документа"), doc.get("Номер")) + "\" в статус \"" + doc.get("Статус")[0] + "\"";
    }

    private static String errand_header(String[] title, String[] regnum, String[] executor, String[] limitdate) {
        for (User user:users)
            if (user.full.equals(executor[0]))
                executor[0] = user.famio;
        return "№ " + regnum[0] + ", " + title[0] + ", Исполнитель " + executor[0] + ", срок: " + limitdate[0];
    }

    static String historystandartcreateerrand(Map<String, String[]> doc){
        return currentcurrent_user() + " создал(а) новый документ \"" + errand_header(doc.get("Заголовок"), doc.get("Номер"), doc.get("Исполнитель"), doc.get("Срок исполнения")) + "\" в статусе \"" + doc.get("Статус")[0] + "\"";
    }

    private static String resolutions_header(String[] regnum, String[] date, String[] limitdate) {
        if (date == null)
            return "№ " + regnum[0] + " от , срок: " + limitdate[0];
        else
            return "№ " + regnum[0] + " от " + date[0] + ", срок: " + limitdate[0];
    }

    static String historystandartcreateresolutions(Map<String, String[]> doc){
        if (doc.get("Статус")[0].contains("Черновик"))
            return currentcurrent_user() + " создал(а) новый документ \"" + resolutions_header(doc.get("Номер"), doc.get("Дата"), doc.get("Срок исполнения")) + "\" в статусе \"" + doc.get("Статус")[0] + "\"";
        else
            return currentcurrent_user() + " создал(а) новый документ \"" + resolutions_header(doc.get("Номер_old"), doc.get("Дата"), doc.get("Срок исполнения_old")) + "\" в статусе \"" + doc.get("Статус")[0] + "\"";
    }

    private static String protocol_header(String[] type, String[] regnum, String[] date) {

        return type[0] + ", № " + regnum[0] + " от " + date[0];
    }

    static String historystandartcreateprotocol(Map<String, String[]> doc){
        return currentcurrent_user() + " создал(а) новый документ \"" + protocol_header(doc.get("Вид документа"), doc.get("Номер"), doc.get("Дата")) + "\" в статусе \"" + doc.get("Статус")[0] + "\"";
    }

    String randomstring(int length) {
        String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_";
        SecureRandom RANDOM = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }
}
