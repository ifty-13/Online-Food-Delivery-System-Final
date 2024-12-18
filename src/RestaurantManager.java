import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class RestaurantManager extends JFrame {
	private JButton italiantable;
	private JButton frenchtable;
	private JButton americantable;
	private JButton indiantable;
	private JButton updatebutton;
	private JButton deletebutton;
	private JButton insertbutton;
	private JButton orders;
	private JButton payments;
	private JTable showtable;
	private JScrollPane jScrollPane1;
	private JLabel title;
	private JLabel updat;
	private JLabel insertitems;
	private JLabel dishn;
	private JLabel pric;
	private JLabel tabletoinsert;
	private JLabel deleteitems;
	private JLabel bg;
	private JTextField dish;
	private JTextField pr;

	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static PreparedStatement st = null;
	static PreparedStatement pst = null;

	static int id[] = new int[20];
	static float price[] = new float[20];
	static String dishname[] = new String[20];

	private String tables[];
	private JComboBox tablename;

	private String tables2[];
	private JComboBox tablename2;

	private String tables3[];
	private JComboBox tablename3;

	int rtc;

	public RestaurantManager() {
		super("Admin Dashboard");

		initComponents();
		// Center the frame
		setLocationRelativeTo(null); // This will center the JFrame on the screen
	}
	
	
	
	public static void connection() {
		try {

            Class.forName("java.sql.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food","root","root");
            stmt = con.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Error!", JOptionPane.ERROR_MESSAGE);

        }
	}
	
	public void italianTableClick(ActionEvent e) {
		try {
            JTableHeader header = showtable.getTableHeader();
            header.setEnabled(false);
            DefaultTableModel model = (DefaultTableModel) showtable.getModel();
            connection();
            String q = "SELECT * FROM food.italian;";
            rs = stmt.executeQuery(q);
            int s = model.getRowCount();
            while (s != 0) {
                model.removeRow(0);
                s--;
            }
            while (rs.next()) {
            	
            	int id = rs.getInt("ITA_id");
                String name = rs.getString("ITA_name");
                float price = rs.getFloat("ITA_price");
                Object row[] = {id, name, price};
                model.addRow(row);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ItalianMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public void frenchTableClick(ActionEvent e) {
		try {
            JTableHeader header = showtable.getTableHeader();
            header.setEnabled(false);
            DefaultTableModel model = (DefaultTableModel) showtable.getModel();
            connection();
            String q = "SELECT * FROM food.french;";
            rs = stmt.executeQuery(q);
            int s = model.getRowCount();
            while (s != 0) {
                model.removeRow(0);
                s--;
            }
            while (rs.next()) {
            	
            	int id = rs.getInt("FR_id");
                String name = rs.getString("FR_name");
                float price = rs.getFloat("FR_price");
                Object row[] = {id, name, price};
                model.addRow(row);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ItalianMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public void indianTableClick(ActionEvent e) {
		try {
            JTableHeader header = showtable.getTableHeader();
            header.setEnabled(false);
            DefaultTableModel model = (DefaultTableModel) showtable.getModel();
            connection();
            String q = "SELECT * FROM food.indian;";
            rs = stmt.executeQuery(q);
            int s = model.getRowCount();
            while (s != 0) {
                model.removeRow(0);
                s--;
            }
            while (rs.next()) {
            	
            	int id = rs.getInt("IND_id");
                String name = rs.getString("IND_name");
                float price = rs.getFloat("IND_price");
                Object row[] = {id, name, price};
                model.addRow(row);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ItalianMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public void americanTableClick(ActionEvent e) {
		try {
            JTableHeader header = showtable.getTableHeader();
            header.setEnabled(false);
            DefaultTableModel model = (DefaultTableModel) showtable.getModel();
            connection();
            String q = "SELECT * FROM food.american;";
            rs = stmt.executeQuery(q);
            int s = model.getRowCount();
            while (s != 0) {
                model.removeRow(0);
                s--;
            }
            while (rs.next()) {
            	
            	int id = rs.getInt("AR_id");
                String name = rs.getString("AR_name");
                float price = rs.getFloat("AR_price");
                Object row[] = {id, name, price};
                model.addRow(row);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ItalianMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public void updateButtonClick(ActionEvent e) {
		connection();
		
		int i = 0;
        DefaultTableModel model = (DefaultTableModel) showtable.getModel();
        int rc = model.getRowCount();
        rtc = 0;
        int row = showtable.getSelectedRow();
        String did = model.getValueAt(row, 0).toString();
		String dname = model.getValueAt(row, 1).toString();
		String dprice = model.getValueAt(row, 2).toString();
		String tablen = tablename.getItemAt(tablename.getSelectedIndex()).toString();
		
		try {
			float c = Float.parseFloat(dprice);
			int d = Integer.parseInt(did);
			if(tablen == "Italian") {
				pst = (PreparedStatement) con.prepareStatement("UPDATE food.italian SET ITA_name = ? WHERE ITA_id = ?");
				st = (PreparedStatement) con.prepareStatement("UPDATE food.italian SET ITA_price = ? WHERE ITA_id = ?");
			}
			if(tablen == "French") {
				pst = (PreparedStatement) con.prepareStatement("UPDATE food.french SET FR_name = ? WHERE FR_id = ?");
				st = (PreparedStatement) con.prepareStatement("UPDATE food.french SET FR_price = ? WHERE FR_id = ?");
			}
			if(tablen == "American") {
				pst = (PreparedStatement) con.prepareStatement("UPDATE food.american SET AR_name = ? WHERE AR_id = ?");
				st = (PreparedStatement) con.prepareStatement("UPDATE food.american SET AR_price = ? WHERE AR_id = ?");
			}
			if(tablen == "Indian") {
				pst = (PreparedStatement) con.prepareStatement("UPDATE food.indian SET IND_name = ? WHERE IND_id = ?");
				st = (PreparedStatement) con.prepareStatement("UPDATE food.indian SET IND_price = ? WHERE IND_id = ?");
			}
				
			pst.setString(1, dname);
			pst.setInt(2, d);
				
			st.setFloat(1, c);
			st.setInt(2, d);
				
			int rowsAffected = pst.executeUpdate();
			int rowsAffected2 = st.executeUpdate();
				
			if(rowsAffected == 1 && rowsAffected2 == 1) {
				JOptionPane.showMessageDialog(null, "Update Successful!");
			}
			
		}
		catch(Exception except) {
			JOptionPane.showMessageDialog(null,  except.getMessage());
		}
	}
	
	public void deleteButtonClick(ActionEvent e) {
		connection();
		
		int i = 0;
        DefaultTableModel model = (DefaultTableModel) showtable.getModel();
        int rc = model.getRowCount();
        rtc = 0;
        int row = showtable.getSelectedRow();
		String eve = model.getValueAt(row, 0).toString();
		String tablena = tablename3.getItemAt(tablename3.getSelectedIndex()).toString();
		
		try {
			int d = Integer.parseInt(eve);
			if(tablena == "Italian") {
				st = (PreparedStatement) con.prepareStatement("DELETE FROM food.italian WHERE ITA_id = ?");
			}
			if(tablena == "French") {
				st = (PreparedStatement) con.prepareStatement("DELETE FROM food.french WHERE FR_id = ?");
			}
			if(tablena == "American") {
				st = (PreparedStatement) con.prepareStatement("DELETE FROM food.american WHERE AR_id = ?");
			}
			if(tablena == "Indian") {
				st = (PreparedStatement) con.prepareStatement("DELETE FROM food.indian WHERE IND_id = ?");
			}
			st.setInt(1,d);
			int rowsAffected = st.executeUpdate();
			model.removeRow(row);
		}
		catch(Exception except) {
			JOptionPane.showMessageDialog(null,  except.getMessage());
		}
	}
	
	public void insertButtonClick(ActionEvent e) {
		String dishname = dish.getText();
		float cost = Float.parseFloat(String.valueOf(pr.getText().toString()));
		String tablena = tablename2.getItemAt(tablename2.getSelectedIndex()).toString();
		
		try {
			connection();
			if(tablena == "Italian") {
				st = (PreparedStatement) con.prepareStatement("INSERT INTO food.italian (ITA_name, ITA_price) VALUES (?,?)");
			}
			if(tablena == "French") {
				st = (PreparedStatement) con.prepareStatement("INSERT INTO food.french (FR_name, FR_price) VALUES (?,?)");
			}
			if(tablena == "American") {
				st = (PreparedStatement) con.prepareStatement("INSERT INTO food.american (AR_name, AR_price) VALUES (?,?)");
			}
			if(tablena == "Indian") {
				st = (PreparedStatement) con.prepareStatement("INSERT INTO food.indian (IND_name, IND_price) VALUES (?,?)");
			}
			st.setString(1, dishname);
			st.setFloat(2, cost);
				
			int i = st.executeUpdate();
				
			if(i == 1) {
				JOptionPane.showMessageDialog(null, "Item Added!");
			}
		}
		catch(Exception except) {
			System.out.println("Error "+except);
		}
	}
	
	public static void main(String[]args) {
		new RestaurantManager().setVisible(true);
	}

}
