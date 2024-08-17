package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferMoney {
    SelenideElement amountInput = $("[data-test-id=amount] input");
    SelenideElement fromInput = $("[data-test-id=from] input");
    SelenideElement transferButton = $("[data-test-id=action-transfer]");
    SelenideElement errorMessage = $("[data-test-id=error-notification]");

    public void transferMoney(DataHelper.CardInfo cardNumber, String amount) {
        amountInput.setValue(amount);
        fromInput.setValue(cardNumber.getCardNumber());
        transferButton.click();
    }

    public DashboardPage makeValidTransfer(DataHelper.CardInfo cardNumber, String amount) {
        transferMoney(cardNumber, amount);
        return new DashboardPage();
    }
}
