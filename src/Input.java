import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class Input {
    public void readFile(Lineas lines, File file) throws IOException {
        for (String line : Files.readAllLines(Paths.get(file.getName()))) {
            lines.insert(line);
        }
    }    
}