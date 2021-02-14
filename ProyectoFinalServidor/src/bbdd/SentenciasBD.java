//paquete
package bbdd;
//imports

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import utiles.*;

/**
 *
 * Clase en la que tenemos los m�todos necesarios para realizar inserts,
 * updates, select y deletes adem�s de poder crear una lista de selects en una
 * tabla
 *
 * @author diego
 *
 */
public class SentenciasBD {

    //Atributos
    private static boolean exito;

    //Getters
    public static boolean isExito() {
        return exito;
    }

    public static String selectPwdByUser(String user) {

        ResultSet result = null;
        String pwd = "";
        String sql;
        sql = "Select password from " + Constantes.TablaUsuarios + " where nameuser = '" + user + "'";

        //realiza la conexi�n con la BD
        Conexion conexion = new Conexion();

        //recoge el Statement devuelto por la conexi�n
        try ( Statement st = conexion.conectar()) {

            //enlace conexion
            String url = "jdbc:mysql://localhost:3306/" + Constantes.bbdd;
            //cargar el driver
            Class.forName("com.mysql.jdbc.Driver");
            //usuario y contraseña
            Connection conect = (Connection) DriverManager.getConnection(url, Constantes.usuario, Constantes.passwd);
            Statement sentencia = conect.createStatement();
            result = sentencia.executeQuery(sql);

            pwd = result.getString(1);

            sentencia.close();
            conect.close();

        } catch (SQLException | ClassNotFoundException ex) {
        }

        return pwd;
    }

    /**
     *
     * M�todo que nos ejecuta la sentencia insert que queremos realizar en
     * nuestra BBDD
     *
     * @param user Usuario a insertar en la bbdd
     *
     */
    public static void insertUser(User user) {

        //realiza la conexi�n con la BD
        Conexion conexion = new Conexion();

        //recoge el Statement devuelto por la conexi�n
        try ( Statement st = conexion.conectar()) {

            //consulta INSERT
            String sql = "INSERT INTO " + Constantes.TablaUsuarios + " VALUES('" + user.getId() + "','" + user.getEmail() + "','" + user.getPassword() + "')";
            //ejecutamos la query
            st.executeUpdate(sql);
            //JOptionPane.showMessageDialog(null, "Operaci�n realizada con �xito", "Sentencia SQL", JOptionPane.INFORMATION_MESSAGE);
            exito = true;

        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException msqli) {

            JOptionPane.showMessageDialog(null, "Ya existe ese registro ", "Sentencia SQL", JOptionPane.ERROR_MESSAGE);
            exito = false;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error de operación " + e.getMessage(), "Sentencia SQL", JOptionPane.ERROR_MESSAGE);
            exito = false;

        }

    }

}
