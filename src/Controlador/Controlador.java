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
            System.out.println("Error al eliminar el usuario " + e);
        }
        return datos;
    }

    public ResultSet buscarUsuario(Usuario usuario) {
        try {
            StringBuilder query = new StringBuilder("SELECT * FROM Usuario WHERE 1");
            if (usuario.getRut() != null && !usuario.getRut().isEmpty() && !usuario.getRut().endsWith(" ")) {
                query.append(" AND Rut = '").append(usuario.getRut()).append("'");
            }
            if (usuario.getNombre() != null && !usuario.getNombre().isEmpty()) {
                query.append(" AND Nombre = '").append(usuario.getNombre()).append("'");
            }
            if (usuario.getCargo() != null && !usuario.getCargo().isEmpty()) {
                query.append(" AND Cargo = '").append(usuario.getCargo()).append("'");
            }
            if (usuario.getArea() != null && !usuario.getArea().isEmpty()) {
                query.append(" AND Area = '").append(usuario.getArea()).append("'");
            }
            if (usuario.getCorreo() != null && !usuario.getCorreo().isEmpty()) {
                query.append(" AND Correo = '").append(usuario.getClave()).append("'");
            }
            sentencia = conexion.establecerConexion().createStatement();
            resultado = sentencia.executeQuery(query.toString());
            System.out.println(query.toString());
        } catch (SQLException e) {
            System.out.println("Error al buscar el usuario " + e);
        }
        return resultado;
    }

    public ResultSet validarRutUsuario(String rut) {
        try {
            String query = "SELECT * FROM Usuario WHERE Rut = '" + rut + "'";
            sentencia = conexion.establecerConexion().createStatement();
            resultado = sentencia.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Error de validación " + e);
        }
        return resultado;
    }

    public boolean validarCorreoUsuario(String correo) {
        boolean valido = true;
        if (correo.contains("@")) {
            valido = false;
        }
        return valido;
    }

    public int modificarUsuario(Usuario usuario, int id) {
        int datos = 0;
        try {
            sentencia = conexion.establecerConexion().createStatement();
            String query = "UPDATE Usuario SET Rut = '" + usuario.getRut() + "' , Nombre = '" + usuario.getNombre() + "' , Cargo = '" + usuario.getCargo() + "' , Area = '" + usuario.getArea() + "' , Clave = '" + usuario.getClave() + "' , Correo = '" + usuario.getCorreo() + "' WHERE Id =" + id;
            datos = sentencia.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error al actualizar el usuario " + e);
        }
        return datos;
    }

    public int guardarUsuario(Usuario usuario) {
        int datos = 0;
        try {
            ResultSet validacionRut = validarRutUsuario(usuario.getRut());
            boolean validacionCorreo = validarCorreoUsuario(usuario.getCorreo());
            if (validacionRut.next()) {
                datos = -1;
            }
            else if (validacionCorreo) {
                datos = -2;
            }
            else {
                sentencia = conexion.establecerConexion().createStatement();
                String query2 = String.format("INSERT INTO Usuario (Rut, Nombre, Cargo, Area, Clave, Correo) "
                        + "VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
                        usuario.getRut(),
                        usuario.getNombre(),
                        usuario.getCargo(),
                        usuario.getArea(),
                        usuario.getClave(),
                        usuario.getCorreo());
                datos = sentencia.executeUpdate(query2);
            }
            System.out.println(datos);
        } catch (Exception e) {
            System.out.println("Error al guardar usuario " + e);
        }
        return datos;
    }
    
    // Método Validar Login
    public boolean validarLogin(String correo, String clave) {
        try {
            String query = "SELECT * FROM Usuario WHERE correo = '" + correo + "' AND clave = '" + clave + "'";
            sentencia = conexion.establecerConexion().createStatement();
            resultado = sentencia.executeQuery(query);
            
            return resultado.next();
            
        } catch (SQLException e) {
            System.out.println("Error al validar login: " + e.getMessage());
            return false;
        }
    }

    // Método Obtener Usuario por Correo
    public Usuario obtenerUsuarioPorCorreo(String correo) {
        Usuario usuario = null;
        try {
            String query = "SELECT * FROM Usuario WHERE correo = '" + correo + "'";
            sentencia = conexion.establecerConexion().createStatement();
            resultado = sentencia.executeQuery(query);
            
            if (resultado.next()) {
                usuario = new Usuario();
                usuario.setId(resultado.getInt("id"));
                usuario.setRut(resultado.getString("rut"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setCargo(resultado.getString("cargo"));
                usuario.setArea(resultado.getString("area"));
                usuario.setClave(resultado.getString("clave"));
                usuario.setCorreo(resultado.getString("correo"));
            }
            
        } catch (SQLException e) {
            System.out.println("Error al obtener usuario: " + e.getMessage());
        }
        return usuario;
    }
    
    public ResultSet MostrarDatosDeUsuario() {
        String query = "SELECT * FROM Usuario";
        try {
            sentencia = conexion.establecerConexion().createStatement();
            resultado = sentencia.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Error al mostrar datos"+e);
        }
        return resultado;
    }

    
}
