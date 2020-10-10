package service;

public abstract class Service {

    public abstract boolean addFromProcessedData(String[] data);

    public abstract String generateOutputString();
}