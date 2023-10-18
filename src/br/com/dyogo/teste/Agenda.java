package br.com.dyogo.teste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dyogo.conection.ConnectionFactory;

public class Agenda {

	private Connection connection;

	public Agenda() {
		this.connection = ConnectionFactory.getConection();
	}

	public void adiconar(Pessoa cadastro) {
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

	public void alterar(Pessoa conecit) {
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

	public void remove(Pessoa contat) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete " + "from agenda where id=?");
			stmt.setInt(1, contat.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public  List<Pessoa> getList() {

		try {
			List<Pessoa> contatos = new ArrayList<Pessoa>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from agenda order by id");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Pessoa contato = new Pessoa();
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
	
	public int getUltId() {

		try {
			
			PreparedStatement stmt = this.connection.prepareStatement("select max(id) cod from agenda");
			
			ResultSet rs = stmt.executeQuery();

			int id = 0; 
			while (rs.next()) {		
				id = rs.getInt("cod");
			}
			rs.close();
			stmt.close();
			return id;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
