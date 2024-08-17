package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;
import java.util.Random;

public class DataHelper {

    private static final Faker fake = new Faker(new Locale("ru"));

    private DataHelper() {
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    public static int getPossibleAmount(int balance) {

        return new Random().nextInt(balance) + 1;
    }

    public static int getImpossibleAmount(int balance) {

        return Math.abs(balance) + new Random().nextInt(10000);
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya","qwerty123");
    }
    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya","123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor() {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
    }

    public static CardInfo getCardNumber1() {
        return new CardInfo("5559000000000001");
    }
    public static CardInfo getCardNumber2() {
        return new CardInfo("5559000000000002");
    }
}
