package Clases;

import Model.ConexionDB;

import Vista.LoginFrm;

import Vista.CreaUsuarioFrm;
import Vista.MenuPrincipalFrm;

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

        MenuPrincipalFrm ventana = new MenuPrincipalFrm();
        ventana.setVisible(true);

    }
    
}
