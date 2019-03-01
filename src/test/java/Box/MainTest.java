package Box;



import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import static Box.Base.*;
import static Box.ErrandStep.readerrand1;
import static Box.IncomingStep.*;
import static Box.InternalStep.createinternal;
import static Box.Users.*;

@Features("Тестовый")
@Stories("История")
@Test
public class MainTest extends About {

    @Title("Создать внутренний документ")
    public void test1() {

        HashMap<String, String[]> doc = new HashMap<String, String[]>();;
        doc.put("document", new String[]{"internal"});
        doc.put("Вид документа", new String[]{"Акт выполненных работ"});
        doc.put("Номер документа", new String[]{"21"});
        doc.put("Дата документа", new String[]{"21.12.2019"});
        doc.put("Вложения Документ", new String[]{"C:\\Webdrivers\\Вложение.doc"});
        doc.put("Вложения Приложения", new String[]{"C:\\Webdrivers\\Вложение.xls"});

        //авторизоваться
        auth("syakubov", "syakubov", "111");

        for (int i = 0; i < 3000; i++ ) {
            //создать входящий документ

            gotoarmsed("http://172.19.213.17:8080/share/page/arm?code=ARCHIVE_FINANCE");

            currentdriver().get(currentdriver().getCurrentUrl());

            click("Создать", Objects.ARMSED.createButton);
            click("Входящий первичный документ", Objects.ARMSED.Createmenu.internaldocument);

            waitForLoad();
            fillattrcreateincoming_attach("Документ", doc);
            waitForLoad();
            fillattrcreateincoming_attach("Приложения", doc);
            waitForLoad();

            verifyattr("Вид документа", Objects.Document.Createform.Internaldocument.type_label);
            fillfield("Вид документа", Objects.Document.Createform.Internaldocument.type_button, doc.get("Вид документа"), doc);

            verifyattr("Номер документа", Objects.Document.Createform.Internaldocument.title_label);
            fillfield("Номер документа", Objects.Document.Createform.Internaldocument.title_field, doc.get("Номер документа"), doc);

            verifyattr("Дата документа", Objects.Document.Createform.Internaldocument.signing_date_label);
            fillfield("Дата документа", Objects.Document.Createform.Internaldocument.signing_date_field, doc.get("Дата документа"), doc);

            click("Создать", Objects.Document.Createform.create_button);

            clickaction("Добавить в реестр выгрузки", righaction("Добавить в реестр выгрузки"));


        }



    }




}