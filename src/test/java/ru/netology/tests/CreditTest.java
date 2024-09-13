package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataBaseUtils;
import ru.netology.data.DataGenerator;
import ru.netology.pages.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditTest {
    public static String url = System.getProperty("sut.url");

    private

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        open(url);
    }

    @Test
    void testUICardApproved() {
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardApproved());
        paymentPage.clickContinueButton();
        paymentPage.testOperationIsApproved();
    }

    @Test
    void testDBCardApproved() {
        DataBaseUtils dataBaseUtils = new DataBaseUtils();
        dataBaseUtils.clearDB();
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardApproved());
        paymentPage.clickContinueButton();
        paymentPage.waitRequest();
        String status = dataBaseUtils.getCreditStatus();
        dataBaseUtils.clearDB();
        dataBaseUtils.close();
        assertEquals("APPROVED", status);
    }

    @Test
    void testUICardDeclined() {
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardDeclined());
        paymentPage.clickContinueButton();
        paymentPage.testOperationIsCanceled();
    }

    @Test
    void testDBCardDeclined() {
        DataBaseUtils dataBaseUtils = new DataBaseUtils();
        dataBaseUtils.clearDB();
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardDeclined());
        paymentPage.clickContinueButton();
        paymentPage.waitRequest();
        String status = dataBaseUtils.getCreditStatus();
        dataBaseUtils.clearDB();
        dataBaseUtils.close();
        assertEquals("DECLINED", status);
    }

    @Test
    void testUICardShouldNotBe14Symbols() {
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardNumber14Symbols());
        paymentPage.clickContinueButton();
        paymentPage.testFormatIsInvalid();
    }

    @Test
    void testUICardIsNotBeInDB() {
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardFakeNumber());
        paymentPage.clickContinueButton();
        paymentPage.testOperationIsCanceled();
    }

    @Test
    void testDBCardIsNotBeInDB() {
        DataBaseUtils dataBaseUtils = new DataBaseUtils();
        dataBaseUtils.clearDB();
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardFakeNumber());
        paymentPage.clickContinueButton();
        paymentPage.waitRequest();
        String orderCount = dataBaseUtils.getOrderCount();
        dataBaseUtils.clearDB();
        dataBaseUtils.close();
        assertEquals("0", orderCount);
    }

    @Test
    void testUIMonthShouldNotBeOneSymbol() {
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardMonthOneSymbol());
        paymentPage.clickContinueButton();
        paymentPage.testFormatIsInvalid();
    }

    @Test
    void testUIMonthShouldNotBeOver12() {
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardMonthOver12());
        paymentPage.clickContinueButton();
        paymentPage.testCardExpirationDateIsIncorrect();
    }

    @Test
    void testUIMonthShouldNotBeNull() {
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardMonthNull());
        paymentPage.clickContinueButton();
        paymentPage.testCardExpirationDateIsIncorrect();
    }

    @Test
    void testUINumberShouldNotBeEmpty() {
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardWithEmptyNumber());
        paymentPage.clickContinueButton();
        paymentPage.testFormatIsInvalid();
    }

    @Test
    void testUIMonthShouldNotBeEmpty() {
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardWithEmptyMonth());
        paymentPage.clickContinueButton();
        paymentPage.testFormatIsInvalid();
    }

    @Test
    void testUIYearShouldNotBeEmpty() {
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardWithEmptyYear());
        paymentPage.clickContinueButton();
        paymentPage.testFormatIsInvalid();
    }

    @Test
    void testUIOwnerShouldNotBeEmpty() {
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardWithEmptyOwner());
        paymentPage.clickContinueButton();
        paymentPage.testFieldShouldBeFilled();
    }

    @Test
    void testUICvvShouldNotBeEmpty() {
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardWithEmptyCvv());
        paymentPage.clickContinueButton();
        paymentPage.testFormatIsInvalid();
    }

    @Test
    void testUIYearShouldNotBeNull() {
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardYearNull());
        paymentPage.clickContinueButton();
        paymentPage.testCardExpired();
    }

    @Test
    void testUIYearShouldNotBeOneSymbol() {
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardYearOneSymbol());
        paymentPage.clickContinueButton();
        paymentPage.testFormatIsInvalid();
    }

    @Test
    void testUIYearShouldNotBePrevious() {
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardPreviousYear());
        paymentPage.clickContinueButton();
        paymentPage.testCardExpired();
    }

    @Test
    void testUIYearShouldNotBeInFuture() {
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardYearInFuture());
        paymentPage.clickContinueButton();
        paymentPage.testCardExpirationDateIsIncorrect();
    }

    @Test
    void testUICvvShouldNotBeConsistOfSingleDigit() {
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardCvvConsistOfSingleDigit());
        paymentPage.clickContinueButton();
        paymentPage.testFormatIsInvalid();
    }

    @Test
    void testUICvvShouldNotBeConsistOfTwoDigits() {
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardCvvConsistOfTwoDigit());
        paymentPage.clickContinueButton();
        paymentPage.testFormatIsInvalid();
    }

    @Test
    void testUIOwnerShouldNotBeOneWord() {
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardHolderOneWord());
        paymentPage.clickContinueButton();
        paymentPage.testFormatIsInvalid();
    }

    @Test
    void testUIOwnerShouldNotHaveCyrillicSymbols() {
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardHolderCyrillic());
        paymentPage.clickContinueButton();
        paymentPage.testFormatIsInvalid();
    }

    @Test
    void testUIOwnerShouldNotHaveDigits() {
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardHolderDigits());
        paymentPage.clickContinueButton();
        paymentPage.testFormatIsInvalid();
    }

    @Test
    void testUIOwnerShouldNotHaveSpecialSymbols() {
        val startPage = new StartPage();
        val paymentPage = startPage.clickCreditButton();
        paymentPage.fillCardInfo(DataGenerator.generateCardHolderSpecialSymbols());
        paymentPage.clickContinueButton();
        paymentPage.testFormatIsInvalid();
    }
}
