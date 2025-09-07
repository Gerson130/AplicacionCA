package Clases;

import Model.ConexionDB;
import Vista.CreaUsuarioFrm;
import Vista.MenuPrincipalFrm;
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConexionDB c = new ConexionDB();
        c.establecerConexion();
        MenuPrincipalFrm ventana = new MenuPrincipalFrm();
        ventana.setVisible(true);
    }
    
}
