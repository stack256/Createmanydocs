package Box;

import java.util.ArrayList;

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



    static User getanotheruser(User user){
        ArrayList<User> values = new ArrayList<>();
        User uservalue = null;
        for (User val:users)
            if (val != user)
                values.add(val);
        //Random rnd = new Random(System.currentTimeMillis());
        uservalue = values.get((int) (Math.random() * values.size()));
        values.clear();
        return uservalue;
    }

    static User getuserbyroles(String... roles){
        ArrayList<User> values = new ArrayList<>();
        User user = null;

        boolean t = true;
        for (User val:users) {
            t = true;
            for (String role : roles)
                if (!val.roles.contains(role))
                    t = false;
            if (t)
                values.add(val);
        }


        /*for (String role:roles)
            for (User val:users)
                if (val.roles.contains(role))
                    if (!values.contains(val))
                        values.add(val);*/
        //Random rnd = new Random(System.currentTimeMillis());
        user = values.get((int) (Math.random() * values.size()));
        values.clear();
        return user;
    }

    static User getuserbyrolewithoutrole(String roleyep, String rolenope){
        ArrayList<User> values = new ArrayList<>();
        User user = null;
        for (User val:users)
            if (val.roles.contains(roleyep))
                if (!values.contains(val))
                    values.add(val);

        int i = 0;
        while (i < values.size()) {
            for (User val : values)
                if (val.roles.contains(rolenope)) {
                    values.remove(val);
                    i = -1;
                    break;
                }
            i++;
        }
        //Random rnd = new Random(System.currentTimeMillis());
        user = values.get((int) (Math.random() * values.size()));
        values.clear();
        return user;
    }

    static User getuserbylogin(String login){
        for (User val:users)
            if (val.login.equals(login))
                return val;
        return null;
    }

    static User getrukbyorg(String org){
        switch (org){
            case "AT_Subdivision1":
                return getuserbylogin("gpetuhov");
            case "AT_Subdivision2":
                return getuserbylogin("ersov");
            default:
                return null;
        }
    }

    static User getuserbyfull(String full){
        for (User val:users)
            if (val.full.equals(full))
                return val;
        return null;
    }


    static ArrayList<User> users = new ArrayList<>();

    static void usersinitial(){
        User newuser = new User();
        newuser.login = "admin";
        newuser.pass = System.getProperty("admin.pass");
        users.add(newuser);


        newuser = new User();
        newuser.login = "shestakov";
        newuser.pass = "Par@ol1";
        newuser.roles.add("Внутренние. Создатели");
        newuser.roles.add("Внутренние. Создатели");
        newuser.roles.add("Договор. Исполнитель");
        newuser.roles.add("Исходящие. Создатели");
        newuser.roles.add("НД. Создатели");
        newuser.roles.add("ОРД. Создатели");
        newuser.roles.add("Подписанты документов");
        newuser.roles.add("Поручения. Инициатор");
        newuser.famio = "Шестаков А.Г.";
        newuser.fio = "Шестаков Альфред Гордеевич";
        newuser.full = "Шестаков Альфред Гордеевич Тестировщик AT_Subdivision1";
        users.add(newuser);


        newuser = new User();
        newuser.login = "moiseev";
        newuser.pass = "Par@ol1";
        newuser.roles.add("Подписанты документов");
        newuser.roles.add("Поручения. Инициатор");
        newuser.famio = "Моисеев В.В.";
        newuser.fio = "Моисеев Вадим Викторович";
        newuser.full = "Моисеев Вадим Викторович Тестировщик AT_Subdivision1";
        users.add(newuser);


        newuser = new User();
        newuser.login = "timofeev";
        newuser.pass = "Par@ol1";
        newuser.roles.add("Исходящие. Создатели");
        newuser.roles.add("Поручения.Инициатор");
        newuser.roles.add("СЭД. Регистраторы");
        newuser.famio = "Тимофеев Л.В.";
        newuser.fio = "Тимофеев Людвиг Вячеславович";
        newuser.full = "Тимофеев Людвиг Вячеславович Тестировщик AT_Subdivision1";
        users.add(newuser);


        newuser = new User();
        newuser.login = "udin";
        newuser.pass = "Par@ol1";
        newuser.roles.add("Внутренние. Создатели");
        newuser.roles.add("Договор. Куратор");
        newuser.roles.add("Исходящие. Отправляющие");
        newuser.roles.add("Поручения.Инициатор");
        newuser.roles.add("Резолюции. Создатели");
        newuser.famio = "Юдин Л.В.";
        newuser.fio = "Юдин Лукьян Вадимович";
        newuser.full = "Юдин Лукьян Вадимович Тестировщик AT_Subdivision1";
        users.add(newuser);


        newuser = new User();
        newuser.login = "samoilov";
        newuser.pass = "Par@ol1";
        newuser.roles.add("Исходящие. Создатели");
        newuser.roles.add("Подписанты документов");
        newuser.roles.add("Поручения.Инициатор");
        newuser.roles.add("Руководитель высшего звена");
        newuser.famio = "Самойлов Г.Л.";
        newuser.fio = "Самойлов Григорий Лукьевич";
        newuser.full = "Самойлов Григорий Лукьевич Тестировщик AT_Subdivision1";
        users.add(newuser);


        newuser = new User();
        newuser.login = "makarov";
        newuser.pass = "Par@ol1";
        newuser.roles.add("Подписанты документов");
        newuser.famio = "Макаров П.П.";
        newuser.fio = "Макаров Петр Пантелеймонович";
        newuser.full = "Макаров Петр Пантелеймонович Тестировщик AT_Subdivision1";
        users.add(newuser);


        newuser = new User();
        newuser.login = "fadeev";
        newuser.pass = "Par@ol1";
        newuser.roles.add("Подписанты документов");
        newuser.roles.add("Поручения.Инициатор");
        newuser.roles.add("Совещания.Создатели");
        newuser.famio = "Фадеев Е.А.";
        newuser.fio = "Фадеев Евгений Аркадьевич";
        newuser.full = "Фадеев Евгений Аркадьевич Тестировщик AT_Subdivision1";
        users.add(newuser);


        newuser = new User();
        newuser.login = "belakov";
        newuser.pass = "Par@ol1";
        newuser.roles.add("Договор. Регистратор");
        newuser.roles.add("СЭД. Регистраторы");
        newuser.famio = "Беляков С.Л.";
        newuser.fio = "Беляков Савелий Лаврентьевич";
        newuser.full = "Беляков Савелий Лаврентьевич Тестировщик AT_Subdivision2";
        users.add(newuser);


        newuser = new User();
        newuser.login = "fomin";
        newuser.pass = "Par@ol1";
        newuser.roles.add("Поручения. Инициатор");
        newuser.roles.add("СЭД. Регистраторы");
        newuser.famio = "Фомин Т.Ф.";
        newuser.fio = "Фомин Трофим Федотович";
        newuser.full = "Фомин Трофим Федотович Тестировщик AT_Subdivision2";
        users.add(newuser);


        newuser = new User();
        newuser.login = "kozlov";
        newuser.pass = "Par@ol1";
        newuser.roles.add("Подписанты документов");
        newuser.famio = "Козлов М.А.";
        newuser.fio = "Козлов Михаил Аристархович";
        newuser.full = "Козлов Михаил Аристархович Тестировщик AT_Subdivision2";
        users.add(newuser);


        newuser = new User();
        newuser.login = "ribakov";
        newuser.pass = "Par@ol1";
        newuser.roles.add("СЭД.Контролёры");
        newuser.roles.add("Согласующие документов");
        newuser.famio = "Рыбаков С.Д.";
        newuser.fio = "Рыбаков Самуил Демьянович";
        newuser.full = "Рыбаков Самуил Демьянович Тестировщик AT_Subdivision2";
        users.add(newuser);


        newuser = new User();
        newuser.login = "filippova";
        newuser.pass = "Par@ol1";
        newuser.roles.add("Согласующие документов");
        newuser.famio = "Филиппова М.А.";
        newuser.fio = "Филиппова Мила Агафоновна";
        newuser.full = "Филиппова Мила Агафоновна Тестировщик AT_Subdivision2";
        users.add(newuser);


        newuser = new User();
        newuser.login = "denisov";
        newuser.pass = "Par@ol1";
        newuser.roles.add("СЭД.Контролёры");
        newuser.roles.add("Согласующие документов");
        newuser.famio = "Денисов Р.В.";
        newuser.fio = "Денисов Руслан Всеволодович";
        newuser.full = "Денисов Руслан Всеволодович Тестировщик AT_Subdivision2";
        users.add(newuser);


        newuser = new User();
        newuser.login = "maslova";
        newuser.pass = "Par@ol1";
        newuser.roles.add("Согласующие документов");
        newuser.famio = "Маслова А.Г.";
        newuser.fio = "Маслова Алевтина Глебовна";
        newuser.full = "Маслова Алевтина Глебовна Тестировщик AT_Subdivision2";
        users.add(newuser);


        newuser = new User();
        newuser.login = "gpetuhov";
        newuser.pass = "Par@ol1";
        newuser.famio = "Петухов Г.К.";
        newuser.fio = "Петухов Геннадий Кимович";
        newuser.full = "Петухов Геннадий Кимович Руководитель AT_Subdivision1";
        users.add(newuser);


        newuser = new User();
        newuser.login = "ersov";
        newuser.pass = "Par@ol1";
        newuser.famio = "Ершов К.А.";
        newuser.fio = "Ершов Корнелий Андреевич";
        newuser.full = "Ершов Корнелий Андреевич Руководитель AT_Subdivision1";
        users.add(newuser);







        /*
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
/*
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
        newuser = new User();*/
    }
}
