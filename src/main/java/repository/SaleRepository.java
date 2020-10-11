package repository;

import domain.Sale;

import java.util.ArrayList;
import java.util.List;

public class SaleRepository implements Repository<Sale>{

    private final List<Sale> sales = new ArrayList<>();

    /**
     * Saves a Sale to the repository
     * @param s Sale
     * @return boolean
     */
    public boolean save(Sale s){
        return sales.add(s);
    }

    /**
     * Returns the size of the repository
     * @return int
     */
    public int size(){
        return sales.size();
    }

    /**
     * Returns a list of all the sales in the repository
     * @return List<Sale>
     */
    public List<Sale> getAll(){
        return sales;
    }

}