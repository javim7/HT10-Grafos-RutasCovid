import java.util.*;
/***************************************
 * @author Javier Mombiela 20067
 * 
 * Clase Grafo, implementa a la interface
 * Graph y es responsable de adimistrar
 * los nodos y aristas.
 ***************************************/
public class Grafo<V> implements Graph<V> {

    protected Integer[][] adjacentMatrix;
    protected Integer[][] weightMatrix;
    protected String[][] paths;
    protected int index = 0; // Cantidad de nodos
    protected Map<V, Node<V>> relations; // Saber las relaciones entre nodos
    protected ArrayList<Edge<V>> edges;
    protected ArrayList<V> keys;

    /**
     * Constructor del grafo
     *
     * @pos esta construido el grafo e
     * inicializados sus atributos
     */
    public Grafo() {
        relations = new Hashtable<>();
        edges = new ArrayList<>();
        keys = new ArrayList<>();
    }

    /**
     * Se encarga de agregar un nodo al grafo
     *
     * @param newNode es el valor que se le quiere agregar al nodo
     * @pre el grafo tiene n valores
     * @pos el grafo tiene n +1 valores si no esta repetido
     */
    public void add(V newNode) {
        // Verificando si ya se encuentra en el dic, el valor ingresado
        if (relations.containsKey(newNode)) return;
        relations.put(newNode, new Node<V>(newNode, index));
        keys.add(newNode);
        index++;
    }

    /**
     * Se encarga de agregar una relacion entre dos nodos
     *
     * @param origin      el nodo de origen
     * @param destination el nodo de destino
     * @param weight      el peso entre los nodos
     * @return true si se agrego, false si no existen los nodos
     */
    public boolean addEdge(V origin, V destination, Integer weight) {
        Edge<V> newRoad;
        int position = -1;

        // Verificando que
        if (!(relations.containsKey(origin) && relations.containsKey(destination)))
            return false;
        else {
            newRoad = new Edge<>(origin, destination, weight);

            // Buscando si ya esta en la base
            for (int i = 0; i < edges.size(); i++) {
                if (newRoad.equals(edges.get(i))) {
                    position = i;
                    break;
                }
            }

            // Si la posicion es 0 es que encontro a uno que es igual
            if (position > 0) {
                edges.get(position).setWeight(weight);
            } else {
                edges.add(newRoad);
            }
            return true;
        }

    }

     /**
     * Se encarga de eliminar una relacion
     * @pre debe de haber al menos un elemento en el grafo
     * @pos hay una relacion menos si existen los nodos
     * @param origin el nodo de origen
     * @param destination el nodo de destino
     */
    public boolean removeEdge(V origin, V destination){
        Edge<V> newRoad;
        int position = -1;

        // Verificando que
        if (relations.containsKey(origin) && relations.containsKey(destination)) {
            newRoad = new Edge<>(origin, destination, 0);

            // Buscando si ya esta en la base
            for (int i = 0; i < edges.size(); i++) {
                if (newRoad.equals(edges.get(i))) {
                    position = i;
                    break;
                }
            }

            // Si la posicion es 0 es que encontro a uno que es igual
            if (position > -1) {
                edges.remove(position);
                return true;
            }
        }

        return false;
    }

    /**
     * Se encarga de crear la tabla de adyacencia
     * @pre el grafo tiene que tener al menos 2 nodos
     * @pos crea la tabla de adyacencia
     */
    public void makeAdjacentMatrix() {
        Edge<V> temp;
        adjacentMatrix = new Integer[index][index]; // Creando la matriz
        weightMatrix = new Integer[index][index];
        paths = new String[index][index];

        for (int i = 0; i < index; i++) {             // Llenando con valores 'infinitos'
            Arrays.fill(adjacentMatrix[i], 99999999);
            Arrays.fill(weightMatrix[i], 99999999);
        }

        for (int i = 0; i < edges.size(); i++) {
            temp = edges.get(i);
            adjacentMatrix[relations.get(temp.origin).getIndex()][relations.get(temp.destination).getIndex()] = temp.weight;
            weightMatrix[relations.get(temp.origin).getIndex()][relations.get(temp.destination).getIndex()] = temp.weight;
        }

        for (int i = 0; i < index; i++) {             // Aqui se vuelven 0 las row = colum
            adjacentMatrix[i][i] = 0;
            weightMatrix[i][i] = 0;
        }

    }

