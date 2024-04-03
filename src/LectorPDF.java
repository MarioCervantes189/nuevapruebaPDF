
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class LectorPDF implements PropertyChangeListener {

    private Lineas index;
    private File file;
    private PDDocument document;

    LectorPDF(Lineas index, File file) {
        try {
            document = Loader.loadPDF(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.index = index;
        this.file = file;
    }

    public void propertyChange(PropertyChangeEvent evento) {
        String palabra = ((List<String>) evento.getNewValue()).get(0);
        List<Integer> paginasIndex = new ArrayList<>();
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        
        for (int pageNumber = 1; pageNumber <= document.getNumberOfPages(); pageNumber++) {
            pdfTextStripper.setStartPage(pageNumber);
            pdfTextStripper.setEndPage(pageNumber);
            try {
                String text = removeSpecialSymbols(pdfTextStripper.getText(document));
                if (text.toLowerCase().contains(palabra.toLowerCase())) {
                    paginasIndex.add(pageNumber);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        index.insert(palabra, paginasIndex);
    }

    private String removeSpecialSymbols(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String result = normalized.replaceAll("[^\\p{L}\\p{M}0-9\\s]", "");
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(result).replaceAll("");
    }
}