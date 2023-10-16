package br.com.dyogo.teste;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) throws SQLException{
		
		Scanner sc = new Scanner(System.in);
		
		Connection con = ConnectionFactory.getConection();
		Agenda agenda = new Agenda();
		Pessoa p = new Pessoa();
		
		System.out.print("digite um nome: ");
		String nome = sc.nextLine();
		
		System.out.print("digite um telefone: ");
		String telefone = sc.nextLine();
		
		p.setNome(nome);
		p.setTelefone(telefone);
		
		agenda.adiconar(p);
		
		System.out.println("feito");
		
		sc.close();
		con.close();
	}

}
