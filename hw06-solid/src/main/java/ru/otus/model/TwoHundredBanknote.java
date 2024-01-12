package ru.otus.model;

import ru.otus.enums.Denomination;

public class TwoHundredBanknote extends Banknote {
    @Override
    public Denomination getDenomination() {
        return Denomination.TWO_HUNDRED;
    }

    @Override
    public int getDenominationValue() {
        return Denomination.TWO_HUNDRED.getValue();
    }
}
