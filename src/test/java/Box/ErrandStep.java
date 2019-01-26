package Box;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static Box.About.*;
import static Box.Base.*;
import static Box.Users.getuserbylogin;

public class ErrandStep {

    @Step("Создать поручение")
    static void createerrand(Map<String, String[]> doc, String button) {
        gotoarmsed();
        click("Создать", Objects.ARMSED.createButton);
        click("Поручение", Objects.ARMSED.Createmenu.erranddocument);
        fillcreateerrand(doc);
        String currenturl = currentdriver().getCurrentUrl();
        if (button.equals("Сохранить черновик")) {
            click("Сохранить черновик", Objects.Document.Createform.Erranddocument.save_button, Objects.Document.Viewform.Erranddocument.status_field);
            doc.put("Статус", new String[]{"Черновик"});
        }
        else
        if (button.equals("Направить")) {
            click("Направить", Objects.Document.Createform.Erranddocument.default_button, Objects.Document.Viewform.Erranddocument.status_field);
            doc.put("Статус",new String[]{"Ожидает исполнения"});
        }
        else hardassertfail(button + " - Нет такой кнопки на форме создания поручения");

        if (doc.get("Автор") == null)
            doc.put("Автор", new String[]{getuserbylogin(currentcurrent_login()).full});
        if (doc.get("Создатель") == null)
            doc.put("Создатель", new String[]{getuserbylogin(currentcurrent_login()).full});
    }

    @Step("Заполнить атрибуты")
    private static void fillcreateerrand(Map<String, String[]> doc) {
        verifyattr("Тип поручения", Objects.Document.Createform.Erranddocument.type_label);
        fillfield("Тип поручения", Objects.Document.Createform.Erranddocument.type_button, doc.get("Тип поручения"), doc);

        verifyattr("Заголовок", Objects.Document.Createform.Erranddocument.title_label);
        fillfield("Заголовок", Objects.Document.Createform.Erranddocument.title_button, doc.get("Заголовок"), doc);

        verifyattr("Текст поручения", Objects.Document.Createform.Erranddocument.content_label);
        fillfield("Текст поручения", Objects.Document.Createform.Erranddocument.content_field, doc.get("Текст поручения"), doc);

        verifyattr("Исполнитель", Objects.Document.Createform.Erranddocument.executor_label);
        fillfield("Исполнитель", Objects.Document.Createform.Erranddocument.executor_button, doc.get("Исполнитель"), doc);

        verifyattr("Соисполнители", Objects.Document.Createform.Erranddocument.coexecutor_label);
        fillfield("Соисполнители", Objects.Document.Createform.Erranddocument.coexecutor_button, doc.get("Соисполнители"), doc);

        verifyattr("Срок исполнения", Objects.Document.Createform.Erranddocument.limitationdate_label);
        fillfield("Срок исполнения", Objects.Document.Createform.Erranddocument.limitationdate_radiodate, doc.get("Срок исполнения"), doc);

        verifyattr("Вложения", Objects.Document.Createform.Erranddocument.attachments_label);

        if (doc.get("Направлять периодически") != null || doc.get("Контролер") != null || doc.get("Требуется отчет") != null ||
                doc.get("Важное") != null || doc.get("Тематика") != null){
            click("Расширенное поручение", Objects.Document.Createform.Erranddocument.expand_button);

            verifyattr("Направлять периодически", Objects.Document.Createform.Erranddocument.periodically_label);
            fillfield("Направлять периодически", Objects.Document.Createform.Erranddocument.periodically_checkbox, doc.get("Направлять периодически"), doc);

            boolean t = false;
            for (String val:doc.get("Направлять периодически"))
                if (val.equals("Да")) t = true;
            if (t) {
                verifyattr("Повторять", Objects.Document.Createform.Erranddocument.reiterationrule_label);
                fillfield("Повторять", Objects.Document.Createform.Erranddocument.reiterationrule_button, doc.get("Повторять"), doc);

                verifyattr("Начало повторений", Objects.Document.Createform.Erranddocument.periodstart_label);
                fillfield("Начало повторений", Objects.Document.Createform.Erranddocument.periodstart_field, doc.get("Начало повторений"), doc);

                verifyattr("Окончание повторений", Objects.Document.Createform.Erranddocument.periodend_label);
                fillfield("Окончание повторений", Objects.Document.Createform.Erranddocument.periodend_radiodate, doc.get("Окончание повторений"), doc);
            }

            verifyattr("Контролер", Objects.Document.Createform.Erranddocument.controller_label);
            fillfield("Контролер", Objects.Document.Createform.Erranddocument.controller_button, doc.get("Контролер"), doc);

            verifyattr("Требуется отчет", Objects.Document.Createform.Erranddocument.needreport_label);
            fillfield("Требуется отчет", Objects.Document.Createform.Erranddocument.needreport_checkbox, doc.get("Требуется отчет"), doc);

            t = false;
            for (String val:doc.get("Требуется отчет"))
                if (val.equals("Да")) t = true;
            if (t) {
                verifyattr("Получатель отчета", Objects.Document.Createform.Erranddocument.reportrecipient_label);
                fillfield("Получатель отчета", Objects.Document.Createform.Erranddocument.reportrecipient_select, doc.get("Получатель отчета"), doc);
            }

            verifyattr("Важное", Objects.Document.Createform.Erranddocument.important_label);
            fillfield("Важное", Objects.Document.Createform.Erranddocument.important_checkbox, doc.get("Важное"), doc);

            verifyattr("Тематика", Objects.Document.Createform.Erranddocument.subject_label);
            fillfield("Тематика", Objects.Document.Createform.Erranddocument.subject_button, doc.get("Тематика"), doc);

        }
    }

