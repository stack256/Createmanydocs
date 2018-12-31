package Box;

import java.util.ArrayList;
import java.util.Random;

class Users {
    static class User{
        String login;
        String pass;
        ArrayList<String> roles;
        String famio;
        String full;
        String fio;

        User(){
            login = "";
            pass = "";
            roles = new ArrayList<>();
            famio = "";
            fio = "";
            full = "";
        }
    }



    static User getuserbyroles(String... roles){
        ArrayList<User> values = new ArrayList<>();
        User user = null;
        for (String role:roles)
            for (User val:users)
                if (val.roles.contains(role))
                    if (!values.contains(val))
                        values.add(val);
        Random rnd = new Random(System.currentTimeMillis());
        user = values.get(rnd.nextInt(values.size()));
        values.clear();
        return user;
    }

    static User getuserbylogin(String login){
        for (User val:users)
            if (val.login.equals(login))
                return val;
        return null;
    }

    static ArrayList<User> users = new ArrayList<>();

    static void usersinitial(){
        User newuser = new User();

        newuser.login = "jd1";
        newuser.pass = "123";
        newuser.roles.add("Внутренние. Создатели");
        newuser.roles.add("Входящие. Рассматривающие");
        newuser.roles.add("Входящие. Рассматривающие в подразделении");
        newuser.roles.add("Добавляющие участников");
        newuser.roles.add("Договор. Исполнитель");
        newuser.roles.add("Договор. Куратор");
        newuser.roles.add("Договор. Подписант");
        newuser.roles.add("Договор. Регистратор");
        newuser.roles.add("Исходящие. Отправляющие");
        newuser.roles.add("Исходящие. Создатели");
        newuser.roles.add("Мероприятия.Создатели");
        newuser.roles.add("Мероприятия.Технолог");
        newuser.roles.add("НД. Создатели");
        newuser.roles.add("ОРД. Создатели");
        newuser.roles.add("Ответственные за ресурсы");
        newuser.roles.add("Подписанты документов");
        newuser.roles.add("Подписанты документов ЭП");
        newuser.roles.add("Поручения. Выбирающий инициатора");
        newuser.roles.add("Поручения. Куратор");
        newuser.roles.add("Поручения.Инициатор");
        newuser.roles.add("Резолюции. Создатели");
        newuser.roles.add("Руководитель высшего звена");
        newuser.roles.add("СЭД. Архивариус");
        newuser.roles.add("СЭД. Выбирающий Исполнителя");
        newuser.roles.add("СЭД. Регистраторы");
        newuser.roles.add("СЭД. Технолог");
        newuser.roles.add("Совещания.Создатели");
        newuser.roles.add("Согласующие документов");
        newuser.roles.add("Технолог Контрагентов");
        newuser.roles.add("Технолог Оргструктуры");
        newuser.roles.add("Технолог ЮЗД");
        newuser.roles.add("Технолог календарей");
        newuser.roles.add("Технолог маршрутов");
        newuser.roles.add("Технолог шаблонов");
        newuser.roles.add("Участие в ЮЗД");
        newuser.famio = "Наумова К.1.";
        newuser.fio = "Наумова Калерия 1";
        newuser.full = "Наумова Калерия 1 Директор Название организации";
        users.add(newuser);
        newuser = new User();

        /*newuser.login = "jd2";
        newuser.pass = "123";
        newuser.roles.add("Внутренние. Создатели");
        newuser.roles.add("Добавляющие участников");
        newuser.roles.add("Исходящие. Создатели");
        newuser.roles.add("Поручения. Выбирающий инициатора");
        newuser.roles.add("Руководитель высшего звена");
        newuser.roles.add("Совещания.Создатели");
        newuser.roles.add("Технолог Контрагентов");
        newuser.famio = "Тихомирова С.2.";
        newuser.fio = "Тихомирова Саломея 2";
        newuser.full = "Тихомирова Саломея 2 Тестировщик Подразделение 1";
        users.add(newuser);
        newuser = new User();*/

        newuser.login = "jd3";
        newuser.pass = "123";
        newuser.roles.add("Внутренние. Создатели");
        newuser.roles.add("Договор. Куратор");
        newuser.roles.add("Исходящие. Создатели");
        newuser.roles.add("НД. Создатели");
        newuser.roles.add("ОРД. Создатели");
        newuser.roles.add("Подписанты документов ЭП");
        newuser.roles.add("СЭД. Выбирающий Исполнителя");
        newuser.roles.add("СЭД. Контролёры");
        newuser.roles.add("СЭД. Регистраторы");
        newuser.roles.add("Технолог ЮЗД");
        newuser.roles.add("Участие в ЮЗД");
        newuser.famio = "Белозёров Я.3.";
        newuser.fio = "Белозёров Януарий 3";
        newuser.full = "Белозёров Януарий 3 Инженер-разработчик Подразделение 1";
        users.add(newuser);
        newuser = new User();

        newuser.login = "jd4";
        newuser.pass = "123";
        newuser.roles.add("Мероприятия.Создатели");
        newuser.roles.add("Подписанты документов ЭП");
        newuser.roles.add("Поручения. Выбирающий инициатора");
        newuser.roles.add("Согласующие документов");
        newuser.famio = "Васютина Ф.4.";
        newuser.fio = "Васютина Флорентина 4";
        newuser.full = "Васютина Флорентина 4 Директор Подразделение 1";
        users.add(newuser);
        newuser = new User();

        newuser.login = "jd5";
        newuser.pass = "123";
        newuser.roles.add("Подписанты документов");
        newuser.famio = "Полякова В.5.";
        newuser.fio = "Полякова Вера 5";
        newuser.full = "Полякова Вера 5 Директор Подразделение 1";
        users.add(newuser);
        newuser = new User();

        newuser.login = "jd6";
        newuser.pass = "123";
        newuser.roles.add("Внутренние. Создатели");
        newuser.roles.add("Договор. Исполнитель");
        newuser.roles.add("Исходящие. Создатели");
        newuser.roles.add("НД. Создатели");
        newuser.roles.add("ОРД. Создатели");
        newuser.roles.add("Ответственные за ресурсы");
        newuser.roles.add("Подписанты документов");
        newuser.roles.add("Резолюции. Создатели");
        newuser.roles.add("Руководитель высшего звена");
        newuser.roles.add("СЭД. Архивариус");
        newuser.roles.add("Совещания.Создатели");
        newuser.roles.add("Технолог делегирования");
        newuser.roles.add("Технолог маршрутов");
        newuser.roles.add("Технолог шаблонов");
        newuser.famio = "Кондратьева Р.6.";
        newuser.fio = "Кондратьева Ростислава 6";
        newuser.full = "Кондратьева Ростислава 6 Тестировщик Подразделение 1";
        users.add(newuser);
        newuser = new User();

        newuser.login = "jd7";
        newuser.pass = "123";
        newuser.roles.add("Добавляющие участников");
        newuser.roles.add("Договор. Регистратор");
        newuser.roles.add("Поручения. Выбирающий инициатора");
        newuser.roles.add("Поручения.Инициатор");
        newuser.roles.add("Резолюции. Создатели");
        newuser.famio = "Марков Ю.7.";
        newuser.fio = "Марков Юрий 7";
        newuser.full = "Марков Юрий 7 Дегустатор Подразделение 1";
        users.add(newuser);
        newuser = new User();

        newuser.login = "jd8";
        newuser.pass = "123";
        newuser.roles.add("Внутренние. Создатели");
        newuser.roles.add("Исходящие. Отправляющие");
        newuser.roles.add("Резолюции. Создатели");
        newuser.roles.add("СЭД. Контролёры");
        newuser.roles.add("Совещания.Создатели");
        newuser.roles.add("Совещания.Технолог");
        newuser.roles.add("Согласующие документов");
        newuser.roles.add("Читатель документов");
        newuser.famio = "Захар З.8.";
        newuser.fio = "Захар Захар 8";
        newuser.full = "Захар Захар 8 Управляющий Подразделение 1";
        users.add(newuser);
        newuser = new User();

        newuser.login = "jd9";
        newuser.pass = "123";
        newuser.roles.add("Внутренние. Создатели");
        newuser.famio = "Петр П.9.";
        newuser.fio = "Петр Петр 9";
        newuser.full = "Петр Петр 9 Заместитель директора Подразделение 1";
        users.add(newuser);
        newuser = new User();

        newuser.login = "jdd";
        newuser.pass = "123";
        newuser.roles.add("Внутренние. Создатели");
        newuser.roles.add("Входящие. Рассматривающие");
        newuser.roles.add("Входящие. Рассматривающие в подразделении");
        newuser.roles.add("Выбирающий автора поручения и резолюции");
        newuser.roles.add("Добавляющие участников");
        newuser.roles.add("Договор. Исполнитель");
        newuser.roles.add("Договор. Куратор");
        newuser.roles.add("Договор. Подписант");
        newuser.roles.add("Договор. Регистратор");
        newuser.roles.add("Договор. Читатель");
        newuser.roles.add("Исходящие. Отправляющие");
        newuser.roles.add("Исходящие. Создатели");
        newuser.roles.add("Мероприятия.Технолог");
        newuser.roles.add("НД. Создатели");
        newuser.roles.add("ОРД. Создатели");
        newuser.roles.add("Ответственные за ресурсы");
        newuser.roles.add("Подписанты документов");
        newuser.roles.add("Подписанты документов ЭП");
        newuser.roles.add("Подписывающийся на действия пользователя");
        newuser.roles.add("Поручения. Выбирающий инициатора");
        newuser.roles.add("Поручения. Куратор");
        newuser.roles.add("Поручения. Читатель");
        newuser.roles.add("Редактор документов");
        newuser.roles.add("Резолюции. Создатели");
        newuser.roles.add("Резолюции. Читатель");
        newuser.roles.add("Руководитель высшего звена");
        newuser.roles.add("СЭД. Архивариус");
        newuser.roles.add("СЭД. Выбирающий Исполнителя");
        newuser.roles.add("СЭД. Контролёры");
        newuser.roles.add("СЭД. Подписанты");
        newuser.roles.add("СЭД. Регистраторы");
        newuser.roles.add("СЭД. Технолог");
        newuser.roles.add("Совещания.Создатели");
        newuser.roles.add("Совещания.Технолог");
        newuser.roles.add("Согласующие документов");
        newuser.roles.add("Технолог Контрагентов");
        newuser.roles.add("Технолог Оргструктуры");
        newuser.roles.add("Технолог ЮЗД");
        newuser.roles.add("Технолог бизнес-журнала");
        newuser.roles.add("Технолог делегирования");
        newuser.roles.add("Технолог календарей");
        newuser.roles.add("Технолог маршрутов");
        newuser.roles.add("Технолог подписок");
        newuser.roles.add("Технолог справочников");
        newuser.roles.add("Технолог шаблонов");
        newuser.roles.add("Участие в ЮЗД");
        newuser.roles.add("Читатель документов");
        newuser.famio = "John D. J.j.";
        newuser.fio = "John D. John D. jdd";
        newuser.full = "John D. John D. jdd Еще один дегустатор Название организации";
        users.add(newuser);
        newuser = new User();
    }
}
