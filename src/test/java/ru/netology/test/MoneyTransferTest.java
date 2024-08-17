package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {

    DataHelper.CardInfo firstCardInfo;
    DataHelper.CardInfo secondCardInfo;
    int firstCardBalance;
    int secondCardBalance;
    DashboardPage dashboardPage;
    @BeforeEach
    void setup() {
        var loginPage = open("http://0.0.0.0:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor();
        var verificationPage = loginPage.validLogin(authInfo);
        dashboardPage = verificationPage.validVerify(verificationCode);
        firstCardInfo = DataHelper.getCardNumber1();
        secondCardInfo = DataHelper.getCardNumber2();
        firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
        secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
    }

    @Test
    void shouldTransferMoneyBetweenCards() {

        var amount = DataHelper.getPossibleAmount(firstCardBalance);
        var transferMoney = dashboardPage.cardToTransfer(secondCardInfo);
        dashboardPage = transferMoney.makeValidTransfer(firstCardInfo, String.valueOf(amount));
        var actualBalance1 = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalance2 = dashboardPage.getCardBalance(secondCardInfo);
        Assertions.assertEquals(firstCardBalance - amount, actualBalance1);
        Assertions.assertEquals(secondCardBalance + amount, actualBalance2);

    }


}
