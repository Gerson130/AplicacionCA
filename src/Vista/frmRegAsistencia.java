
package Vista;

import Controlador.AsistenciaController;
import Controlador.Controlador;
import Model.Usuario;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmRegAsistencia extends javax.swing.JFrame {


    private Usuario usuarioLogueado;
    private AsistenciaController asistenciaController;
    
    public frmRegAsistencia() {
        initComponents();
        asistenciaController = new AsistenciaController();
    }
    
    // Constructor que recibe el usuario logueado
    public frmRegAsistencia(Usuario usuario) {
        this();
        this.usuarioLogueado = usuario;
        actualizarInterfaz();
    }

    
    private void actualizarInterfaz() {
        if (usuarioLogueado != null) {
            lblUsuario.setText("Usuario: " + usuarioLogueado.getNombre());
            verificarEstadoAsistencia();
        }
    }
    
    private void verificarEstadoAsistencia() {
        boolean entradaRegistrada = asistenciaController.yaRegistroEntradaHoy(usuarioLogueado.getId());
        boolean salidaRegistrada = asistenciaController.yaRegistroSalidaHoy(usuarioLogueado.getId());
        
        if (!entradaRegistrada) {
            lblEstado.setText("Estado: Pendiente de entrada");
            btnEntrada.setEnabled(true);
            btnSalida.setEnabled(false);
        } else if (!salidaRegistrada) {
            lblEstado.setText("Estado: Entrada registrada - Pendiente salida");
            btnEntrada.setEnabled(false);
            btnSalida.setEnabled(true);
        } else {
            lblEstado.setText("Estado: Jornada completa registrada");
            btnEntrada.setEnabled(false);
            btnSalida.setEnabled(false);
        }
    }
    
    private void registrarEntrada() {
        boolean exito = asistenciaController.registrarAsistencia(usuarioLogueado.getId(), "ENTRADA");
        if (exito) {
            JOptionPane.showMessageDialog(this, "Entrada registrada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            verificarEstadoAsistencia();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar entrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void registrarSalida() {
        boolean exito = asistenciaController.registrarAsistencia(usuarioLogueado.getId(), "SALIDA");
        if (exito) {
            JOptionPane.showMessageDialog(this, "Salida registrada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            verificarEstadoAsistencia();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar salida", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        btnEntrada = new javax.swing.JButton();
        btnSalida = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsuario.setText("REGISTRO DE ASISTENCIA");
        mainPanel.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 43, 170, -1));

        lblEstado.setText("Estado");
        mainPanel.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, -1, -1));

        btnEntrada.setText("ENTRADA");
        btnEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntradaActionPerformed(evt);
            }
        });
        mainPanel.add(btnEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        btnSalida.setText("SALIDA");
        btnSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalidaActionPerformed(evt);
            }
        });
        mainPanel.add(btnSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntradaActionPerformed
        registrarEntrada();
    }//GEN-LAST:event_btnEntradaActionPerformed

    private void btnSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalidaActionPerformed
        registrarSalida();
    }//GEN-LAST:event_btnSalidaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmRegAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmRegAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmRegAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmRegAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmRegAsistencia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrada;
    private javax.swing.JButton btnSalida;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
