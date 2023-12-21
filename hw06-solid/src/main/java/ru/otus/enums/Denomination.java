package ru.otus.enums;

public enum Denomination {
    FIFTY(mnemonic, denomination),
    ONE_HUNDRED(mnemonic, denomination),
    TWO_HUNDRED(mnemonic, denomination),
    FIVE_HUNDRED(mnemonic, denomination),
    ONE_THOUSAND(mnemonic, denomination),
    TWO_THOUSAND(mnemonic, denomination),
    FIVE_THOUSAND(mnemonic, denomination);

    private final String mnemonic;
    private final int denomination;

    Denomination(String mnemonic, int denomination) {
        this.mnemonic = mnemonic;
        this.denomination = denomination;
    }
}
