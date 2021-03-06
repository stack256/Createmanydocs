package Box;

import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;

import java.util.HashMap;

import static Box.Base.*;
import static Box.ErrandStep.*;
import static Box.Users.getuserbylogin;
import static Box.Users.getuserbyroles;

@Features("Поручение")
@Test
public class ErrandTest extends About {

    @Title("Создать поручение")
    public void test6() {
        Users.User user = getuserbyroles("Поручения. Инициатор");

        HashMap<String, String[]> doc = new HashMap<String, String[]>();
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

}
