package Box;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Box.About.*;
import static Box.Base.*;

public class IncomingStep {
    @Step("Создать входящий документ")
    static void createincoming(Map<String, String[]> doc, boolean... flag) {
        if (flag.length == 0) {
            gotoarmsed();
            click("Создать", Objects.ARMSED.createButton);
            click("Входящий документ", Objects.ARMSED.Createmenu.incomingdocument);
            checkcreateincoming(doc);
            checkcreateincoming_value();
            fillcreateincoming(doc);
        } else
            checkcreateincoming(doc);

        checkcreateincoming_value(doc);

        click("Создать", Objects.Document.Createform.create_button, Objects.Document.Viewform.Incomingdocument.status_field);
        doc.put("Статус",new String[]{"Черновик"});
        doc.put("Номер",new String[]{"Не присвоено"});
    }

    @Step("Проверить значения атрибутов на форме создания")
    private static void checkcreateincoming_value(Map<String, String[]> doc) {
        checkattrcreateincoming_value("Входящий", doc);
        checkattrcreateincoming_value("Прочее", doc);
        checkattrcreateincoming_value("Заголовок", doc);
        checkattrcreateincoming_value("Вид документа", doc);
        checkattrcreateincoming_value("Способ доставки", doc);
        checkattrcreateincoming_value("Корреспондент", doc);
        checkattrcreateincoming_value("Представитель корреспондента", doc);
        checkattrcreateincoming_value("Получатель", doc);
        checkattrcreateincoming_value("В ответ на", doc);
        checkattrcreateincoming_value("Исходящий номер", doc);
        checkattrcreateincoming_value("Исходящий от", doc);
        checkattrcreateincoming_value("Содержание", doc);
        checkattrcreateincoming_value("Количество листов", doc);
        checkattrcreateincoming_value("Тематика", doc);
        checkattrcreateincoming_value("Номер дела", doc);
        checkattrcreateincoming_value("Примечание", doc);
        checkattrcreateincoming_value("Срок исполнения", doc);
        checkattrcreateincoming_value("На контроле", doc);
        checkattrcreateincoming_value("Нерегистрируемый", doc);
    }

    @Step("Проверить значения атрибутов на форме создания")
    private static void checkcreateincoming_value() {
        checkattrcreateincoming_value("Входящий");
        checkattrcreateincoming_value("Прочее");
        checkattrcreateincoming_value("Заголовок");
        checkattrcreateincoming_value("Вид документа");
        checkattrcreateincoming_value("Способ доставки");
        checkattrcreateincoming_value("Корреспондент");
        checkattrcreateincoming_value("Представитель корреспондента");
        checkattrcreateincoming_value("Получатель");
        checkattrcreateincoming_value("В ответ на");
        checkattrcreateincoming_value("Исходящий номер");
        checkattrcreateincoming_value("Исходящий от");
        checkattrcreateincoming_value("Содержание");
        checkattrcreateincoming_value("Количество листов");
        checkattrcreateincoming_value("Тематика");
        checkattrcreateincoming_value("Номер дела");
        checkattrcreateincoming_value("Примечание");
        checkattrcreateincoming_value("Срок исполнения");
        checkattrcreateincoming_value("На контроле");
        checkattrcreateincoming_value("Нерегистрируемый");
    }

    private static void checkattrcreateincoming_value(String AttrLabel, Map<String, String[]> doc) {
        if (AttrLabel.equals("Входящий") || AttrLabel.equals("Прочее"))
            checkattrcreateincomingchild_value(AttrLabel,doc.get("Вложения " + AttrLabel));
        else
            checkattrcreateincomingchild_value(AttrLabel,doc.get(AttrLabel));
    }

    private static void checkattrcreateincoming_value(String AttrLabel) {
        checkattrcreateincomingchild_value(AttrLabel);
    }

