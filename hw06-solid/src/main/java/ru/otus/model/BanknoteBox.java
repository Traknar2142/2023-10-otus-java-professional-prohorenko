package ru.otus.model;

import java.util.List;
import java.util.Map;
import ru.otus.enums.Denomination;

public interface BanknoteBox {
    void insertBanknotes(List<Banknote> banknotes);

    List<Banknote> getBanknotes(int sumOfMoney);

    Map<Denomination, Integer> getCashInfo();
}
