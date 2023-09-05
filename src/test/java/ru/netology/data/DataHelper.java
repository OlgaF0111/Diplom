package ru.netology.data;

import lombok.Value;
import org.checkerframework.checker.units.qual.C;

public class DataHelper {                                                        //Класс генератор

    public static DataGenerator dataGenerator = new DataGenerator();             //создан объект Датагенератора

    @Value                                                                       //аннотация библиотеки Ломбок.Генерирует для объекта конструктор,геттеры.Она на этапе компиляции добавляет в классы код за нас.
    public static class CardInfo {                                               //метод генерации Информации о карте
        String cardNumber;                                                       //переменная номер карты
        String month;                                                            //месяц
        String year;                                                             //год
        String owner;                                                            //владелец
        String cvc;
    }

// не понимаю, почему в поле месяц и год ставим, 1, 2, 3.

    //  НОМЕР КАРТЫ

    public static CardInfo getValidApprovedCardData() {                         //статичный метод информации о карте - получите валидные данные карты
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } //возвращает информацию о карте(генерирует.получение утвержденного номера карты,получение перенесенного значения на 1месяц,получение перенесенного на год вперед,получение валидного владельца,получение действительного CVC)

    public static CardInfo getValidDeclinedCardData() {                         //статичный метод информации о карте- получите данные отклоненной карты
        return new CardInfo(DataGenerator.getDeclinedCardNumber(), DataGenerator.getShiftedMonthFromNow(2), DataGenerator.getShiftedYearFromNow(2), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } //получение номера отклоненной карты

    public static CardInfo getCardNumberFieldEmpty() {                          //статичный метод информации о карте -поле "номер карты" пусто
        return new CardInfo(DataGenerator.getEmptyCardNumberField(), DataGenerator.getShiftedMonthFromNow(3), DataGenerator.getShiftedYearFromNow(3), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } //вполучение пустого поля с номером карты

    public static CardInfo getInvalidCardNumberWithOneDigit() {                 //статичный метод информации о карте -получите номер карты с одной цифрой
        return new CardInfo(DataGenerator.getIncorrectCardNumberWithOneDigit(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } //получение не валидного номера карты с одной цифрой

    public static CardInfo getInvalidCardNumberWithFifteenDigits() {            //статичный метод информации о карте -получите номер карты из пятнадцати цифр
        return new CardInfo(DataGenerator.getIncorrectCardNumberWithFifteenDigits(), DataGenerator.getShiftedMonthFromNow(2), DataGenerator.getShiftedYearFromNow(2), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } //получение не валидного номера карты из пятнадцати цифр

    public static CardInfo getInvalidCardNumberIfFieldAllZeros() {              //статичный метод информации о карте -получите номер карты с нулями
        return new CardInfo(DataGenerator.getInvalidCardNumber(), DataGenerator.getShiftedMonthFromNow(3), DataGenerator.getShiftedYearFromNow(3), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } //получение номера карты состоящего из нулей

    public static CardInfo getAnotherBankCardNumber() {                         //статичный метод информации о карте -получите другой недействительный номер банковской карты
        return new CardInfo(DataGenerator.getAnotherBankFieldCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } //получение не валидного номера банковской карты




    //  МЕСЯЦ

    public static CardInfo getFieldMonthEmpty() {                            //статичный метод информации о карте -получить поле Месяц пустым
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getEmptyMonthField(), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } //возвращает информацию о карте(генерирует.получение валидного номера карты,   получение пустого поле месяца,   получение перенесенного на год вперед,   получение валидного владельца,получение валидного CVC)

    public static CardInfo getInvalidMonthWithOneDigit() {                  //статичный метод информации о карте -получить неверный месяц с одной цифрой
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getIncorrectMonthWithOneDigit(), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } //получение не валидного месяца с одной цифрой

    public static CardInfo getInvalidMonthWithZeros() {                     //статичный метод информации о карте -получаем недопустимый месяц с нулями
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getInvalidMonthIfFieldZeros(), DataGenerator.getShiftedYearFromNow(2), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } //получение месяца с нулями

    public static CardInfo getInvalidMonthWithIrrelevantValue() {           //статичный метод информации о карте -получить недопустимый месяц с нерелевантным значением.
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getInvalidMonth(), DataGenerator.getShiftedYearFromNow(3), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } //получение не существующего месяца =13

    public static CardInfo getInvalidMonthIsCurrentYear() {                 //статичный метод информации о карте -получить предыдущий месяц
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(-1), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } //получение на месяц назад от текущего

    public static CardInfo getTheNextMonthAfterCurrentOne() {               //статичный метод информации о карте -получить следующий месяц за текущим
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(0), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } // получение предыдущего месяца





    //  ГОД

    public static CardInfo getFieldYearEmpty() {                           //статичный метод информации о карте -Получить поле Год пустым
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getEmptyYearField(), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } //возвращает информацию о карте(генерирует.получение валидного номера карты,   получение месяца перенесенного на 1,   получение пустого поле год,   получение валидного владельца,   получение валидного CVC)

    public static CardInfo getInvalidYearWithOneDigit() {                 //статичный метод информации о карте -получить неверный год с одной цифрой
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(2), DataGenerator.getIncorrectYearWithOneDigit(), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } //получение года с одной цифрой

    public static CardInfo getInvalidYearWithZeros() {                    //статичный метод информации о карте -получаем недопустимый год с нулями
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(3), DataGenerator.getInvalidYearIfFieldZeros(), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } // получение года с нулями

    public static CardInfo getTheCardNextYear() {                          //статичный метод информации о карте -получить карту со следующим годом действия
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow( 1), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } //получение значения на год вперед от текущего

    public static CardInfo getInvalidYearExceedingCardExpirationDate() {    //статичный метод информации о карте -получить недействительный год, превышающий срок действия карты
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(6), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } //получение значения на 6 лет вперед в поле Год

    public static CardInfo getCardWithExpiredYear() {                       //статичный метод информации о карте -получить карту с истекшим годом действия, предыдущий
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(-1), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    } //получение значения на год назад от текущего






    //  ВЛАДЕЛЕЦ

    public static CardInfo getInvalidOwnerWithCyrillic() {               //статичный метод информации о карте -получить недействительного владельца с кириллицей
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(2), DataGenerator.getShiftedYearFromNow(2), DataGenerator.getInvalidOwnerFieldOnCyrillic(), DataGenerator.getValidCVC());
    } //возвращает информацию о карте(генерирует.получение валидного номера карты,   получение месяца перенесенного на 2,   получение значения на 2года вперед,    получение не валидного владельца на кириллице,   получение валидного CVC)

    public static CardInfo getInvalidOwnerWithOneWord() {               //статичный метод информации о карте -получить недействительного владельца одним Словом на латинице
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(3), DataGenerator.getInvalidOwnerFieldWithOneWord(), DataGenerator.getValidCVC());
    } //получение не валидного владельца одним Словом на латинице

    public static CardInfo getOwnerFieldEmpty() {                       //статичный метод информации о карте -получить поле владельца пустым
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(3), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getEmptyOwnerField(), DataGenerator.getValidCVC());
    } //получение пустого поле владельца

    public static CardInfo getInvalidOwnerWithOneLetter() {             //статичный метод информации о карте -получить недействительного владельца с помощью одной буквы
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(2), DataGenerator.getShiftedYearFromNow(2), DataGenerator.getInvalidOwnerFieldWithOneLetter(), DataGenerator.getValidCVC());
    } //получение не валидного владельца в поле с одной буквой

    public static CardInfo getInvalidOwnerWithLowerCase() {             //статичный метод информации о карте -получить недопустимого владельца строчными буквами
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(3), DataGenerator.getInvalidOwnerFieldWithLowerCase(), DataGenerator.getValidCVC());
    } //получение не валидного владельца строчными буквами

    public static CardInfo getInvalidOwnerWithUpperCase() {             //статичный метод информации о карте -получить недопустимый владелец с заглавными буквами
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(3), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getInvalidOwnerFieldWithUpperCase(), DataGenerator.getValidCVC());
    } //получение не валидного владелеца с заглавными буквами

   public static CardInfo getInvalidOwnerWithThreeWords() {            //статичный метод информации о карте -получить недействительного владельца с помощью трех слов на латинице
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(3), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getInvalidOwnerFieldWithThreeWords(), DataGenerator.getValidCVC());
    } //получение не валидного владельца из трех слов на латинице

