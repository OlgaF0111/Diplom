package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {                                                            //Класс генератор

    //методы используемые для тестовых данных


    //  НОМЕР КАРТЫ

    public static String getApprovedCardNumber() {

        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardNumber() {

        return "4444 4444 4444 4442";
    }

    public static String getEmptyCardNumberField() {

        return "";
    }

    public static String getIncorrectCardNumberWithOneDigit() {

        return "4";
    }

    public static String getIncorrectCardNumberWithFifteenDigits() {

        return "4444 4444 4444 444";
    }

    public static String getInvalidCardNumber() {

        return "0000 0000 0000 0000";
    }

    public static String getAnotherBankFieldCardNumber() {

        return "4444 4444 4444 4443";
    }


    //  МЕСЯЦ

    public static String getShiftedMonthFromNow(int shiftedMonth) {                                    //Перенесенный месяц от текущего месяца (int сдвинутый месяц)
        return LocalDate.now().plusMonths(shiftedMonth).format(DateTimeFormatter.ofPattern("MM"));     //возвращает объект, который представляет текущую дату.+добавляет к дате некоторое количество месяцев.
    }                                                                                                  // формат (написания даты и времени. статический метод (ММ))

    public static String getEmptyMonthField() {

        return "";
    }

    public static String getIncorrectMonthWithOneDigit() {

        return "1";
    }

    public static String getInvalidMonthIfFieldZeros() {

        return "00";
    }

    public static String getInvalidMonth() {

        return "13";
    }


    //  ГОД

    public static String getShiftedYearFromNow(int shiftedYears) {                                  //Перенесенный год от текущего (int сдвинутые годы)
        return LocalDate.now().plusYears(shiftedYears).format(DateTimeFormatter.ofPattern("yy"));   // класс,возвращает объект, который представляет текущую дату.+добавляет к дате некоторое количество лет.
    }                                                                                               // формат (написания даты и времени. статический метод (ГГ))

    public static String getEmptyYearField() {

        return "";
    }

    public static String getIncorrectYearWithOneDigit() {

        return "1";
    }

    public static String getInvalidYearIfFieldZeros() {

        return "00";
    }


    //  Владелец

    public static String getValidOwner() {                                             //получить действительного владельца
        Faker faker = new Faker(new Locale("en"));                             // создан объект фейкера(новый объект класса (определение языка))
        return faker.name().firstName() + " " + faker.name().lastName();               //возвращает созданную случайную имя и фамилию на латинице
    } // +генерация случайной фамилии

    public static String getInvalidOwnerFieldOnCyrillic() {                           //Недопустимое Поле Владельца на Кириллице
        Faker faker = new Faker(new Locale("ru"));                            // создан объект фейкера(новый объект класса (определение языка))
        return faker.name().firstName() + " " + faker.name().lastName();              //возвращает созданную случайную имя и фамилию на кириллице
    }

    public static String getInvalidOwnerFieldWithOneWord() {                          //Недопустимое поле владельца с одним словом на латинице
        Faker faker = new Faker(new Locale("en"));                            // создан объект фейкера(новый объект класса (определение языка))
        return faker.name().firstName();                                              //возвращает созданное случайное имя на латинице
    }

    public static String getEmptyOwnerField() {

        return "";
    }

    public static String getInvalidOwnerFieldWithOneLetter() {

        return "F";
    }

    public static String getInvalidOwnerFieldWithLowerCase() {

        return "petr petrov";
    }

    public static String getInvalidOwnerFieldWithUpperCase() {

        return "PETR PETROV";
    }

    public static String getInvalidOwnerFieldWithThreeWords() {

        return "Petr Petrovich Petrov";
    }

    public static String getInvalidOwnerFieldWithLotsNumberOfLetters() {
        return "FFFFFFFFFFFFFFFFAAAAAAAAAAAAAAAAARRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR";
    }

    public static String getInvalidOwnerFieldWithDigits() {

        return "" + "0123";
    }

    public static String getInvalidOwnerFieldWithSymbols() {

        return "$&@%%&#@";
    }


    //  CVC

    public static String getValidCVC() {                                          //получить действительный CVC
        Faker faker = new Faker();                                                // создан объект фейкера
        return faker.numerify("###");                                  // возвращает случайный набор чисел
    }

    public static String getEmptyFieldCVC() {

        return "";
    }

    public static String getIncorrectCVCWithOneDigit() {

        return "1";
    }

    public static String getIncorrectCVCWithTwoDigits() {

        return "12";
    }

    public static String getInvalidCVC() {

        return "000";
    }
}
