package service;

import config.Characters;
import domain.Sale;
import repository.SaleRepository;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class SaleService extends Service{

    public SaleService(){}

    private static final SaleRepository saleRepository = new SaleRepository();
    public static Sale bestSale = null;

    public boolean addFromProcessedData(String[] data){
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
        updateMostExpensiveSale();
        return "MostExpensiveSale - " + bestSale.getSaleId() + Characters.NEW_LINE;
    }

    public Map<String, Double> mapSalesToSalesman(){
        return saleRepository.getAll().stream()
                .collect(Collectors.groupingBy(Sale::getSalesmanName, Collectors.summingDouble(Sale::getSalePrice)));
    }

    private void updateMostExpensiveSale(){
        Sale sale = saleRepository.getAll().stream().max(Comparator.comparingDouble(Sale::getSalePrice)).orElse(null);
        if(sale != null){
            if(bestSale == null) bestSale = sale;
            else if(sale.getSalePrice() > bestSale.getSalePrice()) bestSale = sale;
        }
    }
}
