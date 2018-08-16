package section12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**Q12-2*/
public class Main {
	public static void main(String[] args) {
		quest12_2();
		System.out.println("１円以上のアイテム一覧表を表示します");
		ArrayList<Item> items = ItemsDAO.findByMinimumPrice(1);
		for(Item item:items) {
			System.out.printf("%10s%4d%4d",item.getName(),item.getPrice(),item.getWhight());
		}
	}
	/**データベースの準備*/
	static void quest12_2(){
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		try(
				Connection con = DriverManager.getConnection
					("jdbc:h2:~/Dropbox/Java/スッキリわかるJava入門 実践編 第２版/rpgdb");
				PreparedStatement pstmt = con.prepareStatement
					("CREATE TABLE IF NOT EXISTS ITEMS (NAME char primary key,PRICE int,WEIGHT int)");
		){
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		try(
				Connection con = DriverManager.getConnection
					("jdbc:h2:~/Dropbox/Java/スッキリわかるJava入門 実践編 第２版/rpgdb");
				PreparedStatement pstmt2 = con.prepareStatement
						("SELECT * FROM ITEMS WHERE NAME=?");
				PreparedStatement pstmt3 = con.prepareStatement
						("INSERT INTO ITEMS (NAME,PRICE,WEIGHT) VALUES (?,?,?)");
				PreparedStatement pstmt4 = con.prepareStatement
						("UPDATE ITEMS SET PRICE=?, WEIGHT=? WHERE NAME=?");
		){
			String[] names = {"やくそう","どくけしそう"};
			int[] prices = {5,7};
			int[] weights = {2,2};
			for(int i=0;i<names.length;i++) {
				pstmt2.setString(1, names[i]);
				ResultSet rs = pstmt2.executeQuery();
				if(!rs.next()) {
					pstmt3.setString(1, names[i]);
					pstmt3.setInt(2, prices[i]);
					pstmt3.setInt(3, weights[i]);
					pstmt3.executeUpdate();
				}else {
					pstmt4.setInt(1, prices[i]);
					pstmt4.setInt(2, weights[i]);
					pstmt4.setString(3, names[i]);
					pstmt4.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
