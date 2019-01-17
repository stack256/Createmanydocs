package Box;

import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;

import static Box.Base.*;
import static Box.NdStep.*;
import static Box.Users.getuserbylogin;
import static Box.Users.getuserbyroles;

@Features("Нормативные документы")
@Test
public class NdTest extends About {

    @Title("Создать нормативный документ")
    public void test4() {
        Users.User user = getuserbyroles("НД. Создатели");

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


}
