package service;

import config.Characters;
import domain.Customer;
import lombok.Getter;
import lombok.Setter;
import repository.CustomerRepository;

public class CustomerService extends Service{

    private static CustomerRepository customerRepository = new CustomerRepository();

    @Getter
    @Setter
    private static int customersFromInputFile = 0;

    public boolean addFromProcessedData(String[] data){
        customersFromInputFile++;
        return customerRepository.save(Customer.builder().cpf(data[1]).name(data[2]).businessArea(data[3]).build());
    }

    public boolean addCustomer(Customer c){
        customersFromInputFile++;
        return customerRepository.save(c);
    }

    public int getSize(){
        return customerRepository.size();
    }

    public String generateOutputString(){
        return "CustomerFromInput - " + customersFromInputFile + Characters.NEW_LINE;
    }

    public void refresh(){
        customersFromInputFile = 0;
    }
}
