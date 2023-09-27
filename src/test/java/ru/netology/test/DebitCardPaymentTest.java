package ru.netology.test;


import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.page.MainPage;
import ru.netology.db.SqlHelper;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitCardPaymentTest {                                                 //Проверка оплаты дебетовой картой

    private MainPage mainPage;                                                              //Главная страница

    @BeforeAll                                                                      //аннотированный метод д/б выполнен перед всеми @Test
    public static void setUpAll() {                                                 //метод создания подключения к отчету по моему д/б throws SQLException
        SelenideLogger.addListener("allure", new AllureSelenide());           //Логирует шаги теста Selenide и уведомляет об этом.Добавить слушателя в текущий поток с имененм аллур (создание отчета)
    }

    @AfterAll                                                                      //аннотированный метод д/б выполнен после всех тестов в текущем тестовом классе
    public static void tearDownAll() {                                             //метод удаления функции после теста
        SelenideLogger.removeListener("allure");                             //Логирует шаги теста Selenide и уведомляет об этом.удаление слушателя с имененм аллур
        SqlHelper.cleanDb();                                                       //очистить базу данных()
    }

    @BeforeEach                                                                   //отвечает за настройку подключения
    public void setUp() {                                                         //метод выполнения функции до теста
        mainPage = open("http://localhost:8080", MainPage.class); //Главная страница=открыть(адрес тетсируемой системы. класс главной страницы)
    }



                                                             //ПОЗИТИВНЫЕ СЦЕНАРИИ

    @Test
    public void paymentByDebitCardWithApprovedStatusWithIntroductionValidValues() {    //оплата Дебетовой Картой Со Статусом "Одобрено" С Введением Валидных Значений
        val paymentPage = mainPage.getDebitCardPayment();                              //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getValidApprovedCardData();                              //инфо=Помощник по обработке данных.Получить валидные данные карты
        paymentPage.fillPaymentFormat(info);                                           //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkSuccessNotification();                                        //страница оплаты.Проверка успешного уведомления
        assertEquals("APPROVED", SqlHelper.getStatusPaymentEntity());

    }
    @Test
    public void attemptPayDebitCardWithIntroductionNextMonth() {                       //оплата дебетовой картой с введением следующего месяца за текущим.
        val paymentPage = mainPage.getDebitCardPayment();                              //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getTheNextMonthAfterCurrentOne();                        //инфо=класс генератор.Получить следующий месяц следующий за текущим
        paymentPage.fillPaymentFormat(info);                                           //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkSuccessNotification();                                        //страница оплаты.Проверка успешного уведомления
        assertEquals("APPROVED", SqlHelper.getStatusPaymentEntity());                               //проверка на равенство ожид и факт результа(ожид-одобренный,Статус платежа)
    }

    @Test
    public void anAttemptToPayWithDebitCardWithTheIntroductionOfNextYear() {            //оплата дебетовой картой с введением  года следующего за текущим
        val paymentPage = mainPage.getDebitCardPayment();                               //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getTheCardNextYear();                                     //инфо=класс генератор.Получить карту с указанием следующего за текущим годом
        paymentPage.fillPaymentFormat(info);                                            //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkSuccessNotification();                                         //страница оплаты.Проверка успешного уведомления
        assertEquals("APPROVED", SqlHelper.getStatusPaymentEntity());           //проверка на равенство ожид и факт результа(ожид-одобренный,Статус платежа)
    }

    @Test
    public void anAttemptPayWithDebitCardWithIntroductionOwnerCapitalLetters() {        //оплата дебетовой картой с введением Владелица заглавными буквами
        val paymentPage = mainPage.getDebitCardPayment();                               //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getOwnerWithUpperCase();                                  //инфо=класс генератор.Получить владельца заглавными буквами на латинице
        paymentPage.fillPaymentFormat(info);                                            //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkSuccessNotification();                                         //страница оплаты.Проверка успешного уведомления
        assertEquals("APPROVED", SqlHelper.getStatusPaymentEntity());           //проверка на равенство ожид и факт результа(ожид-одобренный,Статус платежа)
    }




                                                               // НЕГАТИВНЫЕ СЦЕНАРИИ


    // НОМЕР КАРТЫ

    @Test
    public void anAttemptToPayWithDebitCardWithTheStatusDECLINED() {                //Попытка Оплаты Дебетовой Картой Со Статусом ОТКЛОНЕНО
        val paymentPage = mainPage.getDebitCardPayment();                           //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getValidDeclinedCardData();                           //инфо=Помощник по обработке данных.Получить действительные данные отклоненной карты
        paymentPage.fillPaymentFormat(info);                                        //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkErrorNotification();                                       //страница оплаты.Проверка уведомления об ошибке
        assertEquals("DECLINED", SqlHelper.getStatusPaymentEntity());       //проверка на равенство ожид и факт результа(ожид-отклоненный,Статус платежа)
    }

    @Test
    public void anAttemptToPayWithDebitCardWithAnEmptyCardNumberField() {            //Попытка Оплаты Дебетовой Картой С Пустым Полем Номера Карты
        val paymentPage = mainPage.getDebitCardPayment();                            //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getCardNumberFieldEmpty();                             //инфо=класс генератор.Получить пустое поле с номером карты
        paymentPage.fillPaymentFormat(info);                                         //страница оплаты.Заполните форму платежа(информация)
        paymentPage.verifyEmptyField();                                              //страница оплаты.поле обязательно для заполнения
    }
    @Test
    public void anAttemptPayWithDebitCardWithCardNumberConsistingOfOneDigit() {       //Попытка Оплаты Дебетовой Картой С Номером Карты, Состоящим Из Одной Цифры
        val paymentPage = mainPage.getDebitCardPayment();                             //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getInvalidCardNumberWithOneDigit();                     //инфо=класс генератор.Получить неверный номер карты с одной цифрой
        paymentPage.fillPaymentFormat(info);                                          //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                               //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptToPayWithDebitCardWithFifteenDigitCardNumber() {             //Попытка Оплаты Дебетовой Картой С Пятнадцатизначным Номером Карты
        val paymentPage = mainPage.getDebitCardPayment();                             //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getInvalidCardNumberWithFifteenDigits();                //инфо=класс генератор.Получить недействительный номер карты из пятнадцати цифр
        paymentPage.fillPaymentFormat(info);                                          //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                               //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptToPayWithDebitCardWthZeroValuesInTheCardField() {           //Попытка Оплаты Дебетовой Картой С Нулевыми Значениями В Поле Карта
        val paymentPage = mainPage.getDebitCardPayment();                            //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getInvalidCardNumberIfFieldAllZeros();                 //инфо=класс генератор.Получить неверный номер карты, если введете все нули
        paymentPage.fillPaymentFormat(info);                                         //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkErrorNotification();                                        //страница оплаты.Проверка уведомления об ошибке
    }

    @Test
    public void anAttemptToPayWithDebitCardWithAnInvalidCardNumber() {                //Попытка Оплаты Дебетовой Картой С Неверным Номером Карты
        val paymentPage = mainPage.getDebitCardPayment();                             //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getAnotherBankCardNumber();                             //инфо=класс генератор.Получить другой номер банковской карты
        paymentPage.fillPaymentFormat(info);                                          //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkErrorNotification();                                         //страница оплаты.Проверка уведомления об ошибке
    }





    // МЕСЯЦ

    @Test
    public void anAttemptToPayWithDebitCardWithAnEmptyMonthField() {                 //Попытка Оплаты Дебетовой Картой С Пустым Полем Месяца
        val paymentPage = mainPage.getDebitCardPayment();                            //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getFieldMonthEmpty();                                  //инфо=класс генератор.Получить поле Месяц пустым
        paymentPage.fillPaymentFormat(info);                                         //страница оплаты.Заполните форму платежа(информация)
        paymentPage.verifyEmptyField();                                              //страница оплаты.поле обязательно для заполнения
    }
    @Test
    public void anAttemptWithDebitCardWithTheIntroductionOfSingleDigitDateMonth() {  //Попытка Оплаты Дебетовой Картой С Введением одной цифры в поле Месяца
        val paymentPage = mainPage.getDebitCardPayment();                            //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getInvalidMonthWithOneDigit();                         //инфо=класс генератор.Получить неверный месяц с одной цифрой
        paymentPage.fillPaymentFormat(info);                                         //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                              //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptToPayWithDebitCardWithTheIntroductionZeroValuesInMonth() {  //Попытка Оплаты Дебетовой Картой С Введением Нулевых Значений В Месяц
        val paymentPage = mainPage.getDebitCardPayment();                            //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getInvalidMonthWithZeros();                            //инфо=класс генератор.Получить недопустимый месяц с нулями
        paymentPage.fillPaymentFormat(info);                                         //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkInvalidCardExpirationDate();                                //страница оплаты.неверно указан срок действия карты
    }
    @Test
    public void anAttemptWithDebitCardWithTheIntroductionNonExistentDateOfMonth() {  //попытка оплаты дебетовой картой с введением несуществующей даты месяца.
        val paymentPage = mainPage.getDebitCardPayment();                            //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getInvalidMonthWithIrrelevantValue();                  //инфо=класс генератор.Получить не существующий месяц
        paymentPage.fillPaymentFormat(info);                                         //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkInvalidCardExpirationDate();                                //страница оплаты.неверно указан срок действия карты
    }
    @Test
    public void anAttemptToPayWithDebitCardWithTheIntroductionPreviousMonth() {       //попытка оплаты дебетовой картой с введением предыдущего месяца.
        val paymentPage = mainPage.getDebitCardPayment();                             //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getInvalidMonthIsCurrentYear();                         //инфо=класс генератор.Получить предыдущий месяц
        paymentPage.fillPaymentFormat(info);                                          //страница оплаты.Заполните форму платежа(информация)
        paymentPage.verifyCardExpired();                                              //страница оплаты.срок действия карты истек
    }




    //  ГОД

    @Test
    public void attemptedDebitCardPaymentWithAnEmptyYearField() {                       //попытка оплаты дебетовой картой с пустым полем Год
        val paymentPage = mainPage.getDebitCardPayment();                               //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getFieldYearEmpty();                                      //инфо=класс генератор.Получить поле Год пустым
        paymentPage.fillPaymentFormat(info);                                            //страница оплаты.Заполните форму платежа(информация)
        paymentPage.verifyEmptyField();                                                 //страница оплаты.поле обязательно для заполнения
    }
    @Test
    public void anAttemptWithDebitCardWithTheIntroductionOfSingleDigitInYearField() {   // попытка оплаты дебетовой картой с введением одной цифры в поле год
        val paymentPage = mainPage.getDebitCardPayment();                               //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getInvalidYearWithOneDigit();                             //инфо=класс генератор.Получить неверный год с одной цифрой
        paymentPage.fillPaymentFormat(info);                                            //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                 //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptToPayWithDebitCardWithTheIntroductionOfZerosInYearField() {    // попытка оплаты дебетовой картой с введением нулей в поле год
        val paymentPage = mainPage.getDebitCardPayment();                               //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getInvalidYearWithZeros();                                //инфо=класс генератор.Получить недопустимый год с нулями
        paymentPage.fillPaymentFormat(info);                                            //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkInvalidCardExpirationDate();                                   //страница оплаты.неверно указан срок действия карты
    }
    @Test
    public void debitCardPaymentExperienceWithIntroductionExpiredYearOfCardIssuance() { //попытка оплаты дебетовой картой с введением истекшего года выдачи карты
        val paymentPage = mainPage.getDebitCardPayment();                               //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getInvalidYearExceedingCardExpirationDate();              //инфо=класс генератор.Получить год, превышающий дату истечения срока действия карты на 6 лет от текущего
        paymentPage.fillPaymentFormat(info);                                            //страница оплаты.Заполните форму платежа(информация)
        paymentPage.verifyCardExpired();                                                //страница оплаты.срок действия карты истек
    }
    @Test
    public void attemptWithDebitCardWithIntroductionPreviousYear() {                    //попытка оплаты дебетовой картой с введением предыдущего года выдачи карты
        val paymentPage = mainPage.getDebitCardPayment();                               //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getCardWithExpiredYear();                                 //инфо=класс генератор.Получить недействительный год, текущий год -1
        paymentPage.fillPaymentFormat(info);                                            //страница оплаты.Заполните форму платежа(информация)
        paymentPage.verifyCardExpired();                                                //страница оплаты.срок действия карты истек
    }




    // ВЛАДЕЛЕЦ

    @Test
    public void anAttemptToPayWithDebitCardWithTheIntroductionOfOwnerInCyrillic() {     // попытка оплаты дебетовой картой с введением Владельца на кириллице
        val paymentPage = mainPage.getDebitCardPayment();                               //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getInvalidOwnerWithCyrillic();                            //инфо=класс генератор.Получить недействительного владельца с кириллицей
        paymentPage.fillPaymentFormat(info);                                            //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                 //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptWithDebitCardWithTheIntroductionOfOneWordInOwnerFieldLatin() { //попытка оплаты дебетовой картой с введением  одного слова в поле Владелец на латинице
        val paymentPage = mainPage.getDebitCardPayment();                               //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getInvalidOwnerWithOneWord();                             //инфо=класс генератор.Получить недействительного владельца одним Словом
        paymentPage.fillPaymentFormat(info);                                            //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                 //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptToPayWithDebitCardWithAnEmptyOwnerField() {                    //попытка оплаты дебетовой картой  с пустым полем Владелец.
        val paymentPage = mainPage.getDebitCardPayment();                               //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getOwnerFieldEmpty();                                     //инфо=класс генератор.Получить поле Владельца пустым
        paymentPage.fillPaymentFormat(info);                                            //страница оплаты.Заполните форму платежа(информация)
        paymentPage.verifyEmptyField();                                                 //страница оплаты.поле обязательно для заполнения
    }
    @Test
    public void anAttemptWithDebitCardWithTheIntroductionOneLetterTheOwnerField() {     //попытка оплаты дебетовой картой с введением одной буквы в поле Владелец.
        val paymentPage = mainPage.getDebitCardPayment();                               //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getInvalidOwnerWithOneLetter();                           //инфо=класс генератор.Получить недействительного владельца с одной буквой
        paymentPage.fillPaymentFormat(info);                                            //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                 //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptToPayWithDebitCardWithTheIntroductionOwnerLowercaseLetters() { // попытка оплаты дебетовой картой с введением Владелица строчными буквами
        val paymentPage = mainPage.getDebitCardPayment();                               //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getInvalidOwnerWithLowerCase();                           //инфо=класс генератор.Получить недопустимого владельца состоящего из строчных букв
        paymentPage.fillPaymentFormat(info);                                            //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                 //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptWithDebitCardWithIntroductionOwnerConsistingThreeWords() {     //попытка оплаты дебетовой картой с введением Владельца состоящего из трех латинских слов
        val paymentPage = mainPage.getDebitCardPayment();                               //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getInvalidOwnerWithThreeWords();                          //инфо=класс генератор.Получить недействительного владельца из трех слов
        paymentPage.fillPaymentFormat(info);                                            //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                 //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptWithDebitCardWithIntroductionMaximumAllowableNumberLetters() { //попытка оплаты дебетовой картой с введением Владельца максимально-допустимого количества букв
        val paymentPage = mainPage.getDebitCardPayment();                               //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getInvalidOwnerWithLotsNumberOfLetters();                 //инфо=класс генератор.Получить Владельца состоящего из большого количества букв
        paymentPage.fillPaymentFormat(info);                                            //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                 //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptWithDebitCardWithIntroductionNumbersOwnerField() {             // попытка оплаты дебетовой картой с введением цифр в поле Владелец
        val paymentPage = mainPage.getDebitCardPayment();                               //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getInvalidOwnerWithDigits();                              //инфо=класс генератор.Получить неверного Владельца с цифрами
        paymentPage.fillPaymentFormat(info);                                            //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                 //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptWithDebitCardWithIntroductionSpecialCharactersOwnerField() {   // попытка оплаты дебетовой картой с введением спецсимволов в поле Владелец.
        val paymentPage = mainPage.getDebitCardPayment();                               //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getInvalidOwnerWithSymbols();                             //инфо=класс генератор.Получить поле Владельца с символами
        paymentPage.fillPaymentFormat(info);                                            //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                 //страница оплаты.Проверка неправильного формата
    }



    //  CVC

    @Test
    public void tryingWithDebitCardWithEmptyCVCField() {                                //попытке оплатить дебетовой картой с пустым полем CVC
        val paymentPage = mainPage.getDebitCardPayment();                               //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getEmptyCVCField();                                       //инфо=класс генератор.Получить неверный CVC пустым
        paymentPage.fillPaymentFormat(info);                                            //страница оплаты.Заполните форму платежа(информация)
        paymentPage.verifyEmptyField();                                                 //страница оплаты.поле обязательно для заполнения
    }
    @Test
    public void anAttemptWithDebitCardWithIntroductionSingleDigitSMSValue() {           //попытка оплаты дебетовой картой с введением СМС значения из одной цифры
        val paymentPage = mainPage.getDebitCardPayment();                               //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getInvalidCVCWithOneDigit();                              //инфо=класс генератор.Получить неверный CVC с одной цифрой
        paymentPage.fillPaymentFormat(info);                                            //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                 //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void  anAttemptWithDebitCardWithIntroductionTwoDigitSMSValue() {             //попытка оплаты дебетовой картой  с введением СМС значения из двух цифр
        val paymentPage = mainPage.getDebitCardPayment();                               //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getInvalidCVCWithTwoDigits();                             //инфо=класс генератор.Получить неверный CVC с двумя цифрами
        paymentPage.fillPaymentFormat(info);                                            //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                 //страница оплаты.Проверка неправильного формата
    }
    @Test
    public void anAttemptWithDebitCardWithIntroductionZeroSMSValues() {                 //попытка оплаты дебетовой картой с введением СМС нулевых значений
        val paymentPage = mainPage.getDebitCardPayment();                               //страница оплаты=Главная страница.Получить оплату дебетовой картой
        val info = DataHelper.getInvalidCVCWithZeros();                                 //инфо=класс генератор.Получить неверный CVC с нулями
        paymentPage.fillPaymentFormat(info);                                            //страница оплаты.Заполните форму платежа(информация)
        paymentPage.checkWrongFormat();                                                 //страница оплаты.Проверка неправильного формата
    }

}