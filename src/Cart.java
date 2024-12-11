import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Cart  extends JFrame{
	public static float total = 0;
	public static int userid = 0;
	
	private JLabel title;
	private JButton payment;
	private JButton del;
	private JTable jTable1;
	private JScrollPane jScrollPane1;
	private DefaultTableModel model;
	
	static Connection con = null;
    static Statement stmt = null;
    static ResultSet rs = null;
    
    static float amt[] = new float[40];
    static String price[] = new String[40];
    static String dishname[] = new String[40];
    static String user;
    static String d[] = new String[40];
    static float x[] = new float[40];
    int total_items;
    //public float total;
    //int total = 0;
    int rtc;

	public Cart(){
		super("Cart");
		
		try {
			initComponents();
			//getUserID();
			DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
			dbConnect();
			String sql="SELECT * FROM food.cart";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				int id = rs.getInt("item_id");
                String name = rs.getString("item_name");
                float price = rs.getFloat("item_price");
                int quant = rs.getInt("item_quantity");
                Object row[] = {id, name, price, quant};
                model.addRow(row);
			}
		}
		catch(Exception except) {
			System.out.println("Error "+except);
		}
		/*
		try {
            initComponents();
            JTableHeader header = jTable1.getTableHeader();
            header.setEnabled(false);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            dbConnect();
            String q = "SELECT * FROM food.cart;";
            rs = stmt.executeQuery(q);
            int s = model.getRowCount();
            while (s != 0) {
                model.removeRow(0);
                s--;
            }
            while (rs.next()) {
            	
            	int id = rs.getInt("item_id");
                String name = rs.getString("item_name");
                float price = rs.getFloat("item_price");
                int quant = rs.getInt("item_quantity");
                Object row[] = {id, name, price, quant};
                model.addRow(row);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ItalianMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
		
	}
	
	public void initComponents() {
		title = new JLabel("My Cart");
		payment = new JButton("Proceed to Payment");
		del = new JButton("Delete Selected");
		jTable1 = new JTable();
		jScrollPane1 = new JScrollPane();
		
		model = new DefaultTableModel(new String [] {"ID", "Name", "Price", "Quantity"},0){
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 600));
        setResizable(false);
        getContentPane().setLayout(null);
        
        title.setFont(new java.awt.Font("Times New Roman", 0, 30));
        getContentPane().add(title);
        title.setBounds(200,10,140,40);
        
        //jTable1.setBackground(new java.awt.Color(51, 51, 51));
        jTable1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1,1,1,1));
        //jTable1.setFont(new java.awt.Font("Candara", 0, 15)); // NOI18N
        //jTable1.setForeground(new java.awt.Color(1, 204, 1));
        jTable1.setRowHeight(40);
        jTable1.setModel(model);
        /*
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "",""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        */
        
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setName(""); // NOI18N
        jScrollPane1.setViewportView(jTable1);
        
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(160);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(60, 50, 350, 400);
        
        del.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		deleteButtonClick(e);
        	}
        });
        getContentPane().add(del);
        del.setBounds(60,500,160,40);
        
        payment.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		paymentButtonClick(e);
        	}
        });
        getContentPane().add(payment);
        payment.setBounds(250,500, 160, 40);
		
	}
	
	private void deleteButtonClick(ActionEvent e) {
		dbConnect();
		
		int i = 0;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int rc = model.getRowCount();
        rtc = 0;
        int row = jTable1.getSelectedRow();
		String eve = model.getValueAt(row, 0).toString();
		
		try {
			int d = Integer.parseInt(eve);
			PreparedStatement st = (PreparedStatement) con.prepareStatement("DELETE FROM food.cart WHERE item_id = ?");
			st.setInt(1,d);
			int rowsAffected = st.executeUpdate();
			model.removeRow(row);
		}
		catch(Exception except) {
			JOptionPane.showMessageDialog(null,  except.getMessage());
		}
	}
	
	private void paymentButtonClick(ActionEvent e) {
		Login log = new Login();
		System.out.println(log.user);
		Payment pay = new Payment();
		pay.setVisible(true);
		//dbConnect();
		int i = 0;
		//float total = 0;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int rc = model.getRowCount();
        rtc = 0;
        //System.out.println(rc);
        while (i != rc) {
        	d[i] = jTable1.getValueAt(i, 3).toString();
        	total_items += Integer.parseInt(d[i]);
            if (Integer.parseInt(d[i]) != 0) {
                //dishname[i] = jTable1.getValueAt(i, 1).toString();
                price[i] = jTable1.getValueAt(i, 2).toString();
                amt[i] = Float.parseFloat(price[i]) * Integer.parseInt(d[i]);
                total += amt[i];
                //System.out.println("\n" + d + "\n");
                System.out.println(amt[i]);
                rtc++;
            }
            i++;
        }
        JOptionPane.showMessageDialog(null, "Order cost is "+total);
        
        try {
        	PreparedStatement st = (PreparedStatement) con.prepareStatement("INSERT INTO food.orders (username, total_items, amount, order_status) VALUES (?, ?, ?, ?)");
        	st.setString(1, log.user);
        	st.setInt(2, total_items);
        	st.setFloat(3, total);
        	st.setString(4, "Processed");
        	
        	int rowsAffected = st.executeUpdate();
        	
        	//System.out.println(log.userid);
        }
        catch(Exception except) {
        	System.out.println("Error "+except);
        }
        System.out.println("Total is "+total);
        System.out.println("Total items "+ total_items);
        dispose();
	}
	
	public void dbConnect() {
		try {
            Class.forName("java.sql.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food","root","root");
            stmt = con.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Error!", JOptionPane.ERROR_MESSAGE);

        }
	}

	public static void main(String[] args) {
		new Cart().setVisible(true);

	}

}
