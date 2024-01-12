package ru.otus.model;

import java.util.List;

public interface Atm {
    void insertBanknotes(List<Banknote> banknotes);

    List<Banknote> getMoney(int sumOfMoney);

    int showBalance();
}
