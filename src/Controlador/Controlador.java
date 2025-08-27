package Controlador;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.ConexionDB;
import Model.Usuario;

public class Controlador {
    
    public Statement sentencia;
    public ResultSet resultado;
    
    ConexionDB conexion = new ConexionDB();
    
    public int eliminarUsuario (int id) {
        int datos = 0;
        try {
            String query = "DELETE FROM Usuario WHERE Id = "+id;
            sentencia = conexion.establecerConexion().createStatement();
            datos = sentencia.executeUpdate(query);
        } catch (Exception e) {
        }
        return datos;
    }
    
    public ResultSet buscarUsuario (int id) {
    String query = "SELECT * FROM Usuario WHERE Id = "+id;
    try {
        sentencia = conexion.establecerConexion().createStatement();
        resultado = sentencia.executeQuery(query);
    } catch (SQLException e) {
        System.out.println("Error al obtener producto "+ e);
    }
    return resultado;
    }
    
    public int modificarUsuario (Usuario usuario) {
    try {
        StringBuilder sql = new StringBuilder("SELECT * FROM Usuario WHERE Id = "+usuario.Id);
        String query = "UPDATE ";
    
    }
    }
}
