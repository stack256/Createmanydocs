package Box;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
                    attrname.equals("Подписан") || attrname.equals("Подписано на бумажном носителе"))
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
        if (values != null) {
            switch (attrname) {
                case "Заголовок":
                case "В ответ на Заголовок":
                case "Наименование":
                case "ИНН":
                case "КПП":
                case "ФИО":
                case "№ документа":
                case "Адрес":
                case "Исходящий номер":
                case "Исходящий от":
                case "Количество листов":
                case "Примечание":
                case "Срок исполнения":
                case "Содержит в названии":
                case "Номер":
                case "Дата создания С":
                case "Дата создания По":
                case "Срок ответа":
                case "Дата подписания":
                    for (String value : values)
                        settext(attrname, xpath, value);
                    break;
                case "Вид документа":
                case "Способ доставки":
                case "Регион":
                case "Представитель корреспондента":
                case "Адресат корреспондента":
                case "Автор":
                case "Тематика":
                case "Подписанты":
                    click("...", xpath);
                    fillselectdialogsimple(attrname, doc, values);
                    break;
                case "Корреспондент":
                    click("...", xpath);
                    fillselectdialogsender(attrname, values, doc);
                    break;
                case "Номер дела":
                    click("...", xpath);
                    fillselectdialogfileregister(attrname, doc, values);
                    break;
                case "Получатель":
                    click("...", xpath);
                    fillselectdialogrecipient(attrname, doc, values);
                    break;
                case "В ответ на":
                    click("...", xpath);
                    fillselectdialogresponseto(attrname, doc, values);
                    break;
                case "Содержание":
                    driver.switchTo().frame(driver.findElement(By.xpath(Document.Createform.Incomingdocument.summarycontent_iframe)));
                    for (String value : values)
                        settext(attrname, xpath, value);
                    driver.switchTo().defaultContent();
                    break;
                //чекбоксы которые по умолчанию в "Нет"
                case "На контроле":
                case "Нерегистрируемый":
                case "Подписано на бумажном носителе":
                case "Завершающий":
                    for (String value : values)
                        if (value.equals("Да"))
                            click(attrname, xpath);
                    break;
                /*чекбоксы которые по умолчанию в "Да"
                case "name of check":
                    for (String value : values)
                        if (value.equals("Нет"))
                            click(attrname, xpath);
                    break;*/
                default:
                    softassertfail(attrname + " - Тест не знает такого атрибута, надо дописать");
                    break;
            }
        } else
            switch (attrname){
                case "Наименование":
                case "ИНН":
                case "КПП":
                case "ФИО":
                case "№ документа":
                case "Адрес":
                case "Регион":
                    break;
                default:
                    doc.put(attrname, new String[]{"(Нет)"});
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

    private static void waitelement(String xpath) {
        waitForLoad();
        try {
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception e) {
            softassertfail("Не найден элемент " + xpath);
        }
        waitForLoad();
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
