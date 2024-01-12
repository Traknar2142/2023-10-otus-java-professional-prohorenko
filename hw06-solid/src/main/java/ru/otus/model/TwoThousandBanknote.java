package ru.otus.model;

import ru.otus.enums.Denomination;

public class TwoThousandBanknote extends Banknote {
    @Override
    public Denomination getDenomination() {
        return Denomination.TWO_THOUSAND;
    }

    @Override
    public int getDenominationValue() {
        return Denomination.TWO_THOUSAND.getValue();
    }
}
