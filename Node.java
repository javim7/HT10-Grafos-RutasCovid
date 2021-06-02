/***************************************
 * @author Javier Mombiela 20067
 * 
 * Clase Node, es la clase que respresenta 
 * a cada nodo individualmente.
 ***************************************/
public class Node<V> {

    // Atributos del nodo
    protected V value;
    protected Integer index;

    /**
     * Constructor del nodo, sabe su posicion y valor
     * @pos hay un nuevo nodo
     * @param value es el valor que se le de al nodo
     * @param index es la posicion en la que se encuentra
     */
    public Node(V value, Integer index){
        this.value = value;
        this.index = index;
    }

    /**
     * Se encarga de dar el indice del nodo en la matriz
     * @return el index'
     */
    public Integer getIndex() {
        return index;
    }
}