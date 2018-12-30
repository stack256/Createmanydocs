package Box;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.events.*;
import ru.yandex.qatools.allure.experimental.LifecycleListener;

import static Box.MainTest.*;

import java.util.ArrayList;

class About {
    static class Stack {
        Integer number;
        Boolean value;

        Stack(){
            number = 0;
            value = true;
        }
    }
    private static ArrayList<Stack> stack;

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
            System.out.println(stack.toString());
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

    static void report(String report) {
        saveAllureScreenshot();
        saveAllureText(report);
    }

    static void softassertfail(Boolean value, String report) {
        if (!value)
            softassertfail(report);
    }

    @Step("{0}")
    static void softassertfail(String report) {
        Allure.LIFECYCLE.fire(new StepFailureEvent());
        Allure.LIFECYCLE.fire(new TestCaseFailureEvent().withThrowable(new RuntimeException("Есть неблокирующие ошибки")));
        Stack buf = stack.get(stack.size()-1);
        buf.value = false;
        stack.set(stack.size()-1,buf);
        report(report);
    }


    static void hardassertfail(Boolean value, String report) {
        if (!value)
            hardassertfail(report);
    }

    @Step("{0}")
    static void hardassertfail(String report) {
        report(report);
        Assert.fail(report);
    }
}
