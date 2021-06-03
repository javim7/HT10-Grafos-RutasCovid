import java.util.LinkedList;

public class WeightedGraph {
    static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Graph {
        int vertices;
        LinkedList<Edge> [] adjacencylist;

        Graph(int vertices) {
            this.vertices = vertices;
            adjacencylist = new LinkedList[vertices];
            //initialize adjacency lists for all the vertices
            for (int i = 0; i < vertices ; i++) {
                adjacencylist[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            adjacencylist[source].addFirst(edge); //for directed graph
        }

        public void printGraph(){
            String[] departments = new String[] {"Guatemala", "Sacatepeques", "Chimaltenango", "SantaRosa", "Escuintla"};
            for (int i = 0; i < vertices ; i++) {
                LinkedList<Edge> list = adjacencylist[i];
                for (int j = 0; j < list.size() ; j++) {
                    System.out.println(departments[i] + " is connected to " +
                            departments[list.get(j).destination] + " with weight " +  list.get(j).weight + " Km");
                }
            }
        }
    }
}