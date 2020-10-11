package service;

import config.Characters;
import config.Identifiers;
import domain.Salesman;
import lombok.Getter;
import lombok.Setter;
import repository.SalesmanRepository;

import java.util.Map;

public class SalesmanService implements Service<Salesman>{

    private final SalesmanRepository salesmanRepository = new SalesmanRepository();

    @Getter
    @Setter
    private Map.Entry<String, Double> worstSalesmanEver = null;

    @Getter
    private int salesmenFromInputFile = 0;


    public boolean addFromProcessedData(String[] data){
        if(!data[0].equals(Identifiers.SALESMAN_ID)) return false;
        salesmenFromInputFile++;
        return salesmanRepository.save(Salesman.builder().cnpj(data[1]).name(data[2]).salary(Double.parseDouble(data[3])).build());
    }

    public boolean add(Salesman s){
        salesmenFromInputFile++;
        return salesmanRepository.save(s);
    }

    public int getSize(){
        return salesmanRepository.size();
    }

    public String generateOutputString(){
        return "SalesmanFromInput - " + salesmenFromInputFile + Characters.NEW_LINE;
    }

    /**
     * Refreshes the service after processing a file.
     */
    public void refresh(){
        salesmenFromInputFile = 0;
    }

}
