package Clases;

import Model.ConexionDB;
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConexionDB c = new ConexionDB();
        c.establecerConexion();
    }
    
}
