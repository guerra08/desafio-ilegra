package service;

import config.Characters;
import config.Identifiers;
import domain.Sale;
import lombok.Getter;
import repository.SaleRepository;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class SaleService extends Service{

    private final SaleRepository saleRepository = new SaleRepository();
    @Getter
    private Sale bestSale = null;
    @Getter
    private Map.Entry<String, Double> worstSalesmanEver = null;

    public boolean addFromProcessedData(String[] data){
        if(!data[0].equals(Identifiers.SALE_ID)) return false;
        Sale s = Sale.builder().saleId(data[1]).soldProducts(data[2]).salesmanName(data[3]).build();
        if(bestSale != null && s.getSalePrice().compareTo(bestSale.getSalePrice()) > 0)
            bestSale = s;
        return saleRepository.save(Sale.builder().saleId(data[1]).soldProducts(data[2]).salesmanName(data[3]).build());
    }

    public boolean add(Sale s){
        if(bestSale != null && s.getSalePrice().compareTo(bestSale.getSalePrice()) > 0)
            bestSale = s;
        return saleRepository.save(s);
    }

    public String generateOutputString(){
        updateBestSaleAndWorstSalesmanEver();
        return "MostExpensiveSale - " + bestSale.getSaleId() + Characters.NEW_LINE + "WorstSalesmanEver - " + worstSalesmanEver.getKey() + Characters.NEW_LINE;
    }

    /**
     * Returns a Map containing the Salesmen and it's total sales value.
     * @return Map<String, Double>
     */
    private Map<String, Double> getMapOfSalesmenAndTotalSalesValue(){
        return saleRepository.getAll().stream()
                .collect(Collectors.groupingBy(Sale::getSalesmanName, Collectors.summingDouble(Sale::getSalePrice)));
    }

    /**
     * Updates the best sale and worst salesman ever.
     */
    public void updateBestSaleAndWorstSalesmanEver(){
        bestSale            = saleRepository.getAll().stream().max(Comparator.comparingDouble(Sale::getSalePrice)).orElse(null);
        worstSalesmanEver   = getMapOfSalesmenAndTotalSalesValue().entrySet().stream().min(Comparator.comparingDouble(Map.Entry::getValue)).orElse(null);
    }

    public int getSize(){
        return saleRepository.size();
    }

}
