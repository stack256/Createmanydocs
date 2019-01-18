package Box;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Box.About.*;
import static Box.Base.*;

public class OrdStep {

    @Step("Создать организационно-распорядительный документ")
    static void createord(Map<String, String[]> doc, HashMap<String, HashMap<String, String[]>> items) {
        gotoarmsed();
        click("Создать", Objects.ARMSED.createButton);
        click("ОРД", Objects.ARMSED.Createmenu.orddocument);
        fillcreateord(doc, items);
        String currenturl = currentdriver().getCurrentUrl();
        click("Создать", Objects.Document.Createform.create_button, Objects.Document.Viewform.Orddocument.status_field);
        doc.put("Статус",new String[]{"Черновик"});
        doc.put("Номер",new String[]{"Не присвоено"});
    }

    @Step("Заполнить атрибуты")
    private static void fillcreateord(Map<String, String[]> doc, HashMap<String, HashMap<String, String[]>> items) {
        verifyattr("Вложения", Objects.Document.Createform.Orddocument.attachments_label);

        verifyattr("Вид документа", Objects.Document.Createform.Orddocument.type_label);
        fillfield("Вид документа", Objects.Document.Createform.Orddocument.type_button, doc.get("Вид документа"), doc);

        verifyattr("Заголовок", Objects.Document.Createform.Orddocument.title_label);
        fillfield("Заголовок", Objects.Document.Createform.Orddocument.title_field, doc.get("Заголовок"), doc);

        verifyattr("Срок исполнения", Objects.Document.Createform.Orddocument.executiondate_label);
        fillfield("Срок исполнения", Objects.Document.Createform.Orddocument.executiondate_field, doc.get("Срок исполнения"), doc);

        verifyattr("Содержание", Objects.Document.Createform.Orddocument.summarycontent_label);
        fillfield("Содержание", Objects.Document.Createform.Orddocument.summarycontent_field, doc.get("Содержание"), doc);

        verifyattr("Подписано на бумажном носителе", Objects.Document.Createform.Orddocument.signedbypaper_label);
        fillfield("Подписано на бумажном носителе", Objects.Document.Createform.Orddocument.signedbypaper_checkbox, doc.get("Подписано на бумажном носителе"), doc);

        boolean t = false;
        for (String val:doc.get("Подписано на бумажном носителе"))
            if (val.equals("Да")) t = true;
        if (t) {
            verifyattr("Подписанты", Objects.Document.Createform.Orddocument.signers_label);
            fillfield("Подписанты", Objects.Document.Createform.Orddocument.signers_button, doc.get("Подписанты"), doc);

            verifyattr("Дата подписания", Objects.Document.Createform.Orddocument.signing_date_label);
            fillfield("Дата подписания", Objects.Document.Createform.Orddocument.signing_date_field, doc.get("Дата подписания"), doc);

            doc.put("Подписан", new String[]{"Да"});
        }

        verifyattr("Контролёр", Objects.Document.Createform.Orddocument.controller_label);
        fillfield("Контролёр", Objects.Document.Createform.Orddocument.controller_button, doc.get("Контролёр"), doc);

        if (doc.get("Контролёр") != null) {
            verifyattr("Подтверждать завершение работы по документу", Objects.Document.Createform.Orddocument.confirmcompletion_label);
            fillfield("Подтверждать завершение работы по документу", Objects.Document.Createform.Orddocument.confirmcompletion_checkbox, doc.get("Подтверждать завершение работы по документу"), doc);
        }

        verifyattr("Примечание", Objects.Document.Createform.Orddocument.note_label);
        fillfield("Примечание", Objects.Document.Createform.Orddocument.note_field, doc.get("Примечание"), doc);

        verifyattr("Количество листов", Objects.Document.Createform.Orddocument.sheets_number_label);
        fillfield("Количество листов", Objects.Document.Createform.Orddocument.sheets_number_field, doc.get("Количество листов"), doc);

        verifyattr("Тематика", Objects.Document.Createform.Orddocument.subject_label);
        fillfield("Тематика", Objects.Document.Createform.Orddocument.subject_button, doc.get("Тематика"), doc);

        verifyattr("Номер дела", Objects.Document.Createform.Orddocument.file_register_label);
        fillfield("Номер дела", Objects.Document.Createform.Orddocument.file_register_button, doc.get("Номер дела"), doc);

        verifyattr("Отменяемые документы", Objects.Document.Createform.Orddocument.canceled_label);
        fillfield("Отменяемые документы", Objects.Document.Createform.Orddocument.canceled_button, doc.get("Отменяемые документы"), doc);

        verifyattr("Принимаемые документы", Objects.Document.Createform.Orddocument.accepted_label);
        fillfield("Принимаемые документы", Objects.Document.Createform.Orddocument.accepted_button, doc.get("Принимаемые документы"), doc);


        if (!items.isEmpty()){
            //метод для заполнения формы создания пунктов (которая еще не описана)
            int k = items.size();

            click("Создание", Objects.Document.Createform.Orddocument.items_button);

            for (int i=0; i<k; i++) {
                HashMap<String, String[]> item = new HashMap<String, String[]>();
                item = items.get(Integer.toString(i+1));
                verifyattr("Пункты Заголовок", Objects.Document.Createform.Orddocument.Items.title_label);
                fillfield("Пункты Заголовок", Objects.Document.Createform.Orddocument.Items.title_field, item.get("Пункты Заголовок"), item);

                verifyattr("Пункты Автор", Objects.Document.Createform.Orddocument.Items.author_label);
                //fillfield("Пункты Автор", Document.Createform.Orddocument.Items.author_button, items.get("Пункты Автор"), items);

                verifyattr("Пункты Содержание", Objects.Document.Createform.Orddocument.Items.summary_label);
                fillfield("Пункты Содержание", Objects.Document.Createform.Orddocument.Items.summary_field, item.get("Пункты Содержание"), item);

                verifyattr("Пункты Исполнитель", Objects.Document.Createform.Orddocument.Items.executor_label);
                fillfield("Пункты Исполнитель", Objects.Document.Createform.Orddocument.Items.executor_button, item.get("Пункты Исполнитель"), item);

                verifyattr("Пункты Соисполнители", Objects.Document.Createform.Orddocument.Items.coexecutors_label);
                fillfield("Пункты Соисполнители", Objects.Document.Createform.Orddocument.Items.coexecutors_button, item.get("Пункты Соисполнители"), item);

                verifyattr("Пункты Срок исполнения", Objects.Document.Createform.Orddocument.Items.limitationdate_label);
                fillfield("Пункты Срок исполнения", Objects.Document.Createform.Orddocument.Items.limitationdate_radiodate, item.get("Пункты Срок исполнения"), item);

                verifyattr("Пункты Требуется отчет", Objects.Document.Createform.Orddocument.Items.needreport_label);
                fillfield("Пункты Требуется отчет", Objects.Document.Createform.Orddocument.Items.needreport_checkbox, item.get("Пункты Требуется отчет"), item);

                verifyattr("Пункты Контролер", Objects.Document.Createform.Orddocument.Items.controller_label);
                fillfield("Пункты Контролер", Objects.Document.Createform.Orddocument.Items.controller_button, item.get("Пункты Контролер"), item);

                verifyattr("Пункты Тематика", Objects.Document.Createform.Orddocument.Items.subject_label);
                fillfield("Пункты Тематика", Objects.Document.Createform.Orddocument.Items.subject_button, item.get("Пункты Тематика"), item);
                if (i > 0)
                    click("Сохранить и создать новый", Objects.Document.Createform.Orddocument.Items.saveandcreate_button);
                else
                    click("Закрыть", Objects.Document.Createform.Orddocument.Items.close_button);
            }
        }

    }

