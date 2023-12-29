package ru.otus.model;

import java.util.List;

public interface BanknoteBox {
    void insertBanknotes(List<Banknote> banknotes);
    List<Banknote> getBanknotes(int sumOfMoney);
}
