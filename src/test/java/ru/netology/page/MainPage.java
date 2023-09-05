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
        heading.shouldBe(Condition.visible);         //заголовок. сообщение успеха д/б видимым (проверяет условие, виден ли элемент)
    }

    public PaymentPage getDebitCardPayment() {       //Конструктор Страницы оплаты, метод возвращающий Оплату дебетовой картой()
        buyButton.click();                           //нажмите кнопку "Купить".
        paymentByCard.shouldBe(Condition.visible);   //оплата картой.сообщение успеха д/б видимым (проверяет условие, виден ли элемент)
        return new PaymentPage();                    //возврат экземпляра той страницы которая должна появиться после ввода данных
    }

    public PaymentPage getPaymentByCreditCard() {     //Конструктор Страницы оплаты, метод возвращающий Оплату кредитной картой()
        buyButtonOnCredit.click();                    //нажмите кнопку "Купить в кредит".
        creditCardData.shouldBe(Condition.visible);   //данные кредитной карты. сообщение успеха д/б видимым (проверяет условие, виден ли элемент)
        return new PaymentPage();                     //возврат экземпляра той страницы которая должна появиться после ввода данных
    }
}