import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ItalianMenu extends JFrame implements ActionListener{
    private JLabel menubg;
    private JLabel itaheading;
    private JTable itatable;
    private JScrollPane jScrollPane1;
    private JButton addtocart;

    static Connection con = null;
    static Statement stmt = null;
    static ResultSet rs = null;
    static PreparedStatement ps = null;
    static int amount[] = new int[40];
    static String price[] = new String[40];
    static String dishname[] = new String[40];
    static String user;
    static String d[] = new String[40];
    int total = 0;
    int rtc;
    int rowsAffected;

    public ItalianMenu() {
        super("Menu Italiano");

        try {
            initComponents();
            JTableHeader header = itatable.getTableHeader();
            header.setEnabled(false);
            DefaultTableModel model = (DefaultTableModel) itatable.getModel();
            connection();
            String q = "SELECT * FROM food.italian;";
            rs = stmt.executeQuery(q);
            int s = model.getRowCount();
            while (s != 0) {
                model.removeRow(0);
                s--;
            }
            while (rs.next()) {

                int id = rs.getInt("ITA_id");
                String name = rs.getString("ITA_name");
                float price = rs.getFloat("ITA_price");
                Object row[] = {id, name, price, '1'};
                model.addRow(row);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ItalianMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Center the frame
        setLocationRelativeTo(null); // This will center the JFrame on the screen
    }

    private void initComponents() {

        menubg = new JLabel();
        itaheading = new JLabel();
        itatable = new JTable();
        jScrollPane1 = new JScrollPane();
        addtocart = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(599, 820));
        setResizable(false);
        getContentPane().setLayout(null);

        itaheading = new JLabel(new ImageIcon("src\\imgs\\italianhead.png")); // NOI18N
        itaheading.setOpaque(true);
        getContentPane().add(itaheading);
        itaheading.setBounds(90, 150, 400, 25);

        //itatable.setBackground(new java.awt.Color(51, 51, 51));
        itatable.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        itatable.setFont(new java.awt.Font("Candara", 0, 15)); // NOI18N
        itatable.setForeground(new java.awt.Color(1, 204, 1));
        itatable.setRowHeight(40);
        itatable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "", "", "", ""
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        itatable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        itatable.setName(""); // NOI18N
        jScrollPane1.setViewportView(itatable);

        if (itatable.getColumnModel().getColumnCount() > 0) {
            itatable.getColumnModel().getColumn(0).setResizable(false);
            itatable.getColumnModel().getColumn(0).setPreferredWidth(80);
            itatable.getColumnModel().getColumn(1).setResizable(false);
            itatable.getColumnModel().getColumn(1).setPreferredWidth(160);
            itatable.getColumnModel().getColumn(2).setResizable(false);
            itatable.getColumnModel().getColumn(2).setPreferredWidth(80);
            itatable.getColumnModel().getColumn(3).setResizable(false);
        }

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(90, 180, 400, 490);

        addtocart.setText("Add to Cart");
        getContentPane().add(addtocart);
        addtocart.setBounds(210,680,140,40);
        addtocart.addActionListener(this);

        menubg = new JLabel(new ImageIcon("src\\imgs\\italianmenu.png"));
        //menubg.setIcon(new ImageIcon("C:\\Users\\Venus\\Desktop\\resize-15936008151897207572Capture2.png")); // NOI18N
        menubg.setText("Menu Background");
        menubg.setMaximumSize(new java.awt.Dimension(599, 820));
        menubg.setMinimumSize(new java.awt.Dimension(599, 820));
        //menubg.setPreferredSize(new java.awt.Dimension(599, 890));
        getContentPane().add(menubg);
        menubg.setBounds(0, 0, 599, 820);

        pack();
    }

    public static void connection() {
        try {

            Class.forName("java.sql.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food","root","root");
            stmt = con.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Error!", JOptionPane.ERROR_MESSAGE);

        }
    }

    public static void main(String[] args) {
        new ItalianMenu().setVisible(true);;

    }

}
