import static org.junit.jupiter.api.Assertions.*;

class GrafoTest {

    Grafo<String> grafo = new Grafo<>();

    @org.junit.jupiter.api.Test
    void add() {

        grafo.add("Antigua");
        grafo.add("Mixco");
        grafo.add("Escuuintla");
        grafo.add("Sancris");
        grafo.add("Jutiapa");
        grafo.add("Jalapa");

        System.out.print(grafo.relations.get("Mixco").index + "\n"); // Verificando que sea el index

    }

    @org.junit.jupiter.api.Test
    void addEdge() {
    }

    @org.junit.jupiter.api.Test
    void removeEdge() {
    }

    @org.junit.jupiter.api.Test
    void floydAlgorithm() {
    }
}