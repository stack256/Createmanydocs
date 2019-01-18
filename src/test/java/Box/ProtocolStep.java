package Box;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Box.About.*;
import static Box.Base.*;

public class ProtocolStep {

    @Step("Создать протокол")
    static void createprotocol(Map<String, String[]> doc, HashMap<String, HashMap<String, String[]>> items) {
        gotoarmsed();
        click("Создать", Objects.ARMSED.createButton);
        click("Протокол", Objects.ARMSED.Createmenu.protocoldocument);
        fillcreateprotocol(doc, items);
        String currenturl = currentdriver().getCurrentUrl();
        click("Создать", Objects.Document.Createform.create_button, Objects.Document.Viewform.Protocoldocument.status_field);
        doc.put("Статус",new String[]{"Черновик"});
        doc.put("Номер",new String[]{"Не присвоено"});
        doc.put("Дата",new String[]{currentdate()});
    }

    @Step("Заполнить атрибуты")
    private static void fillcreateprotocol(Map<String, String[]> doc, HashMap<String, HashMap<String, String[]>> items) {
        verifyattr("Вложения", Objects.Document.Createform.Protocoldocument.attachments_label);

        verifyattr("Заголовок", Objects.Document.Createform.Protocoldocument.title_label);
        fillfield("Заголовок", Objects.Document.Createform.Protocoldocument.title_field, doc.get("Заголовок"), doc);

        verifyattr("Вид документа", Objects.Document.Createform.Protocoldocument.type_label);
        fillfield("Вид документа", Objects.Document.Createform.Protocoldocument.type_button, doc.get("Вид документа"), doc);

        verifyattr("Срок исполнения", Objects.Document.Createform.Protocoldocument.executiondate_label);
        fillfield("Срок исполнения", Objects.Document.Createform.Protocoldocument.executiondate_field, doc.get("Срок исполнения"), doc);

        verifyattr("Содержание", Objects.Document.Createform.Protocoldocument.summarycontent_label);
        fillfield("Содержание", Objects.Document.Createform.Protocoldocument.summarycontent_field, doc.get("Содержание"), doc);

        verifyattr("Согласующие", Objects.Document.Createform.Protocoldocument.signers_label);
        fillfield("Согласующие", Objects.Document.Createform.Protocoldocument.signers_button, doc.get("Согласующие"), doc);

        verifyattr("Председатель совещания", Objects.Document.Createform.Protocoldocument.chairman_label);
        fillfield("Председатель совещания", Objects.Document.Createform.Protocoldocument.chairman_button, doc.get("Председатель совещания"), doc);

        verifyattr("Секретарь", Objects.Document.Createform.Protocoldocument.secretary_label);
        fillfield("Секретарь", Objects.Document.Createform.Protocoldocument.secretary_button, doc.get("Секретарь"), doc);

        verifyattr("Присутствовали", Objects.Document.Createform.Protocoldocument.attended_label);
        fillfield("Присутствовали", Objects.Document.Createform.Protocoldocument.attended_button, doc.get("Присутствовали"), doc);

        verifyattr("Подписано на бумажном носителе", Objects.Document.Createform.Protocoldocument.signedbypaper_label);
        fillfield("Подписано на бумажном носителе", Objects.Document.Createform.Protocoldocument.signedbypaper_checkbox, doc.get("Подписано на бумажном носителе"), doc);

        verifyattr("Примечание", Objects.Document.Createform.Protocoldocument.note_label);
        fillfield("Примечание", Objects.Document.Createform.Protocoldocument.note_field, doc.get("Примечание"), doc);

        verifyattr("Количество листов", Objects.Document.Createform.Protocoldocument.sheets_number_label);
        fillfield("Количество листов", Objects.Document.Createform.Protocoldocument.sheets_number_field, doc.get("Количество листов"), doc);

        verifyattr("Тематика", Objects.Document.Createform.Protocoldocument.subject_label);
        fillfield("Тематика", Objects.Document.Createform.Protocoldocument.subject_button, doc.get("Тематика"), doc);

        verifyattr("Номер дела", Objects.Document.Createform.Protocoldocument.file_register_label);
        fillfield("Номер дела", Objects.Document.Createform.Protocoldocument.file_register_button, doc.get("Номер дела"), doc);

        verifyattr("Автосоздание поручений", Objects.Document.Createform.Protocoldocument.autocreation_label);
        fillfield("Автосоздание поручений", Objects.Document.Createform.Protocoldocument.autocreation_checkbox, doc.get("Автосоздание поручений"), doc);


        if (!items.isEmpty()){
            //метод для заполнения формы создания пунктов (которая еще не описана)
            click("Создание", Objects.Document.Createform.Protocoldocument.additem_button);
            additemsprotocol(items);
        }
    }

