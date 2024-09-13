package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {

    public static String getMonthInFuture() {
        return LocalDate.now().plusMonths(2).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getYearInPast(int yearCount) {
        return LocalDate.now().minusYears(yearCount).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getYearInFuture(int yearCount) {
        return LocalDate.now().plusYears(yearCount).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static CardInfo generateCardApproved() {
        Faker faker = new Faker();
        return new CardInfo(
                "4444444444444441",
                getMonthInFuture(),
                getYearInFuture(1),
                faker.name().fullName(),
                faker.number().digits(3)
        );
    }

    public static CardInfo generateCardDeclined() {
        Faker faker = new Faker();
        return new CardInfo(
                "4444444444444442",
                getMonthInFuture(),
                getYearInFuture(1),
                faker.name().fullName(),
                faker.number().digits(3)
        );
    }

    public static CardInfo generateCardNumber14Symbols() {
        Faker faker = new Faker();
        return new CardInfo(
                faker.number().digits(14),
                getMonthInFuture(),
                getYearInFuture(1),
                faker.name().fullName(),
                faker.number().digits(3)
        );
    }

    public static CardInfo generateCardFakeNumber() {
        Faker faker = new Faker();
        return new CardInfo(
                "3333333333333333",
                getMonthInFuture(),
                getYearInFuture(1),
                faker.name().fullName(),
                faker.number().digits(3)
        );
    }

    public static CardInfo generateCardWithEmptyNumber() {
        Faker faker = new Faker();
        return new CardInfo(
                "",
                getMonthInFuture(),
                getYearInFuture(1),
                faker.name().fullName(),
                faker.number().digits(3)
        );
    }

    public static CardInfo generateCardWithEmptyMonth() {
        Faker faker = new Faker();
        return new CardInfo(
                "4444444444444441",
                "",
                getYearInFuture(1),
                faker.name().fullName(),
                faker.number().digits(3)
        );
    }

    public static CardInfo generateCardWithEmptyYear() {
        Faker faker = new Faker();
        return new CardInfo(
                "4444444444444441",
                getMonthInFuture(),
                "",
                faker.name().fullName(),
                faker.number().digits(3)
        );
    }

    public static CardInfo generateCardWithEmptyOwner() {
        Faker faker = new Faker();
        return new CardInfo(
                "4444444444444441",
                getMonthInFuture(),
                getYearInFuture(1),
                "",
                faker.number().digits(3)
        );
    }

    public static CardInfo generateCardWithEmptyCvv() {
        Faker faker = new Faker();
        return new CardInfo(
                "4444444444444441",
                getMonthInFuture(),
                getYearInFuture(1),
                faker.name().fullName(),
                ""
        );
    }

    public static CardInfo generateCardMonthOneSymbol() {
        Faker faker = new Faker();
        return new CardInfo(
                "4444444444444441",
                "2",
                getYearInFuture(1),
                faker.name().fullName(),
                faker.number().digits(3)
        );
    }

    public static CardInfo generateCardMonthOver12() {
        Faker faker = new Faker();
        return new CardInfo(
                "4444444444444441",
                "13",
                getYearInFuture(1),
                faker.name().fullName(),
                faker.number().digits(3)
        );
    }

    public static CardInfo generateCardMonthNull() {
        Faker faker = new Faker();
        return new CardInfo(
                "4444444444444441",
                "00",
                LocalDate.now().format(DateTimeFormatter.ofPattern("yy")),
                faker.name().fullName(),
                faker.number().digits(3)
        );
    }

    public static CardInfo generateCardYearOneSymbol() {
        Faker faker = new Faker();
        return new CardInfo(
                "4444444444444441",
                getMonthInFuture(),
                "2",
                faker.name().fullName(),
                faker.number().digits(3)
        );
    }

    public static CardInfo generateCardYearInFuture() {
        Faker faker = new Faker();
        return new CardInfo(
                "4444444444444441",
                getMonthInFuture(),
                getYearInFuture(6),
                faker.name().fullName(),
                faker.number().digits(3)
        );
    }

    public static CardInfo generateCardPreviousYear() {
        Faker faker = new Faker();
        return new CardInfo(
                "4444444444444441",
                getMonthInFuture(),
                getYearInPast(1),
                faker.name().fullName(),
                faker.number().digits(3)
        );
    }

    public static CardInfo generateCardYearNull() {
        Faker faker = new Faker();
        return new CardInfo(
                "4444444444444441",
                getMonthInFuture(),
                "00",
                faker.name().fullName(),
                faker.number().digits(3)
        );
    }

    public static CardInfo generateCardCvvConsistOfSingleDigit() {
        Faker faker = new Faker();
        return new CardInfo(
                "4444444444444441",
                getMonthInFuture(),
                getYearInFuture(1),
                faker.name().fullName(),
                faker.number().digits(1)
        );
    }

    public static CardInfo generateCardCvvConsistOfTwoDigit() {
        Faker faker = new Faker();
        return new CardInfo(
                "4444444444444441",
                getMonthInFuture(),
                getYearInFuture(1),
                faker.name().fullName(),
                faker.number().digits(2)
        );
    }

    public static CardInfo generateCardHolderOneWord() {
        Faker faker = new Faker();
        return new CardInfo(
                "4444444444444441",
                getMonthInFuture(),
                getYearInFuture(1),
                faker.name().firstName(),
                faker.number().digits(3)
        );
    }

    public static CardInfo generateCardHolderCyrillic() {
        Faker faker = new Faker(new Locale("ru"));
        return new CardInfo(
                "4444444444444441",
                getMonthInFuture(),
                getYearInFuture(1),
                faker.name().fullName(),
                faker.number().digits(3)
        );
    }

    public static CardInfo generateCardHolderDigits() {
        Faker faker = new Faker();
        return new CardInfo(
                "4444444444444441",
                getMonthInFuture(),
                getYearInFuture(1),
                faker.name().firstName() + " " + faker.number().digit(),
                faker.number().digits(3)
        );
    }

    public static CardInfo generateCardHolderSpecialSymbols() {
        Faker faker = new Faker();
        return new CardInfo(
                "4444444444444441",
                getMonthInFuture(),
                getYearInFuture(1),
                faker.name().firstName() + " %$ * &",
                faker.number().digits(3)
        );
    }

}