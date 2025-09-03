package Clases;

import Model.ConexionDB;
import Vista.CreaUsuarioFrm;
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConexionDB c = new ConexionDB();
        c.establecerConexion();
        CreaUsuarioFrm ventana = new CreaUsuarioFrm();
        ventana.setVisible(true);
    }
    
}
