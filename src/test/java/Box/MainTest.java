package Box;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.model.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static Box.Base.*;
import static Box.About.*;
import static Box.Users.*;
import static Box.Objects.*;

public class MainTest {

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
        removedoc = new ArrayList<>();
        stack.add(new About.Stack());

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



    @Description("Какое то описание")
    @Severity(SeverityLevel.CRITICAL)
    @Features("Входящий")
    @Stories("Жизненный цикл")
    @Title("Создать входящий документ")
    @Test
    public void test1() {
        User user = getuserbyroles("СЭД. Регистраторы");

        doc.put("document", new String[]{"incoming"});
        doc.put("Заголовок", new String[]{"Заголовок"});
        doc.put("Вид документа", new String[]{"Запрос"});
        doc.put("Способ доставки", new String[]{"Личный прием"});
        doc.put("Корреспондент", new String[]{"AT_Organization"});
        doc.put("Корреспондент Тип", new String[]{"Внутренний контрагент"});
        doc.put("Корреспондент Наименование", new String[]{"AT_Organization"});
        doc.put("Представитель корреспондента", new String[]{getuserbylogin("denisov").full});
        doc.put("Получатель", new String[]{"Сотрудник",getuserbylogin("filippova").full});
        //doc.put("В ответ на", new String[]{"Исходящий документ: А1 ЭП только Прочее, № ИСХ-01035/17 от 24.10.2017"});
        //doc.put("В ответ на Номер", new String[]{"1035"});
        doc.put("Исходящий номер", new String[]{"Outgoing-number"});
        doc.put("Исходящий от", new String[]{"21.12.2019"});
        doc.put("Содержание", new String[]{"21.12.2019"});
        doc.put("Количество листов", new String[]{"21"});
        doc.put("Тематика", new String[]{"Доставка воды"});
        //doc.put("Номер дела", new String[]{"2666","123","прпу-Это дело"});
        doc.put("Примечание", new String[]{"21"});
        doc.put("Срок исполнения", new String[]{"21.12.2019"});
        doc.put("На контроле", new String[]{"Да"});
        doc.put("Нерегистрируемый", new String[]{"Да"});

        //авторизоваться
        auth(user.famio,user.login,user.pass);
        //создать входящий документ
        createincoming(doc);
        readincoming(doc);
        doc.put("Запись в бж",new String[]{historystandartcreate(doc)});
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

        doc.put("document", new String[]{"internal"});
        doc.put("Составитель", new String[]{user.full});
        doc.put("Исполнитель", new String[]{user.full});
        doc.put("Заголовок", new String[]{"Заголовок"});
        doc.put("Вид документа", new String[]{"Аналитическая записка"});
        doc.put("Срок ответа", new String[]{"21.12.2019"});
        doc.put("Получатель", new String[]{"Сотрудник",getuserbylogin("filippova").full});
        doc.put("Содержание", new String[]{"21.12.2019"});
        doc.put("Подписано на бумажном носителе", new String[]{"Да"});
        doc.put("Подписанты", new String[]{getuserbylogin("fomin").full, getuserbylogin("kozlov").full});
        doc.put("Дата подписания", new String[]{"21.12.2019"});
        //doc.put("В ответ на", new String[]{"Внутренний документ: 1234567890, № NA-00094 от 28.09.2018"});
        //doc.put("В ответ на Номер", new String[]{"00094"});
        doc.put("Количество листов", new String[]{"21"});
        doc.put("Тематика", new String[]{"Доставка воды"});
        //doc.put("Номер дела", new String[]{"2666","123","прпу-Это дело"});
        doc.put("Примечание", new String[]{"21"});

        //авторизоваться
        auth(user.famio,user.login,user.pass);
        //создать входящий документ
        createinternal(doc);
        readinternal(doc);
        doc.put("Запись в бж",new String[]{historystandartcreate(doc)});
        readhistory(doc.get("Запись в бж"),doc);
        //if (!stack.get(0).value)
        removedocs();
    }



