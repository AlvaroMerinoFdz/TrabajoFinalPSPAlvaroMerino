package hilos;

import bbdd.*;
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import utiles.*;

/**
 *
 * @author Alvaro Merino
 */
public class Hilo extends Thread {
    private PublicKey clavePub;
    private PrivateKey clavePriv;
    private PublicKey clavePubAjena;
    private Socket cliente;

    public Hilo(Socket cliente, Object[] claves) {
        this.cliente = cliente;
        this.clavePub = (PublicKey) claves[Constantes.CLAVEPUBLICA];
        this.clavePriv = (PrivateKey) claves[Constantes.CLAVEPRIVADA];
    }

    private void intercambioClaves() throws IOException, ClassNotFoundException {
        clavePubAjena = (PublicKey) Utiles.recibirObjeto(cliente);
        Utiles.enviarObject(cliente, clavePub);
    }

    @Override
    public void run() {

        try {
            intercambioClaves();
            int opcion = Utiles.RecibirInt(cliente);
            String pwd1;
            
            if (opcion == Constantes.LOGIN) {
                //Recibimos el objeto Cifrado
                SealedObject objetoCifrado = (SealedObject) Utiles.recibirObjeto(cliente);
                User usuario = Utiles.descifrarObjeto(objetoCifrado, clavePriv); //Desciframos el objeto
                pwd1 = SentenciasBD.selectPwdByUser(usuario.getEmail()); //Seleccionamos en la base de datos el usuario
                //Comprobamos si son iguales las pwd
                boolean iguales = usuario.getPassword().equals(pwd1);
                Utiles.enviarBoolean(cliente, iguales); //Enviamos respuesta
                System.out.println(iguales);
                
            } else {
                //Recibimos el objeto Cifrado
                SealedObject objetoCifrado = (SealedObject) Utiles.recibirObjeto(cliente);
                User usuario = Utiles.descifrarObjeto(objetoCifrado, clavePriv); //Desciframos el objeto
                SentenciasBD.insertUser(usuario);//Registramos en la bbdd
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
