package file;

import config.Dir;
import runnable.Processor;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Input {

    private Input(){}

    /**
     * Checks and generates the report for existing files in ~/data/in.
     */
    public static void generateReportsFromExistingFiles(){
        try(
            DirectoryStream<Path> existingPaths = Files.newDirectoryStream(Paths.get(Dir.INPUT_DIR))
        ){
            Processor p = new Processor();
            for(Path path : existingPaths){
                if(!Files.isDirectory(path) && path.toString().endsWith(".dat")){
                    p.processFile(path.getFileName());
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
