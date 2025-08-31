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

    public int eliminarUsuario(int id) {
        int datos = 0;
        try {
            String query = "DELETE FROM Usuario WHERE Id = " + id;
            sentencia = conexion.establecerConexion().createStatement();
            datos = sentencia.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error al eliminar el usuario "+ e);
        }
        return datos;
    }

    public ResultSet buscarUsuario(Usuario usuario) {
        try {
            sentencia = conexion.establecerConexion().createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM Usuario WHERE Id = 1");
            if (usuario.getId() > -1) {
                query.append(" AND Id = ").append(usuario.getId());
            }
            if (usuario.getRut() != null && !usuario.getRut().isEmpty()) {
                query.append(" AND Rut = ").append(usuario.getRut());
            }
            if (usuario.getNombre() != null && !usuario.getNombre().isEmpty()) {
                query.append(" AND Nombre = ").append(usuario.getNombre());
            }
            if (usuario.getCargo() != null && !usuario.getCargo().isEmpty()) {
                query.append(" AND Cargo = ").append(usuario.getCargo());
            }
            if (usuario.getArea() != null && !usuario.getArea().isEmpty()) {
                query.append(" AND Area = ").append(usuario.getArea());
            }
            if (usuario.getClave() != null && !usuario.getClave().isEmpty()) {
                query.append(" AND Contrasenia = ").append(usuario.getClave());
            }
            if (usuario.getCorreo() != null && !usuario.getCorreo().isEmpty()) {
                query.append(" AND Correo = ").append(usuario.getClave());
            }
            resultado = sentencia.executeQuery(query.toString());
        } catch (SQLException e) {
            System.out.println("Error al buscar el usuario " + e);
        }
        return resultado;
    }

    public int modificarUsuario(int Id) {
        int datos = 0;
        Usuario usuario = new Usuario();
        try {
            sentencia = conexion.establecerConexion().createStatement();
            String query = "UPDATE Usuario SET Rut = " + usuario.getRut() + " , Nombre = " + usuario.getNombre() + " , Cargo = " + usuario.getCargo() + " , Area = " + usuario.getArea() + " , Contrasenia = " + usuario.getClave() + " , Correo = " + usuario.getCorreo() + " WHERE Id =" + Id;
            datos = sentencia.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error al actualizar el usuario " + e);
        }
        return datos;
    }
       
    public int guardarUsuario(Usuario usuario){
        int datos = 0;
        try {
        sentencia = conexion.establecerConexion().createStatement();
        String query = String.format("INSERT INTO Usuario (Rut, Nombre, Cargo, Area, Clave, Correo) "
                + "VALUES '%s', '%s', '%s', '%s', '%s', '%s'",
                usuario.getRut(),
                usuario.getNombre(),
                usuario.getCargo(),
                usuario.getArea(), 
                usuario.getClave(),
                usuario.getCorreo());

        datos = sentencia.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error al guardar usuario "+e);
        }
        return datos;
    }
}
