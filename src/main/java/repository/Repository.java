package repository;

import java.util.List;

public interface Repository<T>{

    boolean save(T elem);

    int size();

    List<T> getAll();

}
