package ru.otus.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import ru.otus.enums.Denomination;
import ru.otus.exception.NoMoneyException;

public class BanknoteBoxImpl implements BanknoteBox {
    private final Map<Denomination, List<Banknote>> cashHolder = new TreeMap<>();

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
        if (sumOfMoney != 0) {
            throw new NoMoneyException("Недостаточно денег");
        }

        return result;
    }

    @Override
    public Map<Denomination, Integer> getCashInfo() {
        if (!cashHolder.isEmpty()) {
            Map<Denomination, Integer> cashInfo = new EnumMap<>(Denomination.class);
            for (Map.Entry<Denomination, List<Banknote>> entry : cashHolder.entrySet()) {
                cashInfo.put(entry.getKey(), entry.getValue().size());
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
