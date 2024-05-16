package ru.otus.model;

import ru.otus.enums.Denomination;

public class FiveHundredBanknote extends Banknote {
    @Override
    public Denomination getDenomination() {
        return Denomination.FIVE_HUNDRED;
    }

    @Override
    public int getDenominationValue() {
        return Denomination.FIVE_HUNDRED.getValue();
    }
}
