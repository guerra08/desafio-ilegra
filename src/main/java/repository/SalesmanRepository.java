package repository;

import domain.Salesman;

import java.util.ArrayList;
import java.util.List;

public class SalesmanRepository implements Repository<Salesman>{

    private final List<Salesman> salesmen = new ArrayList<>();

    /**
     * Adds a new Salesman to the repository
     * @param s The new Salesman
     * @return boolean
     */
    public boolean save(Salesman s){
        return salesmen.add(s);
    }

    /**
     * Returns the amount of salesmen in the repository
     * @return int
     */
    public int size(){
        return salesmen.size();
    }

    /**
     * Returns all the saved salesman
     * @return List<Salesman>
     */
    public List<Salesman> getAll(){
        return salesmen;
    }

}
