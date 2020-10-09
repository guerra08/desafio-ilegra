package service;

import domain.Salesman;
import repository.SalesmanRepository;

public class SalesmanService {

    private static SalesmanRepository salesmanRepository = new SalesmanRepository();

    public static String worstSalesmanEver = null;
    public static double worstSaleValueEver = 0.0;

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
