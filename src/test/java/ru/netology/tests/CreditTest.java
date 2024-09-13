package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlHelper;
import ru.netology.pages.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditTest {
    public static String url = System.getProperty("sut.url");

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openPage() {
        open(url);
    }

    @AfterEach
    public void cleanBase() {
        SqlHelper.clearDB();
    }

    @Test
    void shouldBeAllFieldValidApproved() {
        val startPage = new PaymentPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getApprovedCard());
        payment.waitOperationIsApproved();
        assertEquals("APPROVED", SqlHelper.getCreditRequestStatus());
    }

    @Test
    void shouldBeAllFieldValidDeclined() {
        val startPage = new PaymentPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getDeclinedCard());
        payment.waitOperationIsCanceled();
        assertEquals("DECLINED", SqlHelper.getCreditRequestStatus());
    }

    @Test
    void shouldNotBeNumberCard12Symbols() {
        val startPage = new PaymentPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getNumberCard12Symbols());
        payment.waitFormatIsInvalid();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void shouldNotBeCardNotInDatabase() {
        val startPage = new PaymentPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardNotInDatabase());
        payment.waitOperationIsCanceled();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void shouldNotBeMonth1Symbol() {
        val startPage = new PaymentPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardMonth1Symbol());
        payment.waitFormatIsInvalid();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void shouldNotBeMonthOver12() {
        val startPage = new PaymentPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardMonthOver12());
        payment.waitCardExpirationDateIsIncorrect();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void shouldNotBeMonth00ThisYear() {
        val startPage = new PaymentPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardMonth00ThisYear());
        payment.waitCardExpirationDateIsIncorrect();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void shouldNotBeEmptyNumber() {
        val startPage = new PaymentPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardWithEmptyNumber());
        payment.waitFormatIsInvalid();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void shouldNotBeMonthEmpty() {
        val startPage = new PaymentPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardWithEmptyMonth());
        payment.waitFormatIsInvalid();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void shouldNotBeYearEmpty() {
        val startPage = new PaymentPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardWithEmptyYear());
        payment.waitFormatIsInvalid();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void shouldNotBeOwnerEmpty() {
        val startPage = new PaymentPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardWithEmptyOwner());
        payment.waitWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void shouldNotBeCvvEmpty() {
        val startPage = new PaymentPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardWithEmptyCvv());
        payment.waitFormatIsInvalid();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void shouldNotBeYear00() {
        val startPage = new PaymentPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardYear00());
        payment.waitTheCardHasExpired();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void shouldNotBeYear1Symbol() {
        val startPage = new PaymentPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardYear1Symbol());
        payment.waitFormatIsInvalid();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void shouldNotBeYearUnderThisYear() {
        val startPage = new PaymentPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardYearUnderThisYear());
        payment.waitTheCardHasExpired();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void shouldNotBeYearOverThisYearOn6() {
        val startPage = new PaymentPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardYearOverThisYearOn6());
        payment.waitCardExpirationDateIsIncorrect();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void shouldNotBeCvv1Symbol() {
        val startPage = new PaymentPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardCvv1Symbol());
        payment.waitFormatIsInvalid();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void shouldNotBeCvv2Symbols() {
        val startPage = new PaymentPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardCvv2Symbols());
        payment.waitFormatIsInvalid();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void shouldNotBeOwner1Word() {
        val startPage = new PaymentPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardHolder1Word());
        payment.waitFormatIsInvalid();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void shouldNotBeOwnerCirillic() {
        val startPage = new PaymentPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardHolderCirillic());
        payment.waitFormatIsInvalid();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void shouldNotBeOwnerNumeric() {
        val startPage = new PaymentPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardHolderNumeric());
        payment.waitFormatIsInvalid();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void shouldNotBeOwnerSpecialSymbols() {
        val startPage = new PaymentPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardSpecialSymbols());
        payment.waitFormatIsInvalid();
        assertEquals("0", SqlHelper.getOrderCount());
    }
}
