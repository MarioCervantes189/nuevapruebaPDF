import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Lineas {
    private List<String> lineas;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private HashMap<String, List<Integer>> diccionario; //se agrega un hashmap que funcione como diccionario (como en python)
    //la llave es la palabra y el valor es una lista de enteros que representan las posiciones de las lineas en las que aparece la palabra
    public Lineas(){
        lineas = new ArrayList<>();
        diccionario = new HashMap<>();
    }
   
    public void agregarEscuchador(PropertyChangeListener funcion) {
        support.addPropertyChangeListener(funcion);
    }

    public void insert(String lineaNueva) {
        List<String> lineaAnterior = new ArrayList<>(lineas);
        lineas.add(lineaNueva);
        support.firePropertyChange("lineas", lineaAnterior, lineas);
    }

    public void insert(String lineaNueva, List<Integer> paginas) {
        List<String> lineaAnterior = new ArrayList<>(lineas);
        lineas.add(lineaNueva);
        diccionario.put(lineaNueva, paginas);
        support.firePropertyChange("lineas", lineaAnterior, diccionario);
    }

    public void insert(int index,String frase,HashMap<String, List<Integer>> hashPalabras) {
        List<String> lineaAnterior = new ArrayList<>(lineas);
        String listaPaginas = "";
        for (int i = 0; i < hashPalabras.get(frase).size(); i++) {
            listaPaginas += hashPalabras.get(frase).get(i) + ", ";
        }
        try {
            listaPaginas = listaPaginas.substring(0, listaPaginas.length() - 2);
        }catch (Exception e){
            return;
        }
        
        String lineaNueva = ""+frase+" ["+listaPaginas+"]";
        lineas.add(index, lineaNueva);
    }

    
    public void insert(int index,String lineaNueva){
        lineas.add(index,lineaNueva);
    }
    public String getUltimoInsert(){
        return lineas.remove(lineas.size() - 1);
    }

    public List<String> getLineas() {
        return lineas;
    }

    public HashMap<String, List<Integer>> getDiccionarioDePalabras() {
        return diccionario;
    }
}