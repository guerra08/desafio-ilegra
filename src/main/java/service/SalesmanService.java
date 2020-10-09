package service;

import domain.Salesman;
import repository.SalesmanRepository;

public class SalesmanService {

    private static SalesmanRepository salesmanRepository = new SalesmanRepository();

    public static boolean addSalesman(Salesman s){
        return salesmanRepository.saveSalesman(s);
    }

    public static int salesmenCount(){
        return salesmanRepository.getSalesmenSize();
    }

    public static void refresh(){
        salesmanRepository = new SalesmanRepository();
    }

}