    @Description("Какое то описание")
    @Severity(SeverityLevel.CRITICAL)
    @Features("Внутренний")
    @Stories("Жизненный цикл")
    @Title("Создание документа копированием")
    @Test
    public void test21() {
        User user = getuserbyroles("Внутренние. Создатели");

        doc.put("document", new String[]{"internal"});
        doc.put("Заголовок", new String[]{"Заголовок"});
        doc.put("Вид документа", new String[]{"Аналитическая записка"});
        doc.put("Срок ответа", new String[]{"21.12.2019"});
        doc.put("Получатель", new String[]{"Сотрудник",getuserbylogin("filippova").full});
        doc.put("Содержание", new String[]{"21.12.2019"});
        doc.put("Подписано на бумажном носителе", new String[]{"Да"});
        doc.put("Подписанты", new String[]{getuserbylogin("fomin").full, getuserbylogin("kozlov").full});
        doc.put("Дата подписания", new String[]{"21.12.2019"});
        //doc.put("В ответ на", new String[]{"Внутренний документ: 1234567890, № NA-00094 от 28.09.2018"});
        //doc.put("В ответ на Номер", new String[]{"00094"});
        doc.put("Количество листов", new String[]{"21"});
        doc.put("Тематика", new String[]{"Доставка воды"});
        //doc.put("Номер дела", new String[]{"2666","123","прпу-Это дело"});
        doc.put("Примечание", new String[]{"21"});

        //авторизоваться
        auth(user.famio,user.login,user.pass);
        //создать входящий документ
        createinternal(doc);
        //проверить атрибуты и их значения на форме просмотра
        readinternal(doc);
        //проверить наличие записи в бж
        doc.put("Запись в бж",new String[]{historystandartcreate(doc)});
        readhistory(doc.get("Запись в бж"),doc);

        click("Копировать документ", Document.Viewform.copydoc_button);
        HashMap<String, String[]> doc1 = new HashMap<String, String[]>();
        doc1.put("Заголовок",doc.get("Заголовок"));
        doc1.put("Вид документа",doc.get("Вид документа"));
        doc1.put("Срок ответа",doc.get("Срок ответа"));
        doc1.put("Получатель",doc.get("Получатель"));
        doc1.put("Содержание",doc.get("Содержание"));
        doc1.put("Тематика",doc.get("Тематика"));
        doc1.put("Номер дела",doc.get("Номер дела"));
        //создать входящий документ
        createinternal(doc1,true);
        //проверить атрибуты и их значения на форме просмотра
        readinternal(doc1);
        //проверить наличие записи в бж
        doc1.put("Запись в бж",new String[]{historystandartcreate(doc1)});
        readhistory(doc1.get("Запись в бж"),doc1);

        //if (!stack.get(0).value)
        removedocs();
    }



    @Description("Какое то описание")
    @Severity(SeverityLevel.CRITICAL)
    @Features("Внутренний")
    @Stories("Жизненный цикл")
    @Title("Зарегистрировать проект")
    @Test
    public void test22() {
        User user = getuserbyroles("Внутренние. Создатели");

        doc.put("document", new String[]{"internal"});
        doc.put("Заголовок", new String[]{"Заголовок"});
        doc.put("Вид документа", new String[]{"Аналитическая записка"});
        doc.put("Срок ответа", new String[]{"21.12.2019"});
        doc.put("Получатель", new String[]{"Сотрудник",getuserbylogin("filippova").full});
        doc.put("Содержание", new String[]{"21.12.2019"});
        doc.put("Подписано на бумажном носителе", new String[]{"Да"});
        doc.put("Подписанты", new String[]{getuserbylogin("fomin").full, getuserbylogin("kozlov").full});
        doc.put("Дата подписания", new String[]{"21.12.2019"});
        //doc.put("В ответ на", new String[]{"Внутренний документ: 1234567890, № NA-00094 от 28.09.2018"});
        //doc.put("В ответ на Номер", new String[]{"00094"});
        doc.put("Количество листов", new String[]{"21"});
        doc.put("Тематика", new String[]{"Доставка воды"});
        //doc.put("Номер дела", new String[]{"2666","123","прпу-Это дело"});
        doc.put("Примечание", new String[]{"21"});

        //авторизоваться
        auth(user.famio,user.login,user.pass);
        //создать входящий документ
        createinternal(doc);
        //проверить атрибуты и их значения на форме просмотра
        readinternal(doc);
        //проверить наличие записи в бж
        doc.put("Запись в бж",new String[]{historystandartcreate(doc)});
        readhistory(doc.get("Запись в бж"),doc);

        //выполнить действие Зарегистрировать проект
        righactionexecute("Зарегистрировать проект","ОК","Проект");
        doc.put("Статус", new String[]{"Проект"});
        doc.put("Дата регистрации проекта", new String[]{currentdate()});
        doc.put("Дата", new String[]{currentdate()});
        //проверить атрибуты и их значения на форме просмотра
        readinternal(doc);
        //проверить наличие записи в бж
        doc.put("Запись в бж",new String[]{
                getuserbylogin(current_login).famio + " зарегистрировал(а) проект документа " + doc.get("Номер")[0] + " от " + doc.get("Дата")[0],
                historystandartchangestatus(doc)});
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

        doc.put("document", new String[]{"outgoing"});
        doc.put("Составитель", new String[]{user.full});
        doc.put("Исполнитель", new String[]{user.full});
        doc.put("Заголовок", new String[]{"Заголовок"});
        doc.put("Вид документа", new String[]{"Информационное письмо"});
        doc.put("Способ доставки", new String[]{"Личный прием"});
        doc.put("Корреспондент", new String[]{"AT_Organization"});
        doc.put("Корреспондент Тип", new String[]{"Внутренний контрагент"});
        doc.put("Корреспондент Наименование", new String[]{"AT_Organization"});
        doc.put("Адресат корреспондента", new String[]{getuserbylogin("denisov").full});
        doc.put("Содержание", new String[]{"21.12.2019"});
        doc.put("Подписано на бумажном носителе", new String[]{"Да"});
        doc.put("Подписанты", new String[]{getuserbylogin("fomin").full, getuserbylogin("kozlov").full});
        doc.put("Дата подписания", new String[]{"21.12.2019"});
        //doc.put("В ответ на", new String[]{"Внутренний документ: 1234567890, № NA-00094 от 28.09.2019"});
        //doc.put("В ответ на Номер", new String[]{"00094"});
        doc.put("Количество листов", new String[]{"21"});
        doc.put("Тематика", new String[]{"Доставка воды"});
        //doc.put("Номер дела", new String[]{"2666","123","прпу-Это дело"});
        doc.put("Примечание", new String[]{"21"});
        doc.put("Завершающий", new String[]{"Нет"});

        //авторизоваться
        auth(user.famio,user.login,user.pass);
        //создать входящий документ
        createoutgoing(doc);
        readoutgoing(doc);
        doc.put("Запись в бж",new String[]{historystandartcreate(doc)});
        readhistory(doc.get("Запись в бж"),doc);
        //if (!stack.get(0).value)
        removedocs();
    }



