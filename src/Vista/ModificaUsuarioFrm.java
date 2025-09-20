package Vista;

import javax.swing.JOptionPane;
import Controlador.Controlador;
import Model.Usuario;
import Model.ConexionDB;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gerso
 */
public class ModificaUsuarioFrm extends javax.swing.JDialog {

    Controlador servicio = new Controlador();
    Usuario usuario = new Usuario();
    ConexionDB conexion = new ConexionDB();

    public ResultSet resultado;
    public Statement sentencia;

    /**
     * Creates new form ModificaUsuarioFrm
     */
    public ModificaUsuarioFrm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        rdo10.setSelected(true);
        txtRut9.setEnabled(false);
        rdoBuscar.setSelected(true);
        lblAvanzado.setVisible(false);
        lblContrasenia.setVisible(false);
        txtClave.setVisible(false);
        btnEliminar.setVisible(false);
        btnModificar.setVisible(false);
        rdoModificar.setEnabled(false);
        mostrarTodosLosUsuarios();
    }
    
    public void mostrarTodosLosUsuarios() {
        DefaultTableModel nuevoModelo = new DefaultTableModel();
        String[] lista = new String[7];
        
        nuevoModelo.addColumn("Id");
        nuevoModelo.addColumn("Rut");
        nuevoModelo.addColumn("Nombre");
        nuevoModelo.addColumn("Cargo");
        nuevoModelo.addColumn("Área");
        nuevoModelo.addColumn("Clave");
        nuevoModelo.addColumn("Correo");
        tblUsuarios.setModel(nuevoModelo);
        
        try {
            ResultSet resultado = servicio.MostrarDatosDeUsuario();
            
            while (resultado.next()) {
                lista[0] = resultado.getString(1);
                lista[1] = resultado.getString(2);
                lista[2] = resultado.getString(3);
                lista[3] = resultado.getString(4);
                lista[4] = resultado.getString(5);
                lista[5] = resultado.getString(6);
                lista[6] = resultado.getString(7);
                nuevoModelo.addRow(lista);
                tblUsuarios.setModel(nuevoModelo);
            }
        } catch (SQLException e) {
            System.out.println("Error al llenar tabla"+e);
        }
    }

    public void buscarUsuario() {
        String rut = "";
        String nombre = txtNombre.getText();
        String cargo = txtCargo.getText();
        String area = txtArea.getText();
        String correo = txtCorreo.getText();

        if (txtRut10.getText().endsWith(" ")) {
            rut = txtRut9.getText();
        }
        if (txtRut9.getText().endsWith(" ")) {
            rut = txtRut10.getText();
        }

        usuario.setRut(rut);
        usuario.setNombre(nombre);
        usuario.setCargo(cargo);
        usuario.setArea(area);
        usuario.setCorreo(correo);

        try {
            ResultSet busqueda = servicio.buscarUsuario(usuario);

            String[] datosBD = new String[7];

            DefaultTableModel nuevoModelo = new DefaultTableModel();

            nuevoModelo.addColumn("Id");
            nuevoModelo.addColumn("Rut");
            nuevoModelo.addColumn("Nombre");
            nuevoModelo.addColumn("Cargo");
            nuevoModelo.addColumn("Área");
            nuevoModelo.addColumn("Clave");
            nuevoModelo.addColumn("Correo");

            tblUsuarios.setModel(nuevoModelo);

            while (busqueda.next() == true) {
                datosBD[0] = busqueda.getString(1);
                datosBD[1] = busqueda.getString(2);
                datosBD[2] = busqueda.getString(3);
                datosBD[3] = busqueda.getString(4);
                datosBD[4] = busqueda.getString(5);
                datosBD[5] = busqueda.getString(6);
                datosBD[6] = busqueda.getString(7);
                nuevoModelo.addRow(datosBD);
                tblUsuarios.setModel(nuevoModelo);
            }

        } catch (SQLException e) {
            System.out.println("Error al encontrar usuarios");
        }
    }

    public void actualizarUsuario() {
        Usuario usuario = new Usuario();
        String rut = "";
        String nombre = txtNombre.getText().trim();
        String cargo = txtCargo.getText().trim();
        String area = txtArea.getText().trim();
        String clave = txtClave.getText().trim();
        String correo = txtCorreo.getText().trim();

        if (txtRut10.getText().endsWith(" ")) {
            rut = txtRut9.getText();
        }
        if (txtRut9.getText().endsWith(" ")) {
            rut = txtRut10.getText();
        }

        String id = tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 0).toString();
        System.out.println("" + id);

        usuario.setRut(rut);
        usuario.setNombre(nombre);
        usuario.setCargo(cargo);
        usuario.setArea(area);
        usuario.setClave(clave);
        usuario.setCorreo(correo);

        try {
            int modificacion = servicio.modificarUsuario(usuario, Integer.parseInt(id));
            if (modificacion == 0) {
                JOptionPane.showMessageDialog(this, "Error", "Error al guardar los datos", JOptionPane.ERROR_MESSAGE);
            }
            if (modificacion == -1) {
                JOptionPane.showMessageDialog(this, "Complete el formulario porfavor", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
            if (modificacion == -2) {
                JOptionPane.showMessageDialog(this, "El correo ingresado no cumple con formato", "Información", JOptionPane.WARNING_MESSAGE);
            }
            if (modificacion == -3) {
                JOptionPane.showMessageDialog(this, "El rut no se encuentra disponible", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (modificacion == 1) {
                JOptionPane.showMessageDialog(this, "Los datos han sido guardados exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                mostrarTodosLosUsuarios();
            }
        } catch (Exception e) {
            System.err.println("Error al acgtualizar datos " + e);
        }
    }

    public int obtenerId(int id) {
        return id;
    }

    public void eliminarUsuario() {
        int fila = tblUsuarios.getSelectedRow();
        int id = Integer.parseInt(tblUsuarios.getValueAt(fila, 0).toString());
        if (id == 0) {
            JOptionPane.showMessageDialog(this, "Error", "El usuario no se encuentra", JOptionPane.ERROR_MESSAGE);
        } else {
            servicio.eliminarUsuario(id);
            JOptionPane.showMessageDialog(this, "Información", "Se eliminó correctamente", JOptionPane.INFORMATION_MESSAGE);
            mostrarTodosLosUsuarios();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupRut = new javax.swing.ButtonGroup();
        btnGroupAvanzado = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        txtNombre = new javax.swing.JTextField();
        txtCargo = new javax.swing.JTextField();
        txtArea = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtRut9 = new javax.swing.JFormattedTextField();
        txtRut10 = new javax.swing.JFormattedTextField();
        rdo9 = new javax.swing.JRadioButton();
        rdo10 = new javax.swing.JRadioButton();
        lblNombre = new javax.swing.JLabel();
        lblCargo = new javax.swing.JLabel();
        lblArea = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        lblRut10 = new javax.swing.JLabel();
        lblRut9 = new javax.swing.JLabel();
        rdoModificar = new javax.swing.JRadioButton();
        rdoBuscar = new javax.swing.JRadioButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        txtClave = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        lblBusqueda = new javax.swing.JLabel();
        lblContrasenia = new javax.swing.JLabel();
        lblAvanzado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBuscar.setText("Buscar");
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarMouseClicked(evt);
            }
        });
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, -1, -1));

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblUsuarios);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(409, 35, -1, 330));

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 126, -1));

        txtCargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCargoKeyTyped(evt);
            }
        });
        jPanel1.add(txtCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 126, -1));

        txtArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAreaKeyTyped(evt);
            }
        });
        jPanel1.add(txtArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 126, -1));

        txtCorreo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCorreoFocusLost(evt);
            }
        });
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoKeyTyped(evt);
            }
        });
        jPanel1.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 126, -1));

        try {
            txtRut9.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#######-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel1.add(txtRut9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 126, -1));

        try {
            txtRut10.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtRut10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtRut10FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRut10FocusLost(evt);
            }
        });
        txtRut10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRut10MouseClicked(evt);
            }
        });
        txtRut10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRut10KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRut10KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRut10KeyTyped(evt);
            }
        });
        jPanel1.add(txtRut10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 126, -1));

        btnGroupRut.add(rdo9);
        rdo9.setText("9 digitos");
        rdo9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdo9MouseClicked(evt);
            }
        });
        rdo9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo9ActionPerformed(evt);
            }
        });
        jPanel1.add(rdo9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 98, -1));

        btnGroupRut.add(rdo10);
        rdo10.setText("10 digitos");
        rdo10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdo10MouseClicked(evt);
            }
        });
        jPanel1.add(rdo10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 98, -1));

        lblNombre.setText("Nombre");
        jPanel1.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        lblCargo.setText("Cargo");
        jPanel1.add(lblCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 66, -1));

        lblArea.setText("Área");
        jPanel1.add(lblArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 37, -1));

        lblCorreo.setText("Correo");
        jPanel1.add(lblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 57, -1));

        lblRut10.setText("Rut de 10 digitos");
        jPanel1.add(lblRut10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 117, -1));

        lblRut9.setText("Rut de 9 digitos");
        jPanel1.add(lblRut9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 113, -1));

        btnGroupAvanzado.add(rdoModificar);
        rdoModificar.setText("Modificar");
        rdoModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoModificarMouseClicked(evt);
            }
        });
        jPanel1.add(rdoModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 90, -1));

        btnGroupAvanzado.add(rdoBuscar);
        rdoBuscar.setText("Buscar");
        rdoBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoBuscarMouseClicked(evt);
            }
        });
        jPanel1.add(rdoBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 70, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, 100, -1));

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, 100, -1));

        txtClave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClaveKeyTyped(evt);
            }
        });
        jPanel1.add(txtClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 126, -1));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 126, -1));

        lblBusqueda.setBorder(javax.swing.BorderFactory.createTitledBorder("Búsqueda de trabajador"));
        jPanel1.add(lblBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 340, 340));

        lblContrasenia.setText("Contraseña");
        jPanel1.add(lblContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 80, -1));

        lblAvanzado.setBorder(javax.swing.BorderFactory.createTitledBorder("Modificar o eliminar trabajador"));
        jPanel1.add(lblAvanzado, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 340, 340));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdo10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdo10MouseClicked
        if (rdo10.isSelected()) {
            txtRut10.setEnabled(true);
            txtRut9.setEnabled(false);
            txtRut9.setText("");
        }
    }//GEN-LAST:event_rdo10MouseClicked

    private void rdo9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo9ActionPerformed

    }//GEN-LAST:event_rdo9ActionPerformed

    private void rdo9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdo9MouseClicked
        if (rdo9.isSelected()) {
            txtRut10.setEnabled(false);
            txtRut9.setEnabled(true);
            txtRut10.setText("");
        }
    }//GEN-LAST:event_rdo9MouseClicked

    private void txtRut10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRut10KeyTyped

    }//GEN-LAST:event_txtRut10KeyTyped

    private void txtRut10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRut10KeyReleased

    }//GEN-LAST:event_txtRut10KeyReleased

    private void txtRut10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRut10KeyPressed

    }//GEN-LAST:event_txtRut10KeyPressed

    private void txtRut10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRut10MouseClicked

    }//GEN-LAST:event_txtRut10MouseClicked

    private void txtRut10FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRut10FocusLost

    }//GEN-LAST:event_txtRut10FocusLost

    private void txtRut10FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRut10FocusGained

    }//GEN-LAST:event_txtRut10FocusGained

    private void txtCorreoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusLost

    }//GEN-LAST:event_txtCorreoFocusLost

    private void btnBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseClicked

    }//GEN-LAST:event_btnBuscarMouseClicked

    private void rdoModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoModificarMouseClicked
        if (rdoModificar.isSelected()) {
            lblAvanzado.setVisible(true);
            lblBusqueda.setVisible(false);
            btnBuscar.setVisible(false);
            lblContrasenia.setVisible(true);
            txtClave.setVisible(true);
            btnEliminar.setVisible(true);
            btnModificar.setVisible(true);
            btnLimpiar.setEnabled(false);
        }
    }//GEN-LAST:event_rdoModificarMouseClicked

    private void rdoBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoBuscarMouseClicked
        if (rdoBuscar.isSelected()) {
            lblAvanzado.setVisible(false);
            lblBusqueda.setVisible(true);
            btnBuscar.setVisible(true);
            lblContrasenia.setVisible(false);
            txtClave.setVisible(false);
            btnEliminar.setVisible(false);
            btnModificar.setVisible(false);
            btnLimpiar.setEnabled(true);
        }
    }//GEN-LAST:event_rdoBuscarMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarUsuario();
    }//GEN-LAST:event_btnBuscarActionPerformed
