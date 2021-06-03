import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrafoTest {

    Grafo<String> grafo = new Grafo<>();

    @org.junit.jupiter.api.Test
    void add() {

        //agregando elementos al grafo
        grafo.add("Antigua");
        grafo.add("Mixco");
        grafo.add("Escuuintla");
        grafo.add("Sancris");
        grafo.add("Jutiapa");
        grafo.add("Jalapa");

        //definiendo el resultado y el esperado
        System.out.print(grafo.relations.get("Mixco").index + "\n"); // Verificando que sea el index
        int result = grafo.relations.get("Mixco").index;
        int expexted = 1;

        //comparando resultados
        assertEquals(expexted, result);

    }

    @org.junit.jupiter.api.Test
    void addEdge() {

        //agregando ciudades sin conexion
        grafo.add("Antigua");
        grafo.add("Mixco");
        grafo.add("Escuuintla");
        grafo.add("Sancris");
        grafo.add("Jutiapa");
        grafo.add("Jalapa");

        //definiendo esperado y actual
        boolean result = grafo.addEdge("Jalapa", "Mixco", 5);
        boolean expected = true;

        assertEquals(expected, result);

    }

    @org.junit.jupiter.api.Test
    void removeEdge() {

        //agregando aristas entre dos ciudades
        grafo.addEdge("Mixco", "Antigua", 30);
        grafo.addEdge("Antigua", "Escuintla", 25);
        grafo.addEdge("Escuintla", "SantaLucia", 30);
        grafo.addEdge("SantaLucia", "Jutiapa", 30);
        grafo.addEdge("Jutiapa", "Jalapa", 30);
        grafo.addEdge("Jalapa", "Mixco", 30);

        //haciendo matriz de adyaciencia
        grafo.makeAdjacentMatrix();
        // Observando la matriz
        System.out.println(grafo.showMatrix(0));

        //definiendo esperado y actual
        boolean result = grafo.removeEdge("Antigua", "Escuintla");
        boolean expected = false;

        assertEquals(expected, result);
    }

    @org.junit.jupiter.api.Test
    void floydAlgorithm() {

        //algoritmo floyd
        grafo.floydAlgorithm();

        //haciendo matriz de adyaciencia
        grafo.makeAdjacentMatrix();
        // Observando la matriz
        System.out.println(grafo.showMatrix(0));


    }
}