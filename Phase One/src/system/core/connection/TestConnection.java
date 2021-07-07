package system.core.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/coupons_project_db";
		url += "?serverTimezone=Israel";
		url += "&user=root";
		url += "&password=Kobi0412!";

		try (Connection con = DriverManager.getConnection(url)) {
			System.out.println("connected to: " + url);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("\ndisconnected from: " + url);

	}
}
