package Box;

import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;

import java.util.HashMap;

import static Box.Base.*;
import static Box.Users.getuserbylogin;
import static Box.Users.getuserbyroles;

@Features("Организационно-распорядительный документ")
@Test
public class OrdTest extends About {

    @Title("Создать организационно-распорядительный документ")
    public void test5() {
        Users.User user = getuserbyroles("ОРД. Создатели");

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

}
