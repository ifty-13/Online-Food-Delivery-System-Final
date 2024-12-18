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


    
	public RestaurantManager() {
		super("Admin Dashboard");
		
		initComponents();
		// Center the frame
		setLocationRelativeTo(null); // This will center the JFrame on the screen
	}
	
	public void initComponents() {
		italiantable = new JButton("Italian Menu");
		frenchtable = new JButton("French Menu");
		americantable = new JButton("American Menu");
		indiantable = new JButton("Indian Menu");
		updatebutton = new JButton("Update Selected Row");
		deletebutton = new JButton("Delete Selected Row");
		insertbutton = new JButton("Insert Item");
		orders = new JButton("View All Orders");
		payments = new JButton("View All Payments");
		jScrollPane1 = new JScrollPane();
		showtable = new JTable();
		title = new JLabel("Welcome Restaurant Manager");
		updat = new JLabel("Select Menu");
		deleteitems = new JLabel("Select Menu");
		insertitems = new JLabel("Insert Items");
		dishn = new JLabel("Dish Name");
		tabletoinsert = new JLabel("Select Menu");
		pric = new JLabel("Price");
		dish = new JTextField();
		pr = new JTextField();
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 800));
        setResizable(false);
        getContentPane().setLayout(null);
        
        updat.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(updat);
        updat.setBounds(70, 600, 90, 30);
        
        tables= new String[]{"Italian","French","American","Indian"};
		tablename = new JComboBox(tables);
		getContentPane().add(tablename);
        tablename.setBounds(160, 600, 140, 35);
        
        getContentPane().add(updatebutton);
        updatebutton.setBounds(310, 600, 160, 35);
        updatebutton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		updateButtonClick(e);
        	}
        });
        
        deleteitems.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(deleteitems);
        deleteitems.setBounds(70, 670, 90, 30);
        
        tables3= new String[]{"Italian","French","American","Indian"};
		tablename3 = new JComboBox(tables3);
		getContentPane().add(tablename3);
        tablename3.setBounds(160, 670, 140, 35);
        
        getContentPane().add(deletebutton);
        deletebutton.setBounds(310, 670, 160, 35);
        deletebutton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		deleteButtonClick(e);
        	}
        });
        
        title.setFont(new Font("Times New Roman", 0, 28));
        title.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(title);
        title.setBounds(300, 50, 350, 40);
        
        insertitems.setFont(new Font("Times New Roman", 0, 20));
        insertitems.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(insertitems);
        insertitems.setBounds(670, 110, 140, 30);
        
        dishn.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(dishn);
        dishn.setBounds(575, 160, 70, 30);
        
        getContentPane().add(dish);
        dish.setBounds(650, 160, 180, 30);
        
        tabletoinsert.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(tabletoinsert);
        tabletoinsert.setBounds(565, 210, 90, 30);
        
        tables2= new String[]{"Italian","French","American","Indian"};
		tablename2 = new JComboBox(tables2);
		getContentPane().add(tablename2);
		tablename2.setBounds(650, 210, 140, 30);
		
		pric.setForeground(new java.awt.Color(255, 255, 255));
		getContentPane().add(pric);
		pric.setBounds(600, 260, 70, 30);
		
		getContentPane().add(pr);
		pr.setBounds(650, 260, 70, 30);
		
		getContentPane().add(insertbutton);
		insertbutton.setBounds(660, 320, 150, 40);
		insertbutton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		insertButtonClick(e);
        	}
        });
		
		getContentPane().add(orders);
		orders.setBounds(520, 400, 150, 40);
		orders.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AllOrders ao = new AllOrders();
        		ao.setVisible(true);
        	}
        });
		
		getContentPane().add(payments);
		payments.setBounds(750, 400, 150, 40);
		payments.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AllPayments ap = new AllPayments();
        		ap.setVisible(true);
        	}
        });
        
        showtable.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        showtable.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 15)); // NOI18N
        showtable.setForeground(new java.awt.Color(1, 1, 1));
        showtable.setRowHeight(40);
        showtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Dish Name", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        showtable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        showtable.setName(""); // NOI18N
        jScrollPane1.setViewportView(showtable);
        
        if (showtable.getColumnModel().getColumnCount() > 0) {
            showtable.getColumnModel().getColumn(0).setResizable(false);
            showtable.getColumnModel().getColumn(0).setPreferredWidth(80);
            showtable.getColumnModel().getColumn(1).setResizable(false);
            showtable.getColumnModel().getColumn(1).setPreferredWidth(160);
            showtable.getColumnModel().getColumn(2).setResizable(false);
            showtable.getColumnModel().getColumn(2).setPreferredWidth(80);
        }
        
        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(70, 110, 400, 400);
        
        getContentPane().add(italiantable);
        italiantable.setBounds(70, 530, 110, 40);
        italiantable.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		italianTableClick(e);
        	}
        });
        
        getContentPane().add(frenchtable);
        frenchtable.setBounds(185, 530, 110, 40);
        frenchtable.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frenchTableClick(e);
        	}
        });
        
        getContentPane().add(indiantable);
        indiantable.setBounds(300, 530, 110, 40);
        indiantable.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		indianTableClick(e);
        	}
        });
        
        getContentPane().add(americantable);
        americantable.setBounds(415, 530, 130, 40);
        americantable.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		americanTableClick(e);
        	}
        });
        
        bg = new JLabel(new ImageIcon("src\\imgs\\kitchen.jpg"));
        bg.setText("kitchen");
        bg.setMaximumSize(new java.awt.Dimension(1000, 700));
        bg.setMinimumSize(new java.awt.Dimension(1000, 700));
        getContentPane().add(bg);
        bg.setBounds(0, 0, 1000, 700);
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
