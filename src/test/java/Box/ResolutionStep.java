package Box;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Map;

import static Box.About.*;
import static Box.Base.*;
import static Box.Users.getuserbylogin;

public class ResolutionStep {

    @Step("Создать резолюцию")
    static void createresolutions(Map<String, String[]> doc, String button) {
        gotoarmsed();
        click("Создать", Objects.ARMSED.createButton);
        click("Рехолюция", Objects.ARMSED.Createmenu.resolutionsdocument);
        fillcreateresolutions(doc);
        String currenturl = driver.getCurrentUrl();
        if (button.equals("Сохранить черновик")) {
            click("Сохранить черновик", Objects.Document.Createform.Resolutionsdocument.save_button, Objects.Document.Viewform.Resolutionsdocument.status_field);
            doc.put("Статус", new String[]{"Черновик"});
        }
        else
        if (button.equals("Направить")) {
            click("Направить", Objects.Document.Createform.Resolutionsdocument.default_button, Objects.Document.Viewform.Resolutionsdocument.status_field);
            doc.put("Статус",new String[]{"На исполнении"});
        }
        else hardassertfail(button + " - Нет такой кнопки на форме создания поручения");

        if (doc.get("Создатель") == null)
            doc.put("Создатель", new String[]{getuserbylogin(current_login).full});
    }

    @Step("Заполнить атрибуты")
    private static void fillcreateresolutions(Map<String, String[]> doc) {
        verifyattr("Утверждено вне системы", Objects.Document.Createform.Resolutionsdocument.approvedoutsidesystem_label);
        fillfield("Утверждено вне системы", Objects.Document.Createform.Resolutionsdocument.approvedoutsidesystem_checkbox, doc.get("Утверждено вне системы"), doc);

        verifyattr("Тематика", Objects.Document.Createform.Resolutionsdocument.subject_label);
        fillfield("Тематика", Objects.Document.Createform.Resolutionsdocument.subject_button, doc.get("Тематика"), doc);

        verifyattr("Автор", Objects.Document.Createform.Resolutionsdocument.author_label);
        fillfield("Автор", Objects.Document.Createform.Resolutionsdocument.author_button, doc.get("Автор"), doc);

        verifyattr("Контролер", Objects.Document.Createform.Resolutionsdocument.controller_label);
        fillfield("Контролер", Objects.Document.Createform.Resolutionsdocument.controller_button, doc.get("Контролер"), doc);

        verifyattr("Завершающий", Objects.Document.Createform.Resolutionsdocument.closers_label);
        fillfield("Завершающий", Objects.Document.Createform.Resolutionsdocument.closers_select, doc.get("Завершающий"), doc);

        verifyattr("Контроль", Objects.Document.Createform.Resolutionsdocument.isoncontrol_label);
        fillfield("Контроль", Objects.Document.Createform.Resolutionsdocument.isoncontrol_checkbox, doc.get("Контроль"), doc);

        verifyattr("Срок исполнения", Objects.Document.Createform.Resolutionsdocument.limitationdate_label);
        fillfield("Срок исполнения", Objects.Document.Createform.Resolutionsdocument.limitationdate_radiodate, doc.get("Срок исполнения"), doc);

        if (!errands.isEmpty()){
            //метод для заполнения формы создания пунктов (которая еще не описана)
            int k = errands.size();

            click("Добавить", Objects.Document.Createform.Resolutionsdocument.adderrand_button);

            for (int i=0; i<k; i++) {
                errand = errands.get(Integer.toString(i+1));
                verifyattr("Поручения Тип поручения", "(" + Objects.Document.Createform.Resolutionsdocument.Errands.type_label + ")[" + Integer.toString(i+1) + "]");
                fillfield("Поручения Тип поручения", "(" + Objects.Document.Createform.Resolutionsdocument.Errands.type_button + ")[" + Integer.toString(i+1) + "]", errand.get("Поручения Тип поручения"), errand);

                verifyattr("Поручения Заголовок", "(" + Objects.Document.Createform.Resolutionsdocument.Errands.title_label + ")[" + Integer.toString(i+1) + "]");
                fillfield("Поручения Заголовок", "(" + Objects.Document.Createform.Resolutionsdocument.Errands.title_button + ")[" + Integer.toString(i+1) + "]", errand.get("Поручения Заголовок"), errand);

                verifyattr("Поручения Исполнитель", "(" + Objects.Document.Createform.Resolutionsdocument.Errands.executor_label + ")[" + Integer.toString(i+1) + "]");
                fillfield("Поручения Исполнитель", "(" + Objects.Document.Createform.Resolutionsdocument.Errands.executor_button + ")[" + Integer.toString(i+1) + "]", errand.get("Поручения Исполнитель"), errand);

                verifyattr("Поручения Соисполнители", "(" + Objects.Document.Createform.Resolutionsdocument.Errands.coexecutor_label + ")[" + Integer.toString(i+1) + "]");
                fillfield("Поручения Соисполнители", "(" + Objects.Document.Createform.Resolutionsdocument.Errands.coexecutor_button + ")[" + Integer.toString(i+1) + "]", errand.get("Поручения Соисполнители"), errand);

                verifyattr("Поручения Контролер", "(" + Objects.Document.Createform.Resolutionsdocument.Errands.controller_label + ")[" + Integer.toString(i+1) + "]");
                fillfield("Поручения Контролер", "(" + Objects.Document.Createform.Resolutionsdocument.Errands.controller_button + ")[" + Integer.toString(i+1) + "]", errand.get("Поручения Контролер"), errand);

                verifyattr("Поручения Срок исполнения", "(" + Objects.Document.Createform.Resolutionsdocument.Errands.limitationdate_label + ")[" + Integer.toString(i+1) + "]");
                fillfield("Поручения Срок исполнения", "(" + Objects.Document.Createform.Resolutionsdocument.Errands.limitationdate_radiodate + ")[" + Integer.toString(i+1) + "]", errand.get("Поручения Срок исполнения"), errand);

                verifyattr("Поручения Требуется отчет", "(" + Objects.Document.Createform.Resolutionsdocument.Errands.needreport_label + ")[" + Integer.toString(i+1) + "]");
                fillfield("Поручения Требуется отчет", "(" + Objects.Document.Createform.Resolutionsdocument.Errands.needreport_checkbox + ")[" + Integer.toString(i+1) + "]", errand.get("Поручения Требуется отчет"), errand);

                boolean t = false;
                for (String val:errand.get("Поручения Требуется отчет"))
                    if (val.equals("Да")) t = true;
                if (t) {
                    verifyattr("Поручения Получатель отчета", "(" + Objects.Document.Createform.Resolutionsdocument.Errands.reportrecipient_label + ")[" + Integer.toString(i + 1) + "]");
                    fillfield("Поручения Получатель отчета", "(" + Objects.Document.Createform.Resolutionsdocument.Errands.reportrecipient_select+ ")[" + Integer.toString(i + 1) + "]", errand.get("Поручения Получатель отчета"), errand);
                }
            }
        }
    }

