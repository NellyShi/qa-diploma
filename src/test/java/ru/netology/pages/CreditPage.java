package ru.netology.pages;

import ru.netology.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {

    public CreditPage() {
        $$("h3").find(exactText("Кредит по данным карты")).shouldBe(visible);
    }

    public void inputData(CardInfo card) {
        $(byText("Номер карты")).parent().$("[class=\"input__control\"]").setValue(card.getCardNumber());
        $(byText("Месяц")).parent().$("[class=\"input__control\"]").setValue(card.getMonth());
        $(byText("Год")).parent().$("[class=\"input__control\"]").setValue(card.getYear());
        $(byText("Владелец")).parent().$("[class=\"input__control\"]").setValue(card.getCardHolder());
        $(byText("CVC/CVV")).parent().$("[class=\"input__control\"]").setValue(card.getCvv());
        $$("button").find(exactText("Продолжить")).click();
    }

    public void waitOperationIsApproved() {
        $(byText("Операция одобрена Банком.")).parent().$("[class=\"notification__content\"]").shouldBe(visible, Duration.ofSeconds(10));
        $$("[class=\"icon-button__text\"]").first().click();
    }

    public void waitOperationIsCanceled() {
        $(byText("Ошибка! Банк отказал в проведении операции.")).parent().$("[class=\"notification__content\"]").shouldBe(visible, Duration.ofSeconds(10));
    }

    public void waitFormatIsInvalid() {
        $(byText("Неверный формат")).shouldBe(visible, Duration.ofSeconds(10));
    }

    public void waitCardExpirationDateIsIncorrect() {
        $(byText("Неверно указан срок действия карты")).shouldBe(visible, Duration.ofSeconds(10));
    }

    public void waitTheCardHasExpired() {
        $(byText("Истёк срок действия карты")).shouldBe(visible, Duration.ofSeconds(10));
    }

    public void waitWrongFormat() {
        $(byText("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(15));
    }
}
