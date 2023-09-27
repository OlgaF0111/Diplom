package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {                                                                                   //класс, инкапсулирующий в себе всю логику внутреннего устройства Страница оплаты

    private final SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");                   //приватное поле класса - Поле с номером карты
    private final SelenideElement monthField = $("[placeholder='08']");                                         //приватное поле класса -поле месяца
    private final SelenideElement yearField = $("[placeholder='22']");                                          //приватное поле класса -поле "год"
    private final SelenideElement ownerField = $$(".input").find(exactText("Владелец")).$(".input__control");  //приватное поле класса -поле владельца
    private final SelenideElement cvcField = $("[placeholder='999']");                                          //приватное поле класса -поле cvc
    private final SelenideElement button = $$(".button").find(exactText("Продолжить"));                         //приватное поле класса -кнопка Продолжить

    private final SelenideElement successNotification = $(byText("Успешно"));                           //приватное поле класса -уведомление об успешном завершении
    private final SelenideElement errorNotification = $(byText("Ошибка"));                          //приватное поле класса -уведомление об ошибке
    private final SelenideElement wrongFormat = $(byText("Неверный формат"));                                  //приватное поле класса -неправильный формат
    private final SelenideElement invalidCardExpirationDate = $(byText("Неверно указан срок действия карты")); //приватное поле класса -недействительный срок действия карты
    private final SelenideElement cardExpired = $(byText("Истёк срок действия карты"));                        //приватное поле класса -срок действия карты истек
    private final SelenideElement emptyField = $(byText("Поле обязательно для заполнения"));                   //приватное поле класса -пустое поле


    public void fillPaymentFormat(DataHelper.CardInfo info) {                       //заполните формат платежа (данные карты для входа)
        cardNumberField.setValue(info.getCardNumber());                             //Поле с номером карты. устанавливает значение (получение номера карты)
        monthField.setValue(info.getMonth());                                       //поле месяца. устанавливает значение (получение месяца)
        yearField.setValue(info.getYear());                                         //поле "год". устанавливает значение (получение года)
        ownerField.setValue(info.getOwner());                                       //поле владельца. устанавливает значение (получение владельца)
        cvcField.setValue(info.getCvc());                                           //поле cvc. устанавливает значение (получение cvc)
        button.click();                                                             //кнопка. кликнуть по ней.

    }

    public  void checkSuccessNotification() {                                      //проверьте уведомление об успешном завершении
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));    //уведомление об успешном завершении.сообщение видимое пользователю(проверяет условие, виден ли элемент, в течении 15 сек.)

    }

    public void checkErrorNotification() {                                         //проверьте уведомление об ошибке
        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));      //уведомление об ошибке.соощение видимое пользователю(проверяет условие, виден ли элемент, в течении 15 сек.)
    }

    public void checkWrongFormat() {                                               //проверьте неправильный формат
        wrongFormat.shouldBe(Condition.visible);                                   //неправильный формат.соощение видимое пользователю(проверяет условие, виден ли элемент)
    }

    public void checkInvalidCardExpirationDate() {                                //проверьте срок действия карты
        invalidCardExpirationDate.shouldBe(Condition.visible);                    //неверно указан срок действия карты.соощение видимое пользователю(проверяет условие, виден ли элемент)
    }

    public void verifyCardExpired() {                                             //срок действия карты истек
        cardExpired.shouldBe(Condition.visible);                                  //срок действия карты истек.соощение успеха видимое пользователю(проверяет условие, виден ли элемент)
    }

    public void verifyEmptyField() {                                             //проверьте пустое поле
        emptyField.shouldBe(Condition.visible);                                  //поле обязательно для заполнения.соощение успеха видимое пользователю(проверяет условие, виден ли элемент)
    }
}