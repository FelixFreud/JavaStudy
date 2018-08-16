package section12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemsDAO {
	public static ArrayList<Item> findByMinimumPrice(int minPrice){
		ArrayList<Item> results = new ArrayList<>();
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		try(
				Connection con = DriverManager.getConnection
					("jdbc:h2:~/Dropbox/Java/スッキリわかるJava入門 実践編 第２版/rpgdb");
				PreparedStatement pstmt = con.prepareStatement
						("SELECT * FROM ITEMS WHERE PRICE >= ?");
		){
			pstmt.setInt(1, minPrice);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Item item = new Item();
				item.setName(rs.getString("NAME"));
				item.setPrice(rs.getInt("PRICE"));
				item.setWhight(rs.getInt("WEIGHT"));
				results.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}
}
