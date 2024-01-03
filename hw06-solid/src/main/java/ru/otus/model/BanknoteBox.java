package ru.otus.model;

import ru.otus.enums.Denomination;

import java.util.List;
import java.util.Map;

public interface BanknoteBox {
    void insertBanknotes(List<Banknote> banknotes);
    List<Banknote> getBanknotes(int sumOfMoney);
    Map<Denomination, Integer> getCashInfo();
}
