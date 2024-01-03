package ru.otus.model;

import ru.otus.enums.Denomination;

public class FiveThousandBanknote extends Banknote{
    @Override
    public Denomination getDenomination() {
        return Denomination.FIVE_THOUSAND;
    }

    @Override
    public int getDenominationValue() {
        return Denomination.FIVE_THOUSAND.getValue();
    }
}