    @Step("Проверить наличие атрибутов и их значения на форме просмотра")
    static void readord(Map<String, String[]> doc) {
        waitForLoad();
        String status = null;
        for (String val:doc.get("Статус"))
            status = val;
        waitelement(Objects.Document.Viewform.Orddocument.status_field);
        if (!currentdriver().findElement(By.xpath(Objects.Document.Viewform.Orddocument.status_field)).getText().equals(status)){
            currentdriver().get(currentdriver().getCurrentUrl());
        }
        currentdriver().get(currentdriver().getCurrentUrl());

        String title = docgettitle();

        checkfield("Номер", Objects.Document.Viewform.Orddocument.regnum_label, Objects.Document.Viewform.Orddocument.regnum_field, doc);

        checkfield("Дата регистрации", Objects.Document.Viewform.Orddocument.reg_data_label, Objects.Document.Viewform.Orddocument.reg_data_field, doc);

        checkfield("Вид документа", Objects.Document.Viewform.Orddocument.type_label, Objects.Document.Viewform.Orddocument.type_field, doc);

        checkfield("Заголовок", Objects.Document.Viewform.Orddocument.title_label, Objects.Document.Viewform.Orddocument.title_field, doc);

        checkfield("Срок исполнения", Objects.Document.Viewform.Orddocument.executiondate_field, Objects.Document.Viewform.Orddocument.executiondate_field, doc);

        checkfield("Содержание", Objects.Document.Viewform.Orddocument.summary_label, Objects.Document.Viewform.Orddocument.summary_field, doc);

        checkfield("Контролёр", Objects.Document.Viewform.Orddocument.controller_label, Objects.Document.Viewform.Orddocument.controller_field, doc);

        checkfield("Примечание", Objects.Document.Viewform.Orddocument.note_label, Objects.Document.Viewform.Orddocument.note_field, doc);

        checkfield("Количество листов", Objects.Document.Viewform.Orddocument.sheets_number_label, Objects.Document.Viewform.Orddocument.sheets_number_field, doc);

        checkfield("Тематика", Objects.Document.Viewform.Orddocument.subject_label, Objects.Document.Viewform.Orddocument.subject_field, doc);

        checkfield("Номер дела", Objects.Document.Viewform.Orddocument.file_register_label, Objects.Document.Viewform.Orddocument.file_register_field, doc);

        checkfield("Отменяемые документы", Objects.Document.Viewform.Orddocument.canceled_label, Objects.Document.Viewform.Orddocument.canceled_field, doc);

        checkfield("Принимаемые документы", Objects.Document.Viewform.Orddocument.accepted_label, Objects.Document.Viewform.Orddocument.accepted_field, doc);

        checkfield("Составитель", Objects.Document.Viewform.Orddocument.author_label, Objects.Document.Viewform.Orddocument.author_field, doc);

        checkfield("Исполнитель", Objects.Document.Viewform.Orddocument.executor_label, Objects.Document.Viewform.Orddocument.executor_field, doc);

        checkfield("Номер проекта", Objects.Document.Viewform.Orddocument.regnumproject_label, Objects.Document.Viewform.Orddocument.regnumproject_field, doc);

        checkfield("Дата регистрации проекта", Objects.Document.Viewform.Orddocument.regproject_data_label, Objects.Document.Viewform.Orddocument.regproject_data_field, doc);

        checkfield("Подписано на бумажном носителе", Objects.Document.Viewform.Orddocument.signedonpaper_label, Objects.Document.Viewform.Orddocument.signedonpaper_field, doc);

        checkfield("Подписанты", Objects.Document.Viewform.Orddocument.signers_label, Objects.Document.Viewform.Orddocument.signers_field, doc);

        checkfield("Подписан", Objects.Document.Viewform.Orddocument.signed_label, Objects.Document.Viewform.Orddocument.signed_field, doc);

        checkfield("Дата подписания", Objects.Document.Viewform.Orddocument.signingDate_label, Objects.Document.Viewform.Orddocument.signingDate_field, doc);

        checkfield("Регистратор", Objects.Document.Viewform.Orddocument.registrator_label, Objects.Document.Viewform.Orddocument.registrator_field, doc);


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