    /**
     *  Se encarga de conseguir el camino mas corto
     * @pre matriz de adyacencia = matriz de pesos
     * @pos matriz de pesos y paths tienen la informacion que deben de tener
     */
    public void floydAlgorithm(){
        String[][] pathTemp = new String[index][index];
        String[][] aux = new String[index][index];
        String[] temp;
        String traveledPath = "", chain = "" , littlePaths = "";
        int temp1, temp2, temp3, temp4, min;

        // Inicializando los caminos
        for(int i = 0; i < index; i++){
            for(int j = 0; j < index; j++){
                pathTemp[i][j] = "";
                aux[i][j] = "";
            }
        }

        // forloop para iterar los nodos del grafo
        for (int k = 0; k < index; k++){
            for (int i = 0; i < index; i++){
                for (int j = 0; j < index; j++){
                    temp1 = weightMatrix[i][j];
                    temp2 = weightMatrix[i][k];
                    temp3 = weightMatrix[k][j]; // Matriz de adyacencia
                    temp4 = temp2 + temp3;

                    // Encontrar el camino minimo
                    min = Math.min(temp1, temp4);
                    if (temp1 != temp4){
                        if (min == temp4){
                            traveledPath = "";
                            aux[i][j] = k + "";
                            pathTemp[i][j] = pathsR(i, k, aux, traveledPath) + (k + 1); // Caminos
                        }
                    }

                    weightMatrix[i][j] = min;

                }
            }
        }

        // Consiguiendo los caminos recorridos
        for (int i = 0; i < index; i++){
            for (int j = 0; j < index; j++){
                if (weightMatrix[i][j] != 1000000000){
                    if (i != j){

                        // Consiguiendo los caminos mas cortos
                        if(pathTemp[i][j].equals("")){
                            littlePaths = "De (" + keys.get(i) + " ---> " + keys.get(j) + ") Irse por... ("
                                    + keys.get(i) + ", " + keys.get(j) + ")";
                            paths[i][j] = littlePaths;

                        } else{

                            temp = pathTemp[i][j].split(",");

                            for (int n = 0; n < temp.length; n++){
                                if(!temp[n].equals("null")){
                                    chain += keys.get(Integer.parseInt(temp[n]) - 1) + ", ";
                                }
                            }

                            littlePaths = "De (" + keys.get(i) + " ---> " + keys.get(j) +") Irse por... ("
                                    + keys.get(i) + ", " + chain + "" + keys.get(j) + ")";
                            paths[i][j] = littlePaths;
                            chain = "";
                        }
                    }
                }
            }
        }
    }

    /**
     * Se encarga de encontrar los caminos extra que requiere el
     * @param i la posicion de la matriz
     * @param k la posicion de k que se esta utilizando
     * @param aux el valor de aux
     * @param traveledPath el string del path
     * @return los caminos siguientes
     */
    private String pathsR(int i, int k, String[][] aux, String traveledPath){

        if(aux[i][k].equals("")){
            return "";
        }else {
            // Recursividad al Millon
             traveledPath += pathsR(i, Integer.parseInt(aux[i][k]), aux, traveledPath)
                    + (Integer.parseInt(aux[i][k]) + 1) + ",";
             return traveledPath;
        }

    }

    /**
     * Se encarga de pasar las matrices ordenadas para hacer print
     * @pre las matrices deben de estar creadas
     * @pos se pasan a string las matrices
     * @param matrixType el tipo de matriz (1 de peso) y (0 adyacente)
     * @return la matriz se le quiera mostrar al usuario
     */
    public String showMatrix(Integer matrixType){
        Integer[][] aux = (matrixType == 0)? adjacentMatrix : weightMatrix;
        String matrix = "\t\t";

        // Se agrega el encabezado
        for (int i = 0; i < keys.size(); i++){
            matrix += keys.get(i).toString().substring(0, 1) + " \t";
        }

        // Terminando la matriz
        for (int i = 0; i < aux.length; i++){
            matrix += "\n\t";
            matrix += keys.get(i).toString().substring(0, 1) + "\t";

            for (int j = 0; j < aux[i].length; j++){
                matrix += (aux[i][j] >= 99999999)? "-" : aux[i][j];
                matrix += "\t";
            }

        }

        matrix += "\n\n| En donde:\n";
        for (int i = 0; i < aux.length; i++){
            matrix += "| " + keys.get(i).toString().substring(0, 1) + " -> " + keys.get(i).toString() + "\n";
        }

        return matrix;
    }

    /**
     * Consiguie la ruta mas corta para un origen y un destino
     * @param origin el nodo origen
     * @param destination el nodo destino
     * @return el destino o que no esta
     */
    public String getRoute(V origin, V destination){
        int i, j;
        if (relations.containsKey(origin) && relations.containsKey(destination)){
            i = relations.get(origin).getIndex();
            j = relations.get(destination).getIndex();
            if (weightMatrix[i][j] < 99999999)
                return paths[i][j] + " Una distancia total de: " + weightMatrix[i][j];
        }
        return "La ruta no existe";
    }

    /**
     * Se encarga de encontrar el centro del grafo
     * @pre se tuvo qe hacer el algoritmo de floyd
     * @pos se obtiene el nombre del centro del grafo
     */
    public V findMiddle(){
        Integer[] temp = new Integer[index];
        Integer min = 99999999, position = 0;

        // Inicializando con valores muy grandes
        for (int i = 0; i < index; i++){
            temp[i] = -99999999;
        }

        // Consiguiendo las ecentricidades
        for (int i = 0; i < index; i++){
            for (int j = 0; j < index; j++){

                if(i != j){ // Porque es de A -> A
                    if (temp[j] < weightMatrix[i][j]){
                        temp[j] = weightMatrix[i][j];
                    }
                }

            }
        }

        // Verificando la posicion del nodo con menor ecentricidad
        for (int i = 0; i < index; i++){
            if(min > temp[i]){
                min = temp[i];
                position = i;
            }
        }

        return keys.get(position);
    }

}