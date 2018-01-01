package br.com.financeiro.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectamySQL {


	public static void main(String[] args) {
		
		Connection conexao = null;
		try {
		String url = "jdbc:mysql://localhost/financeiro";
		String usuario = "root";
		String senha = "aldenora";
		conexao = DriverManager.getConnection(url,usuario,senha);
		System.out.println("Coneectou");
		conexao.close();
		} catch (SQLException e) {
			System.out.println("ERRO Ao conectar "+e.getMessage());
		}
	}

}
