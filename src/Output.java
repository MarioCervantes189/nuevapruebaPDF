
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


class Output {
    void writeFile(Lineas lines, File file) throws IOException {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            for (String line : lines.getLineas()) {
                fileWriter.append(line).append('\n');
            }
            fileWriter.flush();
        } finally {
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }
}
