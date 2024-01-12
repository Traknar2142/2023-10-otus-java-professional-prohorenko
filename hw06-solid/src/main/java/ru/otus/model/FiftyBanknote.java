package ru.otus.model;

import ru.otus.enums.Denomination;

public class FiftyBanknote extends Banknote {
    @Override
    public Denomination getDenomination() {
        return Denomination.FIFTY;
    }

    @Override
    public int getDenominationValue() {
        return Denomination.FIFTY.getValue();
    }
}
