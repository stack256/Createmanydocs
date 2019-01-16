package Box;


import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.util.HashMap;

import static Box.Base.*;
import static Box.Users.getuserbylogin;
import static Box.Users.getuserbyroles;

@Features("Простые математические операции")
@Stories("История")
@Test
public class MainTest extends About {

    @Stories("Зарегистрировать черновик")
    @Title("Проверка отображения записи БЖ при отсутствии е мэйла у корреспондента")
    public void test14() {
        Users.User user = getuserbyroles("СЭД. Регистраторы");

        doc.put("document", new String[]{"incoming"});
        doc.put("Заголовок", new String[]{"Тест"});
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
                current_user + " перевел(а) документ \"" + incoming_header(doc.get("Вид документа"), new String[]{"Не присвоено"}) + "\" в статус \"" + doc.get("Статус")[0] + "\"",
                "Корреспонденту ООО Тюльпан не было направлено уведомление по причине отсутствия адреса электронной почты"
        });
        readhistory(doc.get("Запись в бж"),doc);
        //if (!stack.get(0).value)
        removedocs();
    }

}