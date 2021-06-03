//importando clases externas
import java.util.*; 
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/***************************************
 * @author Javier Mombiela 20067
 * 
 * Clase Main, controla el menu y el
 * funcionamiento del programa.
 ***************************************/
public class Main {

    public static void main(String[] args) {

        //creando instancias
        Scanner scan = new Scanner(System.in);
        Graph<String> grafo = new Grafo<>();
        FloydAlgo floyd = new FloydAlgo();

        //creanco variables
        String ciudad1 = "";
        String ciudad2 = "";
        String distancia = "";
        String [] partes = new String [3];
        ArrayList<String> origen = new ArrayList<String>();
        ArrayList<String> destino = new ArrayList<String>();
        ArrayList<String> dist = new ArrayList<String>();

        
        System.out.println("\nInformacion sobre rutas:");
        //se utiliza un try catch para aseguranos que el archivo pueda ser leido correctamente 
        try{

            //creando variables
            File archivoTexto = new File("guategrafo.txt"); //creando nuestro nuevo archivo

            Scanner scanner = new Scanner(archivoTexto); //instanciando la clase scanner con el archivo

            while(scanner.hasNextLine()) { //while para que se lean todas las lineas en el archivo

                String linea = scanner.nextLine(); //guardando los elementos (de cada linea) como variables
                
                //separando cada linea por espacio
                partes = linea.split(" ");
                ciudad1 = partes[0];
                ciudad2 = partes[1];
                distancia = partes[2];

                //agregando datos a las listas dinamicas
                origen.add(ciudad1);
                destino.add(ciudad2);
                dist.add(distancia);

                //mostrando las ciudades disponbiles y distancias
                System.out.println("Origen: " + ciudad1 + ", Destino: " + ciudad2 + ", Distancia: " + distancia );

                //creando el grafo con los datos del .txt
                grafo.add(ciudad1); //estos son los nodos
                grafo.add(ciudad2);
                grafo.addEdge(ciudad1, ciudad2, Integer.parseInt(distancia)); //creando relacione entre ambas ciudades

            }

            //se hace un catch por si el archivo no se puede leer
        } catch (FileNotFoundException errorArchivoNoEncontrado) {
            // Se le advierte al usuario que el archivo no es existente, se termina el programa.
            System.out.println("El archivo de texto guategrafo.txt no ha sido encontrado.");
        }

        //creando la matriz de adyacencia e imprimiendola
        System.out.println("\n Matriz de adyacencia de los departamentos disponibles: ");
        grafo.makeAdjacentMatrix();
        System.out.print(grafo.showMatrix(0));
        grafo.floydAlgorithm();
        //floyd.floydWarshall(grafo.makeAdjacentMatrix());

         //haciendo un boolean iniciandolo en true
         boolean menu = true;

         //haciendo un while para que haya un ciclo infinito
         while(menu) {
             //creando menu
             System.out.println("\nMenu");
             System.out.println("(1) Ver ruta mas corta entre dos ciudades.");
             System.out.println("(2) Ver ciudad en el centro del grafo.");
             System.out.println("(3) Modificar informacion del grafo.");
             System.out.println("(4) Salir del programa.");
 
             int opcion = 0;
    
            //creando un try catch para asegurar que se ingrese una opcion correcta entre 1 y 4
            while(true){
                try{
                    System.out.print("Opcion: ");
                    opcion = scan.nextInt();
                    //(Programación defensiva)
                    //Protección por si el usuario elige un número menor a uno o mayor a dos, seguirá pidiendo la opción. 
                    if(opcion > 4 || opcion < 1){
                        System.out.println("\nOpcion incorrecta, intenta de nuevo..");
                    }
                    //Si el usuario ingresa los datos correctos terminará el ciclo while
                    else{break;}
                }
                //Si el usuario ingresa una letra regresará un mensaje de error. 
                catch(Exception o){
                    scan.nextLine();
                    System.out.println("Caracter invalido! Intenta de nuevo..");
                } 
            } 

            //iniciando opciones

            //iniciando con opcion 1, ver ruta mas corta
            if(opcion==1) {

                System.out.println("\nSeleccione la ciudad de origen:");

                //for loop para imprimir las ciudades de la lista como una lista numerada
                for(int i = 0; i < origen.size(); i++) {
                    System.out.println((i+1) + " " + origen.get(i));
                }

                int tamOrigen = origen.size();
                int opcion2 = 0;
                //creando un try catch para asegurar que se ingrese una opcion correcta entre 1 y el tamano de laa lista
                while(true){
                    try{
                        System.out.print("Origen: ");
                        opcion2 = scan.nextInt();
                        //(Programación defensiva)
                        //Protección por si el usuario elige un número menor a uno o mayor a dos, seguirá pidiendo la opción.
                        if(opcion2 > tamOrigen || opcion2 < 1){
                            System.out.println("\nOpcion incorrecta, intenta de nuevo..");
                        }
                        //Si el usuario ingresa los datos correctos terminará el ciclo while
                        else{break;}
                    }
                    //Si el usuario ingresa una letra regresará un mensaje de error.
                    catch(Exception o){
                        scan.nextLine();
                        System.out.println("Caracter invalido! Intenta de nuevo..");
                    }
                }

                //definiendo la ciudad de orginen selccionada
                String ciudadOrigen = origen.get(opcion2-1);

                //eliminando la ciuadad de origen del listado de destino para que no se pueda seleccionar esa misma ciudad
                destino.remove(ciudadOrigen);

                System.out.println("\nSeleccione la ciudad de destino:");

                //for loop para imprimir las ciudades de la lista como una lsita numerada
                for(int i = 0; i < destino.size(); i++) {
                    System.out.println((i+1) + " " + destino.get(i));
                }

                int tamDestino = destino.size();
                int opcion3 = 0;
                //creando un try catch para asegurar que se ingrese una opcion correcta entre 1 y el tamano de laa lista
                while(true){
                    try{
                        System.out.print("Destino: ");
                        opcion3 = scan.nextInt();
                        //(Programación defensiva)
                        //Protección por si el usuario elige un número menor a uno o mayor a dos, seguirá pidiendo la opción.
                        if(opcion3 > tamDestino || opcion3 < 1){
                            System.out.println("\nOpcion incorrecta, intenta de nuevo..");
                        }
                        //Si el usuario ingresa los datos correctos terminará el ciclo while
                        else{break;}
                    }
                    //Si el usuario ingresa una letra regresará un mensaje de error.
                    catch(Exception o){
                        scan.nextLine();
                        System.out.println("Caracter invalido! Intenta de nuevo..");
                    }
                }

                //definiendo la ciudad de orginen selccionada
                String ciudadDestino = destino.get(opcion3-1);

                //volviendo a agregar la ciudad de destino a su lista
                destino.add(ciudadOrigen);

                //impirmiendo mensajes, incluyendo ruta mas corta
                System.out.println("\nCiudad de origen: " +ciudadOrigen+ ", ciudad de destino: " +ciudadDestino);
                System.out.println("\nLa ruta mas corta "+ grafo.getRoute(ciudadOrigen, ciudadDestino) +"km.");


            }

            //opcion2, ver centro de grafo
            if(opcion==2) {

                System.out.println("\nBuscando el centro del grafo...");
                System.out.println("Centro de grafo localizado");
                System.out.println("La ciudad en el centro del grafo es: " + grafo.findMiddle()+".");

            }

            //opcion3, modificar el grafo
            if(opcion==3) {

                //imprimiendo las subopcioes de la opcion 3
                System.out.println("\nSubmenu");
                System.out.println("(1) Hay trafico entre dos ciudades.");
                System.out.println("(2) Hay una conexion entre dos ciudades.");

                //inicializando opcion4 como cero
                int opcion4 = 0;

                //creando un try catch para asegurar que se ingrese una opcion correcta entre 1 y 4
                while(true){
                    try{
                        System.out.print("Opcion: ");
                        opcion4 = scan.nextInt();
                        //(Programación defensiva)
                        //Protección por si el usuario elige un número menor a uno o mayor a dos, seguirá pidiendo la opción.
                        if(opcion4 > 2 || opcion4 < 1){
                            System.out.println("\nOpcion incorrecta, intenta de nuevo..");
                        }
                        //Si el usuario ingresa los datos correctos terminará el ciclo while
                        else{break;}
                    }
                    //Si el usuario ingresa una letra regresará un mensaje de error.
                    catch(Exception o){
                        scan.nextLine();
                        System.out.println("Caracter invalido! Intenta de nuevo..");
                    }
                }

                if(opcion4==1){

                    //mensaje para que seleccione la ciudad que quiere editar
                    System.out.println("\nSeleccione la ciudad de origen:");

                    //for loop para imprimir las ciudades de la lista como una lista numerada
                    for(int i = 0; i < origen.size(); i++) {
                        System.out.println((i+1) + " " + origen.get(i));
                    }

                    int tamOrigen = origen.size();
                    int opcion5 = 0;
                    //creando un try catch para asegurar que se ingrese una opcion correcta entre 1 y el tamano de laa lista
                    while(true){
                        try{
                            System.out.print("Origen: ");
                            opcion5 = scan.nextInt();
                            //(Programación defensiva)
                            //Protección por si el usuario elige un número menor a uno o mayor a dos, seguirá pidiendo la opción.
                            if(opcion5 > tamOrigen || opcion5 < 1){
                                System.out.println("\nOpcion incorrecta, intenta de nuevo..");
                            }
                            //Si el usuario ingresa los datos correctos terminará el ciclo while
                            else{break;}
                        }
                        //Si el usuario ingresa una letra regresará un mensaje de error.
                        catch(Exception o){
                            scan.nextLine();
                            System.out.println("Caracter invalido! Intenta de nuevo..");
                        }
                    }

                    //definiendo la ciudad de orginen selccionada
                    String ciudadOrigen = origen.get(opcion5-1);

                    //eliminando la ciuadad de origen del listado de destino para que no se pueda seleccionar esa misma ciudad
                    destino.remove(ciudadOrigen);

                    System.out.println("\nSeleccione la ciudad de destino:");

                    //for loop para imprimir las ciudades de la lista como una lsita numerada
                    for(int i = 0; i < destino.size(); i++) {
                        System.out.println((i+1) + " " + destino.get(i));
                    }

                    int tamDestino = destino.size();
                    int opcion6 = 0;
                    //creando un try catch para asegurar que se ingrese una opcion correcta entre 1 y el tamano de laa lista
                    while(true){
                        try{
                            System.out.print("Destino: ");
                            opcion6 = scan.nextInt();
                            //(Programación defensiva)
                            //Protección por si el usuario elige un número menor a uno o mayor a dos, seguirá pidiendo la opción.
                            if(opcion6 > tamDestino || opcion6 < 1){
                                System.out.println("\nOpcion incorrecta, intenta de nuevo..");
                            }
                            //Si el usuario ingresa los datos correctos terminará el ciclo while
                            else{break;}
                        }
                        //Si el usuario ingresa una letra regresará un mensaje de error.
                        catch(Exception o){
                            scan.nextLine();
                            System.out.println("Caracter invalido! Intenta de nuevo..");
                        }
                    }

                    //definiendo la ciudad de orginen selccionada
                    String ciudadDestino = destino.get(opcion6-1);

                    //volviendo a agregar la ciudad de destino a su lista
                    destino.add(ciudadOrigen);

                    //creando una nueva variable tipo boolean para que almacene el resultado de la removida de la arista
                    boolean hayArista = grafo.removeEdge(ciudadOrigen, ciudadDestino);

                    //if para ver si hay arista o no entre estas ciudades
                    if(hayArista==false) {
                        System.out.println("\nSe ha eliminado la conexcion entre " + ciudadOrigen + " y " +ciudadDestino+ ".");
                    } else {
                        System.out.println("\nNo existe conexion entre "  + ciudadOrigen + " y " +ciudadDestino+ ".");
                    }

                    //modificando matriz adyacencia
                    grafo.makeAdjacentMatrix();
                    grafo.floydAlgorithm();

                    //mostrando rutas mas cercanas
                    System.out.print(grafo.showMatrix(0));

                    //impriminedo el nuevo centro del grafo
                    System.out.println("La nueva ciudad en el centro del grafo es: " + grafo.findMiddle()+".");



                }

                if(opcion4==2) {


                    //mensaje para que seleccione la ciudad que quiere editar
                    System.out.println("\nSeleccione la ciudad de origen:");

                    //for loop para imprimir las ciudades de la lista como una lista numerada
                    for(int i = 0; i < origen.size(); i++) {
                        System.out.println((i+1) + " " + origen.get(i));
                    }

                    int tamOrigen = origen.size();
                    int opcion7 = 0;
                    //creando un try catch para asegurar que se ingrese una opcion correcta entre 1 y el tamano de laa lista
                    while(true){
                        try{
                            System.out.print("Origen: ");
                            opcion7 = scan.nextInt();
                            //(Programación defensiva)
                            //Protección por si el usuario elige un número menor a uno o mayor a dos, seguirá pidiendo la opción.
                            if(opcion7 > tamOrigen || opcion7 < 1){
                                System.out.println("\nOpcion incorrecta, intenta de nuevo..");
                            }
                            //Si el usuario ingresa los datos correctos terminará el ciclo while
                            else{break;}
                        }
                        //Si el usuario ingresa una letra regresará un mensaje de error.
                        catch(Exception o){
                            scan.nextLine();
                            System.out.println("Caracter invalido! Intenta de nuevo..");
                        }
                    }

                    //definiendo la ciudad de orginen selccionada
                    String ciudadOrigen = origen.get(opcion7-1);

                    //eliminando la ciuadad de origen del listado de destino para que no se pueda seleccionar esa misma ciudad
                    destino.remove(ciudadOrigen);

                    System.out.println("\nSeleccione la ciudad de destino:");

                    //for loop para imprimir las ciudades de la lista como una lsita numerada
                    for(int i = 0; i < destino.size(); i++) {
                        System.out.println((i+1) + " " + destino.get(i));
                    }

                    int tamDestino = destino.size();
                    int opcion8 = 0;
                    //creando un try catch para asegurar que se ingrese una opcion correcta entre 1 y el tamano de laa lista
                    while(true){
                        try{
                            System.out.print("Destino: ");
                            opcion8 = scan.nextInt();
                            //(Programación defensiva)
                            //Protección por si el usuario elige un número menor a uno o mayor a dos, seguirá pidiendo la opción.
                            if(opcion8 > tamDestino || opcion8 < 1){
                                System.out.println("\nOpcion incorrecta, intenta de nuevo..");
                            }
                            //Si el usuario ingresa los datos correctos terminará el ciclo while
                            else{break;}
                        }
                        //Si el usuario ingresa una letra regresará un mensaje de error.
                        catch(Exception o){
                            scan.nextLine();
                            System.out.println("Caracter invalido! Intenta de nuevo..");
                        }
                    }

                    //definiendo la ciudad de orginen selccionada
                    String ciudadDestino = destino.get(opcion8-1);

                    //volviendo a agregar la ciudad de destino a su lista
                    destino.add(ciudadOrigen);

                    //pidiendo que ingrese la distancia de la nueva conexion
                    System.out.println("\nIngrese la distancia entre ambas ciudades:");
                    int nuevaDistancia = scan.nextInt();

                    //creando la nueva conexion
                    //creando una nueva variable tipo boolean para que almacene el resultado de la removida de la arista
                    boolean hayArista =  grafo.addEdge(ciudadOrigen, ciudadDestino, nuevaDistancia);

                    //if para ver si hay arista o no entre estas ciudades
                    if(hayArista) {
                        System.out.println("\nSe ha creado la conexcion entre " + ciudadOrigen + " y " +ciudadDestino+ ".");
                    } else {
                        System.out.println("\nAlgo ha salido mal");
                    }

                    //modificando matriz adyacencia
                    grafo.makeAdjacentMatrix();
                    grafo.floydAlgorithm();

                    //mostrando rutas mas cercanas
                    System.out.print(grafo.showMatrix(0));

                    //impriminedo el nuevo centro del grafo
                    System.out.println("La nueva ciudad en el centro del grafo es: " + grafo.findMiddle()+".");


                }

            }

            //opcion4, salir del programa
            if(opcion==4) {
                //imprimiendo mensajes de despedidda
                System.out.println("\nGracias por utilizar el programa, vuelva pronto!");
                System.out.println("Finalizando Programa...");
                System.out.println("Programa Finalizado!\n");
                //haciendo el boolean false pora que salga del ciclor
                menu = false;
            }
            
        }

        scan.close();

}

}