    @Description("Какое то описание")
    @Severity(SeverityLevel.CRITICAL)
    @Features("Нормативные документы")
    @Stories("Жизненный цикл")
    @Title("Создать нормативный документ")
    @Test
    public void test4() {
        User user = getuserbyroles("НД. Создатели");

        doc.put("document", new String[]{"nd"});
        doc.put("Составитель", new String[]{user.full});
        doc.put("Исполнитель", new String[]{user.full});
        doc.put("Заголовок", new String[]{"Заголовок"});
        doc.put("Вид документа", new String[]{"Категории подписываемых вложений"});
        doc.put("Содержание", new String[]{"21.12.2019"});
        doc.put("Количество листов", new String[]{"21"});
        doc.put("Подписано на бумажном носителе", new String[]{"Да"});
        doc.put("Подписанты", new String[]{getuserbylogin("fomin").full, getuserbylogin("kozlov").full});
        doc.put("Дата подписания", new String[]{"21.12.2019"});
        doc.put("Номер дела", new String[]{"2018","Управление","Рабочее подразделение","ALL-Дело для всех типов документов"});
        doc.put("Примечание", new String[]{"21"});
        doc.put("Тематика", new String[]{"Доставка воды"});
        doc.put("Подразделения", new String[]{"AT_Organization"});
        doc.put("Бессрочный", new String[]{"Нет"});
        doc.put("Период действия С", new String[]{"01.01.2019"});
        doc.put("Период действия По", new String[]{"21.12.2019"});

        //авторизоваться
        auth(user.famio,user.login,user.pass);
        //создать входящий документ
        creatend(doc);
        readnd(doc);
        doc.put("Запись в бж",new String[]{historystandartcreate(doc)});
        readhistory(doc.get("Запись в бж"),doc);
        //if (!stack.get(0).value)
        removedocs();
    }



