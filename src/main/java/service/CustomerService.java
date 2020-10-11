package service;

import config.Characters;
import config.Identifiers;
import domain.Customer;
import lombok.Getter;
import repository.CustomerRepository;

public class CustomerService extends Service{

    private final CustomerRepository customerRepository = new CustomerRepository();

    @Getter
    private int customersFromInputFile = 0;

    public boolean addFromProcessedData(String[] data){
        if(!data[0].equals(Identifiers.CUSTOMER_ID)) return false;
        customersFromInputFile++;
        return customerRepository.save(Customer.builder().cpf(data[1]).name(data[2]).businessArea(data[3]).build());
    }

    public boolean add(Customer c){
        customersFromInputFile++;
        return customerRepository.save(c);
    }

    public int getSize(){
        return customerRepository.size();
    }

    public String generateOutputString(){
        return "CustomerFromInput - " + customersFromInputFile + Characters.NEW_LINE;
    }

    /**
     * Refreshes the service after processing a file.
     */
    public void refresh(){
        customersFromInputFile = 0;
    }
}
