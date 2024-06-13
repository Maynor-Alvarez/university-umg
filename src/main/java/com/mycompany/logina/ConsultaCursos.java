/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.logina;

import com.mycompany.logina.LoginA;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/**
 *
 * @author Moises
 */
public class ConsultaCursos extends javax.swing.JFrame {

    /**
     * Creates new form ConsultaCursos
     */
    public ConsultaCursos() {
        initComponents();
        selector();
        CargarTabla();
        
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Carga Masiva JSON");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void CargarTabla(){
    DefaultTableModel data = new DefaultTableModel(new String[]{"ID","Nombre","Seccion","Fecha Inicio","Fecha Fin", "Hora Inicio",
         "Hora Fin", "Profesor"},LoginA.curso.size());
        jTable1.setModel(data);
        
        int row=0;
        
        for(Cursos c : LoginA.curso){
            jTable1.setValueAt(c.id, row, 0);
            jTable1.setValueAt(c.nombre, row, 1); 
            jTable1.setValueAt(c.seccion, row, 2);
            jTable1.setValueAt(c.fechainicio, row, 3);
            jTable1.setValueAt(c.fechafin, row, 4);
            jTable1.setValueAt(c.horaInicio, row, 5);
            jTable1.setValueAt(c.horaFin, row, 6);
            jTable1.setValueAt(c.profesor, row, 7);
            row++;
        }
        }
    private void selector() {
        JMenuItem editar = new JMenuItem("Editar");
    editar.addActionListener((ActionEvent e) -> {
            try {
                ModificarCursos a = new ModificarCursos(jTable1.getSelectedRow());
                a.setVisible(true);
                dispose();
            } catch (ParseException ex) {
                Logger.getLogger(ConsultaCursos.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    jTable1.setComponentPopupMenu(new JPopupMenu());
    jTable1.getComponentPopupMenu().add(editar);
}
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Administrador a = new Administrador();
        a.setVisible(true);
        this.dispose();  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       JFileChooser archivo = new JFileChooser();
        int retorno = archivo.showDialog(this,"Cargar");
        if(retorno==JFileChooser.APPROVE_OPTION){
            leerArchivoJsoncursos(archivo.getSelectedFile().toPath().toString());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed
    public void leerArchivoJsoncursos(String rutaConNombre){
         JSONParser jp = new JSONParser();
        try {
            FileReader r = new FileReader(rutaConNombre);
            Object obj = jp.parse(r);
            
            JSONArray cursosList = (JSONArray) obj;
        
            for (Object o : cursosList) {
                
                JSONObject cursoObj = (JSONObject) o;
                String ID = String.valueOf( cursoObj.get("id"));
                String nombre = (String) cursoObj.get("nombre");
                String seccion = (String) cursoObj.get("seccion");
                String fechaInicio = (String) cursoObj.get("fecha_inicio");
                String fechaFinalizacion = (String) cursoObj.get("fecha_fin");
                String horaInicio = (String) cursoObj.get("hora_inicio");
                String horaFinal = (String) cursoObj.get("hora_fin");
                String profesor = (String) cursoObj.get("profesor");

                Cursos curso = new Cursos();
                curso.id = ID;
                curso.nombre = nombre;
                curso.seccion = seccion;
                curso.fechainicio = fechaInicio;
                curso.fechafin= fechaFinalizacion;
                curso.horaInicio = horaInicio;
                curso.horaFin = horaFinal;
                curso.profesor = profesor;
                LoginA.curso.add(curso);
                 }
             CargarTabla();
            }
        
        catch(Exception e){
            e.printStackTrace();
        }
    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
