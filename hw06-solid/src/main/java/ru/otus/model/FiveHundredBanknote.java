package ru.otus.model;

import ru.otus.enums.Denomination;

public class FiveHundredBanknote extends Banknote{
    @Override
    Denomination getDenomination() {
        return Denomination.FIVE_HUNDRED;
    }

    @Override
    int getDenominationValue() {
        return Denomination.FIVE_HUNDRED.getValue();
    }
}
