package ru.otus.model;

import ru.otus.enums.Denomination;

public class OneThousandBanknote extends Banknote {
    @Override
    Denomination getDenomination() {
        return Denomination.ONE_THOUSAND;
    }

    @Override
    int getDenominationValue() {
        return Denomination.ONE_THOUSAND.getValue();
    }
}
