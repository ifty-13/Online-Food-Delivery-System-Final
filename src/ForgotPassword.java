import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.cj.xdevapi.Statement;

public class ForgotPassword extends JFrame implements ActionListener{
	private JLabel title;
	private JLabel usr;
	private JLabel passwd;
	private JLabel conpasswd;
	private JTextField user;
	private JPasswordField newpass;
	private JPasswordField conpass;
	private JButton reset;

	public ForgotPassword() {
		super("Forgot Password");

		title = new JLabel("Forgot Password");
		title.setFont(new java.awt.Font("Times New Roman", 0, 20));
		title.setBounds(175, 30, 140, 40);
		add(title);

		usr = new JLabel("Username");
		usr.setBounds(80, 80, 140, 40);
		add(usr);

		user = new JTextField();
		user.setBounds(160, 85, 170, 30);
		add(user);

		passwd = new JLabel("New Password");
		passwd.setBounds(60, 120, 140, 40);
		add(passwd);

		newpass = new JPasswordField();
		newpass.setBounds(160, 125, 170, 30);
		add(newpass);

		conpasswd = new JLabel("Confirm Password");
		conpasswd.setBounds(40, 160, 140, 40);
		add(conpasswd);

		conpass = new JPasswordField();
		conpass.setBounds(160, 165, 170, 30);
		add(conpass);

		reset = new JButton("Reset Password");
		reset.setBounds(155, 230, 140, 40);
		add(reset);
		reset.addActionListener(this);

		setSize(450,350);
		setResizable(false);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setLayout(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String usern = user.getText();
		String newpassword = String.valueOf(newpass.getPassword());
		String confirmpassword = String.valueOf(conpass.getPassword());

		if(newpassword.equals(confirmpassword)) {
			try {
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/food","root","root");
				PreparedStatement st = (PreparedStatement) conn.prepareStatement("UPDATE food.details SET passwd = ? WHERE username = ?");
				st.setString(1, newpassword);
				st.setString(2, usern);
				int rowsAffected = st.executeUpdate();

				if(rowsAffected == 1) {
					this.dispose();
					JOptionPane.showMessageDialog(null, "Password reset successfully");
				}
				else {
					//this.dispose();
					JOptionPane.showMessageDialog(null, "No such user exists in our records!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(Exception except) {
				System.out.println("Error "+except);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Passwords don't match!", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	public static void main(String[] args) {
		new ForgotPassword();

	}

}
