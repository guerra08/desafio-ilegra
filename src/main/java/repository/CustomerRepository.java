package repository;

import domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    private List<Customer> customers = new ArrayList<>();

    /**
     * Adds a new Customer to the repository
     * @param c The new Customer
     * @return boolean
     */
    public boolean saveCustomer(Customer c){
        return customers.add(c);
    }

    /**
     * Returns the amount of customers in the repository
     * @return int
     */
    public int getCustomersSize(){
        return customers.size();
    }

}