    @Step("{0}: null")
    private static void checkattrcreateincomingchild_value(String AttrLabel) {
        waitForLoad();
        String dynamicXPath = "//div[@class='document-metadata']//*[contains(text(),'%s')]";
        String XPath = String.format(dynamicXPath, AttrLabel);
        try {
            (new WebDriverWait(currentdriver(), currenttimeoutlnseconds()))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPath)));
        } catch (Exception e) {
            hardassertfail("Не найден элемент " + XPath);
        }

        switch (AttrLabel) {
            case "Заголовок":
            case "Исходящий номер":
            case "Количество листов":
                dynamicXPath = "//div[@class='document-metadata']//*[contains(text(),'%s:')]//ancestor::div[contains(@class,'editmode')]//div[contains(@class,'value')]//input[@type='text']";
                break;
            case "Исходящий от":
            case "Срок исполнения":
                dynamicXPath = "//div[@class='document-metadata']//*[contains(text(),'%s:')]//ancestor::div[contains(@class,'editmode')]//div[contains(@class,'value')]//input[@type='hidden']";
                break;
            case "Примечание":
                dynamicXPath = "//div[@class='document-metadata']//*[contains(text(),'%s:')]//ancestor::div[contains(@class,'editmode')]//div[contains(@class,'value')]//textarea";
                break;
            case "Корреспондент":
                dynamicXPath = "//div[@class='document-metadata']//*[contains(text(),'%s:')]//ancestor::div[contains(@class,'control')]//div[contains(@class,'cropped-item')]";
                break;
            case "Получатель":
            case "Тематика":
                dynamicXPath = "//div[@class='document-metadata']//*[contains(text(),'%s:')]//ancestor::div[contains(@class,'control')]//div[contains(@class,'cropped-item')]";
                break;
            case "Входящий":
            case "Прочее":
                dynamicXPath = "//div[@class='document-metadata']//*[contains(text(),'%s')]//ancestor::div[contains(@class,'uploader-block')]//div[contains(@class,'cropped-item')]";
                break;
            case "На контроле":
            case "Нерегистрируемый":
                dynamicXPath = "//div[@class='document-metadata']//*[contains(text(),'%s')]//ancestor::div[contains(@class,'control')]//input[@type='checkbox']";
                break;
            case "Содержание":
                dynamicXPath = "//body[@id='tinymce']";
                currentdriver().switchTo().frame(currentdriver().findElement(By.xpath(Objects.Document.Createform.summarycontent_iframe)));
                break;
            default:
                dynamicXPath = "//div[@class='document-metadata']//*[contains(text(),'%s:')]//ancestor::div[contains(@class,'editmode')]//div[contains(@class,'cropped-item')]";
                break;
        }

        XPath = String.format(dynamicXPath, AttrLabel);

        int i = timeoutlnsecond;
        while (i > 0 && currentdriver().findElements(By.xpath(XPath)).isEmpty()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i--;
        }

        if (AttrLabel.equals("На контроле") || AttrLabel.equals("Нерегистрируемый")){
            boolean t = false;
            if (currentdriver().findElement(By.xpath(XPath)).isSelected() != t)
                softassertfail("Атрибут не содержит значение " + t);
        } else {


            List<WebElement> elements = currentdriver().findElements(By.xpath(XPath));

            if (elements.size() > 0) {
                ArrayList<String> elementstext = new ArrayList<>();
                for (WebElement element : elements) {
                    if (element.getText().length() > 0)
                        elementstext.add(element.getText());
                }
                if (elementstext.size() > 0)
                    softassertfail("Лишние элементы в атрибуте " + elementstext);
            }
        }
        if (AttrLabel.equals("Содержание"))
            currentdriver().switchTo().defaultContent();
    }

    @Step("{0}: {1}")
    private static void checkattrcreateincomingchild_value(String AttrLabel, String[] values) {
        if (values != null)
            if (values[0].equals("Нет") || values[0].equals("(Нет)"))
                values = null;
        waitForLoad();
        String dynamicXPath = "//div[@class='document-metadata']//*[contains(text(),'%s')]";
        String XPath = String.format(dynamicXPath, AttrLabel);
        try {
            (new WebDriverWait(currentdriver(), currenttimeoutlnseconds()))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPath)));
        } catch (Exception e) {
            hardassertfail("Не найден элемент " + XPath);
        }

        switch (AttrLabel) {
            case "Заголовок":
            case "Исходящий номер":
            case "Количество листов":
                dynamicXPath = "//div[@class='document-metadata']//*[contains(text(),'%s:')]//ancestor::div[contains(@class,'editmode')]//div[contains(@class,'value')]//input[@type='text']";
                break;
            case "Исходящий от":
            case "Срок исполнения":
                dynamicXPath = "//div[@class='document-metadata']//*[contains(text(),'%s:')]//ancestor::div[contains(@class,'editmode')]//div[contains(@class,'value')]//input[@type='hidden']";
                break;
            case "Примечание":
                dynamicXPath = "//div[@class='document-metadata']//*[contains(text(),'%s:')]//ancestor::div[contains(@class,'editmode')]//div[contains(@class,'value')]//textarea";
                break;
            case "Корреспондент":
                dynamicXPath = "//div[@class='document-metadata']//*[contains(text(),'%s:')]//ancestor::div[contains(@class,'control')]//div[contains(@class,'cropped-item')]";
                break;
            case "Получатель":
            case "Тематика":
                dynamicXPath = "//div[@class='document-metadata']//*[contains(text(),'%s:')]//ancestor::div[contains(@class,'control')]//div[contains(@class,'cropped-item')]";
                break;
            case "На контроле":
            case "Нерегистрируемый":
                dynamicXPath = "//div[@class='document-metadata']//*[contains(text(),'%s')]//ancestor::div[contains(@class,'control')]//input[@type='checkbox']";
                break;
            case "Входящий":
            case "Прочее":
                dynamicXPath = "//div[@class='document-metadata']//*[contains(text(),'%s')]//ancestor::div[contains(@class,'uploader-block')]//div[contains(@class,'cropped-item')]";
                break;
            case "Содержание":
                dynamicXPath = "//body[@id='tinymce']";
                currentdriver().switchTo().frame(currentdriver().findElement(By.xpath(Objects.Document.Createform.summarycontent_iframe)));
                break;
            default:
                dynamicXPath = "//div[@class='document-metadata']//*[contains(text(),'%s:')]//ancestor::div[contains(@class,'editmode')]//div[contains(@class,'cropped-item')]";
                break;
        }

        XPath = String.format(dynamicXPath, AttrLabel);

        int i = timeoutlnsecond;
        while (i > 0 && currentdriver().findElements(By.xpath(XPath)).isEmpty()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i--;
        }


        if (AttrLabel.equals("На контроле") || AttrLabel.equals("Нерегистрируемый")){
            boolean t = false;
            if (values != null)
                t = values[0].equals("Да");
            if (currentdriver().findElement(By.xpath(XPath)).isSelected() != t)
                softassertfail("Атрибут не содержит значение " + values[0].equals("Да"));
        } else {



            List<WebElement> elements = currentdriver().findElements(By.xpath(XPath));

            if (values != null) {
                for (String value : values) {
                    if (dynamicXPath.contains("uploader-block"))
                        value = value.substring(System.getProperty("user.dir").length()+1);
                    boolean t = false;
                    for (WebElement element : elements) {
                        String elementtext = element.getText();
                        if (dynamicXPath.contains("//input[@type='text']") || dynamicXPath.contains("//textarea"))
                            elementtext = element.getAttribute("value");
                        if (dynamicXPath.contains("//input[@type='hidden']")) {
                            elementtext = element.getAttribute("value");
                            elementtext = elementtext.substring(8,10) + "." + elementtext.substring(5,7) + "." + elementtext.substring(0,4);
                        }
                        if (elementtext.contains(value)) {
                            t = true;
                            elements.remove(element);
                            break;
                        }
                    }
                    if (AttrLabel.equals("Получатель") && (value.equals("Сотрудник") || value.equals("Организация")))
                        t = true;
                    softassertfail(t, "Атрибут не содержит значение " + value + " в поле " + XPath);
                }
            }

            if (elements.size() > 0) {
                ArrayList<String> elementstext = new ArrayList<>();
                for (WebElement element : elements) {
                    if (element.getText().length() > 0)
                        elementstext.add(element.getText());
                }
                if (elementstext.size() > 0)
                    softassertfail("Лишние элементы в атрибуте " + elementstext);
            }
        }
        if (AttrLabel.equals("Содержание"))
            currentdriver().switchTo().defaultContent();
    }

    @Step("Проверить наличие атрибутов на форме создания")
    private static void checkcreateincoming(Map<String, String[]> doc) {
        checkattrcreateincoming("Вложения");
        checkattrcreateincoming("Заголовок");
        checkattrcreateincoming("Вид документа");
        checkattrcreateincoming("Способ доставки");
        checkattrcreateincoming("Корреспондент");
        checkattrcreateincoming("Представитель корреспондента");
        checkattrcreateincoming("Получатель");
        checkattrcreateincoming("В ответ на");
        checkattrcreateincoming("Исходящий номер");
        checkattrcreateincoming("Исходящий от");
        checkattrcreateincoming("Содержание");
        checkattrcreateincoming("Количество листов");
        checkattrcreateincoming("Тематика");
        checkattrcreateincoming("Номер дела");
        checkattrcreateincoming("Примечание");
        checkattrcreateincoming("Срок исполнения");
        checkattrcreateincoming("На контроле");
        checkattrcreateincoming("Нерегистрируемый");
    }

    @Step("Заполнить атрибуты")
    private static void fillcreateincoming(Map<String, String[]> doc) {
        fillattrcreateincoming_attach("Входящий", doc);
        fillattrcreateincoming_attach("Прочее", doc);
        fillattrcreateincoming_input("Заголовок",doc);
        fillattrcreateincoming_dsimple("Вид документа",doc);
        fillattrcreateincoming_dsimple("Способ доставки",doc);
        fillattrcreateincoming_dsender("Корреспондент",doc);
        fillattrcreateincoming_dsimple("Представитель корреспондента",doc);
        fillattrcreateincoming_drecipient("Получатель",doc);
        fillattrcreateincoming_dresponseto("В ответ на",doc);
        fillattrcreateincoming_input("Исходящий номер",doc);
        fillattrcreateincoming_date("Исходящий от",doc);
        fillattrcreateincoming_summary("Содержание",doc);
        fillattrcreateincoming_input("Количество листов",doc);
        fillattrcreateincoming_dsimple("Тематика",doc);
        fillattrcreateincoming_dfileregister("Номер дела",doc);
        fillattrcreateincoming_textarea("Примечание",doc);
        fillattrcreateincoming_date("Срок исполнения",doc);
        fillattrcreateincoming_checkbox("На контроле",doc);
        fillattrcreateincoming_checkbox("Нерегистрируемый",doc);
    }

    @Step("Проверить наличие атрибутов и их значения на форме просмотра")
    static void readincoming(Map<String, String[]> doc) {
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
        doc.put("Номер",new String[]{title.substring(title.indexOf(" № ")+3,title.indexOf(" от "))});
        doc.put("Дата",new String[]{title.substring(title.indexOf(" от ")+4,title.length())});

        checkattrviewincoming("Номер", doc);
        checkattrviewincoming("Дата регистрации", doc);
        checkattrviewincoming("Заголовок", doc);
        checkattrviewincoming("Вид документа", doc);
        checkattrviewincoming("Способ доставки", doc);
        checkattrviewincoming("Корреспондент", doc);
        checkattrviewincoming("Представитель корреспондента", doc);
        checkattrviewincoming("Исходящий номер", doc);
        checkattrviewincoming("Исходящий от", doc);
        checkattrviewincoming("Содержание", doc);
        checkattrviewincoming("Получатель", doc);
        checkattrviewincoming("Срок исполнения", doc);
        checkattrviewincoming("На контроле", doc);
        checkattrviewincoming("Номер дела", doc);
        checkattrviewincoming("Количество листов", doc);
        checkattrviewincoming("Тематика", doc);
        checkattrviewincoming("Примечание", doc);
        checkattrviewincoming("Нерегистрируемый", doc);
        checkattrviewincoming("Номер", doc);

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

    private static void checkattrviewincoming(String AttrLabel, Map<String, String[]> doc) {
        if (doc.get(AttrLabel) == null)
            if (AttrLabel.equals("Нерегистрируемый") || AttrLabel.equals("На контроле"))
                doc.put(AttrLabel, new String[]{"Нет"});
            else
                doc.put(AttrLabel, new String[]{"(Нет)"});
        checkattrviewincomingchild(AttrLabel,doc.get(AttrLabel));
    }

    @Step("{0}: {1}")
    private static void checkattrviewincomingchild(String AttrLabel, String[] values) {
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
            if (AttrLabel.equals("Получатель") && (value.equals("Сотрудник") || value.equals("Организация")))
                t = true;
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

    @Step("Проверить наличие уведомлений у получателей")
    static void recipientnotifications(HashMap<String, String[]> doc) {
        for (String val:doc.get("СЭД. Получатель"))
            readnotification(val,"Вам направлен новый документ " + doc.get("Заголовок")[0]);
    }


}
