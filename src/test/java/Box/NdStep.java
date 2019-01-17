package Box;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Map;

import static Box.About.*;
import static Box.Base.*;

public class NdStep {

    @Step("Создать нормативный документ")
    static void creatend(Map<String, String[]> doc) {
        gotoarmsed();
        click("Создать", Objects.ARMSED.createButton);
        click("НД", Objects.ARMSED.Createmenu.nddocument);
        fillcreatend(doc);
        String currenturl = driver.getCurrentUrl();
        click("Создать", Objects.Document.Createform.create_button, Objects.Document.Viewform.Nddocument.status_field);
        doc.put("Статус",new String[]{"Черновик"});
        doc.put("Номер",new String[]{"Не присвоено"});
    }

    @Step("Заполнить атрибуты")
    private static void fillcreatend(Map<String, String[]> doc) {
        verifyattr("Вложения", Objects.Document.Createform.Nddocument.attachments_label);

        verifyattr("Заголовок", Objects.Document.Createform.Nddocument.title_label);
        fillfield("Заголовок", Objects.Document.Createform.Nddocument.title_field, doc.get("Заголовок"), doc);

        verifyattr("Вид документа", Objects.Document.Createform.Nddocument.type_label);
        fillfield("Вид документа", Objects.Document.Createform.Nddocument.type_button, doc.get("Вид документа"), doc);

        verifyattr("Содержание", Objects.Document.Createform.Nddocument.summarycontent_label);
        fillfield("Содержание", Objects.Document.Createform.Nddocument.summarycontent_field, doc.get("Содержание"), doc);

        verifyattr("Количество листов", Objects.Document.Createform.Nddocument.sheets_number_label);
        fillfield("Количество листов", Objects.Document.Createform.Nddocument.sheets_number_field, doc.get("Количество листов"), doc);

        verifyattr("Подписано на бумажном носителе", Objects.Document.Createform.Nddocument.signedbypaper_label);
        fillfield("Подписано на бумажном носителе", Objects.Document.Createform.Nddocument.signedbypaper_checkbox, doc.get("Подписано на бумажном носителе"), doc);

        boolean t = false;
        for (String val:doc.get("Подписано на бумажном носителе"))
            if (val.equals("Да")) t = true;
        if (t) {
            verifyattr("Подписанты", Objects.Document.Createform.Nddocument.signers_label);
            fillfield("Подписанты", Objects.Document.Createform.Nddocument.signers_button, doc.get("Подписанты"), doc);

            verifyattr("Дата подписания", Objects.Document.Createform.Nddocument.signing_date_label);
            fillfield("Дата подписания", Objects.Document.Createform.Nddocument.signing_date_field, doc.get("Дата подписания"), doc);

            doc.put("Подписан", new String[]{"Да"});
        }

        verifyattr("Номер дела", Objects.Document.Createform.Nddocument.file_register_label);
        fillfield("Номер дела", Objects.Document.Createform.Nddocument.file_register_button, doc.get("Номер дела"), doc);

        verifyattr("Примечание", Objects.Document.Createform.Nddocument.note_label);
        fillfield("Примечание", Objects.Document.Createform.Nddocument.note_field, doc.get("Примечание"), doc);

        verifyattr("Тематика", Objects.Document.Createform.Nddocument.subject_label);
        fillfield("Тематика", Objects.Document.Createform.Nddocument.subject_button, doc.get("Тематика"), doc);

        verifyattr("Подразделения", Objects.Document.Createform.Nddocument.organizationunit_label);
        fillfield("Подразделения", Objects.Document.Createform.Nddocument.organizationunit_button, doc.get("Подразделения"), doc);

        verifyattr("Бессрочный", Objects.Document.Createform.Nddocument.unlimited_label);
        fillfield("Бессрочный", Objects.Document.Createform.Nddocument.unlimited_checkbox, doc.get("Бессрочный"), doc);

        verifyattr("Период действия", Objects.Document.Createform.Nddocument.daterange_label);
        fillfield("Период действия С", Objects.Document.Createform.Nddocument.daterangestart_field, doc.get("Период действия С"), doc);
        fillfield("Период действия По", Objects.Document.Createform.Nddocument.daterangeend_field, doc.get("Период действия По"), doc);

    }

