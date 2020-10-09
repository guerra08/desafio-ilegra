package service;

import domain.Customer;
import repository.CustomerRepository;

public class CustomerService {

    private static CustomerRepository customerRepository = new CustomerRepository();

    public static boolean addCustomer(Customer c){
        return customerRepository.saveCustomer(c);
    }

    public static void refresh(){
        customerRepository = new CustomerRepository();
    }

}
