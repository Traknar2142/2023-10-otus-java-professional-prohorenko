package ru.otus.model;

import ru.otus.enums.Denomination;

public class TwoHundredBanknote extends Banknote{
    @Override
    Denomination getDenomination() {
        return Denomination.TWO_HUNDRED;
    }

    @Override
    int getDenominationValue() {
        return Denomination.TWO_HUNDRED.getValue();
    }
}
