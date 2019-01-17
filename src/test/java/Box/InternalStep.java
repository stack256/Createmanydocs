package Box;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Map;

import static Box.About.*;
import static Box.Base.*;
import static Box.Users.getuserbylogin;

public class InternalStep {

    @Step("Создать внутренний документ")
    static void createinternal(Map<String, String[]> doc, boolean... flag) {
        if (flag.length == 0) {
            gotoarmsed();
            click("Создать", Objects.ARMSED.createButton);
            click("Внутренний документ", Objects.ARMSED.Createmenu.internaldocument);
            fillcreateinternal(doc);
            doc.put("Исполнитель", new String[]{getuserbylogin(current_login).full});
        }
        String currenturl = driver.getCurrentUrl();
        click("Создать", Objects.Document.Createform.create_button, Objects.Document.Viewform.Internaldocument.status_field);
        doc.put("Составитель", new String[]{getuserbylogin(current_login).full});
        doc.put("Статус",new String[]{"Черновик"});
        doc.put("Номер",new String[]{"Не присвоено"});
    }

    @Step("Заполнить атрибуты")
    static void fillcreateinternal(Map<String, String[]> doc) {
        verifyattr("Вложения", Objects.Document.Createform.Internaldocument.attachments_label);

        verifyattr("Заголовок", Objects.Document.Createform.Internaldocument.title_label);
        fillfield("Заголовок", Objects.Document.Createform.Internaldocument.title_field, doc.get("Заголовок"), doc);

        verifyattr("Вид документа", Objects.Document.Createform.Internaldocument.type_label);
        fillfield("Вид документа", Objects.Document.Createform.Internaldocument.type_button, doc.get("Вид документа"), doc);

        verifyattr("Срок ответа", Objects.Document.Createform.Internaldocument.execution_date_label);
        fillfield("Срок ответа", Objects.Document.Createform.Internaldocument.execution_date_field, doc.get("Срок ответа"), doc);

        verifyattr("Получатель", Objects.Document.Createform.Internaldocument.recipient_label);
        fillfield("Получатель", Objects.Document.Createform.Internaldocument.recipient_button, doc.get("Получатель"), doc);

        verifyattr("Содержание", Objects.Document.Createform.Internaldocument.summarycontent_label);
        fillfield("Содержание", Objects.Document.Createform.Internaldocument.summarycontent_field, doc.get("Содержание"), doc);

        verifyattr("Подписано на бумажном носителе", Objects.Document.Createform.Internaldocument.signedbypaper_label);
        fillfield("Подписано на бумажном носителе", Objects.Document.Createform.Internaldocument.signedbypaper_checkbox, doc.get("Подписано на бумажном носителе"), doc);

        boolean t = false;
        for (String val:doc.get("Подписано на бумажном носителе"))
            if (val.equals("Да")) t = true;
        if (t) {
            verifyattr("Подписанты", Objects.Document.Createform.Internaldocument.signers_label);
            fillfield("Подписанты", Objects.Document.Createform.Internaldocument.signers_button, doc.get("Подписанты"), doc);

            verifyattr("Дата подписания", Objects.Document.Createform.Internaldocument.signing_date_label);
            fillfield("Дата подписания", Objects.Document.Createform.Internaldocument.signing_date_field, doc.get("Дата подписания"), doc);

            doc.put("Подписан", new String[]{"Да"});
        }

        verifyattr("В ответ на", Objects.Document.Createform.Internaldocument.response_to_label);
        fillfield("В ответ на", Objects.Document.Createform.Internaldocument.response_to_button, doc.get("В ответ на"), doc);

        verifyattr("Количество листов", Objects.Document.Createform.Internaldocument.sheets_number_label);
        fillfield("Количество листов", Objects.Document.Createform.Internaldocument.sheets_number_field, doc.get("Количество листов"), doc);

        verifyattr("Тематика", Objects.Document.Createform.Internaldocument.subject_label);
        fillfield("Тематика", Objects.Document.Createform.Internaldocument.subject_button, doc.get("Тематика"), doc);

        verifyattr("Номер дела", Objects.Document.Createform.Internaldocument.file_register_label);
        fillfield("Номер дела", Objects.Document.Createform.Internaldocument.file_register_button, doc.get("Номер дела"), doc);

        verifyattr("Примечание", Objects.Document.Createform.Internaldocument.note_label);
        fillfield("Примечание", Objects.Document.Createform.Internaldocument.note_field, doc.get("Примечание"), doc);

    }