    public static CardInfo getInvalidOwnerWithLotsNumberOfLetters() {  //статичный метод информации о карте -получить Недействительного Владельца С Большим Количеством Букв
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(2), DataGenerator.getShiftedYearFromNow(2), DataGenerator.getInvalidOwnerFieldWithLotsNumberOfLetters(), DataGenerator.getValidCVC());
    } //получение не валидного Владельца С Большим Количеством Букв

    public static CardInfo getInvalidOwnerWithDigits() {               //статичный метод информации о карте -получить недействительного владелеца с цифрами
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getInvalidOwnerFieldWithDigits(), DataGenerator.getValidCVC());
    } //получение не валидного владелеца с цифрами

    public static CardInfo getInvalidOwnerWithSymbols() {              //статичный метод информации о карте -получить недействительного владельца с символами
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getInvalidOwnerFieldWithSymbols(), DataGenerator.getValidCVC());
    } //получение не валидного владельца с символами




    // CVC

    public static CardInfo getEmptyCVCField() {                        //статичный метод информации о карте -получить пустое поле CVC
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(3), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getEmptyFieldCVC());
    } //возвращает информацию о карте(генерирует.получение утвержденного номера карты,получение месяца перенесенного на 3,получение значения на года вперед, получение действительного владельца,получение пустого поле CVC)

    public static CardInfo getInvalidCVCWithOneDigit() {              //статичный метод информации о карте -получить недопустимый CVC с одной цифрой
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(2), DataGenerator.getShiftedYearFromNow(2), DataGenerator.getValidOwner(), DataGenerator.getIncorrectCVCWithOneDigit());
    } //получение не валидного CVC с одной цифрой

    public static CardInfo getInvalidCVCWithTwoDigits() {             //статичный метод информации о карте -получить недопустимый CVC с двумя цифрами
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(3), DataGenerator.getValidOwner(), DataGenerator.getIncorrectCVCWithTwoDigits());
    } //получение не валидного CVC с двумя цифрами

    public static CardInfo getInvalidCVCWithZeros() {                 //статичный метод информации о карте -получить недопустимый CVC с нулями
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(2), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getInvalidCVC());
    } //получение не валидного CVC с нулями

}