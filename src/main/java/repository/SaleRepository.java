package repository;

import domain.Sale;

import java.util.ArrayList;
import java.util.List;

public class SaleRepository implements Repository<Sale>{

    private List<Sale> sales = new ArrayList<>();

    public boolean save(Sale s){
        return sales.add(s);
    }

    public int size(){
        return sales.size();
    }

    public List<Sale> getAll(){
        return sales;
    }

}