
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;
import org.bson.types.ObjectId;


public class FrmHome extends javax.swing.JFrame {
    MongoDatabase basedatos;
    MongoCollection<Document> coleccion;
    DefaultTableModel tabling = new DefaultTableModel();
    String idGlobal = "";
    
    public FrmHome() {
        initComponents();
        setBackground(new Color(0,0,0,0));
        btnConfirm.setVisible(false);
        
        try{
            basedatos = new ConexionAtlas(
                    new MongoClientURI("mongodb+srv://MainFrame:WTCT@wintercontingency.hdczx.mongodb.net/U4-P1?retryWrites=true&w=majority") 
            ).getDatabase("U4-P1");
            
            coleccion = basedatos.getCollection("PRODUCTOS");
            
            tabling.addColumn("ID");
            tabling.addColumn("DESCRIPCION");
            tabling.addColumn("PRECIO");
            tabling.addColumn("EXISTENCIA");
            
            
            jTable1.setModel(tabling);
            cargarDatos();
        }catch(Exception e){
            mensaje(e.getMessage());
        }
        
        setLocationRelativeTo(null);
    }
    
    private void mensaje(String m){
        showMessageDialog(this, m);
    }
    
    private void cargarDatos(){
        try{
            MongoCursor<Document> documentos = coleccion.find().iterator();
            
            if(tabling.getRowCount() != 0){
                int total = tabling.getRowCount();
                for(int i = 1; i <= total; i++){
                    tabling.removeRow(0);
                }
            }
            
            while(documentos.hasNext()){
                String[] renglon = new String[4];
                
                ArrayList<Object> doc = new ArrayList(documentos.next().values());
                renglon[0] = doc.get(0).toString();
                renglon[1] = doc.get(1).toString();
                renglon[2] = doc.get(2).toString();
                renglon[3] = doc.get(3).toString();
                
                
                tabling.addRow(renglon);
            }
        }catch(Exception e){
            mensaje(e.getMessage());
        }
    }
    
    private boolean estaSeleccionada(){
        if(jTable1.getSelectedRow() >= 0){
            return true;
        }
        return false;
    }
    
    private boolean eliminar(String id){
        try{
            Document eliminar = new Document("_id", new ObjectId(id));
            
            DeleteResult resultado = coleccion.deleteOne(eliminar);
            if(resultado.getDeletedCount()==1){
                return true;
            }else{
                return false;
            }
            
        }catch(Exception e){
            mensaje(e.getMessage());
        }
        
        return false;
    }
    
