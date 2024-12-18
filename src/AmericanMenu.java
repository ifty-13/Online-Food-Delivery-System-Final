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

public class AmericanMenu extends JFrame implements ActionListener{
    private JLabel menubg;
    private JLabel americanheading;
    private JTable americantable;
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

    public AmericanMenu() {
        super("American Menu");

        try {
            initComponents();
            JTableHeader header = americantable.getTableHeader();
            header.setEnabled(false);
            DefaultTableModel model = (DefaultTableModel) americantable.getModel();
            connection();
            String q = "SELECT * FROM food.american;";
            rs = stmt.executeQuery(q);
            int s = model.getRowCount();
            while (s != 0) {
                model.removeRow(0);
                s--;
            }
            while (rs.next()) {

                int id = rs.getInt("AR_id");
                String name = rs.getString("AR_name");
                float price = rs.getFloat("AR_price");
                Object row[] = {id, name, price, '1'};
                model.addRow(row);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AmericanMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initComponents() {
        menubg = new JLabel();
        americanheading = new JLabel();
        americantable = new JTable();
        jScrollPane1 = new JScrollPane();
        addtocart = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 820));
        setResizable(false);
        getContentPane().setLayout(null);

        americanheading = new JLabel(new ImageIcon("src\\imgs\\americanheading.png")); // NOI18N
        americanheading.setOpaque(true);
        getContentPane().add(americanheading);
        americanheading.setBounds(90, 250, 400, 25);

        //americantable.setBackground(new java.awt.Color(51, 51, 51));
        americantable.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        americantable.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        americantable.setForeground(new java.awt.Color(0,0,139));
        americantable.setRowHeight(40);
        americantable.setModel(new javax.swing.table.DefaultTableModel(
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

        americantable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        americantable.setName(""); // NOI18N
        jScrollPane1.setViewportView(americantable);


        if (americantable.getColumnModel().getColumnCount() > 0) {
            americantable.getColumnModel().getColumn(0).setResizable(false);
            americantable.getColumnModel().getColumn(0).setPreferredWidth(40);
            americantable.getColumnModel().getColumn(1).setResizable(false);
            americantable.getColumnModel().getColumn(1).setPreferredWidth(210);
            americantable.getColumnModel().getColumn(2).setResizable(false);
            americantable.getColumnModel().getColumn(2).setPreferredWidth(80);
            americantable.getColumnModel().getColumn(3).setResizable(false);
        }

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(90, 290, 400, 320);

        addtocart.setText("Add to Cart");
        getContentPane().add(addtocart);
        addtocart.setBounds(210,630,140,40);
        addtocart.addActionListener(this);

        menubg = new JLabel(new ImageIcon("src\\imgs\\americanmenu.jpg"));
        //menubg.setIcon(new ImageIcon("C:\\Users\\Venus\\Desktop\\resize-15936008151897207572Capture2.png")); // NOI18N
        menubg.setText("Menu Background");
        menubg.setMaximumSize(new java.awt.Dimension(600, 820));
        menubg.setMinimumSize(new java.awt.Dimension(600, 820));
        //menubg.setPreferredSize(new java.awt.Dimension(599, 890));
        getContentPane().add(menubg);
        menubg.setBounds(0, 0, 600, 820);

        pack();
    }

  

    public void actionPerformed(ActionEvent e) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            int i = 0;
            DefaultTableModel model = (DefaultTableModel) americantable.getModel();
            int rc = model.getRowCount();
            rtc = 0;
            connection();
            //System.out.println(rc);
            while (i != rc) {
                d[i] = americantable.getValueAt(i, 3).toString();
                if (Integer.parseInt(d[i]) > 0) {
                    String dish = americantable.getValueAt(i, 1).toString();
                    String pr = americantable.getValueAt(i, 2).toString();
                    float pric = Float.parseFloat(pr);
                    int quant = Integer.parseInt(d[i]);
                    PreparedStatement st = (PreparedStatement) con.prepareStatement("INSERT INTO food.cart (item_name,item_price,item_quantity) VALUES (?,?,?);");
                    st.setString(1, dish);
                    st.setFloat(2, pric);
                    st.setInt(3, quant);
                    rowsAffected = st.executeUpdate();
                }
                i++;
            }
            if(rowsAffected == 1) {
                dispose();
                JOptionPane.showMessageDialog(null, "Items Added!");
            }
        }
        catch(Exception except) {
            System.out.println("Error "+except);
        }
    }

    public static void main(String[] args) {
        new AmericanMenu().setVisible(true);
    }
}
