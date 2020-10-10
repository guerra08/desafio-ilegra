package service;

import domain.Sale;
import repository.SaleRepository;

public class SaleService {

    private static SaleRepository saleRepository = new SaleRepository();
    public static Sale bestSale = null;


    public static boolean addSale(Sale s){
        return saleRepository.save(s);
    }

    public static void updateBestSale(){
        saleRepository.getAll().forEach(e -> {
            if(bestSale == null) bestSale = e;
            if(e.getSalePrice().compareTo(bestSale.getSalePrice() )> 0) bestSale = e;
        });
    }

    public static void updateWorstSalesmanEver(){
        saleRepository.getAll().forEach(e -> {
            if(SalesmanService.getWorstSalesmanEver() == null){
                SalesmanService.setWorstSalesmanEver(e.getSalesmanName());
                SalesmanService.setWorstSaleValueEver(e.getSalePrice());
            }
            else if(SalesmanService.worstSaleValueEver.compareTo(e.getSalePrice()) > 0){
                SalesmanService.setWorstSalesmanEver(e.getSalesmanName());
                SalesmanService.setWorstSaleValueEver(e.getSalePrice());
            }
        });
    }

}
