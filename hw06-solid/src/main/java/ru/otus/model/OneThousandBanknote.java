package ru.otus.model;

import ru.otus.enums.Denomination;

public class OneThousandBanknote extends Banknote {
    @Override
    public Denomination getDenomination() {
        return Denomination.ONE_THOUSAND;
    }

    @Override
    public int getDenominationValue() {
        return Denomination.ONE_THOUSAND.getValue();
    }
}
