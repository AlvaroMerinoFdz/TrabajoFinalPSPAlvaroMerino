package bbdd;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import utiles.Constantes;

/**
 *
 * @author Alvaro Merino
 */
public class Conexion{

    //Atributos
    private static Connection conect;

    /**
     *
     * Método que nos permite conectarnos a la base de datos
     *
     * @return la conexión con la base de datos si ha sido posible o null si ha
     * dado error
     *
     */
    public static Statement conectar() {

        //Realizamos un try para poder captura posibles excepciones que se den
        try {
            //enlace conexion
            String url = "jdbc:mysql://localhost:3306/" + Constantes.bbdd;
            //cargar el driver
            Class.forName("com.mysql.jdbc.Driver");
            //usuario y contraseña
            conect = (Connection) DriverManager.getConnection(url, Constantes.usuario, Constantes.passwd);
            //creamos la conexión
            Statement statement = conect.createStatement();
            System.out.println("CONECTADO A LA BASE DE DATOS");
            return statement;
        } catch (ClassNotFoundException | SQLException e) {

            JOptionPane.showMessageDialog(null, "Error al conectar con la BASE DE DATOS " + e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            return null;

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "Error al conectar con la BASE DE DATOS " + ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            return null;

        }
    }

    /**
     * Método para desconectar la base de datos.
     *
     * @throws SQLException
     */
    public static void desconectar() throws SQLException {

        try {
            String url = "jdbc:mysql://localhost:3306/" + Constantes.bbdd;
            //cargar el driver
            Class.forName("com.mysql.jdbc.Driver");
            //usuario y contraseña
            conect = (Connection) DriverManager.getConnection(url, Constantes.usuario, Constantes.passwd);
            //Cerramos la conexión
            conect.close();
            System.out.println("desconectado de la BASE DE DATOS");
        } catch (ClassNotFoundException | SQLException e) {

            JOptionPane.showMessageDialog(null, "Error al desconectar con la BASE DE DATOS " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "Error al conectar con la BASE DE DATOS " + ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);

        }
    }
    
}
