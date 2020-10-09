package service;

import domain.Customer;
import repository.CustomerRepository;

public class CustomerService {

    private static final CustomerRepository customerRepository = new CustomerRepository();

    public static boolean addCustomer(Customer c){
        return customerRepository.saveCustomer(c);
    }

}
