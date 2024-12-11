import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AddCard extends JFrame implements ActionListener{
	private JLabel title;
	private JLabel hold;
	private JLabel cardno;
	private JLabel cardt;
	private JLabel code;
	private JLabel exp;
	private JLabel cont;
	private JLabel bal;
	
	private JTextField holder;
	private JTextField cardnum;
	private JTextField expiry;
	private JTextField count;
	private JTextField balan;
	private JPasswordField seccode;
	
	private JButton addButton;
	
	private String cards[];
	private JComboBox cardtype;
	
	public AddCard() {
		super("Add Credit Card");
		
		title = new JLabel("Add Credit Card");
		hold = new JLabel("Name On Card");
		cardno = new JLabel("Card Number");
		cardt = new JLabel("Card Type");
		code = new JLabel("CVV");
		exp = new JLabel("Expiry Date");
		cont = new JLabel("Country");
		bal = new JLabel("Balance On Card");
		
		holder = new JTextField();
		cardnum = new JTextField();
		expiry = new JTextField();
		count = new JTextField();
		balan = new JTextField();
		seccode = new JPasswordField();
		
		addButton = new JButton("Add card");
		
		title.setFont(new Font("Arial", 10, 25));
		add(title);
		title.setBounds(150, 20, 180, 40);
		
		hold.setFont(new Font("Times New Roman", 0, 17));
		add(hold);
		hold.setBounds(70, 85, 210, 30);
		
		add(holder);
		holder.setBounds(180, 85, 160, 30);
		
		cardno.setFont(new Font("Times New Roman", 0, 17));
		add(cardno);
		cardno.setBounds(80, 125, 210, 30);
		
		add(cardnum);
		cardnum.setBounds(180, 125, 160, 30);
		
		cardt.setFont(new Font("Times New Roman", 0, 17));
		add(cardt);
		cardt.setBounds(95, 165, 210, 30);
		
		cards= new String[]{"Visa","American Express", "Mastercard", "Discover", "Diner's Club"};
		cardtype = new JComboBox(cards);
		add(cardtype);
        cardtype.setBounds(180, 165, 140, 30);
        
        code.setFont(new Font("Times New Roman", 0, 17));
		add(code);
		code.setBounds(120, 210, 210, 30);
		
		add(seccode);
		seccode.setBounds(180,210,100,30);
		
		exp.setFont(new Font("Times New Roman", 0, 17));
		add(exp);
		exp.setBounds(90, 250, 210, 30);
		
		add(expiry);
		expiry.setBounds(180, 250, 140, 30);
		
		cont.setFont(new Font("Times New Roman", 0, 17));
		add(cont);
		cont.setBounds(110, 290, 210, 30);
		
		add(count);
		count.setBounds(180, 290, 140, 30);
		
		bal.setFont(new Font("Times New Roman", 0, 17));
		add(bal);
		bal.setBounds(50, 330, 210, 30);
		
		add(balan);
		balan.setBounds(180, 330, 140, 30);
		
		add(addButton);
		addButton.setBounds(150, 400, 140, 40);
		addButton.addActionListener(this);
		
		setSize(500,550);
		setResizable(false);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setLayout(null);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		String holdername = holder.getText();
		String cardnumber = cardnum.getText();
		String cardty = cardtype.getItemAt(cardtype.getSelectedIndex()).toString();
		String cvvcode = String.valueOf(seccode.getPassword());
		String expdate = expiry.getText();
		String country = count.getText();
		float balnce = Float.parseFloat(String.valueOf(balan.getText()));
		
		//float b = Float.parseFloat(String.valueOf(balnce));
		
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver"); 
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/food","root","root");
			PreparedStatement pstm = (PreparedStatement) conn.prepareStatement("INSERT INTO food.card_details (holder, card_no, card_type, cvv, exp, country, balance) VALUES (?,?,?,?,?,?,?)");
			
			pstm.setString(1, holdername);
			pstm.setString(2, cardnumber);
			pstm.setString(3, cardty);
			pstm.setString(4, cvvcode);
			pstm.setString(5, expdate);
			pstm.setString(6, country);
			pstm.setFloat(7, balnce);
			
			int i = pstm.executeUpdate();
			System.out.println(i+"records inserted");
			
			//int rowAffected = pstm.executeUpdate();
				
			if(i == 1) {
				//this.dispose();
				JOptionPane.showMessageDialog(null, "Card Added!");
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

	public static void main(String[] args) {
		new AddCard();

	}

}

