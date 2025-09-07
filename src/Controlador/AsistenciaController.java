
package Controlador;

import Model.AsistenciaModel;
import Model.ConexionDB;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class AsistenciaController {
    
    private Connection conexion;
    private Statement sentencia;
    
    public AsistenciaController() {
        ConexionDB conexionDB = new ConexionDB();
        conexion = conexionDB.establecerConexion();
    }
    
    // Registrar entrada o salida
    public boolean registrarAsistencia(int idUsuario, String accion) {
        try {
            LocalDate fecha = LocalDate.now();
            LocalTime hora = LocalTime.now();
            
            String query = "INSERT INTO Asistencia (idUsuario, accion, fecha, hora) " +
                         "VALUES (" + idUsuario + ", '" + accion + "', '" + fecha + "', '" + hora + "')";
            
            sentencia = conexion.createStatement();
            int filasAfectadas = sentencia.executeUpdate(query);
            
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Error al registrar asistencia: " + e.getMessage());
            return false;
        }
    }
    
    // Verificar si ya registró entrada hoy
    public boolean yaRegistroEntradaHoy(int idUsuario) {
        try {
            LocalDate hoy = LocalDate.now();
            String query = "SELECT * FROM Asistencia WHERE idUsuario = " + idUsuario + 
                         " AND fecha = '" + hoy + "' AND accion = 'ENTRADA'";
            
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(query);
            
            return resultado.next();
            
        } catch (SQLException e) {
            System.out.println("Error al verificar entrada: " + e.getMessage());
            return false;
        }
    }
    
    // Verificar si ya registró salida hoy
    public boolean yaRegistroSalidaHoy(int idUsuario) {
        try {
            LocalDate hoy = LocalDate.now();
            String query = "SELECT * FROM Asistencia WHERE idUsuario = " + idUsuario + 
                         " AND fecha = '" + hoy + "' AND accion = 'SALIDA'";
            
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(query);
            
            return resultado.next();
            
        } catch (SQLException e) {
            System.out.println("Error al verificar salida: " + e.getMessage());
            return false;
        }
    }
    
}
