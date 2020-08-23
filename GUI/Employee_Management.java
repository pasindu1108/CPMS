/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DataBaseLayer.DataBaseConnection;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import GUI.Home;

public class Employee_Management extends javax.swing.JInternalFrame {

    private ResultSet rs;
    private ResultSet rs1;

    public javax.swing.JDesktopPane dp_home;

    /**
     * Creates new form GUI
     */
    private void groupButton() {
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(rdo_Male);
        bg1.add(rdo_Female);

    }

    private void groupbutton1() {
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(rdo_Married);
        bg2.add(rdo_Single);

    }

    private void groupbutton2() {
        ButtonGroup bg3 = new ButtonGroup();
        bg3.add(rdo_decline);
        bg3.add(rdo_accept);
    }

    public Employee_Management() {
    }

    public Employee_Management(javax.swing.JDesktopPane dp) {
        initComponents();

        this.dp_home = dp;

        groupButton();
        groupbutton1();
        groupbutton2();

        String empidcolumn = null;
        String empidnewrecord = null;
        try {

            Connection con = DataBaseConnection.getDBconnection();
            String query = "select * from employees ORDER BY EmployeeID DESC LIMIT 1";
            PreparedStatement ps = con.prepareStatement(query);
            Statement statement = con.createStatement();
            ResultSet resultset = ps.executeQuery(query);

            while (resultset.next()) {

                empidcolumn = resultset.getString(1);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Occured.");
        }

        if (empidcolumn == null) {
            empidnewrecord = "EMP100000";
        } else {
            String lastrecordid = empidcolumn.substring(3, 9);
            System.out.print(lastrecordid);
            int newid = Integer.parseInt(lastrecordid);
            int newrecordid = newid + 1;
            String id = Integer.toString(newrecordid);
            empidnewrecord = "EMP" + id;
        }
        lbl_empid.setText(empidnewrecord);
    }
    private String url;
    private Connection conDB;
    private Statement st;
    private byte[] person_img;
    private String filename = null;
    public String pid;
    private Statement st1;

    public ArrayList<User> userList() {
        ArrayList<User> userList = new ArrayList<>();
        try {

            Connection con = DataBaseConnection.getDBconnection();

            String query = "SELECT * FROM empleaves where EmployeeID='" + txt_leavesearchid.getText() + "'";
            st = con.prepareStatement(query);
            st = con.createStatement();
            rs = st.executeQuery(query);

            User user;
            while (rs.next()) {
                user = new User(rs.getString("EmployeeID"), rs.getString("Date"), rs.getString("Leavetype"), rs.getString("Duration"), rs.getString("approvel"));
                userList.add(user);
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "System Error Occured");
        }
        return userList;
    }

    /*method to retrieve leave details */
    public void showuser() {

        ArrayList<User> list = userList();
        DefaultTableModel model = (DefaultTableModel) leavetable.getModel();
        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getEID();
            row[1] = list.get(i).getLeaveDate();
            row[2] = list.get(i).getLeaveType();
            row[3] = list.get(i).getDuration();
            row[4] = list.get(i).getApproval();
            model.addRow(row);

        }
    }

    /*Send leave details to array*/
    public ArrayList<Userleave> user_leave_List() {
        ArrayList<Userleave> user_leave_List = new ArrayList<>();
        try {

            Connection con = DataBaseConnection.getDBconnection();

            String query = "SELECT * FROM empleaves";
            PreparedStatement pst = con.prepareStatement(query);
            st = con.prepareStatement(query);
            st = con.createStatement();
            rs = st.executeQuery(query);

            Userleave userleave;
            while (rs.next()) {
                userleave = new Userleave(rs.getString("EmployeeID"), rs.getString("Date"), rs.getString("Leavetype"), rs.getString("Duration"), rs.getString("Approvel"));

                user_leave_List.add(userleave);

            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "System error occured");
        }
        return user_leave_List;
    }

