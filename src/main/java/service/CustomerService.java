package service;

import domain.Customer;
import repository.CustomerRepository;

public class CustomerService extends Service{

    public CustomerService(){}

    private static CustomerRepository customerRepository = new CustomerRepository();

    public boolean addFromProcessedData(String[] data){
        return customerRepository.save(Customer.builder().CPF(data[1]).name(data[2]).businessArea(data[3]).build());
    }

    public boolean addCustomer(Customer c){
        return customerRepository.save(c);
    }

    public int getSize(){
        return customerRepository.size();
    }

    public void refresh(){
        customerRepository = new CustomerRepository();
    }

}
