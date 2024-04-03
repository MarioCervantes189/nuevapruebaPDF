import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Obtener la ruta del archivo PDF
        System.out.print("Ingrese la ruta del archivo PDF: ");
        String pdfFilePath = scanner.nextLine();

        // Obtener la ruta del archivo de entrada
        System.out.print("Ingrese la ruta del archivo de entrada: ");
        String inputFilePath = scanner.nextLine();

        // Obtener la ruta del archivo de salida
        System.out.print("Ingrese la ruta del archivo de salida: ");
        String outputFilePath = scanner.nextLine();

        // Crear objetos con los mismos nombres
        Lineas lineas = new Lineas();
        Lineas index = new Lineas();
        Input input = new Input();
        Output output = new Output();
        File file = new File(pdfFilePath);
        LectorPDF pdf = new LectorPDF(index, file);
        Alphabetizer alphabetizer = new Alphabetizer();

        lineas.agregarEscuchador(pdf);
        index.agregarEscuchador(alphabetizer);

        try {
            input.readFile(lineas, new File(inputFilePath));
            output.writeFile(index, new File(outputFilePath));
            System.out.println("El proceso se ha completado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
