package com.leo.questao1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class Execute {
	
	/**
	 * Imprime a lista dos fornecedores que não possuem produtos
	 * @param conn
	 * @throws SQLException
	 */
	private static void fornecedorSemProduto(Connection conn) throws SQLException{
		String sql = "SELECT fornecedor.id, fornecedor.nome" +
				" FROM fornecedor"+
				" WHERE fornecedor.id not in (select fornecedor_id from produto)";
		Statement s = conn.createStatement();
		ResultSet result = s.executeQuery(sql);
		while(result.next()){
			String nome = result.getString(2);
			String output = "Fornecedor: %s";

			System.out.println(String.format(output, nome));
		}
		System.out.println("\n\n");
	}

	/**
	 * Imprime a lista de produtos
	 * @param conn
	 * @throws SQLException
	 */
	private static void listaProdutos(Connection conn) throws SQLException{
		String sql = "SELECT * FROM produto";
		Statement s = conn.createStatement();
		ResultSet result = s.executeQuery(sql);
		while(result.next()){
			String nome = result.getString(2);
			String marca = result.getString(3);
			Date data_cadastro = result.getDate(4);
			double valor_unitario = result.getDouble(5);
			int quantidade = result.getInt(6);
			String output = "Produto: %s Marca: %s Data de Cadastro: %s Valor Unitario: %f Quantidade: %d";

			System.out.println(String.format(output, nome, marca, data_cadastro, valor_unitario, quantidade));
		}
		System.out.println("\n");

	}

	/**
	 * Imprime o somatório do valor total de Produtos por fornecedor.
	 * @param conn
	 * @throws SQLException
	 */
	private static void somatorio(Connection conn) throws SQLException{
		String sql = "SELECT fornecedor.nome, fornecedor.cnpj, sum(valor_unitario*quantidade)"+
				" FROM produto, fornecedor"+
				" WHERE produto.fornecedor_id = fornecedor.id"+
				" GROUP BY fornecedor.id;";
		Statement s = conn.createStatement();
		ResultSet result = s.executeQuery(sql);
		while(result.next()){
			String nome = result.getString(1);
			int cnpj = result.getInt(2);
			double valor = result.getDouble(3);
			String output = "Fornecedor: %s Cnpj: %d Valor: %f";

			System.out.println(String.format(output, nome, cnpj, valor));
		}
		System.out.println("\n");

	}

	/**
	 * O produto mais caro.
	 * @param conn
	 * @throws SQLException
	 */
	private static void maiorValor(Connection conn) throws SQLException{
		String sql = "SELECT nome, valor_unitario" +
				" FROM produto" +
				" WHERE valor_unitario = (SELECT max(valor_unitario) FROM produto)";
		Statement s = conn.createStatement();
		ResultSet result = s.executeQuery(sql);
		while(result.next()){
			String nome = result.getString(1);
			double valor = result.getDouble(2);
			String output = "Produto: %s Valor: %f";

			System.out.println(String.format(output, nome, valor));
		}
		System.out.println("\n");

	}
	
	/**
	 * Insere um novo fornecedor no banco de dados
	 * @param conn
	 * @throws SQLException
	 */
	private static void inserirFornecedor(Connection conn) throws SQLException{

		Scanner sc = new Scanner(System.in);
		System.out.println("INSERIR FORNECEDOR");
		System.out.println("Digite o nome do fornecedor: ");
		String nome = sc.nextLine();
		System.out.println("Digite o endereco do fornecedor: ");
		String endereco = sc.nextLine();
		System.out.println("Digite o cnpj do fornecedor: ");
		int cnpj = sc.nextInt();


		String fornecedorSql = "INSERT INTO Fornecedor (nome, cnpj, endereco) VALUES (?, ?, ?)";

		PreparedStatement ps = conn.prepareStatement(fornecedorSql);
		ps.setString(1, nome);
		ps.setInt(2, cnpj);
		ps.setString(3, endereco);

		int rowsInserted = ps.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Novo fornecedor inserido");
		}

		sc.close();
	}


	/**
	 * Insere um novo produto no banco de dados
	 * @param conn
	 * @throws SQLException
	 */
	private static void inserirProduto(Connection conn) throws SQLException{

		Scanner sc = new Scanner(System.in);
		System.out.println("INSERIR PRODUTO");
		System.out.println("Digite o nome do produto: ");
		String nome = sc.nextLine();
		System.out.println("Digite a marca do produto: ");
		String marca = sc.nextLine();
		System.out.println("Digite o valor unitário: ");
		double valor = sc.nextDouble();
		System.out.println("Digite a quantidade: ");
		int quantidade = sc.nextInt();
		System.out.println("Digite a identificacao do fornecedor: ");
		int id = sc.nextInt();

		java.util.Date today = new java.util.Date();


		String produtoSql = "INSERT INTO Produto (nome, marca, data_cadastro, valor_unitario, quantidade, fornecedor_id) VALUES (?, ?, ?, ?, ?, ?)";

		PreparedStatement ps = conn.prepareStatement(produtoSql);
		ps.setString(1, nome);
		ps.setString(2, marca);
		ps.setDate(3, new java.sql.Date(today.getTime()));
		ps.setDouble(4, valor);
		ps.setInt(5, quantidade);
		ps.setInt(6, id);

		int rowsInserted = ps.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Novo produto inserido");
		}

		sc.close();
	}

	
	private static void menu(Connection conn) throws SQLException{
		Scanner sc = new Scanner(System.in);
		System.out.print("Escolha uma opção:\n[1] Inserir fornecedor\n[2] Inserir produto\n[3] Listar consultas sql\n>> "); 
		int i = sc.nextInt();
		switch(i){
			case 1:
				inserirFornecedor(conn);
				break;
			case 2:
				inserirProduto(conn);
				break;
			case 3:
				listaProdutos(conn);
				somatorio(conn);
				maiorValor(conn);
				fornecedorSemProduto(conn);
				break;
			default:
				System.out.println("Opcao invalida");
		}
	}
	public static void main(String[] args) {

		ConnectionManager cm;
		Connection conn;
		try {
			cm = new ConnectionManager();
			conn = cm.initiateConnection();
			if(conn != null){
				menu(conn);
				conn.close();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