    @Step("Добавить пункты")
    private static void additemsprotocol(HashMap<String, HashMap<String, String[]>> items) {
        int k = items.size();
        for (int i=0; i<k; i++) {
            if (i!=0)
                click("Сохранить и создать новый", Objects.Document.Createform.Protocoldocument.Items.saveandcreate_button);
            HashMap<String, String[]> item = new HashMap<String, String[]>();
            item = items.get(Integer.toString(i+1));
            fillitemprotocol(i,item);
        }
        click("Закрыть", Objects.Document.Createform.Protocoldocument.Items.close_button);
    }

    @Step("Заполнить пункт {0}")
    private static void fillitemprotocol(Integer i, HashMap<String, String[]> item) {
        verifyattr("Пункт Формулировка", "(" + Objects.Document.Createform.Protocoldocument.Items.formulation_label + ")[" + Integer.toString(i+1) + "]");
        fillfield("Пункт Формулировка", "(" + Objects.Document.Createform.Protocoldocument.Items.formulation_field + ")[" + Integer.toString(i+1) + "]", item.get("Пункт Формулировка"), item);

        verifyattr("Пункт Докладчик", "(" + Objects.Document.Createform.Protocoldocument.Items.reporter_label + ")[" + Integer.toString(i+1) + "]");
        fillfield("Пункт Докладчик", "(" + Objects.Document.Createform.Protocoldocument.Items.reporter_button + ")[" + Integer.toString(i+1) + "]", item.get("Пункт Докладчик"), item);

        verifyattr("Пункт Содокладчики", "(" + Objects.Document.Createform.Protocoldocument.Items.coreporter_label + ")[" + Integer.toString(i+1) + "]");
        fillfield("Пункт Содокладчики", "(" + Objects.Document.Createform.Protocoldocument.Items.coreporter_button + ")[" + Integer.toString(i+1) + "]", item.get("Пункт Содокладчики"), item);

        verifyattr("Пункт Описание", "(" + Objects.Document.Createform.Protocoldocument.Items.point_label + ")[" + Integer.toString(i+1) + "]");
        fillfield("Пункт Описание", "(" + Objects.Document.Createform.Protocoldocument.Items.point_field + ")[" + Integer.toString(i+1) + "]", item.get("Пункт Описание"), item);

        verifyattr("Пункт Выступили", "(" + Objects.Document.Createform.Protocoldocument.Items.menspoke_label + ")[" + Integer.toString(i+1) + "]");
        fillfield("Пункт Выступили", "(" + Objects.Document.Createform.Protocoldocument.Items.menspoke_field + ")[" + Integer.toString(i+1) + "]", item.get("Пункт Выступили"), item);

        verifyattr("Пункт Решение", "(" + Objects.Document.Createform.Protocoldocument.Items.decision_label + ")[" + Integer.toString(i+1) + "]");
        fillfield("Пункт Решение", "(" + Objects.Document.Createform.Protocoldocument.Items.decision_field + ")[" + Integer.toString(i+1) + "]", item.get("Пункт Решение"), item);

        verifyattr("Пункт Исполнитель", "(" + Objects.Document.Createform.Protocoldocument.Items.executor_label + ")[" + Integer.toString(i+1) + "]");
        fillfield("Пункт Исполнитель", "(" + Objects.Document.Createform.Protocoldocument.Items.executor_button+ ")[" + Integer.toString(i+1) + "]", item.get("Пункт Исполнитель"), item);

        verifyattr("Пункт Срок исполнения", "(" + Objects.Document.Createform.Protocoldocument.Items.executiondate_label + ")[" + Integer.toString(i+1) + "]");
        fillfield("Пункт Срок исполнения", "(" + Objects.Document.Createform.Protocoldocument.Items.executiondate_field + ")[" + Integer.toString(i+1) + "]", item.get("Пункт Срок исполнения"), item);

        verifyattr("Пункт Примечание", "(" + Objects.Document.Createform.Protocoldocument.Items.note_label + ")[" + Integer.toString(i+1) + "]");
        fillfield("Пункт Примечание", "(" + Objects.Document.Createform.Protocoldocument.Items.note_field + ")[" + Integer.toString(i+1) + "]", item.get("Пункт Примечание"), item);
    }