    @Step("Проверить наличие атрибутов и их значения на форме просмотра")
    static void readerrand(Map<String, String[]> doc) {
        waitForLoad();
        String status = null;
        for (String val:doc.get("Статус"))
            status = val;
        waitelement(Objects.Document.Viewform.Erranddocument.status_field);
        if (!currentdriver().findElement(By.xpath(Objects.Document.Viewform.Erranddocument.status_field)).getText().equals(status)){
            currentdriver().get(currentdriver().getCurrentUrl());
        }
        currentdriver().get(currentdriver().getCurrentUrl());


        String title = docgettitle();
        doc.put("Номер",new String[]{title.substring(2,title.indexOf(","))});
        doc.put("Срок исполнения", new String[]{title.substring(title.indexOf("срок:")+6,title.length())});

        checkfield("Документ-основание", Objects.Document.Viewform.Erranddocument.base_label, Objects.Document.Viewform.Erranddocument.base_field, doc);

        checkfield("Основание", Objects.Document.Viewform.Erranddocument.additional_label, Objects.Document.Viewform.Erranddocument.additional_field, doc);

        checkfield("Автор", Objects.Document.Viewform.Erranddocument.initiator_label, Objects.Document.Viewform.Erranddocument.initiator_field, doc);

        checkfield("Создатель", Objects.Document.Viewform.Erranddocument.author_label, Objects.Document.Viewform.Erranddocument.author_field, doc);

        checkfield("Тип поручения", Objects.Document.Viewform.Erranddocument.type_label, Objects.Document.Viewform.Erranddocument.type_field, doc);

        checkfield("Заголовок", Objects.Document.Viewform.Erranddocument.title_label, Objects.Document.Viewform.Erranddocument.title_field, doc);

        checkfield("Текст поручения", Objects.Document.Viewform.Erranddocument.content_label, Objects.Document.Viewform.Erranddocument.content_field, doc);

        checkfield("Исполнитель", Objects.Document.Viewform.Erranddocument.executor_label, Objects.Document.Viewform.Erranddocument.executor_field, doc);

        checkfield("Соисполнители", Objects.Document.Viewform.Erranddocument.coexecutors_label, Objects.Document.Viewform.Erranddocument.coexecutors_field, doc);

        checkfield("Срок исполнения", Objects.Document.Viewform.Erranddocument.limitationdate_label, Objects.Document.Viewform.Erranddocument.limitationdate_field, doc);

        checkfield("Закрывает вышестоящее поручение", Objects.Document.Viewform.Erranddocument.autoclose_label, Objects.Document.Viewform.Erranddocument.autoclose_field, doc);

        checkfield("Направлять периодически", Objects.Document.Viewform.Erranddocument.periodically_label, Objects.Document.Viewform.Erranddocument.periodically_field, doc);

        checkfield("Контролер", Objects.Document.Viewform.Erranddocument.controller_label, Objects.Document.Viewform.Erranddocument.controller_field, doc);

        checkfield("Требуется отчет", Objects.Document.Viewform.Erranddocument.needreport_label, Objects.Document.Viewform.Erranddocument.needreport_field, doc);

        checkfield("Получатель отчета", Objects.Document.Viewform.Erranddocument.reportrecipient_label, Objects.Document.Viewform.Erranddocument.reportrecipient_field, doc);

        checkfield("Тематика", Objects.Document.Viewform.Erranddocument.subject_label, Objects.Document.Viewform.Erranddocument.subject_field, doc);

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























    @Step("Проверить наличие атрибутов и их значения на форме просмотра")
    static void readerrand1(Map<String, String[]> doc) {
        waitForLoad();
        String status = null;
        for (String val:doc.get("Статус"))
            status = val;
        waitelement(Objects.Document.Viewform.Incomingdocument.status_field);
        if (!currentdriver().findElement(By.xpath(Objects.Document.Viewform.Incomingdocument.status_field)).getText().equals(status)){
            currentdriver().get(currentdriver().getCurrentUrl());
        }
        currentdriver().get(currentdriver().getCurrentUrl());

        String title = docgettitle();
        doc.put("Номер",new String[]{title.substring(2,title.indexOf(","))});
        doc.put("Срок исполнения", new String[]{title.substring(title.indexOf("срок:")+6,title.length())});

        checkattrviewerrand1("Документ-основание", doc);
        checkattrviewerrand1("Основание", doc);
        checkattrviewerrand1("Автор", doc);
        checkattrviewerrand1("Создатель", doc);
        checkattrviewerrand1("Тип поручения", doc);
        checkattrviewerrand1("Заголовок", doc);
        checkattrviewerrand1("Текст поручения", doc);
        checkattrviewerrand1("Исполнитель", doc);
        checkattrviewerrand1("Соисполнители", doc);
        checkattrviewerrand1("Срок исполнения", doc);
        checkattrviewerrand1("Закрывает вышестоящее поручение", doc);
        checkattrviewerrand1("Направлять периодически", doc);
        checkattrviewerrand1("Контролер", doc);
        checkattrviewerrand1("Требуется отчет", doc);
        checkattrviewerrand1("Получатель отчета", doc);
        checkattrviewerrand1("Тематика", doc);

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

    private static void checkattrviewerrand1(String AttrLabel, Map<String, String[]> doc) {
        if (doc.get(AttrLabel) == null)
            if (AttrLabel.equals("Закрывает вышестоящее поручение") || AttrLabel.equals("Направлять периодически") || AttrLabel.equals("Требуется отчет"))
                doc.put(AttrLabel, new String[]{"Нет"});
            else
                doc.put(AttrLabel, new String[]{"(Нет)"});
        checkattrviewerrandchild1(AttrLabel,doc.get(AttrLabel));
    }

    @Step("{0}: {1}")
    private static void checkattrviewerrandchild1(String AttrLabel, String[] values) {
        waitForLoad();
        String dynamicXPath = "//div[contains(@class,'tab-common')]//*[contains(text(),'%s:')]";
        String XPath = String.format(dynamicXPath, AttrLabel);
        try {
            (new WebDriverWait(currentdriver(), currenttimeoutlnseconds()))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPath)));
        } catch (Exception e) {
            hardassertfail("Не найден элемент " + XPath);
        }

