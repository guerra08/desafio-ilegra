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
        if(bestSale != null){
            saleRepository.getAll().forEach(e -> {
                if(e.getSalePrice() > bestSale.getSalePrice()) bestSale = e;
            });
        }
    }

    public static void updateWorstSalesmanEver(){
        saleRepository.getAll().forEach(e -> {
            if(SalesmanService.worstSalesmanEver == null){
                SalesmanService.worstSalesmanEver = e.getSalesmanName();
                SalesmanService.worstSaleValueEver = e.getSalePrice();
            }
            else if(SalesmanService.worstSaleValueEver > e.getSalePrice()){
                SalesmanService.worstSalesmanEver = e.getSalesmanName();
                SalesmanService.worstSaleValueEver = e.getSalePrice();
            }
        });
    }

}
