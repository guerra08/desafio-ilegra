package service;

import config.Characters;
import domain.Salesman;
import lombok.Getter;
import lombok.Setter;
import repository.SalesmanRepository;

import java.util.Comparator;
import java.util.Map;

public class SalesmanService extends Service{

    private static SalesmanRepository salesmanRepository = new SalesmanRepository();

    private SaleService saleService = new SaleService();

    @Getter
    @Setter
    private static Map.Entry<String, Double> worstSalesmanEver = null;

    @Getter
    @Setter
    private static int salesmenFromInputFile = 0;


    public boolean addFromProcessedData(String[] data){
        salesmenFromInputFile++;
        return salesmanRepository.save(Salesman.builder().cnpj(data[1]).name(data[2]).salary(Double.parseDouble(data[3])).build());
    }

    public boolean addSalesman(Salesman s){
        salesmenFromInputFile++;
        return salesmanRepository.save(s);
    }

    public int getSize(){
        return salesmanRepository.size();
    }

    public String generateOutputString(){
        updateWorstSalesmanEver();
        return "SalesmanFromInput - " + salesmenFromInputFile + Characters.NEW_LINE + "WorstSalesmanEver - " + getWorstSalesmanEver().getKey() + Characters.NEW_LINE;
    }

    public void refresh(){
        salesmenFromInputFile = 0;
    }

    private void updateWorstSalesmanEver(){
        Map.Entry<String, Double> worstFromRepo = saleService.mapSalesToSalesman().entrySet().stream().
                min(Comparator.comparingDouble(Map.Entry::getValue)).orElse(null);
        if(worstSalesmanEver == null) worstSalesmanEver = worstFromRepo;
        else if(worstFromRepo != null && worstFromRepo.getValue() < worstSalesmanEver.getValue()){
            worstSalesmanEver = worstFromRepo;
        }
    }

}
