package Box;

import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;

import java.util.HashMap;

import static Box.Base.*;
import static Box.IncomingStep.*;
import static Box.Users.*;

@Features("Входящий")
@Test(retryAnalyzer = About.Retry.class)
public class IncomingTest extends About {

    @Title("Создать входящий документ в статусе Черновик")
    public void test1() {
        User user = getuserbyroles("СЭД. Регистраторы");

        HashMap<String, String[]> doc = new HashMap<String, String[]>();;
        doc.put("document", new String[]{"incoming"});
        doc.put("Заголовок", new String[]{randomstring(10)});
        doc.put("Вид документа", new String[]{"Запрос"});
        doc.put("Способ доставки", new String[]{"Личный прием"});
        doc.put("Корреспондент", new String[]{"AT_Organization"});
        doc.put("Корреспондент Тип", new String[]{"Внутренний контрагент"});
        doc.put("Корреспондент Наименование", new String[]{"AT_Organization"});
        doc.put("Представитель корреспондента", new String[]{getuserbylogin("denisov").full});
        doc.put("Получатель", new String[]{"Сотрудник",getuserbylogin("filippova").full});
        //doc.put("В ответ на", new String[]{"Исходящий документ: А1 ЭП только Прочее, № ИСХ-01035/17 от 24.10.2017"});
        //doc.put("В ответ на Номер", new String[]{"1035"});
        doc.put("Исходящий номер", new String[]{randomstring(7)});
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

    @Title("Направить на регистрацию черновик")
    public void test11() {
        User user = getuserbyroles("СЭД. Регистраторы");

        HashMap<String, String[]> doc = new HashMap<String, String[]>();;
        doc.put("document", new String[]{"incoming"});
        doc.put("Заголовок", new String[]{randomstring(10)});
        doc.put("Вид документа", new String[]{"Запрос"});
        doc.put("Способ доставки", new String[]{"Личный прием"});
        doc.put("Корреспондент", new String[]{"AT_Organization"});
        doc.put("Корреспондент Тип", new String[]{"Внутренний контрагент"});
        doc.put("Корреспондент Наименование", new String[]{"AT_Organization"});
        doc.put("Представитель корреспондента", new String[]{getuserbylogin("denisov").full});
        doc.put("Получатель", new String[]{"Сотрудник",getuserbylogin("filippova").full});
        //doc.put("В ответ на", new String[]{"Исходящий документ: А1 ЭП только Прочее, № ИСХ-01035/17 от 24.10.2017"});
        //doc.put("В ответ на Номер", new String[]{"1035"});
        doc.put("Исходящий номер", new String[]{randomstring(7)});
        doc.put("Исходящий от", new String[]{"21.12.2019"});
        doc.put("Содержание", new String[]{"21.12.2019"});
        doc.put("Количество листов", new String[]{"21"});
        doc.put("Тематика", new String[]{"Доставка воды"});
        //doc.put("Номер дела", new String[]{"2666","123","прпу-Это дело"});
        doc.put("Примечание", new String[]{"21"});
        doc.put("Срок исполнения", new String[]{"21.12.2019"});
        doc.put("На контроле", new String[]{"Да"});
        doc.put("Нерегистрируемый", new String[]{"Нет"});

        //авторизоваться
        auth(user.famio,user.login,user.pass);
        //создать входящий документ
        createincoming(doc);
        //проверить атрибуты и их значения на форме просмотра
        readincoming(doc);
        //проверить наличие записи в бж
        doc.put("Запись в бж",new String[]{historystandartcreate(doc)});
        readhistory(doc.get("Запись в бж"),doc);
        //выполнить действие Направить на регистрацию
        righactionexecute("Направить на регистрацию","ОК","На обработке регистратором",doc);
        //проверить атрибуты и их значения на форме просмотра
        readincoming(doc);
        //проверить наличие записи в бж
        doc.put("Запись в бж",new String[]{historystandartchangestatus(doc)});
        readhistory(doc.get("Запись в бж"),doc);
        //if (!stack.get(0).value)
        removedocs();
    }

    @Stories("Зарегистрировать черновик")
    @Title("Проверка отправки уведомлений корреспонденту, если не заполнены данные по исходящему документу")
    public void test12() {
        User user = getuserbyroles("СЭД. Регистраторы");

        HashMap<String, String[]> doc = new HashMap<String, String[]>();;
        doc.put("document", new String[]{"incoming"});
        doc.put("Заголовок", new String[]{randomstring(10)});
        doc.put("Вид документа", new String[]{"Запрос"});
        doc.put("Способ доставки", new String[]{"Личный прием"});
        doc.put("Корреспондент", new String[]{"ООО Ромашка"});
        doc.put("Корреспондент Тип", new String[]{"Внешний контрагент"});
        doc.put("Корреспондент Наименование", new String[]{"ООО Ромашка"});
        //doc.put("Представитель корреспондента", new String[]{getuserbylogin("denisov").full});
        doc.put("Получатель", new String[]{"Сотрудник",getuserbylogin("filippova").full});
        //doc.put("В ответ на", new String[]{"Исходящий документ: А1 ЭП только Прочее, № ИСХ-01035/17 от 24.10.2017"});
        //doc.put("В ответ на Номер", new String[]{"1035"});
        //doc.put("Исходящий номер", new String[]{"Outgoing-number"});
        //doc.put("Исходящий от", new String[]{"21.12.2019"});
        doc.put("Содержание", new String[]{"21.12.2019"});
        doc.put("Количество листов", new String[]{"21"});
        doc.put("Тематика", new String[]{"Доставка воды"});
        //doc.put("Номер дела", new String[]{"2666","123","прпу-Это дело"});
        doc.put("Примечание", new String[]{"21"});
        doc.put("Срок исполнения", new String[]{"21.12.2019"});
        doc.put("На контроле", new String[]{"Да"});
        doc.put("Нерегистрируемый", new String[]{"Нет"});

        //авторизоваться
        auth(user.famio,user.login,user.pass);
        //создать входящий документ
        createincoming(doc);
        //проверить атрибуты и их значения на форме просмотра
        readincoming(doc);
        //проверить наличие записи в бж
        doc.put("Запись в бж",new String[]{historystandartcreate(doc)});
        readhistory(doc.get("Запись в бж"),doc);

        //выполнить действие Зарегистрировать
        righactionexecute("Зарегистрировать","ОК","Зарегистрирован",doc);
        doc.put("Дата регистрации",doc.get("Дата"));
        //проверить атрибуты и их значения на форме просмотра
        readincoming(doc);
        //проверить наличие записи в бж
        doc.put("Запись в бж",new String[]{currentcurrent_user() + " перевел(а) документ \"" + incoming_header(doc.get("Вид документа"), new String[]{"Не присвоено"}) + "\" в статус \"" + doc.get("Статус")[0] + "\""});
        readhistory(doc.get("Запись в бж"),doc);
        ReadEmail("itsarkova", "12345","Добрый день! Ваше письмо " + doc.get("Заголовок")[0] + " № б/н от дата не указана, поступившее " + doc.get("Дата")[0] + ", зарегистрировано " + doc.get("Дата")[0] + " под номером " + doc.get("Номер")[0] + ".");
        //ReadEmail("itsarkova", "12345","Добрый день! Ваше письмо " + doc.get("Заголовок")[0] + " б/н дата не указана, поступившее " + doc.get("Дата")[0] + ", зарегистрировано " + doc.get("Дата")[0] + " под номером " + doc.get("Номер")[0] + ".");
        //if (!stack.get(0).value)
        removedocs();
    }

    @Stories("Зарегистрировать черновик")
    @Title("Проверка отправки уведомлений корреспонденту, если заполнены данные по исходящему документу")
    public void test13() {
        User user = getuserbyroles("СЭД. Регистраторы");

        HashMap<String, String[]> doc = new HashMap<String, String[]>();;
        doc.put("document", new String[]{"incoming"});
        doc.put("Заголовок", new String[]{randomstring(10)});
        doc.put("Вид документа", new String[]{"Запрос"});
        doc.put("Способ доставки", new String[]{"Курьерская служба"});
        doc.put("Корреспондент", new String[]{"ООО Ромашка"});
        doc.put("Корреспондент Тип", new String[]{"Внешний контрагент"});
        doc.put("Корреспондент Наименование", new String[]{"ООО Ромашка"});
        //doc.put("Представитель корреспондента", new String[]{getuserbylogin("denisov").full});
        doc.put("Получатель", new String[]{"Сотрудник",getuserbylogin("filippova").full});
        //doc.put("В ответ на", new String[]{"Исходящий документ: А1 ЭП только Прочее, № ИСХ-01035/17 от 24.10.2017"});
        //doc.put("В ответ на Номер", new String[]{"1035"});
        doc.put("Исходящий номер", new String[]{randomstring(7)});
        doc.put("Исходящий от", new String[]{currentdate(-1)});
        doc.put("Содержание", new String[]{"21.12.2019"});
        doc.put("Количество листов", new String[]{"21"});
        doc.put("Тематика", new String[]{"Доставка воды"});
        //doc.put("Номер дела", new String[]{"2666","123","прпу-Это дело"});
        doc.put("Примечание", new String[]{"21"});
        doc.put("Срок исполнения", new String[]{"21.12.2019"});
        doc.put("На контроле", new String[]{"Да"});
        doc.put("Нерегистрируемый", new String[]{"Нет"});

        //авторизоваться
        auth(user.famio,user.login,user.pass);
        //создать входящий документ
        createincoming(doc);
        //проверить атрибуты и их значения на форме просмотра
        readincoming(doc);
        //проверить наличие записи в бж
        doc.put("Запись в бж",new String[]{historystandartcreate(doc)});
        readhistory(doc.get("Запись в бж"),doc);
        //выполнить действие Направить на регистрацию
        righactionexecute("Зарегистрировать","ОК","Зарегистрирован",doc);
        doc.put("Дата регистрации",doc.get("Дата"));
        //проверить атрибуты и их значения на форме просмотра
        readincoming(doc);
        //проверить наличие записи в бж
        doc.put("Запись в бж",new String[]{currentcurrent_user() + " перевел(а) документ \"" + incoming_header(doc.get("Вид документа"), new String[]{"Не присвоено"}) + "\" в статус \"" + doc.get("Статус")[0] + "\""});
        readhistory(doc.get("Запись в бж"),doc);
        ReadEmail("itsarkova", "12345","Добрый день! Ваше письмо " + doc.get("Заголовок")[0] + " № " + doc.get("Исходящий номер")[0] + " от " + doc.get("Исходящий от")[0] + ", поступившее " + doc.get("Дата")[0] + ", зарегистрировано " + doc.get("Дата")[0] + " под номером " + doc.get("Номер")[0] + ".");
        //ReadEmail("itsarkova", "12345","Добрый день! Ваше письмо " + doc.get("Заголовок")[0] + " " + doc.get("Исходящий номер")[0] + " " + doc.get("Исходящий от")[0] + ", поступившее " + doc.get("Дата")[0] + ", зарегистрировано " + doc.get("Дата")[0] + " под номером " + doc.get("Номер")[0] + ".");
//if (!stack.get(0).value)
        removedocs();
    }

    @Stories("Зарегистрировать черновик")
    @Title("Проверка отображения записи БЖ при отсутствии е мэйла у корреспондента")
    public void test14() {
        User user = getuserbyroles("СЭД. Регистраторы");

        HashMap<String, String[]> doc = new HashMap<String, String[]>();;
        doc.put("document", new String[]{"incoming"});
        doc.put("Заголовок", new String[]{randomstring(10)});
        doc.put("Вид документа", new String[]{"Запрос"});
        doc.put("Способ доставки", new String[]{"Личный прием"});
        doc.put("Корреспондент", new String[]{"ООО Тюльпан"});
        doc.put("Корреспондент Тип", new String[]{"Внешний контрагент"});
        doc.put("Корреспондент Наименование", new String[]{"ООО Тюльпан"});
        //doc.put("Представитель корреспондента", new String[]{getuserbylogin("denisov").full});
        doc.put("Получатель", new String[]{"Сотрудник",getuserbylogin("filippova").full});
        //doc.put("В ответ на", new String[]{"Исходящий документ: А1 ЭП только Прочее, № ИСХ-01035/17 от 24.10.2017"});
        //doc.put("В ответ на Номер", new String[]{"1035"});
        //doc.put("Исходящий номер", new String[]{"78/2"});
        //doc.put("Исходящий от", new String[]{currentdate(-1)});
        doc.put("Содержание", new String[]{"21.12.2019"});
        doc.put("Количество листов", new String[]{"21"});
        doc.put("Тематика", new String[]{"Доставка воды"});
        //doc.put("Номер дела", new String[]{"2666","123","прпу-Это дело"});
        doc.put("Примечание", new String[]{"21"});
        doc.put("Срок исполнения", new String[]{"21.12.2019"});
        doc.put("На контроле", new String[]{"Да"});
        doc.put("Нерегистрируемый", new String[]{"Нет"});

        //авторизоваться
        auth(user.famio,user.login,user.pass);
        //создать входящий документ
        createincoming(doc);
        //проверить атрибуты и их значения на форме просмотра
        readincoming(doc);
        //проверить наличие записи в бж
        doc.put("Запись в бж",new String[]{historystandartcreate(doc)});
        readhistory(doc.get("Запись в бж"),doc);
        //выполнить действие Направить на регистрацию
        righactionexecute("Зарегистрировать","ОК","Зарегистрирован",doc);
        doc.put("Дата регистрации",doc.get("Дата"));
        //проверить атрибуты и их значения на форме просмотра
        readincoming(doc);
        //проверить наличие записи в бж
        doc.put("Запись в бж",new String[]{
                currentcurrent_user() + " перевел(а) документ \"" + incoming_header(doc.get("Вид документа"), new String[]{"Не присвоено"}) + "\" в статус \"" + doc.get("Статус")[0] + "\"",
                "Корреспонденту ООО Тюльпан не было направлено уведомление по причине отсутствия адреса электронной почты"
        });
        readhistory(doc.get("Запись в бж"),doc);
        //if (!stack.get(0).value)
        removedocs();
    }

    @Title("Создание документа копированием")
    public void test15() {
        Users.User user = getuserbyroles("СЭД. Регистраторы");

        HashMap<String, String[]> doc = new HashMap<String, String[]>();;
        doc.put("document", new String[]{"incoming"});
        doc.put("Заголовок", new String[]{randomstring(10)});
        doc.put("Вид документа", new String[]{"Запрос"});
        doc.put("Способ доставки", new String[]{"Личный прием"});
        doc.put("Корреспондент", new String[]{"AT_Organization"});
        doc.put("Корреспондент Тип", new String[]{"Внутренний контрагент"});
        doc.put("Корреспондент Наименование", new String[]{"AT_Organization"});
        doc.put("Представитель корреспондента", new String[]{getuserbylogin("denisov").full});
        doc.put("Получатель", new String[]{"Сотрудник",getuserbylogin("filippova").full});
        //doc.put("В ответ на", new String[]{"Исходящий документ: А1 ЭП только Прочее, № ИСХ-01035/17 от 24.10.2017"});
        //doc.put("В ответ на Номер", new String[]{"1035"});
        doc.put("Исходящий номер", new String[]{randomstring(7)});
        doc.put("Исходящий от", new String[]{"21.12.2019"});
        doc.put("Содержание", new String[]{"21.12.2019"});
        doc.put("Количество листов", new String[]{"21"});
        doc.put("Тематика", new String[]{"Доставка воды"});
        //doc.put("Номер дела", new String[]{"2666","123","прпу-Это дело"});
        doc.put("Примечание", new String[]{"21"});
        doc.put("Срок исполнения", new String[]{"21.12.2019"});
        doc.put("На контроле", new String[]{"Да"});
        doc.put("Нерегистрируемый", new String[]{"Да"});
        doc.put("Вложения Входящий", new String[]{System.getProperty("user.dir") + "/Вложение.doc"});
        doc.put("Вложения Прочее", new String[]{System.getProperty("user.dir") + "/Вложение.xls"});

        //авторизоваться
        auth(user.famio,user.login,user.pass);
        //создать входящий документ
        createincoming(doc);
        //проверить атрибуты и их значения на форме просмотра
        readincoming(doc);
        //проверить наличие вложений
        checkattachyep(doc, "Входящий", "Прочее");
        //проверить наличие записи в бж
        doc.put("Запись в бж",new String[]{historystandartcreate(doc)});
        readhistory(doc.get("Запись в бж"),doc);

        click("Копировать документ", Objects.Document.Viewform.copydoc_button);
        HashMap<String, String[]> doc1 = new HashMap<String, String[]>();
        doc1.put("Заголовок",doc.get("Заголовок"));
        doc1.put("Вид документа",doc.get("Вид документа"));
        doc1.put("Способ доставки",doc.get("Способ доставки"));
        doc1.put("Корреспондент",doc.get("Корреспондент"));
        doc1.put("Получатель",doc.get("Получатель"));
        doc1.put("Исходящий номер",doc.get("Исходящий номер"));
        doc1.put("Исходящий от",doc.get("Исходящий от"));
        doc1.put("Содержание",doc.get("Содержание"));
        doc1.put("Тематика",doc.get("Тематика"));
        doc1.put("Номер дела",doc.get("Номер дела"));
        doc1.put("Срок исполнения",doc.get("Срок исполнения"));
        doc1.put("На контроле",doc.get("На контроле"));
        //создать входящий документ
        createincoming(doc1,true);
        //проверить атрибуты и их значения на форме просмотра
        readincoming(doc1);
        //проверить отсутсвие вложений
        checkattachnope(doc, "Входящий", "Прочее");
        //проверить наличие записи в бж
        doc1.put("Запись в бж",new String[]{historystandartcreate(doc1)});
        readhistory(doc1.get("Запись в бж"),doc1);
        //if (!stack.get(0).value)
        removedocs();
    }

    @Stories("Направить адресатам черновик")
    @Title("Направление адресатам входящего документа в статусе “Черновик”. Нерегистрируемый = Да")
    public void test16() {
        Users.User user = getuserbyroles("СЭД. Регистраторы");

        HashMap<String, String[]> doc = new HashMap<String, String[]>();;
        doc.put("document", new String[]{"incoming"});
        doc.put("Заголовок", new String[]{randomstring(10)});
        doc.put("Вид документа", new String[]{"Запрос"});
        doc.put("Способ доставки", new String[]{"Личный прием"});
        doc.put("Корреспондент", new String[]{"AT_Organization"});
        doc.put("Корреспондент Тип", new String[]{"Внутренний контрагент"});
        doc.put("Корреспондент Наименование", new String[]{"AT_Organization"});
        doc.put("Представитель корреспондента", new String[]{getuserbylogin("denisov").full});
        doc.put("Получатель", new String[]{"Сотрудник",getanotheruser(user).full,"Организация","AT_Subdivision1"});
        //doc.put("В ответ на", new String[]{"Исходящий документ: А1 ЭП только Прочее, № ИСХ-01035/17 от 24.10.2017"});
        //doc.put("В ответ на Номер", new String[]{"1035"});
        doc.put("Исходящий номер", new String[]{randomstring(7)});
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
        //проверить атрибуты и их значения на форме просмотра
        readincoming(doc);
        //проверить наличие записи в бж
        doc.put("Запись в бж",new String[]{historystandartcreate(doc)});
        readhistory(doc.get("Запись в бж"),doc);
        //выполнить действие Направить на регистрацию
        righactionexecute("Направить адресатам","ОК","Направлен",doc);
        //проверить атрибуты и их значения на форме просмотра
        readincoming(doc);
        //проверить наличие уведомлений у получателей
        doc.put("СЭД. Получатель", new String[]{doc.get("Получатель")[1],getuserbylogin("gpetuhov").full});
        recipientnotifications(doc);


        //if (!stack.get(0).value)
        removedocs();
    }

    @Stories("Направить адресатам черновик")
    @Title("Направление входящего документа адресатам. Нерегистрируемый = Нет, регистратор без роли создания поручений")
    public void test17() {
        Users.User user = getuserbyrolewithoutrole("СЭД. Регистраторы","Поручения. Инициатор");

        HashMap<String, String[]> doc = new HashMap<String, String[]>();;
        doc.put("document", new String[]{"incoming"});
        doc.put("Заголовок", new String[]{randomstring(10)});
        doc.put("Вид документа", new String[]{"Запрос"});
        doc.put("Способ доставки", new String[]{"Личный прием"});
        doc.put("Корреспондент", new String[]{"AT_Organization"});
        doc.put("Корреспондент Тип", new String[]{"Внутренний контрагент"});
        doc.put("Корреспондент Наименование", new String[]{"AT_Organization"});
        doc.put("Представитель корреспондента", new String[]{getuserbylogin("denisov").full});
        doc.put("Получатель", new String[]{"Сотрудник",getanotheruser(user).full,"Организация","AT_Subdivision1"});
        //doc.put("В ответ на", new String[]{"Исходящий документ: А1 ЭП только Прочее, № ИСХ-01035/17 от 24.10.2017"});
        //doc.put("В ответ на Номер", new String[]{"1035"});
        doc.put("Исходящий номер", new String[]{randomstring(7)});
        doc.put("Исходящий от", new String[]{"21.12.2019"});
        doc.put("Содержание", new String[]{"21.12.2019"});
        doc.put("Количество листов", new String[]{"21"});
        doc.put("Тематика", new String[]{"Доставка воды"});
        //doc.put("Номер дела", new String[]{"2666","123","прпу-Это дело"});
        doc.put("Примечание", new String[]{"21"});
        doc.put("Срок исполнения", new String[]{"21.12.2019"});
        doc.put("На контроле", new String[]{"Да"});
        doc.put("Нерегистрируемый", new String[]{"Нет"});

        //авторизоваться
        auth(user.famio,user.login,user.pass);
        //создать входящий документ
        createincoming(doc);
        //проверить атрибуты и их значения на форме просмотра
        readincoming(doc);
        //проверить наличие записи в бж
        doc.put("Запись в бж",new String[]{historystandartcreate(doc)});
        readhistory(doc.get("Запись в бж"),doc);

        //выполнить действие Зарегистрировать
        righactionexecute("Зарегистрировать","ОК","Зарегистрирован",doc);
        doc.put("Дата регистрации",doc.get("Дата"));
        //проверить атрибуты и их значения на форме просмотра
        readincoming(doc);
        //проверить наличие записи в бж
        doc.put("Запись в бж",new String[]{currentcurrent_user() + " перевел(а) документ \"" + incoming_header(doc.get("Вид документа"), new String[]{"Не присвоено"}) + "\" в статус \"" + doc.get("Статус")[0] + "\""});
        readhistory(doc.get("Запись в бж"),doc);

        //выполнить действие Направить на регистрацию
        righactionexecute("Направить адресатам","Действие не может быть выполнено: Вы не обладаете ролью \"Поручения.Инициатор\". Обратитесь к администратору Системы","ОК");

        //if (!stack.get(0).value)
        removedocs();
    }

}
