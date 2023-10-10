package br.com.dyogo.teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/cadastro", "root", "123456");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
