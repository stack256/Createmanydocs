package Box;

import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;

import java.util.HashMap;

import static Box.Base.*;
import static Box.InternalStep.*;
import static Box.Users.getuserbylogin;
import static Box.Users.getuserbyroles;

@Features("Внутренний")
@Test
public class InternalTest extends About {

    @Title("Создать внутренний документ")
    public void test2() {
        Users.User user = getuserbyroles("Внутренние. Создатели");

        HashMap<String, String[]> doc = new HashMap<String, String[]>();;
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

    @Title("Создание документа копированием")
    public void test21() {
        Users.User user = getuserbyroles("Внутренние. Создатели");

        HashMap<String, String[]> doc = new HashMap<String, String[]>();;
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

        click("Копировать документ", Objects.Document.Viewform.copydoc_button);
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

    @Title("Зарегистрировать проект")
    public void test22() {
        Users.User user = getuserbyroles("Внутренние. Создатели");

        HashMap<String, String[]> doc = new HashMap<String, String[]>();;
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
        righactionexecute("Зарегистрировать проект","ОК","Проект",doc);
        doc.put("Дата регистрации проекта", new String[]{currentdate()});
        doc.put("Дата", new String[]{currentdate()});
        //проверить атрибуты и их значения на форме просмотра
        readinternal(doc);
        //проверить наличие записи в бж
        doc.put("Запись в бж",new String[]{
                getuserbylogin(currentcurrent_login()).famio + " зарегистрировал(а) проект документа " + doc.get("Номер")[0] + " от " + doc.get("Дата")[0],
                historystandartchangestatus(doc)});
        readhistory(doc.get("Запись в бж"),doc);

        //if (!stack.get(0).value)
        removedocs();
    }

    /*
тут написано добавление маршрута согласования
но не написано чтение маршрута согласования
    @Description("Какое то описание")
    @Severity(SeverityLevel.CRITICAL)
    @Features("Внутренний")
    @Stories("Жизненный цикл")
    @Title("Создать внутренний документ")
    @Test(retryAnalyzer = About.Retry.class)
    public void test23() {
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
        openrightblock("Основные сведения");

        //auth("Шестаков", getuserbylogin("shestakov").login,getuserbylogin("shestakov").pass);
        //driver.get("http://172.16.125.2:8080/ecm/page/document?nodeRef=workspace://SpacesStore/a0aa19a1-eb5f-450a-94d5-5b97f5421a06");
        changetab("Согласование и подписание",doc);

        doc.put("Согласование", new String[]{"Нетиповой"});
        doc.put("Согласование Завершать после первого отклонения согласующим", new String[]{"Да"});
        doc.put("Согласование Уведомлять о каждой рецензии", new String[]{"Да"});
        doc.put("Согласование По истечении срока", new String[]{"Вернуть на доработку"});

        HashMap<String, HashMap<String, String[]>> approval = new HashMap<String, HashMap<String, String[]>>();
        HashMap<String, String[]> approvalitem = new HashMap<String, String[]>();
        approvalitem.put("document", new String[]{"approval"});
        approvalitem.put("Название этапа", new String[]{"Первый"});
        approvalitem.put("Тип этапа", new String[]{"Последовательно"});
        approvalitem.put("Срок по умолчанию для согласующего (р. д.)", new String[]{"2"});
        approvalitem.put("Использовать правило для этапа", new String[]{"Нет"});
        approvalitem.put("Согласующие", new String[]{"Сотрудник",getuserbylogin("denisov").full});
        approval.put("1",approvalitem);
        approvalitem = new HashMap<String, String[]>();
        approvalitem.put("document", new String[]{"approval"});
        approvalitem.put("Название этапа", new String[]{"Второй"});
        approvalitem.put("Тип этапа", new String[]{"Параллельно"});
        approvalitem.put("Срок по умолчанию для согласующего (р. д.)", new String[]{"3"});
        approvalitem.put("Использовать правило для этапа", new String[]{"Да"});
        approvalitem.put("Правило для этапа", new String[]{"Руководители по иерархии"});
        approval.put("2",approvalitem);

        approvaladd(doc,approval);


        //if (!stack.get(0).value)
        removedocs();
    }

*/
}
