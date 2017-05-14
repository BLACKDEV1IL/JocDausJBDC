package CapaPersistencia;

import java.sql.SQLException;

public class LoginBBDD {

	private static ConnectionBBDD connection;

	public static void login(String user, String password) throws Exception {
		if (connection == null) {
			connection = new ConnectionBBDD(user, password);
		}
	}

	static ConnectionBBDD getConnection() throws Exception, SQLException {
		if (connection == null)
			throw new Exception("No ha iniciat sessió");
		return connection;
	}

}
