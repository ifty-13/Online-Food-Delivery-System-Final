

        /*
        try{
			Login log = new Login();
			//System.out.println("From Login "+log.user);
			PreparedStatement pst = (PreparedStatement) con.prepareStatement("SELECT * FROM food.orders WHERE username = ?");
			pst.setString(1, "rishijoshi25");
			ResultSet rs = pst.executeQuery();

			while(rs.next()){
				System.out.println("Query executed");

				int id = rs.getInt("order_id");
				String user = rs.getString("username");
				int totalitems = rs.getInt("total_items");
				float amt = rs.getFloat("amount");
				String orderstat = rs.getString("order_status");
				String datetime = rs.getString("timestamp");

				//System.out.println(ema + " "+ first+" "+last+" "+pho+" "+sta);

				Object row[] = {id, user, totalitems, amt, orderstat, datetime};
		        model.addRow(row);
			}
		}
		catch(Exception except) {
			System.out.println("Error "+except);
		}
		*/
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
		new AllPayments().setVisible(true);

	}

}
