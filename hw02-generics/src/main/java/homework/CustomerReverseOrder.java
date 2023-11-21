package homework;

import java.util.ArrayDeque;
import java.util.Deque;

public class CustomerReverseOrder {
    private static final Deque<Customer> CUSTOMERS = new ArrayDeque<>();

    public void add(Customer customer) {
        CUSTOMERS.push(customer);
    }

    public Customer take() {
        return CUSTOMERS.pop();
    }
}