    private boolean estaVacio(JTextField tf){
        if(tf.getText().isEmpty()){
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pHeader = new javax.swing.JPanel();
        lTitulo = new javax.swing.JLabel();
        lSalir = new javax.swing.JLabel();
        pBody = new javax.swing.JPanel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtExistencia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        pOptions = new javax.swing.JPanel();
        btnDelete = new javax.swing.JLabel();
        btnAdd = new javax.swing.JLabel();
        btnLoad = new javax.swing.JLabel();
        btnConfirm = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pHeader.setBackground(new java.awt.Color(25, 25, 25));

        lTitulo.setFont(new java.awt.Font("Comfortaa", 0, 24)); // NOI18N
        lTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lTitulo.setText("Productos");

        lSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gfx/icons8_exit_50px.png"))); // NOI18N
        lSalir.setToolTipText("");
        lSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lSalirMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pHeaderLayout = new javax.swing.GroupLayout(pHeader);
        pHeader.setLayout(pHeaderLayout);
        pHeaderLayout.setHorizontalGroup(
            pHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 543, Short.MAX_VALUE)
                .addComponent(lSalir)
                .addGap(19, 19, 19))
        );
        pHeaderLayout.setVerticalGroup(
            pHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pHeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lTitulo)
                    .addComponent(lSalir))
                .addGap(20, 20, 20))
        );

        getContentPane().add(pHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 70));

        pBody.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Descripcion");

        jLabel2.setText("Precio");

        jLabel3.setText("Existencia");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout pBodyLayout = new javax.swing.GroupLayout(pBody);
        pBody.setLayout(pBodyLayout);
        pBodyLayout.setHorizontalGroup(
            pBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBodyLayout.createSequentialGroup()
                .addGap(0, 15, Short.MAX_VALUE)
                .addGroup(pBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pBodyLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(96, 96, 96)
                        .addComponent(jLabel2)
                        .addGap(121, 121, 121)
                        .addComponent(jLabel3))
                    .addGroup(pBodyLayout.createSequentialGroup()
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 15, Short.MAX_VALUE))
        );
        pBodyLayout.setVerticalGroup(
            pBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBodyLayout.createSequentialGroup()
                .addGap(0, 9, Short.MAX_VALUE)
                .addGroup(pBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(6, 6, 6)
                .addGroup(pBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        getContentPane().add(pBody, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 750, 480));

        pOptions.setBackground(new java.awt.Color(25, 25, 25));

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gfx/icons8_delete_document_50px.png"))); // NOI18N
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gfx/icons8_add_50px.png"))); // NOI18N
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });

        btnLoad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gfx/icons8_edit_property_50px.png"))); // NOI18N
        btnLoad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoadMouseClicked(evt);
            }
        });

        btnConfirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gfx/icons8_checked_50px.png"))); // NOI18N
        btnConfirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConfirmMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pOptionsLayout = new javax.swing.GroupLayout(pOptions);
        pOptions.setLayout(pOptionsLayout);
        pOptionsLayout.setHorizontalGroup(
            pOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pOptionsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(pOptionsLayout.createSequentialGroup()
                        .addGroup(pOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pOptionsLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnLoad)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(pOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pOptionsLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConfirm)
                    .addContainerGap()))
        );
        pOptionsLayout.setVerticalGroup(
            pOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pOptionsLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete)
                .addGap(21, 21, 21))
            .addGroup(pOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pOptionsLayout.createSequentialGroup()
                    .addGap(110, 110, 110)
                    .addComponent(btnLoad)
                    .addContainerGap(180, Short.MAX_VALUE)))
            .addGroup(pOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pOptionsLayout.createSequentialGroup()
                    .addGap(177, 177, 177)
                    .addComponent(btnConfirm)
                    .addContainerGap(113, Short.MAX_VALUE)))
        );

        getContentPane().add(pOptions, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 150, 70, 340));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lSalirMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lSalirMouseClicked

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        try{
            if(estaVacio(txtDescripcion)==true){
                mensaje("Error\n\nEl campo esta vacio"); txtDescripcion.requestFocus(); return;
            }if(estaVacio(txtPrecio)){
                mensaje("Error\n\nEl campo esta vacio"); txtPrecio.requestFocus(); return;
            }if(estaVacio(txtExistencia)){
                mensaje("Error\n\nEl campo esta vacio"); txtExistencia.requestFocus(); return;
            }
            
            Document insertar = new Document();
            insertar.put("nombre", txtDescripcion.getText());
            insertar.put("domicilio", txtPrecio.getText());
            insertar.put("edad", txtExistencia.getText());
            
            coleccion.insertOne(insertar);
            
            txtDescripcion.setText("");
            txtPrecio.setText("");
            txtExistencia.setText("");
            
            mensaje("Se inserto correctamente el registro");
            cargarDatos();
        }catch(Exception e){
            mensaje(e.getMessage());
        }
    }//GEN-LAST:event_btnAddMouseClicked

    private void btnLoadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoadMouseClicked
        if(!estaSeleccionada()){
            mensaje("Error\n\nDebes seleccionar una fila antes para actualizar.");
            return;
        }
        
        int fila = jTable1.getSelectedRow();
        idGlobal = tabling.getValueAt(fila, 0).toString();
        
        txtDescripcion.setText(tabling.getValueAt(fila, 1).toString());
        txtPrecio.setText(tabling.getValueAt(fila, 2).toString());
        txtExistencia.setText(tabling.getValueAt(fila, 3).toString());
        
        btnAdd.setVisible(false);
        btnConfirm.setVisible(true);
    }//GEN-LAST:event_btnLoadMouseClicked

    private void btnConfirmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmMouseClicked
        try{
            if(idGlobal.isEmpty()){
                mensaje("Error\n\nID esta vacio");
                return;
            }
            
            // Para actualizar se requieren 2 documentos:
            // 1.- Contiene el ID a actualizar
            // 2.- Contiene la DATA sin ID que se va a actualizar
            
            Document filtro = new Document("_id", new ObjectId(idGlobal));
            Document dataActualizar = new Document();
            
            dataActualizar.put("nombre", txtDescripcion.getText());
            dataActualizar.put("domicilio", txtPrecio.getText());
            dataActualizar.put("edad", txtExistencia.getText());
            
            UpdateResult resultado = coleccion.updateOne(filtro, new Document("$set", dataActualizar));
            if(resultado.getMatchedCount()==1){
                mensaje("Se actualizo con exito");
                cargarDatos();
                
                txtDescripcion.setText("");
                txtPrecio.setText("");
                txtExistencia.setText("");
                
                btnAdd.setVisible(true);
                btnConfirm.setVisible(false);
            }else{
                mensaje("Error\nNo se pudo actualizar");
                
            }
        }catch(Exception e){
            mensaje(e.getMessage());
        }
    }//GEN-LAST:event_btnConfirmMouseClicked

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        if(!estaSeleccionada()){
            mensaje("Error\n\nDebes seleccionar una fila antes para borrar.");
            return;
        }
        
        int fila = jTable1.getSelectedRow();
        idGlobal = tabling.getValueAt(fila, 0).toString();
        int resp = showConfirmDialog(this, "¿Estas seguro que deseas borrar el ID: " + idGlobal + "?");
        if(resp == JOptionPane.OK_OPTION){
            if(eliminar(idGlobal)){
                mensaje("Se elimino correctamente");
                cargarDatos();
            }else{
                mensaje("Error\n\nNo se pudo eliminar el id: " + idGlobal + "?");
            }
        }
    }//GEN-LAST:event_btnDeleteMouseClicked

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAdd;
    private javax.swing.JLabel btnConfirm;
    private javax.swing.JLabel btnDelete;
    private javax.swing.JLabel btnLoad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lSalir;
    private javax.swing.JLabel lTitulo;
    private javax.swing.JPanel pBody;
    private javax.swing.JPanel pHeader;
    private javax.swing.JPanel pOptions;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtExistencia;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
