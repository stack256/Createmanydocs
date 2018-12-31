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
    }

    static class Document{
        static String table_history = "//table//*[text()='Описание']";
        static String delete_check = "//div[contains(text(),'Документ был удален.')]";
        static String copy_document = "//button[@title='Копировать документ']";
        static String admin_function = "//button[@title='Административные функции']";
        static String admin_delete = "//li//a[text()='Удалить безвозвратно']";
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
        }
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
        }
    }

    static class ARMSED{
        static class Createmenu{
            static String incomingdocument = "//ul//a[text()='Входящий документ']";
            static String internaldocument = "//ul//a[text()='Внутренний документ']";
            static String outgoingdocument = "//ul//a[text()='Исходящий документ']";
        }
        static String createButton = "//button[text()='Создать']";
    }
}
