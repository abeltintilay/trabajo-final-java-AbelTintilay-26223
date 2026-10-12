 // VAMOS A REALIZAR UN CRUD BASICO

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        ArrayList<String> articulos = new ArrayList<>();

        int opcion;
        do {
            System.out.println("---------------------------------------------------------");
            System.out.println("CRUD BASICO DE ARTICULOS");
            System.out.println("---------------------------------------------------------");
            System.out.println("Ingrese una opción (1, 2, 3) o 0 para salir: ");
            System.out.println("1- Ingresar un Articulo");  
            System.out.println("2-Listar Articulos");
            System.out.println("3- Consultar por un Articulo");
            System.out.println("4- Modificar un Articulo");
            System.out.println("5- Eliminar un Articulo");
            System.out.println("0- Salir");
            System.out.println("---------------------------------------------------------");
            System.out.print("Ingrese una Opción: ");

            opcion = leerEntero(scanner,"Ingrese una opción válida del 1 al 5 o 0 para SALIR: ");

            
            try{
                    // usando un segun>
                    switch (opcion) {
                        case 1:
                                ingresarArticulo(scanner, articulos);
                                break;
                        case 2:
                                listarArticulos(articulos);
                                break;

                        case 3:
                                consultarArticulo(scanner, articulos);
                                break;

                        case 4:
                                modificarArticulo(scanner, articulos);
                            
                            break;
                        case 5:
                               eliminarArticulo(scanner, articulos);

                            
                            break;
                        case 0:
                            System.out.println("Saliendo del sistema...");
                            break;
                        default:
                            System.out.println("Opción inválida. Intente nuevamente.");
                    }

            }

            catch (Exception e) {
                System.out.println("Error: DEBE INGRESAR UN NUMERO DEL 1 AL 5 O 0 PARA SALIR. Intente nuevamente.");
                scanner.nextLine(); // Limpiar el buffer en caso de error
                opcion = -1; // Reiniciar la opción para continuar el bucle
            }

            
        } while (opcion != 0);

        // cerrando el scanner
        scanner.close();

    }

    public static int leerEntero(Scanner scanner, String mensaje) {
       
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: DEBE INGRESAR UN NUMERO ENTERO. Intente nuevamente.");
            }
        }
    }

    // METODO INGRESAR ARTICULO

    public static void ingresarArticulo(Scanner scanner, ArrayList<String> listaArticulos) {
        System.out.println("----INGRESAR ATICULO---- ");

        String descripcion = leerTextoNoVacio(scanner, "Ingrese la descripción del Articulo: ");


        if (existeArticulo(listaArticulos, descripcion)) {
            System.out.println("El Articulo '" + descripcion + "' ya existe en la lista. No se puede agregar.");
            return;
        };

        listaArticulos.add(descripcion);
        System.out.println("Articulo agregado correctamente: " + descripcion);

        
    }

// METODO LISTAR ARTICULOS

    public static void listarArticulos(ArrayList<String> articulos) {

        System.out.println("----LISTADO DE ARTICULOS----");

        if (articulos.isEmpty()) {
            System.out.println("No hay Articulos cargados");
            return;
        }   
        
        for (int i = 0; i < articulos.size(); i++) {
                System.out.println((i + 1) + "-- " + articulos.get(i));
            }
        
    }

// METODO CONSULTAR ARTICULOS


    public static void consultarArticulo(Scanner scanner, ArrayList<String> articulos) {
        
        System.out.println("----CONSULTAR ARTICULO----");

        // si la lista esta vacia, no se puede consultar
        if (articulos.isEmpty()) {
            System.out.println("No hay Articulos cargados");
            return;
        }

        String descripcionBuscada = leerTextoNoVacio(scanner, "Ingrese la descripción del Articulo a consultar: ");

        int posicion = buscarPosicionArticulo(articulos, descripcionBuscada);

        if (posicion == -1) {
            System.out.println("El Articulo '" + descripcionBuscada + "' no se encuentra en la lista.");
        } else {
            System.out.println("El Articulo encontrado esta en la posición " + (posicion + 1) + ".");
            System.out.println("Descripción: " + articulos.get(posicion));
        }
    }


// METODO MODIFICAR ARTICULOS

    public static void modificarArticulo(Scanner scanner, ArrayList<String> articulos) {
        System.out.println("----MODIFICAR ARTICULO----");

        if (articulos.isEmpty()) {
            System.out.println("No hay Articulos cargados");
            return;
        }

        String descripcionActual = leerTextoNoVacio(scanner, "Ingrese la descripción del Articulo a modificar: ");

        int posicion = buscarPosicionArticulo(articulos, descripcionActual);

        if (posicion == -1) {
            System.out.println("El Articulo '" + descripcionActual + "' no EXISTE  en la lista. No se puede modificar.");
            return;
        } 

        String nuevaDescripcion = leerTextoNoVacio(scanner, "Ingrese la nueva descripción del Articulo: ");
        
        if (existeArticulo(articulos, nuevaDescripcion)&& !articulos.get(posicion).equalsIgnoreCase(nuevaDescripcion)) {
            System.out.println("El Articulo '" + nuevaDescripcion + "' ya existe en la lista. No se puede modificar.");
            return;
        }
        
        // el articulo se modifica en la posicion encontrada
        
        articulos.set(posicion, nuevaDescripcion);
        System.out.println("Articulo modificado correctamente.");
        
    }

// METODO ELIMINAR ARTICULOS

    public static void eliminarArticulo(Scanner scanner, ArrayList<String> articulos) {
        System.out.println("----ELIMINAR ARTICULO----");

        if (articulos.isEmpty()) {
            System.out.println("No hay Articulos cargados");
            return;
        }

        String descripcionAEliminar = leerTextoNoVacio(scanner, "Ingrese la descripción del Articulo a eliminar: ");

        int posicion = buscarPosicionArticulo(articulos, descripcionAEliminar);

        if (posicion == -1) {
            System.out.println("El Articulo '" + descripcionAEliminar + "' no se encuentra en la lista. NO SE PUEDE ELIMINAR.");
            return;
        }

        // Eliminar el Articulo en la posición encontrada
        articulos.remove(posicion);
        System.out.println("Articulo ELIMINADO CORRECTAMENTE: " + descripcionAEliminar);
    }



    public static int buscarPosicionArticulo(ArrayList<String> articulos, String descripcion) {
        for (int i = 0; i < articulos.size(); i++) {
            if (articulos.get(i).equalsIgnoreCase(descripcion.trim())) {
                return i;
            }
        }
        return -1; // Retorna -1 si no se encuentra el Articulo
    }


    public static String leerTextoNoVacio(Scanner scanner, String mensaje) {
       while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();
            if (!texto.trim().isEmpty()) {
                return texto.trim();
            } 

            // se realiza un mensaje de error si el texto ingresado está vacío
            System.out.println("Error: El texto no puede estar vacío. Intente nuevamente.");
            
        }
    }

    public static boolean existeArticulo(ArrayList<String> articulos, String descripcion) {
        for (String articulo : articulos) {
            if (articulo.equalsIgnoreCase(descripcion.trim())) {
                return true;
            }
        }
        return false;
    }

}

