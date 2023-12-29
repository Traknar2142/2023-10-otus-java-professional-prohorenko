package ru.otus.model;

import ru.otus.enums.Denomination;

public abstract class Banknote {
    abstract Denomination getDenomination();
    abstract int getDenominationValue();
}
