import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Review extends JFrame{
	private JLabel title;
	private JButton ok;

	private JTable jTable1;
	private JScrollPane jScrollPane1;
	private DefaultTableModel model;

	static Connection con = null;
    static Statement stmt = null;
    static ResultSet rs = null;

    //int id[] = new int[20];
    //String user[] = new String[20];
    //int totalitems[] = new int[20];
    //float amt[] = new float[20];
    //String datetime[] = new String[20];

	public Review() {
		super("Review History");
	
		//Object row[] = {"Order ID", "Username", "Total Items", "Amount", "Order Status", "Time Stamp"};
        //model.addRow(row);
		
		try{
			initComponents();
			DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
			dbConnect();
			Login log = new Login();
			//System.out.println("From Login "+log.user);
			PreparedStatement pst = (PreparedStatement) con.prepareStatement("SELECT * FROM food.orders WHERE username = ?");
			pst.setString(1, log.user);
			ResultSet rs = pst.executeQuery(); 

			while(rs.next()){
				//System.out.println("Query executed");
				
				int id = rs.getInt("order_id");
				String user = rs.getString("username");
				int totalitems = rs.getInt("total_items");
				float amt = rs.getFloat("amount");
				String orderstat = rs.getString("order_status");
				String datetime = rs.getString("time_stamp");
				String review=rs.getString("review");
				if(review.equals("0")){
					review=" ";
				}
				
				Object row[] = {id, user, totalitems, amt, orderstat, datetime,review};
		        model.addRow(row);
			}
		}
		catch(Exception except) {
			System.out.println("Error "+except);
		}
		
	}

	public void initComponents() {
		jTable1 = new JTable();
		jScrollPane1 = new JScrollPane();
		title = new JLabel("Reviews");
		ok = new JButton("OK");

		model = new DefaultTableModel(new String[]{"Order ID", "Username", "Total Items", "Amount", "Order Status", "Time Stamp", "Review"}, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Allow only the "Review" column to be editable
				return column == 6;
			}
		};

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(700, 700));
		setResizable(false);
		getContentPane().setLayout(null);

		jTable1.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		jTable1.setRowHeight(40);
		jTable1.setModel(model);
		jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jTable1.setName("");
		jScrollPane1.setViewportView(jTable1);

		if (jTable1.getColumnModel().getColumnCount() > 0) {
			jTable1.getColumnModel().getColumn(0).setResizable(false);
			jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
			jTable1.getColumnModel().getColumn(1).setResizable(false);
			jTable1.getColumnModel().getColumn(1).setPreferredWidth(160);
			jTable1.getColumnModel().getColumn(2).setResizable(false);
			jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);
			jTable1.getColumnModel().getColumn(3).setResizable(false);
			jTable1.getColumnModel().getColumn(3).setPreferredWidth(80);
			jTable1.getColumnModel().getColumn(4).setResizable(false);
			jTable1.getColumnModel().getColumn(4).setPreferredWidth(140);
			jTable1.getColumnModel().getColumn(5).setResizable(false);
			jTable1.getColumnModel().getColumn(5).setPreferredWidth(180);
			jTable1.getColumnModel().getColumn(6).setResizable(false);
			jTable1.getColumnModel().getColumn(6).setPreferredWidth(100);
		}

		getContentPane().add(jScrollPane1);
		jScrollPane1.setBounds(100, 90, 450, 500);

		title.setFont(new Font("Times New Roman", 10, 30));
		getContentPane().add(title);
		title.setBounds(260, 30, 140, 40);

		getContentPane().add(ok);
		ok.setBounds(260, 600, 140, 30);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// Custom renderer for the "Review" column
		jTable1.getColumn("Review").setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				label.setText(value != null ? value.toString() : "No Review");
				return label;
			}
		});
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
		new Review().setVisible(true);
	}

}
