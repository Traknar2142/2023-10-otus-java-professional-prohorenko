package ru.otus.model;

import ru.otus.enums.Denomination;

public class FiveThousandBanknote extends Banknote{
    @Override
    Denomination getDenomination() {
        return Denomination.FIVE_THOUSAND;
    }

    @Override
    int getDenominationValue() {
        return Denomination.FIVE_THOUSAND.getValue();
    }
}
