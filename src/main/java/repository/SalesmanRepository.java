package repository;

import domain.Salesman;

import java.util.ArrayList;
import java.util.List;

public class SalesmanRepository {

    private List<Salesman> salesmen = new ArrayList<>();

    /**
     * Adds a new Salesman to the repository
     * @param s The new Salesman
     * @return boolean
     */
    public boolean saveSalesman(Salesman s){
        return salesmen.add(s);
    }

    /**
     * Returns the amount of salesmen in the repository
     * @return int
     */
    public int getSalesmenSize(){
        return salesmen.size();
    }

}
