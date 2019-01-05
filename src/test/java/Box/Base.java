package Box;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Box.Objects.*;
import static Box.About.*;
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
        click("Ад ф-ии",Document.admin_function);
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
    private static void gotoarmsed() {
        click("Логика: СЭД", MenuBar.logsed);
        timeoutlnseconds = 120;
        waitelement(ARMSED.createButton);
        timeoutlnseconds = 30;
    }

    @Step("Создать входящий документ")
    static void createincoming(Map<String, String[]> doc) {
        gotoarmsed();
        click("Создать",ARMSED.createButton);
        click("Входящий документ", ARMSED.Createmenu.incomingdocument);
        fillcreateincoming(doc);
        String currenturl = driver.getCurrentUrl();
        click("Создать",Document.Createform.create_button);
        while (currenturl.equals(driver.getCurrentUrl())) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        doc.put("Статус",new String[]{"Черновик"});
        doc.put("Номер",new String[]{"Не присвоено"});
        doc.put("Запись в бж",new String[]{historystandartcreate(doc)});
    }

    @Step("Заполнить атрибуты")
    private static void fillcreateincoming(Map<String, String[]> doc) {
        verifyattr("Вложения", Document.Createform.Incomingdocument.attachments_label);

        verifyattr("Заголовок", Document.Createform.Incomingdocument.title_label);
        fillfield("Заголовок",Document.Createform.Incomingdocument.title_field, doc.get("Заголовок"), doc);

        verifyattr("Вид документа", Document.Createform.Incomingdocument.type_label);
        fillfield("Вид документа",Document.Createform.Incomingdocument.type_button, doc.get("Вид документа"), doc);

        verifyattr("Способ доставки", Document.Createform.Incomingdocument.delivery_method_label);
        fillfield("Способ доставки",Document.Createform.Incomingdocument.delivery_method_button, doc.get("Способ доставки"), doc);

        verifyattr("Корреспондент", Document.Createform.Incomingdocument.sender_label);
        fillfield("Корреспондент",Document.Createform.Incomingdocument.sender_button, doc.get("Корреспондент"), doc);

        verifyattr("Представитель корреспондента", Document.Createform.Incomingdocument.addressee_label);
        fillfield("Представитель корреспондента",Document.Createform.Incomingdocument.addressee_button, doc.get("Представитель корреспондента"), doc);

        verifyattr("Получатель", Document.Createform.Incomingdocument.recipient_label);
        fillfield("Получатель",Document.Createform.Incomingdocument.recipient_button, doc.get("Получатель"), doc);

        verifyattr("В ответ на", Document.Createform.Incomingdocument.response_to_label);
        fillfield("В ответ на",Document.Createform.Incomingdocument.response_to_button, doc.get("В ответ на"), doc);

        verifyattr("Исходящий номер", Document.Createform.Incomingdocument.outgoing_number_label);
        fillfield("Исходящий номер",Document.Createform.Incomingdocument.outgoing_number_field, doc.get("Исходящий номер"), doc);

        verifyattr("Исходящий от", Document.Createform.Incomingdocument.outgoing_date_label);
        fillfield("Исходящий от",Document.Createform.Incomingdocument.outgoing_date_field, doc.get("Исходящий от"), doc);

        verifyattr("Содержание", Document.Createform.Incomingdocument.summarycontent_label);
        fillfield("Содержание",Document.Createform.Incomingdocument.summarycontent_field, doc.get("Содержание"), doc);

        verifyattr("Количество листов", Document.Createform.Incomingdocument.sheets_number_label);
        fillfield("Количество листов",Document.Createform.Incomingdocument.sheets_number_field, doc.get("Количество листов"), doc);

        verifyattr("Тематика", Document.Createform.Incomingdocument.subject_label);
        fillfield("Тематика",Document.Createform.Incomingdocument.subject_button, doc.get("Тематика"), doc);

        verifyattr("Номер дела", Document.Createform.Incomingdocument.file_register_label);
        fillfield("Номер дела",Document.Createform.Incomingdocument.file_register_button, doc.get("Номер дела"), doc);

        verifyattr("Примечание", Document.Createform.Incomingdocument.note_label);
        fillfield("Примечание",Document.Createform.Incomingdocument.note_field, doc.get("Примечание"), doc);

        verifyattr("Срок исполнения", Document.Createform.Incomingdocument.execution_date_label);
        fillfield("Срок исполнения",Document.Createform.Incomingdocument.execution_date_field, doc.get("Срок исполнения"), doc);

        verifyattr("На контроле", Document.Createform.Incomingdocument.is_on_control_label);
        fillfield("На контроле",Document.Createform.Incomingdocument.is_on_control_checkbox, doc.get("На контроле"), doc);

        verifyattr("Нерегистрируемый", Document.Createform.Incomingdocument.is_not_registered_label);
        fillfield("Нерегистрируемый",Document.Createform.Incomingdocument.is_not_registered_checkbox, doc.get("Нерегистрируемый"), doc);
    }

    @Step("Проверить наличие атрибутов и их значения на форме просмотра")
    static void readincoming(Map<String, String[]> doc) {
        waitForLoad();
        String status = null;
        for (String val:doc.get("Статус"))
            status = val;
        waitelement(Document.Viewform.Incomingdocument.status_field);
        if (!driver.findElement(By.xpath(Document.Viewform.Incomingdocument.status_field)).getText().equals(status)){
            driver.get(driver.getCurrentUrl());
        }
        checkfield("Номер", Document.Viewform.Incomingdocument.regnum_label, Document.Viewform.Incomingdocument.regnum_field, doc);

        checkfield("Дата регистрации", Document.Viewform.Incomingdocument.reg_data_label, Document.Viewform.Incomingdocument.reg_data_field, doc);

        checkfield("Заголовок", Document.Viewform.Incomingdocument.title_label, Document.Viewform.Incomingdocument.title_field, doc);

        checkfield("Вид документа", Document.Viewform.Incomingdocument.type_label, Document.Viewform.Incomingdocument.type_field, doc);

        checkfield("Способ доставки", Document.Viewform.Incomingdocument.delivery_method_label, Document.Viewform.Incomingdocument.delivery_method_field, doc);

        checkfield("Корреспондент", Document.Viewform.Incomingdocument.sender_label, Document.Viewform.Incomingdocument.sender_field, doc);

        checkfield("Представитель корреспондента", Document.Viewform.Incomingdocument.addressee_label, Document.Viewform.Incomingdocument.addressee_field, doc);

        checkfield("Исходящий номер", Document.Viewform.Incomingdocument.outgoing_number_label, Document.Viewform.Incomingdocument.outgoing_number_field, doc);

        checkfield("Исходящий от", Document.Viewform.Incomingdocument.outgoing_date_label, Document.Viewform.Incomingdocument.outgoing_date_field, doc);

        checkfield("Содержание", Document.Viewform.Incomingdocument.summary_label, Document.Viewform.Incomingdocument.summary_field, doc);

        checkfield("Получатель", Document.Viewform.Incomingdocument.recipient_label, Document.Viewform.Incomingdocument.recipient_field, doc);

        checkfield("Срок исполнения", Document.Viewform.Incomingdocument.execution_date_label, Document.Viewform.Incomingdocument.execution_date_field, doc);

        checkfield("На контроле", Document.Viewform.Incomingdocument.is_on_control_label, Document.Viewform.Incomingdocument.is_on_control_field, doc);

        checkfield("Номер дела", Document.Viewform.Incomingdocument.file_register_label, Document.Viewform.Incomingdocument.file_register_field, doc);

        checkfield("Количество листов", Document.Viewform.Incomingdocument.sheets_number_label, Document.Viewform.Incomingdocument.sheets_number_field, doc);

        checkfield("Тематика", Document.Viewform.Incomingdocument.subject_label, Document.Viewform.Incomingdocument.subject_field, doc);

        checkfield("Примечание", Document.Viewform.Incomingdocument.note_label, Document.Viewform.Incomingdocument.note_field, doc);

        checkfield("Нерегистрируемый", Document.Viewform.Incomingdocument.is_not_registered_label, Document.Viewform.Incomingdocument.is_not_registered_field, doc);

        String currenturl = driver.getCurrentUrl();
        if (currenturl.contains("#")){
            currenturl = currenturl.substring(0,currenturl.indexOf('#'));
        }
        if (!removedoc.contains(currenturl))
            removedoc.add(currenturl);
    }

    @Step("Создать внутренний документ")
    static void createinternal(Map<String, String[]> doc) {
        gotoarmsed();
        click("Создать",ARMSED.createButton);
        click("Внутренний документ", ARMSED.Createmenu.internaldocument);
        fillcreateinternal(doc);
        String currenturl = driver.getCurrentUrl();
        click("Создать",Document.Createform.create_button);
        while (currenturl.equals(driver.getCurrentUrl())) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        doc.put("Статус",new String[]{"Черновик"});
        doc.put("Номер",new String[]{"Не присвоено"});
        doc.put("Запись в бж",new String[]{historystandartcreate(doc)});
    }

    @Step("Заполнить атрибуты")
    private static void fillcreateinternal(Map<String, String[]> doc) {
        verifyattr("Вложения", Document.Createform.Internaldocument.attachments_label);

        verifyattr("Заголовок", Document.Createform.Internaldocument.title_label);
        fillfield("Заголовок",Document.Createform.Internaldocument.title_field, doc.get("Заголовок"), doc);

        verifyattr("Вид документа", Document.Createform.Internaldocument.type_label);
        fillfield("Вид документа",Document.Createform.Internaldocument.type_button, doc.get("Вид документа"), doc);

        verifyattr("Срок ответа", Document.Createform.Internaldocument.execution_date_label);
        fillfield("Срок ответа",Document.Createform.Internaldocument.execution_date_field, doc.get("Срок ответа"), doc);

        verifyattr("Получатель", Document.Createform.Internaldocument.recipient_label);
        fillfield("Получатель",Document.Createform.Internaldocument.recipient_button, doc.get("Получатель"), doc);

        verifyattr("Содержание", Document.Createform.Internaldocument.summarycontent_label);
        fillfield("Содержание",Document.Createform.Internaldocument.summarycontent_field, doc.get("Содержание"), doc);

        verifyattr("Подписано на бумажном носителе", Document.Createform.Internaldocument.signedbypaper_label);
        fillfield("Подписано на бумажном носителе",Document.Createform.Internaldocument.signedbypaper_checkbox, doc.get("Подписано на бумажном носителе"), doc);

        boolean t = false;
        for (String val:doc.get("Подписано на бумажном носителе"))
            if (val.equals("Да")) t = true;
        if (t) {
            verifyattr("Подписанты", Document.Createform.Internaldocument.signers_label);
            fillfield("Подписанты", Document.Createform.Internaldocument.signers_button, doc.get("Подписанты"), doc);

            verifyattr("Дата подписания", Document.Createform.Internaldocument.signing_date_label);
            fillfield("Дата подписания", Document.Createform.Internaldocument.signing_date_field, doc.get("Дата подписания"), doc);

            doc.put("Подписан", new String[]{"Да"});
        }

        verifyattr("В ответ на", Document.Createform.Internaldocument.response_to_label);
        fillfield("В ответ на", Document.Createform.Internaldocument.response_to_button, doc.get("В ответ на"), doc);

        verifyattr("Количество листов", Document.Createform.Internaldocument.sheets_number_label);
        fillfield("Количество листов", Document.Createform.Internaldocument.sheets_number_field, doc.get("Количество листов"), doc);

        verifyattr("Тематика", Document.Createform.Internaldocument.subject_label);
        fillfield("Тематика", Document.Createform.Internaldocument.subject_button, doc.get("Тематика"), doc);

        verifyattr("Номер дела", Document.Createform.Internaldocument.file_register_label);
        fillfield("Номер дела", Document.Createform.Internaldocument.file_register_button, doc.get("Номер дела"), doc);

        verifyattr("Примечание", Document.Createform.Internaldocument.note_label);
        fillfield("Примечание", Document.Createform.Internaldocument.note_field, doc.get("Примечание"), doc);

    }

    @Step("Проверить наличие атрибутов и их значения на форме просмотра")
    static void readinternal(Map<String, String[]> doc) {
        waitForLoad();
        String status = null;
        for (String val:doc.get("Статус"))
            status = val;
        waitelement(Document.Viewform.Internaldocument.status_field);
        if (!driver.findElement(By.xpath(Document.Viewform.Internaldocument.status_field)).getText().equals(status)){
            driver.get(driver.getCurrentUrl());
        }
        checkfield("Номер", Document.Viewform.Internaldocument.regnum_label, Document.Viewform.Internaldocument.regnum_field, doc);

        checkfield("Дата регистрации", Document.Viewform.Internaldocument.reg_data_label, Document.Viewform.Internaldocument.reg_data_field, doc);

        checkfield("Составитель", Document.Viewform.Internaldocument.author_label, Document.Viewform.Internaldocument.author_field, doc);

        checkfield("Исполнитель", Document.Viewform.Internaldocument.executor_label, Document.Viewform.Internaldocument.executor_field, doc);

        checkfield("Заголовок", Document.Viewform.Internaldocument.title_label, Document.Viewform.Internaldocument.title_field, doc);

        checkfield("Вид документа", Document.Viewform.Internaldocument.type_label, Document.Viewform.Internaldocument.type_field, doc);

        checkfield("Срок ответа", Document.Viewform.Internaldocument.response_date_label, Document.Viewform.Internaldocument.response_date_field, doc);

        checkfield("Получатель", Document.Viewform.Internaldocument.recipient_label, Document.Viewform.Internaldocument.recipient_field, doc);

        checkfield("Содержание", Document.Viewform.Internaldocument.summary_label, Document.Viewform.Internaldocument.summary_field, doc);

        checkfield("В ответ на", Document.Viewform.Internaldocument.responseto_label, Document.Viewform.Internaldocument.responseto_field, doc);

        checkfield("Количество листов", Document.Viewform.Internaldocument.sheets_number_label, Document.Viewform.Internaldocument.sheets_number_field, doc);

        checkfield("Тематика", Document.Viewform.Internaldocument.subject_label, Document.Viewform.Internaldocument.subject_field, doc);

        checkfield("Номер дела", Document.Viewform.Internaldocument.file_register_label, Document.Viewform.Internaldocument.file_register_field, doc);

        checkfield("Примечание", Document.Viewform.Internaldocument.note_label, Document.Viewform.Internaldocument.note_field, doc);

        checkfield("Номер проекта", Document.Viewform.Internaldocument.regnumproject_label, Document.Viewform.Internaldocument.regnumproject_field, doc);

        checkfield("Дата регистрации проекта", Document.Viewform.Internaldocument.regproject_data_label, Document.Viewform.Internaldocument.regproject_data_field, doc);

        checkfield("Подписано на бумажном носителе", Document.Viewform.Internaldocument.signedonpaper_label, Document.Viewform.Internaldocument.signedonpaper_field, doc);

        checkfield("Подписанты", Document.Viewform.Internaldocument.signers_label, Document.Viewform.Internaldocument.signers_field, doc);

        checkfield("Подписан", Document.Viewform.Internaldocument.signed_label, Document.Viewform.Internaldocument.signed_field, doc);

        checkfield("Дата подписания", Document.Viewform.Internaldocument.signingDate_label, Document.Viewform.Internaldocument.signingDate_field, doc);

        checkfield("Регистратор", Document.Viewform.Internaldocument.registrator_label, Document.Viewform.Internaldocument.registrator_field, doc);

        String currenturl = driver.getCurrentUrl();
        if (currenturl.contains("#")){
            currenturl = currenturl.substring(0,currenturl.indexOf('#'));
        }
        if (!removedoc.contains(currenturl))
            removedoc.add(currenturl);
    }

    @Step("Создать исходящий документ")
    static void createoutgoing(Map<String, String[]> doc) {
        gotoarmsed();
        click("Создать",ARMSED.createButton);
        click("Исходящий документ", ARMSED.Createmenu.outgoingdocument);
        fillcreateoutgoing(doc);
        String currenturl = driver.getCurrentUrl();
        click("Создать",Document.Createform.create_button);
        while (currenturl.equals(driver.getCurrentUrl())) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        doc.put("Статус",new String[]{"Черновик"});
        doc.put("Номер",new String[]{"Не присвоено"});
        doc.put("Запись в бж",new String[]{historystandartcreate(doc)});
    }

    @Step("Заполнить атрибуты")
    private static void fillcreateoutgoing(Map<String, String[]> doc) {
        verifyattr("Вложения", Document.Createform.Outgoingdocument.attachments_label);

        verifyattr("Заголовок", Document.Createform.Outgoingdocument.title_label);
        fillfield("Заголовок",Document.Createform.Outgoingdocument.title_field, doc.get("Заголовок"), doc);

        verifyattr("Вид документа", Document.Createform.Outgoingdocument.type_label);
        fillfield("Вид документа",Document.Createform.Outgoingdocument.type_button, doc.get("Вид документа"), doc);

        verifyattr("Способ доставки", Document.Createform.Outgoingdocument.delivery_method_label);
        fillfield("Способ доставки",Document.Createform.Outgoingdocument.delivery_method_button, doc.get("Способ доставки"), doc);

        verifyattr("Корреспондент", Document.Createform.Outgoingdocument.sender_label);
        fillfield("Корреспондент",Document.Createform.Outgoingdocument.sender_button, doc.get("Корреспондент"), doc);

        verifyattr("Адресат корреспондента", Document.Createform.Outgoingdocument.addressee_label);
        fillfield("Адресат корреспондента",Document.Createform.Outgoingdocument.addressee_button, doc.get("Адресат корреспондента"), doc);

        verifyattr("Содержание", Document.Createform.Outgoingdocument.summarycontent_label);
        fillfield("Содержание",Document.Createform.Outgoingdocument.summarycontent_field, doc.get("Содержание"), doc);

        verifyattr("Подписано на бумажном носителе", Document.Createform.Outgoingdocument.signedbypaper_label);
        fillfield("Подписано на бумажном носителе",Document.Createform.Outgoingdocument.signedbypaper_checkbox, doc.get("Подписано на бумажном носителе"), doc);

        boolean t = false;
        for (String val:doc.get("Подписано на бумажном носителе"))
            if (val.equals("Да")) t = true;
        if (t) {
            verifyattr("Подписанты", Document.Createform.Outgoingdocument.signers_label);
            fillfield("Подписанты", Document.Createform.Outgoingdocument.signers_button, doc.get("Подписанты"), doc);

            verifyattr("Дата подписания", Document.Createform.Outgoingdocument.signing_date_label);
            fillfield("Дата подписания", Document.Createform.Outgoingdocument.signing_date_field, doc.get("Дата подписания"), doc);

            doc.put("Подписан", new String[]{"Да"});
        }

        verifyattr("В ответ на", Document.Createform.Outgoingdocument.response_to_label);
        fillfield("В ответ на", Document.Createform.Outgoingdocument.response_to_button, doc.get("В ответ на"), doc);

        verifyattr("Количество листов", Document.Createform.Outgoingdocument.sheets_number_label);
        fillfield("Количество листов", Document.Createform.Outgoingdocument.sheets_number_field, doc.get("Количество листов"), doc);

        verifyattr("Тематика", Document.Createform.Outgoingdocument.subject_label);
        fillfield("Тематика", Document.Createform.Outgoingdocument.subject_button, doc.get("Тематика"), doc);

        verifyattr("Номер дела", Document.Createform.Outgoingdocument.file_register_label);
        fillfield("Номер дела", Document.Createform.Outgoingdocument.file_register_button, doc.get("Номер дела"), doc);

        verifyattr("Примечание", Document.Createform.Outgoingdocument.note_label);
        fillfield("Примечание", Document.Createform.Outgoingdocument.note_field, doc.get("Примечание"), doc);

        verifyattr("Завершающий", Document.Createform.Outgoingdocument.finishing_label);
        //fillfield("Завершающий", Document.Createform.Outgoingdocument.finishing_field, doc.get("Завершающий"), doc);
        //атрибут временно залочен, хз насколько

    }

    @Step("Проверить наличие атрибутов и их значения на форме просмотра")
    static void readoutgoing(Map<String, String[]> doc) {
        waitForLoad();
        String status = null;
        for (String val:doc.get("Статус"))
            status = val;
        waitelement(Document.Viewform.Outgoingdocument.status_field);
        if (!driver.findElement(By.xpath(Document.Viewform.Outgoingdocument.status_field)).getText().equals(status)){
            driver.get(driver.getCurrentUrl());
        }
        checkfield("Номер", Document.Viewform.Outgoingdocument.regnum_label, Document.Viewform.Outgoingdocument.regnum_field, doc);

        checkfield("Дата регистрации", Document.Viewform.Outgoingdocument.reg_data_label, Document.Viewform.Outgoingdocument.reg_data_field, doc);

        checkfield("Заголовок", Document.Viewform.Outgoingdocument.title_label, Document.Viewform.Outgoingdocument.title_field, doc);

        checkfield("Вид документа", Document.Viewform.Outgoingdocument.type_label, Document.Viewform.Outgoingdocument.type_field, doc);

        checkfield("Способ доставки", Document.Viewform.Outgoingdocument.delivery_method_label, Document.Viewform.Outgoingdocument.delivery_method_field, doc);

        checkfield("Корреспондент", Document.Viewform.Outgoingdocument.sender_label, Document.Viewform.Outgoingdocument.sender_field, doc);

        checkfield("Адресат корреспондента", Document.Viewform.Outgoingdocument.addressee_label, Document.Viewform.Outgoingdocument.addressee_field, doc);

        checkfield("Содержание", Document.Viewform.Outgoingdocument.summary_label, Document.Viewform.Outgoingdocument.summary_field, doc);

        checkfield("Количество листов", Document.Viewform.Outgoingdocument.sheets_number_label, Document.Viewform.Outgoingdocument.sheets_number_field, doc);

        checkfield("Тематика", Document.Viewform.Outgoingdocument.subject_label, Document.Viewform.Outgoingdocument.subject_field, doc);

        checkfield("Примечание", Document.Viewform.Outgoingdocument.note_label, Document.Viewform.Outgoingdocument.note_field, doc);

        checkfield("Номер дела", Document.Viewform.Outgoingdocument.file_register_label, Document.Viewform.Outgoingdocument.file_register_field, doc);

        checkfield("Составитель", Document.Viewform.Outgoingdocument.author_label, Document.Viewform.Outgoingdocument.author_field, doc);

        checkfield("Исполнитель", Document.Viewform.Outgoingdocument.executor_label, Document.Viewform.Outgoingdocument.executor_field, doc);

        checkfield("Номер проекта", Document.Viewform.Outgoingdocument.regnumproject_label, Document.Viewform.Outgoingdocument.regnumproject_field, doc);

        checkfield("Дата регистрации проекта", Document.Viewform.Outgoingdocument.regproject_data_label, Document.Viewform.Outgoingdocument.regproject_data_field, doc);

        checkfield("Подписано на бумажном носителе", Document.Viewform.Outgoingdocument.signedonpaper_label, Document.Viewform.Outgoingdocument.signedonpaper_field, doc);

        checkfield("Подписанты", Document.Viewform.Outgoingdocument.signers_label, Document.Viewform.Outgoingdocument.signers_field, doc);

        checkfield("Подписан", Document.Viewform.Outgoingdocument.signed_label, Document.Viewform.Outgoingdocument.signed_field, doc);

        checkfield("Дата подписания", Document.Viewform.Outgoingdocument.signingDate_label, Document.Viewform.Outgoingdocument.signingDate_field, doc);

        checkfield("Регистратор", Document.Viewform.Outgoingdocument.registrator_label, Document.Viewform.Outgoingdocument.registrator_field, doc);

        checkfield("Завершающий", Document.Viewform.Outgoingdocument.finishing_label, Document.Viewform.Outgoingdocument.finishing_field, doc);

        String currenturl = driver.getCurrentUrl();
        if (currenturl.contains("#")){
            currenturl = currenturl.substring(0,currenturl.indexOf('#'));
        }
        if (!removedoc.contains(currenturl))
            removedoc.add(currenturl);
    }

    @Step("Создать нормативный документ")
    static void creatend(Map<String, String[]> doc) {
        gotoarmsed();
        click("Создать",ARMSED.createButton);
        click("НД", ARMSED.Createmenu.nddocument);
        fillcreatend(doc);
        String currenturl = driver.getCurrentUrl();
        click("Создать",Document.Createform.create_button);
        while (currenturl.equals(driver.getCurrentUrl())) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        doc.put("Статус",new String[]{"Черновик"});
        doc.put("Номер",new String[]{"Не присвоено"});
        doc.put("Запись в бж",new String[]{historystandartcreate(doc)});
    }

    @Step("Заполнить атрибуты")
    private static void fillcreatend(Map<String, String[]> doc) {
        verifyattr("Вложения", Document.Createform.Nddocument.attachments_label);

        verifyattr("Заголовок", Document.Createform.Nddocument.title_label);
        fillfield("Заголовок",Document.Createform.Nddocument.title_field, doc.get("Заголовок"), doc);

        verifyattr("Вид документа", Document.Createform.Nddocument.type_label);
        fillfield("Вид документа",Document.Createform.Nddocument.type_button, doc.get("Вид документа"), doc);

        verifyattr("Содержание", Document.Createform.Nddocument.summarycontent_label);
        fillfield("Содержание",Document.Createform.Nddocument.summarycontent_field, doc.get("Содержание"), doc);

        verifyattr("Количество листов", Document.Createform.Nddocument.sheets_number_label);
        fillfield("Количество листов", Document.Createform.Nddocument.sheets_number_field, doc.get("Количество листов"), doc);

        verifyattr("Подписано на бумажном носителе", Document.Createform.Nddocument.signedbypaper_label);
        fillfield("Подписано на бумажном носителе",Document.Createform.Nddocument.signedbypaper_checkbox, doc.get("Подписано на бумажном носителе"), doc);

        boolean t = false;
        for (String val:doc.get("Подписано на бумажном носителе"))
            if (val.equals("Да")) t = true;
        if (t) {
            verifyattr("Подписанты", Document.Createform.Nddocument.signers_label);
            fillfield("Подписанты", Document.Createform.Nddocument.signers_button, doc.get("Подписанты"), doc);

            verifyattr("Дата подписания", Document.Createform.Nddocument.signing_date_label);
            fillfield("Дата подписания", Document.Createform.Nddocument.signing_date_field, doc.get("Дата подписания"), doc);

            doc.put("Подписан", new String[]{"Да"});
        }

        verifyattr("Номер дела", Document.Createform.Nddocument.file_register_label);
        fillfield("Номер дела", Document.Createform.Nddocument.file_register_button, doc.get("Номер дела"), doc);

        verifyattr("Примечание", Document.Createform.Nddocument.note_label);
        fillfield("Примечание", Document.Createform.Nddocument.note_field, doc.get("Примечание"), doc);

        verifyattr("Тематика", Document.Createform.Nddocument.subject_label);
        fillfield("Тематика", Document.Createform.Nddocument.subject_button, doc.get("Тематика"), doc);

        verifyattr("Подразделения", Document.Createform.Nddocument.organizationunit_label);
        fillfield("Подразделения", Document.Createform.Nddocument.organizationunit_button, doc.get("Подразделения"), doc);

        verifyattr("Бессрочный", Document.Createform.Nddocument.unlimited_label);
        fillfield("Бессрочный", Document.Createform.Nddocument.unlimited_checkbox, doc.get("Бессрочный"), doc);

        verifyattr("Период действия", Document.Createform.Nddocument.daterange_label);
        fillfield("Период действия С", Document.Createform.Nddocument.daterangestart_field, doc.get("Период действия С"), doc);
        fillfield("Период действия По", Document.Createform.Nddocument.daterangeend_field, doc.get("Период действия По"), doc);

    }

    @Step("Проверить наличие атрибутов и их значения на форме просмотра")
    static void readnd(Map<String, String[]> doc) {
        waitForLoad();
        String status = null;
        for (String val:doc.get("Статус"))
            status = val;
        waitelement(Document.Viewform.Nddocument.status_field);
        if (!driver.findElement(By.xpath(Document.Viewform.Nddocument.status_field)).getText().equals(status)){
            driver.get(driver.getCurrentUrl());
        }
        checkfield("Номер", Document.Viewform.Nddocument.regnum_label, Document.Viewform.Nddocument.regnum_field, doc);

        checkfield("Дата регистрации", Document.Viewform.Nddocument.reg_data_label, Document.Viewform.Nddocument.reg_data_field, doc);

        checkfield("Заголовок", Document.Viewform.Nddocument.title_label, Document.Viewform.Nddocument.title_field, doc);

        checkfield("Вид документа", Document.Viewform.Nddocument.type_label, Document.Viewform.Nddocument.type_field, doc);

        checkfield("Содержание", Document.Viewform.Nddocument.summary_label, Document.Viewform.Nddocument.summary_field, doc);

        checkfield("Количество листов", Document.Viewform.Nddocument.sheets_number_label, Document.Viewform.Nddocument.sheets_number_field, doc);

        checkfield("Подписано на бумажном носителе", Document.Viewform.Nddocument.signedonpaper_label, Document.Viewform.Nddocument.signedonpaper_field, doc);

        checkfield("Подписанты", Document.Viewform.Nddocument.signers_label, Document.Viewform.Nddocument.signers_field, doc);

        checkfield("Дата подписания", Document.Viewform.Nddocument.signingDate_label, Document.Viewform.Nddocument.signingDate_field, doc);

        checkfield("Подписан", Document.Viewform.Nddocument.signed_label, Document.Viewform.Nddocument.signed_field, doc);

        checkfield("Номер дела", Document.Viewform.Nddocument.file_register_label, Document.Viewform.Nddocument.file_register_field, doc);

        checkfield("Примечание", Document.Viewform.Nddocument.note_label, Document.Viewform.Nddocument.note_field, doc);

        checkfield("Тематика", Document.Viewform.Nddocument.subject_label, Document.Viewform.Nddocument.subject_field, doc);

        checkfield("Подразделения", Document.Viewform.Nddocument.organizationunit_label, Document.Viewform.Nddocument.organizationunit_field, doc);

        checkfield("Бессрочный", Document.Viewform.Nddocument.unlimited_label, Document.Viewform.Nddocument.unlimited_field, doc);

        checkfield("Период действия С", Document.Viewform.Nddocument.daterange_label, Document.Viewform.Nddocument.daterangestart_field, doc);

        checkfield("Период действия По", Document.Viewform.Nddocument.daterange_label, Document.Viewform.Nddocument.daterangeend_field, doc);

        checkfield("Составитель", Document.Viewform.Nddocument.author_label, Document.Viewform.Nddocument.author_field, doc);

        checkfield("Исполнитель", Document.Viewform.Nddocument.executor_label, Document.Viewform.Nddocument.executor_field, doc);

        checkfield("Номер проекта", Document.Viewform.Nddocument.regnumproject_label, Document.Viewform.Nddocument.regnumproject_field, doc);

        checkfield("Дата регистрации проекта", Document.Viewform.Nddocument.regproject_data_label, Document.Viewform.Nddocument.regproject_data_field, doc);

        checkfield("Регистратор", Document.Viewform.Nddocument.registrator_label, Document.Viewform.Nddocument.registrator_field, doc);


        String currenturl = driver.getCurrentUrl();
        if (currenturl.contains("#")){
            currenturl = currenturl.substring(0,currenturl.indexOf('#'));
        }
        if (!removedoc.contains(currenturl))
            removedoc.add(currenturl);
    }

    @Step("Создать организационно-распорядительный документ")
    static void createord(Map<String, String[]> doc, HashMap<String, HashMap<String, String[]>> items) {
        gotoarmsed();
        click("Создать",ARMSED.createButton);
        click("ОРД", ARMSED.Createmenu.orddocument);
        fillcreateord(doc, items);
        String currenturl = driver.getCurrentUrl();
        click("Создать",Document.Createform.create_button);
        while (currenturl.equals(driver.getCurrentUrl())) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        doc.put("Статус",new String[]{"Черновик"});
        doc.put("Номер",new String[]{"Не присвоено"});
        doc.put("Запись в бж",new String[]{historystandartcreate(doc)});
    }

    @Step("Заполнить атрибуты")
    private static void fillcreateord(Map<String, String[]> doc, HashMap<String, HashMap<String, String[]>> items) {
        verifyattr("Вложения", Document.Createform.Orddocument.attachments_label);

        verifyattr("Вид документа", Document.Createform.Orddocument.type_label);
        fillfield("Вид документа",Document.Createform.Orddocument.type_button, doc.get("Вид документа"), doc);

        verifyattr("Заголовок", Document.Createform.Orddocument.title_label);
        fillfield("Заголовок",Document.Createform.Orddocument.title_field, doc.get("Заголовок"), doc);

        verifyattr("Срок исполнения", Document.Createform.Orddocument.executiondate_label);
        fillfield("Срок исполнения",Document.Createform.Orddocument.executiondate_field, doc.get("Срок исполнения"), doc);

        verifyattr("Содержание", Document.Createform.Orddocument.summarycontent_label);
        fillfield("Содержание",Document.Createform.Orddocument.summarycontent_field, doc.get("Содержание"), doc);

        verifyattr("Подписано на бумажном носителе", Document.Createform.Orddocument.signedbypaper_label);
        fillfield("Подписано на бумажном носителе",Document.Createform.Orddocument.signedbypaper_checkbox, doc.get("Подписано на бумажном носителе"), doc);

        boolean t = false;
        for (String val:doc.get("Подписано на бумажном носителе"))
            if (val.equals("Да")) t = true;
        if (t) {
            verifyattr("Подписанты", Document.Createform.Orddocument.signers_label);
            fillfield("Подписанты", Document.Createform.Orddocument.signers_button, doc.get("Подписанты"), doc);

            verifyattr("Дата подписания", Document.Createform.Orddocument.signing_date_label);
            fillfield("Дата подписания", Document.Createform.Orddocument.signing_date_field, doc.get("Дата подписания"), doc);

            doc.put("Подписан", new String[]{"Да"});
        }

        verifyattr("Контролёр", Document.Createform.Orddocument.controller_label);
        fillfield("Контролёр", Document.Createform.Orddocument.controller_button, doc.get("Контролёр"), doc);

        if (doc.get("Контролёр") != null) {
            verifyattr("Подтверждать завершение работы по документу", Document.Createform.Orddocument.confirmcompletion_label);
            fillfield("Подтверждать завершение работы по документу", Document.Createform.Orddocument.confirmcompletion_checkbox, doc.get("Подтверждать завершение работы по документу"), doc);
        }

        verifyattr("Примечание", Document.Createform.Orddocument.note_label);
        fillfield("Примечание", Document.Createform.Orddocument.note_field, doc.get("Примечание"), doc);

        verifyattr("Количество листов", Document.Createform.Orddocument.sheets_number_label);
        fillfield("Количество листов", Document.Createform.Orddocument.sheets_number_field, doc.get("Количество листов"), doc);

        verifyattr("Тематика", Document.Createform.Orddocument.subject_label);
        fillfield("Тематика", Document.Createform.Orddocument.subject_button, doc.get("Тематика"), doc);

        verifyattr("Номер дела", Document.Createform.Orddocument.file_register_label);
        fillfield("Номер дела", Document.Createform.Orddocument.file_register_button, doc.get("Номер дела"), doc);

        verifyattr("Отменяемые документы", Document.Createform.Orddocument.canceled_label);
        fillfield("Отменяемые документы", Document.Createform.Orddocument.canceled_button, doc.get("Отменяемые документы"), doc);

        verifyattr("Принимаемые документы", Document.Createform.Orddocument.accepted_label);
        fillfield("Принимаемые документы", Document.Createform.Orddocument.accepted_button, doc.get("Принимаемые документы"), doc);


        if (!items.isEmpty()){
            //метод для заполнения формы создания пунктов (которая еще не описана)
            int k = items.size();

            click("Создание", Document.Createform.Orddocument.items_button);

            for (int i=0; i<k; i++) {
                item = items.get(Integer.toString(i+1));
                verifyattr("Пункты Заголовок", Document.Createform.Orddocument.Items.title_label);
                fillfield("Пункты Заголовок", Document.Createform.Orddocument.Items.title_field, item.get("Пункты Заголовок"), item);

                verifyattr("Пункты Автор", Document.Createform.Orddocument.Items.author_label);
                //fillfield("Пункты Автор", Document.Createform.Orddocument.Items.author_button, items.get("Пункты Автор"), items);

                verifyattr("Пункты Содержание", Document.Createform.Orddocument.Items.summary_label);
                fillfield("Пункты Содержание", Document.Createform.Orddocument.Items.summary_field, item.get("Пункты Содержание"), item);

                verifyattr("Пункты Исполнитель", Document.Createform.Orddocument.Items.executor_label);
                fillfield("Пункты Исполнитель", Document.Createform.Orddocument.Items.executor_button, item.get("Пункты Исполнитель"), item);

                verifyattr("Пункты Соисполнители", Document.Createform.Orddocument.Items.coexecutors_label);
                fillfield("Пункты Соисполнители", Document.Createform.Orddocument.Items.coexecutors_button, item.get("Пункты Соисполнители"), item);

                verifyattr("Пункты Срок исполнения", Document.Createform.Orddocument.Items.limitationdate_label);
                fillfield("Пункты Срок исполнения", Document.Createform.Orddocument.Items.limitationdate_radiodate, item.get("Пункты Срок исполнения"), item);

                verifyattr("Пункты Требуется отчет", Document.Createform.Orddocument.Items.needreport_label);
                fillfield("Пункты Требуется отчет", Document.Createform.Orddocument.Items.needreport_checkbox, item.get("Пункты Требуется отчет"), item);

                verifyattr("Пункты Контролер", Document.Createform.Orddocument.Items.controller_label);
                fillfield("Пункты Контролер", Document.Createform.Orddocument.Items.controller_button, item.get("Пункты Контролер"), item);

                verifyattr("Пункты Тематика", Document.Createform.Orddocument.Items.subject_label);
                fillfield("Пункты Тематика", Document.Createform.Orddocument.Items.subject_button, item.get("Пункты Тематика"), item);
                if (i > 0)
                    click("Сохранить и создать новый", Document.Createform.Orddocument.Items.saveandcreate_button);
                else
                    click("Закрыть", Document.Createform.Orddocument.Items.close_button);
            }
        }

    }

    @Step("Проверить наличие атрибутов и их значения на форме просмотра")
    static void readord(Map<String, String[]> doc) {
        waitForLoad();
        String status = null;
        for (String val:doc.get("Статус"))
            status = val;
        waitelement(Document.Viewform.Orddocument.status_field);
        if (!driver.findElement(By.xpath(Document.Viewform.Orddocument.status_field)).getText().equals(status)){
            driver.get(driver.getCurrentUrl());
        }
        checkfield("Номер", Document.Viewform.Orddocument.regnum_label, Document.Viewform.Orddocument.regnum_field, doc);

        checkfield("Дата регистрации", Document.Viewform.Orddocument.reg_data_label, Document.Viewform.Orddocument.reg_data_field, doc);

        checkfield("Вид документа", Document.Viewform.Orddocument.type_label, Document.Viewform.Orddocument.type_field, doc);

        checkfield("Заголовок", Document.Viewform.Orddocument.title_label, Document.Viewform.Orddocument.title_field, doc);

        checkfield("Срок исполнения", Document.Viewform.Orddocument.executiondate_field, Document.Viewform.Orddocument.executiondate_field, doc);

        checkfield("Содержание", Document.Viewform.Orddocument.summary_label, Document.Viewform.Orddocument.summary_field, doc);

        checkfield("Контролёр", Document.Viewform.Orddocument.controller_label, Document.Viewform.Orddocument.controller_field, doc);

        checkfield("Примечание", Document.Viewform.Orddocument.note_label, Document.Viewform.Orddocument.note_field, doc);

        checkfield("Количество листов", Document.Viewform.Orddocument.sheets_number_label, Document.Viewform.Orddocument.sheets_number_field, doc);

        checkfield("Тематика", Document.Viewform.Orddocument.subject_label, Document.Viewform.Orddocument.subject_field, doc);

        checkfield("Номер дела", Document.Viewform.Orddocument.file_register_label, Document.Viewform.Orddocument.file_register_field, doc);

        checkfield("Отменяемые документы", Document.Viewform.Orddocument.canceled_label, Document.Viewform.Orddocument.canceled_field, doc);

        checkfield("Принимаемые документы", Document.Viewform.Orddocument.accepted_label, Document.Viewform.Orddocument.accepted_field, doc);

        checkfield("Составитель", Document.Viewform.Orddocument.author_label, Document.Viewform.Orddocument.author_field, doc);

        checkfield("Исполнитель", Document.Viewform.Orddocument.executor_label, Document.Viewform.Orddocument.executor_field, doc);

        checkfield("Номер проекта", Document.Viewform.Orddocument.regnumproject_label, Document.Viewform.Orddocument.regnumproject_field, doc);

        checkfield("Дата регистрации проекта", Document.Viewform.Orddocument.regproject_data_label, Document.Viewform.Orddocument.regproject_data_field, doc);

        checkfield("Подписано на бумажном носителе", Document.Viewform.Orddocument.signedonpaper_label, Document.Viewform.Orddocument.signedonpaper_field, doc);

        checkfield("Подписанты", Document.Viewform.Orddocument.signers_label, Document.Viewform.Orddocument.signers_field, doc);

        checkfield("Подписан", Document.Viewform.Orddocument.signed_label, Document.Viewform.Orddocument.signed_field, doc);

        checkfield("Дата подписания", Document.Viewform.Orddocument.signingDate_label, Document.Viewform.Orddocument.signingDate_field, doc);

        checkfield("Регистратор", Document.Viewform.Orddocument.registrator_label, Document.Viewform.Orddocument.registrator_field, doc);


        String currenturl = driver.getCurrentUrl();
        if (currenturl.contains("#")){
            currenturl = currenturl.substring(0,currenturl.indexOf('#'));
        }
        if (!removedoc.contains(currenturl))
            removedoc.add(currenturl);
    }

    @Step("Создать поручение")
    static void createerrand(Map<String, String[]> doc, String button) {
        gotoarmsed();
        click("Создать",ARMSED.createButton);
        click("Поручение", ARMSED.Createmenu.erranddocument);
        fillcreateerrand(doc);
        String currenturl = driver.getCurrentUrl();
        if (button.equals("Сохранить черновик")) {
            click("Сохранить черновик", Document.Createform.Erranddocument.save_button);
            doc.put("Статус", new String[]{"Черновик"});
        }
        else
        if (button.equals("Направить")) {
            click("Направить", Document.Createform.Erranddocument.default_button);
            doc.put("Статус",new String[]{"Ожидает исполнения"});
        }
        else hardassertfail(button + " - Нет такой кнопки на форме создания поручения");
        while (currenturl.equals(driver.getCurrentUrl())) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (doc.get("Автор") == null)
            doc.put("Автор", new String[]{getuserbylogin(current_login).full});
        if (doc.get("Создатель") == null)
            doc.put("Создатель", new String[]{getuserbylogin(current_login).full});
    }

    @Step("Заполнить атрибуты")
    private static void fillcreateerrand(Map<String, String[]> doc) {
        verifyattr("Тип поручения", Document.Createform.Erranddocument.type_label);
        fillfield("Тип поручения",Document.Createform.Erranddocument.type_button, doc.get("Тип поручения"), doc);

        verifyattr("Заголовок", Document.Createform.Erranddocument.title_label);
        fillfield("Заголовок",Document.Createform.Erranddocument.title_button, doc.get("Заголовок"), doc);

        verifyattr("Текст поручения", Document.Createform.Erranddocument.content_label);
        fillfield("Текст поручения",Document.Createform.Erranddocument.content_field, doc.get("Текст поручения"), doc);

        verifyattr("Исполнитель", Document.Createform.Erranddocument.executor_label);
        fillfield("Исполнитель",Document.Createform.Erranddocument.executor_button, doc.get("Исполнитель"), doc);

        verifyattr("Соисполнители", Document.Createform.Erranddocument.coexecutor_label);
        fillfield("Соисполнители",Document.Createform.Erranddocument.coexecutor_button, doc.get("Соисполнители"), doc);

        verifyattr("Срок исполнения", Document.Createform.Erranddocument.limitationdate_label);
        fillfield("Срок исполнения",Document.Createform.Erranddocument.limitationdate_radiodate, doc.get("Срок исполнения"), doc);

        verifyattr("Вложения", Document.Createform.Erranddocument.attachments_label);

        if (doc.get("Направлять периодически") != null || doc.get("Контролер") != null || doc.get("Требуется отчет") != null ||
                doc.get("Важное") != null || doc.get("Тематика") != null){
            click("Расширенное поручение",Document.Createform.Erranddocument.expand_button);

            verifyattr("Направлять периодически", Document.Createform.Erranddocument.periodically_label);
            fillfield("Направлять периодически",Document.Createform.Erranddocument.periodically_checkbox, doc.get("Направлять периодически"), doc);

            boolean t = false;
            for (String val:doc.get("Направлять периодически"))
                if (val.equals("Да")) t = true;
            if (t) {
                verifyattr("Повторять", Document.Createform.Erranddocument.reiterationrule_label);
                fillfield("Повторять",Document.Createform.Erranddocument.reiterationrule_button, doc.get("Повторять"), doc);

                verifyattr("Начало повторений", Document.Createform.Erranddocument.periodstart_label);
                fillfield("Начало повторений",Document.Createform.Erranddocument.periodstart_field, doc.get("Начало повторений"), doc);

                verifyattr("Окончание повторений", Document.Createform.Erranddocument.periodend_label);
                fillfield("Окончание повторений",Document.Createform.Erranddocument.periodend_radiodate, doc.get("Окончание повторений"), doc);
            }

            verifyattr("Контролер", Document.Createform.Erranddocument.controller_label);
            fillfield("Контролер",Document.Createform.Erranddocument.controller_button, doc.get("Контролер"), doc);

            verifyattr("Требуется отчет", Document.Createform.Erranddocument.needreport_label);
            fillfield("Требуется отчет",Document.Createform.Erranddocument.needreport_checkbox, doc.get("Требуется отчет"), doc);

            t = false;
            for (String val:doc.get("Требуется отчет"))
                if (val.equals("Да")) t = true;
            if (t) {
                verifyattr("Получатель отчета", Document.Createform.Erranddocument.reportrecipient_label);
                fillfield("Получатель отчета",Document.Createform.Erranddocument.reportrecipient_select, doc.get("Получатель отчета"), doc);
            }

            verifyattr("Важное", Document.Createform.Erranddocument.important_label);
            fillfield("Важное",Document.Createform.Erranddocument.important_checkbox, doc.get("Важное"), doc);

            verifyattr("Тематика", Document.Createform.Erranddocument.subject_label);
            fillfield("Тематика",Document.Createform.Erranddocument.subject_button, doc.get("Тематика"), doc);

        }
    }

    @Step("Проверить наличие атрибутов и их значения на форме просмотра")
    static void readerrand(Map<String, String[]> doc) {
        waitForLoad();
        String status = null;
        for (String val:doc.get("Статус"))
            status = val;
        waitelement(Document.Viewform.Erranddocument.status_field);
        if (!driver.findElement(By.xpath(Document.Viewform.Erranddocument.status_field)).getText().equals(status)){
            driver.get(driver.getCurrentUrl());
        }


        String title = driver.findElement(By.xpath(Objects.Document.documenttitle)).getText();
        doc.put("Номер",new String[]{title.substring(2,title.indexOf(","))});
        doc.put("Срок исполнения", new String[]{title.substring(title.indexOf("срок:")+6,title.length())});

        checkfield("Документ-основание", Document.Viewform.Erranddocument.base_label, Document.Viewform.Erranddocument.base_field, doc);

        checkfield("Основание", Document.Viewform.Erranddocument.additional_label, Document.Viewform.Erranddocument.additional_field, doc);

        checkfield("Автор", Document.Viewform.Erranddocument.initiator_label, Document.Viewform.Erranddocument.initiator_field, doc);

        checkfield("Создатель", Document.Viewform.Erranddocument.author_label, Document.Viewform.Erranddocument.author_field, doc);

        checkfield("Тип поручения", Document.Viewform.Erranddocument.type_label, Document.Viewform.Erranddocument.type_field, doc);

        checkfield("Заголовок", Document.Viewform.Erranddocument.title_label, Document.Viewform.Erranddocument.title_field, doc);

        checkfield("Текст поручения", Document.Viewform.Erranddocument.content_label, Document.Viewform.Erranddocument.content_field, doc);

        checkfield("Исполнитель", Document.Viewform.Erranddocument.executor_label, Document.Viewform.Erranddocument.executor_field, doc);

        checkfield("Соисполнители", Document.Viewform.Erranddocument.coexecutors_label, Document.Viewform.Erranddocument.coexecutors_field, doc);

        checkfield("Срок исполнения", Document.Viewform.Erranddocument.limitationdate_label, Document.Viewform.Erranddocument.limitationdate_field, doc);

        checkfield("Закрывает вышестоящее поручение", Document.Viewform.Erranddocument.autoclose_label, Document.Viewform.Erranddocument.autoclose_field, doc);

        checkfield("Направлять периодически", Document.Viewform.Erranddocument.periodically_label, Document.Viewform.Erranddocument.periodically_field, doc);

        checkfield("Контролер", Document.Viewform.Erranddocument.controller_label, Document.Viewform.Erranddocument.controller_field, doc);

        checkfield("Требуется отчет", Document.Viewform.Erranddocument.needreport_label, Document.Viewform.Erranddocument.needreport_field, doc);

        checkfield("Получатель отчета", Document.Viewform.Erranddocument.reportrecipient_label, Document.Viewform.Erranddocument.reportrecipient_field, doc);

        checkfield("Тематика", Document.Viewform.Erranddocument.subject_label, Document.Viewform.Erranddocument.subject_field, doc);


        String currenturl = driver.getCurrentUrl();
        if (currenturl.contains("#")){
            currenturl = currenturl.substring(0,currenturl.indexOf('#'));
        }
        if (!removedoc.contains(currenturl))
            removedoc.add(currenturl);
    }

    @Step("Создать резолюцию")
    static void createresolutions(Map<String, String[]> doc, String button) {
        gotoarmsed();
        click("Создать",ARMSED.createButton);
        click("Рехолюция", ARMSED.Createmenu.resolutionsdocument);
        fillcreateresolutions(doc);
        String currenturl = driver.getCurrentUrl();
        if (button.equals("Сохранить черновик")) {
            click("Сохранить черновик", Document.Createform.Resolutionsdocument.save_button);
            doc.put("Статус", new String[]{"Черновик"});
        }
        else
        if (button.equals("Направить")) {
            click("Направить", Document.Createform.Resolutionsdocument.default_button);
            doc.put("Статус",new String[]{"На исполнении"});
        }
        else hardassertfail(button + " - Нет такой кнопки на форме создания поручения");
        while (currenturl.equals(driver.getCurrentUrl())) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (doc.get("Создатель") == null)
            doc.put("Создатель", new String[]{getuserbylogin(current_login).full});
    }

    @Step("Заполнить атрибуты")
    private static void fillcreateresolutions(Map<String, String[]> doc) {
        verifyattr("Утверждено вне системы", Document.Createform.Resolutionsdocument.approvedoutsidesystem_label);
        fillfield("Утверждено вне системы",Document.Createform.Resolutionsdocument.approvedoutsidesystem_checkbox, doc.get("Утверждено вне системы"), doc);

        verifyattr("Тематика", Document.Createform.Resolutionsdocument.subject_label);
        fillfield("Тематика",Document.Createform.Resolutionsdocument.subject_button, doc.get("Тематика"), doc);

        verifyattr("Автор", Document.Createform.Resolutionsdocument.author_label);
        fillfield("Автор",Document.Createform.Resolutionsdocument.author_button, doc.get("Автор"), doc);

        verifyattr("Контролер", Document.Createform.Resolutionsdocument.controller_label);
        fillfield("Контролер",Document.Createform.Resolutionsdocument.controller_button, doc.get("Контролер"), doc);

        verifyattr("Завершающий", Document.Createform.Resolutionsdocument.closers_label);
        fillfield("Завершающий",Document.Createform.Resolutionsdocument.closers_select, doc.get("Завершающий"), doc);

        verifyattr("Контроль", Document.Createform.Resolutionsdocument.isoncontrol_label);
        fillfield("Контроль",Document.Createform.Resolutionsdocument.isoncontrol_checkbox, doc.get("Контроль"), doc);

        verifyattr("Срок исполнения", Document.Createform.Resolutionsdocument.limitationdate_label);
        fillfield("Срок исполнения",Document.Createform.Resolutionsdocument.limitationdate_radiodate, doc.get("Срок исполнения"), doc);

        if (!errands.isEmpty()){
            //метод для заполнения формы создания пунктов (которая еще не описана)
            int k = errands.size();

            click("Добавить", Document.Createform.Resolutionsdocument.adderrand_button);

            for (int i=0; i<k; i++) {
                errand = errands.get(Integer.toString(i+1));
                verifyattr("Поручения Тип поручения", "(" + Document.Createform.Resolutionsdocument.Errands.type_label + ")[" + Integer.toString(i+1) + "]");
                fillfield("Поручения Тип поручения", "(" + Document.Createform.Resolutionsdocument.Errands.type_button + ")[" + Integer.toString(i+1) + "]", errand.get("Поручения Тип поручения"), errand);

                verifyattr("Поручения Заголовок", "(" + Document.Createform.Resolutionsdocument.Errands.title_label + ")[" + Integer.toString(i+1) + "]");
                fillfield("Поручения Заголовок", "(" + Document.Createform.Resolutionsdocument.Errands.title_button + ")[" + Integer.toString(i+1) + "]", errand.get("Поручения Заголовок"), errand);

                verifyattr("Поручения Исполнитель", "(" + Document.Createform.Resolutionsdocument.Errands.executor_label + ")[" + Integer.toString(i+1) + "]");
                fillfield("Поручения Исполнитель", "(" + Document.Createform.Resolutionsdocument.Errands.executor_button + ")[" + Integer.toString(i+1) + "]", errand.get("Поручения Исполнитель"), errand);

                verifyattr("Поручения Соисполнители", "(" + Document.Createform.Resolutionsdocument.Errands.coexecutor_label + ")[" + Integer.toString(i+1) + "]");
                fillfield("Поручения Соисполнители", "(" + Document.Createform.Resolutionsdocument.Errands.coexecutor_button + ")[" + Integer.toString(i+1) + "]", errand.get("Поручения Соисполнители"), errand);

                verifyattr("Поручения Контролер", "(" + Document.Createform.Resolutionsdocument.Errands.controller_label + ")[" + Integer.toString(i+1) + "]");
                fillfield("Поручения Контролер", "(" + Document.Createform.Resolutionsdocument.Errands.controller_button + ")[" + Integer.toString(i+1) + "]", errand.get("Поручения Контролер"), errand);

                verifyattr("Поручения Срок исполнения", "(" + Document.Createform.Resolutionsdocument.Errands.limitationdate_label + ")[" + Integer.toString(i+1) + "]");
                fillfield("Поручения Срок исполнения", "(" + Document.Createform.Resolutionsdocument.Errands.limitationdate_radiodate + ")[" + Integer.toString(i+1) + "]", errand.get("Поручения Срок исполнения"), errand);

                verifyattr("Поручения Требуется отчет", "(" + Document.Createform.Resolutionsdocument.Errands.needreport_label + ")[" + Integer.toString(i+1) + "]");
                fillfield("Поручения Требуется отчет", "(" + Document.Createform.Resolutionsdocument.Errands.needreport_checkbox + ")[" + Integer.toString(i+1) + "]", errand.get("Поручения Требуется отчет"), errand);

                boolean t = false;
                for (String val:errand.get("Поручения Требуется отчет"))
                    if (val.equals("Да")) t = true;
                if (t) {
                    verifyattr("Поручения Получатель отчета", "(" + Document.Createform.Resolutionsdocument.Errands.reportrecipient_label + ")[" + Integer.toString(i + 1) + "]");
                    fillfield("Поручения Получатель отчета", "(" + Document.Createform.Resolutionsdocument.Errands.reportrecipient_select+ ")[" + Integer.toString(i + 1) + "]", errand.get("Поручения Получатель отчета"), errand);
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
        waitelement(Document.Viewform.Resolutionsdocument.status_field);
        if (!driver.findElement(By.xpath(Document.Viewform.Incomingdocument.status_field)).getText().equals(status)){
            driver.get(driver.getCurrentUrl());
        }

        String title = driver.findElement(By.xpath(Objects.Document.documenttitle)).getText();
        doc.put("Номер",new String[]{title.substring(2,title.indexOf("от")-1)});

        if (!status.contains("Черновик") && title.substring(2,title.indexOf("от")-1).equals("Не присвоено")) {
            doc.put("Номер_old",new String[]{title.substring(2,title.indexOf("от")-1)});
            doc.put("Срок исполнения_old", doc.get("Срок исполнения"));
            driver.get(driver.getCurrentUrl());
        }

        title = driver.findElement(By.xpath(Objects.Document.documenttitle)).getText();
        doc.put("Номер",new String[]{title.substring(2,title.indexOf("от")-1)});
        doc.put("Срок исполнения", new String[]{title.substring(title.indexOf("срок:")+6,title.length())});

        checkfield("Утверждено вне системы", Document.Viewform.Resolutionsdocument.approvedoutsidesystem_label, Document.Viewform.Resolutionsdocument.approvedoutsidesystem_field, doc);

        checkfield("Документ-основание", Document.Viewform.Resolutionsdocument.base_label, Document.Viewform.Resolutionsdocument.base_field, doc);

        checkfield("Основание", Document.Viewform.Resolutionsdocument.basedocument_label, Document.Viewform.Resolutionsdocument.basedocument_field, doc);

        checkfield("Контроль", Document.Viewform.Resolutionsdocument.isoncontrol_label, Document.Viewform.Resolutionsdocument.isoncontrol_field, doc);

        checkfield("Тематика", Document.Viewform.Resolutionsdocument.subject_label, Document.Viewform.Resolutionsdocument.subject_field, doc);

        checkfield("Автор", Document.Viewform.Resolutionsdocument.resolutionsauthor_label, Document.Viewform.Resolutionsdocument.resolutionsauthor_field, doc);

        checkfield("Создатель", Document.Viewform.Resolutionsdocument.documentauthor_label, Document.Viewform.Resolutionsdocument.documentauthor_field, doc);

        checkfield("Завершающий", Document.Viewform.Resolutionsdocument.closers_label, Document.Viewform.Resolutionsdocument.closers_field, doc);

        checkfield("Контролер", Document.Viewform.Resolutionsdocument.controller_label, Document.Viewform.Resolutionsdocument.controller_field, doc);

        checkfield("Срок исполнения", Document.Viewform.Resolutionsdocument.limitationdate_label,
                Document.Viewform.Resolutionsdocument.limitationdate_date_radio, Document.Viewform.Resolutionsdocument.limitationdate_date_field,
                Document.Viewform.Resolutionsdocument.limitationdate_limitless_radio, Document.Viewform.Resolutionsdocument.limitationdate_limitless_field, doc);



        String currenturl = driver.getCurrentUrl();
        if (currenturl.contains("#")){
            currenturl = currenturl.substring(0,currenturl.indexOf('#'));
        }
        if (!removedoc.contains(currenturl))
            removedoc.add(currenturl);
    }

    @Step("Атрибут <{0}>")
    private static void checkfield(String attrname, String xpath, String xpathradio1, String xpathfield1, String xpathradio2, String xpathfield2, Map<String, String[]> doc) {
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
    private static void checkfield(String attrname, String xpath, String xpathfield, Map<String, String[]> doc) {
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
            settext("Поиск",SelectDialog.Simple.input,value);
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
                    click("",SelectDialog.Recipient.select_type);
                    click("Сотрудник",SelectDialog.Recipient.select_type_employee);
                    break;
                case "Организация":
                    click("",SelectDialog.Recipient.select_type);
                    click("Сотрудник",SelectDialog.Recipient.select_type_organization);
                    break;
                default:
                    settext("",SelectDialog.Recipient.search_field,val);
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
            click("",SelectDialog.Sender.select_type);
            click("Внутренний контрагент",SelectDialog.Sender.select_type_organization);
            click("Показать дополнительные параметры поиска", SelectDialog.Sender.show_parametrs);
            fillfield("Наименование",SelectDialog.Sender.fullname_field,doc.get("Корреспондент Наименование"),doc);
            for (String val:doc.get("Корреспондент Наименование"))
                value = val;
            fillfield("ИНН",SelectDialog.Sender.fullname_field,doc.get("Корреспондент ИНН"),doc);
            fillfield("КПП",SelectDialog.Sender.fullname_field,doc.get("Корреспондент КПП"),doc);
        } else
        if (type.equals("Физическое лицо")){
            click("",SelectDialog.Sender.select_type);
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
        click("",SelectDialog.Sender.search_button);
        waitForLoad();
        click("",sd_sender_tableadd(value));
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

    private static void fillfield(String attrname, String xpath, String[] values, Map<String, String[]> doc) {
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
                        /*case "Подписано на бумажном носителе":
                        case "Завершающий":
                        case "Бессрочный":
                        case "Пункты Требуется отчет":
                        case "Подтверждать завершение работы по документу":*/
                            for (String value : values)
                                if (value.equals("Да") != driver.findElement(By.xpath(xpath)).isSelected())
                                    click(attrname, xpath);
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
            default:
                softassertfail(attrname + " - Тест не знает такого типа, надо дописать");
                break;
        }
    }

    @Step("Проверить наличие атрибута <{0}>")
    private static void verifyattr(String report, String xpath) {
        waitForLoad();
        try {
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            softassertfail("Не найден элемент " + xpath);
        }
        waitForLoad();
    }

    private static Boolean waitelement(String xpath) {
        waitForLoad();
        try {
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception e) {
            if (!xpath.equals(SelectDialog.Simple.dialog))
                softassertfail("Не найден элемент " + xpath);
            return false;
        }
        waitForLoad();
        return true;
    }

    @Step("Заполнить атрибут <{0}> значением <{2}>")
    private static void settext(String attrname, String xpath, String text) {
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
    private static void click(String report, String xpath) {
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

    private static void click(String report, String xpath, String waitdialog) {
        click(report, xpath);
        int i = 10;
        while (!waitelement(waitdialog) && i > 0) {
            clickagain(report, xpath);
            i--;
        }
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

    @Step("Проверить наличие записи в бж: {0}")
    static void readhistory(String[] values, Map<String, String[]> doc){
        click("История", Document.Viewform.Rightblock.history_header);
        click("Развернуть", Document.Viewform.Rightblock.history_open);
        String dynamicXPath = "//td[contains(.,\'%s\')]";
        for (String value: values) {
            String XPath = String.format(dynamicXPath, value);
            waitelement(Document.table_history);
            waitForLoad();
            waitelement(XPath);
        }
    }

}
