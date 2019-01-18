package Box;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Box.About.*;
import static Box.Base.*;

public class IncomingStep {
    @Step("Создать входящий документ")
    static void createincoming(Map<String, String[]> doc, boolean... flag) {
        if (flag.length == 0) {
            gotoarmsed();
            click("Создать", Objects.ARMSED.createButton);
            click("Входящий документ", Objects.ARMSED.Createmenu.incomingdocument);
            checkcreateincoming(doc);
            fillcreateincoming(doc);
        }
        String currenturl = currentdriver().getCurrentUrl();
        click("Создать", Objects.Document.Createform.create_button, Objects.Document.Viewform.Incomingdocument.status_field);
        doc.put("Статус",new String[]{"Черновик"});
        doc.put("Номер",new String[]{"Не присвоено"});
    }

    @Step("Проверить наличие атрибутов и их значения на форме создания")
    private static void checkcreateincoming(Map<String, String[]> doc) {
        //checkfield("Вложения Входящий", Objects.Document.Createform.Incomingdocument.attachments_label, Objects.Document.Createform.Incomingdocument.attachments_common_plus,doc);
    }

    @Step("Заполнить атрибуты")
    private static void fillcreateincoming(Map<String, String[]> doc) {
        verifyattr("Вложения", Objects.Document.Createform.Incomingdocument.attachments_label);
        fillfield("Вложения Входящий", Objects.Document.Createform.Incomingdocument.attachments_common_plus, doc.get("Вложения Входящий"), doc);
        fillfield("Вложения Прочее", Objects.Document.Createform.Incomingdocument.attachments_another_plus, doc.get("Вложения Прочее"), doc);

        verifyattr("Заголовок", Objects.Document.Createform.Incomingdocument.title_label);
        fillfield("Заголовок", Objects.Document.Createform.Incomingdocument.title_field, doc.get("Заголовок"), doc);

        verifyattr("Вид документа", Objects.Document.Createform.Incomingdocument.type_label);
        fillfield("Вид документа", Objects.Document.Createform.Incomingdocument.type_button, doc.get("Вид документа"), doc);

        verifyattr("Способ доставки", Objects.Document.Createform.Incomingdocument.delivery_method_label);
        fillfield("Способ доставки", Objects.Document.Createform.Incomingdocument.delivery_method_button, doc.get("Способ доставки"), doc);

        verifyattr("Корреспондент", Objects.Document.Createform.Incomingdocument.sender_label);
        fillfield("Корреспондент", Objects.Document.Createform.Incomingdocument.sender_button, doc.get("Корреспондент"), doc);

        verifyattr("Представитель корреспондента", Objects.Document.Createform.Incomingdocument.addressee_label);
        fillfield("Представитель корреспондента", Objects.Document.Createform.Incomingdocument.addressee_button, doc.get("Представитель корреспондента"), doc);

        verifyattr("Получатель", Objects.Document.Createform.Incomingdocument.recipient_label);
        fillfield("Получатель", Objects.Document.Createform.Incomingdocument.recipient_button, doc.get("Получатель"), doc);

        verifyattr("В ответ на", Objects.Document.Createform.Incomingdocument.response_to_label);
        fillfield("В ответ на", Objects.Document.Createform.Incomingdocument.response_to_button, doc.get("В ответ на"), doc);

        verifyattr("Исходящий номер", Objects.Document.Createform.Incomingdocument.outgoing_number_label);
        fillfield("Исходящий номер", Objects.Document.Createform.Incomingdocument.outgoing_number_field, doc.get("Исходящий номер"), doc);

        verifyattr("Исходящий от", Objects.Document.Createform.Incomingdocument.outgoing_date_label);
        fillfield("Исходящий от", Objects.Document.Createform.Incomingdocument.outgoing_date_field, doc.get("Исходящий от"), doc);

        verifyattr("Содержание", Objects.Document.Createform.Incomingdocument.summarycontent_label);
        fillfield("Содержание", Objects.Document.Createform.Incomingdocument.summarycontent_field, doc.get("Содержание"), doc);

        verifyattr("Количество листов", Objects.Document.Createform.Incomingdocument.sheets_number_label);
        fillfield("Количество листов", Objects.Document.Createform.Incomingdocument.sheets_number_field, doc.get("Количество листов"), doc);

        verifyattr("Тематика", Objects.Document.Createform.Incomingdocument.subject_label);
        fillfield("Тематика", Objects.Document.Createform.Incomingdocument.subject_button, doc.get("Тематика"), doc);

        verifyattr("Номер дела", Objects.Document.Createform.Incomingdocument.file_register_label);
        fillfield("Номер дела", Objects.Document.Createform.Incomingdocument.file_register_button, doc.get("Номер дела"), doc);

        verifyattr("Примечание", Objects.Document.Createform.Incomingdocument.note_label);
        fillfield("Примечание", Objects.Document.Createform.Incomingdocument.note_field, doc.get("Примечание"), doc);

        verifyattr("Срок исполнения", Objects.Document.Createform.Incomingdocument.execution_date_label);
        fillfield("Срок исполнения", Objects.Document.Createform.Incomingdocument.execution_date_field, doc.get("Срок исполнения"), doc);

        verifyattr("На контроле", Objects.Document.Createform.Incomingdocument.is_on_control_label);
        fillfield("На контроле", Objects.Document.Createform.Incomingdocument.is_on_control_checkbox, doc.get("На контроле"), doc);

        verifyattr("Нерегистрируемый", Objects.Document.Createform.Incomingdocument.is_not_registered_label);
        fillfield("Нерегистрируемый", Objects.Document.Createform.Incomingdocument.is_not_registered_checkbox, doc.get("Нерегистрируемый"), doc);
    }

