package utiles;

import java.io.*;
import java.net.Socket;
import java.security.*;
import javax.crypto.*;

/**
 *
 * @author Alvaro Merino
 */
public class Utiles {
    public static String recorrerPassword(char[]cadena){
        String password="";
        for(int i=0;i<cadena.length;i++){
            password+=cadena[i];
        }
        return password;
    }
    public static boolean comprobarPassword(String password1, String password2){
        boolean iguales = false;
        if(password1.equals(password2)){
            iguales=true;
        }
        return iguales;
    }
    
    public static String pwdAlgoritmo(String password) throws NoSuchAlgorithmException{
        String pwdAlgoritmo = "";
            MessageDigest md = MessageDigest.getInstance(Constantes.ALGORITMODIGEST);
            
            byte datos[] = password.getBytes();
            md.update(datos);
            byte resumen[] = md.digest();
            pwdAlgoritmo = new String(resumen);
        return pwdAlgoritmo;
    }
    public static Object[] generarClaves() throws NoSuchAlgorithmException{
        Object[] claves = new Object[2];
        
        KeyPairGenerator KeyGen = KeyPairGenerator.getInstance(Constantes.ALGORITMOCLAVES);
        KeyGen.initialize(Constantes.KEYSIZE);
        KeyPair par = KeyGen.generateKeyPair();
        PrivateKey clavepriv = par.getPrivate();
        PublicKey clavepubl = par.getPublic();
        
        claves[Constantes.CLAVEPRIVADA] = clavepriv;
        claves[Constantes.CLAVEPUBLICA] = clavepubl;
        
        return claves;
    }
    
     public static SealedObject cifrarObjeto(User user, PublicKey clavPubAjena) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException {

        //Instanciamos el Cipher y lo inicializamos en el modo encriptación con la clave pública del servidor
        Cipher c = Cipher.getInstance(Constantes.ALGORITMOCIPHER);
        c.init(Cipher.ENCRYPT_MODE, clavPubAjena);

        return new SealedObject((Serializable) user, c);
    }
     
     public static void enviarObject(Socket receptor, Object objeto){
         try{
             ObjectOutputStream oos = new ObjectOutputStream(receptor.getOutputStream());
             oos.writeObject(objeto);
             System.out.println("Objeto enviado.");
         }catch(IOException e){
             
         }
     }
     
     public static Object recibirObjeto(Socket receptor) throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(receptor.getInputStream());
        Object objeto = ois.readObject();
        System.out.println("Objeto recibido");
         return objeto;
     }
     
     public static void EnviarInt(Socket receptor, int opcion) throws IOException{
         DataOutputStream dos = new DataOutputStream(receptor.getOutputStream());
         dos.writeInt(opcion);
     }
     
     public static int RecibirInt(Socket receptor) throws IOException{
         int recibido =0;
         DataInputStream dis = new DataInputStream(receptor.getInputStream());
         recibido = dis.readInt();
         return recibido;
     }
     
     public static void enviarBoolean(Socket receptor, boolean opcion) throws IOException {
        DataOutputStream dos = new DataOutputStream(receptor.getOutputStream());
        dos.writeBoolean(opcion);
    }

    public static boolean recibirBoolean(Socket receptor) throws IOException {
        boolean opcion = false;
        DataInputStream dis = new DataInputStream(receptor.getInputStream());
        opcion = dis.readBoolean();
        return opcion;
    }
    
}
