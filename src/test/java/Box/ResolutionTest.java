package Box;

import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;

import java.util.HashMap;

import static Box.Base.*;
import static Box.ResolutionStep.*;
import static Box.Users.getuserbylogin;
import static Box.Users.getuserbyroles;

@Features("Резолюция")
@Test
public class ResolutionTest extends About {

    @Title("Создать резолюцию")
    public void test7() {
        Users.User user = getuserbyroles("Резолюции. Создатели");

        HashMap<String, String[]> doc = new HashMap<String, String[]>();;
        doc.put("document", new String[]{"resolutions"});
        doc.put("Утверждено вне системы", new String[]{"Да"});
        doc.put("Тематика", new String[]{"Доставка воды"});
        doc.put("Автор", new String[]{getuserbylogin(user.login).full});
        doc.put("Контролер", new String[]{getuserbylogin("denisov").full});
        doc.put("Завершающий", new String[]{"Контролёр"});
        doc.put("Контроль", new String[]{"Да"});
        doc.put("Срок исполнения", new String[]{"1 календарный день"});

        HashMap<String, HashMap<String, String[]>> errands = new HashMap<String, HashMap<String, String[]>>();
        HashMap<String, String[]> errand = new HashMap<String, String[]>();
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
        createresolutions(doc, "Направить", errands);
        readresolutions(doc);
        doc.put("Запись в бж",new String[]{historystandartcreateresolutions(doc)});
        readhistory(doc.get("Запись в бж"),doc);
        //if (!stack.get(0).value)
        removedocs();
    }

}
