package service;

import domain.Sale;
import repository.SaleRepository;

public class SaleService extends Service{

    public SaleService(){}

    private static SaleRepository saleRepository = new SaleRepository();
    public static Sale bestSale = null;

    public boolean addFromProcessedData(String[] data){
        return saleRepository.save(Sale.builder().saleId(data[1]).soldProducts(data[2]).salesmanName(data[3]).build());
    }

    public boolean addSale(Sale s){
        return saleRepository.save(s);
    }

    public void updateBestSale(){
        saleRepository.getAll().forEach(e -> {
            if(bestSale == null) bestSale = e;
            if(e.getSalePrice().compareTo(bestSale.getSalePrice() )> 0) bestSale = e;
        });
    }

    public void updateWorstSalesmanEver(){
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
