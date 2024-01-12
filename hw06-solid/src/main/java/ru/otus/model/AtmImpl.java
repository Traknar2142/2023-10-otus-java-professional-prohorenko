package ru.otus.model;

import java.util.List;
import java.util.Map;
import ru.otus.enums.Denomination;

public class AtmImpl implements Atm {
    private final BanknoteBox banknoteBox;

    public AtmImpl(BanknoteBox banknoteBox) {
        this.banknoteBox = banknoteBox;
    }

    @Override
    public void insertBanknotes(List<Banknote> banknotes) {
        banknoteBox.insertBanknotes(banknotes);
    }

    @Override
    public List<Banknote> getMoney(int sumOfMoney) {
        return banknoteBox.getBanknotes(sumOfMoney);
    }

    @Override
    public int showBalance() {
        Map<Denomination, Integer> cashInfo = banknoteBox.getCashInfo();
        int result = 0;
        for (Map.Entry<Denomination, Integer> entry : cashInfo.entrySet()) {
            result += entry.getKey().getValue() * entry.getValue();
        }
        return result;
    }
}
