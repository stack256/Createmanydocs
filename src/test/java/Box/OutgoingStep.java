package Box;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.Map;

import static Box.About.*;
import static Box.Base.*;

public class OutgoingStep {

    @Step("Создать исходящий документ")
    static void createoutgoing(Map<String, String[]> doc) {
        gotoarmsed();
        click("Создать", Objects.ARMSED.createButton);
        click("Исходящий документ", Objects.ARMSED.Createmenu.outgoingdocument);
        fillcreateoutgoing(doc);
        String currenturl = currentdriver().getCurrentUrl();
        click("Создать", Objects.Document.Createform.create_button, Objects.Document.Viewform.Outgoingdocument.status_field);
        doc.put("Статус",new String[]{"Черновик"});
        doc.put("Номер",new String[]{"Не присвоено"});
    }

    @Step("Заполнить атрибуты")
    private static void fillcreateoutgoing(Map<String, String[]> doc) {
        verifyattr("Вложения", Objects.Document.Createform.Outgoingdocument.attachments_label);

        verifyattr("Заголовок", Objects.Document.Createform.Outgoingdocument.title_label);
        fillfield("Заголовок", Objects.Document.Createform.Outgoingdocument.title_field, doc.get("Заголовок"), doc);

        verifyattr("Вид документа", Objects.Document.Createform.Outgoingdocument.type_label);
        fillfield("Вид документа", Objects.Document.Createform.Outgoingdocument.type_button, doc.get("Вид документа"), doc);

        verifyattr("Способ доставки", Objects.Document.Createform.Outgoingdocument.delivery_method_label);
        fillfield("Способ доставки", Objects.Document.Createform.Outgoingdocument.delivery_method_button, doc.get("Способ доставки"), doc);

        verifyattr("Корреспондент", Objects.Document.Createform.Outgoingdocument.sender_label);
        fillfield("Корреспондент", Objects.Document.Createform.Outgoingdocument.sender_button, doc.get("Корреспондент"), doc);

        verifyattr("Адресат корреспондента", Objects.Document.Createform.Outgoingdocument.addressee_label);
        fillfield("Адресат корреспондента", Objects.Document.Createform.Outgoingdocument.addressee_button, doc.get("Адресат корреспондента"), doc);

        verifyattr("Содержание", Objects.Document.Createform.Outgoingdocument.summarycontent_label);
        fillfield("Содержание", Objects.Document.Createform.Outgoingdocument.summarycontent_field, doc.get("Содержание"), doc);

        verifyattr("Подписано на бумажном носителе", Objects.Document.Createform.Outgoingdocument.signedbypaper_label);
        fillfield("Подписано на бумажном носителе", Objects.Document.Createform.Outgoingdocument.signedbypaper_checkbox, doc.get("Подписано на бумажном носителе"), doc);

        boolean t = false;
        for (String val:doc.get("Подписано на бумажном носителе"))
            if (val.equals("Да")) t = true;
        if (t) {
            verifyattr("Подписанты", Objects.Document.Createform.Outgoingdocument.signers_label);
            fillfield("Подписанты", Objects.Document.Createform.Outgoingdocument.signers_button, doc.get("Подписанты"), doc);

            verifyattr("Дата подписания", Objects.Document.Createform.Outgoingdocument.signing_date_label);
            fillfield("Дата подписания", Objects.Document.Createform.Outgoingdocument.signing_date_field, doc.get("Дата подписания"), doc);

            doc.put("Подписан", new String[]{"Да"});
        }

        verifyattr("В ответ на", Objects.Document.Createform.Outgoingdocument.response_to_label);
        fillfield("В ответ на", Objects.Document.Createform.Outgoingdocument.response_to_button, doc.get("В ответ на"), doc);

        verifyattr("Количество листов", Objects.Document.Createform.Outgoingdocument.sheets_number_label);
        fillfield("Количество листов", Objects.Document.Createform.Outgoingdocument.sheets_number_field, doc.get("Количество листов"), doc);

        verifyattr("Тематика", Objects.Document.Createform.Outgoingdocument.subject_label);
        fillfield("Тематика", Objects.Document.Createform.Outgoingdocument.subject_button, doc.get("Тематика"), doc);

        verifyattr("Номер дела", Objects.Document.Createform.Outgoingdocument.file_register_label);
        fillfield("Номер дела", Objects.Document.Createform.Outgoingdocument.file_register_button, doc.get("Номер дела"), doc);

        verifyattr("Примечание", Objects.Document.Createform.Outgoingdocument.note_label);
        fillfield("Примечание", Objects.Document.Createform.Outgoingdocument.note_field, doc.get("Примечание"), doc);

        verifyattr("Завершающий", Objects.Document.Createform.Outgoingdocument.finishing_label);
        //fillfield("Завершающий", Document.Createform.Outgoingdocument.finishing_field, doc.get("Завершающий"), doc);
        //атрибут временно залочен, хз насколько

    }

