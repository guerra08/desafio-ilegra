package service;

import config.Characters;
import config.Identifiers;
import domain.Sale;
import repository.SaleRepository;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class SaleService extends Service{

    public SaleService(){}

    private final SaleRepository saleRepository = new SaleRepository();
    public Sale bestSale = null;
    public Map.Entry<String, Double> worstSalesmanEver = null;

    public boolean addFromProcessedData(String[] data){
        if(!data[0].equals(Identifiers.SALE_ID)) return false;
        Sale s = Sale.builder().saleId(data[1]).soldProducts(data[2]).salesmanName(data[3]).build();
        if(bestSale != null && s.getSalePrice().compareTo(bestSale.getSalePrice()) > 0)
            bestSale = s;
        return saleRepository.save(Sale.builder().saleId(data[1]).soldProducts(data[2]).salesmanName(data[3]).build());
    }

    public boolean addSale(Sale s){
        if(bestSale != null && s.getSalePrice().compareTo(bestSale.getSalePrice()) > 0)
            bestSale = s;
        return saleRepository.save(s);
    }

    public String generateOutputString(){
        updateBestAndWorst();
        return "MostExpensiveSale - " + bestSale.getSaleId() + Characters.NEW_LINE;
    }

    private Map<String, Double> getWorstSalesmanFromSales(){
        return saleRepository.getAll().stream()
                .collect(Collectors.groupingBy(Sale::getSalesmanName, Collectors.summingDouble(Sale::getSalePrice)));
    }

    private void updateBestAndWorst(){
        Sale sale = saleRepository.getAll().stream().max(Comparator.comparingDouble(Sale::getSalePrice)).orElse(null);
        if(sale != null){
            if(bestSale == null) bestSale = sale;
            else if(sale.getSalePrice() > bestSale.getSalePrice()) bestSale = sale;
        }
        Map.Entry<String, Double> worstFromRepo = getWorstSalesmanFromSales().entrySet().stream().min(Comparator.comparingDouble(Map.Entry::getValue)).orElse(null);
        if(worstSalesmanEver == null) worstSalesmanEver = worstFromRepo;
        else if(worstFromRepo != null && worstFromRepo.getValue() < worstSalesmanEver.getValue()){
            worstSalesmanEver = worstFromRepo;
        }
    }

}