    @Step("Проверить наличие атрибутов и их значения на форме просмотра")
    static void readinternal(Map<String, String[]> doc) {
        waitForLoad();
        String status = null;
        for (String val:doc.get("Статус"))
            status = val;
        waitelement(Objects.Document.Viewform.Internaldocument.status_field);
        if (!driver.findElement(By.xpath(Objects.Document.Viewform.Internaldocument.status_field)).getText().equals(status)){
            driver.get(driver.getCurrentUrl());
        }

        String title = docgettitle();
        doc.put("Номер",new String[]{title.substring(title.indexOf(" № ")+3,title.indexOf(" от "))});
        if (status.equals("Проект"))
            doc.put("Номер проекта",doc.get("Номер"));

        checkfield("Номер", Objects.Document.Viewform.Internaldocument.regnum_label, Objects.Document.Viewform.Internaldocument.regnum_field, doc);

        checkfield("Дата регистрации", Objects.Document.Viewform.Internaldocument.reg_data_label, Objects.Document.Viewform.Internaldocument.reg_data_field, doc);

        checkfield("Составитель", Objects.Document.Viewform.Internaldocument.author_label, Objects.Document.Viewform.Internaldocument.author_field, doc);

        checkfield("Исполнитель", Objects.Document.Viewform.Internaldocument.executor_label, Objects.Document.Viewform.Internaldocument.executor_field, doc);

        checkfield("Заголовок", Objects.Document.Viewform.Internaldocument.title_label, Objects.Document.Viewform.Internaldocument.title_field, doc);

        checkfield("Вид документа", Objects.Document.Viewform.Internaldocument.type_label, Objects.Document.Viewform.Internaldocument.type_field, doc);

        checkfield("Срок ответа", Objects.Document.Viewform.Internaldocument.response_date_label, Objects.Document.Viewform.Internaldocument.response_date_field, doc);

        checkfield("Получатель", Objects.Document.Viewform.Internaldocument.recipient_label, Objects.Document.Viewform.Internaldocument.recipient_field, doc);

        checkfield("Содержание", Objects.Document.Viewform.Internaldocument.summary_label, Objects.Document.Viewform.Internaldocument.summary_field, doc);

        checkfield("В ответ на", Objects.Document.Viewform.Internaldocument.responseto_label, Objects.Document.Viewform.Internaldocument.responseto_field, doc);

        checkfield("Количество листов", Objects.Document.Viewform.Internaldocument.sheets_number_label, Objects.Document.Viewform.Internaldocument.sheets_number_field, doc);

        checkfield("Тематика", Objects.Document.Viewform.Internaldocument.subject_label, Objects.Document.Viewform.Internaldocument.subject_field, doc);

        checkfield("Номер дела", Objects.Document.Viewform.Internaldocument.file_register_label, Objects.Document.Viewform.Internaldocument.file_register_field, doc);

        checkfield("Примечание", Objects.Document.Viewform.Internaldocument.note_label, Objects.Document.Viewform.Internaldocument.note_field, doc);

        checkfield("Номер проекта", Objects.Document.Viewform.Internaldocument.regnumproject_label, Objects.Document.Viewform.Internaldocument.regnumproject_field, doc);

        checkfield("Дата регистрации проекта", Objects.Document.Viewform.Internaldocument.regproject_data_label, Objects.Document.Viewform.Internaldocument.regproject_data_field, doc);

        checkfield("Подписано на бумажном носителе", Objects.Document.Viewform.Internaldocument.signedonpaper_label, Objects.Document.Viewform.Internaldocument.signedonpaper_field, doc);

        checkfield("Подписанты", Objects.Document.Viewform.Internaldocument.signers_label, Objects.Document.Viewform.Internaldocument.signers_field, doc);

        checkfield("Подписан", Objects.Document.Viewform.Internaldocument.signed_label, Objects.Document.Viewform.Internaldocument.signed_field, doc);

        checkfield("Дата подписания", Objects.Document.Viewform.Internaldocument.signingDate_label, Objects.Document.Viewform.Internaldocument.signingDate_field, doc);

        checkfield("Регистратор", Objects.Document.Viewform.Internaldocument.registrator_label, Objects.Document.Viewform.Internaldocument.registrator_field, doc);

        String currenturl = driver.getCurrentUrl();
        if (currenturl.contains("#")){
            currenturl = currenturl.substring(0,currenturl.indexOf('#'));
        }
        if (!removedoc.contains(currenturl))
            removedoc.add(currenturl);
    }
}