    @Step("Проверить наличие атрибутов и их значения на форме просмотра")
    static void readoutgoing(Map<String, String[]> doc) {
        waitForLoad();
        String status = null;
        for (String val:doc.get("Статус"))
            status = val;
        waitelement(Objects.Document.Viewform.Outgoingdocument.status_field);
        if (!currentdriver().findElement(By.xpath(Objects.Document.Viewform.Outgoingdocument.status_field)).getText().equals(status)){
            currentdriver().get(currentdriver().getCurrentUrl());
        }
        currentdriver().get(currentdriver().getCurrentUrl());

        String title = docgettitle();
        checkfield("Номер", Objects.Document.Viewform.Outgoingdocument.regnum_label, Objects.Document.Viewform.Outgoingdocument.regnum_field, doc);

        checkfield("Дата регистрации", Objects.Document.Viewform.Outgoingdocument.reg_data_label, Objects.Document.Viewform.Outgoingdocument.reg_data_field, doc);

        checkfield("Заголовок", Objects.Document.Viewform.Outgoingdocument.title_label, Objects.Document.Viewform.Outgoingdocument.title_field, doc);

        checkfield("Вид документа", Objects.Document.Viewform.Outgoingdocument.type_label, Objects.Document.Viewform.Outgoingdocument.type_field, doc);

        checkfield("Способ доставки", Objects.Document.Viewform.Outgoingdocument.delivery_method_label, Objects.Document.Viewform.Outgoingdocument.delivery_method_field, doc);

        checkfield("Корреспондент", Objects.Document.Viewform.Outgoingdocument.sender_label, Objects.Document.Viewform.Outgoingdocument.sender_field, doc);

        checkfield("Адресат корреспондента", Objects.Document.Viewform.Outgoingdocument.addressee_label, Objects.Document.Viewform.Outgoingdocument.addressee_field, doc);

        checkfield("Содержание", Objects.Document.Viewform.Outgoingdocument.summary_label, Objects.Document.Viewform.Outgoingdocument.summary_field, doc);

        checkfield("Количество листов", Objects.Document.Viewform.Outgoingdocument.sheets_number_label, Objects.Document.Viewform.Outgoingdocument.sheets_number_field, doc);

        checkfield("Тематика", Objects.Document.Viewform.Outgoingdocument.subject_label, Objects.Document.Viewform.Outgoingdocument.subject_field, doc);

        checkfield("Примечание", Objects.Document.Viewform.Outgoingdocument.note_label, Objects.Document.Viewform.Outgoingdocument.note_field, doc);

        checkfield("Номер дела", Objects.Document.Viewform.Outgoingdocument.file_register_label, Objects.Document.Viewform.Outgoingdocument.file_register_field, doc);

        checkfield("Составитель", Objects.Document.Viewform.Outgoingdocument.author_label, Objects.Document.Viewform.Outgoingdocument.author_field, doc);

        checkfield("Исполнитель", Objects.Document.Viewform.Outgoingdocument.executor_label, Objects.Document.Viewform.Outgoingdocument.executor_field, doc);

        checkfield("Номер проекта", Objects.Document.Viewform.Outgoingdocument.regnumproject_label, Objects.Document.Viewform.Outgoingdocument.regnumproject_field, doc);

        checkfield("Дата регистрации проекта", Objects.Document.Viewform.Outgoingdocument.regproject_data_label, Objects.Document.Viewform.Outgoingdocument.regproject_data_field, doc);

        checkfield("Подписано на бумажном носителе", Objects.Document.Viewform.Outgoingdocument.signedonpaper_label, Objects.Document.Viewform.Outgoingdocument.signedonpaper_field, doc);

        checkfield("Подписанты", Objects.Document.Viewform.Outgoingdocument.signers_label, Objects.Document.Viewform.Outgoingdocument.signers_field, doc);

        checkfield("Подписан", Objects.Document.Viewform.Outgoingdocument.signed_label, Objects.Document.Viewform.Outgoingdocument.signed_field, doc);

        checkfield("Дата подписания", Objects.Document.Viewform.Outgoingdocument.signingDate_label, Objects.Document.Viewform.Outgoingdocument.signingDate_field, doc);

        checkfield("Регистратор", Objects.Document.Viewform.Outgoingdocument.registrator_label, Objects.Document.Viewform.Outgoingdocument.registrator_field, doc);

        checkfield("Завершающий", Objects.Document.Viewform.Outgoingdocument.finishing_label, Objects.Document.Viewform.Outgoingdocument.finishing_field, doc);

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
}
