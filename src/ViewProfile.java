import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewProfile extends JFrame{
	private JLabel accountbg;
	private JLabel title;
	private JLabel firstname;
	private JLabel lastname;
	private JLabel email;
	private JLabel phone;
	private JLabel state;

	private JLabel firstn;
	private JLabel lastn;
	private JLabel em;
	private JLabel ph;
	private JLabel st;

	private JButton back;
	private JButton ohistory;
	private JButton phistory;
	private JButton addcard;

	private JButton review;

	static Connection con = null;
	static Statement stmt = null;
	static PreparedStatement pst = null;
	static ResultSet rs = null;

	String first, last, ema, pho, sta;

	public ViewProfile() {
		super("My Account");

		dbConnect();
		initComponents();

	}

	public void initComponents(){
		title = new JLabel("My Account");
		firstname = new JLabel("First Name: -");
		lastname = new JLabel("Last Name: -");
		email = new JLabel("Email: -");
		phone = new JLabel("Phone: -");
		state = new JLabel("State: -");
		back = new JButton("Back");
		ohistory = new JButton("Order History");
		phistory = new JButton("Payment History");
		addcard = new JButton("Add Card");
		review = new JButton("Review"); // Add the Review button

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setMinimumSize(new java.awt.Dimension(600, 820));
		setResizable(false);
		getContentPane().setLayout(null);

		title.setFont(new java.awt.Font("Arial", 10, 30));
		getContentPane().add(title);
		title.setBounds(220, 50, 200, 40);

		firstname.setFont(new java.awt.Font("Times New Roman", 0, 20));
		getContentPane().add(firstname);
		firstname.setBounds(120,120,140,40);

		lastname.setFont(new java.awt.Font("Times New Roman", 0, 20));
		getContentPane().add(lastname);
		lastname.setBounds(120,180,140,40);

		email.setFont(new java.awt.Font("Times New Roman", 0, 20));
		getContentPane().add(email);
		email.setBounds(120,240,140,40);

		phone.setFont(new java.awt.Font("Times New Roman", 0, 20));
		getContentPane().add(phone);
		phone.setBounds(120,300,140,40);

		state.setFont(new java.awt.Font("Times New Roman", 0, 20));
		getContentPane().add(state);
		state.setBounds(120,360,140,40);

		// Review Button Action
		review.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Review oh = new Review();
				oh.setVisible(true);
			};
		});
		getContentPane().add(review);
		review.setBounds(200, 650, 120, 40);  // Position the Review button

		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(back);
		back.setBounds(60, 550, 120, 40);

		ohistory.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				OrderHistory oh = new OrderHistory();
				oh.setVisible(true);
			}
		});
		getContentPane().add(ohistory);
		ohistory.setBounds(200, 550, 120, 40);

		phistory.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				PaymentHistory ph = new PaymentHistory();
				ph.setVisible(true);
			}
		});
		getContentPane().add(phistory);
		phistory.setBounds(340, 550, 140, 40);

		addcard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCard ac = new AddCard();
				ac.setVisible(true);
			}
		});
		getContentPane().add(addcard);
		addcard.setBounds(60, 650, 120, 40);


		try{
			Login log = new Login();
			PreparedStatement pst = (PreparedStatement) con.prepareStatement("SELECT email, firstname, lastname, phone, state FROM food.details WHERE username = ?");
			pst.setString(1, log.user);
			ResultSet rs = pst.executeQuery();

			while(rs.next()){
				ema = rs.getString("email");
				first = rs.getString("firstname");
				last = rs.getString("lastname");
				pho = rs.getString("phone");
				sta = rs.getString("state");
			}

			firstn = new JLabel(first);
			firstn.setFont(new java.awt.Font("Times New Roman", 0, 20));
			getContentPane().add(firstn);
			firstn.setBounds(260,120,140,40);

			lastn = new JLabel(last);
			lastn.setFont(new java.awt.Font("Times New Roman", 0, 20));
			getContentPane().add(lastn);
			lastn.setBounds(260,180,140,40);

			em = new JLabel(ema);
			em.setFont(new java.awt.Font("Times New Roman", 0, 20));
			getContentPane().add(em);
			em.setBounds(260,240,240,40);

			ph = new JLabel(pho);
			ph.setFont(new java.awt.Font("Times New Roman", 0, 20));
			getContentPane().add(ph);
			ph.setBounds(260,300,140,40);

			st = new JLabel(sta);
			st.setFont(new java.awt.Font("Times New Roman", 0, 20));
			getContentPane().add(st);
			st.setBounds(260,360,140,40);

		} catch(Exception except) {
			System.out.println("Error "+except);
		}

		accountbg = new JLabel(new ImageIcon("src//imgs//accountbg.jpg"));
		accountbg.setText("wallpaper");
		accountbg.setMaximumSize(new java.awt.Dimension(600, 820));
		accountbg.setMinimumSize(new java.awt.Dimension(600, 820));
		getContentPane().add(accountbg);
		accountbg.setBounds(0, 0, 600, 820);

		pack();
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
		new ViewProfile().setVisible(true);

	}

}
