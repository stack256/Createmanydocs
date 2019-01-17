package Box;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;


import java.util.HashMap;

import static Box.Base.*;
import static Box.IncomingStep.*;
import static Box.Users.*;

@Features("Тестовый")
@Stories("История")
//@Test
public class MainTest extends About {

    @Title("Создание документа копированием")
    public void test15() {
        /*Users.User user = getuserbyroles("СЭД. Регистраторы");

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
        doc.put("Вложения Входящий", new String[]{System.getProperty("user.dir") + "/Вложение.doc"});
        doc.put("Вложения Прочее", new String[]{System.getProperty("user.dir") + "/Вложение.xls"});

        //авторизоваться
        auth(user.famio,user.login,user.pass);
        //создать входящий документ
        createincoming(doc);
        readincoming(doc);
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

        openrightblock("Вложения");
        if (waitelement(Objects.Document.Viewform.attachments_showinlist, false))
            click("Показать в виде списка", Objects.Document.Viewform.attachments_showinlist);
        softassertfail(!waitelement("//td[contains(@class,'category-name') and contains(text(),'Входящий')]//ancestor::div[contains(@class,'attachment-list')]//td[contains(.,'Вложение.doc')]",false),"Найдено вложение в категории Входящий");
        softassertfail(!waitelement("//td[contains(@class,'category-name') and contains(text(),'Прочее')]//ancestor::div[contains(@class,'attachment-list')]//td[contains(.,'Вложение.xls')]",false),"Найдено вложение в категории Прочее");

        //проверить наличие записи в бж
        doc1.put("Запись в бж",new String[]{historystandartcreate(doc1)});
        readhistory(doc1.get("Запись в бж"),doc1);
        //if (!stack.get(0).value)
        removedocs();*/
    }


}