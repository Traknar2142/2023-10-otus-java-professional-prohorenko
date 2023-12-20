package homework;

import java.util.Map;
import java.util.TreeMap;

public class CustomerService {
    private static final TreeMap<Customer, String> TREE_MAP = new TreeMap<>();

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> entry = TREE_MAP.entrySet().iterator().next();
        return getNewEntry(entry);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> entry = TREE_MAP.higherEntry(customer);
        if (entry == null) {
            return null;
        }
        return getNewEntry(entry);
    }

    public void add(Customer customer, String data) {
        TREE_MAP.put(customer, data);
    }

    private Map.Entry<Customer, String> getNewEntry(Map.Entry<Customer, String> entry) {
        Customer key = entry.getKey();
        return Map.entry(new Customer(key.getId(), key.getName(), key.getScores()), entry.getValue());
    }
}
