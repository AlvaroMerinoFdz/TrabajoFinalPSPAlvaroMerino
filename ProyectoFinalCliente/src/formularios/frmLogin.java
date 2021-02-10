/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import java.io.IOException;
import java.net.*;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import utiles.Constantes;
import utiles.Utiles;

/**
 *
 * @author Alvaro Merino
 */
public class frmLogin extends javax.swing.JFrame {
    private Socket servidor;
    private PublicKey clavePub;
    private PrivateKey clavePriv;
    private PublicKey clavePubAjena;
    
    public frmLogin() throws UnknownHostException, IOException {
        initComponents();
        InetAddress dir = InetAddress.getLocalHost();
        servidor = new Socket(dir,3000);
    }
    private void obtenerClaves() throws NoSuchAlgorithmException{
        Object[] claves = Utiles.generarClaves();
        clavePriv = (PrivateKey) claves[Constantes.CLAVEPRIVADA];
        clavePub = (PublicKey) claves[Constantes.CLAVEPUBLICA];
    }
    private void intercambioClaves() throws IOException{
        Utiles.enviarObjeto(servidor,clavePub);
        clavePubAjena = (PublicKey) Utiles.recibirObjeto(servidor);
    }
    

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUsuario = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        btnIniciarSesion = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        lblUsuario = new javax.swing.JLabel();
        lblContraseña = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(630, 440));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 160, -1));
        getContentPane().add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 160, -1));

        btnIniciarSesion.setText("Iniciar Sesión");
        getContentPane().add(btnIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, -1, -1));

        btnRegistrar.setText("Registrarse...");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, -1, -1));

        lblUsuario.setForeground(new java.awt.Color(240, 240, 240));
        lblUsuario.setText("Usuario:");
        getContentPane().add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, -1, -1));

        lblContraseña.setForeground(new java.awt.Color(240, 240, 240));
        lblContraseña.setText("Contraseña: ");
        getContentPane().add(lblContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, -1, -1));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.jpg"))); // NOI18N
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 310));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            Utiles.EnviarInt(servidor, Constantes.REGISTRO);
            frmRegistrar reg = new frmRegistrar(servidor,clavePubAjena, clavePriv);
            reg.setVisible(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
