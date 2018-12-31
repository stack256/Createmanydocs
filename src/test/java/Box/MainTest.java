package Box;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.model.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static Box.Base.*;
import static Box.About.*;

public class MainTest {

    @BeforeMethod
    public void setUp() {
        timeoutlnseconds = 15;
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
        auth("Admin","admin","uVPmD7pd");
        for (String val:removedoc){
            driver.get(val);
            admindelete();
        }
        doc.clear();
        stack.clear();
        removedoc.clear();
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
        doc.put("Вид документа", new String[]{"Запрос"});
        doc.put("Способ доставки", new String[]{"Личный прием"});
        doc.put("Корреспондент", new String[]{"AT_Organization"});
        doc.put("Корреспондент Тип", new String[]{"Внутренний контрагент"});
        doc.put("Корреспондент Наименование", new String[]{"AT_Organization"});
        doc.put("Представитель корреспондента", new String[]{"Денисов Руслан Всеволодович Тестировщик AT_Subdivision2"});
        doc.put("Получатель", new String[]{"Сотрудник","John D. John D. jdd Еще один дегустатор Название организации"});
        doc.put("В ответ на", new String[]{"Исходящий документ: А1 ЭП только Прочее, № ИСХ-01035/17 от 24.10.2017"});
        doc.put("В ответ на Номер", new String[]{"1035"});
        doc.put("Исходящий номер", new String[]{"Outgoing-number"});
        doc.put("Исходящий от", new String[]{"21.12.2018"});
        doc.put("Содержание", new String[]{"21.12.2018"});
        doc.put("Количество листов", new String[]{"21"});
        doc.put("Тематика", new String[]{"Доставка воды"});
        doc.put("Номер дела", new String[]{"2666","123","прпу-Это дело"});
        doc.put("Примечание", new String[]{"21"});
        doc.put("Срок исполнения", new String[]{"21.12.2018"});
        doc.put("На контроле", new String[]{"Да"});
        doc.put("Нерегистрируемый", new String[]{"Да"});



        //авторизоваться
        auth("jd1","jd1","123");
        //создать входящий документ
        createincoming(doc);



        /*doc.put("Статус",new String[]{"Черновик"});
        doc.put("Номер",new String[]{"Не присвоен"});
        doc.put("Запись в бж",new String[]{historystandartcreate(doc)});
        int k = doc.get("Номер дела").length;
        if (k == 1)
            doc.put("Номер дела", new String[]{"/" + doc.get("Номер дела")[k-1]});
        else
        if (k == 2)
            doc.put("Номер дела", new String[]{"/" + doc.get("Номер дела")[k-2] + "/" + doc.get("Номер дела")[k-1]});
        else
        if (k > 2)
            doc.put("Номер дела", new String[]{"/_/" + doc.get("Номер дела")[k-2] + "/" + doc.get("Номер дела")[k-1]});
        driver.get("http://172.16.125.14:8180/share/page/document?nodeRef=workspace://SpacesStore/9373b47f-569f-4334-8c46-72e487f64093");*/
        readincoming(doc);
        readhistory(doc.get("Запись в бж"),doc);
    }
}