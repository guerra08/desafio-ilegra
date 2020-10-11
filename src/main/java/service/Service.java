package service;

public abstract class Service {

    /**
     * Adds an entry from a processed data String array
     * @param data String array
     * @return boolean
     */
    public abstract boolean addFromProcessedData(String[] data);

    /**
     * Generates the output String for the file
     * @return String
     */
    public abstract String generateOutputString();

    /**
     * Returns the current size of the repository
     * @return int
     */
    public abstract int getSize();
}