//
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtArea.setText("");
        txtCargo.setText("");
        txtClave.setText("");
        txtCorreo.setText("");
        txtNombre.setText("");
        txtRut10.setText("");
        txtRut9.setText("");
        rdoModificar.setEnabled(false);
        mostrarTodosLosUsuarios();

    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked

        int fila = tblUsuarios.getSelectedRow();
        if (fila > 0) {
            rdoModificar.setEnabled(true);
            String rut = tblUsuarios.getValueAt(fila, 1).toString();
            if (rut.length() == 9) {
                txtRut9.setText(rut);
                txtRut10.setText("");
                txtRut9.setEnabled(true);
                rdo9.setSelected(true);
                txtRut10.setEnabled(false);
            } else {
                txtRut10.setText(rut);
                txtRut9.setText("");
                txtRut10.setEnabled(true);
                rdo10.setSelected(true);
                txtRut9.setEnabled(false);
            }
            txtNombre.setText(tblUsuarios.getValueAt(fila, 2).toString());
            txtCargo.setText(tblUsuarios.getValueAt(fila, 3).toString());
            txtArea.setText(tblUsuarios.getValueAt(fila, 4).toString());
            txtClave.setText(tblUsuarios.getValueAt(fila, 5).toString());
            txtCorreo.setText(tblUsuarios.getValueAt(fila, 6).toString());

            int id = Integer.parseInt(tblUsuarios.getValueAt(fila, 0).toString());
            System.out.println(id);
        }
    }//GEN-LAST:event_tblUsuariosMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        actualizarUsuario();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarUsuario();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        String largo = txtNombre.getText();
        char caracter = evt.getKeyChar();
        if (largo.length() >= 50) {
            evt.consume();
        }
        if (caracter < 'A' && caracter < 'a' && caracter > '0'  ){
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtCargoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCargoKeyTyped
        String largo = txtCargo.getText();
        char caracter = evt.getKeyChar();
        
        if (largo.length() >= 30) {
            evt.consume();
        }
        if (caracter < 'A' && caracter < 'a' && caracter > '0') {
            evt.consume();
        }
    }//GEN-LAST:event_txtCargoKeyTyped

    private void txtAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAreaKeyTyped
        String largo = txtArea.getText();
        char caracter = evt.getKeyChar();
        
        if (largo.length() >= 30) {
            evt.consume();
        }
        if (caracter < 'A' && caracter < 'a' && caracter > '0') {
            evt.consume();
        }
    }//GEN-LAST:event_txtAreaKeyTyped

    private void txtCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyTyped
        String largo = txtCorreo.getText();
        char caracter = evt.getKeyChar();
        
        if (largo.length() >= 30) {
            evt.consume();
        }
        if (caracter == ' ') {
            evt.consume();
        }
    }//GEN-LAST:event_txtCorreoKeyTyped

    private void txtClaveKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClaveKeyTyped
        String largo = txtClave.getText();
        
        if (largo.length() >= 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtClaveKeyTyped

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
            java.util.logging.Logger.getLogger(ModificaUsuarioFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificaUsuarioFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificaUsuarioFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificaUsuarioFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ModificaUsuarioFrm dialog = new ModificaUsuarioFrm(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.ButtonGroup btnGroupAvanzado;
    private javax.swing.ButtonGroup btnGroupRut;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblArea;
    private javax.swing.JLabel lblAvanzado;
    private javax.swing.JLabel lblBusqueda;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblContrasenia;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRut10;
    private javax.swing.JLabel lblRut9;
    private javax.swing.JRadioButton rdo10;
    private javax.swing.JRadioButton rdo9;
    private javax.swing.JRadioButton rdoBuscar;
    private javax.swing.JRadioButton rdoModificar;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtArea;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtClave;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JFormattedTextField txtRut10;
    private javax.swing.JFormattedTextField txtRut9;
    // End of variables declaration//GEN-END:variables
}
