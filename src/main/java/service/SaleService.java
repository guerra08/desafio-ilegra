package service;

import config.Characters;
import domain.Sale;
import repository.SaleRepository;

public class SaleService extends Service{

    public SaleService(){}

    private static SaleRepository saleRepository = new SaleRepository();
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
       return "MostExpensiveSale - " + bestSale.getSaleId() + Characters.NEW_LINE;
    }

    public void cleanRepository(){
        saleRepository = new SaleRepository();
    }

}
