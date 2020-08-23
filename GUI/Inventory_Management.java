/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import classes.Suppliers;
import classes.Inventory;
import DataBaseLayer.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Inventory_Management extends javax.swing.JInternalFrame {

    public javax.swing.JDesktopPane dp_home;

    public Inventory_Management() {

    }

    public Inventory_Management(javax.swing.JDesktopPane dp) {
        initComponents();

        this.dp_home = dp;

        String columnValue = null;
        String recordid = null;
        String newrecord = null;

        try {

            Connection con = DataBaseConnection.getDBconnection();
            String query = "select * from suppliers ORDER BY SupplierID DESC LIMIT 1";
            PreparedStatement ps = con.prepareStatement(query);
            Statement statement = con.createStatement();
            ResultSet resultset = ps.executeQuery(query);

            while (resultset.next()) {

                columnValue = resultset.getString(1);
                System.out.print(columnValue);

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error Occured.");
        }

        if (columnValue == null) {
            newrecord = "SUP100000";
        } else {
            String lastrecordid = columnValue.substring(3, 9);
            System.out.print(lastrecordid);
            int newid = Integer.parseInt(lastrecordid);
            int newrecordid = newid + 1;
            String id = Integer.toString(newrecordid);
            newrecord = "SUP" + id;
        }

        lbl_supplierid.setText(newrecord);

        String itemcolumnValue = null;
        String itemnewrecord = null;
        try {

            Connection con = DataBaseConnection.getDBconnection();
            String query = "select * from inventory ORDER BY Itemcode DESC LIMIT 1";
            PreparedStatement ps = con.prepareStatement(query);
            Statement statement = con.createStatement();
            ResultSet resultset = ps.executeQuery(query);

            while (resultset.next()) {

                itemcolumnValue = resultset.getString(1);
                System.out.print(itemcolumnValue);

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error Occured.");
        }

        if (itemcolumnValue == null) {
            itemnewrecord = "Item100000";
        } else {
            String itemlastrecordid = itemcolumnValue.substring(4, 10);
            System.out.print(itemlastrecordid);
            int itemnewid = Integer.parseInt(itemlastrecordid);
            int itemnewrecordid = itemnewid + 1;
            String id = Integer.toString(itemnewrecordid);
            itemnewrecord = "Item" + id;
        }

        lbl_itemgenerate.setText(itemnewrecord);

    }
    private ResultSet rs;
    private Statement st;

    public ArrayList<Suppliers> supplierList() {
        ArrayList<Suppliers> supplierList = new ArrayList<>();
        try {

            Connection con = DataBaseConnection.getDBconnection();

            String query = "SELECT * FROM suppliers where Status=1";
            st = con.prepareStatement(query);
            st = con.createStatement();
            rs = st.executeQuery(query);

            Suppliers suppliers;
            while (rs.next()) {
                suppliers = new Suppliers(rs.getString("SupplierID"), rs.getString("Name"), rs.getString("Type"), rs.getString("Contact"), rs.getString("Address"), rs.getString("Email"));
                supplierList.add(suppliers);
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "System Error Occured");
        }
        return supplierList;
    }

    /*method to retrieve leave details */
    public void showsuppliers() {

        ArrayList<Suppliers> list = supplierList();
        DefaultTableModel model = (DefaultTableModel) supplier_table.getModel();
        Object[] row = new Object[6];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getSupplierID();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getType();
            row[3] = list.get(i).getContact();
            row[4] = list.get(i).getAddress();
            row[5] = list.get(i).getEmail();

            model.addRow(row);

        }
    }

    public ArrayList<Inventory> inventoryList() {
        ArrayList<Inventory> inventoryList = new ArrayList<>();
        try {

            Connection con = DataBaseConnection.getDBconnection();

            String query = "SELECT * FROM inventory where Status=1";
            st = con.prepareStatement(query);
            st = con.createStatement();
            rs = st.executeQuery(query);

            Inventory inventory;
            while (rs.next()) {
                inventory = new Inventory(rs.getString("Itemcode"), rs.getString("Name"), rs.getInt("Qty"), rs.getInt("Unitprice"));
                inventoryList.add(inventory);
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "System Error Occured");
        }
        return inventoryList;
    }

    /*method to retrieve leave details */
    public void showinventory() {

        ArrayList<Inventory> list = inventoryList();
        DefaultTableModel model = (DefaultTableModel) tblinventory.getModel();
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getItemcode();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getQty();
            row[3] = list.get(i).getUnitrice();

            model.addRow(row);

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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        btnadd = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        btn_Invetory_view1 = new javax.swing.JButton();
        txtitemname = new javax.swing.JTextField();
        txtqty = new javax.swing.JTextField();
        txtunitprice = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblinventory = new javax.swing.JTable();
        btnview = new javax.swing.JButton();
        txt_supplier = new javax.swing.JTextField();
        lbl_itemgenerate = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txt_pidinventory = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        btnadd2 = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        btn_Invetory_view5 = new javax.swing.JButton();
        txt_Supplier_name = new javax.swing.JTextField();
        txt_contact = new javax.swing.JTextField();
        txt_Supplier_Email = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        supplier_table = new javax.swing.JTable();
        btnview3 = new javax.swing.JButton();
        txt_Supplier_Address = new javax.swing.JTextField();
        lbl_supplierid = new javax.swing.JLabel();
        cmb_Supplier_materials = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Manage Inventory");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/asset-2.png"))); // NOI18N
        setMinimumSize(new java.awt.Dimension(950, 600));
        setPreferredSize(new java.awt.Dimension(1000, 600));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTabbedPane1.setMaximumSize(new java.awt.Dimension(0, 450));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(950, 600));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(950, 600));

        jPanel1.setBackground(java.awt.Color.darkGray);
        jPanel1.setMaximumSize(new java.awt.Dimension(950, 700));
        jPanel1.setMinimumSize(new java.awt.Dimension(950, 700));
        jPanel1.setPreferredSize(new java.awt.Dimension(950, 800));

        btnadd.setBackground(new java.awt.Color(255, 255, 255));
        btnadd.setText("Add Details");
        btnadd.setMaximumSize(new java.awt.Dimension(140, 47));
        btnadd.setMinimumSize(new java.awt.Dimension(140, 47));
        btnadd.setPreferredSize(new java.awt.Dimension(140, 47));
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Item Code");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Name");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("QTY");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText(" Suplier ID");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText(" Unit Price");

        btn_Invetory_view1.setBackground(new java.awt.Color(255, 255, 255));
        btn_Invetory_view1.setText("Manage Details");
        btn_Invetory_view1.setMaximumSize(new java.awt.Dimension(140, 47));
        btn_Invetory_view1.setMinimumSize(new java.awt.Dimension(140, 47));
        btn_Invetory_view1.setPreferredSize(new java.awt.Dimension(140, 47));
        btn_Invetory_view1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Invetory_view1ActionPerformed(evt);
            }
        });

        txtqty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtqtyActionPerformed(evt);
            }
        });

        txtunitprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtunitpriceActionPerformed(evt);
            }
        });

        tblinventory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ItemCode", "ItemName", "Qty", "Unit Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblinventory);

        btnview.setBackground(new java.awt.Color(255, 255, 255));
        btnview.setText("View All");
        btnview.setMaximumSize(new java.awt.Dimension(140, 47));
        btnview.setMinimumSize(new java.awt.Dimension(140, 47));
        btnview.setPreferredSize(new java.awt.Dimension(140, 47));
        btnview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnviewActionPerformed(evt);
            }
        });

        lbl_itemgenerate.setText("jLabel1");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText(" PID");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtitemname, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                    .addComponent(txtqty, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                    .addComponent(lbl_itemgenerate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 560, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_pidinventory, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(txtunitprice, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txt_supplier, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_Invetory_view1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnview, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE))
                        .addGap(1, 1, 1)))
                .addGap(102, 102, 102))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtitemname, txtqty, txtunitprice});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_Invetory_view1, btnadd});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(lbl_itemgenerate))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Invetory_view1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(txtitemname, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtqty, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(txt_pidinventory, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel37))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txt_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtunitprice, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38))))
                .addGap(62, 62, 62)
                .addComponent(btnview, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addGap(70, 70, 70))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_Invetory_view1, btnadd, btnview});

        jScrollPane3.setViewportView(jPanel1);

        jTabbedPane1.addTab("Inventories", jScrollPane3);

        jPanel5.setBackground(java.awt.Color.darkGray);
        jPanel5.setMaximumSize(new java.awt.Dimension(940, 700));
        jPanel5.setMinimumSize(new java.awt.Dimension(940, 700));
        jPanel5.setPreferredSize(new java.awt.Dimension(940, 800));

        btnadd2.setBackground(new java.awt.Color(255, 255, 255));
        btnadd2.setText("Add Details");
        btnadd2.setMaximumSize(new java.awt.Dimension(140, 47));
        btnadd2.setMinimumSize(new java.awt.Dimension(140, 47));
        btnadd2.setPreferredSize(new java.awt.Dimension(140, 47));
        btnadd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnadd2ActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Supplier ID");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Name");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Contact No.");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Address");

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Email");

        btn_Invetory_view5.setBackground(new java.awt.Color(255, 255, 255));
        btn_Invetory_view5.setText("Manage Details");
        btn_Invetory_view5.setMaximumSize(new java.awt.Dimension(140, 47));
        btn_Invetory_view5.setMinimumSize(new java.awt.Dimension(140, 47));
        btn_Invetory_view5.setPreferredSize(new java.awt.Dimension(140, 47));
        btn_Invetory_view5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Invetory_view5ActionPerformed(evt);
            }
        });

        txt_contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_contactActionPerformed(evt);
            }
        });

        txt_Supplier_Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Supplier_EmailActionPerformed(evt);
            }
        });

        supplier_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SupplierID", "Name", "Type", "Contact No.", "Address", "e-Mail"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane8.setViewportView(supplier_table);

        btnview3.setBackground(new java.awt.Color(255, 255, 255));
        btnview3.setText("View All");
        btnview3.setMaximumSize(new java.awt.Dimension(140, 47));
        btnview3.setMinimumSize(new java.awt.Dimension(140, 47));
        btnview3.setPreferredSize(new java.awt.Dimension(140, 47));
        btnview3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnview3ActionPerformed(evt);
            }
        });

        txt_Supplier_Address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Supplier_AddressActionPerformed(evt);
            }
        });

        lbl_supplierid.setText("jLabel1");

        cmb_Supplier_materials.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Raw Materials" }));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Type");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_Supplier_name, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_contact, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_Supplier_Address, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 550, Short.MAX_VALUE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(lbl_supplierid, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel49)
                                        .addGap(32, 32, 32)
                                        .addComponent(cmb_Supplier_materials, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(233, 233, 233))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(txt_Supplier_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnadd2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_Invetory_view5, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnview3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE))))
                .addGap(103, 103, 103))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_Invetory_view5, btnadd2});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(lbl_supplierid)
                    .addComponent(cmb_Supplier_materials, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnadd2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Invetory_view5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(txt_Supplier_name, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_contact, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(txt_Supplier_Address, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_Supplier_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50))))
                .addGap(62, 62, 62)
                .addComponent(btnview3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addGap(124, 124, 124))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_Invetory_view5, btnadd2, btnview3});

        jScrollPane7.setViewportView(jPanel5);

        jTabbedPane1.addTab("Suppliers", jScrollPane7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnview3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnview3ActionPerformed
        DefaultTableModel model = (DefaultTableModel) supplier_table.getModel();
        model.setRowCount(0);

        supplierList();
        showsuppliers();        // TODO add your handling code here:
    }//GEN-LAST:event_btnview3ActionPerformed

    private void txt_Supplier_EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Supplier_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Supplier_EmailActionPerformed

    private void txt_contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_contactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_contactActionPerformed

    private void btn_Invetory_view5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Invetory_view5ActionPerformed
        // TODO add your handling code here:
        Manage_Sup sup = new Manage_Sup();
        dp_home.add(sup).setVisible(true);
    }//GEN-LAST:event_btn_Invetory_view5ActionPerformed

    private void btnadd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnadd2ActionPerformed
        // TODO add your handling code here:
        String columnValue = null;
        String recordid = null;
        String newrecord = null;

        try {

            Connection con = DataBaseConnection.getDBconnection();
            String query = "select * from suppliers ORDER BY SupplierID DESC LIMIT 1";
            PreparedStatement ps = con.prepareStatement(query);
            Statement statement = con.createStatement();
            ResultSet resultset = ps.executeQuery(query);

            while (resultset.next()) {

                columnValue = resultset.getString(1);
                System.out.print(columnValue);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Occured.");
        }

        if (columnValue == null) {
            newrecord = "SUP100000";
        } else {
            String lastrecordid = columnValue.substring(3, 9);
            System.out.print(lastrecordid);
            int newid = Integer.parseInt(lastrecordid);
            int newrecordid = newid + 1;
            String id = Integer.toString(newrecordid);
            newrecord = "SUP" + id;
        }

        String name = txt_Supplier_name.getText();
        String type = cmb_Supplier_materials.getSelectedItem().toString();
        String contact = txt_contact.getText();
        String address = txt_Supplier_Address.getText();
        String email = txt_Supplier_Email.getText();
        //String birth=txt_DOB.getText();

        try {
            Connection con = DataBaseConnection.getDBconnection();
            String query = " insert into suppliers (SupplierID,Name,Type,Contact,Address,Email,Status) values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);

            if ((name.equals("")) || (contact.equals("")) || (address.equals("")) || (email.equals(""))) {

                JOptionPane.showMessageDialog(null, "Error");
            } else {

                pst.setString(1, newrecord);
                pst.setString(2, name);
                pst.setString(3, type);
                pst.setString(4, contact);
                pst.setString(5, address);
                pst.setString(6, email);
                pst.setInt(7, 1);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "inserted successfully");
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error");
        }


    }//GEN-LAST:event_btnadd2ActionPerformed

    private void btnviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnviewActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblinventory.getModel();
        model.setRowCount(0);
        inventoryList();
        showinventory();        // TODO add your handling code here:
    }//GEN-LAST:event_btnviewActionPerformed

    private void txtunitpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtunitpriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtunitpriceActionPerformed

    private void txtqtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtqtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtqtyActionPerformed

    private void btn_Invetory_view1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Invetory_view1ActionPerformed
        Manage_Inv inv = new Manage_Inv();
        dp_home.add(inv).setVisible(true);
    }//GEN-LAST:event_btn_Invetory_view1ActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        String columnValue = null;
        String recordid = null;
        String newrecord = null;

        try {

            Connection con = DataBaseConnection.getDBconnection();
            String query = "select * from inventory ORDER BY Itemcode DESC LIMIT 1";
            PreparedStatement ps = con.prepareStatement(query);
            Statement statement = con.createStatement();
            ResultSet resultset = ps.executeQuery(query);

            while (resultset.next()) {

                columnValue = resultset.getString(1);
                System.out.print(columnValue);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Occured.");
        }

        if (columnValue == null) {
            newrecord = "Item100000";
        } else {
            String lastrecordid = columnValue.substring(4, 10);
            System.out.print(lastrecordid);
            int newid = Integer.parseInt(lastrecordid);
            int newrecordid = newid + 1;
            String id = Integer.toString(newrecordid);
            newrecord = "Item" + id;
        }

        String name = txtitemname.getText();
        String supplier = txt_supplier.getText();
        int qty = Integer.parseInt(txtqty.getText());
        int unitprice = Integer.parseInt(txtunitprice.getText());
        String pid = txt_pidinventory.getText();
        //String birth=txt_DOB.getText();

        try {
            Connection con = DataBaseConnection.getDBconnection();
            String query = " insert into inventory (Itemcode,PID,SupplierID,Name,Qty,Unitprice,Status) values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);

            if ((name.equals("")) || (supplier.equals("")) || (pid.equals(""))) {

                JOptionPane.showMessageDialog(null, "Error");
            } else {

                pst.setString(1, newrecord);
                pst.setString(2, txt_pidinventory.getText());
                pst.setString(3, supplier);
                pst.setString(4, name);
                pst.setInt(5, qty);
                pst.setInt(6, unitprice);
                pst.setInt(7, 1);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "inserted successfully");
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error");
        }


    }//GEN-LAST:event_btnaddActionPerformed

    private void txt_Supplier_AddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Supplier_AddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Supplier_AddressActionPerformed

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
            java.util.logging.Logger.getLogger(Inventory_Management.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventory_Management.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventory_Management.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventory_Management.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventory_Management().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Invetory_view1;
    private javax.swing.JButton btn_Invetory_view5;
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnadd2;
    private javax.swing.JButton btnview;
    private javax.swing.JButton btnview3;
    private javax.swing.JComboBox<String> cmb_Supplier_materials;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbl_itemgenerate;
    private javax.swing.JLabel lbl_supplierid;
    private javax.swing.JTable supplier_table;
    private javax.swing.JTable tblinventory;
    private javax.swing.JTextField txt_Supplier_Address;
    private javax.swing.JTextField txt_Supplier_Email;
    private javax.swing.JTextField txt_Supplier_name;
    private javax.swing.JTextField txt_contact;
    private javax.swing.JTextField txt_pidinventory;
    private javax.swing.JTextField txt_supplier;
    private javax.swing.JTextField txtitemname;
    private javax.swing.JTextField txtqty;
    private javax.swing.JTextField txtunitprice;
    // End of variables declaration//GEN-END:variables
 private void setInventroyDetails(ResultSet result) {
        DefaultTableModel model = (DefaultTableModel) tblinventory.getModel();

        try {
            if (result.isBeforeFirst()) {
                try {
                    while (result.next()) {
                        String itemcode = result.getString("ItemCode");
                        String pid = result.getString("PID");
                        String itemname = result.getString("Name");
                        int qty = result.getInt("QTY");
                        String date = result.getDate("IDate").toString();
                        String sid = result.getString("SID");
                        float uprice = result.getFloat("unitPrice");
                        model.addRow(new Object[]{itemcode, pid, itemname, qty, date, sid, uprice});

                    }

                } catch (SQLException ex) {

                }

            } else {
                JOptionPane.showMessageDialog(null, "No matching records found", "Information", JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (SQLException sql) {

        }
    }

}
