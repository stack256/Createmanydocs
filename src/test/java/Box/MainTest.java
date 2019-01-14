package Box;


import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static Box.Base.auth;

@Features("Простые математические операции")
@Stories("История")
//@Test
public class MainTest extends About {

    @Description("Какое то описание")
    @Severity(SeverityLevel.CRITICAL)
    @Title("Создать")
    public void test() {
        auth("qwe","jd1","123");
        //ReadEmail("syakubov", "12345","Напоминание: Вам необходимо что-то сделать с незакрытыми пунктами документа ОРД: Требуется регистрация, неАвтоматическая регистрация, № 00481-17 от 28.09.2017, срок исполнения не указан");

    }




}