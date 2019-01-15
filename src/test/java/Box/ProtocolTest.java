package Box;

import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;

import java.util.HashMap;

import static Box.Base.*;
import static Box.Users.getuserbylogin;
import static Box.Users.getuserbyroles;

@Features("Протокол")
@Test
public class ProtocolTest extends About {

    @Title("Создать протокол")
    public void test8() {
        Users.User user = getuserbyroles("Совещания.Создатели");

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
