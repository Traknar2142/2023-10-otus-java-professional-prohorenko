package ru.otus.enums;

public enum Denomination {
    FIFTY("FIFTY", 50),
    ONE_HUNDRED("ONE_HUNDRED", 100),
    TWO_HUNDRED("TWO_HUNDRED", 200),
    FIVE_HUNDRED("FIVE_HUNDRED", 500),
    ONE_THOUSAND("ONE_THOUSAND", 1000),
    TWO_THOUSAND("TWO_THOUSAND", 2000),
    FIVE_THOUSAND("FIVE_THOUSAND", 5000);

    private final String mnemonic;
    private final int denomination;

    Denomination(String mnemonic, int denomination) {
        this.mnemonic = mnemonic;
        this.denomination = denomination;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public int getValue() {
        return denomination;
    }
}
