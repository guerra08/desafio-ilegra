package config;

/**
 * System directories used by the application.
 */
public class Dir {

    public static final String INPUT_DIR = System.getProperty("user.home") +
            Characters.FILE_PATH_SEPARATOR + "data" + Characters.FILE_PATH_SEPARATOR + "in" + Characters.FILE_PATH_SEPARATOR;

    public static final String OUTPUT_DIR = System.getProperty("user.home") +
            Characters.FILE_PATH_SEPARATOR + "data" + Characters.FILE_PATH_SEPARATOR + "out" + Characters.FILE_PATH_SEPARATOR;

}