    /*method to retrieve leave details */
    public void allshowuser() {

        ArrayList<Userleave> list = user_leave_List();
        DefaultTableModel model = (DefaultTableModel) leavetable.getModel();
        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getEID();
            row[1] = list.get(i).getLeaveDate();
            row[2] = list.get(i).getLeaveType();
            row[3] = list.get(i).getDuration();
            row[4] = list.get(i).getApproval();
            model.addRow(row);

        }
    }

    /*get attendence details to a particular array*/
    public ArrayList<Userattend> user_attend_List() {
        ArrayList<Userattend> user_attend_rList = new ArrayList<>();
        try {
            Connection con = DataBaseConnection.getDBconnection();

            String query = "SELECT * FROM attendence where EmployeeID='" + txt_attendence_empid.getText() + "'";
            st = con.prepareStatement(query);
            st = con.createStatement();
            rs = st.executeQuery(query);

            String idq = txt_attendence_empid.getText();
            String i1 = idq.substring(0, 1);

            Userattend userattend;

            while (rs.next()) {

                userattend = new Userattend(rs.getString("EmployeeID"), rs.getString("PID"), rs.getString("Date"), rs.getString("Intime"), rs.getString("Outtime"));
                user_attend_rList.add(userattend);

            }

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "System Error Occured");
        }
        return user_attend_rList;
    }

    /*method retreieve attendence details*/
    public void attendshowuser() {

        ArrayList<Userattend> list = user_attend_List();
        DefaultTableModel model = (DefaultTableModel) attendencetable.getModel();
        Object[] row = new Object[6];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getEID();
            row[1] = list.get(i).getPID();
            row[2] = list.get(i).getAttendDate();
            row[3] = list.get(i).getIntime();
            row[4] = list.get(i).getOuttime();

            model.addRow(row);

        }

    }

    /*retrieve detailsof employees who are working under the supervisor*/
    public ArrayList<Userunder> user_allshow_List() {
        ArrayList<Userunder> user_allshow_List = new ArrayList<>();
        try {
            Connection con = DataBaseConnection.getDBconnection();

            String query = "SELECT PID FROM project_assign where EmployeeID='" + txt_search_emp.getText() + "' AND Position='Supervisor'";
            st = con.prepareStatement(query);
            st = con.createStatement();
            rs = st.executeQuery(query);

            if (rs.next()) {
                pid = rs.getString("PID");
            } else {
                JOptionPane.showMessageDialog(null, "Employee ID is does not exist");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "System Error Occured");
        }
        return user_allshow_List;
    }

    public ArrayList<Userunder2> user_aallshow_List() {
        ArrayList<Userunder2> user_aallshow_List = new ArrayList<>();
        try {
            Connection con = DataBaseConnection.getDBconnection();

            String query1 = "SELECT * FROM project_assign WHERE PID='" + pid + "' AND Position='Labourer'";
            st1 = con.prepareStatement(query1);
            st1 = con.createStatement();
            rs1 = st1.executeQuery(query1);
            /*  PID='"+pid+"'"; */

            Userunder2 userunder2;
            while (rs1.next()) {
                String e = rs1.getString("EmployeeID");
                userunder2 = new Userunder2(rs1.getString("EmployeeID"), rs1.getString("PID"), rs1.getString("Startdate"), rs1.getString("Enddate"), rs1.getString("Position"), rs1.getInt("Status"));
                user_aallshow_List.add(userunder2);
                System.out.println(e);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return user_aallshow_List;
    }

    public void allempuser() {

        ArrayList<Userunder2> list = user_aallshow_List();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        Object[] row = new Object[6];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getEID();

            row[1] = list.get(i).getPID();
            row[2] = list.get(i).getStartdate();
            row[3] = list.get(i).getEnddate();
            row[4] = list.get(i).getPosition();
            row[5] = list.get(i).getStatus();
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
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        lblID = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_empFname = new javax.swing.JTextField();
        txt_addressNumber = new javax.swing.JTextField();
        rdo_Married = new javax.swing.JRadioButton();
        rdo_Single = new javax.swing.JRadioButton();
        rdo_Male = new javax.swing.JRadioButton();
        rdo_Female = new javax.swing.JRadioButton();
        txt_ContactNo = new javax.swing.JTextField();
        txt_eMail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_bSalary = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_addressStreet = new javax.swing.JTextField();
        txt_addressCity = new javax.swing.JTextField();
        txt_empLname = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btn_Browse = new javax.swing.JButton();
        btn_addDetails = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        cmb_Position = new javax.swing.JComboBox<>();
        btb_manageDetails = new javax.swing.JButton();
        btb_manageDetails1 = new javax.swing.JButton();
        txt_filename = new javax.swing.JTextField();
        lbl_empid = new javax.swing.JLabel();
        lbl_empIMG = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        btn_empSearch1 = new javax.swing.JButton();
        txt_search_emp = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        btn_attendance_empID_Search = new javax.swing.JButton();
        btn_Invetory_view1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        attendencetable = new javax.swing.JTable();
        txt_attendence_empid = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        cmb_leaveType = new javax.swing.JComboBox<>();
        cmb_leaveDuration = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txta_leaveDes = new javax.swing.JTextArea();
        rdo_accept = new javax.swing.JRadioButton();
        rdo_decline = new javax.swing.JRadioButton();
        btn_addLeaveDetails = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        btn_leave_empID_Search = new javax.swing.JButton();
        btn_addLeaveDetails1 = new javax.swing.JButton();
        txt_leaveempid = new javax.swing.JTextField();
        txt_leavesearchid = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        leavetable = new javax.swing.JTable();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        setBackground(java.awt.Color.lightGray);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Employee Management");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/sss.png"))); // NOI18N
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setPreferredSize(new java.awt.Dimension(1000, 600));
        setVisible(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jTabbedPane1.setBackground(new java.awt.Color(44, 62, 80));
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTabbedPane1.setMaximumSize(new java.awt.Dimension(132767, 132767));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(900, 600));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(962, 600));

        jScrollPane4.setMinimumSize(new java.awt.Dimension(962, 737));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(962, 737));

        jPanel1.setBackground(java.awt.Color.darkGray);
        jPanel1.setPreferredSize(new java.awt.Dimension(962, 731));

        lblID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblID.setForeground(new java.awt.Color(255, 255, 255));
        lblID.setText("ID");

        lblName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblName.setForeground(new java.awt.Color(255, 255, 255));
        lblName.setText("First Name");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Address");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Gender");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Contact Number");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Email");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Status");

        txt_empFname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_empFnameActionPerformed(evt);
            }
        });

        rdo_Married.setBackground(new java.awt.Color(44, 62, 80));
        rdo_Married.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdo_Married.setForeground(new java.awt.Color(255, 255, 255));
        rdo_Married.setText("Married");
        rdo_Married.setBorder(null);
        rdo_Married.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rdo_Married.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_MarriedActionPerformed(evt);
            }
        });

        rdo_Single.setBackground(new java.awt.Color(44, 62, 80));
        rdo_Single.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdo_Single.setForeground(new java.awt.Color(255, 255, 255));
        rdo_Single.setText("Single");
        rdo_Single.setBorder(null);
        rdo_Single.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        rdo_Male.setBackground(new java.awt.Color(44, 62, 80));
        rdo_Male.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdo_Male.setForeground(new java.awt.Color(255, 255, 255));
        rdo_Male.setText("Male");
        rdo_Male.setBorder(null);
        rdo_Male.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        rdo_Female.setBackground(new java.awt.Color(44, 62, 80));
        rdo_Female.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdo_Female.setForeground(new java.awt.Color(255, 255, 255));
        rdo_Female.setText("Female");
        rdo_Female.setBorder(null);
        rdo_Female.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Basic Salary");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Number");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Street");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("City");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Last Name");

        btn_Browse.setBackground(new java.awt.Color(255, 255, 255));
        btn_Browse.setText("Browse");
        btn_Browse.setBorder(null);
        btn_Browse.setIconTextGap(5);
        btn_Browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BrowseActionPerformed(evt);
            }
        });

        btn_addDetails.setBackground(new java.awt.Color(255, 255, 255));
        btn_addDetails.setText("Add Details");
        btn_addDetails.setBorder(null);
        btn_addDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addDetailsActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Position");

        cmb_Position.setEditable(true);
        cmb_Position.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Labourer", "Supervisor", "Manager", "Administrator" }));

        btb_manageDetails.setBackground(new java.awt.Color(255, 255, 255));
        btb_manageDetails.setText("Manage Details");
        btb_manageDetails.setBorder(null);
        btb_manageDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btb_manageDetailsActionPerformed(evt);
            }
        });

        btb_manageDetails1.setBackground(new java.awt.Color(255, 255, 255));
        btb_manageDetails1.setText("Assign Projects");
        btb_manageDetails1.setBorder(null);
        btb_manageDetails1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btb_manageDetails1ActionPerformed(evt);
            }
        });

        lbl_empid.setForeground(java.awt.Color.white);
        lbl_empid.setText("Emp_ID");

        lbl_empIMG.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Image", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_ContactNo)
                                    .addComponent(txt_eMail)
                                    .addComponent(txt_bSalary)
                                    .addComponent(cmb_Position, 0, 193, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btb_manageDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_addDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btb_manageDetails1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_empLname, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                        .addComponent(txt_empFname))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rdo_Married)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdo_Single)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 434, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_filename)
                                    .addComponent(lbl_empIMG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_Browse, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_empid, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdo_Male)
                                .addGap(18, 18, 18)
                                .addComponent(rdo_Female)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_addressNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_addressCity, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txt_addressStreet, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btb_manageDetails, btb_manageDetails1, btn_Browse, btn_addDetails, cmb_Position});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblID)
                            .addComponent(lbl_empid))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblName)
                            .addComponent(txt_empFname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txt_empLname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(rdo_Married)
                            .addComponent(rdo_Single))
                        .addGap(35, 35, 35)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_addressNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt_addressStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txt_addressCity, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_empIMG, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_filename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Browse, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rdo_Male)
                    .addComponent(rdo_Female))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_ContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_eMail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_bSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(cmb_Position, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_addDetails)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btb_manageDetails)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btb_manageDetails1)))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btb_manageDetails, btb_manageDetails1, btn_Browse, btn_addDetails});

        jScrollPane4.setViewportView(jPanel1);

        jTabbedPane1.addTab("Add Employee", jScrollPane4);

        jPanel6.setBackground(java.awt.Color.darkGray);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("EMP ID");

        btn_empSearch1.setBackground(new java.awt.Color(255, 255, 255));
        btn_empSearch1.setText("Search");
        btn_empSearch1.setMaximumSize(new java.awt.Dimension(35, 35));
        btn_empSearch1.setMinimumSize(new java.awt.Dimension(35, 35));
        btn_empSearch1.setPreferredSize(new java.awt.Dimension(35, 35));
        btn_empSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_empSearch1ActionPerformed(evt);
            }
        });

        txt_search_emp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_search_empKeyReleased(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EID", "PID", "startdate", "Enddate", "Position", "status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(txt_search_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_empSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search_emp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(btn_empSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("View Employee", jPanel6);

        jPanel3.setBackground(java.awt.Color.darkGray);

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("EMP ID");

        btn_attendance_empID_Search.setBackground(new java.awt.Color(255, 255, 255));
        btn_attendance_empID_Search.setText("Search");
        btn_attendance_empID_Search.setBorder(null);
        btn_attendance_empID_Search.setMaximumSize(new java.awt.Dimension(35, 35));
        btn_attendance_empID_Search.setMinimumSize(new java.awt.Dimension(35, 35));
        btn_attendance_empID_Search.setPreferredSize(new java.awt.Dimension(35, 35));
        btn_attendance_empID_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_attendance_empID_SearchActionPerformed(evt);
            }
        });

        btn_Invetory_view1.setBackground(new java.awt.Color(255, 255, 255));
        btn_Invetory_view1.setText("Add Attendance Manually");
        btn_Invetory_view1.setMaximumSize(new java.awt.Dimension(140, 47));
        btn_Invetory_view1.setMinimumSize(new java.awt.Dimension(140, 47));
        btn_Invetory_view1.setPreferredSize(new java.awt.Dimension(140, 47));
        btn_Invetory_view1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Invetory_view1ActionPerformed(evt);
            }
        });

        attendencetable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EID", "PID", "Date", "In Time", "Out Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(attendencetable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(18, 18, 18)
                        .addComponent(txt_attendence_empid, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_attendance_empID_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Invetory_view1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(txt_attendence_empid)
                    .addComponent(jLabel33)
                    .addComponent(btn_attendance_empID_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btn_Invetory_view1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("View Attendance Details", jPanel3);

        jPanel4.setBackground(java.awt.Color.darkGray);
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("ID");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Date");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Leave Type");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Duration");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Description");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Approval");

        cmb_leaveType.setEditable(true);
        cmb_leaveType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Personal Leave", "Administrative Leave", "Illness" }));

        cmb_leaveDuration.setEditable(true);
        cmb_leaveDuration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 Day", "3 Day", "1 Week", "1 Month", " " }));

        txta_leaveDes.setColumns(20);
        txta_leaveDes.setRows(5);
        jScrollPane1.setViewportView(txta_leaveDes);

        rdo_accept.setBackground(new java.awt.Color(44, 62, 80));
        rdo_accept.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdo_accept.setForeground(new java.awt.Color(255, 255, 255));
        rdo_accept.setText("Accept");
        rdo_accept.setBorder(null);

        rdo_decline.setBackground(new java.awt.Color(44, 62, 80));
        rdo_decline.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdo_decline.setForeground(new java.awt.Color(255, 255, 255));
        rdo_decline.setText("Decline");
        rdo_decline.setBorder(null);
        rdo_decline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_declineActionPerformed(evt);
            }
        });

        btn_addLeaveDetails.setBackground(new java.awt.Color(255, 255, 255));
        btn_addLeaveDetails.setText("View");
        btn_addLeaveDetails.setBorder(null);
        btn_addLeaveDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addLeaveDetailsActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("ID");

        btn_leave_empID_Search.setBackground(new java.awt.Color(255, 255, 255));
        btn_leave_empID_Search.setText("Search");
        btn_leave_empID_Search.setBorder(null);
        btn_leave_empID_Search.setMaximumSize(new java.awt.Dimension(35, 35));
        btn_leave_empID_Search.setMinimumSize(new java.awt.Dimension(35, 35));
        btn_leave_empID_Search.setPreferredSize(new java.awt.Dimension(35, 35));
        btn_leave_empID_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_leave_empID_SearchActionPerformed(evt);
            }
        });

        btn_addLeaveDetails1.setBackground(new java.awt.Color(255, 255, 255));
        btn_addLeaveDetails1.setText("Add Details");
        btn_addLeaveDetails1.setBorder(null);
        btn_addLeaveDetails1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addLeaveDetails1ActionPerformed(evt);
            }
        });

        leavetable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EID", "LeaveDate", "LeaveType", "Duration", "Approval"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane7.setViewportView(leavetable);

        jDateChooser1.setBackground(java.awt.Color.darkGray);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_leaveempid, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmb_leaveType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmb_leaveDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_addLeaveDetails1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(txt_leavesearchid, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_leave_empID_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(rdo_accept)
                        .addGap(38, 38, 38)
                        .addComponent(rdo_decline)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_addLeaveDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(97, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(95, 95, 95))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txt_leaveempid, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel37)
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(cmb_leaveType, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(cmb_leaveDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addGap(37, 37, 37)))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(rdo_accept)
                            .addComponent(rdo_decline)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btn_addLeaveDetails1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_addLeaveDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addGap(22, 22, 22)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(txt_leavesearchid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_leave_empID_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(jPanel4);

        jTabbedPane1.addTab("Leaves", jScrollPane5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_addLeaveDetails1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addLeaveDetails1ActionPerformed
        // TODO add your handling code here:
        int accept = 0;
        try {
            Connection con = DataBaseConnection.getDBconnection();
            String query = "insert into empleaves( EmployeeID, Date, Leavetype, Duration, Description, Approvel) values(?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(query);

            String empid = txt_leaveempid.getText();
            String date = ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText();
            String leavet = cmb_leaveType.getSelectedItem().toString();
            String leaved = cmb_leaveDuration.getSelectedItem().toString();
            String approvel = null;

            if ((rdo_accept.isSelected()) || (rdo_decline.isSelected())) {

                pst.setString(1, empid);
                pst.setString(2, date);
                pst.setString(3, leavet);
                pst.setString(4, leaved);
                pst.setString(5, txta_leaveDes.getText());

                if (rdo_accept.isSelected()) {
                    accept = 1;
                    approvel = "approved";
                }
                if (rdo_decline.isSelected()) {
                    accept = 0;
                    approvel = "decline";
                }

                pst.setString(6, approvel);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "inserted successfully");

            } else {
                JOptionPane.showMessageDialog(null, "Please select accept or decline");
            }

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "System Error Occured");
        }

    }//GEN-LAST:event_btn_addLeaveDetails1ActionPerformed

    private void btn_leave_empID_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_leave_empID_SearchActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) leavetable.getModel();
        model.setRowCount(0);
        userList();
        showuser();
    }//GEN-LAST:event_btn_leave_empID_SearchActionPerformed

    private void btn_addLeaveDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addLeaveDetailsActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) leavetable.getModel();
        model.setRowCount(0);

        user_leave_List();
        allshowuser();
    }//GEN-LAST:event_btn_addLeaveDetailsActionPerformed

    private void rdo_declineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_declineActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_declineActionPerformed

    private void btn_Invetory_view1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Invetory_view1ActionPerformed
        // TODO add your handling code here:
        AttendenceManually am = new AttendenceManually();
        dp_home.add(am).setVisible(true);

    }//GEN-LAST:event_btn_Invetory_view1ActionPerformed

    private void btn_attendance_empID_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_attendance_empID_SearchActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) attendencetable.getModel();
        model.setRowCount(0);
        user_attend_List();
        attendshowuser();
    }//GEN-LAST:event_btn_attendance_empID_SearchActionPerformed

    private void txt_search_empKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_search_empKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_search_empKeyReleased

    private void btn_empSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_empSearch1ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        user_allshow_List();
        user_aallshow_List();
        allempuser();

    }//GEN-LAST:event_btn_empSearch1ActionPerformed

    private void btb_manageDetails1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btb_manageDetails1ActionPerformed
        // TODO add your handling code here:
        Project_Assignments pa = new Project_Assignments();
        dp_home.add(pa).setVisible(true);
    }//GEN-LAST:event_btb_manageDetails1ActionPerformed

    private void btb_manageDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btb_manageDetailsActionPerformed
        // TODO add your handling code here:
        Manage_Employee memp = new Manage_Employee();
        dp_home.add(memp).setVisible(true);
    }//GEN-LAST:event_btb_manageDetailsActionPerformed

    private void btn_addDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addDetailsActionPerformed
        // TODO add your handling code here:

        String columnValue = null;
        String recordid = null;
        String newrecord = null;

        try {

            Connection con = DataBaseConnection.getDBconnection();
            String query = "select * from employees ORDER BY EmployeeID DESC LIMIT 1";
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
            newrecord = "EMP100000";
        } else {
            String lastrecordid = columnValue.substring(3, 9);
            System.out.print(lastrecordid);
            int newid = Integer.parseInt(lastrecordid);
            int newrecordid = newid + 1;
            String id = Integer.toString(newrecordid);
            newrecord = "EMP" + id;
        }

        String Firstname = txt_empFname.getText();
        String Lastname = txt_empLname.getText();
        int addressno = Integer.parseInt(txt_addressNumber.getText());
        String street = txt_addressStreet.getText();
        String city = txt_addressCity.getText();
        String contactno = txt_ContactNo.getText();
        int s1 = contactno.length();
        String email = txt_eMail.getText();
        //String birth=txt_DOB.getText();
        String position = cmb_Position.getSelectedItem().toString();
        String basicsalary = txt_bSalary.getText();
        int salary = Integer.parseInt(basicsalary);
        String gender = null;
        String marital = null;

        if (rdo_Male.isSelected()) {
            gender = "male";
        } else {
            gender = "female";
        }

        if (rdo_Married.isSelected()) {
            marital = "married";
        } else {
            marital = "unmarried";
        }

        try {
            Connection con = DataBaseConnection.getDBconnection();
            String query = " insert into employees (EmployeeID,FirstName,LastName,MaritalStatus,HouseNo,Street,City,Gender,ContactNo,Email,Position,BasicSalary,Image,Status) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?)";
            PreparedStatement pst = con.prepareStatement(query);

            if ((Firstname.equals("")) || (Lastname.equals("")) || (street.equals("")) || city.equals("") || email.equals("")) {
                JOptionPane.showMessageDialog(null, "Error");
            } else {

                pst.setString(1, newrecord);
                pst.setString(2, Firstname);
                pst.setString(3, Lastname);
                pst.setString(4, marital);
                pst.setInt(5, addressno);
                pst.setString(6, street);
                pst.setString(7, city);
                pst.setString(8, gender);
                pst.setString(9, contactno);
                pst.setString(10, email);
                pst.setString(11, position);
                pst.setInt(12, salary);
                pst.setBytes(13, person_img);
                pst.setInt(14, 1);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "inserted successfully");
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error");
        }

    }//GEN-LAST:event_btn_addDetailsActionPerformed

    private void btn_BrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BrowseActionPerformed

        /*Browse button*/
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        lbl_empIMG.setIcon(new ImageIcon(f.toString()));
        filename = f.getAbsolutePath();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(lbl_empIMG.getWidth(), lbl_empIMG.getHeight(), lbl_empIMG.getWidth()));
        lbl_empIMG.setIcon(imageIcon);
        String filename = f.getAbsolutePath();
        txt_filename.setText(filename);

        try {

            File image = new File(filename);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);

            }
            person_img = bos.toByteArray();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

    }//GEN-LAST:event_btn_BrowseActionPerformed

    private void rdo_MarriedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_MarriedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_MarriedActionPerformed

    private void txt_empFnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_empFnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_empFnameActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:

    }//GEN-LAST:event_formInternalFrameOpened

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
            java.util.logging.Logger.getLogger(Employee_Management.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Employee_Management.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Employee_Management.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Employee_Management.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new Employee_Management().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable attendencetable;
    private javax.swing.JButton btb_manageDetails;
    private javax.swing.JButton btb_manageDetails1;
    private javax.swing.JButton btn_Browse;
    private javax.swing.JButton btn_Invetory_view1;
    private javax.swing.JButton btn_addDetails;
    private javax.swing.JButton btn_addLeaveDetails;
    private javax.swing.JButton btn_addLeaveDetails1;
    private javax.swing.JButton btn_attendance_empID_Search;
    private javax.swing.JButton btn_empSearch1;
    private javax.swing.JButton btn_leave_empID_Search;
    private javax.swing.JComboBox<String> cmb_Position;
    private javax.swing.JComboBox<String> cmb_leaveDuration;
    private javax.swing.JComboBox<String> cmb_leaveType;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lbl_empIMG;
    private javax.swing.JLabel lbl_empid;
    private javax.swing.JTable leavetable;
    private javax.swing.JRadioButton rdo_Female;
    private javax.swing.JRadioButton rdo_Male;
    private javax.swing.JRadioButton rdo_Married;
    private javax.swing.JRadioButton rdo_Single;
    private javax.swing.JRadioButton rdo_accept;
    private javax.swing.JRadioButton rdo_decline;
    private javax.swing.JTextField txt_ContactNo;
    private javax.swing.JTextField txt_addressCity;
    private javax.swing.JTextField txt_addressNumber;
    private javax.swing.JTextField txt_addressStreet;
    private javax.swing.JTextField txt_attendence_empid;
    private javax.swing.JTextField txt_bSalary;
    private javax.swing.JTextField txt_eMail;
    private javax.swing.JTextField txt_empFname;
    private javax.swing.JTextField txt_empLname;
    private javax.swing.JTextField txt_filename;
    private javax.swing.JTextField txt_leaveempid;
    private javax.swing.JTextField txt_leavesearchid;
    private javax.swing.JTextField txt_search_emp;
    private javax.swing.JTextArea txta_leaveDes;
    // End of variables declaration//GEN-END:variables
}
