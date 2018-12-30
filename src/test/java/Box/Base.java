package Box;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.*;

import javax.lang.model.util.Elements;
import java.util.List;
import java.util.Map;

import static Box.Objects.*;
import static Box.About.*;

class Base {

    @Step("Авторизоваться пользователем {0}")
    static void auth(String report, String login, String pass) {
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
    }

    @Step("Перейти в АРМ СЭД")
    static void gotoarmsed() {
        click("Логика: СЭД", MenuBar.logsed);
        timeoutlnseconds = 120;
        waitelement(ARMSED.createButton);
        timeoutlnseconds = 30;
    }

    @Step("Создать входящий документ")
    static void createincoming(Map doc) {
        gotoarmsed();
        click("Создать",ARMSED.createButton);
        click("Входящий документ", ARMSED.Createmenu.incomingdocument);
        verifyattr("Вложения", Document.Createform.Incomingdocument.attachments_label);
        verifyattr("Заголовок", Document.Createform.Incomingdocument.title_label);
        fillfield("Заголовок",Document.Createform.Incomingdocument.title_field, (String[]) doc.get("Заголовок"));
        verifyattr("Вид документа", Document.Createform.Incomingdocument.type_label);
        fillfield("Вид документа",Document.Createform.Incomingdocument.type_button, (String[]) doc.get("Вид документа"));
        verifyattr("Способ доставки", Document.Createform.Incomingdocument.delivery_method_label);
        fillfield("Способ доставки",Document.Createform.Incomingdocument.delivery_method_button, (String[]) doc.get("Способ доставки"));
        verifyattr("sender_label", Document.Createform.Incomingdocument.sender_label);
        verifyattr("addressee_label", Document.Createform.Incomingdocument.addressee_label);
        verifyattr("recipient_label", Document.Createform.Incomingdocument.recipient_label);
        verifyattr("response_to_label", Document.Createform.Incomingdocument.response_to_label);
        verifyattr("outgoing_number_label", Document.Createform.Incomingdocument.outgoing_number_label);
        verifyattr("outgoing_date_label", Document.Createform.Incomingdocument.outgoing_date_label);
        verifyattr("summarycontent_label", Document.Createform.Incomingdocument.summarycontent_label);
        verifyattr("sheets_number_label", Document.Createform.Incomingdocument.sheets_number_label);
        verifyattr("subject_label", Document.Createform.Incomingdocument.subject_label);
        verifyattr("file_register_label", Document.Createform.Incomingdocument.file_register_label);
        verifyattr("note_label", Document.Createform.Incomingdocument.note_label);
        verifyattr("execution_date_label", Document.Createform.Incomingdocument.execution_date_label);
        verifyattr("is_on_control_label", Document.Createform.Incomingdocument.is_on_control_label);
        verifyattr("is_not_registered_label", Document.Createform.Incomingdocument.is_not_registered_label);




/*

        'Заполнить Способ доставки'
        if (create_delivery_method != '') {
            'Открыть форму выбора способа доставки'
            WebUI.callTestCase(findTestCase('Base Test Case/Click'), [('button') : findTestObject('Document/Create form/Incoming document/Delivery-method_button')], FailureHandling.STOP_ON_FAILURE)

            'Заполнить "Способ доставки"'
            WebUI.callTestCase(findTestCase('Base Test Case/Fill_select-dialog/Fill_select-dialog_simple'), [('value1') : create_delivery_method])

            'Проверить заполненный способ доставки'
            WebUI.verifyElementText(findTestObject('Document/Create form/Incoming document/Delivery-method_field'), create_delivery_method)
        }

        'Заполнить Корреспондент'
        if ((create_sender_fullname != '') || (create_sender_lastname != '')) {
            if (create_sender_fullname != '') {
                create_sender = create_sender_fullname
            }

            if (create_sender_lastname != '') {
                create_sender = create_sender_lastname
            }

            'Открыть форму выбора корреспондента'
            WebUI.callTestCase(findTestCase('Base Test Case/Click'), [('button') : findTestObject('Document/Create form/Incoming document/Sender_button')], FailureHandling.STOP_ON_FAILURE)

            'Заполнить "Корреспондент"'
            WebUI.callTestCase(findTestCase('Base Test Case/Fill_select-dialog/Fill_select-dialog_sender'), [('type') : create_sender_type, ('fullname') : create_sender_fullname
                    , ('inn') : create_sender_inn, ('kpp') : create_sender_kpp, ('lastname') : create_sender_lastname, ('documentnumber') : create_sender_documentnumber
                    , ('address') : create_sender_address, ('region') : create_sender_region])

            //WebUI.verifyElementText(findTestObject('Document/Create form/Incoming document/Sender_field'), 'AT_Organization')
            elements = WebUI.getText(findTestObject('Document/Create form/Incoming document/Sender_field'))

            if (!(elements.contains(create_sender))) {
                KeywordUtil.markError('Failure Message')
            }
        }

        'Заполнить Представитель корреспондента'
        if (create_addressee != '') {
            'Открыть форму выбора представителя корреспондента'
            WebUI.callTestCase(findTestCase('Base Test Case/Click'), [('button') : findTestObject('Document/Create form/Incoming document/Addressee_button')], FailureHandling.STOP_ON_FAILURE)

            'Заполнить "Представитель корреспондента"'
            WebUI.callTestCase(findTestCase('Base Test Case/Fill_select-dialog/Fill_select-dialog_simple'), [('value1') : create_addressee])

            'Проверить заполненный представитель корреспондента'
            WebUI.verifyElementText(findTestObject('Document/Create form/Incoming document/Addressee_field'), create_addressee)
        }

        'Заполнить Получатель'
        if (create_recipient != '') {
            'Открыть форму выбора получателя'
            WebUI.callTestCase(findTestCase('Base Test Case/Click'), [('button') : findTestObject('Document/Create form/Incoming document/Recipient_button')], FailureHandling.STOP_ON_FAILURE)

            'Заполнить "Получатель"'
            WebUI.callTestCase(findTestCase('Base Test Case/Fill_select-dialog/Fill_select-dialog_recipient'), [('type1') : create_recipient_type
                    , ('value1') : create_recipient])

            'Проверить заполненный получатель'
            WebUI.verifyElementText(findTestObject('Document/Create form/Incoming document/Recipient_field'), create_recipient)
        }

        'Заполнить В ответ на'
        if (create_response_to != '') {
            'Открыть форму в ответ на'
            WebUI.callTestCase(findTestCase('Base Test Case/Click'), [('button') : findTestObject('Document/Create form/Incoming document/Response-to_button')], FailureHandling.STOP_ON_FAILURE)

            'Заполнить "В ответ на"'
            WebUI.callTestCase(findTestCase('Base Test Case/Fill_select-dialog/Fill_select-dialog_response-to'), [('searchtext') : create_response_to_searchtext
                    , ('regnum') : create_response_to_regnum, ('title') : create_response_to_title, ('datefrom') : create_response_to_datefrom
                    , ('dateto') : create_response_to_dateto, ('author') : create_response_to_author, ('searchlogic') : create_response_to_searchlogic
                    , ('result') : create_response_to])

            'Проверить заполненный в ответ на'
            WebUI.verifyElementText(findTestObject('Document/Create form/Incoming document/Response-to_field'), create_response_to)
        }

        'Заполнить Исходящий номер'
        WebUI.callTestCase(findTestCase('Base Test Case/Set text'), [('element') : findTestObject('Document/Create form/Incoming document/Outgoing-number_field'), ('value') : create_outgoing_number], FailureHandling.STOP_ON_FAILURE)

        'Заполнить Исходящий от'
        WebUI.callTestCase(findTestCase('Base Test Case/Set text'), [('element') : findTestObject('Document/Create form/Incoming document/Outgoing-date_field'), ('value') : create_outgoing_date], FailureHandling.STOP_ON_FAILURE)

        'Заполнить Содержание'
        WebUI.callTestCase(findTestCase('Base Test Case/Set text'), [('element') : findTestObject('Document/Create form/Incoming document/SummaryContent_field'), ('value') : create_summary], FailureHandling.STOP_ON_FAILURE)

        'Заполнить Количество листов'
        WebUI.callTestCase(findTestCase('Base Test Case/Set text'), [('element') : findTestObject('Document/Create form/Incoming document/Sheets-number_field'), ('value') : create_sheets_number], FailureHandling.STOP_ON_FAILURE)

        'Заполнить Тематика'
        if (create_subject != '') {
            'Открыть форму выбора тематика'
            WebUI.callTestCase(findTestCase('Base Test Case/Click'), [('button') : findTestObject('Document/Create form/Incoming document/Subject_button')], FailureHandling.STOP_ON_FAILURE)

            'Заполнить "Тематика"'
            WebUI.callTestCase(findTestCase('Base Test Case/Fill_select-dialog/Fill_select-dialog_simple'), [('value1') : create_subject])

            //WebUI.verifyElementText(findTestObject('Document/Create form/Incoming document/Subject_field'), 'Доставка воды')
            elements = WebUI.getText(findTestObject('Document/Create form/Incoming document/Subject_field'))

            'Проверить заполненный тематика'
            if (!(elements.contains(create_subject))) {
                KeywordUtil.markError('Failure Message')
            }
        }

        'Заполнить Номер дела'
        if (create_file_register != '') {
            'Открыть форму выбора номер дела'
            WebUI.callTestCase(findTestCase('Base Test Case/Click'), [('button') : findTestObject('Document/Create form/Incoming document/File-register_button')], FailureHandling.STOP_ON_FAILURE)

            'Заполнить номер дела'
            WebUI.callTestCase(findTestCase('Base Test Case/Fill_select-dialog/Fill_select-dialog_file-register'), [('value1') : create_file_register1
                    , ('value2') : create_file_register2, ('value3') : create_file_register3, ('value4') : create_file_register4
                    , ('value5') : create_file_register5, ('value6') : create_file_register6, ('value7') : create_file_register7
                    , ('value8') : create_file_register8, ('value9') : create_file_register9])

            elements = WebUI.getText(findTestObject('Document/Create form/Incoming document/File-register_field'))

            'Проверить заполненный номер дела'
            if (!(elements.contains(create_file_register))) {
                KeywordUtil.markError('Failure Message')
            }
        }

        'Заполнить Примечание'
        WebUI.callTestCase(findTestCase('Base Test Case/Set text'), [('element') : findTestObject('Document/Create form/Incoming document/Note_field'), ('value') : create_note], FailureHandling.STOP_ON_FAILURE)

        'Заполнить Срок исполнения'
        WebUI.callTestCase(findTestCase('Base Test Case/Set text'), [('element') : findTestObject('Document/Create form/Incoming document/Execution-date_field'), ('value') : create_execution_date], FailureHandling.STOP_ON_FAILURE)

        'Заполнить На контроле'
        if(create_is_on_control.equals('Да')){
            WebUI.callTestCase(findTestCase('Base Test Case/Click'), [('button') : findTestObject('Document/Create form/Incoming document/Is-on-control_checkbox')], FailureHandling.STOP_ON_FAILURE)
        }

        'Заполнить Нерегистрируемый'
        if(create_is_not_registered.equals('Да')){
            WebUI.callTestCase(findTestCase('Base Test Case/Click'), [('button') : findTestObject('Document/Create form/Incoming document/Is-not-registered_checkbox')], FailureHandling.STOP_ON_FAILURE)
        }

        String currenturl = WebUI.getUrl()

        WebUI.callTestCase(findTestCase('Base Test Case/Click'), [('button') : findTestObject('Document/Create form/Create_button')], FailureHandling.STOP_ON_FAILURE)

        while (currenturl == WebUI.getUrl()) {
            Thread.sleep(100)
        }*/
    }

    private static void fillselectdialogsimple(String... values) {
        waitelement(SelectDialog.Simple.dialog);
        for(String value:values){
            settext("",SelectDialog.Simple.input,value);
            click("",SelectDialog.Simple.search_button);
            waitForLoad();
            click("",sd_simple_tableadd(value));
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
    }

    @Step("Заполнить атрибут <{0}>")
    private static void fillfield(String attrname, String xpath, String[] values) {
        if (values != null) {
            switch (attrname) {
                case "Заголовок":
                    for (String value : values)
                        settext("Заголовок", xpath, value);
                    break;
                case "Вид документа":
                case "Способ доставки":
                    click("...", xpath);
                    fillselectdialogsimple(values);
                    break;
                default:
                    hardassertfail(attrname + " - Нет такого атрибута");
                    break;
            }
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
            hardassertfail("Не найден элемент " + xpath);
        }
        waitForLoad();
    }

    @Step("Заполнить атрибут <{0}> значением <{2}>")
    private static void settext(String report, String xpath, String text) {
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
            (new WebDriverWait(driver, timeoutlnseconds))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception e) {
            hardassertfail("Не найден элемент " + xpath);
        }
        driver.findElement(By.xpath(xpath)).click();
        waitForLoad();
    }
}
