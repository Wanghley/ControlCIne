package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {

	private Connection conexao;
	private String host, porta, username, database, password, dbUrl;


	public Conexao() {

		try {

			//carrega o driver do banco
			Class.forName("org.postgresql.Driver");

			//URI dbUri = new URI(System.getenv("tzbduyetmgavwr:9b2580938dffaa62a01658a0b5996e90de17d2aa9895a5ea0e649b7956f11d73@ec2-174-129-253-101.compute-1.amazonaws.com:5432/d82gnra6pmjq6f\n"));
			this.host = "localhost";
			this.porta = "5432";
			this.username = "postgres";
			this.password = "admin";
			this.database = "CONTROLCINE";
			this.dbUrl = "jdbc:postgresql://" + this.host + ':' + this.porta +'/'+ this.database /*+ "?sslmode=require"*/;


			//tenta realizar a conexao
			conexao = (Connection) DriverManager.getConnection(dbUrl, username, password);
			//System.out.println("conexão realizada com sucesso");

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); //mostra a exceção que for capturada por problema de sql
		}


	}
	
	public Connection getConexao() {
		return conexao;
	}
	
	



}
