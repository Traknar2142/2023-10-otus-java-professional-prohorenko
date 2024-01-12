package ru.otus.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.enums.Denomination;
import ru.otus.exception.NoMoneyException;

/**
 * Не стал расписывать тесты на каждый компонент.
 * Здесь демонстрация а-ля функционала atm в котором уже есть отсек с купюрами
 */
class AtmTest {
    private Atm atm;

    @BeforeEach
    void resetAtm() {
        BanknoteBox banknoteBox = new BanknoteBoxImpl();
        atm = new AtmImpl(banknoteBox);
        FiftyBanknote fiftyBanknote = new FiftyBanknote();
        FiveHundredBanknote fiveHundredBanknote = new FiveHundredBanknote();
        FiveThousandBanknote fiveThousandBanknote = new FiveThousandBanknote();
        List<Banknote> banknotes =
                new ArrayList<>(Arrays.asList(fiftyBanknote, fiveHundredBanknote, fiveThousandBanknote));
        atm.insertBanknotes(banknotes);
    }

    @Test
    @DisplayName("должна выдать баланс 5550")
    void showBalanceTest() {
        int balance = atm.showBalance();
        assertThat(balance).isEqualTo(5550);
    }

    @Test
    @DisplayName("должна добавлять купюру, номиналом 50 и выводить баланс 5600")
    void insertBanknoteTest() {
        FiftyBanknote fiftyBanknote = new FiftyBanknote();
        List<Banknote> banknotes = new ArrayList<>(List.of(fiftyBanknote));
        atm.insertBanknotes(banknotes);
        int balance = atm.showBalance();
        assertThat(balance).isEqualTo(5600);
    }

    @Test
    @DisplayName("должна выдать купюру номиналом 50")
    void getOneBanknoteTest() {
        List<Banknote> money = atm.getMoney(50);
        Banknote banknote = money.get(0);
        assertThat(Denomination.FIFTY).isEqualTo(banknote.getDenomination());
        assertThat(Denomination.FIFTY.getValue()).isEqualTo(banknote.getDenominationValue());
    }

    @Test
    @DisplayName("должна выдать две купюры номиналом 500 и 50")
    void getTwoBanknotesTest() {
        List<Banknote> money = atm.getMoney(550);
        Banknote fiftyBanknote = money.get(0);
        Banknote fiveHundredBanknote = money.get(1);
        assertThat(Denomination.FIFTY).isEqualTo(fiftyBanknote.getDenomination());
        assertThat(Denomination.FIFTY.getValue()).isEqualTo(fiftyBanknote.getDenominationValue());
        assertThat(Denomination.FIVE_HUNDRED).isEqualTo(fiveHundredBanknote.getDenomination());
        assertThat(Denomination.FIVE_HUNDRED.getValue()).isEqualTo(fiveHundredBanknote.getDenominationValue());
    }

    @Test
    @DisplayName("должна бросить исключение NoMoneyException")
    void getTooManyBanknotesTest() {
        assertThatThrownBy(() -> {
                    atm.getMoney(9999999);
                })
                .isInstanceOf(NoMoneyException.class)
                .hasMessage("Недостаточно денег");
    }
}
