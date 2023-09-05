package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {                                                            //Класс генератор

    //методы используемые для тестовых данных



    //  НОМЕР КАРТЫ

    public static String getApprovedCardNumber() {                                      //геттер возвращает утвержденный номер карты лежащий в поле

        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardNumber() {                                      //геттер возвращает номер отклоненной карты

        return "4444 4444 4444 4442";
    }

    public static String getEmptyCardNumberField() {                                   //получить пустое поле номера карты

        return "";
    }

    public static String getIncorrectCardNumberWithOneDigit() {                        //получите номер карты с одной цифрой

        return "4";
    }

    public static String getIncorrectCardNumberWithFifteenDigits() {                   //геттер возвращает номер карты из пятнадцати цифр

        return "4444 4444 4444 444";
    }

    public static String getInvalidCardNumber() {                                      //геттер возвращает неверный номер карты с нулями

        return "0000 0000 0000 0000";
    }

    public static String getAnotherBankFieldCardNumber() {                             //геттер возвращает другой номер банковской карты

        return "4444 4444 4444 4443";
    }



    //  МЕСЯЦ

    public static String getShiftedMonthFromNow(int shiftedMonth) {                                    //Перенесенный месяц от текущего месяца (int сдвинутый месяц)
        return LocalDate.now().plusMonths(shiftedMonth).format(DateTimeFormatter.ofPattern("MM"));     // класс,возвращает объект, который представляет текущую дату.+добавляет к дате некоторое количество месяцев.
    }                                                                                                  // формат (написания даты и времени. статический метод (ММ))

    public static String getEmptyMonthField() {                                         //Пустое поле месяца

        return "";
    }

    public static String getIncorrectMonthWithOneDigit() {                              //Неправильный месяц с одной цифрой

        return "1";
    }

    public static String getInvalidMonthIfFieldZeros() {                                //получите недопустимый месяц, если в поле есть нули

        return "00";
    }

    public static String getInvalidMonth() {                                            //получить недействительный месяц

        return "13";
    }



    //  ГОД

    public static String getShiftedYearFromNow(int shiftedYears) {                                  //Перенесенный на год вперед (int сдвинутые годы)
        return LocalDate.now().plusYears(shiftedYears).format(DateTimeFormatter.ofPattern("yy"));   // класс,возвращает объект, который представляет текущую дату.+добавляет к дате некоторое количество лет.
    }                                                                                               // формат (написания даты и времени. статический метод (ГГ))

    public static String getEmptyYearField() {                                          //получить пустое поле года

        return "";
    }

    public static String getIncorrectYearWithOneDigit() {                                //получить неверный год с одной цифрой

        return "1";
    }

    public static String getInvalidYearIfFieldZeros() {                                  //получите недопустимый год, если в поле есть нули

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

    public static String getEmptyOwnerField() {                                      //получить пустое поле владельца

        return "";
    }

    public static String getInvalidOwnerFieldWithOneLetter() {                        //Недопустимое поле владельца с одной буквой

        return "F";
    }

    public static String getInvalidOwnerFieldWithLowerCase() {                        //Недопустимое поле владельца со строчными буквами

        return "petr petrov";
    }

    public static String getInvalidOwnerFieldWithUpperCase() {                        //Недопустимое поле владельца с заглавными буквами

        return "PETR PETROV";
    }

    public static String getInvalidOwnerFieldWithThreeWords() {                       //Недопустимое поле владельца с тремя словами на латинице

        return "Petr Petrovich Petrov";
    }

    public static String getInvalidOwnerFieldWithLotsNumberOfLetters() {             //Недопустимое Поле Владельца с Большим Количеством Букв
        return "FFFFFFFFFFFFFFFFAAAAAAAAAAAAAAAAARRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR";
    }

    public static String getInvalidOwnerFieldWithDigits() {                          //Недопустимое поле владельца с цифрами

        return "" + "0123";
    }

    public static String getInvalidOwnerFieldWithSymbols() {                         //Недопустимое поле владельца с символами

        return "$&@%%&#@";
    }



    //  CVC

    public static String getValidCVC() {                                          //получить действительный CVC
        Faker faker = new Faker();                                                // создан объект фейкера
        return faker.numerify("###");                                  // возвращает случайный набор чисел
    }

    public static String getEmptyFieldCVC() {                                     //получить пустое поле CVC

        return "";
    }

    public static String getIncorrectCVCWithOneDigit() {                          //получите неверный CVC с одной цифрой

        return "1";
    }

    public static String getIncorrectCVCWithTwoDigits() {                          //получите неверный CVC с двумя цифрами

        return "12";
    }

    public static String getInvalidCVC() {                                         //получить недопустимый CVC

        return "000";
    }
}
