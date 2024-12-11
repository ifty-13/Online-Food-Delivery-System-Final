import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CreateAccount extends JFrame implements ActionListener{
	private JLabel title;
	private JLabel uname;
	private JLabel passwrd;
	private JLabel confirmpass;
	private JLabel mail;
	private JLabel fname;
	private JLabel lname;
	private JLabel ph;
	private JLabel st;
	
	private JTextField usrname;
	private JPasswordField password;
	private JPasswordField conpassword;
	private JTextField email;
	private JTextField firstn;
	private JTextField lastn;
	private JTextField phone;
	private JTextField state;
	
	private JButton createButton;
	
	ResultSet rs = null;
	
	public CreateAccount() {
		super("Create Account");
		
		title = new JLabel("Create Account");
		title.setFont(new java.awt.Font("Times New Roman", 0, 20));
		title.setBounds(200, 30, 140, 40);
		add(title);
		
		fname = new JLabel("First Name");
		fname.setBounds(80, 80, 140, 40);
		add(fname);
		
		firstn = new JTextField();
		firstn.setBounds(150, 85, 210, 30);
		add(firstn);
		
		lname = new JLabel("Last Name");
		lname.setBounds(80, 120, 140, 40);
		add(lname);
		
		lastn = new JTextField();
		lastn.setBounds(150, 125, 210, 30);
		add(lastn);
		
		uname = new JLabel("Username");
		uname.setBounds(80, 160, 140, 40);
		add(uname);
		
		usrname = new JTextField();
		usrname.setBounds(150, 165, 210, 30);
		add(usrname);
		
		passwrd = new JLabel("Password");
		passwrd.setBounds(80, 200, 140, 40);
		add(passwrd);
		
		password = new JPasswordField();
		password.setBounds(150, 205, 210, 30);
		add(password);
		
		confirmpass = new JLabel("Confirm Password");
		confirmpass.setBounds(40, 240, 140, 40);
		add(confirmpass);
		
		conpassword = new JPasswordField();
		conpassword.setBounds(150, 245, 210, 30);
		add(conpassword);
		
		mail = new JLabel("Email");
		mail.setBounds(100, 280, 140, 40);
		add(mail);
		
		email = new JTextField();
		email.setBounds(150, 285, 210, 30);
		add(email);
		
		ph = new JLabel("Phone");
		ph.setBounds(100, 320, 140, 40);
		add(ph);
		
		phone = new JTextField();
		phone.setBounds(150, 325, 210, 30);
		add(phone);
		
		st = new JLabel("State");
		st.setBounds(100, 360, 140, 40);
		add(st);
		
		state = new JTextField();
		state.setBounds(150, 365, 210, 30);
		add(state);
		
		createButton = new JButton("Create Account");
		createButton.setBounds(180, 420, 140, 40);
		add(createButton);
		createButton.addActionListener(this);
		
		setSize(500,550);
		setResizable(false);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setLayout(null);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
		String first = firstn.getText();
		String last = lastn.getText();
		String user = String.valueOf(usrname.getText());
		String pass = String.valueOf(password.getPassword());
		String conpass = String.valueOf(conpassword.getPassword());
		String em = String.valueOf(email.getText());
		String phonenum = String.valueOf(phone.getText());
		String sta = state.getText();
		
		if(pass.equals(conpass)) {
			try {
				//Class.forName("oracle.jdbc.driver.OracleDriver"); 
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/food","root","root" +
						"root");
				PreparedStatement pstm = (PreparedStatement) conn.prepareStatement("INSERT INTO Details (username, passwd, email, firstname, lastname, phone, state) VALUES (?,?,?,?,?,?,?)");
				
				pstm.setString(1, user);
				pstm.setString(2, pass);
				pstm.setString(3, em);
				pstm.setString(4, first);
				pstm.setString(5, last);
				pstm.setString(6, phonenum);
				pstm.setString(7, sta);
				
				int i = pstm.executeUpdate();
				//System.out.println(i+"records inserted");
				
				//int rowAffected = pstm.executeUpdate();
					
				if(i == 1) {
					dispose();
					JOptionPane.showMessageDialog(null, "Account Created!");
				}
				else {
					//this.dispose();
					JOptionPane.showMessageDialog(null, "Check the details you entered", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(Exception except) {
				System.out.println("Error "+except);
			}
		}
		else {
			//this.dispose();
			JOptionPane.showMessageDialog(null, "Passwords don't match", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void main(String[]args) {
		new CreateAccount();
	}
}
