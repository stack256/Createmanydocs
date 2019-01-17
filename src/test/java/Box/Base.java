package Box;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Box.About.*;
import static Box.Objects.*;
import static Box.Users.*;

class Base {

    @Step("Авторизоваться пользователем {0}")
    static void auth(String report, String login, String pass) {
        if (current_login != null && current_login != login)
            logout();
        settext("Имя пользователя", AuthPage.username, login);
        settext("Пароль", AuthPage.password, pass);
        String currenturl = driver.getCurrentUrl();
        int count = 100;
        click("Войти", AuthPage.login);
        while ((currenturl.equals(driver.getCurrentUrl())) && (count >= 0)) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count--;
        }

        current_login = login;
        current_user = report;
    }

    @Step("Выйти из системы")
    private static void logout(){
        driver.get(driver.getCurrentUrl());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String currenturl = driver.getCurrentUrl();
        int count = 100;
        click("Меню пользователя",MenuBar.user_menu_popup);
        click("Выйти",MenuBar.user_menu_logout);
        while ((currenturl.equals(driver.getCurrentUrl())) && (count >= 0)) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count--;
        }
    }

    @Step("Удалить документ")
    static void admindelete() {
        click("Административные функции",Document.admin_function);
        click("Удалить",Document.admin_delete);
        executeactionapprove("Да");
        waitelement(Document.delete_check);
    }

    @Step("Удаление документов после успешного теста")
    static void removedocs() {
        auth("Admin","admin",System.getProperty("admin.pass"));
        for (String val:removedoc){
            driver.get(val);
            admindelete();
        }
    }

    @Step("Выполнение действия")
    private static void executeactionapprove(String approve) {
        String dynamicXPath = "//div[contains(@class,\'container\') and contains (@style,\'visibility: visible\')]//button[text()=\'%s\']";
        click(approve,String.format(dynamicXPath, approve));
    }

    @Step("Перейти в АРМ СЭД")
    static void gotoarmsed() {
        click("Логика: СЭД", MenuBar.logsed);
        timeoutlnseconds = 120;
        waitelement(ARMSED.createButton);
        timeoutlnseconds = 10;
    }

    static String docgettitle() {
        String title = null;
        try {
            title = driver.findElement(By.xpath(Document.documenttitle)).getText();
        } catch (Exception e) {
            try {
                Thread.sleep(1000);
                waitForLoad();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            title = driver.findElement(By.xpath(Document.documenttitle)).getText();
        }
        if (title == null || title.length()==0){
            try {
                Thread.sleep(1000);
                waitForLoad();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            title = driver.findElement(By.xpath(Document.documenttitle)).getText();
        }
        if (title == null || title.length()==0){
            hardassertfail("Не получилось прочитать заголовок документа");
        }
        return title;
    }

    @Step("Атрибут <{0}>")
    static void checkfield(String attrname, String xpath, String xpathradio1, String xpathfield1, String xpathradio2, String xpathfield2, Map<String, String[]> doc) {
        waitForLoad();
        try {
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            hardassertfail("Не найден элемент " + xpath);
        }

        String value = null;
        for (String val:doc.get(attrname))
            value = val;

        String status = null;
        for (String val:doc.get("Статус"))
            status = val;
        if (!value.contains("Без срока"))
            readfieldradio(xpathradio1, true);
        else
            readfieldradio(xpathradio1, false);
        if (value.contains("Без срока") || ((value.contains("к.д.") || value.contains("р.д.")) && status.equals("Черновик")) || (!value.contains(".") && !status.equals("Черновик")))
            readfieldchild(xpathfield1, attrname, "(Нет)");
        else
            readfieldchild(xpathfield1, attrname, doc.get(attrname));



        if (value.contains("Без срока"))
            readfieldradio(xpathradio2, true);
        else
            readfieldradio(xpathradio2, false);
        readfieldchild(xpathfield2, attrname, "Без срока");


    }

    @Step("Значения радио <{1}>")
    private static void readfieldradio(String xpath, boolean t) {
        String value = null;
        try{
            value = driver.findElement(By.xpath(xpath)).getAttribute("checked");
        } catch(Exception e){
            value = Boolean.toString(false);
        }
        if (value == null)
            value = Boolean.toString(false);
        softassertfail(value,Boolean.toString(t));
    }

    @Step("Атрибут <{0}>")
    static void checkfield(String attrname, String xpath, String xpathfield, Map<String, String[]> doc) {
        waitForLoad();
        try {
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            hardassertfail("Не найден элемент " + xpath);
        }
        readfield(attrname, xpathfield, doc);
    }

    private static void readfield(String attrname, String xpath, Map<String, String[]> doc) {
        if (doc.get(attrname) == null)
            if (attrname.equals("Завершающий") || attrname.equals("На контроле") || attrname.equals("Нерегистрируемый") ||
                    attrname.equals("Подписан") || attrname.equals("Подписано на бумажном носителе") || attrname.equals("Бессрочный") ||
                    attrname.equals("Закрывает вышестоящее поручение") || attrname.equals("Направлять периодически") || attrname.equals("Требуется отчет"))
                doc.put(attrname,new String[]{"Нет"});
            else
                doc.put(attrname,new String[]{"(Нет)"});
        readfieldchild(xpath, attrname, doc.get(attrname));
    }

    @Step("Значения <{2}>")
    private static void readfieldchild(String xpath, String attrname, String... values) {
        if (attrname.equals("Получатель")){
            for(String value:values) {
                if (!(value.equals("Сотрудник") || value.equals("Организация"))) {
                    List<WebElement> elements = driver.findElements(By.xpath(xpath));
                    boolean t = false;
                    for (WebElement element : elements)
                        if (element.getText().contains(value)) t = true;
                    softassertfail(t, "Атрибут не содержит значение " + value);
                }
            }
        } else
            for(String value:values) {
                List<WebElement> elements = driver.findElements(By.xpath(xpath));
                boolean t = false;
                for (WebElement element : elements) {
                    if (element.getText().contains(value)) t = true;
                }
                softassertfail(t, "Атрибут не содержит значение " + value);
            }
    }

    @Step("Заполнить атрибут <{0}> значением <{2}>")
    private static void fillselectdialogsimple(String attrname, Map<String, String[]> doc, String... values) {
        waitelement(SelectDialog.Simple.dialog);
        click("Очистить", SelectDialog.clearall);
        for(String value:values){
            settext("Строка поиска",SelectDialog.Simple.input,value);
            click("Поиск",SelectDialog.Simple.search_button);
            waitForLoad();
            click("Добавить",sd_simple_tableadd(value));
            if (attrname.equals("Подписанты")){
                for (User user:users)
                    if (value.equals(user.full))
                        value = user.fio;
            }
            List<WebElement> elements = driver.findElements(By.xpath(SelectDialog.Simple.selected_elements));
            boolean t = false;
            for(WebElement element:elements)
                if (element.getText().contains(value)) t = true;
            hardassertfail(t, "Не выбран элемент " + value);
        }
        click("ОК", SelectDialog.Simple.ok_button);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (attrname.equals("Подписанты")){
            ArrayList<String> aval = new ArrayList<>();
            for(String value:values)
                for (User user:users)
                    if (value.equals(user.full))
                        aval.add(user.fio);
            int k = aval.size();
            String[] buf = new String[k];
            for (int i = 0; i < k; i++)
                buf[i] = aval.get(i);
            doc.put("Подписанты",buf);
            aval.clear();
        }
    }

    @Step("Заполнить атрибут <{0}> значением <{2}>")
    private static void fillselectdialogrecipient(String attrname, Map<String, String[]> doc, String... values) {
        waitelement(SelectDialog.Recipient.dialog);
        click("Очистить", SelectDialog.clearall);
        for (String val:values)
            switch (val){
                case "Сотрудник":
                    click("Развернуть выпадающий список",SelectDialog.Recipient.select_type);
                    click("Сотрудник",SelectDialog.Recipient.select_type_employee);
                    break;
                case "Организация":
                    click("Развернуть выпадающий список",SelectDialog.Recipient.select_type);
                    click("Сотрудник",SelectDialog.Recipient.select_type_organization);
                    break;
                default:
                    settext("Строка поиска",SelectDialog.Recipient.search_field,val);
                    click("Поиск",SelectDialog.Recipient.search_button);
                    waitForLoad();
                    click("Добавить", sd_recipient_tableadd(val));
                    List<WebElement> elements = driver.findElements(By.xpath(SelectDialog.Recipient.selected_elements));
                    boolean t = false;
                    for(WebElement element:elements)
                        if (element.getText().contains(val)) t = true;
                    hardassertfail(t, "Не выбран элемент " + val);
                    break;
            }

        click("ОК", SelectDialog.Recipient.ok_button);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Заполнить атрибут <{0}> значением <{2}>")
    private static void fillselectdialogresponseto(String attrname, Map<String, String[]> doc, String... values) {
        waitelement(SelectDialog.Responseto.dialog);
        click("Очистить", SelectDialog.clearall);
        click("Показать дополнительные параметры поиска",SelectDialog.Responseto.show_parametrs);
        fillfield("Содержит в названии", SelectDialog.Responseto.search_text_field, doc.get("В ответ на Содержит в названии"), doc);
        fillfield("Номер", SelectDialog.Responseto.regnum_field, doc.get("В ответ на Номер"), doc);
        fillfield("В ответ на Заголовок", SelectDialog.Responseto.title_field, doc.get("В ответ на Заголовок"), doc);
        fillfield("Дата создания С", SelectDialog.Responseto.date_from_field, doc.get("В ответ на Дата создания С"), doc);
        fillfield("Дата создания По", SelectDialog.Responseto.date_to_field, doc.get("В ответ на Дата создания По"), doc);
        fillfield("Автор", SelectDialog.Responseto.author_button, doc.get("В ответ на Автор"), doc);
        fillfield("Режим связи условий поиска", SelectDialog.Responseto.search_logic, doc.get("В ответ на Режим связи условий поиска"), doc);
        click("Поиск",SelectDialog.Responseto.search_button);
        waitForLoad();
        String value = null;
        for (String val:doc.get(attrname))
            value = val;
        click("Добавить", sd_simple_tableadd(value));
        List<WebElement> elements = driver.findElements(By.xpath(SelectDialog.Responseto.selected_elements));
        boolean t = false;
        for(WebElement element:elements)
            if (element.getText().contains(value)) t = true;
        hardassertfail(t, "Не выбран элемент " + value);
        click("ОК", SelectDialog.Responseto.ok_button);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Заполнить атрибут <{0}> значением <{2}>")
    private static void fillselectdialogfileregister(String attrname, Map<String, String[]> doc, String... values) {
        waitelement(SelectDialog.Fileregister.dialog);
        click("Очистить", SelectDialog.clearall);
        int k = values.length;
        if (k == 1)
            doc.put(attrname, new String[]{"/" + values[k-1]});
        else
        if (k == 2)
            doc.put(attrname, new String[]{"/" + values[k-2] + "/" + values[k-1]});
        else
        if (k > 2)
            doc.put(attrname, new String[]{"/_/" + values[k-2] + "/" + values[k-1]});
        for(String val:values)
            click(val,sd_filergistertree_tableadd(val));

        String value = null;
        for (String val:doc.get(attrname))
            value = val;
        List<WebElement> elements = driver.findElements(By.xpath(SelectDialog.Fileregister.selected_elements));
        boolean t = false;
        for(WebElement element:elements)
            if (element.getText().contains(value)) t = true;
        hardassertfail(t, "Не выбран элемент " + value);
        click("ОК", SelectDialog.Fileregister.ok_button);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Заполнить атрибут <{0}> значением <{1}>")
    private static void fillselectdialogsender(String attrname, String[] values, Map<String, String[]> doc) {
        String type = null, value = null;
        if (doc.get("Корреспондент") != null)
            for (String val:doc.get("Корреспондент Тип"))
                type = val;
        waitelement(SelectDialog.Sender.dialog);
        click("Очистить", SelectDialog.clearall);
        if ((type == null) || (type.equals("Внешний контрагент"))) {
            click("Показать дополнительные параметры поиска", SelectDialog.Sender.show_parametrs);
            fillfield("Наименование",SelectDialog.Sender.fullname_field,doc.get("Корреспондент Наименование"),doc);
            for (String val:doc.get("Корреспондент Наименование"))
                value = val;
            fillfield("ИНН",SelectDialog.Sender.fullname_field,doc.get("Корреспондент ИНН"),doc);
            fillfield("КПП",SelectDialog.Sender.fullname_field,doc.get("Корреспондент КПП"),doc);
        } else
        if (type.equals("Внутренний контрагент")){
            click("Развернуть выпадающий список",SelectDialog.Sender.select_type);
            click("Внутренний контрагент",SelectDialog.Sender.select_type_organization);
            click("Показать дополнительные параметры поиска", SelectDialog.Sender.show_parametrs);
            fillfield("Наименование",SelectDialog.Sender.fullname_field,doc.get("Корреспондент Наименование"),doc);
            for (String val:doc.get("Корреспондент Наименование"))
                value = val;
            fillfield("ИНН",SelectDialog.Sender.fullname_field,doc.get("Корреспондент ИНН"),doc);
            fillfield("КПП",SelectDialog.Sender.fullname_field,doc.get("Корреспондент КПП"),doc);
        } else
        if (type.equals("Физическое лицо")){
            click("Развернуть выпадающий список",SelectDialog.Sender.select_type);
            click("Физическое лицо",SelectDialog.Sender.select_type_person);
            click("Показать дополнительные параметры поиска", SelectDialog.Sender.show_parametrs);
            fillfield("ФИО",SelectDialog.Sender.fullname_field,doc.get("Корреспондент ФИО"),doc);
            for (String val:doc.get("Корреспондент ФИО"))
                value = val;
            fillfield("№ документа",SelectDialog.Sender.fullname_field,doc.get("Корреспондент № документа"),doc);
            fillfield("Адрес",SelectDialog.Sender.fullname_field,doc.get("Корреспондент Адрес"),doc);
            fillfield("ИНН",SelectDialog.Sender.fullname_field,doc.get("Корреспондент ИНН"),doc);
            fillfield("Регион",SelectDialog.Sender.fullname_field,doc.get("Корреспондент Регион"),doc);
        } else
            hardassertfail(type + " - Нет такого типа на форме выбора корреспондента");
        click("Поиск",SelectDialog.Sender.search_button);
        waitForLoad();
        click("Добавить",sd_sender_tableadd(value));
        List<WebElement> elements = driver.findElements(By.xpath(SelectDialog.Sender.selected_elements));
        boolean t = false;
        for(WebElement element:elements)
            if (element.getText().contains(value)) t = true;
        hardassertfail(t, "Не выбран элемент " + value);
        click("ОК", SelectDialog.Sender.ok_button);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Заполнить атрибут <{0}> значением <{2}>")
    private static void fillselectdialogreporter(String attrname, Map<String, String[]> doc, String... values) {
        waitelement(SelectDialog.Reporter.dialog);
        click("Очистить", SelectDialog.clearall);
        for (String val:values)
            switch (val){
                case "Сотрудник":
                    click("Развернуть выпадающий список",SelectDialog.Reporter.select_type);
                    click("Сотрудник",SelectDialog.Reporter.select_type_employee);
                    break;
                case "Адресант":
                    click("Развернуть выпадающий список",SelectDialog.Reporter.select_type);
                    click("Адресант",SelectDialog.Reporter.select_type_adresant);
                    break;
                default:
                    settext("Строка поиска",SelectDialog.Reporter.search_field,val);
                    click("Поиск",SelectDialog.Reporter.search_button);
                    waitForLoad();
                    click("Добавить", sd_reporter_tableadd(val));
                    List<WebElement> elements = driver.findElements(By.xpath(SelectDialog.Reporter.selected_elements));
                    boolean t = false;
                    for(WebElement element:elements)
                        if (element.getText().contains(val)) t = true;
                    hardassertfail(t, "Не выбран элемент " + val);
                    break;
            }

        click("ОК", SelectDialog.Reporter.ok_button);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Заполнить атрибут <{0}> значением <{2}>")
    private static void fillselectdialogapprove(String attrname, Map<String, String[]> doc, String... values) {
        waitelement(SelectDialog.Approve.dialog);
        click("Очистить", SelectDialog.clearall);
        for (String val:values)
            switch (val){
                case "Сотрудник":
                    click("Развернуть выпадающий список",SelectDialog.Approve.select_type);
                    click("Сотрудник",SelectDialog.Approve.select_type_employee);
                    break;
                case "Адресант":
                    click("Развернуть выпадающий список",SelectDialog.Approve.select_type);
                    click("Макросы участников маршрута",SelectDialog.Approve.select_type_macros);
                    break;
                default:
                    settext("Строка поиска",SelectDialog.Approve.search_field,val);
                    click("Поиск",SelectDialog.Approve.search_button);
                    waitForLoad();
                    click("Добавить", sd_reporter_tableadd(val));
                    List<WebElement> elements = driver.findElements(By.xpath(SelectDialog.Approve.selected_elements));
                    boolean t = false;
                    for(WebElement element:elements)
                        if (element.getText().contains(val)) t = true;
                    hardassertfail(t, "Не выбран элемент " + val);
                    break;
            }

        click("ОК", SelectDialog.Approve.ok_button);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Загрузить вложение в категорию {0}")
    private static void fillattachment(String attrname, String xpath, String... values) {
        for (String value : values){
            click("Добавить",xpath, Document.Createform.attachments_uploadbutton);
            waitForLoad();
            driver.findElement(By.xpath(Document.Createform.attachments_input)).sendKeys(value);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitForLoad();
        }
    }

    //@Step("Заполнить атрибут {0}")
    static void fillfield(String attrname, String xpath, String[] values, Map<String, String[]> doc) {
        if (values != null)
        switch (attrname) {
            case "Заголовок":
                if (doc.get("document")[0].equals("errand")) {
                    click("...", xpath, SelectDialog.Simple.dialog);
                    fillselectdialogsimple(attrname, doc, values);
                    break;
                }
            case "Исходящий номер":
            case "Исходящий от":
            case "Количество листов":
            case "Примечание":
            case "Срок исполнения":
                if (doc.get("document")[0].equals("resolutions")) {
                    filllimitationdate_resolution(attrname,values);
                    break;
                } else
                if (doc.get("document")[0].equals("errand")) {
                    filllimitationdate_errand(attrname,values);
                    break;
                }
            case "Наименование":
            case "ИНН":
            case "КПП":
            case "Срок ответа":
            case "Дата подписания":
            case "Период действия С":
            case "Период действия По":
            case "Пункты Заголовок":
            case "Пункты Содержание":
            case "Текст поручения":
            case "Начало повторений":
            case "Пункт Формулировка":
            case "Пункт Описание":
            case "Пункт Выступили":
            case "Пункт Решение":
            case "Пункт Срок исполнения":
            case "Пункт Примечание":
            case "Название этапа":
            case "Срок по умолчанию для согласующего (р. д.)":
                for (String value : values)
                    settext(attrname, xpath, value);
                break;
            case "Вид документа":
            case "Способ доставки":
            case "Представитель корреспондента":
            case "Тематика":
            case "Подписанты":
            case "Адресат корреспондента":
            case "Подразделения":
            case "Контролёр":
            case "Пункты Автор":
            case "Пункты Исполнитель":
            case "Пункты Соисполнители":
            case "Пункты Контролер":
            case "Пункты Тематика":
            case "Тип поручения":
            case "Исполнитель":
            case "Соисполнители":
            case "Контролер":
            case "Автор":
            case "Поручения Тип поручения":
            case "Поручения Заголовок":
            case "Поручения Исполнитель":
            case "Поручения Соисполнители":
            case "Поручения Контролер":
            case "Согласующие":
                if (doc.get("document")[0].equals("approval")) {
                    click("...", xpath, SelectDialog.Approve.dialog);
                    fillselectdialogapprove(attrname, doc, values);
                    break;
                }
            case "Председатель совещания":
            case "Секретарь":
            case "Присутствовали":
            case "Пункт Исполнитель":
            case "Правило для этапа":
                click("...", xpath, SelectDialog.Simple.dialog);
                fillselectdialogsimple(attrname, doc, values);
                break;
            case "Корреспондент":
                click("...", xpath, SelectDialog.Sender.dialog);
                fillselectdialogsender(attrname, values, doc);
                break;
            case "Получатель":
                click("...", xpath, SelectDialog.Recipient.dialog);
                fillselectdialogrecipient(attrname, doc, values);
                break;
            case "В ответ на":
            case "Отменяемые документы":
            case "Принимаемые документы":
                click("...", xpath, SelectDialog.Responseto.dialog);
                fillselectdialogresponseto(attrname, doc, values);
                break;
            case "Содержание":
                fillsummary(attrname, xpath, values);
                break;
            case "Номер дела":
                click("...", xpath, SelectDialog.Fileregister.dialog);
                fillselectdialogfileregister(attrname, doc, values);
                break;
            case "На контроле":
            case "Нерегистрируемый":
            case "Подписано на бумажном носителе":
            case "Завершающий":
                if (doc.get("document")[0].equals("resolutions")) {
                    fillselect(attrname,xpath,values);
                    break;
                }
            case "Бессрочный":
            case "Подтверждать завершение работы по документу":
            case "Пункты Требуется отчет":
            case "Требуется отчет":
            case "Важное":
            case "Направлять периодически":
            case "Поручения Требуется отчет":
            case "Утверждено вне системы":
            case "Контроль":
            case "Автосоздание поручений":
            case "Согласование Завершать после первого отклонения согласующим":
            case "Согласование Уведомлять о каждой рецензии":
            case "Использовать правило для этапа":
                for (String value : values)
                    if (value.equals("Да") != driver.findElement(By.xpath(xpath)).isSelected())
                        click(attrname, xpath);
                break;
            case "Вложения Входящий":
            case "Вложения Прочее":
                fillattachment(attrname,xpath,values);
                break;
            case "Пункты Срок исполнения":
                filllimitationdate_point(attrname, values);
                break;
            case "Поручения Срок исполнения":
                filllimitationdate_resolutionerrand(attrname, values);
                break;
            case "Окончание повторений":
                periodend(attrname,values);
                break;
            case "Повторять":
                reiterationrule(attrname,values);
                break;
            case "Получатель отчета":
            case "Поручения Получатель отчета":
            case "Согласование По истечении срока":
            case "Тип этапа":
                fillselect(attrname,xpath,values);
                break;
            case "Пункт Докладчик":
            case "Пункт Содокладчики":
                click("...", xpath, SelectDialog.Reporter.dialog);
                fillselectdialogreporter(attrname, doc, values);
                break;
            default:
                softassertfail(attrname + " - Тест не знает такого атрибута, надо дописать");
                break;
        }
    }

    @Step("Заполнить атрибут {0} значением {1}")
    private static void reiterationrule(String attrname, String[] values) {
        if (values[0].equals("Еженедельно")){
            WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Erranddocument.reiterationruletype_select));
            Select select = new Select(selectElem);
            select.selectByVisibleText("Еженедельно");
            for (String val:values)
                if (!val.equals("Еженедельно"))
                    click(val,"//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[@class='item' and text()='" + val + "']");
            click("Сохранить", Document.Createform.Erranddocument.reiterationrulesave_button);
        } else
        if (values[0].equals("Ежемесячно")){
            WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Erranddocument.reiterationruletype_select));
            Select select = new Select(selectElem);
            select.selectByVisibleText("Ежемесячно");
            for (String val:values)
                if (!val.equals("Ежемесячно"))
                    click(val,"//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[@class='item' and text()='" + val + "']");
            click("Сохранить", Document.Createform.Erranddocument.reiterationrulesave_button);
        } else
        if (values[0].equals("Ежеквартально")){
            WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Erranddocument.reiterationruletype_select));
            Select select = new Select(selectElem);
            select.selectByVisibleText("Ежеквартально");
            for (String val:values)
                if (!val.equals("Ежеквартально"))
                    click(val,"//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[@class='item' and text()='" + val + "']");
            click("Сохранить", Document.Createform.Erranddocument.reiterationrulesave_button);
        }
    }

    @Step("Заполнить атрибут {0} значением {1}")
    private static void periodend(String attrname, String[] values) {
        switch (values[0]){
            case "После":
                click("Радио",Document.Createform.Erranddocument.periodend_radiorepeatcount);
                settext("Срок", Document.Createform.Erranddocument.periodend_radiorepeatcount_field, values[1]);
                break;
            case "Через":
                click("Радио",Document.Createform.Erranddocument.periodend_radioduring);
                settext("Срок", Document.Createform.Erranddocument.periodend_radioduring_field, values[1]);
                WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Erranddocument.periodend_radioduring_select));
                Select select = new Select(selectElem);
                select.selectByVisibleText(values[2]);
                break;
            case "Нет конечной даты":
                click("Радио",Document.Createform.Erranddocument.periodend_radioendless);
                break;
            default:
                click("Радио",Document.Createform.Erranddocument.periodend_radiodate);
                settext("Срок", Document.Createform.Erranddocument.periodend_radiodate_field, values[0]);
                break;
        }
    }

    @Step("Заполнить атрибут {0} значением {2}")
    private static void fillselect(String attrname, String xpath, String[] values) {
        WebElement selectElem = driver.findElement(By.xpath(xpath));
        for (String val:values) {
            Select select = new Select(selectElem);
            select.selectByVisibleText(val);
        }
    }

    @Step("Заполнить атрибут {0} значением {2}")
    private static void fillsummary(String attrname, String xpath, String[] values) {
        driver.switchTo().frame(driver.findElement(By.xpath(Document.Createform.summarycontent_iframe)));
        for (String value : values)
            settext(attrname, xpath, value);
        driver.switchTo().defaultContent();
    }

    @Step("Заполнить атрибут {0} значением {1}")
    private static void filllimitationdate_resolutionerrand(String attrname, String[] values) {
        for (String value : values)
            if (value.contains("рабочий день")) {
                click("Радио",Document.Createform.Resolutionsdocument.Errands.limitationdate_radiodays);
                settext("Срок", Document.Createform.Resolutionsdocument.Errands.limitationdate_radiodays_field, value.substring(0,value.indexOf("рабочий день")-1));
                WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Resolutionsdocument.Errands.limitationdate_radiodays_select));
                Select select = new Select(selectElem);
                select.selectByVisibleText("рабочий день");
                doc.put(attrname,new String[]{value.substring(0,value.indexOf("рабочий день")-1) + " р.д."});
            } else
            if (value.contains("календарный день")) {
                click("Радио",Document.Createform.Resolutionsdocument.Errands.limitationdate_radiodays);
                settext("Срок", Document.Createform.Resolutionsdocument.Errands.limitationdate_radiodays_field, value.substring(0,value.indexOf("календарный день")-1));
                WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Resolutionsdocument.Errands.limitationdate_radiodays_select));
                Select select = new Select(selectElem);
                select.selectByVisibleText("календарный день");
                doc.put(attrname,new String[]{value.substring(0,value.indexOf("календарный день")-1) + " к.д."});
            } else
            if (value.contains("Без срока")) {
                click("Радио",Document.Createform.Resolutionsdocument.Errands.limitationdate_radiolimitless);
            } else {
                click("Радио",Document.Createform.Resolutionsdocument.Errands.limitationdate_radiodate);
                settext("Дата", Document.Createform.Resolutionsdocument.Errands.limitationdate_radiodate_field, value);
            }

    }

    @Step("Заполнить атрибут {0} значением {1}")
    private static void filllimitationdate_errand(String attrname, String[] values) {
        for (String value : values)
            if (value.contains("рабочий день")) {
                click("Радио",Document.Createform.Erranddocument.limitationdate_radiodays);
                settext("Срок", Document.Createform.Erranddocument.limitationdate_radiodays_field, value.substring(0,value.indexOf("рабочий день")-1));
                WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Erranddocument.limitationdate_radiodays_select));
                Select select = new Select(selectElem);
                select.selectByVisibleText("рабочий день");
                doc.put(attrname,new String[]{value.substring(0,value.indexOf("рабочий день")-1) + " р.д."});
            } else
            if (value.contains("календарный день")) {
                click("Радио",Document.Createform.Erranddocument.limitationdate_radiodays);
                settext("Срок", Document.Createform.Erranddocument.limitationdate_radiodays_field, value.substring(0,value.indexOf("календарный день")-1));
                WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Erranddocument.limitationdate_radiodays_select));
                Select select = new Select(selectElem);
                select.selectByVisibleText("календарный день");
                doc.put(attrname,new String[]{value.substring(0,value.indexOf("календарный день")-1) + " к.д."});
            } else
            if (value.contains("Без срока")) {
                click("Радио",Document.Createform.Erranddocument.limitationdate_radiolimitless);
            } else {
                click("Радио",Document.Createform.Erranddocument.limitationdate_radiodate);
                settext("Дата", Document.Createform.Erranddocument.limitationdate_radiodate_field, value);
            }
    }

    @Step("Заполнить атрибут {0} значением {1}")
    private static void filllimitationdate_point(String attrname, String[] values) {
        for (String value : values)
            if (value.contains("рабочий день")) {
                click("Радио",Document.Createform.Orddocument.Items.limitationdate_radiodays);
                settext("Срок", Document.Createform.Orddocument.Items.limitationdate_radiodays_field, value.substring(0,value.indexOf("рабочий день")-1));
                WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Orddocument.Items.limitationdate_radiodays_select));
                Select select = new Select(selectElem);
                select.selectByVisibleText("рабочий день");
            } else
            if (value.contains("календарный день")) {
                click("Радио",Document.Createform.Orddocument.Items.limitationdate_radiodays);
                settext("Срок", Document.Createform.Orddocument.Items.limitationdate_radiodays_field, value.substring(0,value.indexOf("календарный день")-1));
                WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Orddocument.Items.limitationdate_radiodays_select));
                Select select = new Select(selectElem);
                select.selectByVisibleText("календарный день");
            } else
            if (value.contains("Без срока")) {
                click("Радио",Document.Createform.Orddocument.Items.limitationdate_radiolimitless);
            } else {
                click("Радио",Document.Createform.Orddocument.Items.limitationdate_radiodate);
                settext("Срок", Document.Createform.Orddocument.Items.limitationdate_radiodate_field, value);
            }
    }

    @Step("Заполнить атрибут {0} значением {1}")
    private static void filllimitationdate_resolution(String attrname, String[] values) {
        for (String value : values)
            if (value.contains("рабочий день")) {
                click("Радио",Document.Createform.Resolutionsdocument.limitationdate_radiodays);
                settext("Срок", Document.Createform.Resolutionsdocument.limitationdate_radiodays_field, value.substring(0,value.indexOf("рабочий день")-1));
                WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Resolutionsdocument.limitationdate_radiodays_select));
                Select select = new Select(selectElem);
                select.selectByVisibleText("рабочий день");
                doc.put(attrname,new String[]{value.substring(0,value.indexOf("рабочий день")-1) + " р.д."});
            } else
            if (value.contains("календарный день")) {
                click("Радио",Document.Createform.Resolutionsdocument.limitationdate_radiodays);
                settext("Срок", Document.Createform.Resolutionsdocument.limitationdate_radiodays_field, value.substring(0,value.indexOf("календарный день")-1));
                WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Resolutionsdocument.limitationdate_radiodays_select));
                Select select = new Select(selectElem);
                select.selectByVisibleText("календарный день");
                doc.put(attrname,new String[]{value.substring(0,value.indexOf("календарный день")-1) + " к.д."});
            } else
            if (value.contains("Без срока")) {
                click("Радио",Document.Createform.Resolutionsdocument.limitationdate_radiolimitless);
            } else {
                click("Радио",Document.Createform.Resolutionsdocument.limitationdate_radiodate);
                settext("Дата", Document.Createform.Resolutionsdocument.limitationdate_radiodate_field, value);
            }
    }

    /*
        static void fillfield(String attrname, String xpath, String[] values, Map<String, String[]> doc) {
            String docum = null;
            for (String value : doc.get("document"))
                docum = value;

            switch (docum){
                case "incoming":
                    if (values != null) {
                        switch (attrname) {
                            case "Заголовок":
                            case "Исходящий номер":
                            case "Исходящий от":
                            case "Количество листов":
                            case "Примечание":
                            case "Срок исполнения":
                            case "Наименование":
                            case "ИНН":
                            case "КПП":
                                for (String value : values)
                                    settext(attrname, xpath, value);
                                break;
                            case "Вид документа":
                            case "Способ доставки":
                            case "Представитель корреспондента":
                            case "Тематика":
                                click("...", xpath, SelectDialog.Simple.dialog);
                                fillselectdialogsimple(attrname, doc, values);
                                break;
                            case "Корреспондент":
                                click("...", xpath, SelectDialog.Sender.dialog);
                                fillselectdialogsender(attrname, values, doc);
                                break;
                            case "Получатель":
                                click("...", xpath, SelectDialog.Recipient.dialog);
                                fillselectdialogrecipient(attrname, doc, values);
                                break;
                            case "В ответ на":
                                click("...", xpath, SelectDialog.Responseto.dialog);
                                fillselectdialogresponseto(attrname, doc, values);
                                break;
                            case "Содержание":
                                driver.switchTo().frame(driver.findElement(By.xpath(Document.Createform.Incomingdocument.summarycontent_iframe)));
                                for (String value : values)
                                    settext(attrname, xpath, value);
                                driver.switchTo().defaultContent();
                                break;
                            case "Номер дела":
                                click("...", xpath, SelectDialog.Fileregister.dialog);
                                fillselectdialogfileregister(attrname, doc, values);
                                break;
                            case "На контроле":
                            case "Нерегистрируемый":
                                for (String value : values)
                                    if (value.equals("Да") != driver.findElement(By.xpath(xpath)).isSelected())
                                        click(attrname, xpath);
                                break;
                            case "Вложения Входящий":
                            case "Вложения Прочее":
                                fillattachment(attrname,xpath,values);
                                break;
                            default:
                                softassertfail(attrname + " - Тест не знает такого атрибута, надо дописать");
                                break;
                        }
                    }else
                        switch (attrname){
                            case "Повторять":
                                break;
                            default:
                                doc.put(attrname, new String[]{"(Нет)"});
                                break;
                        }
                    break;
                case "internal":
                    if (values != null) {
                        switch (attrname) {
                            case "Заголовок":
                            case "Срок ответа":
                            case "Дата подписания":
                            case "Количество листов":
                            case "Примечание":
                                for (String value : values)
                                    settext(attrname, xpath, value);
                                break;
                            case "Вид документа":
                            case "Подписанты":
                            case "Тематика":
                                click("...", xpath, SelectDialog.Simple.dialog);
                                fillselectdialogsimple(attrname, doc, values);
                                break;
                            case "Получатель":
                                click("...", xpath, SelectDialog.Recipient.dialog);
                                fillselectdialogrecipient(attrname, doc, values);
                                break;
                            case "Содержание":
                                driver.switchTo().frame(driver.findElement(By.xpath(Document.Createform.Incomingdocument.summarycontent_iframe)));
                                for (String value : values)
                                    settext(attrname, xpath, value);
                                driver.switchTo().defaultContent();
                                break;
                            case "Подписано на бумажном носителе":
                                for (String value : values)
                                    if (value.equals("Да") != driver.findElement(By.xpath(xpath)).isSelected())
                                        click(attrname, xpath);
                                break;
                            case "В ответ на":
                                click("...", xpath, SelectDialog.Responseto.dialog);
                                fillselectdialogresponseto(attrname, doc, values);
                                break;
                            case "Номер дела":
                                click("...", xpath, SelectDialog.Fileregister.dialog);
                                fillselectdialogfileregister(attrname, doc, values);
                                break;
                            default:
                                softassertfail(attrname + " - Тест не знает такого атрибута, надо дописать");
                                break;
                        }
                    }else
                        switch (attrname){
                            case "Повторять":
                                break;
                            default:
                                doc.put(attrname, new String[]{"(Нет)"});
                                break;
                        }
                    break;
                case "outgoing":
                    if (values != null) {
                        switch (attrname) {
                            case "Заголовок":
                            case "Дата подписания":
                            case "Количество листов":
                            case "Примечание":
                            case "Наименование":
                            case "ИНН":
                            case "КПП":
                                for (String value : values)
                                    settext(attrname, xpath, value);
                                break;
                            case "Вид документа":
                            case "Способ доставки":
                            case "Адресат корреспондента":
                            case "Подписанты":
                            case "Тематика":
                                click("...", xpath, SelectDialog.Simple.dialog);
                                fillselectdialogsimple(attrname, doc, values);
                                break;
                            case "Корреспондент":
                                click("...", xpath, SelectDialog.Sender.dialog);
                                fillselectdialogsender(attrname, values, doc);
                                break;
                            case "Содержание":
                                driver.switchTo().frame(driver.findElement(By.xpath(Document.Createform.Incomingdocument.summarycontent_iframe)));
                                for (String value : values)
                                    settext(attrname, xpath, value);
                                driver.switchTo().defaultContent();
                                break;
                            case "Подписано на бумажном носителе":
                            case "Завершающий":
                                for (String value : values)
                                    if (value.equals("Да") != driver.findElement(By.xpath(xpath)).isSelected())
                                        click(attrname, xpath);
                                break;
                            case "В ответ на":
                                click("...", xpath, SelectDialog.Responseto.dialog);
                                fillselectdialogresponseto(attrname, doc, values);
                                break;
                            case "Номер дела":
                                click("...", xpath, SelectDialog.Fileregister.dialog);
                                fillselectdialogfileregister(attrname, doc, values);
                                break;
                            default:
                                softassertfail(attrname + " - Тест не знает такого атрибута, надо дописать");
                                break;
                        }
                    }else
                        switch (attrname){
                            case "Повторять":
                                break;
                            default:
                                doc.put(attrname, new String[]{"(Нет)"});
                                break;
                        }
                    break;
                case "nd":
                    if (values != null) {
                        switch (attrname) {
                            case "Заголовок":
                            case "Количество листов":
                            case "Дата подписания":
                            case "Примечание":
                            case "Период действия С":
                            case "Период действия По":
                                for (String value : values)
                                    settext(attrname, xpath, value);
                                break;
                            case "Вид документа":
                            case "Подписанты":
                            case "Тематика":
                            case "Подразделения":
                                click("...", xpath, SelectDialog.Simple.dialog);
                                fillselectdialogsimple(attrname, doc, values);
                                break;
                            case "Содержание":
                                driver.switchTo().frame(driver.findElement(By.xpath(Document.Createform.Incomingdocument.summarycontent_iframe)));
                                for (String value : values)
                                    settext(attrname, xpath, value);
                                driver.switchTo().defaultContent();
                                break;
                            case "Подписано на бумажном носителе":
                            case "Бессрочный":
                                for (String value : values)
                                    if (value.equals("Да") != driver.findElement(By.xpath(xpath)).isSelected())
                                        click(attrname, xpath);
                                break;
                            case "Номер дела":
                                click("...", xpath, SelectDialog.Fileregister.dialog);
                                fillselectdialogfileregister(attrname, doc, values);
                                break;
                            default:
                                softassertfail(attrname + " - Тест не знает такого атрибута, надо дописать");
                                break;
                        }
                    }else
                        switch (attrname){
                            case "Повторять":
                                break;
                            default:
                                doc.put(attrname, new String[]{"(Нет)"});
                                break;
                        }
                    break;
                case "ord":
                    if (values != null) {
                        switch (attrname) {
                            case "Заголовок":
                            case "Срок исполнения":
                            case "Дата подписания":
                            case "Примечание":
                            case "Количество листов":
                            case "Пункты Заголовок":
                            case "Пункты Содержание":
                                for (String value : values)
                                    settext(attrname, xpath, value);
                                break;
                            case "Вид документа":
                            case "Подписанты":
                            case "Контролёр":
                            case "Тематика":
                            case "Пункты Автор":
                            case "Пункты Исполнитель":
                            case "Пункты Соисполнители":
                            case "Пункты Контролер":
                            case "Пункты Тематика":
                                click("...", xpath, SelectDialog.Simple.dialog);
                                fillselectdialogsimple(attrname, doc, values);
                                break;
                            case "Содержание":
                                driver.switchTo().frame(driver.findElement(By.xpath(Document.Createform.Incomingdocument.summarycontent_iframe)));
                                for (String value : values)
                                    settext(attrname, xpath, value);
                                driver.switchTo().defaultContent();
                                break;
                            case "Подписано на бумажном носителе":
                            case "Подтверждать завершение работы по документу":
                            case "Пункты Требуется отчет":
                                for (String value : values)
                                    if (value.equals("Да") != driver.findElement(By.xpath(xpath)).isSelected())
                                        click(attrname, xpath);
                                break;
                            case "Номер дела":
                                click("...", xpath, SelectDialog.Fileregister.dialog);
                                fillselectdialogfileregister(attrname, doc, values);
                                break;
                            case "Отменяемые документы":
                            case "Принимаемые документы":
                                click("...", xpath, SelectDialog.Responseto.dialog);
                                fillselectdialogresponseto(attrname, doc, values);
                                break;
                            case "Пункты Срок исполнения":
                                for (String value : values)
                                    if (value.contains("рабочий день")) {
                                        click("Радио",Document.Createform.Orddocument.Items.limitationdate_radiodays);
                                        settext("Срок", Document.Createform.Orddocument.Items.limitationdate_radiodays_field, value.substring(0,value.indexOf("рабочий день")-1));
                                        WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Orddocument.Items.limitationdate_radiodays_select));
                                        Select select = new Select(selectElem);
                                        select.selectByVisibleText("рабочий день");
                                    } else
                                    if (value.contains("календарный день")) {
                                        click("Радио",Document.Createform.Orddocument.Items.limitationdate_radiodays);
                                        settext("Срок", Document.Createform.Orddocument.Items.limitationdate_radiodays_field, value.substring(0,value.indexOf("календарный день")-1));
                                        WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Orddocument.Items.limitationdate_radiodays_select));
                                        Select select = new Select(selectElem);
                                        select.selectByVisibleText("календарный день");
                                    } else
                                    if (value.contains("Без срока")) {
                                        click("Радио",Document.Createform.Orddocument.Items.limitationdate_radiolimitless);
                                    } else {
                                        click("Радио",Document.Createform.Orddocument.Items.limitationdate_radiodate);
                                        settext("Срок", Document.Createform.Orddocument.Items.limitationdate_radiodate_field, value);
                                    }
                                break;
                            default:
                                softassertfail(attrname + " - Тест не знает такого атрибута, надо дописать");
                                break;
                        }
                    }else
                        switch (attrname){
                            case "Повторять":
                                break;
                            default:
                                doc.put(attrname, new String[]{"(Нет)"});
                                break;
                        }
                    break;
                case "errand":
                    if (values != null) {
                        switch (attrname) {
                            case "Текст поручения":
                            case "Начало повторений":
                                for (String value : values)
                                    settext(attrname, xpath, value);
                                break;
                            case "Тип поручения":
                            case "Заголовок":
                            case "Исполнитель":
                            case "Соисполнители":
                            case "Контролер":
                            case "Тематика":
                                click("...", xpath, SelectDialog.Simple.dialog);
                                fillselectdialogsimple(attrname, doc, values);
                                break;
                            case "Срок исполнения":
                                for (String value : values)
                                    if (value.contains("рабочий день")) {
                                        click("Радио",Document.Createform.Erranddocument.limitationdate_radiodays);
                                        settext("Срок", Document.Createform.Erranddocument.limitationdate_radiodays_field, value.substring(0,value.indexOf("рабочий день")-1));
                                        WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Erranddocument.limitationdate_radiodays_select));
                                        Select select = new Select(selectElem);
                                        select.selectByVisibleText("рабочий день");
                                        doc.put(attrname,new String[]{value.substring(0,value.indexOf("рабочий день")-1) + " р.д."});
                                    } else
                                    if (value.contains("календарный день")) {
                                        click("Радио",Document.Createform.Erranddocument.limitationdate_radiodays);
                                        settext("Срок", Document.Createform.Erranddocument.limitationdate_radiodays_field, value.substring(0,value.indexOf("календарный день")-1));
                                        WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Erranddocument.limitationdate_radiodays_select));
                                        Select select = new Select(selectElem);
                                        select.selectByVisibleText("календарный день");
                                        doc.put(attrname,new String[]{value.substring(0,value.indexOf("календарный день")-1) + " к.д."});
                                    } else
                                    if (value.contains("Без срока")) {
                                        click("Радио",Document.Createform.Erranddocument.limitationdate_radiolimitless);
                                    } else {
                                        click("Радио",Document.Createform.Erranddocument.limitationdate_radiodate);
                                        settext("Дата", Document.Createform.Erranddocument.limitationdate_radiodate_field, value);
                                    }
                                break;
                            case "Окончание повторений":
                                switch (values[0]){
                                    case "После":
                                        click("Радио",Document.Createform.Erranddocument.periodend_radiorepeatcount);
                                        settext("Срок", Document.Createform.Erranddocument.periodend_radiorepeatcount_field, values[1]);
                                        break;
                                    case "Через":
                                        click("Радио",Document.Createform.Erranddocument.periodend_radioduring);
                                        settext("Срок", Document.Createform.Erranddocument.periodend_radioduring_field, values[1]);
                                        WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Erranddocument.periodend_radioduring_select));
                                        Select select = new Select(selectElem);
                                        select.selectByVisibleText(values[2]);
                                        break;
                                    case "Нет конечной даты":
                                        click("Радио",Document.Createform.Erranddocument.periodend_radioendless);
                                        break;
                                    default:
                                        click("Радио",Document.Createform.Erranddocument.periodend_radiodate);
                                        settext("Срок", Document.Createform.Erranddocument.periodend_radiodate_field, values[0]);
                                        break;
                                }
                                break;
                            case "Требуется отчет":
                            case "Важное":
                                for (String value : values)
                                    if (value.equals("Да") != driver.findElement(By.xpath(xpath)).isSelected())
                                        click(attrname, xpath);
                                break;
                            case "Повторять":
                                if (values[0].equals("Еженедельно")){
                                    WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Erranddocument.reiterationruletype_select));
                                    Select select = new Select(selectElem);
                                    select.selectByVisibleText("Еженедельно");
                                    for (String val:values)
                                        if (!val.equals("Еженедельно"))
                                            click(val,"//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[@class='item' and text()='" + val + "']");
                                    click("Сохранить", Document.Createform.Erranddocument.reiterationrulesave_button);
                                } else
                                if (values[0].equals("Ежемесячно")){
                                    WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Erranddocument.reiterationruletype_select));
                                    Select select = new Select(selectElem);
                                    select.selectByVisibleText("Ежемесячно");
                                    for (String val:values)
                                        if (!val.equals("Ежемесячно"))
                                            click(val,"//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[@class='item' and text()='" + val + "']");
                                    click("Сохранить", Document.Createform.Erranddocument.reiterationrulesave_button);
                                } else
                                if (values[0].equals("Ежеквартально")){
                                    WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Erranddocument.reiterationruletype_select));
                                    Select select = new Select(selectElem);
                                    select.selectByVisibleText("Ежеквартально");
                                    for (String val:values)
                                        if (!val.equals("Ежеквартально"))
                                            click(val,"//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[@class='item' and text()='" + val + "']");
                                    click("Сохранить", Document.Createform.Erranddocument.reiterationrulesave_button);
                                }
                                break;
                            case "Направлять периодически":
                                for (String value : values)
                                    if (value.equals("Да") != driver.findElement(By.xpath(xpath)).isSelected())
                                        click(attrname, xpath);
                                break;
                            case "Получатель отчета":
                                WebElement selectElem = driver.findElement(By.xpath(xpath));
                                for (String val:values) {
                                    Select select = new Select(selectElem);
                                    select.selectByVisibleText(val);
                                }
                                break;
                            default:
                                softassertfail(attrname + " - Тест не знает такого атрибута, надо дописать");
                                break;
                        }
                    }else
                        switch (attrname){
                            case "Закрывает вышестоящее поручение":
                            case "Направлять периодически":
                            case "Требуется отчет":
                                break;
                            default:
                                doc.put(attrname, new String[]{"(Нет)"});
                                break;
                        }
                    break;
                case "resolutions":
                    if (values != null) {
                        switch (attrname) {
                            case "Текст поручения":
                            case "Начало повторений":
                                for (String value : values)
                                    settext(attrname, xpath, value);
                                break;
                            case "Тематика":
                            case "Автор":
                            case "Контролер":
                            case "Поручения Тип поручения":
                            case "Поручения Заголовок":
                            case "Поручения Исполнитель":
                            case "Поручения Соисполнители":
                            case "Поручения Контролер":
                                click("...", xpath, SelectDialog.Simple.dialog);
                                fillselectdialogsimple(attrname, doc, values);
                                break;
                            case "Срок исполнения":
                                for (String value : values)
                                    if (value.contains("рабочий день")) {
                                        click("Радио",Document.Createform.Resolutionsdocument.limitationdate_radiodays);
                                        settext("Срок", Document.Createform.Resolutionsdocument.limitationdate_radiodays_field, value.substring(0,value.indexOf("рабочий день")-1));
                                        WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Resolutionsdocument.limitationdate_radiodays_select));
                                        Select select = new Select(selectElem);
                                        select.selectByVisibleText("рабочий день");
                                        doc.put(attrname,new String[]{value.substring(0,value.indexOf("рабочий день")-1) + " р.д."});
                                    } else
                                    if (value.contains("календарный день")) {
                                        click("Радио",Document.Createform.Resolutionsdocument.limitationdate_radiodays);
                                        settext("Срок", Document.Createform.Resolutionsdocument.limitationdate_radiodays_field, value.substring(0,value.indexOf("календарный день")-1));
                                        WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Resolutionsdocument.limitationdate_radiodays_select));
                                        Select select = new Select(selectElem);
                                        select.selectByVisibleText("календарный день");
                                        doc.put(attrname,new String[]{value.substring(0,value.indexOf("календарный день")-1) + " к.д."});
                                    } else
                                    if (value.contains("Без срока")) {
                                        click("Радио",Document.Createform.Resolutionsdocument.limitationdate_radiolimitless);
                                    } else {
                                        click("Радио",Document.Createform.Resolutionsdocument.limitationdate_radiodate);
                                        settext("Дата", Document.Createform.Resolutionsdocument.limitationdate_radiodate_field, value);
                                    }
                                break;
                            case "Поручения Срок исполнения":
                                for (String value : values)
                                    if (value.contains("рабочий день")) {
                                        click("Радио",Document.Createform.Resolutionsdocument.Errands.limitationdate_radiodays);
                                        settext("Срок", Document.Createform.Resolutionsdocument.Errands.limitationdate_radiodays_field, value.substring(0,value.indexOf("рабочий день")-1));
                                        WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Resolutionsdocument.Errands.limitationdate_radiodays_select));
                                        Select select = new Select(selectElem);
                                        select.selectByVisibleText("рабочий день");
                                        doc.put(attrname,new String[]{value.substring(0,value.indexOf("рабочий день")-1) + " р.д."});
                                    } else
                                    if (value.contains("календарный день")) {
                                        click("Радио",Document.Createform.Resolutionsdocument.Errands.limitationdate_radiodays);
                                        settext("Срок", Document.Createform.Resolutionsdocument.Errands.limitationdate_radiodays_field, value.substring(0,value.indexOf("календарный день")-1));
                                        WebElement selectElem = driver.findElement(By.xpath(Document.Createform.Resolutionsdocument.Errands.limitationdate_radiodays_select));
                                        Select select = new Select(selectElem);
                                        select.selectByVisibleText("календарный день");
                                        doc.put(attrname,new String[]{value.substring(0,value.indexOf("календарный день")-1) + " к.д."});
                                    } else
                                    if (value.contains("Без срока")) {
                                        click("Радио",Document.Createform.Resolutionsdocument.Errands.limitationdate_radiolimitless);
                                    } else {
                                        click("Радио",Document.Createform.Resolutionsdocument.Errands.limitationdate_radiodate);
                                        settext("Дата", Document.Createform.Resolutionsdocument.Errands.limitationdate_radiodate_field, value);
                                    }
                                break;
                            case "Поручения Требуется отчет":
                            case "Утверждено вне системы":
                            case "Контроль":
                                for (String value : values)
                                    if (value.equals("Да") != driver.findElement(By.xpath(xpath)).isSelected())
                                        click(attrname, xpath);
                                break;
                            case "Завершающий":
                            case "Поручения Получатель отчета":
                                WebElement selectElem = driver.findElement(By.xpath(xpath));
                                for (String val:values) {
                                    Select select = new Select(selectElem);
                                    select.selectByVisibleText(val);
                                }
                                break;
                            default:
                                softassertfail(attrname + " - Тест не знает такого атрибута, надо дописать");
                                break;
                        }
                    }else
                        switch (attrname){
                            case "Утверждено вне системы":
                            case "Контроль":
                                doc.put(attrname, new String[]{"Нет"});
                                break;
                            default:
                                doc.put(attrname, new String[]{"(Нет)"});
                                break;
                        }
                    break;
                case "protocol":
                    if (values != null) {
                        switch (attrname) {
                            case "Заголовок":
                            case "Срок исполнения":
                            case "Примечание":
                            case "Количество листов":
                            case "Пункт Формулировка":
                            case "Пункт Описание":
                            case "Пункт Выступили":
                            case "Пункт Решение":
                            case "Пункт Срок исполнения":
                            case "Пункт Примечание":
                                for (String value : values)
                                    settext(attrname, xpath, value);
                                break;
                            case "Вид документа":
                            case "Согласующие":
                            case "Председатель совещания":
                            case "Секретарь":
                            case "Присутствовали":
                            case "Тематика":
                            case "Пункт Исполнитель":
                                click("...", xpath, SelectDialog.Simple.dialog);
                                fillselectdialogsimple(attrname, doc, values);
                                break;
                            case "Содержание":
                                driver.switchTo().frame(driver.findElement(By.xpath(Document.Createform.Protocoldocument.summarycontent_iframe)));
                                for (String value : values)
                                    settext(attrname, xpath, value);
                                driver.switchTo().defaultContent();
                                break;
                            case "Подписано на бумажном носителе":
                            case "Автосоздание поручений":
                                for (String value : values)
                                    if (value.equals("Да") != driver.findElement(By.xpath(xpath)).isSelected())
                                        click(attrname, xpath);
                                break;
                            case "Номер дела":
                                click("...", xpath, SelectDialog.Fileregister.dialog);
                                fillselectdialogfileregister(attrname, doc, values);
                                break;
                            case "Пункт Докладчик":
                            case "Пункт Содокладчики":
                                click("...", xpath, SelectDialog.Reporter.dialog);
                                fillselectdialogreporter(attrname, doc, values);
                                break;
                            default:
                                softassertfail(attrname + " - Тест не знает такого атрибута, надо дописать");
                                break;
                        }
                    }else
                        switch (attrname){
                            case "Утверждено вне системы":
                            case "Контроль":
                                doc.put(attrname, new String[]{"Нет"});
                                break;
                            default:
                                doc.put(attrname, new String[]{"(Нет)"});
                                break;
                        }
                    break;
                case "approval":
                    if (values != null) {
                        switch (attrname) {
                            case "Согласование Завершать после первого отклонения согласующим":
                            case "Согласование Уведомлять о каждой рецензии":
                            case "Использовать правило для этапа":
                                for (String value : values)
                                    if (value.equals("Да") != driver.findElement(By.xpath(xpath)).isSelected())
                                        click(attrname, xpath);
                                break;
                            case "Согласование По истечении срока":
                            case "Тип этапа":
                                WebElement selectElem = driver.findElement(By.xpath(xpath));
                                for (String val : values) {
                                    Select select = new Select(selectElem);
                                    select.selectByVisibleText(val);
                                }
                                break;
                            case "Название этапа":
                            case "Срок по умолчанию для согласующего (р. д.)":
                                for (String value : values)
                                    settext(attrname, xpath, value);
                                break;
                            case "Правило для этапа":
                                click("...", xpath, SelectDialog.Simple.dialog);
                                fillselectdialogsimple(attrname, doc, values);
                                break;
                            case "Согласующие":
                                click("...", xpath, SelectDialog.Approve.dialog);
                                fillselectdialogapprove(attrname, doc, values);
                                break;
                            default:
                                softassertfail(attrname + " - Тест не знает такого атрибута, надо дописать");
                                break;
                        }
                    }else
                        switch (attrname){
                            case "Утверждено вне системы":
                            case "Контроль":
                                doc.put(attrname, new String[]{"Нет"});
                                break;
                            default:
                                doc.put(attrname, new String[]{"(Нет)"});
                                break;
                        }
                    break;
                default:
                    softassertfail(attrname + " - Тест не знает такого типа, надо дописать");
                    break;
            }
        }
    */
    @Step("Проверить наличие атрибута <{0}>")
    static void verifyattr(String report, String xpath) {
        waitForLoad();
        try {
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            softassertfail("Не найден элемент " + xpath);
        }
        waitForLoad();
    }

    static Boolean waitelement(String xpath, boolean... flag) {
        waitForLoad();
        try {
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception e) {
            if (flag.length == 0)
                softassertfail("Не найден элемент " + xpath);
            return false;
        }
        waitForLoad();
        return true;
    }

    @Step("Заполнить атрибут <{0}> значением <{2}>")
    static void settext(String attrname, String xpath, String text) {
        waitForLoad();
        try {
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception e) {
            hardassertfail("Не найден элемент " + xpath);
        }
        driver.findElement(By.xpath(xpath)).clear();
        driver.findElement(By.xpath(xpath)).sendKeys(text);
        waitForLoad();
    }

    @Step("Нажать кнопку <{0}>")
    static void click(String report, String xpath) {
        waitForLoad();
        if (xpath.contains("Копировать документ"))
            driver.executeScript("scroll(10, 10);");
        try {
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            hardassertfail("Не найден элемент " + xpath);
        }
        try {
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception e) {
            hardassertfail("Не кликабелен элемент " + xpath);
        }
        driver.findElement(By.xpath(xpath)).click();
        waitForLoad();
    }

    static void click(String report, String xpath, String waitdialog) {
        click(report, xpath);
        int i = 10;
        while (!waitelement(waitdialog,false) && i > 0) {
            clickagain(report, xpath);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i--;
        }
        if (!waitelement(waitdialog,false))
            hardassertfail("Не удалось создать документ");
    }

    @Step("Переклик кнопки <{0}>")
    private static void clickagain(String report, String xpath) {
        waitForLoad();
        try {
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            hardassertfail("Не найден элемент " + xpath);
        }
        try {
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception e) {
            hardassertfail("Не кликабелен элемент " + xpath);
        }
        driver.findElement(By.xpath(xpath)).click();
        waitForLoad();
    }

    @Step("Развернуть блок {0}")
    static void openrightblock(String value){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(righblocktitle(value)))).build().perform();
        click("Развернуть", righblockopen(value));
    }

    @Step("Проверить наличие записей в бж")
    static void readhistory(String[] values, Map<String, String[]> doc){
        openrightblock("История");
        waitelement(Document.table_history);
        waitForLoad();
        String dynamicXPath = "//td[contains(.,\'%s\')]";
        for (String value: values)
            readhistoryitem(value, String.format(dynamicXPath, value));
    }

    @Step("Запись: {0}")
    static void readhistoryitem(String report, String historyitem){
        waitelement(historyitem);
    }

    @Step("Выполнить действие {0}")
    static void righactionexecute(String action, String approve){
        click(action, righaction(action));
        click(approve,approveaction(approve));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForLoad();
    }

    @Step("Выполнить действие {0}")
    static void righactionexecute(String action, String approve, String status, HashMap<String, String[]> doc){
        clickaction(action, righaction(action));
        if (action.equals("Направить адресатам")) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        clickactionapprove(approve,approveaction(approve));
        boolean t = false;
        waitForLoad();
        waitelement(Document.Viewform.status_field);
        int i = 10;
        while (i > 0 && !t){
            try {
                Thread.sleep(1000);
                waitForLoad();
                t = driver.findElement(By.xpath(Document.Viewform.status_field)).getText().equals(status);
            } catch (Exception e) {
                //e.printStackTrace();
            }
            waitForLoad();
            i--;
        }
        doc.put("Статус", new String[]{status});
    }

    @Step("Выполнить действие <{0}>")
    static void clickaction(String report, String xpath) {
        waitForLoad();
        try {
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            hardassertfail("Не найден элемент " + xpath);
        }
        try {
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception e) {
            hardassertfail("Не кликабелен элемент " + xpath);
        }
        driver.findElement(By.xpath(xpath)).click();
        waitForLoad();
    }

    @Step("Нажать кнопку <{0}> на форме выполнения действия")
    static void clickactionapprove(String report, String xpath) {
        waitForLoad();
        try {
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            hardassertfail("Не найден элемент " + xpath);
        }
        try {
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception e) {
            hardassertfail("Не кликабелен элемент " + xpath);
        }
        driver.findElement(By.xpath(xpath)).click();
        waitForLoad();
    }

    @Step("Перейти на вкладку {0}")
    static void changetab(String tab, HashMap<String, String[]> doc) {
        switch (doc.get("document")[0]){
            case "internal":
                switch (tab){
                    case "Атрибуты":
                        click(tab,Document.Viewform.Internaldocument.tab_common);
                        break;
                    case "Согласование и подписание":
                        click(tab,Document.Viewform.Internaldocument.tab_approvalAndSigning);
                        break;
                    case "Ответы":
                        click(tab,Document.Viewform.Internaldocument.tab_answers);
                        break;
                    case "Исполнение":
                        click(tab,Document.Viewform.Internaldocument.tab_executiontab);
                        break;
                    default:
                        softassertfail(tab + " - Тест не знает такой вкладки, надо дописать");
                        break;
                }
                break;
            default:
                softassertfail(doc.get("document")[0] + " - Тест не знает такого типа документа, надо дописать");
                break;
        }
    }

    @Step("Добавить маршрут согласования")
    static void approvaladd(HashMap<String, String[]> doc, HashMap<String, HashMap<String, String[]>> approval) {
        click("Создать маршрут", Document.Createform.Approval.createroute_button);
        if (doc.get("Согласование")[0].equals("Нетиповой")){
            click("Нетиповой", Document.Createform.Approval.nottipical_button);

            String typebuf = doc.get("document")[0];
            doc.put("document", new String[]{"approval"});

            verifyattr("Согласование Завершать после первого отклонения согласующим", Document.Createform.Approval.finafterfirstno_label);
            fillfield("Согласование Завершать после первого отклонения согласующим",Document.Createform.Approval.finafterfirstno_checkbox, doc.get("Согласование Завершать после первого отклонения согласующим"), doc);

            verifyattr("Согласование Уведомлять о каждой рецензии", Document.Createform.Approval.notifyaboutevery_label);
            fillfield("Согласование Уведомлять о каждой рецензии",Document.Createform.Approval.notifyaboutevery_checkbox, doc.get("Согласование Уведомлять о каждой рецензии"), doc);

            verifyattr("Согласование По истечении срока", Document.Createform.Approval.afterApprovalExpired_label);
            fillfield("Согласование По истечении срока",Document.Createform.Approval.afterApprovalExpired_select, doc.get("Согласование По истечении срока"), doc);

            doc.put("document", new String[]{typebuf});

            click("Сохранить",Document.Createform.Approval.saveroute_button);

            if (!approval.isEmpty())
                additemsapproval(approval);
        }
    }

    @Step("Добавить этапы")
    private static void additemsapproval(HashMap<String, HashMap<String, String[]>> approval) {
        int k = approval.size();
        for (int i=0; i<k; i++) {
            if (i!=0)
                click("Добавить этап",Document.Createform.Approval.additem_button,Document.Createform.Approval.title_label);
            approvalitem = approval.get(Integer.toString(i+1));
            fillitemapproval(i+1,approvalitem);
        }
    }

    @Step("Заполнить этап {0}")
    private static void fillitemapproval(Integer i, HashMap<String, String[]> approvalitem) {
        verifyattr("Название этапа", Document.Createform.Approval.title_label);
        fillfield("Название этапа", Document.Createform.Approval.title_field, approvalitem.get("Название этапа"), approvalitem);

        verifyattr("Тип этапа", Document.Createform.Approval.type_label);
        fillfield("Тип этапа", Document.Createform.Approval.type_select, approvalitem.get("Тип этапа"), approvalitem);

        verifyattr("Срок по умолчанию для согласующего (р. д.)", Document.Createform.Approval.temporary_label);
        fillfield("Срок по умолчанию для согласующего (р. д.)", Document.Createform.Approval.temporary_field, approvalitem.get("Срок по умолчанию для согласующего (р. д.)"), approvalitem);

        verifyattr("Использовать правило для этапа", Document.Createform.Approval.userule_label);
        fillfield("Использовать правило для этапа", Document.Createform.Approval.userule_checkbox, approvalitem.get("Использовать правило для этапа"), approvalitem);

        verifyattr("Правило для этапа", Document.Createform.Approval.rule_label);
        if (approvalitem.get("Использовать правило для этапа")[0].equals("Да"))
            fillfield("Правило для этапа", Document.Createform.Approval.rule_button, approvalitem.get("Правило для этапа"), approvalitem);

        verifyattr("Согласующие", Document.Createform.Approval.approvals_label);
        if (approvalitem.get("Использовать правило для этапа")[0].equals("Нет"))
            fillfield("Согласующие", Document.Createform.Approval.approvals_button, approvalitem.get("Согласующие"), approvalitem);

        click("Сохранить",Document.Createform.Approval.saverouteitem_button);
    }

    @Step("Проверить налаичие уведомления у пользователя {0}")
    static void readnotification(String val, String notification) {
        User user = getuserbyfull(val);
        auth(user.famio,user.login,user.pass);
        click("Уведомления", Objects.MenuBar.notifications, Objects.MenuBar.notifications_check_opened);
        waitnotification(notification);
    }

    @Step("Уведомление <{0}>")
    static void waitnotification(String notification) {
        waitForLoad();
        String dynamicXPath = "//td[contains(.,'%s')]";
        String xpath = String.format(dynamicXPath, notification);
        try {
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception e) {
            softassertfail("Уведомление не найдено");
            e.printStackTrace();
        }
        waitForLoad();
    }

    @Step("Поиск письма на почте {0}")
    static void ReadEmail(String email, String pass, String message){
        String currenturl = driver.getCurrentUrl();
        driver.get("http://mail.alf.datateh.ru/#/mailbox/INBOX");
        boolean t = false;
        int i = 1;
        timeoutlnseconds = 5;
        settext("Почта","//input[@name='RainLoopEmail']",email);
        settext("Пароль","//input[@name='RainLoopPassword']",pass);
        click("Войти","//button[@type='submit' and contains(@class,'submit')]");
        driver.get("http://mail.alf.datateh.ru/#/mailbox/INBOX");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!t && i <= 10){
            click("Письмо" + i,"//div[contains(@class,'messageListItem')][" + i + "]");
            t = waitelement("//div[contains(@id,'mgs')]//div[contains(.,'" + message + "')]", false) || waitelement("//td[contains(.,'" + message + "')]", false);
            i++;
        }
        saveAllureText(message);
        softassertfail(t,"Письмо не найдено");
        driver.get(currenturl);
        timeoutlnseconds = 10;
    }
}
