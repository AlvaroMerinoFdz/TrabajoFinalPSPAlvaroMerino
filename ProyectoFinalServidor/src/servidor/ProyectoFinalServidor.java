 
package servidor;

import hilos.Hilo;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import utiles.Utiles;

/**
 *
 * @author Alvaro Merino
 */
public class ProyectoFinalServidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        ServerSocket servidor = new ServerSocket(3000);        
        Socket cliente = servidor.accept();
        Object[] claves = Utiles.generarClaves();
        Hilo h = new Hilo(cliente, claves);
        h.start();
    }
    
}
