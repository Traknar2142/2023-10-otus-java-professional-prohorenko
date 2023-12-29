package ru.otus.model;

import ru.otus.enums.Denomination;

public class TwoThousandBanknote extends Banknote {
    @Override
    Denomination getDenomination() {
        return Denomination.TWO_THOUSAND;
    }

    @Override
    int getDenominationValue() {
        return Denomination.TWO_THOUSAND.getValue();
    }
}
