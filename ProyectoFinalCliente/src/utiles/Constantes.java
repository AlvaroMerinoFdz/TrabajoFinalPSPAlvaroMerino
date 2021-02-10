package utiles;

/**
 *
 * @author Alvaro Merino
 */
public class Constantes {
    //constantes de la base de datos
    public static String bbdd = "pspAlvaro";
    public static String usuario = "root";
    public static String passwd = "";
    public static String TablaUsuarios = "usuarios";
    public static String TablaTipoUsuarios = "tipoUsuarios";
    public static String TablaPermisos = "tabla_permisos";
    
    //constantes de seguridad
    public static int CLAVEPUBLICA = 1;
    public static int CLAVEPRIVADA = 0;
    public static String ALGORITMODIGEST = "SHA1";
    public static String ALGORITMOCIPHER = "RSA/ECB/PKCS1Padding";
    public static String ALGORITMOCLAVES = "RSA";    
    public static int KEYSIZE = 2048;
    public static int REGISTRO = 1;
    public static int LOGIN = 0;
}
