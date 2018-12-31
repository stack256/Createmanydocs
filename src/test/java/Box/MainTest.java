package Box;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.model.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static Box.Base.*;
import static Box.About.*;
import static Box.Users.*;

public class MainTest {

    @BeforeMethod
    public void setUp() {
        current_login = null;
        doc = new HashMap<String, String[]>();
        usersinitial();
        timeoutlnseconds = 30;
        Allure.LIFECYCLE.addListener(About.AllureStepListener.getInstance());
        stack = new ArrayList<About.Stack>();
        removedoc = new ArrayList<>();
        stack.add(new About.Stack());
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        String baseUrl = System.getProperty("stend.url");
        driver.get(baseUrl);
    }

    @AfterMethod
    public void tiredDown() {
        doc.clear();
        stack.clear();
        removedoc.clear();
        users.clear();
        driver.quit();
    }


    @Description("Какое то описание")
    @Severity(SeverityLevel.CRITICAL)
    @Features("Входящий")
    @Stories("Жизненный цикл")
    @Title("Создать входящий документ")
    @Test
    public void test1() {
        User user = getuserbyroles("СЭД. Регистраторы");

        doc.put("Заголовок", new String[]{"Заголовок"});
        doc.put("Вид документа", new String[]{"Запрос"});
        doc.put("Способ доставки", new String[]{"Личный прием"});
        doc.put("Корреспондент", new String[]{"AT_Organization"});
        doc.put("Корреспондент Тип", new String[]{"Внутренний контрагент"});
        doc.put("Корреспондент Наименование", new String[]{"AT_Organization"});
        doc.put("Представитель корреспондента", new String[]{"Денисов Руслан Всеволодович Тестировщик AT_Subdivision2"});
        doc.put("Получатель", new String[]{"Сотрудник","John D. John D. jdd Еще один дегустатор Название организации"});
        //doc.put("В ответ на", new String[]{"Исходящий документ: А1 ЭП только Прочее, № ИСХ-01035/17 от 24.10.2017"});
        //doc.put("В ответ на Номер", new String[]{"1035"});
        doc.put("Исходящий номер", new String[]{"Outgoing-number"});
        doc.put("Исходящий от", new String[]{"21.12.2019"});
        doc.put("Содержание", new String[]{"21.12.2019"});
        doc.put("Количество листов", new String[]{"21"});
        doc.put("Тематика", new String[]{"Доставка воды"});
        doc.put("Номер дела", new String[]{"2666","123","прпу-Это дело"});
        doc.put("Примечание", new String[]{"21"});
        doc.put("Срок исполнения", new String[]{"21.12.2019"});
        doc.put("На контроле", new String[]{"Да"});
        doc.put("Нерегистрируемый", new String[]{"Да"});

        //авторизоваться
        auth(user.famio,user.login,user.pass);
        //создать входящий документ
        createincoming(doc);
        readincoming(doc);
        readhistory(doc.get("Запись в бж"),doc);
        //if (!stack.get(0).value)
        removedocs();
    }


    @Description("Какое то описание")
    @Severity(SeverityLevel.CRITICAL)
    @Features("Внутренний")
    @Stories("Жизненный цикл")
    @Title("Создать внутренний документ")
    @Test
    public void test2() {
        User user = getuserbyroles("Внутренние. Создатели");

        doc.put("Составитель", new String[]{user.full});
        doc.put("Исполнитель", new String[]{user.full});
        doc.put("Заголовок", new String[]{"Заголовок"});
        doc.put("Вид документа", new String[]{"Аналитическая записка"});
        doc.put("Срок ответа", new String[]{"21.12.2019"});
        doc.put("Получатель", new String[]{"Сотрудник",getuserbylogin("jd5").full});
        doc.put("Содержание", new String[]{"21.12.2019"});
        doc.put("Содержание", new String[]{"21.12.2019"});
        doc.put("Подписано на бумажном носителе", new String[]{"Да"});
        doc.put("Подписанты", new String[]{getuserbylogin("jd3").full, getuserbylogin("jd4").full});
        doc.put("Дата подписания", new String[]{"21.12.2019"});
        //doc.put("В ответ на", new String[]{"Внутренний документ: 1234567890, № NA-00094 от 28.09.2018"});
        //doc.put("В ответ на Номер", new String[]{"00094"});
        doc.put("Количество листов", new String[]{"21"});
        doc.put("Тематика", new String[]{"Доставка воды"});
        doc.put("Номер дела", new String[]{"2666","123","прпу-Это дело"});
        doc.put("Примечание", new String[]{"21"});

        //авторизоваться
        auth(user.famio,user.login,user.pass);
        //создать входящий документ
        createinternal(doc);
        readinternal(doc);
        readhistory(doc.get("Запись в бж"),doc);
        //if (!stack.get(0).value)
        removedocs();
    }


    @Description("Какое то описание")
    @Severity(SeverityLevel.CRITICAL)
    @Features("Исходящий")
    @Stories("Жизненный цикл")
    @Title("Создать исходящий документ")
    @Test
    public void test3() {
        User user = getuserbyroles("Исходящие. Создатели");

        doc.put("Составитель", new String[]{user.full});
        doc.put("Исполнитель", new String[]{user.full});
        doc.put("Заголовок", new String[]{"Заголовок"});
        doc.put("Вид документа", new String[]{"Информационное письмо"});
        doc.put("Способ доставки", new String[]{"Личный прием"});
        doc.put("Корреспондент", new String[]{"AT_Organization"});
        doc.put("Корреспондент Тип", new String[]{"Внутренний контрагент"});
        doc.put("Корреспондент Наименование", new String[]{"AT_Organization"});
        doc.put("Адресат корреспондента", new String[]{"Денисов Руслан Всеволодович Тестировщик AT_Subdivision2"});
        doc.put("Содержание", new String[]{"21.12.2019"});
        doc.put("Подписано на бумажном носителе", new String[]{"Да"});
        doc.put("Подписанты", new String[]{getuserbylogin("jd3").full, getuserbylogin("jd4").full});
        doc.put("Дата подписания", new String[]{"21.12.2019"});
        //doc.put("В ответ на", new String[]{"Внутренний документ: 1234567890, № NA-00094 от 28.09.2019"});
        //doc.put("В ответ на Номер", new String[]{"00094"});
        doc.put("Количество листов", new String[]{"21"});
        doc.put("Тематика", new String[]{"Доставка воды"});
        doc.put("Номер дела", new String[]{"2666","123","прпу-Это дело"});
        doc.put("Примечание", new String[]{"21"});
        doc.put("Завершающий", new String[]{"Нет"});

        //авторизоваться
        auth(user.famio,user.login,user.pass);
        //создать входящий документ
        createoutgoing(doc);
        readoutgoing(doc);
        readhistory(doc.get("Запись в бж"),doc);
        //if (!stack.get(0).value)
        removedocs();
    }
}