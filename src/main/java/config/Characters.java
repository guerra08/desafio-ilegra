package config;

import java.io.File;

/**
 * Defines special characters needed by the application
 */
public class Characters {

    private Characters(){}

    public static final String MAIN_SEPARATOR           = "ç";
    public static final String PRODUCTS_SEPARATOR       = ",";
    public static final String PRODUCT_INFO_SEPARATOR   = "-";

    public static final String FILE_PATH_SEPARATOR  = File.separator;
    public static final String NEW_LINE             = System.getProperty("line.separator");

}
