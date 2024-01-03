package ru.otus.model;

import ru.otus.enums.Denomination;
import ru.otus.exception.NoMoneyException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BanknoteBoxImpl implements BanknoteBox {
    private static final Map<Denomination, List<Banknote>> cashHolder = new TreeMap<>();

    @Override
    public void insertBanknotes(List<Banknote> banknotes) {
        banknotes.forEach(this::putBanknote);
    }

    @Override
    public List<Banknote> getBanknotes(int sumOfMoney) {
        List<Banknote> result = new ArrayList<>();

        for (Map.Entry<Denomination, List<Banknote>> entry : cashHolder.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                List<Banknote> value = entry.getValue();
                for (Banknote banknote : value) {
                    sumOfMoney = sumOfMoney - banknote.getDenominationValue();
                    if (sumOfMoney >= 0) {
                        result.add(banknote);
                    } else break;
                }
            }
            if (sumOfMoney == 0) break;
        }

        return result;
    }

    @Override
    public Map<Denomination, Integer> getCashInfo() {
        if (!cashHolder.isEmpty()){
            Map<Denomination, Integer> cashInfo = new HashMap<>();
            for (Map.Entry<Denomination, List<Banknote>> entry : cashHolder.entrySet()){
                cashInfo.put(entry.getKey(),entry.getValue().size());
            }
            return cashInfo;
        }
        throw new NoMoneyException("Atm has no money");
    }

    private void putBanknote(Banknote banknote) {
        if (!cashHolder.containsKey(banknote.getDenomination())) {
            cashHolder.put(banknote.getDenomination(), new ArrayList<>());
        }
        cashHolder.get(banknote.getDenomination()).add(banknote);
    }
}
