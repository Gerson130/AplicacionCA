package Clases;

import Model.ConexionDB;
import Vista.LoginFrm;
public class Principal {

 
    public static void main(String[] args) {
        ConexionDB c = new ConexionDB();
        c.establecerConexion();
        
        // Iniciar con la ventana de login
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrm().setVisible(true);
            }
        });
    }
    
}
