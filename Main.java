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

        //creanco variables
        String ciudad1 = "";
        String ciudad2 = "";
        String distancia = "";
        String [] partes = new String [3];
        ArrayList<String> origen = new ArrayList<String>();
        ArrayList<String> destino = new ArrayList<String>();
        ArrayList<String> dist = new ArrayList<String>();

        

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
                System.out.println("| Origen: " + ciudad1 + "| Destino: " + ciudad2 + "| Distancia: " + distancia );

            }

            //se hace un catch por si el archivo no se puede leer
        } catch (FileNotFoundException errorArchivoNoEncontrado) {
            // Se le advierte al usuario que el archivo no es existente, se termina el programa.
            System.out.println("El archivo de texto guategrafo.txt no ha sido encontrado.");
        }

        System.out.println(Arrays.toString(partes));


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

            //for loop para imprimir las ciudades de la lista como una lsita numerada
            for(int i = 0; i < origen.size(); i++) {
                System.out.println(i + " " + origen.get(i));
            }

            int opcion2 = 0;
            //creando un try catch para asegurar que se ingrese una opcion correcta entre 1 y el tamano de laa lista
            while(true){
                try{
                    System.out.print("Origen: ");
                    opcion2 = scan.nextInt();
                    //(Programación defensiva)
                    //Protección por si el usuario elige un número menor a uno o mayor a dos, seguirá pidiendo la opción. 
                    if(opcion2 > origen.size() || opcion < 1){
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

            //definiendo la ciudad selccionada
            String ciudadOrigen = origen.get(opcion2);
            System.out.println(ciudadOrigen);

            }

            //opcion2, ver centro de grafo
            if(opcion==2) {
                continue;
            }

            //opcion3, modificar el grafo
            if(opcion==3) {

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