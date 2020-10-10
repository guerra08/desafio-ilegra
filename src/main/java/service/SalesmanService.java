package service;

import domain.Salesman;
import lombok.Getter;
import lombok.Setter;
import repository.SalesmanRepository;

import java.math.BigDecimal;

public class SalesmanService {

    private static SalesmanRepository salesmanRepository = new SalesmanRepository();

    @Getter
    @Setter
    private static String worstSalesmanEver = null;

    @Getter
    @Setter
    public static BigDecimal worstSaleValueEver = new BigDecimal("0.00");

    public static boolean addSalesman(Salesman s){
        return salesmanRepository.save(s);
    }

    public static int getSize(){
        return salesmanRepository.size();
    }

    public static void refresh(){
        salesmanRepository = new SalesmanRepository();
    }

}
