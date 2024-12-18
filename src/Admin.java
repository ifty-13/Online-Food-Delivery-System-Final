import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Admin extends JFrame{
	private JLabel title;
	private JButton updatebutton;
	private JButton addUserButton;
	private JButton del;
	private JButton orders;
	private JButton payment;
	private JButton rmanager;
	private JTable jTable1;
	private JScrollPane jScrollPane1;
	private DefaultTableModel model;

	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static PreparedStatement pst = null;

	int rtc;

	public Admin() {
		super("Admin");

		try {
			initComponents();
			//getUserID();
			DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
			dbConnect();
			String sql="SELECT * FROM food.details";
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next())
			{
				int id = rs.getInt("user_id");
				String uname = rs.getString("username");
				String pass = rs.getString("passwd");
				String em = rs.getString("email");
				String fname = rs.getString("firstname");
				String lname = rs.getString("lastname");
				String ph = rs.getString("phone");
				String st = rs.getString("state");
				Object row[] = {id, uname, pass, em, fname, lname, ph, st};
				model.addRow(row);
			}
		}
		catch(Exception except) {
			System.out.println("Error "+except);
		}
		// Center the frame
		setLocationRelativeTo(null); // This will center the JFrame on the screen
	}

	public void initComponents() {

		title = new JLabel("Welcome Admin");
		del = new JButton("Delete Selected");
		addUserButton = new JButton("Create User");
		updatebutton = new JButton("Update Selected");
		orders = new JButton("View All Orders");
		payment = new JButton("View All Payments");
		rmanager = new JButton("View and Update Menus");
		jTable1 = new JTable();
		jScrollPane1 = new JScrollPane();

		//model = new DefaultTableModel(new String [] {"ID", "Username", "Password", "Email", "Firstname", "Lastname", "Phone", "State"},0);
		model = (DefaultTableModel) jTable1.getModel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setMinimumSize(new java.awt.Dimension(700, 700));
		setResizable(false);
		getContentPane().setLayout(null);

		title.setFont(new Font("Times New Roman", 0, 28));
		getContentPane().add(title);
		title.setBounds(200,10,200,40);

		//jTable1.setBackground(new java.awt.Color(51, 51, 51));
		jTable1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1,1,1,1));
		//jTable1.setFont(new java.awt.Font("Candara", 0, 15)); // NOI18N
		//jTable1.setForeground(new java.awt.Color(1, 204, 1));
		jTable1.setRowHeight(40);
		jTable1.setModel(model);

		jTable1.setModel(new javax.swing.table.DefaultTableModel(
				new Object [][] {

				},
				new String [] {
						"ID", "Username", "Password", "Email", "Firstname", "Lastname", "Phone", "State"
				}
		) {
			boolean[] canEdit = new boolean [] {
					false, true, true, true, false, false, true, true
			};

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit [columnIndex];
			}
		});


		jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
		jTable1.setName(""); // NOI18N
		jScrollPane1.setViewportView(jTable1);

		if (jTable1.getColumnModel().getColumnCount() > 0) {
			jTable1.getColumnModel().getColumn(0).setResizable(false);
			jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
			jTable1.getColumnModel().getColumn(1).setResizable(false);
			jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
			jTable1.getColumnModel().getColumn(2).setResizable(false);
			jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
			jTable1.getColumnModel().getColumn(3).setResizable(false);
			jTable1.getColumnModel().getColumn(3).setPreferredWidth(200);
			jTable1.getColumnModel().getColumn(4).setResizable(false);
			jTable1.getColumnModel().getColumn(4).setPreferredWidth(100);
			jTable1.getColumnModel().getColumn(5).setResizable(false);
			jTable1.getColumnModel().getColumn(5).setPreferredWidth(100);
			jTable1.getColumnModel().getColumn(6).setResizable(false);
			jTable1.getColumnModel().getColumn(6).setPreferredWidth(100);
			jTable1.getColumnModel().getColumn(7).setResizable(false);
			jTable1.getColumnModel().getColumn(7).setPreferredWidth(50);
		}

		getContentPane().add(jScrollPane1);
		jScrollPane1.setBounds(40, 65, 600, 400);

		// add addUserButton

		del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteButtonClick(e);
			}
		});
		getContentPane().add(del);
		del.setBounds(60,500,160,40);

		addUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUserButtonClick(e);
			}
		});
		getContentPane().add(addUserButton);
		addUserButton.setBounds(430,500,160,40);

		updatebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateButtonClick(e);
			}
		});
		getContentPane().add(updatebutton);
		updatebutton.setBounds(250,500,160,40);

		orders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AllOrders ao = new AllOrders();
				ao.setVisible(true);
			}
		});
		getContentPane().add(orders);
		orders.setBounds(60,560,160,40);

		payment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AllPayments ap = new AllPayments();
				ap.setVisible(true);
			}
		});
		getContentPane().add(payment);
		payment.setBounds(250,560,160,40);

		rmanager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RestaurantManager rm = new RestaurantManager();
				rm.setVisible(true);
			}
		});
		getContentPane().add(rmanager);
		rmanager.setBounds(430,560,180,40);
	}
	private void addUserButtonClick(ActionEvent e) {
		dbConnect();

		try {
			String username = JOptionPane.showInputDialog("Enter Username:");
			String password = JOptionPane.showInputDialog("Enter Password:");
			String email = JOptionPane.showInputDialog("Enter Email:");
			String firstname = JOptionPane.showInputDialog("Enter Firstname:");
			String lastname = JOptionPane.showInputDialog("Enter Lastname:");
			String phone = JOptionPane.showInputDialog("Enter Phone:");
			String state = JOptionPane.showInputDialog("Enter State:");

			if (username != null && password != null && email != null && firstname != null && lastname != null && phone != null && state != null) {
				// Get the last ID from the database
				pst = con.prepareStatement("SELECT MAX(user_id) FROM food.details");
				ResultSet rs = pst.executeQuery();
				int lastId = 0;
				if (rs.next()) {
					lastId = rs.getInt(1); // Fetch the maximum ID
				}
				int newId = lastId + 1; // Calculate the new ID

				// Insert the new user into the database
				pst = con.prepareStatement("INSERT INTO food.details (user_id, username, passwd, email, firstname, lastname, phone, state) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
				pst.setInt(1, newId);
				pst.setString(2, username);
				pst.setString(3, password);
				pst.setString(4, email);
				pst.setString(5, firstname);
				pst.setString(6, lastname);
				pst.setString(7, phone);
				pst.setString(8, state);

				int rowsAffected = pst.executeUpdate();

				if (rowsAffected == 1) {
					DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
					// Add a new row with the new ID
					Object[] row = { Integer.toString(newId), username, password, email, firstname, lastname, phone, state};
					model.addRow(row);
					JOptionPane.showMessageDialog(null, "User added successfully!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "All fields are required.");
			}
		} catch (Exception except) {
			JOptionPane.showMessageDialog(null, except.getMessage());
		}
	}




	public void updateButtonClick(ActionEvent e) {
		dbConnect();

		int i = 0;
		DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
		int rc = model.getRowCount();
		rtc = 0;
		int row = jTable1.getSelectedRow();

		String uid = model.getValueAt(row, 0).toString();
		String uname = model.getValueAt(row, 1).toString();
		String pass= model.getValueAt(row, 2).toString();
		String ema = model.getValueAt(row, 3).toString();
		String p = model.getValueAt(row, 6).toString();
		String s = model.getValueAt(row, 7).toString();

		try {
			int id = Integer.parseInt(uid);
			pst = (PreparedStatement) con.prepareStatement("UPDATE food.details SET username = ? , passwd = ? , email = ? , phone = ? , state = ? WHERE user_id = ?");


			pst.setString(1, uname);
			pst.setString(2, pass);
			pst.setString(3, ema);
			pst.setString(4, p);
			pst.setString(5, s);
			pst.setInt(6, id);


			int rowsAffected = pst.executeUpdate();


			if(rowsAffected == 1) {
				JOptionPane.showMessageDialog(null, "Update Successful!");
			}

		}
		catch(Exception except) {
			JOptionPane.showMessageDialog(null,  except.getMessage());
		}
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
		new Admin().setVisible(true);

	}

}
