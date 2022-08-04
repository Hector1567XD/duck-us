package client;

import client.core.DuckGame;
import client.core.DuckMenu;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class DuckUs {

    public static void main(String[] args) {
        DuckOrquestador orquestator = new DuckOrquestador();

        // Cargar de archivo
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
             archivo = new File("jugador.txt");
             fr = new FileReader(archivo);
             br = new BufferedReader(fr);

            // Lectura del fichero
             String name;
               while ((name = br.readLine()) != null) {
                  orquestator.setDefaultName(name);
               }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
       
        if (Constants.SKIP_MENU) {
                orquestator.starGameWithoutMenu();
            } else {
                DuckMenu.start(orquestator);
            }
        
             
        }
    }
}    