    @Description("Какое то описание")
    @Severity(SeverityLevel.CRITICAL)
    @Features("Организационно-распорядительный документ")
    @Stories("Жизненный цикл")
    @Title("Создать организационно-распорядительный документ")
    @Test
    public void test5() {
        User user = getuserbyroles("ОРД. Создатели");

        doc.put("document", new String[]{"ord"});
        doc.put("Составитель", new String[]{user.full});
        doc.put("Исполнитель", new String[]{user.full});
        doc.put("Вид документа", new String[]{"Категории подписываемых вложений"});
        doc.put("Заголовок", new String[]{"Заголовок"});
        doc.put("Срок исполнения", new String[]{"21.12.2019"});
        doc.put("Содержание", new String[]{"21.12.2019"});
        doc.put("Подписано на бумажном носителе", new String[]{"Да"});
        doc.put("Подписанты", new String[]{getuserbylogin("fomin").full, getuserbylogin("kozlov").full});
        doc.put("Дата подписания", new String[]{"21.12.2019"});
        doc.put("Контролёр", new String[]{getuserbylogin("denisov").full});
        doc.put("Подтверждать завершение работы по документу", new String[]{"Да"});
        doc.put("Примечание", new String[]{"21"});
        doc.put("Количество листов", new String[]{"21"});
        doc.put("Тематика", new String[]{"Доставка воды"});
        doc.put("Номер дела", new String[]{"2018","Управление","Рабочее подразделение","ALL-Дело для всех типов документов"});
        //doc.put("Отменяемые документы", new String[]{"Внутренний документ: 1234567890, № NA-00094 от 28.09.2019"});
        //doc.put("Отменяемые документы Номер", new String[]{"00094"});
        //doc.put("Принимаемые документы", new String[]{"Внутренний документ: 1234567890, № NA-00094 от 28.09.2019"});
        //doc.put("Принимаемые документы Номер", new String[]{"00094"});

        item = new HashMap<String, String[]>();
        item.put("document", new String[]{"ord"});
        item.put("Пункты Заголовок", new String[]{"Заголовок"});
        item.put("Пункты Автор", new String[]{user.full});
        item.put("Пункты Содержание", new String[]{"Содержание"});
        item.put("Пункты Исполнитель", new String[]{getuserbylogin("denisov").full});
        item.put("Пункты Соисполнители", new String[]{getuserbylogin("kozlov").full});
        item.put("Пункты Срок исполнения", new String[]{"Без срока"});
        item.put("Пункты Требуется отчет", new String[]{"Да"});
        item.put("Пункты Контролер", new String[]{getuserbylogin("denisov").full});
        item.put("Пункты Тематика", new String[]{"Доставка воды"});
        items.put("1", item);

        //авторизоваться
        auth(user.famio,user.login,user.pass);
        //создать входящий документ
        createord(doc, items);
        readord(doc);
        doc.put("Запись в бж",new String[]{historystandartcreate(doc)});
        readhistory(doc.get("Запись в бж"),doc);
        //if (!stack.get(0).value)
        removedocs();
    }



    @Description("Какое то описание")
    @Severity(SeverityLevel.CRITICAL)
    @Features("Поручение")
    @Stories("Жизненный цикл")
    @Title("Создать поручение")
    @Test
    public void test6() {
        User user = getuserbyroles("Поручения. Инициатор");

        doc.put("document", new String[]{"errand"});
        doc.put("Тип поручения", new String[]{"На исполнение (неконтрольное)"});
        doc.put("Заголовок", new String[]{"Ознакомить подчиненных"});
        doc.put("Текст поручения", new String[]{"Заголовок"});
        doc.put("Исполнитель", new String[]{getuserbylogin("kozlov").full});
        doc.put("Соисполнители", new String[]{getuserbylogin("denisov").full});
        doc.put("Срок исполнения", new String[]{"21.12.2019"});
        doc.put("Направлять периодически", new String[]{"Да"});
        doc.put("Повторять", new String[]{"Ежедневно"});
        doc.put("Начало повторений", new String[]{"21.12.2018"});
        doc.put("Окончание повторений", new String[]{"21.12.2019"});
        doc.put("Контролер", new String[]{getuserbylogin("denisov").full});
        doc.put("Требуется отчет", new String[]{"Да"});
        doc.put("Получатель отчета", new String[]{"Контролер"});
        doc.put("Важное", new String[]{"Да"});
        doc.put("Тематика", new String[]{"Доставка воды"});

        //авторизоваться
        auth(user.famio,user.login,user.pass);
        //создать входящий документ
        createerrand(doc, "Сохранить черновик");
        readerrand(doc);
        doc.put("Запись в бж",new String[]{historystandartcreateerrand(doc)});
        readhistory(doc.get("Запись в бж"),doc);
        //if (!stack.get(0).value)
        removedocs();
    }



