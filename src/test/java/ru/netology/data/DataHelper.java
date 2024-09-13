package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    public static CardInfo getApprovedCard() {
        return new CardInfo("4444444444444441", "12", getMovedYearInFuture(1), "Nelli Shigapova", "123");
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo("4444444444444442", "12", getMovedYearInFuture(1), "Nelli Shigapova", "123");
    }

    public static CardInfo getEmptyCard() {
        return new CardInfo("", "", "", "", "");
    }

    public static String getMovedMonth() {
        int shift = (int) (Math.random() * 10);
        return LocalDate.now().plusMonths(shift).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getMovedYearInPast(int yearCount) {
        return LocalDate.now().minusYears(yearCount).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getMovedYearInFuture(int yearCount) {
        return LocalDate.now().plusYears(yearCount).format(DateTimeFormatter.ofPattern("yy"));
    }


    public static CardInfo getNumberCard12Symbols() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMovedMonth();
        String year = getMovedYearInFuture(1);
        String cvv = faker.number().digits(5);
        String number = faker.number().digits(12);
        return new CardInfo(number, month, year, holder, cvv);
    }

    public static CardInfo getCardNotInDatabase() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMovedMonth();
        String year = getMovedYearInFuture(1);
        String cvv = faker.number().digits(3);
        return new CardInfo("3333333333333333", month, year, holder, cvv);
    }

    public static CardInfo getCardWithEmptyNumber() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMovedMonth();
        String year = getMovedYearInFuture(1);
        String cvv = faker.number().digits(3);
        return new CardInfo("", month, year, holder, cvv);
    }

    public static CardInfo getCardWithEmptyMonth() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getMovedYearInFuture(1);
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", "", year, holder, cvv);
    }

    public static CardInfo getCardWithEmptyYear() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMovedMonth();
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, "", holder, cvv);
    }

    public static CardInfo getCardWithEmptyOwner() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMovedMonth();
        String cvv = faker.number().digits(3);
        String year = getMovedYearInFuture(1);
        return new CardInfo("4444444444444441", month, year, "", cvv);
    }

    public static CardInfo getCardWithEmptyCvv() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMovedMonth();
        String cvv = faker.number().digits(3);
        String year = getMovedYearInFuture(1);
        return new CardInfo("4444444444444441", month, year, holder, "");
    }

    public static CardInfo getCardMonth1Symbol() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = faker.number().digit();
        String year = getMovedYearInFuture(1);
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, holder, cvv);
    }

    public static CardInfo getCardMonthOver12() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getMovedYearInFuture(1);
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", "13", year, holder, cvv);
    }

    public static CardInfo getCardMonth00ThisYear() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", "00", year, holder, cvv);
    }

    public static CardInfo getCardMonth00AndFutureYear() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getMovedYearInFuture(1);
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", "00", year, holder, cvv);
    }

    public static CardInfo getCardYear1Symbol() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMovedMonth();
        String year = faker.number().digit();
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, holder, cvv);
    }

    public static CardInfo getCardYearOverThisYearOn6() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMovedMonth();
        String year = getMovedYearInFuture(6);
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, holder, cvv);
    }

    public static CardInfo getCardYearUnderThisYear() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMovedMonth();
        String year = getMovedYearInPast(1);
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, holder, cvv);
    }

    public static CardInfo getCardYear00() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMovedMonth();
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, "00", holder, cvv);
    }

    public static CardInfo getCardCvv1Symbol() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMovedMonth();
        String year = getMovedYearInFuture(1);
        String cvv = faker.number().digits(1);
        return new CardInfo("4444444444444441", month, year, holder, cvv);
    }

    public static CardInfo getCardCvv2Symbols() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMovedMonth();
        String year = getMovedYearInFuture(1);
        String cvv = faker.number().digits(2);
        return new CardInfo("4444444444444441", month, year, holder, cvv);
    }

    public static CardInfo getCardHolder1Word() {
        Faker faker = new Faker();
        String holder = faker.name().firstName();
        String month = getMovedMonth();
        String year = getMovedYearInFuture(1);
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, holder, cvv);
    }

    public static CardInfo getCardHolderCirillic() {
        Faker faker = new Faker(new Locale("ru"));
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMovedMonth();
        String year = getMovedYearInFuture(1);
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, holder, cvv);
    }

    public static CardInfo getCardHolderNumeric() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.number().digit();
        String month = getMovedMonth();
        String year = getMovedYearInFuture(1);
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, holder, cvv);
    }

    public static CardInfo getCardSpecialSymbols() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " %$ * &";
        String month = getMovedMonth();
        String year = getMovedYearInFuture(1);
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, holder, cvv);
    }
}