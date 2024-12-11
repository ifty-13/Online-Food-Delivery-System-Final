import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Login extends JFrame implements ActionListener{
	public static String user;
	private JLabel title;
	private JLabel label1;
	private JLabel label2;
	public static JTextField username;
	private JPasswordField password;
	private JButton login;
	private JButton forgotpass;
	private JButton addUserButton;

	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static PreparedStatement pst = null;

	int rtc;
	public Login() {
		super("Login");

		title = new JLabel("Login");
		title.setFont(new java.awt.Font("Times New Roman", 0, 20));
		title.setBounds(220, 50, 140, 40);
		add(title);

		label1 = new JLabel("Username");
		label1.setBounds(125, 100, 140, 40);
		add(label1);

		label2 = new JLabel("Password");
		label2.setBounds(125, 150 , 140 ,40);
		add(label2);

		username = new JTextField();
		username.setBounds(200, 110, 140, 30);
		add(username);

		password = new JPasswordField();
		password.setBounds(200, 155, 140, 30);
		add(password);

		login = new JButton("Login");
		login.setBounds(110, 220, 140, 40);
		add(login);
		login.addActionListener(this);

		forgotpass = new JButton("Forgot Password");
		forgotpass.setBounds(260, 220, 140, 40);
		add(forgotpass);
		forgotpass.addActionListener(this);

		// Add User Button
		addUserButton = new JButton("Add User");
		addUserButton.setBounds(185, 270, 140, 40);
		add(addUserButton);
		addUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUserButtonClick(e);
			}
		});

		setSize(500, 400); // Adjusted size to fit the content
		setResizable(false);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setLayout(null);
		setLocationRelativeTo(null); // Center the frame
	}


	public void actionPerformed(ActionEvent e) {
		user = username.getText();
		String pass = String.valueOf(password.getPassword());

		if (e.getSource() == login) {
			try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/food", "root", "root");
				 PreparedStatement st = conn.prepareStatement("SELECT username, passwd FROM Details WHERE username=? AND passwd=?")) {

				st.setString(1, user);
				st.setString(2, pass);
				ResultSet rs = st.executeQuery();

				if (rs.next()) {
					if ("admin".equals(user) && "admin".equals(pass)) {
						JOptionPane.showMessageDialog(login, "Welcome Admin!");
						Admin ad = new Admin();
						ad.setVisible(true);
						this.dispose();
					}

					/*else if ("manager".equals(user) && "manager".equals(pass)) {
						JOptionPane.showMessageDialog(login, "Welcome Mr. Manager!");
						RestaurantManager rm = new RestaurantManager();
						rm.setVisible(true);
						this.dispose();
					} */

					else {
						JOptionPane.showMessageDialog(login, "You have successfully logged in");
						Home h = new Home();
						h.setVisible(true);
						this.dispose();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Wrong Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
				}

			} catch (SQLException except) {
				System.out.println("Error: " + except.getMessage());
				JOptionPane.showMessageDialog(null, "Database connection error: " + except.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == forgotpass) {
			ForgotPassword fp = new ForgotPassword();
			fp.setVisible(true);
		}
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
					JOptionPane.showMessageDialog(null, "User added successfully!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "All fields are required.");
			}
		} catch (Exception except) {
			JOptionPane.showMessageDialog(null, except.getMessage());
		}
	}

	public static void main(String[]args) {

		new Login().setVisible(true);;


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
}
