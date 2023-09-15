package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;



public class MainPage {                                                                                                                    //класс, инкапсулирующий в себе всю логику внутреннего устройства Главной страницы
    private final SelenideElement heading = Selenide.$("h2.heading");                                                             //приватное поле класса заголовок=возвращает первый найденный по CSS селектору Заголовок на странице (выносим в поля ключевой элемент странийы)
    private final SelenideElement buyButton = Selenide.$$(".button").first();                                                     //приватное поле класса кнопка купить=возвращает Кнопку найденную по CSS селектру. первую()
    private final SelenideElement buyButtonOnCredit = Selenide.$$(".button").last();                                              //приватное поле класса Кнопка купить в кредит=возвращает Кнопку найденную по CSS селектру.последнюю()
    private final SelenideElement paymentByCard = Selenide.$$("h3.heading").find(Condition.exactText("Оплата по карте"));         //приватное поле класса оплата картой=возвращает Заголовок найденный по CSS селектру.найти(элемент с точно заданным текстом ())
    private final SelenideElement creditCardData = Selenide.$$("h3.heading").find(Condition.exactText("Кредит по данным карты")); //приватное поле класса данные кредитной карты=возвращает Заголовок найденный по CSS селектру.найти(элемент с точно заданным текстом ())

    public MainPage() {                              //Конструктор открывающий Главную страницу
        heading.shouldBe(Condition.visible);         //видимость заголовка
    }

    public PaymentPage getDebitCardPayment() {       //Оплату дебетовой картой
        buyButton.click();                           //нажмите на кнопку первую
        paymentByCard.shouldBe(Condition.visible);   //оплата картой, видимость элемента
        return new PaymentPage();                    //возвращает страницу Оплаты (которая появится после клика по кнопке)
    }

    public PaymentPage getPaymentByCreditCard() {     //Оплата кредитной картой
        buyButtonOnCredit.click();                    //нажмите кнопку последнюю
        creditCardData.shouldBe(Condition.visible);   //оплата кредитной картой
        return new PaymentPage();                     //возвращает страницу Оплаты (которая появится после клика по кнопке)
    }
}

