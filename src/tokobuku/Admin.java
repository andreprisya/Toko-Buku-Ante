/*
    * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokobuku;

import java.awt.HeadlessException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Andre
 */
public class Admin extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Admin() {
        initComponents();
        CustomerPanel.setVisible(true);
        cdul.setVisible(true);
        cmenbuy.setVisible(true);
        cmenrent.setVisible(false);
        buku.setVisible(false);
        bdul.setVisible(false);
        bmen.setVisible(false);
        supplier.setVisible(false);
        sdul.setVisible(false);
        smen.setVisible(false);
        TampilDataCustB();
        TambahNamabuk();
        this.setLocationRelativeTo(null);
    }
    
    public void KosongBuku(){
        idbuk.setText(null);
        nambuk.setText(null);
        bukgen.setText(null);
        bukpeng.setText(null);
        harbuk.setText(null);
        namsup.setSelectedItem(this);
        pen.setText(null);
        jTextField2.setText(null);
    }
    
    public void KosongSupp(){
        supid.setText(null);
        supnam.setText(null);
        supcit.setText(null);
        supno.setText(null);
    }
    
    public void KosongCustB(){
        idcusbuy.setText(null);
        namcusbu.setText(null);
        nocusbu.setText(null);
        jmbuk.setText(null);
        ttlpem.setText(null);
        jubuk.setSelectedItem(this);
    }
    
    public void KosongCustR(){
        jTextField5.setText(null);
        jTextField16.setText(null);
        jTextField17.setText(null);
        jTextField18.setText(null);
        jTextField19.setText(null);
        jTextField20.setText(null);
        jTextField1.setText(null);
    }
    
    public void TampilDataBuku(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Buku");
        model.addColumn("Judul Buku");
        model.addColumn("Genre Buku");
        model.addColumn("Pengarang");
        model.addColumn("Penerbit");
        model.addColumn("Harga Buku");
        model.addColumn("Id Supplier");
        model.addColumn("Jumlah Buku");
    
        //Menampilkan data pada database ke dalam tabel
        try {
            String sql="SELECT * FROM buku";
            java.sql.Connection conn=(Connection)Config.configDB();
            
            java.sql.Statement stm=conn.createStatement();
            
            java.sql.ResultSet res=stm.executeQuery(sql);
            
           while (res.next()){
           model.addRow(new Object[]{
               res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
                res.getString(5),
                res.getString(6),
                res.getString(7),
                res.getString(8)});
            }
            jTbuku.setModel(model);
        
        } catch(SQLException e){
            System.out.println("Error " + e.getMessage());
        }
    }
    
    public void TambahNamasupp(){
        try{
            String sql="SELECT * FROM supplier";
        
            java.sql.Connection conn=(java.sql.Connection)Config.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            ResultSet res = pstm.executeQuery();
                while(res.next()){
                namsup.addItem(res.getString("nama_supplier"));
                }          
            res.last();
            int jumlahdata = res.getRow();
            res.first();
        } catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void TambahNamabuk(){
        try{
            String sql="SELECT * FROM buku";
        
            java.sql.Connection conn=(java.sql.Connection)Config.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            ResultSet res = pstm.executeQuery();
                while(res.next()){
                    jubuk.addItem(res.getString("judul_buku"));
                }          
            res.last();
            int jumlahdata = res.getRow();
            res.first();
        } catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void TampilDataSupp(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Supplier");
        model.addColumn("Nama Supplier");
        model.addColumn("Alamat Supplier");
        model.addColumn("No Telepon");

    
        //Menampilkan data pada database ke dalam tabel
        try {
            String sql="SELECT * FROM supplier";
            java.sql.Connection conn=(Connection)Config.configDB();
            
            java.sql.Statement stm=conn.createStatement();
            
            java.sql.ResultSet res=stm.executeQuery(sql);
            
           while (res.next()){
           model.addRow(new Object[]{
               res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4)});
            }
            jTsupplier.setModel(model);
        
        } catch(SQLException e){
            System.out.println("Error " + e.getMessage());
        }
    }
    
    public void TampilDataCustB(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Customer");
        model.addColumn("Nama Customer");
        model.addColumn("No Telepon");
        model.addColumn("Tanggal Transaksi");
        model.addColumn("Id Buku");
        model.addColumn("Jumlah Buku");
        model.addColumn("Total Harga");

    
        //Menampilkan data pada database ke dalam tabel
        try {
            String sql="SELECT * FROM cust_beli";
            java.sql.Connection conn=(Connection)Config.configDB();
            
            java.sql.Statement stm=conn.createStatement();
            
            java.sql.ResultSet res=stm.executeQuery(sql);
            
           while (res.next()){
           model.addRow(new Object[]{
               res.getString(1),
               res.getString(2),
               res.getString(3),
               res.getString(4),
               res.getString(5),
               res.getString(6),
               res.getString(7)});
            }
            jTcust_buy.setModel(model);
        
        } catch(SQLException e){
            System.out.println("Error " + e.getMessage());
        }
    }
    
    public void TampilDataCustR(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Customer");
        model.addColumn("Nama Customer");
        model.addColumn("No Telepon");
        model.addColumn("Tanggal Peminjaman");
        model.addColumn("Id Buku");
        model.addColumn("Lama Peminjaman(hari)");
        model.addColumn("Alamat Customer");
        model.addColumn("Total Harga");
        

    
        //Menampilkan data pada database ke dalam tabel
        try {
            String sql="SELECT * FROM cust_sewa";
            java.sql.Connection conn=(Connection)Config.configDB();
            
            java.sql.Statement stm=conn.createStatement();
            
            java.sql.ResultSet res=stm.executeQuery(sql);
            
           while (res.next()){
           model.addRow(new Object[]{
               res.getString(1),
               res.getString(2),
               res.getString(3),
               res.getString(4),
               res.getString(5),
               res.getString(6),
               res.getString(7),
               res.getString(8)});
            }
            jTable4.setModel(model);
        
        } catch(SQLException e){
            System.out.println("Error " + e.getMessage());
        }
    }
    
    public static Date getTanggalFromTable(JTable table, int kolom) {
        JTable tabel = table;
        String str_tgl = String.valueOf(tabel.getValueAt(tabel.getSelectedRow(), kolom));
        Date tanggal = null;
        try {
            tanggal = new SimpleDateFormat("yyyy-MM-dd").parse(str_tgl);

        }
        catch (ParseException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tanggal;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButton5 = new javax.swing.JRadioButton();
        mainPanel = new javax.swing.JPanel();
        tampilPanel = new javax.swing.JPanel();
        supplier = new javax.swing.JPanel();
        sdul = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        smen = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTsupplier = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        supnam = new javax.swing.JTextField();
        supid = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        supcit = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        supno = new javax.swing.JTextField();
        buku = new javax.swing.JPanel();
        bdul = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        bmen = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTbuku = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        idbuk = new javax.swing.JTextField();
        nambuk = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        bukgen = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        bukpeng = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        pen = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        namsup = new javax.swing.JComboBox<>();
        harbuk = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        CustomerPanel = new javax.swing.JPanel();
        cdul = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cmenrent = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton14 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jTextField1 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        cmenbuy = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTcust_buy = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        namcusbu = new javax.swing.JTextField();
        idcusbuy = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nocusbu = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ttlpem = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jmbuk = new javax.swing.JTextField();
        jubuk = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jdcusbuy = new com.toedter.calendar.JDateChooser();
        btnCetakBuy = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        AdminPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jRadioButton5.setText("jRadioButton5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(150, 30));

        supplier.setBackground(new java.awt.Color(102, 102, 102));

        sdul.setBackground(new java.awt.Color(102, 102, 102));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Supplier");

        javax.swing.GroupLayout sdulLayout = new javax.swing.GroupLayout(sdul);
        sdul.setLayout(sdulLayout);
        sdulLayout.setHorizontalGroup(
            sdulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sdulLayout.createSequentialGroup()
                .addContainerGap(115, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(101, 101, 101))
        );
        sdulLayout.setVerticalGroup(
            sdulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sdulLayout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        smen.setBackground(new java.awt.Color(153, 153, 153));

        jTsupplier.setModel(new javax.swing.table.DefaultTableModel(
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
        jTsupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTsupplierMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTsupplier);

        jButton8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton8.setText("Tambah");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton9.setText("Hapus");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton10.setText("Edit");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Nama Supplier :");

        supnam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supnamActionPerformed(evt);
            }
        });

        supid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supidActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("Id Supplier :");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("Alamat Supplier :");

        supcit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supcitActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText("No Telp :");

        javax.swing.GroupLayout smenLayout = new javax.swing.GroupLayout(smen);
        smen.setLayout(smenLayout);
        smenLayout.setHorizontalGroup(
            smenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(smenLayout.createSequentialGroup()
                .addGap(307, 307, 307)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 356, Short.MAX_VALUE))
            .addGroup(smenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(smenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(smenLayout.createSequentialGroup()
                        .addGroup(smenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, smenLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(389, 389, 389))
                            .addGroup(smenLayout.createSequentialGroup()
                                .addGroup(smenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(supnam, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                    .addComponent(supid))
                                .addGap(78, 78, 78)))
                        .addGroup(smenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14)
                            .addComponent(supcit, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                            .addComponent(supno))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        smenLayout.setVerticalGroup(
            smenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, smenLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(smenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(smenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(supcit, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(supnam))
                .addGap(18, 18, 18)
                .addGroup(smenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel14))
                .addGap(8, 8, 8)
                .addGroup(smenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(supid, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(supno))
                .addGap(51, 51, 51)
                .addGroup(smenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout supplierLayout = new javax.swing.GroupLayout(supplier);
        supplier.setLayout(supplierLayout);
        supplierLayout.setHorizontalGroup(
            supplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(supplierLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(smen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(supplierLayout.createSequentialGroup()
                .addGap(339, 339, 339)
                .addComponent(sdul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        supplierLayout.setVerticalGroup(
            supplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(supplierLayout.createSequentialGroup()
                .addComponent(sdul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(smen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        buku.setBackground(new java.awt.Color(102, 102, 102));

        bdul.setBackground(new java.awt.Color(102, 102, 102));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Buku");

        javax.swing.GroupLayout bdulLayout = new javax.swing.GroupLayout(bdul);
        bdul.setLayout(bdulLayout);
        bdulLayout.setHorizontalGroup(
            bdulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bdulLayout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(106, 106, 106))
        );
        bdulLayout.setVerticalGroup(
            bdulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bdulLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        bmen.setBackground(new java.awt.Color(153, 153, 153));

        jTbuku.setModel(new javax.swing.table.DefaultTableModel(
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
        jTbuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTbukuMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTbuku);

        jButton11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton11.setText("Tambah");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton12.setText("Hapus");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton13.setText("Edit");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("id Buku");

        idbuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idbukActionPerformed(evt);
            }
        });

        nambuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nambukActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setText("Nama Buku :");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText("Genre Buku :");

        bukgen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bukgenActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel18.setText("Pengarang buku :");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText("Penerbit :");

        pen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                penActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel27.setText("Supplier :");

        namsup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---- PILIH ---" }));
        namsup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namsupActionPerformed(evt);
            }
        });
        namsup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                namsupKeyReleased(evt);
            }
        });

        harbuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                harbukActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel29.setText("Harga Buku :");

        jLabel31.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel31.setText("Jumlah Buku");

        javax.swing.GroupLayout bmenLayout = new javax.swing.GroupLayout(bmen);
        bmen.setLayout(bmenLayout);
        bmenLayout.setHorizontalGroup(
            bmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bmenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bmenLayout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())
                    .addGroup(bmenLayout.createSequentialGroup()
                        .addGroup(bmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bmenLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel15)
                                    .addGap(465, 465, 465))
                                .addGroup(bmenLayout.createSequentialGroup()
                                    .addGroup(bmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(idbuk))
                                    .addGap(114, 114, 114))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bmenLayout.createSequentialGroup()
                                    .addGroup(bmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(nambuk, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(pen)
                                        .addComponent(harbuk, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGap(114, 114, 114)))
                            .addGroup(bmenLayout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(381, 381, 381)))
                        .addGroup(bmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(bukgen)
                            .addComponent(bukpeng)
                            .addComponent(namsup, 0, 392, Short.MAX_VALUE)
                            .addGroup(bmenLayout.createSequentialGroup()
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel31)
                            .addComponent(jTextField2))
                        .addGap(90, 90, 90))))
        );
        bmenLayout.setVerticalGroup(
            bmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bmenLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(bmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bukgen, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(idbuk))
                .addGap(18, 18, 18)
                .addGroup(bmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel18))
                .addGap(8, 8, 8)
                .addGroup(bmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nambuk, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(bukpeng))
                .addGap(18, 18, 18)
                .addGroup(bmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel27))
                .addGap(8, 8, 8)
                .addGroup(bmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pen, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(namsup))
                .addGap(18, 18, 18)
                .addGroup(bmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(bmenLayout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(harbuk, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bmenLayout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(bmenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        javax.swing.GroupLayout bukuLayout = new javax.swing.GroupLayout(buku);
        buku.setLayout(bukuLayout);
        bukuLayout.setHorizontalGroup(
            bukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bukuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bmen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bukuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bdul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(354, 354, 354))
        );
        bukuLayout.setVerticalGroup(
            bukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bukuLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(bdul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bmen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        CustomerPanel.setBackground(new java.awt.Color(102, 102, 102));

        cdul.setBackground(new java.awt.Color(102, 102, 102));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CUSTOMER");

        javax.swing.GroupLayout cdulLayout = new javax.swing.GroupLayout(cdul);
        cdul.setLayout(cdulLayout);
        cdulLayout.setHorizontalGroup(
            cdulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cdulLayout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );
        cdulLayout.setVerticalGroup(
            cdulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cdulLayout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        cmenrent.setBackground(new java.awt.Color(153, 153, 153));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel20.setText("Nama Customer :");

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel21.setText("Alamat Customer :");

        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setText("ID Buku :");

        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });

        jTextField18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField18ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel23.setText("Nomor Telepon Customer :");

        jLabel24.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel24.setText("Mulai Meminjam");

        jLabel25.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel25.setText("Harga peminjaman : ");

        jTextField19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField19ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel26.setText("Lama peminjaman (hari) :");

        jTextField20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField20ActionPerformed(evt);
            }
        });

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jButton14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton14.setText("Tambah");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton17.setText("Hapus");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton18.setText("Edit");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel28.setText("ID Customer :");

        jButton19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton19.setText("Cetak");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cmenrentLayout = new javax.swing.GroupLayout(cmenrent);
        cmenrent.setLayout(cmenrentLayout);
        cmenrentLayout.setHorizontalGroup(
            cmenrentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cmenrentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cmenrentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cmenrentLayout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addContainerGap())
                    .addGroup(cmenrentLayout.createSequentialGroup()
                        .addGroup(cmenrentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cmenrentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel20)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel22)
                                .addComponent(jTextField16, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                                .addComponent(jTextField5)
                                .addComponent(jTextField17)
                                .addComponent(jTextField1))
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                        .addGroup(cmenrentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cmenrentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cmenrentLayout.createSequentialGroup()
                                    .addComponent(jLabel23)
                                    .addGap(328, 328, 328))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cmenrentLayout.createSequentialGroup()
                                    .addGroup(cmenrentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel25)
                                        .addComponent(jLabel24))
                                    .addGap(361, 361, 361))
                                .addGroup(cmenrentLayout.createSequentialGroup()
                                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap()))
                            .addGroup(cmenrentLayout.createSequentialGroup()
                                .addGroup(cmenrentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(cmenrentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(cmenrentLayout.createSequentialGroup()
                                            .addGap(2, 2, 2)
                                            .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26))
                                .addGap(95, 95, 95))))))
        );
        cmenrentLayout.setVerticalGroup(
            cmenrentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cmenrentLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(cmenrentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cmenrentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1)
                    .addComponent(jTextField18, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cmenrentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cmenrentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cmenrentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cmenrentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(cmenrentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cmenrentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cmenrentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cmenbuy.setBackground(new java.awt.Color(153, 153, 153));

        jTcust_buy.setModel(new javax.swing.table.DefaultTableModel(
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
        jTcust_buy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTcust_buyMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTcust_buy);

        jButton5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton5.setText("Tambah");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton6.setText("Hapus");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton7.setText("Edit");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Nama Customer :");

        namcusbu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namcusbuActionPerformed(evt);
            }
        });

        idcusbuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idcusbuyActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Id Customer :");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("No Telpon :");

        nocusbu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nocusbuActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Judul Buku :");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Total Pembayaran :");

        ttlpem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttlpemActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel30.setText("Tanggal Transaksi :");

        jmbuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmbukActionPerformed(evt);
            }
        });
        jmbuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jmbukKeyReleased(evt);
            }
        });

        jubuk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---- PILIH ----" }));
        jubuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jubukActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Jumlah Buku : ");

        btnCetakBuy.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCetakBuy.setText("Cetak");
        btnCetakBuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakBuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cmenbuyLayout = new javax.swing.GroupLayout(cmenbuy);
        cmenbuy.setLayout(cmenbuyLayout);
        cmenbuyLayout.setHorizontalGroup(
            cmenbuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cmenbuyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cmenbuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cmenbuyLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 965, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(cmenbuyLayout.createSequentialGroup()
                        .addGroup(cmenbuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(namcusbu, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                            .addComponent(idcusbuy)
                            .addComponent(jLabel8)
                            .addComponent(ttlpem)
                            .addComponent(jubuk, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(154, 154, 154)
                        .addGroup(cmenbuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jmbuk)
                            .addComponent(jdcusbuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nocusbu)
                            .addGroup(cmenbuyLayout.createSequentialGroup()
                                .addGroup(cmenbuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel7))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(cmenbuyLayout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCetakBuy, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(59, 59, 59))))
        );
        cmenbuyLayout.setVerticalGroup(
            cmenbuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cmenbuyLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(cmenbuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cmenbuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(namcusbu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(cmenbuyLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(nocusbu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cmenbuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cmenbuyLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(8, 8, 8)
                        .addComponent(idcusbuy, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cmenbuyLayout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdcusbuy, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cmenbuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cmenbuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jubuk, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jmbuk, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cmenbuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ttlpem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCetakBuy, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        jButton15.setText("Sewa");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("Beli");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CustomerPanelLayout = new javax.swing.GroupLayout(CustomerPanel);
        CustomerPanel.setLayout(CustomerPanelLayout);
        CustomerPanelLayout.setHorizontalGroup(
            CustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(CustomerPanelLayout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addComponent(cdul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(CustomerPanelLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(cmenbuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(CustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CustomerPanelLayout.createSequentialGroup()
                    .addContainerGap(31, Short.MAX_VALUE)
                    .addComponent(cmenrent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        CustomerPanelLayout.setVerticalGroup(
            CustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomerPanelLayout.createSequentialGroup()
                .addGroup(CustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CustomerPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cdul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CustomerPanelLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(CustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(42, 42, 42)
                .addComponent(cmenbuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(CustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CustomerPanelLayout.createSequentialGroup()
                    .addContainerGap(136, Short.MAX_VALUE)
                    .addComponent(cmenrent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout tampilPanelLayout = new javax.swing.GroupLayout(tampilPanel);
        tampilPanel.setLayout(tampilPanelLayout);
        tampilPanelLayout.setHorizontalGroup(
            tampilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tampilPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(CustomerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(tampilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tampilPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(supplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(tampilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tampilPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(buku, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        tampilPanelLayout.setVerticalGroup(
            tampilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tampilPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CustomerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21))
            .addGroup(tampilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tampilPanelLayout.createSequentialGroup()
                    .addComponent(supplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(tampilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(buku, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tampilPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tampilPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        AdminPanel.setBackground(new java.awt.Color(51, 66, 87));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Dashboard Admin");

        jButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton1.setText("Customer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton2.setText("Supplier");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton3.setText("Buku");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton4.setText("Logout");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AdminPanelLayout = new javax.swing.GroupLayout(AdminPanel);
        AdminPanel.setLayout(AdminPanelLayout);
        AdminPanelLayout.setHorizontalGroup(
            AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(AdminPanelLayout.createSequentialGroup()
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        AdminPanelLayout.setVerticalGroup(
            AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminPanelLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel1)
                .addGap(64, 64, 64)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(AdminPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AdminPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Button Show panel Buku
        CustomerPanel.setVisible(false);
        cdul.setVisible(false);
        cmenbuy.setVisible(false);
        cmenrent.setVisible(false);
        buku.setVisible(true);
        bdul.setVisible(true);
        bmen.setVisible(true);
        supplier.setVisible(false);
        sdul.setVisible(false);
        smen.setVisible(false);
        TampilDataBuku();
        TambahNamasupp();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void namcusbuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namcusbuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namcusbuActionPerformed

    private void nocusbuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nocusbuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nocusbuActionPerformed

    private void jTcust_buyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTcust_buyMouseClicked
        // Mouse clik CUST BELI
        idcusbuy.setEditable(false);

        int baris = jTcust_buy.rowAtPoint(evt.getPoint());
        
        String idcusto = jTcust_buy.getValueAt(baris, 0).toString();
        idcusbuy.setText(idcusto);
        String namacusto = jTcust_buy.getValueAt(baris, 1).toString();
        namcusbu.setText(namacusto);
        String nohpcuto = jTcust_buy.getValueAt(baris, 2).toString();
        nocusbu.setText(nohpcuto);
        jdcusbuy.setDate(getTanggalFromTable(jTcust_buy,3));
        String jmbuku = jTcust_buy.getValueAt(baris, 5).toString();
        jmbuk.setText(jmbuku);
        String totalbayar = jTcust_buy.getValueAt(baris, 6).toString();
        ttlpem.setText(totalbayar);
    }//GEN-LAST:event_jTcust_buyMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Button tampil panel Customer Beli
        CustomerPanel.setVisible(true);
        cdul.setVisible(true);
        cmenbuy.setVisible(true);
        cmenrent.setVisible(false);
        buku.setVisible(false);
        bdul.setVisible(false);
        bmen.setVisible(false);
        supplier.setVisible(false);
        sdul.setVisible(false);
        smen.setVisible(false);
        TampilDataCustB();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTsupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTsupplierMouseClicked
        // MOUSE CLICKED TABEL SUPPLIER
        supid.setEditable(false);
        
        int baris = jTsupplier.rowAtPoint(evt.getPoint());
        
        String id = jTsupplier.getValueAt(baris, 0).toString();
        supid.setText(id);
        String nama = jTsupplier.getValueAt(baris, 1).toString();
        supnam.setText(nama);
        String alamat = jTsupplier.getValueAt(baris, 2).toString();
        supcit.setText(alamat);
        String telp = jTsupplier.getValueAt(baris, 3).toString();
        supno.setText(telp);
        
    }//GEN-LAST:event_jTsupplierMouseClicked

    private void supnamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supnamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supnamActionPerformed

    private void supidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supidActionPerformed

    private void supcitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supcitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supcitActionPerformed

    private void jTbukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTbukuMouseClicked
        // MOUSE CLICKED TABEL BUKU
        idbuk.setEditable(false);
        
        int baris = jTbuku.rowAtPoint(evt.getPoint());
        String idbuku = jTbuku.getValueAt(baris, 0).toString();
        idbuk.setText(idbuku);
        String namabuku = jTbuku.getValueAt(baris, 1).toString();
        nambuk.setText(namabuku);
        String genre = jTbuku.getValueAt(baris, 2).toString();
        bukgen.setText(genre);
        String pengarang = jTbuku.getValueAt(baris, 3).toString();
        bukpeng.setText(pengarang);
        String penerbit = jTbuku.getValueAt(baris, 4).toString();
        pen.setText(penerbit);
        String harga = jTbuku.getValueAt(baris, 5).toString();
        harbuk.setText(harga);
        String jumlahbuku = jTbuku.getValueAt(baris, 7).toString();
        jTextField2.setText(jumlahbuku);
        
    }//GEN-LAST:event_jTbukuMouseClicked

    private void idbukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idbukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idbukActionPerformed

    private void nambukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nambukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nambukActionPerformed

    private void bukgenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bukgenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bukgenActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Button tampil panel Supplier
        
        CustomerPanel.setVisible(false);
        cdul.setVisible(false);
        cmenrent.setVisible(false);
        cmenbuy.setVisible(false);
        buku.setVisible(false);
        bdul.setVisible(false);
        bmen.setVisible(false);
        supplier.setVisible(true);
        sdul.setVisible(true);
        smen.setVisible(true);
        TampilDataSupp();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void penActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_penActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        new Login().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void ttlpemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttlpemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttlpemActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void jTextField18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField18ActionPerformed

    private void jTextField19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField19ActionPerformed

    private void jTextField20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField20ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // Buy
        CustomerPanel.setVisible(true);
        cdul.setVisible(true);
        cmenbuy.setVisible(true);
        cmenrent.setVisible(false);
        buku.setVisible(false);
        bdul.setVisible(false);
        bmen.setVisible(false);
        supplier.setVisible(false);
        sdul.setVisible(false);
        smen.setVisible(false);
        TampilDataCustB();
        TambahNamabuk();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // Rent
        CustomerPanel.setVisible(true);
        cdul.setVisible(true);
        cmenbuy.setVisible(false);
        cmenrent.setVisible(true);
        buku.setVisible(false);
        bdul.setVisible(false);
        bmen.setVisible(false);
        supplier.setVisible(false);
        sdul.setVisible(false);
        smen.setVisible(false);
        TampilDataCustR();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void namsupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namsupKeyReleased
        // TIDAK DI GUNAKAN
    }//GEN-LAST:event_namsupKeyReleased

    private void namsupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namsupActionPerformed
        // TODO add your handling code here:
        String idsupp = null;
        try{
            String sql="SELECT * FROM `supplier` WHERE nama_supplier LIKE '"+ namsup.getSelectedItem() +"'";
            java.sql.Connection MySQLConfig = (com.mysql.jdbc.Connection)Config.configDB();
            java.sql.Statement stm=MySQLConfig.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                idedit=(res.getString("id_supplier"));
            }
        }catch(SQLException e){
            System.out.println("Error "+e.getMessage());
        }
    }//GEN-LAST:event_namsupActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // Button tambah 
        try{
            String sql="INSERT INTO buku VALUES (" + idbuk.getText()+","
                    + "'" + nambuk.getText()+"',"
                    + "'" + bukgen.getText()+"',"
                    + "'" + bukpeng.getText()+"',"
                    + "'" + pen.getText()+"',"
                    + "" + harbuk.getText()+","
                    + "'" + idedit +","
                    + "'" + jTextField2.getText() +"')";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Insert data berhasil");
            TampilDataBuku();
            KosongBuku();
            
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
    }//GEN-LAST:event_jButton11ActionPerformed

    private void harbukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_harbukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_harbukActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TOMBOL EDIT BUKU
        try{
            String sql="UPDATE `buku` SET id_buku=" + idbuk.getText()+","
                    + "judul_buku='" + nambuk.getText()+"',"
                    + "genre='" + bukgen.getText()+"',"
                    + "pengarang='" + bukpeng.getText()+"',"
                    + "penerbit='" + pen.getText()+"',"
                    + "harga_buku=" + harbuk.getText()+","
                    + "id_supplier=" +idedit
                    + "jumlah_buku=" + jTextField2.getText()+","
                    + " WHERE id_buku='"+idbuk.getText()+"'";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Update data berhasil");
            TampilDataBuku();
            KosongBuku();
            
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TOMBOL DELETE BUKU
        try{
            String sql="DELETE FROM `buku` WHERE id_buku='" + idbuk.getText() +"'";
            
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Hapus data berhasil");
            TampilDataBuku();
            KosongBuku();
            
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TOMBOL TAMBAH DATA SUPPLIER
        try{
            String sql="INSERT INTO supplier VALUES (" + supid.getText()+","
                    + "'" + supnam.getText()+"',"
                    + "'" + supcit.getText()+"',"
                    + "'" + supno.getText()+"')";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Insert data berhasil");
            TampilDataSupp();
            KosongSupp();
            
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TOMBOL EDIT SUPPLIER
        try{
            String sql="UPDATE `supplier` SET id_supplier=" + supid.getText()+","
                    + "nama_supplier='" + supnam.getText()+"',"
                    + "alamat_supplier='" + supcit.getText()+"',"
                    + "notelp_supplier='" + supno.getText()
                    + "' WHERE id_supplier='"+supid.getText()+"'";
            System.out.println(sql);
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Update data berhasil");
            TampilDataSupp();
            KosongSupp();
            
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TOMBOL HAPUS SUPPLIER
        try{
            String sql="DELETE FROM `supplier` WHERE id_supplier='" + supid.getText() +"'";
            
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Hapus data berhasil");
            TampilDataSupp();
            KosongSupp();
            
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jmbukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmbukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmbukActionPerformed

    private void idcusbuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idcusbuyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idcusbuyActionPerformed

    private void jmbukKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jmbukKeyReleased
        // Key released dari field 
        String hrg = null ;
        int jmlbeli=Integer.parseInt(jmbuk.getText());
        try{
            String sql="select * from buku where judul_buku like '"+jubuk.getSelectedItem()+"'";
            java.sql.Connection MySQLConfig = (Connection)Config.configDB();
            java.sql.Statement stm=MySQLConfig.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                hrg=(res.getString("harga_buku"));
            }
            int total = jmlbeli * Integer.parseInt(hrg);
            ttlpem.setText(String.valueOf(total));
        }catch(SQLException e){
            System.out.println("Error, Harga tidak boleh kosong "+e.getMessage());
        }
    }//GEN-LAST:event_jmbukKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TOMBOL TAMBAH DATA CUST BELI
        String dob=""+jdcusbuy.getDate(); 
        SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
        
         try{
            String sql="INSERT INTO cust_beli VALUES("+idcusbuy.getText()+","
                    + "'"+namcusbu.getText()+"',"
                    + "'"+nocusbu.getText()+"',"
                    + "'"+Date_Format.format(jdcusbuy.getDate())+"',"
                    + "'"+idbuku+"',"
                    + "'"+jmbuk.getText()+"',"
                    + "'"+ttlpem.getText()+"')";      
             java.sql.Connection conn=(com.mysql.jdbc.Connection)Config.configDB();
             java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
             pstm.execute();
             TampilDataCustB();
             KosongCustB();
             
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jubukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jubukActionPerformed
        // Indclude ID Buku
        try{
            String sql="SELECT * FROM `buku` WHERE judul_buku LIKE '"+ jubuk.getSelectedItem() +"'";
            java.sql.Connection MySQLConfig = (com.mysql.jdbc.Connection)Config.configDB();
            java.sql.Statement stm=MySQLConfig.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                idbuku=(res.getString("id_buku"));
            }
        }catch(SQLException e){
            System.out.println("Error "+e.getMessage());
        }      
    }//GEN-LAST:event_jubukActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TOMBOL EDIT CUST BELI
        String tampilan=""+jdcusbuy.getDate();
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String sql = "UPDATE cust_beli SET nama_customer='" + namcusbu.getText() + "',"
                    + "notelp_customer='" + nocusbu.getText() + "',"
                    + "tanggal_transaksi='" + fm.format(jdcusbuy.getDate()) + "',"
                    + "id_buku=" + idbuku + ","
                    + "jumlah_buku=" + jmbuk.getText() + ","
                    + "Total_Harga="+ ttlpem.getText() +" WHERE id_customer=" + idcusbuy.getText();
            System.out.println(sql);
            java.sql.Connection MySQLConfig = (Connection) Config.configDB();
            java.sql.PreparedStatement pstm = MySQLConfig.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "UPDATE Data Berhasil ");
            TampilDataCustB();
             KosongCustB();
             
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:
        jTextField1.setEditable(false);
        
        int baris = jTable4.rowAtPoint(evt.getPoint());
        
        String idcusto = jTable4.getValueAt(baris, 0).toString();
        jTextField1.setText(idcusto);
        String namacusto = jTable4.getValueAt(baris, 1).toString();
        jTextField5.setText(namacusto);
        String alamatcusto = jTable4.getValueAt(baris, 2).toString();
        jTextField16.setText(alamatcusto);
        String idbuku = jTable4.getValueAt(baris, 3).toString();
        jTextField17.setText(idbuku);
        String nohpcuto = jTable4.getValueAt(baris, 4).toString();
        jTextField18.setText(nohpcuto);
        jDateChooser1.setDate(getTanggalFromTable(jTable4,5));
        String totalbayar = jTable4.getValueAt(baris, 6).toString();
        jTextField19.setText(totalbayar);
        String lamapinjam = jTable4.getValueAt(baris, 7).toString();
        jTextField20.setText(lamapinjam);
    }//GEN-LAST:event_jTable4MouseClicked

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        String dob=""+jDateChooser1.getDate(); 
        SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
        
         try{
            String sql="INSERT INTO cust_sewa VALUES("+jTextField1.getText()+","
                    + "'"+jTextField5.getText()+"',"
                    + "'"+jTextField18.getText()+"',"
                    + "'"+Date_Format.format(jDateChooser1.getDate())+"',"
                    + "'"+jTextField17.getText()+"',"
                    + "'"+jTextField20.getText()+"',"
                    + "'"+jTextField16.getText()+"',"
                    + "'"+jTextField19.getText()+"')";      
             java.sql.Connection conn=(com.mysql.jdbc.Connection)Config.configDB();
             java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
             pstm.execute();
             TampilDataCustR();
             KosongCustR();
             
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
                      
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        String tampilan=""+jDateChooser1.getDate();
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String sql = "UPDATE cust_sewa SET nama_customer='" + jTextField5.getText() + "',"
                    + "notelp_customer='" + jTextField18.getText() + "',"
                    + "tanggal_pinjam='" + fm.format(jDateChooser1.getDate()) + "',"
                    + "id_buku=" + jTextField17 + ","
                    + "lama_pinjam=" + jTextField20 + ","
                    + "alamat_customer=" + jTextField16.getText() + ","
                    + "Total_Harga="+ jTextField19.getText() +" WHERE id_customer=" + jTextField1.getText();
            System.out.println(sql);
            java.sql.Connection MySQLConfig = (Connection) Config.configDB();
            java.sql.PreparedStatement pstm = MySQLConfig.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "UPDATE Data Berhasil ");
            TampilDataCustR();
            KosongCustR();
             
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try{
            String sql="DELETE FROM `cust_beli` WHERE id_customer='" + idcusbuy.getText() +"'";
            
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Hapus data berhasil");
            TampilDataCustB();
            KosongCustB();
            
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        try{
            String sql="DELETE FROM `cust_sewa` WHERE id_customer='" + jTextField1.getText() +"'";
            
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Hapus data berhasil");
            TampilDataCustR();
            KosongCustR();
            
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void btnCetakBuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakBuyActionPerformed
        // TODO add your handling code here:
        try {
            String report = ("D:\\Praktikum PBO\\Toko_Buku_Ante_new\\src\\tokobuku\\report_beli.jrxml");
            HashMap hash = new HashMap();
            hash.put("idcustomerbeli", idcusbuy.getText());
            JasperReport jrpt = JasperCompileManager.compileReport(report);
            JasperPrint jsPrint = JasperFillManager.fillReport(jrpt, hash, Config.configDB());
            JasperViewer.viewReport(jsPrint, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "gagal mencetak laporan karena : "
                + e.getMessage(), "Cetak Laporan", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCetakBuyActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        try {
            String report = ("D:\\Praktikum PBO\\Toko_Buku_Ante_new\\src\\tokobuku\\report_sewa.jrxml");
            HashMap hash = new HashMap();
            hash.put("idcustomersewa", jTextField1.getText());
            JasperReport jrpt = JasperCompileManager.compileReport(report);
            JasperPrint jsPrint = JasperFillManager.fillReport(jrpt, hash, Config.configDB());
            JasperViewer.viewReport(jsPrint, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "gagal mencetak laporan karena : "
                + e.getMessage(), "Cetak Laporan", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton19ActionPerformed

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
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }
    private String idedit;
    private String idbuku;
    private String namabuk;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AdminPanel;
    private javax.swing.JPanel CustomerPanel;
    private javax.swing.JPanel bdul;
    private javax.swing.JPanel bmen;
    private javax.swing.JButton btnCetakBuy;
    private javax.swing.JTextField bukgen;
    private javax.swing.JTextField bukpeng;
    private javax.swing.JPanel buku;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel cdul;
    private javax.swing.JPanel cmenbuy;
    private javax.swing.JPanel cmenrent;
    private javax.swing.JTextField harbuk;
    private javax.swing.JTextField idbuk;
    private javax.swing.JTextField idcusbuy;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTbuku;
    private javax.swing.JTable jTcust_buy;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTable jTsupplier;
    private com.toedter.calendar.JDateChooser jdcusbuy;
    private javax.swing.JTextField jmbuk;
    private javax.swing.JComboBox<String> jubuk;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField nambuk;
    private javax.swing.JTextField namcusbu;
    private javax.swing.JComboBox<String> namsup;
    private javax.swing.JTextField nocusbu;
    private javax.swing.JTextField pen;
    private javax.swing.JPanel sdul;
    private javax.swing.JPanel smen;
    private javax.swing.JTextField supcit;
    private javax.swing.JTextField supid;
    private javax.swing.JTextField supnam;
    private javax.swing.JTextField supno;
    private javax.swing.JPanel supplier;
    private javax.swing.JPanel tampilPanel;
    private javax.swing.JTextField ttlpem;
    // End of variables declaration//GEN-END:variables
}