    @Step("Проверить наличие атрибутов и их значения на форме просмотра")
    static void readnd(Map<String, String[]> doc) {
        waitForLoad();
        String status = null;
        for (String val:doc.get("Статус"))
            status = val;
        waitelement(Objects.Document.Viewform.Nddocument.status_field);
        if (!driver.findElement(By.xpath(Objects.Document.Viewform.Nddocument.status_field)).getText().equals(status)){
            driver.get(driver.getCurrentUrl());
        }

        String title = docgettitle();
        checkfield("Номер", Objects.Document.Viewform.Nddocument.regnum_label, Objects.Document.Viewform.Nddocument.regnum_field, doc);

        checkfield("Дата регистрации", Objects.Document.Viewform.Nddocument.reg_data_label, Objects.Document.Viewform.Nddocument.reg_data_field, doc);

        checkfield("Заголовок", Objects.Document.Viewform.Nddocument.title_label, Objects.Document.Viewform.Nddocument.title_field, doc);

        checkfield("Вид документа", Objects.Document.Viewform.Nddocument.type_label, Objects.Document.Viewform.Nddocument.type_field, doc);

        checkfield("Содержание", Objects.Document.Viewform.Nddocument.summary_label, Objects.Document.Viewform.Nddocument.summary_field, doc);

        checkfield("Количество листов", Objects.Document.Viewform.Nddocument.sheets_number_label, Objects.Document.Viewform.Nddocument.sheets_number_field, doc);

        checkfield("Подписано на бумажном носителе", Objects.Document.Viewform.Nddocument.signedonpaper_label, Objects.Document.Viewform.Nddocument.signedonpaper_field, doc);

        checkfield("Подписанты", Objects.Document.Viewform.Nddocument.signers_label, Objects.Document.Viewform.Nddocument.signers_field, doc);

        checkfield("Дата подписания", Objects.Document.Viewform.Nddocument.signingDate_label, Objects.Document.Viewform.Nddocument.signingDate_field, doc);

        checkfield("Подписан", Objects.Document.Viewform.Nddocument.signed_label, Objects.Document.Viewform.Nddocument.signed_field, doc);

        checkfield("Номер дела", Objects.Document.Viewform.Nddocument.file_register_label, Objects.Document.Viewform.Nddocument.file_register_field, doc);

        checkfield("Примечание", Objects.Document.Viewform.Nddocument.note_label, Objects.Document.Viewform.Nddocument.note_field, doc);

        checkfield("Тематика", Objects.Document.Viewform.Nddocument.subject_label, Objects.Document.Viewform.Nddocument.subject_field, doc);

        checkfield("Подразделения", Objects.Document.Viewform.Nddocument.organizationunit_label, Objects.Document.Viewform.Nddocument.organizationunit_field, doc);

        checkfield("Бессрочный", Objects.Document.Viewform.Nddocument.unlimited_label, Objects.Document.Viewform.Nddocument.unlimited_field, doc);

        checkfield("Период действия С", Objects.Document.Viewform.Nddocument.daterange_label, Objects.Document.Viewform.Nddocument.daterangestart_field, doc);

        checkfield("Период действия По", Objects.Document.Viewform.Nddocument.daterange_label, Objects.Document.Viewform.Nddocument.daterangeend_field, doc);

        checkfield("Составитель", Objects.Document.Viewform.Nddocument.author_label, Objects.Document.Viewform.Nddocument.author_field, doc);

        checkfield("Исполнитель", Objects.Document.Viewform.Nddocument.executor_label, Objects.Document.Viewform.Nddocument.executor_field, doc);

        checkfield("Номер проекта", Objects.Document.Viewform.Nddocument.regnumproject_label, Objects.Document.Viewform.Nddocument.regnumproject_field, doc);

        checkfield("Дата регистрации проекта", Objects.Document.Viewform.Nddocument.regproject_data_label, Objects.Document.Viewform.Nddocument.regproject_data_field, doc);

        checkfield("Регистратор", Objects.Document.Viewform.Nddocument.registrator_label, Objects.Document.Viewform.Nddocument.registrator_field, doc);


        String currenturl = driver.getCurrentUrl();
        if (currenturl.contains("#")){
            currenturl = currenturl.substring(0,currenturl.indexOf('#'));
        }
        if (!removedoc.contains(currenturl))
            removedoc.add(currenturl);
    }
}
