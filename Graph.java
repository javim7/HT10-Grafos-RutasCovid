
/***************************************
 * @author Javier Mombiela 20067
 * 
 * Inteface Graph, es el respnsable de 
 * poder administrar los nodos y las aristas
 * del grafo.
 ***************************************/
public interface Graph<V> {

    /**
     * Se encarga de agregar un nodo al grafo
     *
     * @param newNode es el valor que se le quiere agregar al nodo
     * @pre el grafo tiene n valores
     * @pos el grafo tiene n +1 valores si no esta repetido
     */
    public void add(V newNode);

    /**
     * Se encarga de agregar una relacion entre dos nodos
     *
     * @param origin      el nodo de origen
     * @param destination el nodo de destino
     * @param weight      el peso entre los nodos
     * @return true si se agrego, false si no existen los nodos
     */
    public boolean addEdge(V origin, V destination, Integer weight);

    /**
     * Se encarga de eliminar una relacion
     * @pre debe de haber al menos un elemento en el grafo
     * @pos hay una relacion menos si existen los nodos
     * @param origin el nodo de origen
     * @param destination el nodo de destino
     */
    public boolean removeEdge(V origin, V destination);

    /**
     * Se encarga de crear la tabla de adyacencia
     * @pre el grafo tiene que tener al menos 2 nodos
     * @pos crea la tabla de adyacencia
     */
    public void makeAdjacentMatrix();

    /**
     *  Se encarga de conseguir el camino mas corto
     * @pre matriz de adyacencia = matriz de pesos
     * @pos matriz de pesos y paths tienen la informacion que deben de tener
     */
    public void floydAlgorithm();

    /**
     * Se encarga de pasar las matrices ordenadas para hacer print
     * @pre las matrices deben de estar creadas
     * @pos se pasan a string las matrices
     * @param matrixType el tipo de matriz (1 de peso) y (0 adyacente)
     * @return la matriz se le quiera mostrar al usuario
     */
    public String showMatrix(Integer matrixType);

    /**
     * Consiguie la ruta mas corta para un origen y un destino
     * @param origin el nodo origen
     * @param destination el nodo destino
     * @return el destino o que no esta
     */
    public String getRoute(V origin, V destination);

    /**
     * Se encarga de encontrar el centro del grafo
     * @pre se tuvo qe hacer el algoritmo de floyd
     * @pos se obtiene el nombre del centro del grafo
     */
    public V findMiddle();

}