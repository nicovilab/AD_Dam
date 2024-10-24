import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FileInfo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        try { //manejo de excepciones
            do {
                System.out.println("=== Menú de Opciones ==="); //Creamos el menu de opciones
                System.out.println("1. Listar archivos (en modo simple y detallado) ");
                System.out.println("2. Crear carpeta");
                System.out.println("3. Copiar archivo");
                System.out.println("4. Mover archivo");
                System.out.println("5. Leer archivo");
                System.out.println("6. Escribir archivo");
                System.out.println("7. Eliminar archivo");
                System.out.println("8. Salir");
                System.out.print("Seleccione una opción: ");

                opcion = scanner.nextInt();

                switch (opcion) { //switch para el menu
                    case 1:
                        System.out.println("Selecciona una opción para listar archivos");
                        System.out.println("1. Listado simple");
                        System.out.println("2. Listado detallado");
                        System.out.println("3. Salir al menú");
                        int contadorTipolistado = scanner.nextInt();

                        switch (contadorTipolistado) {
                            case 1:
                                System.out.println("Listado simple");
                                System.out.println("Escribe la ruta:");
                                String route2 = scanner.next();
                                simpleListPrint(route2); //llamamos al metodo de listado simple
                                break;
                            case 2:
                                System.out.println("Listado detallado");
                                System.out.println("Escribe la ruta:");
                                String route = scanner.next();
                                detailedListPrint(route); //llamamos al metodo de listado detallado
                                break;
                            case 3:
                                System.out.println("Has salido del listado de archivos\n");

                                break;
                            default:
                                System.out.println("Opción no válida. Por favor, elija una opción entre 1 y 3.");
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("¿En qué ruta quieres crear la carpeta?");
                        String folderPath = scanner.next();
                        scanner.nextLine();
                        System.out.println("¿Cómo quieres llamar a la carpeta?");
                        String folderName = scanner.nextLine();
                        String concatNamePath = folderPath.concat("\\"+folderName); //concatenamos la ruta con el nombre de la carpeta para el metodo mkdir
                        createDirectory(concatNamePath); //llamamos al metodo para crear el directorio
                        break;
                    case 3:
                        System.out.println("¿En qué ruta se encuentra el archivo que quieres copiar? (Introduce también el nombre del archivo con su extensión)");
                        String orgFile = scanner.next();
                        scanner.nextLine();
                        System.out.println("¿A qué ruta quieres copiar el archivo?");
                        String desFile = scanner.nextLine();
                        System.out.println("Introduce el nombre del nuevo archivo con su extensión");
                        String desName = scanner.nextLine();
                        String desFileName = desFile.concat("\\" +desName); //concatenamos la ruta con el nombre que le pedimos al usuario
                        copyFile(orgFile, desFileName); //llamamos al metodo para copiar archivos
                        break;
                    case 4:
                        System.out.println("¿En qué ruta se encuentra el archivo que quieres mover? (Introduce también el nombre del archivo con su extensión)");
                        String org2File = scanner.next();
                        scanner.nextLine();
                        System.out.println("¿A qué ruta quieres mover el archivo? (Introduce también el nombre del archivo con su extensión)");
                        String des2File = scanner.nextLine();
                        moveFile(org2File, des2File); //llamamos al metodo para copiar archivos
                        break;
                    case 5:
                        //metodo
                        break;
                    case 6:
                        //metodo
                        break;
                    case 7:
                        //metodo
                        break;
                    case 8:
                        System.out.println("Has salido del programa");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elija una opción entre 1 y 8.");
                        break;
                }

            } while (opcion != 8);
        } catch (Exception e) {
            System.out.println(e);
        }

        scanner.close();
    }


    public static String getPermissions(File file) { //metodo para obtener los permisos del archivo
        StringBuilder permissions = new StringBuilder(); //creamos un stringbuilder en vez de un string ya que vamos a modificarlo al comprobar cada permiso con el metodo .append
        permissions.append(file.canRead() ? "r" : "-");
        permissions.append(file.canWrite() ? "w" : "-");
        permissions.append(file.canExecute() ? "x" : "-");

        return permissions.toString();
    }

    public static void fileInfo(File file) { //metodo para obtener la informacion del fichero e imprimirla por consola
        String type = file.isDirectory() ? "(/)" : "(_)";
        String size = file.isFile() ? file.length() + " bytes" : "";
        String permissions = getPermissions(file);
        String lastModified = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(file.lastModified()));

        System.out.println(type + " " + file.getName() + " [" + size + " " + permissions + " " + lastModified + "]");
    }

    public static void simpleListPrint(String path) { //metodo para listar de manera simple los archivos
        File directory = new File(path); //recorremos los archivos de la ruta introducida por el usuario en el menu
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            System.out.println("Los archivos encontrados en esta ruta son: ");
            for (File file : files) { //imprimimos el nombre de los archivos encontrados
                System.out.println(file.getName());
            }
            System.out.println("");
        } else {
            System.out.println("No se puede acceder al directorio");
        }
    }


    public static void detailedListPrint(String path) { //metodo para listar de manera detallada los archivos
        File directory = new File(path); //recorremos los archivos de la ruta introducida por el usuario en el menu
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            System.out.println("Los archivos encontrados en esta ruta son: ");
            for (File file : files) {
                fileInfo(file); //llamamos al metodo de obtener la informacion del archivo
            }
            System.out.println("");
        } else {
            System.out.println("No se puede acceder al directorio");
        }

    }

    public static void createDirectory(String path) { //metodo para crear un directorio
        File directory = new File(path); //al nuevo directorio le pasamos la ruta que le pedimos por escaner al usuario
        if(directory.isDirectory()){ //si el directorio ya existe
            System.out.println("La carpeta que quieres crear ya existe");
        }else{ //si no existe lo creamos
            directory.mkdir();
            System.out.println("La carpeta se ha creado correctamente");
        }
    }

    public static void copyFile(String orgFile, String desFile){ //metodo para copiar archivos
        File originFile = new File(orgFile); //creamos un nuevo archivo de origen
        File destinationFile  = new File(desFile); //creamos un nuevo archivo de destino
        try{
            FileUtils.copyFile(originFile, destinationFile); //hacemos uso del metodo de copyFile de la libreria FileUtils
            System.out.println("Se ha copiado correctamente el archivo \n");
        } catch (IOException e) {
            System.out.println("Ha habido un error copiando el archivo " + e+"\n");;
        }
    }

    public static void moveFile(String orgFile, String desFile){
        File originFile = new File(orgFile); //creamos un nuevo archivo de origen
        File destinationFile  = new File(desFile); //creamos un nuevo archivo de destino
        try{
            FileUtils.moveFile(originFile, destinationFile); //hacemos uso del metodo de moveFile de la libreria FileUtils
            System.out.println("Se ha movido correctamente el archivo \n");
        } catch (IOException e) {
            System.out.println("Ha habido un error moviendo el archivo " + e+"\n");;
        }
    }

}