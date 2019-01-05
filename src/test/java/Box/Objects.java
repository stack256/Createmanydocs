package Box;

class Objects {
    static class AuthPage {
        static String username = "//input[@name='username']";
        static String password = "//input[@name='password']";
        static String login = "//button[text()='Войти']";
    }

    static class MenuBar{
        static String user_menu_popup = "//*[@id='HEADER_USER_MENU_POPUP_text']";
        static String user_menu_logout = "//*[@id='HEADER_USER_MENU_LOGOUT_text']";
        static String notifications_check_opened = "(//div[contains(@class,'notifications-group')]//tr)[1]";
        static String notifications = "//div[@id='HEADER_APP_MENU_BAR']//*[text()='Уведомления']";
        static String logsed = "//div[@id='HEADER_APP_MENU_BAR']//a[text()='Логика: СЭД']";
    }

    static class SelectDialog{
        static class Simple{
            static String selected_elements = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[contains(@id,'selected-elements')]";
            static String search_button = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//button[contains(@id,'search')]";
            static String ok_button = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//button[text()='ОК']";
            static String input = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//input";
            static String dialog = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]";
        }
        static class Sender {
            static String show_parametrs = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[@class='form-fields']/div[(contains(@id,'contractor') or contains(@id,'organisation') or contains(@id,'person')) and not(contains(@class,'hidden'))]//a[text()='Показать дополнительные параметры поиска']";
            static String selected_elements = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[contains(@id,'picker-items')]";
            static String select_type_person = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//li//a[text()='Физическое лицо']";
            static String select_type_organization = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//li//a[text()='Внутренний контрагент']";
            static String select_type = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//button[contains(@id,'select-button')]";
            static String search_button = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[@class='form-fields']/div[(contains(@id,'contractor') or contains(@id,'organisation') or contains(@id,'person')) and not(contains(@class,'hidden'))]//button[text()='Поиск']";
            static String region_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[@class='form-fields']/div[(contains(@id,'contractor') or contains(@id,'organisation') or contains(@id,'person')) and not(contains(@class,'hidden'))]//*[contains(text(),'Регион:')]//ancestor::div[contains(@class,'control editmode')]//input[@type='text']";
            static String region_button = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[@class='form-fields']/div[(contains(@id,'contractor') or contains(@id,'organisation') or contains(@id,'person')) and not(contains(@class,'hidden'))]//*[contains(text(),'Регион:')]//ancestor::div[contains(@class,'control editmode')]//button";
            static String ok_button = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//button[text()='ОК']";
            static String lastname_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[@class='form-fields']/div[(contains(@id,'contractor') or contains(@id,'organisation') or contains(@id,'person')) and not(contains(@class,'hidden'))]//input[contains(@name,'lastName')]";
            static String fullname_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[@class='form-fields']/div[(contains(@id,'contractor') or contains(@id,'organisation') or contains(@id,'person')) and not(contains(@class,'hidden'))]//input[contains(@name,'fullname')]";
            static String document_number_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[@class='form-fields']/div[(contains(@id,'contractor') or contains(@id,'organisation') or contains(@id,'person')) and not(contains(@class,'hidden'))]//input[contains(@name,'document-number')]";
            static String address_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[@class='form-fields']/div[(contains(@id,'contractor') or contains(@id,'organisation') or contains(@id,'person')) and not(contains(@class,'hidden'))]//input[contains(@name,'address')]";
            static String KPP_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[@class='form-fields']/div[(contains(@id,'contractor') or contains(@id,'organisation') or contains(@id,'person')) and not(contains(@class,'hidden'))]//input[contains(@name,'KPP')]";
            static String INN_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[@class='form-fields']/div[(contains(@id,'contractor') or contains(@id,'organisation') or contains(@id,'person')) and not(contains(@class,'hidden'))]//input[contains(@name,'INN')]";
            static String dialog = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]";
        }
        static class Responseto {
            static String title_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//input[contains(@id,'title')]";
            static String show_parametrs = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//a[text()='Показать дополнительные параметры поиска']";
            static String selected_elements = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[contains(@id,'picker-items')]";
            static String search_text_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//input[contains(@id,'search-text')]";
            static String search_logic = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//select[contains(@id,'search-logic')]";
            static String search_button = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//button[text()='Искать']";
            static String regnum_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//input[contains(@id,'regnum')]";
            static String ok_button = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//button[text()='ОК']";
            static String date_to_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//input[contains(@id,'date-to')]";
            static String date_from_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//input[contains(@id,'date-from')]";
            static String author_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Автор:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
            static String author_button = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Автор:')]//ancestor::div[contains(@class,'control editmode')]//button";
            static String dialog = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]";
        }
        static class Recipient {
            static String selected_elements = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[contains(@id,'picker-items')]";
            static String select_type_organization = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//li//a[text()='Организация']";
            static String select_type_employee = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//li//a[text()='Сотрудник']";
            static String select_type = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//button[contains(@id,'select-button')]";
            static String search_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[@class='form-fields']/div[(contains(@id,'employee') or contains(@id,'organization')) and not(contains(@class,'hidden'))]//input[contains(@id,'search-text')]";
            static String search_button = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[@class='form-fields']/div[(contains(@id,'employee') or contains(@id,'organization')) and not(contains(@class,'hidden'))]//button[contains(@id,'search-button')]";
            static String ok_button = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//button[text()='ОК']";
            static String dialog = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]";
        }
        static class Fileregister {
            static String selected_elements = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[contains(@id,'selected-elements')]";
            static String ok_button = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//button[text()='ОК']";
            static String dialog = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]";
        }
        static String clearall = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//span[text()='Очистить']";
    }

    static class Document{
        static String documenttitle = "//div[@class='document-header']//span[@class='title']";
        static String table_history = "//table//*[text()='Описание']";
        static String delete_check = "//div[contains(text(),'Документ был удален.')]";
        static String copy_document = "//button[@title='Копировать документ']";
        static String admin_function = "//button[@title='Административные функции']";
        static String admin_delete = "//li//a[text()='Удалить безвозвратно']";
        static class Napravitadresatam{
            static String dialog = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]";
            static String type_label = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Тип поручения:')]";
            static String type_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[contains(@id,'type')]//div[contains(@class,'cropped-item')]//div[contains(@class,'value')";
            static String title_label = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Заголовок:')]";
            static String title_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[contains(@id,'title')]//div[contains(@class,'value')]//input";
            static String executor_label = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Получатель:')]";
            static String executor_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//div[contains(@id,'executor')]//div[contains(@class,'cropped-item')]//div[contains(@class,'value')]";
        }
        static class Createform{
            static String create_button = "//div[@class='document-metadata']//button[text()='Создать']";
            static class Incomingdocument {
                static String type_label = "//div[@class='document-metadata']//*[contains(text(),'Вид документа:')]";
                static String type_field = "//div[@class='document-metadata']//*[contains(text(),'Вид документа:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String type_button = "//div[@class='document-metadata']//*[contains(text(),'Вид документа:')]//ancestor::div[contains(@class,'control')]//button";
                static String title_label = "//div[@class='document-metadata']//*[contains(text(),'Заголовок:')]";
                static String title_field = "//div[@class='document-metadata']//*[contains(text(),'Заголовок:')]//ancestor::div[contains(@class,'control')]//input";
                static String summarycontent_label = "//div[@class='document-metadata']//*[contains(text(),'Содержание:')]";
                static String summarycontent_iframe = "//iframe[contains(@id,'summaryContent')]";
                static String summarycontent_field = "//body[@id='tinymce']";
                static String subject_label = "//div[@class='document-metadata']//*[contains(text(),'Тематика:')]";
                static String subject_field = "//div[@class='document-metadata']//*[contains(text(),'Тематика:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String subject_button = "//div[@class='document-metadata']//*[contains(text(),'Тематика:')]//ancestor::div[contains(@class,'control')]//button";
                static String sheets_number_label = "//div[@class='document-metadata']//*[contains(text(),'Количество листов:')]";
                static String sheets_number_field = "//div[@class='document-metadata']//*[contains(text(),'Количество листов:')]//ancestor::div[contains(@class,'control')]//input";
                static String sender_label = "//div[@class='document-metadata']//*[contains(text(),'Корреспондент:')]";
                static String sender_field = "//div[@class='document-metadata']//*[contains(text(),'Корреспондент:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String sender_button = "//div[@class='document-metadata']//*[contains(text(),'Корреспондент:')]//ancestor::div[contains(@class,'control')]//button";
                static String response_to_label = "//div[@class='document-metadata']//*[contains(text(),'В ответ на:')]";
                static String response_to_field = "//div[@class='document-metadata']//*[contains(text(),'В ответ на:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String response_to_button = "//div[@class='document-metadata']//*[contains(text(),'В ответ на:')]//ancestor::div[contains(@class,'control')]//button";
                static String recipient_label = "//div[@class='document-metadata']//*[contains(text(),'Получатель:')]";
                static String recipient_field = "//div[@class='document-metadata']//*[contains(text(),'Получатель:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String recipient_button = "//div[@class='document-metadata']//*[contains(text(),'Получатель:')]//ancestor::div[contains(@class,'control')]//button";
                static String outgoing_number_label = "//div[@class='document-metadata']//*[contains(text(),'Исходящий номер:')]";
                static String outgoing_number_field = "//div[@class='document-metadata']//*[contains(text(),'Исходящий номер:')]//ancestor::div[contains(@class,'control')]//input";
                static String outgoing_date_label = "//div[@class='document-metadata']//*[contains(text(),'Исходящий от:')]";
                static String outgoing_date_field = "//div[@class='document-metadata']//*[contains(text(),'Исходящий от:')]//ancestor::div[contains(@class,'control')]//input[@type='text']";
                static String note_label = "//div[@class='document-metadata']//*[contains(text(),'Примечание:')]";
                static String note_field = "//div[@class='document-metadata']//*[contains(text(),'Примечание:')]//ancestor::div[contains(@class,'control')]//textarea";
                static String is_on_control_label = "//div[@class='document-metadata']//*[contains(text(),'На контроле')]";
                static String is_on_control_checkbox = "//div[@class='document-metadata']//*[contains(text(),'На контроле')]//ancestor::div[contains(@class,'control')]//input[@type='checkbox']";
                static String is_not_registered_label = "//div[@class='document-metadata']//*[contains(text(),'Нерегистрируемый')]";
                static String is_not_registered_checkbox = "//div[@class='document-metadata']//*[contains(text(),'Нерегистрируемый')]//ancestor::div[contains(@class,'control')]//input[@type='checkbox']";
                static String file_register_label = "//div[@class='document-metadata']//*[contains(text(),'Номер дела:')]";
                static String file_register_field = "//div[@class='document-metadata']//*[contains(text(),'Номер дела:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String file_register_button = "//div[@class='document-metadata']//*[contains(text(),'Номер дела:')]//ancestor::div[contains(@class,'control')]//button";
                static String execution_date_label = "//div[@class='document-metadata']//*[contains(text(),'Срок исполнения:')]";
                static String execution_date_field = "//div[@class='document-metadata']//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'control')]//input[@type='text']";
                static String delivery_method_label = "//div[@class='document-metadata']//*[contains(text(),'Способ доставки:')]";
                static String delivery_method_field = "//div[@class='document-metadata']//*[contains(text(),'Способ доставки:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String delivery_method_button = "//div[@class='document-metadata']//*[contains(text(),'Способ доставки:')]//ancestor::div[contains(@class,'control')]//button";
                static String attachments_label = "//div[@class='document-metadata']//*[contains(text(),'Вложения:')]";
                static String addressee_label = "//div[@class='document-metadata']//*[contains(text(),'Представитель корреспондента:')]";
                static String addressee_field = "//div[@class='document-metadata']//*[contains(text(),'Представитель корреспондента:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String addressee_button = "//div[@class='document-metadata']//*[contains(text(),'Представитель корреспондента:')]//ancestor::div[contains(@class,'control')]//button";
            }
            static class Internaldocument {
                static String attachments_label = "//div[@class='document-metadata']//*[contains(text(),'Вложения:')]";
                /*
                утт типа пути до нопок и полей составителя и исполнителя
                но ограничусь только названиями атрибутов, потому что пока что не хочу их заполнять
                */
                static String author_label = "//div[@class='document-metadata']//*[contains(text(),'Составитель:')]";
                static String executor_label = "//div[@class='document-metadata']//*[contains(text(),'Исполнитель:')]";
                static String title_label = "//div[@class='document-metadata']//*[contains(text(),'Заголовок:')]";
                static String title_field = "//div[@class='document-metadata']//*[contains(text(),'Заголовок:')]//ancestor::div[contains(@class,'control')]//input";
                static String type_label = "//div[@class='document-metadata']//*[contains(text(),'Вид документа:')]";
                static String type_field = "//div[@class='document-metadata']//*[contains(text(),'Вид документа:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String type_button = "//div[@class='document-metadata']//*[contains(text(),'Вид документа:')]//ancestor::div[contains(@class,'control')]//button";
                static String execution_date_label = "//div[@class='document-metadata']//*[contains(text(),'Срок ответа:')]";
                static String execution_date_field = "//div[@class='document-metadata']//*[contains(text(),'Срок ответа:')]//ancestor::div[contains(@class,'control')]//input[@type='text']";
                static String recipient_label = "//div[@class='document-metadata']//*[contains(text(),'Получатель:')]";
                static String recipient_field = "//div[@class='document-metadata']//*[contains(text(),'Получатель:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String recipient_button = "//div[@class='document-metadata']//*[contains(text(),'Получатель:')]//ancestor::div[contains(@class,'control')]//button";
                static String summarycontent_label = "//div[@class='document-metadata']//*[contains(text(),'Содержание:')]";
                static String summarycontent_iframe = "//iframe[contains(@id,'summaryContent')]";
                static String summarycontent_field = "//body[@id='tinymce']";
                static String signedbypaper_label = "//div[@class='document-metadata']//*[contains(text(),'Подписано на бумажном носителе')]";
                static String signedbypaper_checkbox = "//div[@class='document-metadata']//*[contains(text(),'Подписано на бумажном носителе')]//ancestor::div[contains(@class,'control')]//input[@type='checkbox']";
                static String signers_label = "//div[@class='document-metadata']//*[contains(text(),'Подписанты:')]";
                static String signers_field = "//div[@class='document-metadata']//*[contains(text(),'Подписанты:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String signers_button = "//div[@class='document-metadata']//*[contains(text(),'Подписанты:')]//ancestor::div[contains(@class,'control')]//button";
                static String signing_date_label = "//div[@class='document-metadata']//*[contains(text(),'Дата подписания:')]";
                static String signing_date_field = "//div[@class='document-metadata']//*[contains(text(),'Дата подписания:')]//ancestor::div[contains(@class,'control')]//input[@type='text']";
                static String response_to_label = "//div[@class='document-metadata']//*[contains(text(),'В ответ на:')]";
                static String response_to_field = "//div[@class='document-metadata']//*[contains(text(),'В ответ на:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String response_to_button = "//div[@class='document-metadata']//*[contains(text(),'В ответ на:')]//ancestor::div[contains(@class,'control')]//button";
                static String sheets_number_label = "//div[@class='document-metadata']//*[contains(text(),'Количество листов:')]";
                static String sheets_number_field = "//div[@class='document-metadata']//*[contains(text(),'Количество листов:')]//ancestor::div[contains(@class,'control')]//input";
                static String subject_label = "//div[@class='document-metadata']//*[contains(text(),'Тематика:')]";
                static String subject_field = "//div[@class='document-metadata']//*[contains(text(),'Тематика:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String subject_button = "//div[@class='document-metadata']//*[contains(text(),'Тематика:')]//ancestor::div[contains(@class,'control')]//button";
                static String file_register_label = "//div[@class='document-metadata']//*[contains(text(),'Номер дела:')]";
                static String file_register_field = "//div[@class='document-metadata']//*[contains(text(),'Номер дела:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String file_register_button = "//div[@class='document-metadata']//*[contains(text(),'Номер дела:')]//ancestor::div[contains(@class,'control')]//button";
                static String note_label = "//div[@class='document-metadata']//*[contains(text(),'Примечание:')]";
                static String note_field = "//div[@class='document-metadata']//*[contains(text(),'Примечание:')]//ancestor::div[contains(@class,'control')]//textarea";
            }
            static class Outgoingdocument {
                static String attachments_label = "//div[@class='document-metadata']//*[contains(text(),'Вложения:')]";
                /*
                утт типа пути до нопок и полей составителя и исполнителя
                но ограничусь только названиями атрибутов, потому что пока что не хочу их заполнять
                */
                static String author_label = "//div[@class='document-metadata']//*[contains(text(),'Составитель:')]";
                static String executor_label = "//div[@class='document-metadata']//*[contains(text(),'Исполнитель:')]";
                static String title_label = "//div[@class='document-metadata']//*[contains(text(),'Заголовок:')]";
                static String title_field = "//div[@class='document-metadata']//*[contains(text(),'Заголовок:')]//ancestor::div[contains(@class,'control')]//input";
                static String type_label = "//div[@class='document-metadata']//*[contains(text(),'Вид документа:')]";
                static String type_field = "//div[@class='document-metadata']//*[contains(text(),'Вид документа:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String type_button = "//div[@class='document-metadata']//*[contains(text(),'Вид документа:')]//ancestor::div[contains(@class,'control')]//button";
                static String delivery_method_label = "//div[@class='document-metadata']//*[contains(text(),'Способ доставки:')]";
                static String delivery_method_field = "//div[@class='document-metadata']//*[contains(text(),'Способ доставки:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String delivery_method_button = "//div[@class='document-metadata']//*[contains(text(),'Способ доставки:')]//ancestor::div[contains(@class,'control')]//button";
                static String sender_label = "//div[@class='document-metadata']//*[contains(text(),'Корреспондент:')]";
                static String sender_field = "//div[@class='document-metadata']//*[contains(text(),'Корреспондент:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String sender_button = "//div[@class='document-metadata']//*[contains(text(),'Корреспондент:')]//ancestor::div[contains(@class,'control')]//button";
                static String addressee_label = "//div[@class='document-metadata']//*[contains(text(),'Адресат корреспондента:')]";
                static String addressee_field = "//div[@class='document-metadata']//*[contains(text(),'Адресат корреспондента:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String addressee_button = "//div[@class='document-metadata']//*[contains(text(),'Адресат корреспондента:')]//ancestor::div[contains(@class,'control')]//button";
                static String summarycontent_label = "//div[@class='document-metadata']//*[contains(text(),'Содержание:')]";
                static String summarycontent_iframe = "//iframe[contains(@id,'summaryContent')]";
                static String summarycontent_field = "//body[@id='tinymce']";
                static String signedbypaper_label = "//div[@class='document-metadata']//*[contains(text(),'Подписано на бумажном носителе')]";
                static String signedbypaper_checkbox = "//div[@class='document-metadata']//*[contains(text(),'Подписано на бумажном носителе')]//ancestor::div[contains(@class,'control')]//input[@type='checkbox']";
                static String signers_label = "//div[@class='document-metadata']//*[contains(text(),'Подписанты:')]";
                static String signers_field = "//div[@class='document-metadata']//*[contains(text(),'Подписанты:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String signers_button = "//div[@class='document-metadata']//*[contains(text(),'Подписанты:')]//ancestor::div[contains(@class,'control')]//button";
                static String signing_date_label = "//div[@class='document-metadata']//*[contains(text(),'Дата подписания:')]";
                static String signing_date_field = "//div[@class='document-metadata']//*[contains(text(),'Дата подписания:')]//ancestor::div[contains(@class,'control')]//input[@type='text']";
                static String response_to_label = "//div[@class='document-metadata']//*[contains(text(),'В ответ на:')]";
                static String response_to_field = "//div[@class='document-metadata']//*[contains(text(),'В ответ на:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String response_to_button = "//div[@class='document-metadata']//*[contains(text(),'В ответ на:')]//ancestor::div[contains(@class,'control')]//button";
                static String sheets_number_label = "//div[@class='document-metadata']//*[contains(text(),'Количество листов:')]";
                static String sheets_number_field = "//div[@class='document-metadata']//*[contains(text(),'Количество листов:')]//ancestor::div[contains(@class,'control')]//input";
                static String subject_label = "//div[@class='document-metadata']//*[contains(text(),'Тематика:')]";
                static String subject_field = "//div[@class='document-metadata']//*[contains(text(),'Тематика:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String subject_button = "//div[@class='document-metadata']//*[contains(text(),'Тематика:')]//ancestor::div[contains(@class,'control')]//button";
                static String file_register_label = "//div[@class='document-metadata']//*[contains(text(),'Номер дела:')]";
                static String file_register_field = "//div[@class='document-metadata']//*[contains(text(),'Номер дела:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String file_register_button = "//div[@class='document-metadata']//*[contains(text(),'Номер дела:')]//ancestor::div[contains(@class,'control')]//button";
                static String note_label = "//div[@class='document-metadata']//*[contains(text(),'Примечание:')]";
                static String note_field = "//div[@class='document-metadata']//*[contains(text(),'Примечание:')]//ancestor::div[contains(@class,'control')]//textarea";
                static String finishing_label = "//div[@class='document-metadata']//*[contains(text(),'Завершающий')]";
                static String finishing_field = "//div[@class='document-metadata']//*[contains(text(),'Завершающий')]//ancestor::div[contains(@class,'control')]//input[@type='checkbox']";
            }
            static class Nddocument {
                static String attachments_label = "//div[@class='document-metadata']//*[contains(text(),'Вложения:')]";
                /*
                утт типа пути до нопок и полей составителя и исполнителя
                но ограничусь только названиями атрибутов, потому что пока что не хочу их заполнять
                */
                static String author_label = "//div[@class='document-metadata']//*[contains(text(),'Составитель:')]";
                static String executor_label = "//div[@class='document-metadata']//*[contains(text(),'Исполнитель:')]";
                static String title_label = "//div[@class='document-metadata']//*[contains(text(),'Заголовок:')]";
                static String title_field = "//div[@class='document-metadata']//*[contains(text(),'Заголовок:')]//ancestor::div[contains(@class,'control')]//input";
                static String type_label = "//div[@class='document-metadata']//*[contains(text(),'Вид документа:')]";
                static String type_field = "//div[@class='document-metadata']//*[contains(text(),'Вид документа:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String type_button = "//div[@class='document-metadata']//*[contains(text(),'Вид документа:')]//ancestor::div[contains(@class,'control')]//button";
                static String summarycontent_label = "//div[@class='document-metadata']//*[contains(text(),'Содержание:')]";
                static String summarycontent_iframe = "//iframe[contains(@id,'summaryContent')]";
                static String summarycontent_field = "//body[@id='tinymce']";
                static String sheets_number_label = "//div[@class='document-metadata']//*[contains(text(),'Количество листов:')]";
                static String sheets_number_field = "//div[@class='document-metadata']//*[contains(text(),'Количество листов:')]//ancestor::div[contains(@class,'control')]//input";
                static String signedbypaper_label = "//div[@class='document-metadata']//*[contains(text(),'Подписано на бумажном носителе')]";
                static String signedbypaper_checkbox = "//div[@class='document-metadata']//*[contains(text(),'Подписано на бумажном носителе')]//ancestor::div[contains(@class,'control')]//input[@type='checkbox']";
                static String signers_label = "//div[@class='document-metadata']//*[contains(text(),'Подписанты:')]";
                static String signers_field = "//div[@class='document-metadata']//*[contains(text(),'Подписанты:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String signers_button = "//div[@class='document-metadata']//*[contains(text(),'Подписанты:')]//ancestor::div[contains(@class,'control')]//button";
                static String signing_date_label = "//div[@class='document-metadata']//*[contains(text(),'Дата подписания:')]";
                static String signing_date_field = "//div[@class='document-metadata']//*[contains(text(),'Дата подписания:')]//ancestor::div[contains(@class,'control')]//input[@type='text']";
                static String file_register_label = "//div[@class='document-metadata']//*[contains(text(),'Номер дела:')]";
                static String file_register_field = "//div[@class='document-metadata']//*[contains(text(),'Номер дела:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String file_register_button = "//div[@class='document-metadata']//*[contains(text(),'Номер дела:')]//ancestor::div[contains(@class,'control')]//button";
                static String note_label = "//div[@class='document-metadata']//*[contains(text(),'Примечание:')]";
                static String note_field = "//div[@class='document-metadata']//*[contains(text(),'Примечание:')]//ancestor::div[contains(@class,'control')]//textarea";
                static String subject_label = "//div[@class='document-metadata']//*[contains(text(),'Тематика:')]";
                static String subject_field = "//div[@class='document-metadata']//*[contains(text(),'Тематика:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String subject_button = "//div[@class='document-metadata']//*[contains(text(),'Тематика:')]//ancestor::div[contains(@class,'control')]//button";
                static String organizationunit_label = "//div[@class='document-metadata']//*[contains(text(),'Подразделения:')]";
                static String organizationunit_field = "//div[@class='document-metadata']//*[contains(text(),'Подразделения:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String organizationunit_button = "//div[@class='document-metadata']//*[contains(text(),'Подразделения:')]//ancestor::div[contains(@class,'control')]//button";
                static String unlimited_label = "//div[@class='document-metadata']//*[contains(text(),'Бессрочный')]";
                static String unlimited_checkbox = "//div[@class='document-metadata']//*[contains(text(),'Бессрочный')]//ancestor::div[contains(@class,'control')]//input[@type='checkbox']";
                static String daterange_label = "//div[@class='document-metadata']//*[contains(text(),'Период действия:')]";
                static String daterangestart_field = "//div[@class='document-metadata']//*[contains(text(),'Период действия:')]//ancestor::div[contains(@class,'daterange')]//div[contains(@class,'start')]//div[contains(@class,'control')]//input[@type='text']";
                static String daterangeend_field = "//div[@class='document-metadata']//*[contains(text(),'Период действия:')]//ancestor::div[contains(@class,'daterange')]//div[contains(@class,'end')]//div[contains(@class,'control')]//input[@type='text']";
            }
            static class Orddocument {
                static String attachments_label = "//div[@class='document-metadata']//*[contains(text(),'Вложения:')]";
                /*
                утт типа пути до нопок и полей составителя и исполнителя
                но ограничусь только названиями атрибутов, потому что пока что не хочу их заполнять
                */
                static String author_label = "//div[@class='document-metadata']//*[contains(text(),'Составитель:')]";
                static String executor_label = "//div[@class='document-metadata']//*[contains(text(),'Исполнитель:')]";
                static String type_label = "//div[@class='document-metadata']//*[contains(text(),'Вид документа:')]";
                static String type_field = "//div[@class='document-metadata']//*[contains(text(),'Вид документа:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String type_button = "//div[@class='document-metadata']//*[contains(text(),'Вид документа:')]//ancestor::div[contains(@class,'control')]//button";
                static String title_label = "//div[@class='document-metadata']//*[contains(text(),'Заголовок:')]";
                static String title_field = "//div[@class='document-metadata']//*[contains(text(),'Заголовок:')]//ancestor::div[contains(@class,'control')]//input";
                static String executiondate_label = "//div[@class='document-metadata']//*[contains(text(),'Срок исполнения:')]";
                static String executiondate_field = "//div[@class='document-metadata']//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'control')]//input[@type='text']";
                static String summarycontent_label = "//div[@class='document-metadata']//*[contains(text(),'Содержание:')]";
                static String summarycontent_iframe = "//iframe[contains(@id,'summaryContent')]";
                static String summarycontent_field = "//body[@id='tinymce']";
                static String signedbypaper_label = "//div[@class='document-metadata']//*[contains(text(),'Подписано на бумажном носителе')]";
                static String signedbypaper_checkbox = "//div[@class='document-metadata']//*[contains(text(),'Подписано на бумажном носителе')]//ancestor::div[contains(@class,'control')]//input[@type='checkbox']";
                static String signers_label = "//div[@class='document-metadata']//*[contains(text(),'Подписанты:')]";
                static String signers_field = "//div[@class='document-metadata']//*[contains(text(),'Подписанты:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String signers_button = "//div[@class='document-metadata']//*[contains(text(),'Подписанты:')]//ancestor::div[contains(@class,'control')]//button";
                static String signing_date_label = "//div[@class='document-metadata']//*[contains(text(),'Дата подписания:')]";
                static String signing_date_field = "//div[@class='document-metadata']//*[contains(text(),'Дата подписания:')]//ancestor::div[contains(@class,'control')]//input[@type='text']";
                static String controller_label = "//div[@class='document-metadata']//*[contains(text(),'Контролёр:')]";
                static String controller_field = "//div[@class='document-metadata']//*[contains(text(),'Контролёр:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String controller_button = "//div[@class='document-metadata']//*[contains(text(),'Контролёр:')]//ancestor::div[contains(@class,'control')]//button";
                static String confirmcompletion_label = "//div[@class='document-metadata']//*[contains(text(),'Подтверждать завершение работы по документу')]";
                static String confirmcompletion_checkbox = "//div[@class='document-metadata']//*[contains(text(),'Подтверждать завершение работы по документу')]//ancestor::div[contains(@class,'control')]//input[@type='checkbox']";
                static String note_label = "//div[@class='document-metadata']//*[contains(text(),'Примечание:')]";
                static String note_field = "//div[@class='document-metadata']//*[contains(text(),'Примечание:')]//ancestor::div[contains(@class,'control')]//textarea";
                static String sheets_number_label = "//div[@class='document-metadata']//*[contains(text(),'Количество листов:')]";
                static String sheets_number_field = "//div[@class='document-metadata']//*[contains(text(),'Количество листов:')]//ancestor::div[contains(@class,'control')]//input";
                static String subject_label = "//div[@class='document-metadata']//*[contains(text(),'Тематика:')]";
                static String subject_field = "//div[@class='document-metadata']//*[contains(text(),'Тематика:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String subject_button = "//div[@class='document-metadata']//*[contains(text(),'Тематика:')]//ancestor::div[contains(@class,'control')]//button";
                static String file_register_label = "//div[@class='document-metadata']//*[contains(text(),'Номер дела:')]";
                static String file_register_field = "//div[@class='document-metadata']//*[contains(text(),'Номер дела:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String file_register_button = "//div[@class='document-metadata']//*[contains(text(),'Номер дела:')]//ancestor::div[contains(@class,'control')]//button";
                static String canceled_label = "//div[@class='document-metadata']//*[contains(text(),'Отменяемые документы:')]";
                static String canceled_field = "//div[@class='document-metadata']//*[contains(text(),'Отменяемые документы:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String canceled_button = "//div[@class='document-metadata']//*[contains(text(),'Отменяемые документы:')]//ancestor::div[contains(@class,'control')]//button";
                static String accepted_label = "//div[@class='document-metadata']//*[contains(text(),'Принимаемые документы:')]";
                static String accepted_field = "//div[@class='document-metadata']//*[contains(text(),'Принимаемые документы:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String accepted_button = "//div[@class='document-metadata']//*[contains(text(),'Принимаемые документы:')]//ancestor::div[contains(@class,'control')]//button";
                static String items_label = "//div[@class='document-metadata']//*[contains(text(),'Пункты:')]";
                static String items_button = "//div[@class='document-metadata']//*[contains(text(),'Пункты:')]//ancestor::div[contains(@class,'control')]//button[text()='Создание']";
                static class Items{
                    static String title_label = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Заголовок:')]";
                    static String title_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Заголовок:')]//ancestor::div[contains(@class,'control')]//input";
                    static String author_label = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Автор:')]";
                    static String author_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Автор:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                    static String author_button = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Автор:')]//ancestor::div[contains(@class,'control')]//button";
                    static String summary_label = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Содержание:')]";
                    static String summary_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Содержание:')]//ancestor::div[contains(@class,'control')]//textarea";
                    static String executor_label = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Исполнитель:')]";
                    static String executor_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Исполнитель:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                    static String executor_button = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Исполнитель:')]//ancestor::div[contains(@class,'control')]//button";
                    static String coexecutors_label = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Соисполнители:')]";
                    static String coexecutors_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Соисполнители:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                    static String coexecutors_button = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Соисполнители:')]//ancestor::div[contains(@class,'control')]//button";
                    static String limitationdate_label = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Срок исполнения:')]";
                    static String limitationdate_radiodays = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//input[@type='radio' and @value='DAYS']";
                    static String limitationdate_radiodays_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//input[contains(@id,'limitation-date') and @type='text']";
                    static String limitationdate_radiodays_select = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//select";
                    static String limitationdate_radiodate = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//input[@type='radio' and @value='DATE']";
                    static String limitationdate_radiodate_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//input[contains(@id,'execution-date') and @type='text']";
                    static String limitationdate_radiolimitless = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//input[@type='radio' and @value='LIMITLESS']";
                    static String needreport_label = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Требуется отчет')]";
                    static String needreport_checkbox = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Требуется отчет')]//ancestor::div[contains(@class,'control')]//input[@type='checkbox']";
                    static String controller_label = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Контролер:')]";
                    static String controller_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Контролер:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                    static String controller_button = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Контролер:')]//ancestor::div[contains(@class,'control')]//button";
                    static String subject_label = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Тематика:')]";
                    static String subject_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Тематика:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                    static String subject_button = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Тематика:')]//ancestor::div[contains(@class,'control')]//button";
                    static String saveandcreate_button = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//button[text()='Сохранить и создать новый']";
                    static String saveandclose_button = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//button[text()='Сохранить и закрыть']";
                    static String close_button = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//button[text()='Закрыть']";
                }
            }
            static class Erranddocument {
                static String type_label = "//div[@class='document-metadata']//*[contains(text(),'Тип поручения:')]";
                static String type_field = "//div[@class='document-metadata']//*[contains(text(),'Тип поручения:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String type_button = "//div[@class='document-metadata']//*[contains(text(),'Тип поручения:')]//ancestor::div[contains(@class,'control')]//button";
                static String title_label = "//div[@class='document-metadata']//*[contains(text(),'Заголовок:')]";
                static String title_field = "//div[@class='document-metadata']//*[contains(text(),'Заголовок:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String title_button = "//div[@class='document-metadata']//*[contains(text(),'Заголовок:')]//ancestor::div[contains(@class,'control')]//button";
                static String content_label = "//div[@class='document-metadata']//*[contains(text(),'Текст поручения:')]";
                static String content_field = "//div[@class='document-metadata']//*[contains(text(),'Текст поручения:')]//ancestor::div[contains(@class,'control')]//textarea";
                static String executor_label = "//div[@class='document-metadata']//*[contains(text(),'Исполнитель:')]";
                static String executor_field = "//div[@class='document-metadata']//*[contains(text(),'Исполнитель:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String executor_button = "//div[@class='document-metadata']//*[contains(text(),'Исполнитель:')]//ancestor::div[contains(@class,'control')]//button";
                static String coexecutor_label = "//div[@class='document-metadata']//*[contains(text(),'Соисполнители:')]";
                static String coexecutor_field = "//div[@class='document-metadata']//*[contains(text(),'Соисполнители:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String coexecutor_button = "//div[@class='document-metadata']//*[contains(text(),'Соисполнители:')]//ancestor::div[contains(@class,'control')]//button";
                static String limitationdate_label = "//div[@class='document-metadata']//*[contains(text(),'Срок исполнения:')]";
                static String limitationdate_radiodays = "//div[@class='document-metadata']//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//input[@type='radio' and @value='DAYS']";
                static String limitationdate_radiodays_field = "//div[@class='document-metadata']//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//input[contains(@id,'limitation-date') and @type='text']";
                static String limitationdate_radiodays_select = "//div[@class='document-metadata']//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//select";
                static String limitationdate_radiodate = "//div[@class='document-metadata']//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//input[@type='radio' and @value='DATE']";
                static String limitationdate_radiodate_field = "//div[@class='document-metadata']//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//input[contains(@id,'date-cntrl-date') and @type='text']";
                static String limitationdate_radiolimitless = "//div[@class='document-metadata']//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//input[@type='radio' and @value='LIMITLESS']";
                static String periodically_label = "//div[@class='document-metadata']//*[contains(text(),'Направлять периодически')]";
                static String periodically_checkbox = "//div[@class='document-metadata']//*[contains(text(),'Направлять периодически')]//ancestor::div[contains(@class,'control')]//input[@type='checkbox']";
                static String reiterationrule_label = "//div[@class='document-metadata']//*[contains(text(),'Повторять')]";
                static String reiterationrule_button = "//div[@class='document-metadata']//*[contains(text(),'Повторять')]//ancestor::div[contains(@class,'control')]//div[contains(@class,'value')]//a";
                static String reiterationruletype_select = "//select[contains(@id,'reiteration-rule-type')]";
                static String reiterationrulesave_button = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//button[text()='Сохранить']";
                static String periodstart_label = "//div[@class='document-metadata']//*[contains(text(),'Начало повторений:')]";
                static String periodstart_field = "//div[@class='document-metadata']//*[contains(text(),'Начало повторений:')]//ancestor::div[contains(@class,'control')]//input[@type='text']";
                static String periodend_label = "//div[@class='document-metadata']//*[contains(text(),'Окончание повторений:')]";
                static String periodend_radiodate = "//div[@class='document-metadata']//*[contains(text(),'Окончание повторений:')]//ancestor::div[contains(@class,'errands-validity-set')]//input[@type='radio' and @value='DATERANGE']";
                static String periodend_radiodate_field = "//div[@class='document-metadata']//*[contains(text(),'Окончание повторений:')]//ancestor::div[contains(@class,'errands-validity-set')]//input[contains(@id,'period-end') and @type='text']";
                static String periodend_radioendless = "//div[@class='document-metadata']//*[contains(text(),'Окончание повторений:')]//ancestor::div[contains(@class,'errands-validity-set')]//input[@type='radio' and @value='ENDLESS']";
                static String periodend_radioduring = "//div[@class='document-metadata']//*[contains(text(),'Окончание повторений:')]//ancestor::div[contains(@class,'errands-validity-set')]//input[@type='radio' and @value='DURING']";
                static String periodend_radioduring_field = "//div[@class='document-metadata']//*[contains(text(),'Окончание повторений:')]//ancestor::div[contains(@class,'errands-validity-set')]//input[contains(@id,'period-during') and @type='text']";
                static String periodend_radioduring_select = "//div[@class='document-metadata']//*[contains(text(),'Окончание повторений:')]//ancestor::div[contains(@class,'errands-validity-set')]//select";
                static String periodend_radiorepeatcount = "//div[@class='document-metadata']//*[contains(text(),'Окончание повторений:')]//ancestor::div[contains(@class,'errands-validity-set')]//input[@type='radio' and @value='REPEAT_COUNT']";
                static String periodend_radiorepeatcount_field = "//div[@class='document-metadata']//*[contains(text(),'Окончание повторений:')]//ancestor::div[contains(@class,'errands-validity-set')]//input[contains(@id,'reiteration-count') and @type='text']";
                static String controller_label = "//div[@class='document-metadata']//*[contains(text(),'Контролер:')]";
                static String controller_field = "//div[@class='document-metadata']//*[contains(text(),'Контролер:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String controller_button = "//div[@class='document-metadata']//*[contains(text(),'Контролер:')]//ancestor::div[contains(@class,'control')]//button";
                static String needreport_label = "//div[@class='document-metadata']//*[contains(text(),'Требуется отчет')]";
                static String needreport_checkbox = "//div[@class='document-metadata']//*[contains(text(),'Требуется отчет')]//ancestor::div[contains(@class,'control')]//input[@type='checkbox']";
                static String reportrecipient_label = "//div[@class='document-metadata']//*[contains(text(),'Получатель отчета:')]";
                static String reportrecipient_select = "//div[@class='document-metadata']//*[contains(text(),'Получатель отчета:')]//ancestor::div[contains(@id,'report-recipient')]//select";
                static String important_label = "//div[@class='document-metadata']//*[contains(text(),'Важное')]";
                static String important_checkbox = "//div[@class='document-metadata']//*[contains(text(),'Важное')]//ancestor::div[contains(@class,'control')]//input[@type='checkbox']";
                static String subject_label = "//div[@class='document-metadata']//*[contains(text(),'Тематика:')]";
                static String subject_field = "//div[@class='document-metadata']//*[contains(text(),'Тематика:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String subject_button = "//div[@class='document-metadata']//*[contains(text(),'Тематика:')]//ancestor::div[contains(@class,'control')]//button";
                static String attachments_label = "//div[@class='document-metadata']//*[contains(text(),'Вложения:')]";
                static String expand_button = "//div[@class='document-metadata']//button[text()='Расширенное поручение']";
                static String save_button = "//div[@class='document-metadata']//button[text()='Сохранить черновик']";
                static String default_button = "//div[@class='document-metadata']//button[text()='Направить']";
            }
            static class Resolutionsdocument {
                static String approvedoutsidesystem_label = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Утверждено вне системы')]";
                static String approvedoutsidesystem_checkbox = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Утверждено вне системы')]//ancestor::div[contains(@class,'control')]//input[@type='checkbox']";
                static String subject_label = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Тематика:')]";
                static String subject_field = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Тематика:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String subject_button = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Тематика:')]//ancestor::div[contains(@class,'control')]//button";
                static String author_label = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Автор:')]";
                static String author_field = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Автор:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String author_button = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Автор:')]//ancestor::div[contains(@class,'control')]//button";
                static String controller_label = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Контролер:')]";
                static String controller_field = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Контролер:')]//ancestor::div[contains(@class,'control')]//div[@class='cropped-item']";
                static String controller_button = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Контролер:')]//ancestor::div[contains(@class,'control')]//button";
                static String closers_label = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Завершающий:')]";
                static String closers_select = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Завершающий:')]//ancestor::div[contains(@class,'control')]//select";
                static String isoncontrol_label = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Контроль')]";
                static String isoncontrol_checkbox = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Контроль')]//ancestor::div[contains(@class,'control')]//input[@type='checkbox']";
                static String limitationdate_label = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Срок исполнения:')]";
                static String limitationdate_radiodays = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//input[@type='radio' and @value='DAYS']";
                static String limitationdate_radiodays_field = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//input[contains(@id,'limitation-date') and @type='text']";
                static String limitationdate_radiodays_select = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//select";
                static String limitationdate_radiodate = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//input[@type='radio' and @value='DATE']";
                static String limitationdate_radiodate_field = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//input[contains(@id,'date-cntrl-date') and @type='text']";
                static String limitationdate_radiolimitless = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//input[@type='radio' and @value='LIMITLESS']";
                static class Errands{
                    static String type_label = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Тип поручения:')]";
                    static String type_field = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Тип поручения:')]//ancestor::div[contains(@class,'control editmode')]//div[@class='cropped-item']";
                    static String type_button = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Тип поручения:')]//ancestor::div[contains(@class,'control editmode')]//button";
                    static String title_label = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Заголовок:')]";
                    static String title_field = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Заголовок:')]//ancestor::div[contains(@class,'text editmode')]//div[@class='cropped-item']";
                    static String title_button = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Заголовок:')]//ancestor::div[contains(@class,'text editmode')]//button";
                    static String executor_label = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Исполнитель:')]";
                    static String executor_field = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Исполнитель:')]//ancestor::div[contains(@class,'control editmode')]//div[@class='cropped-item']";
                    static String executor_button = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Исполнитель:')]//ancestor::div[contains(@class,'control editmode')]//button";
                    static String coexecutor_label = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Соисполнители:')]";
                    static String coexecutor_field = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Соисполнители:')]//ancestor::div[contains(@class,'control editmode')]//div[@class='cropped-item']";
                    static String coexecutor_button = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Соисполнители:')]//ancestor::div[contains(@class,'control editmode')]//button";
                    static String controller_label = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Контролер:')]";
                    static String controller_field = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Контролер:')]//ancestor::div[contains(@class,'control editmode')]//div[@class='cropped-item']";
                    static String controller_button = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Контролер:')]//ancestor::div[contains(@class,'control editmode')]//button";
                    static String limitationdate_label = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Срок исполнения:')]";
                    static String limitationdate_radiodays = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//input[@type='radio' and @value='DAYS']";
                    static String limitationdate_radiodays_field = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//input[contains(@id,'limitation-date') and @type='text']";
                    static String limitationdate_radiodays_select = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//select";
                    static String limitationdate_radiodate = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//input[@type='radio' and @value='DATE']";
                    static String limitationdate_radiodate_field = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//input[contains(@id,'date-cntrl-date') and @type='text']";
                    static String limitationdate_radiolimitless = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//input[@type='radio' and @value='LIMITLESS']";
                    static String needreport_label = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Требуется отчет')]";
                    static String needreport_checkbox = "//div[contains(@class,'document-metadata')]//ul[@class='multi-form-documents-list']//*[contains(text(),'Требуется отчет')]//ancestor::div[contains(@class,'control editmode')]//input[@type='checkbox']";
                    static String reportrecipient_label = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Получатель отчета:')]";
                    static String reportrecipient_select = "//div[contains(@class,'document-metadata')]//*[contains(text(),'Получатель отчета:')]//ancestor::div[contains(@class,'control select')]//select";
                }

                static String adderrand_button = "//div[contains(@class,'document-metadata')]//button[text()='Добавить']";
                static String save_button = "//div[contains(@class,'document-metadata')]//button[text()='Сохранить черновик']";
                static String default_button = "//div[contains(@class,'document-metadata')]//button[text()='Направить']";
            }
        }
        static class Viewform{
            static String zavershit_label = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Причина:')]";
            static String zavershit_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//textarea";
            static String vernut_label = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//*[contains(text(),'Причина:')]";
            static String vernut_field = "//div[contains(@class,'container') and contains (@style,'visibility: visible')]//textarea";
            static class Rightblock{
                static String history_open = "//div[contains(@class,'widget')]//h2[contains(.,'История')]//*[contains(@title,'Развернуть')]";
                static String history_header = "//div[contains(@class,'widget')]//h2[contains(.,'История')]";
                static String errands_open = "//div[contains(@class,'widget')]//h2[contains(.,'Поручения')]//*[contains(@title,'Развернуть')]";
                static String errands_header = "//div[contains(@class,'widget')]//h2[contains(.,'Поручения')]";
            }
            static class Incomingdocument{
                static String type_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Вид документа:')]";
                static String type_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'type')]//div[contains(@class,'value')]";
                static String title_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Заголовок:')]";
                static String title_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'title')]//div[contains(@class,'value')]";
                static String summary_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Содержание:')]";
                static String summary_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'summary')]//div[contains(@class,'value')]";
                static String subject_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Тематика:')]";
                static String subject_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'subject')]//div[contains(@class,'value')]";
                static String status_field = "//div[contains(@id,'status')]//*[contains(@class,'value')]";
                static String sheets_number_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Количество листов:')]";
                static String sheets_number_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'sheets-number')]//div[contains(@class,'value')]";
                static String sender_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Корреспондент:')]";
                static String sender_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'sender')]//div[contains(@class,'value')]";
                static String regnum_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Номер:')]";
                static String regnum_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'regnum')]//div[contains(@class,'value')]";
                static String reg_data_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Дата регистрации:')]";
                static String reg_data_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'reg-data')]//div[contains(@class,'value')]";
                static String recipient_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Получатель:')]";
                static String recipient_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'recipient')]//div[contains(@class,'value')]";
                static String outgoing_number_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Исходящий номер:')]";
                static String outgoing_number_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'outgoing-number')]//div[contains(@class,'value')]";
                static String outgoing_date_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Исходящий от:')]";
                static String outgoing_date_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'outgoing-date')]//div[contains(@class,'value')]";
                static String note_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Примечание:')]";
                static String note_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'note')]//div[contains(@class,'value')]";
                static String is_on_control_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'На контроле:')]";
                static String is_on_control_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'is-on-control')]//div[contains(@class,'value')]";
                static String is_not_registered_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Нерегистрируемый:')]";
                static String is_not_registered_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'is-not-registered')]//div[contains(@class,'value')]";
                static String file_register_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Номер дела:')]";
                static String file_register_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'file-register')]//div[contains(@class,'value')]";
                static String execution_date_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Срок исполнения:')]";
                static String execution_date_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'execution-date')]//div[contains(@class,'value')]";
                static String delivery_method_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Способ доставки:')]";
                static String delivery_method_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'delivery-method')]//div[contains(@class,'value')]";
                static String addressee_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Представитель корреспондента:')]";
                static String addressee_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'addressee')]//div[contains(@class,'value')]";
            }
            static class Internaldocument{
                static String regnum_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Номер:')]";
                static String regnum_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'regnum')]//div[contains(@class,'value')]";
                static String reg_data_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Дата регистрации:')]";
                static String reg_data_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'reg-data')]//div[contains(@class,'value')]";
                static String author_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Составитель:')]";
                static String author_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'author')]//div[contains(@class,'value')]";
                static String executor_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Исполнитель:')]";
                static String executor_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'executor')]//div[contains(@class,'value')]";
                static String title_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Заголовок:')]";
                static String title_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'title')]//div[contains(@class,'value')]";
                static String type_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Вид документа:')]";
                static String type_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'type')]//div[contains(@class,'value')]";
                static String response_date_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Срок ответа:')]";
                static String response_date_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'response-date')]//div[contains(@class,'value')]";
                static String recipient_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Получатель:')]";
                static String recipient_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'recipient')]//div[contains(@class,'value')]";
                static String summary_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Содержание:')]";
                static String summary_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'summary')]//div[contains(@class,'value')]";
                static String responseto_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'В ответ на:')]";
                static String responseto_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'response-to')]//div[contains(@class,'value')]";
                static String sheets_number_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Количество листов:')]";
                static String sheets_number_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'sheets-number')]//div[contains(@class,'value')]";
                static String subject_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Тематика:')]";
                static String subject_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'subject')]//div[contains(@class,'value')]";
                static String file_register_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Номер дела:')]";
                static String file_register_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'file-register')]//div[contains(@class,'value')]";
                static String note_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Примечание:')]";
                static String note_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'note')]//div[contains(@class,'value')]";
                static String regnumproject_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Номер проекта:')]";
                static String regnumproject_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'reg-project-data-number')]//div[contains(@class,'value')]";
                static String regproject_data_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Дата регистрации проекта:')]";
                static String regproject_data_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'reg-project-data-date')]//div[contains(@class,'value')]";
                static String signedonpaper_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Подписано на бумажном носителе:')]";
                static String signedonpaper_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'signed-on-paper')]//div[contains(@class,'value')]";
                static String signers_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Подписанты:')]";
                static String signers_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'signerEmployeeAssoc')]//div[contains(@class,'value')]";
                static String signed_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Подписан:')]";
                static String signed_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'isSigned')]//div[contains(@class,'value')]";
                static String signingDate_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Дата подписания:')]";
                static String signingDate_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'signingDate')]//div[contains(@class,'value')]";
                static String registrator_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Регистратор:')]";
                static String registrator_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'registrator')]//div[contains(@class,'value')]";
                static String status_field = "//div[contains(@id,'status')]//*[contains(@class,'value')]";
            }
            static class Outgoingdocument{
                static String regnum_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Номер:')]";
                static String regnum_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'regnum')]//div[contains(@class,'value')]";
                static String reg_data_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Дата регистрации:')]";
                static String reg_data_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'reg-data')]//div[contains(@class,'value')]";
                static String title_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Заголовок:')]";
                static String title_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'title')]//div[contains(@class,'value')]";
                static String type_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Вид документа:')]";
                static String type_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'type')]//div[contains(@class,'value')]";
                static String delivery_method_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Способ доставки:')]";
                static String delivery_method_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'delivery-method')]//div[contains(@class,'value')]";
                static String sender_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Корреспондент:')]";
                static String sender_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'outgoing_contractor')]//div[contains(@class,'value')]";
                static String addressee_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Адресат корреспондента:')]";
                static String addressee_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'outgoing_recipient')]//div[contains(@class,'value')]";
                static String summary_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Содержание:')]";
                static String summary_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'summary')]//div[contains(@class,'value')]";
                static String sheets_number_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Количество листов:')]";
                static String sheets_number_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'sheets-number')]//div[contains(@class,'value')]";
                static String subject_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Тематика:')]";
                static String subject_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'subject')]//div[contains(@class,'value')]";
                static String note_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Примечание:')]";
                static String note_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'note')]//div[contains(@class,'value')]";
                static String file_register_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Номер дела:')]";
                static String file_register_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'file-register')]//div[contains(@class,'value')]";
                static String author_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Составитель:')]";
                static String author_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'author')]//div[contains(@class,'value')]";
                static String executor_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Исполнитель:')]";
                static String executor_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'executor')]//div[contains(@class,'value')]";
                static String regnumproject_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Номер проекта:')]";
                static String regnumproject_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'reg-project-data-number')]//div[contains(@class,'value')]";
                static String regproject_data_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Дата регистрации проекта:')]";
                static String regproject_data_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'reg-project-data-date')]//div[contains(@class,'value')]";
                static String signedonpaper_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Подписано на бумажном носителе:')]";
                static String signedonpaper_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'signed-on-paper')]//div[contains(@class,'value')]";
                static String signers_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Подписанты:')]";
                static String signers_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'signerEmployeeAssoc')]//div[contains(@class,'value')]";
                static String signed_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Подписан:')]";
                static String signed_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'isSigned')]//div[contains(@class,'value')]";
                static String signingDate_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Дата подписания:')]";
                static String signingDate_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'signingDate')]//div[contains(@class,'value')]";
                static String registrator_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Регистратор:')]";
                static String registrator_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'registrator')]//div[contains(@class,'value')]";
                static String finishing_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Завершающий:')]";
                static String finishing_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'finishing')]//div[contains(@class,'value')]";

                static String status_field = "//div[contains(@id,'status')]//*[contains(@class,'value')]";
            }
            static class Nddocument{
                static String regnum_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Номер:')]";
                static String regnum_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'regnum')]//div[contains(@class,'value')]";
                static String reg_data_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Дата регистрации:')]";
                static String reg_data_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'reg-data')]//div[contains(@class,'value')]";
                static String title_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Заголовок:')]";
                static String title_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'title')]//div[contains(@class,'value')]";
                static String type_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Вид документа:')]";
                static String type_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'type')]//div[contains(@class,'value')]";
                static String summary_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Содержание:')]";
                static String summary_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'summary')]//div[contains(@class,'value')]";
                static String sheets_number_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Количество листов:')]";
                static String sheets_number_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'sheets-number')]//div[contains(@class,'value')]";
                static String signedonpaper_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Подписано на бумажном носителе:')]";
                static String signedonpaper_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'signed-on-paper')]//div[contains(@class,'value')]";
                static String signers_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Подписанты:')]";
                static String signers_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'signerEmployeeAssoc')]//div[contains(@class,'value')]";
                static String signingDate_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Дата подписания:')]";
                static String signingDate_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'signingDate')]//div[contains(@class,'value')]";
                static String signed_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Подписан:')]";
                static String signed_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'isSigned')]//div[contains(@class,'value')]";
                static String file_register_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Номер дела:')]";
                static String file_register_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'file-register')]//div[contains(@class,'value')]";
                static String note_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Примечание:')]";
                static String note_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'note')]//div[contains(@class,'value')]";
                static String subject_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Тематика:')]";
                static String subject_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'subject')]//div[contains(@class,'value')]";
                static String organizationunit_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Подразделения:')]";
                static String organizationunit_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'organization-unit')]//div[contains(@class,'value')]";
                static String unlimited_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Бессрочный:')]";
                static String unlimited_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'unlimited')]//div[contains(@class,'value')]";
                static String daterange_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Период действия:')]";
                static String daterangestart_field = "//div[contains(@class,'tab-common')]//*[contains(text(),'Период действия:')]//ancestor::div[contains(@class,'daterange')]//div[contains(@class,'start')]//div[contains(@class,'control')]//div[contains(@class,'value')]";
                static String daterangeend_field = "//div[contains(@class,'tab-common')]//*[contains(text(),'Период действия:')]//ancestor::div[contains(@class,'daterange')]//div[contains(@class,'end')]//div[contains(@class,'control')]//div[contains(@class,'value')]";
                static String author_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Составитель:')]";
                static String author_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'author')]//div[contains(@class,'value')]";
                static String executor_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Исполнитель:')]";
                static String executor_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'executor')]//div[contains(@class,'value')]";
                static String regnumproject_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Номер проекта:')]";
                static String regnumproject_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'reg-project-data-number')]//div[contains(@class,'value')]";
                static String regproject_data_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Дата регистрации проекта:')]";
                static String regproject_data_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'reg-project-data-date')]//div[contains(@class,'value')]";
                static String registrator_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Регистратор:')]";
                static String registrator_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'registrator')]//div[contains(@class,'value')]";

                static String status_field = "//div[contains(@id,'status')]//*[contains(@class,'value')]";
            }
            static class Orddocument{
                static String regnum_label = "//div[contains(@class,'common-set')]//*[contains(text(),'Номер:')]";
                static String regnum_field = "//div[contains(@class,'common-set')]//div[contains(@id,'regnum')]//div[contains(@class,'value')]";
                static String reg_data_label = "//div[contains(@class,'common-set')]//*[contains(text(),'Дата регистрации:')]";
                static String reg_data_field = "//div[contains(@class,'common-set')]//div[contains(@id,'reg-data')]//div[contains(@class,'value')]";
                static String type_label = "//div[contains(@class,'common-set')]//*[contains(text(),'Вид документа:')]";
                static String type_field = "//div[contains(@class,'common-set')]//div[contains(@id,'type')]//div[contains(@class,'value')]";
                static String title_label = "//div[contains(@class,'common-set')]//*[contains(text(),'Заголовок:')]";
                static String title_field = "//div[contains(@class,'common-set')]//div[contains(@id,'title')]//div[contains(@class,'value')]";
                static String executiondate_label = "//div[contains(@class,'common-set')]//*[contains(text(),'Срок исполнения:')]";
                static String executiondate_field = "//div[contains(@class,'common-set')]//div[contains(@id,'execution-date')]//div[contains(@class,'value')]";
                static String summary_label = "//div[contains(@class,'common-set')]//*[contains(text(),'Содержание:')]";
                static String summary_field = "//div[contains(@class,'common-set')]//div[contains(@id,'summary')]//div[contains(@class,'value')]";
                static String controller_label = "//div[contains(@class,'common-set')]//*[contains(text(),'Контролёр:')]";
                static String controller_field = "//div[contains(@class,'common-set')]//div[contains(@id,'controller')]//div[contains(@class,'value')]";
                static String note_label = "//div[contains(@class,'common-set')]//*[contains(text(),'Примечание:')]";
                static String note_field = "//div[contains(@class,'common-set')]//div[contains(@id,'note')]//div[contains(@class,'value')]";
                static String sheets_number_label = "//div[contains(@class,'common-set')]//*[contains(text(),'Количество листов:')]";
                static String sheets_number_field = "//div[contains(@class,'common-set')]//div[contains(@id,'sheets-number')]//div[contains(@class,'value')]";
                static String subject_label = "//div[contains(@class,'common-set')]//*[contains(text(),'Тематика:')]";
                static String subject_field = "//div[contains(@class,'common-set')]//div[contains(@id,'subject')]//div[contains(@class,'value')]";
                static String file_register_label = "//div[contains(@class,'common-set')]//*[contains(text(),'Номер дела:')]";
                static String file_register_field = "//div[contains(@class,'common-set')]//div[contains(@id,'file-register')]//div[contains(@class,'value')]";
                static String canceled_label = "//div[contains(@class,'common-set')]//*[contains(text(),'Отменяемые документы:')]";
                static String canceled_field = "//div[contains(@class,'common-set')]//div[contains(@id,'canceled')]//div[contains(@class,'value')]";
                static String accepted_label = "//div[contains(@class,'common-set')]//*[contains(text(),'Принимаемые документы:')]";
                static String accepted_field = "//div[contains(@class,'common-set')]//div[contains(@id,'accepted')]//div[contains(@class,'value')]";
                static String author_label = "//div[contains(@class,'common-set')]//*[contains(text(),'Составитель:')]";
                static String author_field = "//div[contains(@class,'common-set')]//div[contains(@id,'author')]//div[contains(@class,'value')]";
                static String executor_label = "//div[contains(@class,'common-set')]//*[contains(text(),'Исполнитель:')]";
                static String executor_field = "//div[contains(@class,'common-set')]//div[contains(@id,'executor')]//div[contains(@class,'value')]";
                static String regnumproject_label = "//div[contains(@class,'common-set')]//*[contains(text(),'Номер проекта:')]";
                static String regnumproject_field = "//div[contains(@class,'common-set')]//div[contains(@id,'reg-project-data-number')]//div[contains(@class,'value')]";
                static String regproject_data_label = "//div[contains(@class,'common-set')]//*[contains(text(),'Дата регистрации проекта:')]";
                static String regproject_data_field = "//div[contains(@class,'common-set')]//div[contains(@id,'reg-project-data-date')]//div[contains(@class,'value')]";
                static String signedonpaper_label = "//div[contains(@class,'common-set')]//*[contains(text(),'Подписано на бумажном носителе:')]";
                static String signedonpaper_field = "//div[contains(@class,'common-set')]//div[contains(@id,'signed-on-paper')]//div[contains(@class,'value')]";
                static String signers_label = "//div[contains(@class,'common-set')]//*[contains(text(),'Подписанты:')]";
                static String signers_field = "//div[contains(@class,'common-set')]//div[contains(@id,'signerEmployeeAssoc')]//div[contains(@class,'value')]";
                static String signed_label = "//div[contains(@class,'common-set')]//*[contains(text(),'Подписан:')]";
                static String signed_field = "//div[contains(@class,'common-set')]//div[contains(@id,'isSigned')]//div[contains(@class,'value')]";
                static String signingDate_label = "//div[contains(@class,'common-set')]//*[contains(text(),'Дата подписания:')]";
                static String signingDate_field = "//div[contains(@class,'common-set')]//div[contains(@id,'signingDate')]//div[contains(@class,'value')]";
                static String registrator_label = "//div[contains(@class,'common-set')]//*[contains(text(),'Регистратор:')]";
                static String registrator_field = "//div[contains(@class,'common-set')]//div[contains(@id,'registrator')]//div[contains(@class,'value')]";

                static String status_field = "//div[contains(@id,'status')]//*[contains(@class,'value')]";
            }
            static class Erranddocument{
                static String base_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Документ-основание:')]";
                static String base_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'base')]//div[contains(@class,'value')]";
                static String additional_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Основание:')]";
                static String additional_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'base')]//div[contains(@class,'value')]";
                static String initiator_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Автор:')]";
                static String initiator_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'initiator')]//div[contains(@class,'value')]";
                static String author_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Создатель:')]";
                static String author_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'author')]//div[contains(@class,'value')]";
                static String type_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Тип поручения:')]";
                static String type_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'type')]//div[contains(@class,'value')]";
                static String title_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Заголовок:')]";
                static String title_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'title')]//div[contains(@class,'value')]";
                static String content_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Текст поручения:')]";
                static String content_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'content')]//div[contains(@class,'value')]";
                static String executor_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Исполнитель:')]";
                static String executor_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'executor')]//div[contains(@class,'value')]";
                static String coexecutors_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Соисполнители:')]";
                static String coexecutors_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'coexecutors')]//div[contains(@class,'value')]";
                static String limitationdate_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Срок исполнения:')]";
                static String limitationdate_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'limitation-date')]//div[contains(@class,'value')]";
                static String autoclose_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Закрывает вышестоящее поручение:')]";
                static String autoclose_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'auto-close')]//div[contains(@class,'value')]";
                static String periodically_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Направлять периодически:')]";
                static String periodically_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'periodically')]//div[contains(@class,'value')]";
                static String controller_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Контролер:')]";
                static String controller_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'controller')]//div[contains(@class,'value')]";
                static String needreport_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Требуется отчет:')]";
                static String needreport_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'report-required')]//div[contains(@class,'value')]";
                static String reportrecipient_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Получатель отчета:')]";
                static String reportrecipient_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'report-recipient')]//div[contains(@class,'value')]";
                static String subject_label = "//div[contains(@class,'tab-common')]//*[contains(text(),'Тематика:')]";
                static String subject_field = "//div[contains(@class,'tab-common')]//div[contains(@id,'subject')]//div[contains(@class,'value')]";

                static String status_field = "//div[contains(@id,'status')]//*[contains(@class,'value')]";
            }
            static class Resolutionsdocument{
                static String approvedoutsidesystem_label = "//div[contains(@class,'metadata-form')]//*[contains(text(),'Утверждено вне системы:')]";
                static String approvedoutsidesystem_field = "//div[contains(@class,'metadata-form')]//div[contains(@id,'approved-outside-system')]//div[contains(@class,'value')]";
                static String basedocument_label = "//div[contains(@class,'metadata-form')]//*[contains(text(),'Документ-основание:')]";
                static String basedocument_field = "//div[contains(@class,'metadata-form')]//div[contains(@id,'base-document')]//div[contains(@class,'value')]";
                static String base_label = "//div[contains(@class,'metadata-form')]//*[contains(text(),'Основание:')]";
                static String base_field = "//div[contains(@class,'metadata-form')]//div[contains(@id,'base-assoc')]//div[contains(@class,'value')]";
                static String isoncontrol_label = "//div[contains(@class,'metadata-form')]//*[contains(text(),'Контроль:')]";
                static String isoncontrol_field = "//div[contains(@class,'metadata-form')]//div[contains(@id,'is-on-control')]//div[contains(@class,'value')]";
                static String subject_label = "//div[contains(@class,'metadata-form')]//*[contains(text(),'Тематика:')]";
                static String subject_field = "//div[contains(@class,'metadata-form')]//div[contains(@id,'subject')]//div[contains(@class,'value')]";
                static String resolutionsauthor_label = "//div[contains(@class,'metadata-form')]//*[contains(text(),'Автор:')]";
                static String resolutionsauthor_field = "//div[contains(@class,'metadata-form')]//div[contains(@id,'resolutions_author')]//div[contains(@class,'value')]";
                static String documentauthor_label = "//div[contains(@class,'metadata-form')]//*[contains(text(),'Создатель:')]";
                static String documentauthor_field = "//div[contains(@class,'metadata-form')]//div[contains(@id,'document_author')]//div[contains(@class,'value')]";
                static String closers_label = "//div[contains(@class,'metadata-form')]//*[contains(text(),'Завершающий:')]";
                static String closers_field = "//div[contains(@class,'metadata-form')]//div[contains(@id,'closers')]//div[contains(@class,'value')]";
                static String controller_label = "//div[contains(@class,'metadata-form')]//*[contains(text(),'Контролер:')]";
                static String controller_field = "//div[contains(@class,'metadata-form')]//div[contains(@id,'controller')]//div[contains(@class,'value')]";
                static String limitationdate_label = "//div[contains(@class,'metadata-form')]//*[contains(text(),'Срок исполнения:')]";
                static String limitationdate_date_radio = "//div[contains(@class,'metadata-form')]//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//input[@type='radio' and @value='DATE']";
                static String limitationdate_date_field = "//div[contains(@class,'metadata-form')]//div[contains(@class,'errands-limitation-date-set-date')]";
                static String limitationdate_limitless_radio = "//div[contains(@class,'metadata-form')]//*[contains(text(),'Срок исполнения:')]//ancestor::div[contains(@class,'limitation-date-set')]//input[@type='radio' and @value='LIMITLESS']";
                static String limitationdate_limitless_field = "//div[contains(@class,'metadata-form')]//div[contains(@class,'errands-limitation-date-set-limitless')]";

                static String status_field = "//div[contains(@id,'status')]//*[contains(@class,'value')]";
            }
        }
    }

    static class ARMSED{
        static class Createmenu{
            static String incomingdocument = "//ul//a[text()='Входящий документ']";
            static String internaldocument = "//ul//a[text()='Внутренний документ']";
            static String outgoingdocument = "//ul//a[text()='Исходящий документ']";
            static String nddocument = "//ul//a[text()='НД']";
            static String orddocument = "//ul//a[text()='ОРД']";
            static String erranddocument = "//ul//a[text()='Поручение']";
            static String resolutionsdocument = "//ul//a[text()='Резолюция']";
        }
        static String createButton = "//button[text()='Создать']";
    }
}
