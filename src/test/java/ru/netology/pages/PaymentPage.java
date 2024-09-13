package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private final SelenideElement heading = $$("h2").find(Condition.text("Путешествие дня"));
    private final SelenideElement buyButton = $$("button").find(exactText("Купить"));
    private final SelenideElement creditButton = $$("button").find(exactText("Купить в кредит"));

    public PaymentPage() {
        heading.shouldBe(visible);
    }

    public BuyingPage goToBuyPage() {
        buyButton.click();
        return new BuyingPage();
    }

    public CreditPage goToCreditPage() {
        creditButton.click();
        return new CreditPage();
    }
}
