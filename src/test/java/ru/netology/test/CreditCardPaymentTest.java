package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.MainPage;
import ru.netology.sql.SqlHelper;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardPaymentTest {                                                   //Проверка оплаты с помощью выданного кредита по данным карты

    MainPage mainPage;                                                                 //Главная страница

    @BeforeAll                                                                         //аннотированный метод д/б выполнен перед всеми @Test
    public static void setUpAll() {                                                    //метод создания подключения к ??? по моему д/б throws SQLException
        SelenideLogger.addListener("allure", new AllureSelenide());              //Логирует шаги теста Selenide и уведомляет об этом.Добавить слушателя в текущий поток с имененм аллур (создание отчета)
    }
    @AfterAll                                                                          //аннотированный метод д/б выполнен после всех тестов в текущем тестовом классе
    public static void tearDownAll() {                                                 //метод удаления функции после теста
        SelenideLogger.removeListener("allure");                                 //Логирует шаги теста Selenide и уведомляет об этом.удаление слушателя с имененм аллур
        SqlHelper.cleanDb();                                                           //класс для работы с базой данных.очистить базу данных()
    }
    @BeforeEach                                                                        //отвечает за настройку подключения и сохранения предварительных данных в базу
    public void setUp() {                                                              //метод выполнения функции до теста Установка
        mainPage = open(System.getProperty("sut.url"), MainPage.class);                //Главная страница=открыть(метод получения конкретного системного свойства(адрес тетсируемой системы). класс главной страницы)
    }
//нет кода для подключения к БД, получения статуса запроса от БД

                                                                    // ПОЗИТИВНЫЕ СЦЕНАРИИ

    @Test
    public void paymentByCreditCardWithApprovedStatusWithIntroductionValidValues() {      //оплата  Кредитной Картой Со Статусом Одобрено С Введением Валидных Значений
        val paymentPage = mainPage.getPaymentByCreditCard();                              //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getValidApprovedCardData();                                 //инфо=класс генератор.Получить валидные данные карты
        paymentPage.fillPaymentFormat(info);                                              //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkSuccessNotification();                                           //страница оплаты.Проверка успешного уведомления
        val paymentStatus = SqlHelper.getStatusCreditRequestEntity();                     //статус платежа=класс для работы с базой данных.Получить статус кредитного запроса
        assertEquals("APPROVED", paymentStatus);                                  //проверка на равенство ожид и факт результа(ожид-одобренный,Статус платежа)
    }
    @Test
    public void PayCreditCardWithIntroductionNextMonth() {                               //оплата Кредитной картой с введением следующего месяца.
        val paymentPage = mainPage.getPaymentByCreditCard();                             //страница оплаты=Главная страница.Получить оплату кредитной картой
        val info = DataHelper.getTheNextMonthAfterCurrentOne();                          //инфо=класс генератор.Получить следующий месяц от текущего
        paymentPage.fillPaymentFormat(info);                                             //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkSuccessNotification();                                          //страница оплаты.Проверка успешного уведомления
        val paymentStatus = SqlHelper.getStatusCreditRequestEntity();                    //статус платежа=класс для работы с базой данных.Получить статус кредитного запроса
        assertEquals("APPROVED", paymentStatus);                                 //проверка на равенство ожид и факт результа(ожид-одобренный,Статус платежа)

    }
    @Test
    public void PayWithCreditCardWithTheIntroductionOfNextYear() {                       //оплата Кредитной картой с введением следующего года
        val paymentPage = mainPage.getPaymentByCreditCard();                             //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getCardWithExpiredYear();                                  //инфо=класс генератор.Получить карту с годом следующим за текущим
        paymentPage.fillPaymentFormat(info);                                             //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkSuccessNotification();                                          //страница оплаты.Проверка успешного уведомления
        val paymentStatus = SqlHelper.getStatusCreditRequestEntity();                    //статус платежа=класс для работы с базой данных.Получить статус кредитного запроса
        assertEquals("APPROVED", paymentStatus);                                 //проверка на равенство ожид и факт результа(ожид-одобренный,Статус платежа)
    }
    @Test
    public void PayWithCreditCardWithIntroductionOwnerCapitalLetters() {                 //оплата Кредитной картой с введением Владелица заглавными буквами
        val paymentPage = mainPage.getPaymentByCreditCard();                             //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getInvalidOwnerWithUpperCase();                            //инфо=класс генератор.Получить владельца с заглавными буквами на латинице
        paymentPage.fillPaymentFormat(info);                                             //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkSuccessNotification();                                          //страница оплаты.Проверка успешного уведомления
        val paymentStatus = SqlHelper.getStatusCreditRequestEntity();                    //статус платежа=класс для работы с базой данных.Получить статус кредитного запроса
        assertEquals("APPROVED", paymentStatus);                                 //проверка на равенство ожид и факт результа(ожид-одобренный,Статус платежа)

    }



                                                                //НЕГАТИВНЫЕ СЦЕНАРИИ

    //НОМЕР КАРТЫ

    @Test
    public void anAttemptToPayWithCreditCardWithTheStatusDECLINED() {                   //Попытка Оплаты Кредитной Картой Со Статусом ОТКЛОНЕНО
        val paymentPage = mainPage.getPaymentByCreditCard();                            //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getValidDeclinedCardData();                               //инфо=класс генератор.Получить данные отклоненной карты
        paymentPage.fillPaymentFormat(info);                                            //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkErrorNotification();                                           //страница оплаты.Проверка уведомления об ошибке
        val paymentStatus = SqlHelper.getStatusCreditRequestEntity();                   //статус платежа=класс для работы с базой данных.Получить статус кредитного запроса
        assertEquals("DECLINED", paymentStatus);                                //проверка на равенство ожид и факт результа(ожид-отклоненный,Статус платежа)
    }
    @Test
    public void anAttemptToPayWithCreditCardWithAnEmptyCardNumberField() {            //Попытка Оплаты Кредитной Картой С Пустым Полем Номера Карты
        val paymentPage = mainPage.getPaymentByCreditCard();                          //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getCardNumberFieldEmpty();                              //инфо=класс генератор.Получить пустое поле номера карты
        paymentPage.fillPaymentFormat(info);                                          //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                               //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptPayWithCreditCardWithCardNumberConsistingOfOneDigit() {      //Попытка Оплаты Кредитной Картой С Номером Карты, Состоящим Из Одной Цифры
        val paymentPage = mainPage.getPaymentByCreditCard();                          //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getInvalidCardNumberWithOneDigit();                     //инфо=класс генератор.Получить неверный номер карты с одной цифрой
        paymentPage.fillPaymentFormat(info);                                          //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                               //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptToPayWithCreditCardWithFifteenDigitCardNumber() {             //Попытка Оплаты Кредитной Картой С Пятнадцатизначным Номером Карты
        val paymentPage = mainPage.getPaymentByCreditCard();                           //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getInvalidCardNumberWithFifteenDigits();                 //инфо=класс генератор.Получить недействительный номер карты из пятнадцати цифр
        paymentPage.fillPaymentFormat(info);                                           //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                //страница оплаты.Проверка неправильного формата
    }
     @Test
    public void anAttemptToPayWithCreditCardWthZeroValuesInTheCardField() {            //Попытка Оплаты Кредитной Картой С Нулевыми Значениями В Поле Карта
        val paymentPage = mainPage.getPaymentByCreditCard();                           //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getInvalidCardNumberIfFieldAllZeros();                   //инфо=класс генератор.Получить недействительный номер карты, если ввести все нули
        paymentPage.fillPaymentFormat(info);                                           //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkErrorNotification();                                          //страница оплаты.Проверка уведомления об ошибке
         val paymentStatus = SqlHelper.getStatusCreditRequestEntity();                 //статус платежа=класс для работы с базой данных.Получить статус кредитного запроса
         assertEquals("DECLINED", paymentStatus);                              //проверка на равенство ожид и факт результа(ожид-отклоненный,Статус платежа)
    }
    @Test
    public void anAttemptToPayWithCreditCardWithAnInvalidCardNumber() {                //Попытка Оплаты Кредитной Картой С Неверным Номером Карты
        val paymentPage = mainPage.getPaymentByCreditCard();                           //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getAnotherBankCardNumber();                              //инфо=класс генератор.Получить недействительный номер карты, если ввести все нули
        paymentPage.fillPaymentFormat(info);                                           //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkErrorNotification();                                          //страница оплаты.Проверка уведомления об ошибке
        val paymentStatus = SqlHelper.getStatusCreditRequestEntity();                  //статус платежа=класс для работы с базой данных.Получить статус кредитного запроса
        assertEquals("DECLINED", paymentStatus);                               //проверка на равенство ожид и факт результа(ожид-отклоненный,Статус платежа)
    }




    //МЕСЯЦ

    @Test
    public void anAttemptToPayWithCreditCardWithAnEmptyMonthField() {                  //Попытка Оплаты Кредитной Картой С Пустым Полем Месяца
        val paymentPage = mainPage.getPaymentByCreditCard();                           //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getFieldMonthEmpty();                                    //инфо=класс генератор.Получить поле Месяц пустым
        paymentPage.fillPaymentFormat(info);                                           //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptWithCreditCardWithTheIntroductionOfSingleDigitDateMonth() {   //Попытка Оплаты Кредитной Картой С Введением одной цифры в поле Месяца
        val paymentPage = mainPage.getPaymentByCreditCard();                           //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getInvalidMonthWithOneDigit();                           //инфо=класс генератор.Получить неверный месяц с одной цифрой
        paymentPage.fillPaymentFormat(info);                                           //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptToPayWithCreditCardWithTheIntroductionZeroValuesInMonth() {   //Попытка Оплаты Кредитной Картой С Введением Нулевых Значений В Месяц
        val paymentPage = mainPage.getPaymentByCreditCard();                           //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getInvalidMonthWithZeros();                              //инфо=класс генератор.Получить недопустимый месяц с нулями
        paymentPage.fillPaymentFormat(info);                                           //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkInvalidCardExpirationDate();                                  //страница оплаты.Проверка срока действия недействительной карты
    }
    @Test
    public void anAttemptWithCreditCardWithTheIntroductionNonExistentDateOfMonth() {  //попытка оплаты Кредитной картой с введением несуществующей даты месяца.
        val paymentPage = mainPage.getPaymentByCreditCard();                          //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getInvalidMonthWithIrrelevantValue();                   //инфо=класс генератор.Получить недопустимый месяц с нерелевантным значением
        paymentPage.fillPaymentFormat(info);                                          //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkInvalidCardExpirationDate();                                 //страница оплаты.Проверка срока действия недействительной карты
    }
    @Test
    public void anAttemptToPayWithCreditCardWithTheIntroductionPreviousMonth() {      //попытка оплаты Кредитной картой с введением предыдущего месяца.
        val paymentPage = mainPage.getPaymentByCreditCard();                          //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getInvalidMonthIsCurrentYear();                         //инфо=класс генератор.Получить неверный месяц состоящий из текущего года
        paymentPage.fillPaymentFormat(info);                                          //страница оплаты.Заполните форму платежа(информация)
        paymentPage.verifyCardExpired();                                              //страница оплаты.Проверка что срок действия карты истек
    }




    //ГОД

    @Test
    public void attemptedCreditCardPaymentWithAnEmptyYearField() {                    //попытка оплаты Кредитной картой с пустым полем Год
        val paymentPage = mainPage.getPaymentByCreditCard();                          //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getFieldYearEmpty();                                    //инфо=класс генератор.Получить поле Год пустым
        paymentPage.fillPaymentFormat(info);                                          //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                               //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptWithCreditCardWithTheIntroductionOfSingleDigitInYearField() { // попытка оплаты Кредитной картой с введением одной цифры в поле год
        val paymentPage = mainPage.getPaymentByCreditCard();                           //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getInvalidYearWithOneDigit();                            //инфо=класс генератор.Получить неверный год с одной цифрой
        paymentPage.fillPaymentFormat(info);                                           //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptToPayWithCreditCardWithTheIntroductionOfZerosInYearField() {  // попытка оплаты Кредитной картой с введением нулей в поле год
        val paymentPage = mainPage.getPaymentByCreditCard();                           //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getInvalidYearWithZeros();                               //инфо=класс генератор.Получить недопустимый год с нулями
        paymentPage.fillPaymentFormat(info);                                           //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkInvalidCardExpirationDate();                                  //страница оплаты.Проверка срока действия недействительной карты
    }
    @Test
    public void CreditCardPaymentExperienceWithIntroductionExpiredYearOfCardIssuance() { //попытка оплаты Кредитной картойс введением истекшего года выдачи карты
        val paymentPage = mainPage.getPaymentByCreditCard();                            //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getInvalidYearExceedingCardExpirationDate();              //инфо=класс генератор.Получить год, превышающий дату истечения срока действия карты на 6 лет от текущего
        paymentPage.fillPaymentFormat(info);                                            //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkInvalidCardExpirationDate();                                   //страница оплаты.Проверка срока действия недействительной карты
    }
    @Test
    public void attemptWithCreditCardWithIntroductionPreviousYear() {                   //попытка оплаты Кредитной картой с введением предыдущего года выдачи карты
        val paymentPage = mainPage.getPaymentByCreditCard();                            //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getCardWithExpiredYear();                                 //инфо=класс генератор.Получить недействительный год, текущий год -1
        paymentPage.fillPaymentFormat(info);                                            //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkInvalidCardExpirationDate();                                   //страница оплаты.Проверка срока действия недействительной карты
    }





    //ВЛАДЕЛЕЦ

    @Test
    public void anAttemptToPayWithCreditCardWithTheIntroductionOfOwnerInCyrillic() {     // попытка оплаты Кредитной картой с введением Владельца на кириллице
        val paymentPage = mainPage.getPaymentByCreditCard();                             //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getInvalidOwnerWithCyrillic();                             //инфо=класс генератор.Получить недействительного владельца с кириллицей
        paymentPage.fillPaymentFormat(info);                                             //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                  //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptWithCreditCardWithTheIntroductionOfOneWordInOwnerFieldLatin() { //попытка оплаты Кредитной картой с введением  одного слова в поле Владелец на латинице
        val paymentPage = mainPage.getPaymentByCreditCard();                             //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getInvalidOwnerWithOneWord();                              //инфо=класс генератор.Получить недействительного владельца одним Словом
        paymentPage.fillPaymentFormat(info);                                             //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                  //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptToPayWithCreditCardWithAnEmptyOwnerField() {                     //попытка оплаты Кредитной картой  с пустым полем Владелец.
        val paymentPage = mainPage.getPaymentByCreditCard();                              //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getOwnerFieldEmpty();                                       //инфо=класс генератор.Получить поле Владельца пустым
        paymentPage.fillPaymentFormat(info);                                              //страница оплаты.Заполните форму платежа(информация)
        paymentPage.verifyEmptyField();                                                   //страница оплаты.Проверка пустого поля
    }
    @Test
    public void anAttemptWithCreditCardWithTheIntroductionOneLetterTheOwnerField() {      //попытка оплаты Кредитной картой с введением одной буквы в поле Владелец.
        val paymentPage = mainPage.getPaymentByCreditCard();                              //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getInvalidOwnerWithOneLetter();                             //инфо=класс генератор.Получить недействительного владельца с одной буквой
        paymentPage.fillPaymentFormat(info);                                              //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                   //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptToPayWithCreditCardWithTheIntroductionOwnerLowercaseLetters() {  // попытка оплаты Кредитной картой с введением Владелица строчными буквами
        val paymentPage = mainPage.getPaymentByCreditCard();                              //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getInvalidOwnerWithLowerCase();                             //инфо=класс генератор.Получить недопустимого владельца состоящего из строчных букв
        paymentPage.fillPaymentFormat(info);                                              //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                   //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptWithCreditCardWithIntroductionOwnerConsistingThreeWords() {     //попытка оплаты Кредитной картой с введением Владельца состоящего из трех латинских слов
        val paymentPage = mainPage.getPaymentByCreditCard();                             //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getInvalidOwnerWithThreeWords();                           //инфо=Помощник по обработке данных.Получить недействительного владельца из трех слов
        paymentPage.fillPaymentFormat(info);                                             //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                  //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptWithCreditCardWithIntroductionMaximumAllowableNumberLetters() { //попытка оплаты Кредитной картой с введением Владельца максимально-допустимого количества букв
        val paymentPage = mainPage.getPaymentByCreditCard();                             //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getInvalidOwnerWithLotsNumberOfLetters();                  //инфо=класс генератор.Получить Владельца состоящего из большого количества букв
        paymentPage.fillPaymentFormat(info);                                             //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                  //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptWithCreditCardWithIntroductionNumbersOwnerField() {             // попытка оплаты Кредитной картой с введением цифр в поле Владелец
        val paymentPage = mainPage.getPaymentByCreditCard();                             //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getInvalidOwnerWithDigits();                               //инфо=класс генератор.Получить неверного Владельца с цифрами
        paymentPage.fillPaymentFormat(info);                                             //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                  //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptWithCreditCardWithIntroductionSpecialCharactersOwnerField() {   // попытка оплаты Кредитной картой с введением спецсимволов в поле Владелец.
        val paymentPage = mainPage.getPaymentByCreditCard();                             //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getInvalidOwnerWithSymbols();                              //инфо=класс генератор.Получить поле Владельца с символами
        paymentPage.fillPaymentFormat(info);                                             //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                  //страница оплаты.Проверка неправильного формата
    }



    //CVC

    @Test
    public void tryingWithCreditCardWithEmptyCVCField() {                                 //попытке оплатить Кредитной картой с пустым полем CVC
        val paymentPage = mainPage.getPaymentByCreditCard();                             //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getEmptyCVCField();                                        //инфо=класс генератор.Получить неверный CVC пустым
        paymentPage.fillPaymentFormat(info);                                             //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                  //страница оплаты.Проверка неправильного формата
        paymentPage.checkWrongFormat();                                                  //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptWithCreditCardWithIntroductionSingleDigitSMSValue() {           // попытка оплаты Кредитной картой с введением СМС значения из одной цифры
        val paymentPage = mainPage.getPaymentByCreditCard();                             //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getInvalidCVCWithOneDigit();                               //инфо=класс генератор.Получить неверный CVC с одной цифрой
        paymentPage.fillPaymentFormat(info);                                             //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                  //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptWithCreditCardWithIntroductionTwoDigitSMSValue() {              //попытка оплаты Кредитной картой  с введением СМС значения из двух цифр
        val paymentPage = mainPage.getPaymentByCreditCard();                             //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getInvalidCVCWithTwoDigits();                              //инфо=класс генератор.Получить неверный CVC с двумя цифрами
        paymentPage.fillPaymentFormat(info);                                             //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                  //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptWithCreditCardWithIntroductionZeroSMSValues() {                 // попытка оплаты Кредитной картой с введением СМС нулевых значений
        val paymentPage = mainPage.getPaymentByCreditCard();                             //страница оплаты=Главная страница.Получить оплату с помощью кредитной карты
        val info = DataHelper.getInvalidCVCWithZeros();                                  //инфо=класс генератор.Получить неверный CVC с нулями
        paymentPage.fillPaymentFormat(info);                                             //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                  //страница оплаты.Проверка неправильного формата
    }

}