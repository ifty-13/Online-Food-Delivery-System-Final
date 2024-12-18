import java.awt.*;
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

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class OrderHistory extends JFrame{
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

	public OrderHistory() {
		super("Order History");

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

				//System.out.println(ema + " "+ first+" "+last+" "+pho+" "+sta);

				Object row[] = {id, user, totalitems, amt, orderstat, datetime};
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
		jTable1 = new JTable();
		jScrollPane1 = new JScrollPane();
		title = new JLabel("My Orders");
		ok = new JButton("OK");

		model = new DefaultTableModel(new String[]{"Order ID", "Username", "Total Items", "Amount", "Order Status", "Time Stamp", "Review"}, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Allow only the "Review" column to be editable
				return column == 6;
			}
		};

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setMinimumSize(new java.awt.Dimension(800, 700));
		setResizable(false);
		getContentPane().setLayout(null);

		jTable1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
		jTable1.setRowHeight(40);
		jTable1.setModel(model);
		jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
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
			jTable1.getColumnModel().getColumn(6).setPreferredWidth(200);
		}

		getContentPane().add(jScrollPane1);
		jScrollPane1.setBounds(100, 90, 450, 500);

		title.setFont(new java.awt.Font("Times New Roman", 10, 30));
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

		// Set a custom renderer and editor for the "Review" column
		jTable1.getColumn("Review").setCellRenderer(new ButtonRenderer());
		jTable1.getColumn("Review").setCellEditor(new ButtonEditor(new JCheckBox()));
		//	jTable1.getColumnModel().getColumn(6).setPreferredWidth(100);

	}

	// Custom Button Renderer
	class ButtonRenderer extends JButton implements TableCellRenderer {
		public ButtonRenderer() {
			setOpaque(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			setText(value != null ? value.toString() : "Add");
			return this;
		}
	}

	// Custom Button Editor
	class ButtonEditor extends DefaultCellEditor {
		private JButton button;
		private String label;
		private boolean clicked;

		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
			button = new JButton();
			button.setOpaque(true);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					fireEditingStopped(); // Stop editing to trigger getCellEditorValue
				}
			});
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			label = (value != null) ? value.toString() : "Add";
			button.setText(label);
			clicked = true;
			return button;
		}

		@Override
		public Object getCellEditorValue() {
			if (clicked) {
				// Retrieve selected row values
				int selectedRow = jTable1.getSelectedRow();
				if (selectedRow != -1) { // Ensure a row is selected
					try {
						// Get values from the selected row
						String orderId = jTable1.getValueAt(selectedRow, 0).toString(); // Assuming Order ID is in column 0

						// Prompt user for review input
						String reviewValue = JOptionPane.showInputDialog(button, "Enter review (1 to 5) for Order ID " + orderId + ":");

						// If the user cancels or doesn't enter anything, return early
						if (reviewValue == null || reviewValue.trim().isEmpty()) {
							JOptionPane.showMessageDialog(button, "Review not updated. No input provided.", "Cancelled", JOptionPane.WARNING_MESSAGE);
							return label; // return without updating the review
						}

						// Update the review field in the database
						String updateQuery = "UPDATE orders SET review = ? WHERE order_id = ?";
						try (PreparedStatement pstmt = con.prepareStatement(updateQuery)) {
							pstmt.setString(1, reviewValue);  // User's input for review
							pstmt.setString(2, orderId);      // Order ID for which to update review
							int rowsUpdated = pstmt.executeUpdate();

							// Notify user of update success or failure
							if (rowsUpdated > 0) {
								JOptionPane.showMessageDialog(button, "Review updated successfully for Order ID: " + orderId, "Success", JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(button, "Failed to update review for Order ID: " + orderId, "Failure", JOptionPane.ERROR_MESSAGE);
							}
						}
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(button, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			clicked = false;
			return label;
		}


		@Override
		public boolean stopCellEditing() {
			clicked = false;
			return super.stopCellEditing();
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
		new OrderHistory().setVisible(true);
	}

}
