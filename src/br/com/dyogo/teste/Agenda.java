package br.com.dyogo.teste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Agenda {

	private Connection connection;

	public Agenda() {
		this.connection = ConnectionFactory.getConection();
	}

	public void adiconar(Pessoas cadastro) {
		String sql = "insert into agenda " + "(nome,telefone)" + " values(?,?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			//stmt.setInt(1, cadastro.getId());
			stmt.setString(1, cadastro.getNome());
			stmt.setString(2, cadastro.getTelefone());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public void alterar(Pessoas conecit) {
		String sql = "update agenda set nome=?, telefone=? " + "where id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(3, conecit.getId());
			stmt.setString(1, conecit.getNome());
			stmt.setString(2, conecit.getTelefone());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Pessoas contat) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete " + "from agenda where id=?");
			stmt.setInt(1, contat.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public  List<Pessoas> getList() {

		try {
			List<Pessoas> contatos = new ArrayList<Pessoas>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from agenda order by id");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Pessoas contato = new Pessoas();
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setTelefone(rs.getString("telefone"));

				contatos.add(contato);
			}
			rs.close();
			stmt.close();
			return contatos;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