        dynamicXPath = "//div[contains(@class,'tab-common')]//*[contains(text(),'%s:')]//ancestor::div[contains(@class,'viewmode')]//div[contains(@class,'cropped-item')]";
        XPath = String.format(dynamicXPath, AttrLabel);
        if (currentdriver().findElements(By.xpath(XPath)).isEmpty()) {
            dynamicXPath = "//div[contains(@class,'tab-common')]//*[contains(text(),'%s:')]//ancestor::div[contains(@class,'viewmode')]//div[contains(@class,'value')]";
            XPath = String.format(dynamicXPath, AttrLabel);
        }

        int i = timeoutlnsecond;
        while (i > 0 && currentdriver().findElements(By.xpath(XPath)).isEmpty()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i--;
        }

        List<WebElement> elements = null;
        elements = currentdriver().findElements(By.xpath(XPath));

        for(String value:values) {
            boolean t = false;
            for (WebElement element : elements) {
                if (element.getText().contains(value)) {
                    t = true;
                    elements.remove(element);
                    break;
                }
            }
            //if (AttrLabel.equals("Получатель") && (value.equals("Сотрудник") || value.equals("Организация")))
              //  t = true;
            softassertfail(t, "Атрибут не содержит значение " + value);
        }

        if (elements.size() > 0) {
            ArrayList<String> elementstext = new ArrayList<>();
            for (WebElement element : elements) {
                elementstext.add(element.getText());
            }
            softassertfail("Лишние элементы в атрибуте " + elementstext);
        }
    }

}
