package service;

public interface Service<T> {

    /**
     * Adds an entry from a processed data String array
     * @param data String array
     * @return boolean
     */
    boolean addFromProcessedData(String[] data);

    /**
     * Adds a new entry
     * @param obj Entry
     * @return boolean
     */
    boolean add(T obj);

    /**
     * Generates the output String for the file
     * @return String
     */
    String generateOutputString();

    /**
     * Returns the current size of the repository
     * @return int
     */
    int getSize();
}