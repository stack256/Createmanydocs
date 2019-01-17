package Box;

import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;

import static Box.Base.*;
import static Box.OutgoingStep.*;
import static Box.Users.*;

@Features("Исходящий")
@Test
public class OutgoingTest extends About {

    @Title("Создать исходящий документ")
    public void test3() {
        Users.User user = getuserbyroles("Исходящие. Создатели");

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

}
