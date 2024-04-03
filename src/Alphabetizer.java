import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;

class Alphabetizer implements PropertyChangeListener {

    public void propertyChange(PropertyChangeEvent evt) {
        int index = 0;
        Lineas lines = (Lineas) evt.getSource();
        String frase = lines.getUltimoInsert();

        while (index < lines.getLineas().size() && lines.getLineas().get(index).compareToIgnoreCase(frase) < 0) {
            index++;
        }
        lines.insert(index, frase,lines.getDiccionarioDePalabras());
    }
}
