package service;

import domain.Salesman;
import repository.SalesmanRepository;

public class SalesmanService {

    private static final SalesmanRepository salesmanRepository = new SalesmanRepository();

    public static boolean addSalesman(Salesman s){
        return salesmanRepository.saveSalesman(s);
    }

    public static int salesmenCount(){
        return salesmanRepository.getSalesmenSize();
    }

}