    @Description("Какое то описание")
    @Severity(SeverityLevel.CRITICAL)
    @Features("Резолюция")
    @Stories("Жизненный цикл")
    @Title("Создать резолюцию")
    @Test
    public void test7() {
        User user = getuserbyroles("Резолюции. Создатели");

        doc.put("document", new String[]{"resolutions"});
        doc.put("Утверждено вне системы", new String[]{"Да"});
        doc.put("Тематика", new String[]{"Доставка воды"});
        doc.put("Автор", new String[]{getuserbylogin(user.login).full});
        doc.put("Контролер", new String[]{getuserbylogin("denisov").full});
        doc.put("Завершающий", new String[]{"Контролёр"});
        doc.put("Контроль", new String[]{"Да"});
        doc.put("Срок исполнения", new String[]{"1 календарный день"});

        errand = new HashMap<String, String[]>();
        errand.put("document", new String[]{"resolutions"});
        errand.put("Поручения Тип поручения", new String[]{"На исполнение (неконтрольное)"});
        errand.put("Поручения Заголовок", new String[]{"Ознакомить подчиненных"});
        errand.put("Поручения Исполнитель", new String[]{getuserbylogin("kozlov").full});
        errand.put("Поручения Соисполнители", new String[]{getuserbylogin("denisov").full});
        errand.put("Поручения Контролер", new String[]{getuserbylogin("denisov").full});
        errand.put("Поручения Срок исполнения", new String[]{"Без срока"});
        errand.put("Поручения Требуется отчет", new String[]{"Да"});
        errand.put("Поручения Получатель отчета", new String[]{"Контролер"});
        errands.put("1",errand);
        errand = new HashMap<String, String[]>();

        //авторизоваться
        auth(user.famio,user.login,user.pass);
        //создать входящий документ
        createresolutions(doc, "Направить");
        readresolutions(doc);
        doc.put("Запись в бж",new String[]{historystandartcreateresolutions(doc)});
        readhistory(doc.get("Запись в бж"),doc);
        //if (!stack.get(0).value)
        removedocs();
    }



    @Description("Какое то описание")
    @Severity(SeverityLevel.CRITICAL)
    @Features("Протокол")
    @Stories("Жизненный цикл")
    @Title("Создать протокол")
    @Test
    public void test8() {
        User user = getuserbyroles("Совещания.Создатели");

        doc.put("document", new String[]{"protocol"});
        doc.put("Заголовок", new String[]{"Заголовок"});
        doc.put("Вид документа", new String[]{"Протокол совещания"});
        doc.put("Срок исполнения", new String[]{"27.11.2019"});
        doc.put("Содержание", new String[]{"27.11.2019"});
        doc.put("Согласующие", new String[]{getuserbylogin("denisov").full});
        doc.put("Председатель совещания", new String[]{getuserbylogin("kozlov").full});
        doc.put("Секретарь", new String[]{getuserbylogin("denisov").full});
        doc.put("Присутствовали", new String[]{getuserbylogin("denisov").full});
        doc.put("Подписано на бумажном носителе", new String[]{"Да"});
        doc.put("Примечание", new String[]{"27.11.2019"});
        doc.put("Количество листов", new String[]{"21"});
        doc.put("Тематика", new String[]{"Доставка воды"});
        doc.put("Номер дела", new String[]{"2018","Управление","Рабочее подразделение","ALL-Дело для всех типов документов"});
        doc.put("Автосоздание поручений", new String[]{"Да"});

        item = new HashMap<String, String[]>();
        item.put("document", new String[]{"protocol"});
        item.put("Пункт Формулировка", new String[]{"Формулировка"});
        item.put("Пункт Докладчик", new String[]{"Адресант","Гайткулов Ринат"});
        item.put("Пункт Содокладчики", new String[]{"Сотрудник",getuserbylogin("denisov").full});
        item.put("Пункт Описание", new String[]{"Описание"});
        item.put("Пункт Выступили", new String[]{"Выступили"});
        item.put("Пункт Решение", new String[]{"Решение"});
        item.put("Пункт Исполнитель", new String[]{getuserbylogin("denisov").full});
        item.put("Пункт Срок исполнения", new String[]{"27.11.2019"});
        item.put("Пункт Примечание", new String[]{"Примечание"});
        items.put("1",item);
        item = new HashMap<String, String[]>();

        //авторизоваться
        auth(user.famio,user.login,user.pass);
        //создать входящий документ
        createprotocol(doc);
        readprotocol(doc);
        doc.put("Запись в бж",new String[]{historystandartcreateprotocol(doc)});
        readhistory(doc.get("Запись в бж"),doc);
        //if (!stack.get(0).value)
        removedocs();
    }
}