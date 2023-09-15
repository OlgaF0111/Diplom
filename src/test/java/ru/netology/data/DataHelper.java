package ru.netology.data;

import lombok.Value;
import org.checkerframework.checker.units.qual.C;

public class DataHelper {                                                        //Класс генератор

    public static DataGenerator dataGenerator = new DataGenerator();             //создан объект Датагенератора

    @Value
    //аннотация библиотеки Ломбок.Генерирует для объекта конструктор,геттеры.Она на этапе компиляции добавляет в классы код за нас.
    public static class CardInfo {                                               //метод генерации Информации о карте
        String cardNumber;                                                       //переменная номер карты
        String month;                                                            //месяц
        String year;                                                             //год
        String owner;                                                            //владелец
        String cvc;
    }


    //  НОМЕР КАРТЫ

    public static CardInfo getValidApprovedCardData() {                         //статичный метод информации о карте - получите валидные данные карты
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } //возвращает информацию о карте(генерирует.получение утвержденного номера карты,получение текущего месяца,получение текущего года,получение валидного владельца,получение действительного CVC)

    public static CardInfo getValidDeclinedCardData() {                         //статичный метод информации о карте- получите данные отклоненной карты
        return new CardInfo(DataGenerator.getDeclinedCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getCardNumberFieldEmpty() {                          //статичный метод информации о карте -поле "номер карты" пусто
        return new CardInfo(DataGenerator.getEmptyCardNumberField(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidCardNumberWithOneDigit() {                 //статичный метод информации о карте -получите номер карты с одной цифрой
        return new CardInfo(DataGenerator.getIncorrectCardNumberWithOneDigit(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidCardNumberWithFifteenDigits() {            //статичный метод информации о карте -получите номер карты из пятнадцати цифр
        return new CardInfo(DataGenerator.getIncorrectCardNumberWithFifteenDigits(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidCardNumberIfFieldAllZeros() {              //статичный метод информации о карте -получите номер карты с нулями
        return new CardInfo(DataGenerator.getInvalidCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } //получение номера карты состоящего из нулей

    public static CardInfo getAnotherBankCardNumber() {                         //статичный метод информации о карте -получите другой недействительный номер банковской карты
        return new CardInfo(DataGenerator.getAnotherBankFieldCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }


    //  МЕСЯЦ

    public static CardInfo getFieldMonthEmpty() {                            //статичный метод информации о карте -получить поле Месяц пустым
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getEmptyMonthField(), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } //возвращает информацию о карте(генерирует.получение валидного номера карты,   получение пустого поле месяца,   получение текущего года,   получение валидного владельца,получение валидного CVC)

    public static CardInfo getInvalidMonthWithOneDigit() {                  //статичный метод информации о карте -получить месяц из одной цифры
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getIncorrectMonthWithOneDigit(), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidMonthWithZeros() {                     //статичный метод информации о карте -получаем месяца с нулями
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getInvalidMonthIfFieldZeros(), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidMonthWithIrrelevantValue() {           //статичный метод информации о карте -получить не существующий месяц
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getInvalidMonth(), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidMonthIsCurrentYear() {                 //статичный метод информации о карте -получить предыдущий месяц
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(-1), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getTheNextMonthAfterCurrentOne() {               //статичный метод информации о карте -получить следующий месяц за текущим
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }


    //  ГОД

    public static CardInfo getFieldYearEmpty() {                           //статичный метод информации о карте -Получить поле Год пустым
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getEmptyYearField(), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } //возвращает информацию о карте(генерирует.получение валидного номера карты,   получение текущего месяца,   получение пустого поле год,   получение валидного владельца,   получение валидного CVC)

    public static CardInfo getInvalidYearWithOneDigit() {                 //статичный метод информации о карте -получить год с одной цифрой
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getIncorrectYearWithOneDigit(), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidYearWithZeros() {                    //статичный метод информации о карте -получаем год с нулями
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getInvalidYearIfFieldZeros(), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getTheCardNextYear() {                          //статичный метод информации о карте -получить карту со следующим годом действия
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidYearExceedingCardExpirationDate() {    //статичный метод информации о карте -получить год, превышающий срок действия карты
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(6), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getCardWithExpiredYear() {                       //статичный метод информации о карте -получить карту с предыдущим годом
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(-1), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } //получение значения на год назад от текущего


    //  ВЛАДЕЛЕЦ

    public static CardInfo getInvalidOwnerWithCyrillic() {               //статичный метод информации о карте -получить владельца на кириллице
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getInvalidOwnerFieldOnCyrillic(), DataGenerator.getValidCVC());
    } //возвращает информацию о карте(генерирует.получение валидного номера карты,   получение текущего месяца,   получение текущего года,    получение не валидного владельца на кириллице,   получение валидного CVC)

    public static CardInfo getInvalidOwnerWithOneWord() {               //статичный метод информации о карте -получить владельца из одного Слова на латинице
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getInvalidOwnerFieldWithOneWord(), DataGenerator.getValidCVC());
    }

    public static CardInfo getOwnerFieldEmpty() {                       //статичный метод информации о карте -получить поле владельца пустым
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getEmptyOwnerField(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithOneLetter() {             //статичный метод информации о карте -получить владельца из одной буквы
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getInvalidOwnerFieldWithOneLetter(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithLowerCase() {             //статичный метод информации о карте -получить владельца строчными буквами
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getInvalidOwnerFieldWithLowerCase(), DataGenerator.getValidCVC());
    }

    public static CardInfo getOwnerWithUpperCase() {             //статичный метод информации о карте -получить владелеца заглавными буквами
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getInvalidOwnerFieldWithUpperCase(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithThreeWords() {            //статичный метод информации о карте -получить владельца из трех слов на латинице
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getInvalidOwnerFieldWithThreeWords(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithLotsNumberOfLetters() {  //статичный метод информации о карте -получить Владельца С Большим Количеством Букв
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getInvalidOwnerFieldWithLotsNumberOfLetters(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithDigits() {               //статичный метод информации о карте -получить владелеца с цифрами
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getInvalidOwnerFieldWithDigits(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithSymbols() {              //статичный метод информации о карте -получить владельца с символами
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getInvalidOwnerFieldWithSymbols(), DataGenerator.getValidCVC());
    }


    // CVC

    public static CardInfo getEmptyCVCField() {                        //статичный метод информации о карте -получить пустое поле CVC
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getValidOwner(), DataGenerator.getEmptyFieldCVC());
    } //возвращает информацию о карте(генерирует.получение утвержденного номера карты,получение текущего месяца ,получение текщего года, получение действительного владельца,получение пустого поле CVC)

    public static CardInfo getInvalidCVCWithOneDigit() {              //статичный метод информации о карте -получить CVC из одной цифры
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getValidOwner(), DataGenerator.getIncorrectCVCWithOneDigit());
    }

    public static CardInfo getInvalidCVCWithTwoDigits() {             //статичный метод информации о карте -получить CVC с двумя цифрами
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getValidOwner(), DataGenerator.getIncorrectCVCWithTwoDigits());
    }

    public static CardInfo getInvalidCVCWithZeros() {                 //статичный метод информации о карте -получить  CVC с нулями
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(0), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getValidOwner(), DataGenerator.getInvalidCVC());
    }
}