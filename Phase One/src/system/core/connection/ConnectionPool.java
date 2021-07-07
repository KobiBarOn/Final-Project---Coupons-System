package system.core.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import system.core.exceptions.CouponSystemException;

public class ConnectionPool {

	protected Set<Connection> connections = new HashSet<>();
	public static final int MAX = 5;

	private String url = "jdbc:mysql://localhost:3306/coupons_project_db" + "?serverTimezone=Israel" + "&user=root"
			+ "&password=Kobi0412!";

	private static ConnectionPool instance;

	public ConnectionPool() throws CouponSystemException {
		for (int i = 0; i < MAX; i++) {
			try {
				connections.add(DriverManager.getConnection(url));
			} catch (SQLException e) {
				throw new CouponSystemException("Error in 'ConnectionPool' CTOR");
			}
		}
	}

	public static ConnectionPool getInstance() {
		if (instance == null) {
			try {
				instance = new ConnectionPool();
			} catch (CouponSystemException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	/**
	 * Seeks for an available connection to the DataBase
	 * 
	 * @return an available pattern (connection) to connect with the DataBase
	 * @throws CouponSystemException
	 */
	public synchronized Connection getConnection() throws CouponSystemException {
		while (connections.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				throw new CouponSystemException(
						"Sorry, Non of the connections is availabe at this moment, please try again later", e);
			}
		}
		Iterator<Connection> conIt = connections.iterator();
		Connection connection = conIt.next();
		conIt.remove();
		return connection;
	}

	public synchronized void restoreConnection(Connection connection) {
		while (connections.isEmpty()) {
			notify();
			connections.add(connection);
		}
	}

	public synchronized void closeAllConnections() throws CouponSystemException {
		while(connections.size() != MAX) {
			try {
				System.out.println("Retrieving all connections...");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Iterator<Connection> conIt = connections.iterator();
		Connection connection = conIt.next();
		while (conIt.hasNext()) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new CouponSystemException("Error with closing connection: " + connection, e);
			}
		}
		System.out.println("All connections are closed");
	}

}