    @Step("Проверить наличие атрибутов и их значения на форме просмотра")
    static void readprotocol(Map<String, String[]> doc) {
        waitForLoad();
        String status = null;
        for (String val:doc.get("Статус"))
            status = val;
        waitelement(Objects.Document.Viewform.Resolutionsdocument.status_field);
        if (!currentdriver().findElement(By.xpath(Objects.Document.Viewform.Protocoldocument.status_field)).getText().equals(status)){
            currentdriver().get(currentdriver().getCurrentUrl());
        }
        currentdriver().get(currentdriver().getCurrentUrl());


        String title = docgettitle();
        doc.put("Номер",new String[]{title.substring(title.indexOf("№ ")+2,title.indexOf(" от "))});

        checkfield("Заголовок", Objects.Document.Viewform.Protocoldocument.title_label, Objects.Document.Viewform.Protocoldocument.title_field, doc);

        checkfield("Вид документа", Objects.Document.Viewform.Protocoldocument.type_label, Objects.Document.Viewform.Protocoldocument.type_field, doc);

        checkfield("Срок исполнения", Objects.Document.Viewform.Protocoldocument.executiondate_label, Objects.Document.Viewform.Protocoldocument.executiondate_field, doc);

        checkfield("Содержание", Objects.Document.Viewform.Protocoldocument.summary_label, Objects.Document.Viewform.Protocoldocument.summary_field, doc);

        checkfield("Председатель совещания", Objects.Document.Viewform.Protocoldocument.chairman_label, Objects.Document.Viewform.Protocoldocument.chairman_field, doc);

        checkfield("Секретарь", Objects.Document.Viewform.Protocoldocument.secretary_label, Objects.Document.Viewform.Protocoldocument.secretary_field, doc);

        checkfield("Присутствовали", Objects.Document.Viewform.Protocoldocument.attended_label, Objects.Document.Viewform.Protocoldocument.attended_field, doc);

        checkfield("Подписано на бумажном носителе", Objects.Document.Viewform.Protocoldocument.signedonpaper_label, Objects.Document.Viewform.Protocoldocument.signedonpaper_field, doc);

        checkfield("Примечание", Objects.Document.Viewform.Protocoldocument.note_label, Objects.Document.Viewform.Protocoldocument.note_field, doc);

        checkfield("Количество листов", Objects.Document.Viewform.Protocoldocument.sheetsnumber_label, Objects.Document.Viewform.Protocoldocument.sheetsnumber_field, doc);

        checkfield("Тематика", Objects.Document.Viewform.Protocoldocument.subject_label, Objects.Document.Viewform.Protocoldocument.subject_field, doc);

        checkfield("Номер дела", Objects.Document.Viewform.Protocoldocument.fileregister_label, Objects.Document.Viewform.Protocoldocument.fileregister_field, doc);

        checkfield("Автосоздание поручений", Objects.Document.Viewform.Protocoldocument.autocreation_label, Objects.Document.Viewform.Protocoldocument.autocreation_field, doc);

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
