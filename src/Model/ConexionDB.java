package Model;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConexionDB {
    public String bd = "CA";
    public String url = "jdbc:mysql://localhost:3306/"+bd;
    public String usuario = "root";
    public String clave = "G3rzon23.";
    
    Connection nuevaConexion = null;
    
    public Connection establecerConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            nuevaConexion = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conexión exitosa con: "+bd);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar: "+e.getMessage());
        }
        return nuevaConexion;
    }
    
    public void cerrarConexion() {
        try {
            if (nuevaConexion != null) {
                nuevaConexion.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            System.out.println("Error "+e.getSQLState());
        }
    }
} 