    @Step("Проверить наличие атрибутов и их значения на форме просмотра")
    static void readincoming(Map<String, String[]> doc) {
        waitForLoad();
        String status = null;
        for (String val:doc.get("Статус"))
            status = val;
        waitelement(Objects.Document.Viewform.Incomingdocument.status_field);
        if (!currentdriver().findElement(By.xpath(Objects.Document.Viewform.Incomingdocument.status_field)).getText().equals(status)){
            currentdriver().get(currentdriver().getCurrentUrl());
        }
        currentdriver().get(currentdriver().getCurrentUrl());

        //waitelement(Document.documenttitle);
        String title = docgettitle();
        doc.put("Номер",new String[]{title.substring(title.indexOf(" № ")+3,title.indexOf(" от "))});
        doc.put("Дата",new String[]{title.substring(title.indexOf(" от ")+4,title.length())});

        checkfield("Номер", Objects.Document.Viewform.Incomingdocument.regnum_label, Objects.Document.Viewform.Incomingdocument.regnum_field, doc);

        checkfield("Дата регистрации", Objects.Document.Viewform.Incomingdocument.reg_data_label, Objects.Document.Viewform.Incomingdocument.reg_data_field, doc);

        checkfield("Заголовок", Objects.Document.Viewform.Incomingdocument.title_label, Objects.Document.Viewform.Incomingdocument.title_field, doc);

        checkfield("Вид документа", Objects.Document.Viewform.Incomingdocument.type_label, Objects.Document.Viewform.Incomingdocument.type_field, doc);

        checkfield("Способ доставки", Objects.Document.Viewform.Incomingdocument.delivery_method_label, Objects.Document.Viewform.Incomingdocument.delivery_method_field, doc);

        checkfield("Корреспондент", Objects.Document.Viewform.Incomingdocument.sender_label, Objects.Document.Viewform.Incomingdocument.sender_field, doc);

        checkfield("Представитель корреспондента", Objects.Document.Viewform.Incomingdocument.addressee_label, Objects.Document.Viewform.Incomingdocument.addressee_field, doc);

        checkfield("Исходящий номер", Objects.Document.Viewform.Incomingdocument.outgoing_number_label, Objects.Document.Viewform.Incomingdocument.outgoing_number_field, doc);

        checkfield("Исходящий от", Objects.Document.Viewform.Incomingdocument.outgoing_date_label, Objects.Document.Viewform.Incomingdocument.outgoing_date_field, doc);

        checkfield("Содержание", Objects.Document.Viewform.Incomingdocument.summary_label, Objects.Document.Viewform.Incomingdocument.summary_field, doc);

        checkfield("Получатель", Objects.Document.Viewform.Incomingdocument.recipient_label, Objects.Document.Viewform.Incomingdocument.recipient_field, doc);

        checkfield("Срок исполнения", Objects.Document.Viewform.Incomingdocument.execution_date_label, Objects.Document.Viewform.Incomingdocument.execution_date_field, doc);

        checkfield("На контроле", Objects.Document.Viewform.Incomingdocument.is_on_control_label, Objects.Document.Viewform.Incomingdocument.is_on_control_field, doc);

        checkfield("Номер дела", Objects.Document.Viewform.Incomingdocument.file_register_label, Objects.Document.Viewform.Incomingdocument.file_register_field, doc);

        checkfield("Количество листов", Objects.Document.Viewform.Incomingdocument.sheets_number_label, Objects.Document.Viewform.Incomingdocument.sheets_number_field, doc);

        checkfield("Тематика", Objects.Document.Viewform.Incomingdocument.subject_label, Objects.Document.Viewform.Incomingdocument.subject_field, doc);

        checkfield("Примечание", Objects.Document.Viewform.Incomingdocument.note_label, Objects.Document.Viewform.Incomingdocument.note_field, doc);

        checkfield("Нерегистрируемый", Objects.Document.Viewform.Incomingdocument.is_not_registered_label, Objects.Document.Viewform.Incomingdocument.is_not_registered_field, doc);

        String currenturl = currentdriver().getCurrentUrl();
        if (currenturl.contains("#")){
            currenturl = currenturl.substring(0,currenturl.indexOf('#'));
        }
        if (!currentremovedoc().contains(currenturl)){
            ArrayList<String> removedoc = new ArrayList<String>();
            removedoc = currentremovedoc();
            removedoc.add(currenturl);
            removedocmap.put(Thread.currentThread().getId(),removedoc);
        }
    }

    @Step("Проверить наличие уведомлений у получателей")
    static void recipientnotifications(HashMap<String, String[]> doc) {
        for (String val:doc.get("СЭД. Получатель"))
            readnotification(val,"Вам направлен новый документ " + doc.get("Заголовок")[0]);
    }
}
