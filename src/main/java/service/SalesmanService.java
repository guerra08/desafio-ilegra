package service;

import domain.Salesman;
import lombok.Getter;
import lombok.Setter;
import repository.SalesmanRepository;

import java.math.BigDecimal;

public class SalesmanService extends Service{

    public SalesmanService(){}

    private static SalesmanRepository salesmanRepository = new SalesmanRepository();

    @Getter
    @Setter
    private static String worstSalesmanEver = null;

    @Getter
    @Setter
    public static BigDecimal worstSaleValueEver = new BigDecimal("0.00");

    public boolean addFromProcessedData(String[] data){
        return salesmanRepository.save(Salesman.builder().CNPJ(data[1]).name(data[2]).salary(Double.parseDouble(data[3])).build());
    }

    public boolean addSalesman(Salesman s){
        return salesmanRepository.save(s);
    }

    public int getSize(){
        return salesmanRepository.size();
    }

    public void refresh(){
        salesmanRepository = new SalesmanRepository();
    }

}
