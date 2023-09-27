package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;



public class MainPage {                                                                                                                    //класс, инкапсулирующий в себе всю логику внутреннего устройства Главной страницы
    private final SelenideElement heading = Selenide.$("h2.heading");                                                             //приватное поле класса заголовок=возвращает первый найденный по CSS селектору Заголовок на странице (выносим в поля ключевой элемент странийы)
    private final SelenideElement byButton = Selenide.$$(".button").first();                                                     //приватное поле класса кнопка купить=возвращает Кнопку найденную по CSS селектру. первую()
    private final SelenideElement byButtonOnCredit = Selenide.$$(".button").last();                                              //приватное поле класса Кнопка купить в кредит=возвращает Кнопку найденную по CSS селектру.последнюю()

    public MainPage() {                              //Конструктор открывающий Главную страницу
        heading.shouldBe(Condition.visible);         //видимость заголовка
    }

    public PaymentPage getDebitCardPayment() {       //Оплату дебетовой картой
        byButton.click();                           //нажмите на кнопку первую
        return new PaymentPage();                    //возвращает страницу Оплаты (которая появится после клика по кнопке)
    }

    public PaymentPage getPaymentByCreditCard() {     //Оплата кредитной картой
        byButtonOnCredit.click();                    //нажмите кнопку последнюю
        return new PaymentPage();                     //возвращает страницу Оплаты (которая появится после клика по кнопке)
    }
}

