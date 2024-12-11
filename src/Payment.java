import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Payment extends JFrame{
	JFrame f1;
	private JLabel title;
	private JLabel bg;
	
	private JLabel name;
	private JLabel num;
	private JLabel exp;
	private JLabel code;
	private JLabel country;
	private JLabel type;
	private JLabel username;
	private JLabel password;
	
	private JTextField ownername;
	private JTextField cardnum;
	private JTextField expdate;
	private JTextField cardcountry;
	private JPasswordField cvv;
	private JTextField usern;
	private JPasswordField pass;
	
	private JButton pay;
	
	private String cards[];
	private JComboBox cardtype;
	
	static Connection con = null;
    static Statement stmt = null;
    static ResultSet rs = null;
	
	public Payment() {
		super("Payment Screen");
		
		initComponents();
		dbConnect();
		
		//pay.setText("Pay");
		
		setLayout(null);
		// Center the frame
		setLocationRelativeTo(null); // This will center the JFrame on the screen
		setVisible(true);
	}
	
	public void initComponents() {
		f1 = new JFrame();
		title = new JLabel("Payment");
		bg = new JLabel();
		
		name = new JLabel("Name On Card");
		num = new JLabel("Card Number");
		code = new JLabel("CVV");
		exp = new JLabel("Expiry Date");
		country = new JLabel("Country");
		type = new JLabel("Card Type");
		username = new JLabel("Username");
		password = new JLabel("Password");
		
		ownername = new JTextField();
		cardnum = new JTextField();
		cvv = new JPasswordField();
		expdate = new JTextField();
		cardcountry = new JTextField();
		usern = new JTextField();
		pass = new JPasswordField();
		
		cards= new String[]{"Visa","American Express", "Mastercard", "Discover", "Diner's Club"};
		cardtype = new JComboBox(cards);
		
		pay = new JButton();
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 667));
        setResizable(false);
        getContentPane().setLayout(null);
        
        title.setFont(new java.awt.Font("Candara", 0, 30));
        getContentPane().add(title);
        title.setBounds(430, 20, 140, 40);
		
        name.setFont(new java.awt.Font("Times New Roman", 0, 20));
        getContentPane().add(name);
        name.setBounds(270, 90, 140, 40);
        
        getContentPane().add(ownername);
        ownername.setBounds(430, 90, 200, 40);
        
        type.setFont(new java.awt.Font("Times New Roman", 0, 20));
        getContentPane().add(type);
        type.setBounds(270, 150, 140, 40);
        
        getContentPane().add(cardtype);
        cardtype.setBounds(430, 150, 140, 40);
        
        num.setFont(new java.awt.Font("Times New Roman", 0, 20));
        getContentPane().add(num);
        num.setBounds(270, 210, 140, 40);
        
        getContentPane().add(cardnum);
        cardnum.setBounds(430, 210, 200, 40);
        
        code.setFont(new java.awt.Font("Times New Roman", 10, 20));
        getContentPane().add(code);
        code.setBounds(270, 270, 140, 40);
        
        getContentPane().add(cvv);
        cvv.setBounds(430, 270, 50, 40);
        
        exp.setFont(new java.awt.Font("Times New Roman", 0, 20));
        getContentPane().add(exp);
        exp.setBounds(270, 330, 140, 40);
        
        getContentPane().add(expdate);
        expdate.setBounds(430, 330, 200, 40);
        
        country.setFont(new java.awt.Font("Times New Roman", 0, 20));
        getContentPane().add(country);
        country.setBounds(270, 390, 140, 40);
        
        getContentPane().add(cardcountry);
        cardcountry.setBounds(430, 390, 200, 40);
        

        
        pay.setText("Confirm Payment");
        pay.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		payButtonClick(e);
        	}
        });
        getContentPane().add(pay);
        pay.setBounds(430, 490, 140, 40);
        
		bg = new JLabel(new ImageIcon("src//imgs//pay.jpg"));
        bg.setText("Menu Background");
        bg.setMaximumSize(new java.awt.Dimension(1000, 667));
        bg.setMinimumSize(new java.awt.Dimension(1000, 667));
        //menubg.setPreferredSize(new java.awt.Dimension(599, 890));
        getContentPane().add(bg);
        bg.setBounds(0, 0, 1000, 667);
        
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
	
	public void updateOrderDetails() {
		Login log = new Login();
		System.out.println("From Payment "+log.user);
		try {
			PreparedStatement pst = (PreparedStatement) con.prepareStatement("UPDATE food.orders SET order_status = ? WHERE username = ?");
			pst.setString(1, "Delivered");
			pst.setString(2, log.user);
			
			int ra = pst.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("Error "+e);
		}
	}
	
	public void insertIntoPayment() {
		Login log = new Login();
		Cart cart = new Cart();
		String cardt = cardtype.getItemAt(cardtype.getSelectedIndex()).toString();
		try {
			PreparedStatement pst = (PreparedStatement) con.prepareStatement("INSERT INTO food.payment (username, card_type, amount_spent) VALUES (?, ?, ?)");
			pst.setString(1, log.user);
			pst.setString(2, cardt);
			pst.setFloat(3, cart.total);
			
			int raa = pst.executeUpdate();
		}
		catch(Exception e) {
			
		}
	}

	protected void payButtonClick(ActionEvent e) {
		Cart cart = new Cart();
		String holder = ownername.getText();
		String cardnumber = cardnum.getText().toString();
		String cardt = cardtype.getItemAt(cardtype.getSelectedIndex()).toString();
		String secnum = String.valueOf(cvv.getPassword());
		String exdate = expdate.getText().toString();
		String cont = cardcountry.getText();
		float holderbal = 0;
		
		//System.out.println(holder+" "+cardnumber+" "+cardt+" "+secnum+" "+exdate+" "+cont+" "+cart.total);
		
		try {
			PreparedStatement st = (PreparedStatement) con.prepareStatement("SELECT balance from food.card_details WHERE holder = ?");
			st.setString(1, holder);
			rs=st.executeQuery();
			while(rs.next()) {
				holderbal = rs.getFloat("balance");
			}
			if(holderbal >= cart.total) {
				PreparedStatement pst = (PreparedStatement) con.prepareStatement("UPDATE food.card_details SET balance = (balance - ?) WHERE holder = ? AND card_no = ? AND card_type = ? AND cvv = ? AND exp = ? AND country = ?");
				pst.setFloat(1, cart.total);
				pst.setString(2, holder);
				pst.setString(3, cardnumber);
				pst.setString(4, cardt);
				pst.setString(5, secnum);
				pst.setString(6, exdate);
				pst.setString(7, cont);
				
				int rowsAffected = pst.executeUpdate();
				
				if(rowsAffected == 1) {
					ThankYou ty = new ThankYou();
					updateOrderDetails();
					insertIntoPayment();
					JOptionPane.showMessageDialog(null, "Payment Successful!");
					ty.setVisible(true);
				}
				else { 
					//this.dispose();
					JOptionPane.showMessageDialog(null, "Please check all details carefully", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Insufficient Balance", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(Exception except) {
			System.out.println("Error "+except);
		}
	}

	public static void main(String[] args) {
		new Payment().setVisible(true);

	}

}