    @Step("Проверить наличие атрибутов и их значения на форме просмотра")
    static void readresolutions(Map<String, String[]> doc) {
        waitForLoad();
        String status = null;
        for (String val:doc.get("Статус"))
            status = val;
        waitelement(Objects.Document.Viewform.Resolutionsdocument.status_field);
        if (!driver.findElement(By.xpath(Objects.Document.Viewform.Resolutionsdocument.status_field)).getText().equals(status)){
            driver.get(driver.getCurrentUrl());
        }

        String title = docgettitle();
        doc.put("Номер",new String[]{title.substring(2,title.indexOf("от")-1)});

        if (!status.contains("Черновик") && title.substring(2,title.indexOf("от")-1).equals("Не присвоено")) {
            doc.put("Номер_old",new String[]{title.substring(2,title.indexOf("от")-1)});
            doc.put("Срок исполнения_old", doc.get("Срок исполнения"));
            driver.get(driver.getCurrentUrl());
        }

        title = driver.findElement(By.xpath(Objects.Document.documenttitle)).getText();
        doc.put("Номер",new String[]{title.substring(2,title.indexOf("от")-1)});
        doc.put("Срок исполнения", new String[]{title.substring(title.indexOf("срок:")+6,title.length())});

        checkfield("Утверждено вне системы", Objects.Document.Viewform.Resolutionsdocument.approvedoutsidesystem_label, Objects.Document.Viewform.Resolutionsdocument.approvedoutsidesystem_field, doc);

        checkfield("Документ-основание", Objects.Document.Viewform.Resolutionsdocument.base_label, Objects.Document.Viewform.Resolutionsdocument.base_field, doc);

        checkfield("Основание", Objects.Document.Viewform.Resolutionsdocument.basedocument_label, Objects.Document.Viewform.Resolutionsdocument.basedocument_field, doc);

        checkfield("Контроль", Objects.Document.Viewform.Resolutionsdocument.isoncontrol_label, Objects.Document.Viewform.Resolutionsdocument.isoncontrol_field, doc);

        checkfield("Тематика", Objects.Document.Viewform.Resolutionsdocument.subject_label, Objects.Document.Viewform.Resolutionsdocument.subject_field, doc);

        checkfield("Автор", Objects.Document.Viewform.Resolutionsdocument.resolutionsauthor_label, Objects.Document.Viewform.Resolutionsdocument.resolutionsauthor_field, doc);

        checkfield("Создатель", Objects.Document.Viewform.Resolutionsdocument.documentauthor_label, Objects.Document.Viewform.Resolutionsdocument.documentauthor_field, doc);

        checkfield("Завершающий", Objects.Document.Viewform.Resolutionsdocument.closers_label, Objects.Document.Viewform.Resolutionsdocument.closers_field, doc);

        checkfield("Контролер", Objects.Document.Viewform.Resolutionsdocument.controller_label, Objects.Document.Viewform.Resolutionsdocument.controller_field, doc);

        checkfield("Срок исполнения", Objects.Document.Viewform.Resolutionsdocument.limitationdate_label,
                Objects.Document.Viewform.Resolutionsdocument.limitationdate_date_radio, Objects.Document.Viewform.Resolutionsdocument.limitationdate_date_field,
                Objects.Document.Viewform.Resolutionsdocument.limitationdate_limitless_radio, Objects.Document.Viewform.Resolutionsdocument.limitationdate_limitless_field, doc);



        String currenturl = driver.getCurrentUrl();
        if (currenturl.contains("#")){
            currenturl = currenturl.substring(0,currenturl.indexOf('#'));
        }
        if (!removedoc.contains(currenturl))
            removedoc.add(currenturl);
    }
}
