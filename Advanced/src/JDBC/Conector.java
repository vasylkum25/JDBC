package JDBC;

import java.sql.*;
import java.util.Scanner;


public class Conector {

public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/jdbc_home?useSSL=false";
	
	public static final String USER ="root";
	
	public static final String PASSWORD = "5100117";
	
	public static final String CREATE_TABLE_RECEPT_QUERY = "CREATE TABLE recept (id INT PRIMARY KEY AUTO_INCREMENT,"
			+ " name VARCHAR(255), ingredient1 VARCHAR(255), mas1 INT, odunuci_vumir1 VARCHAR(15), ingredient2 VARCHAR(255), mas2 INT,"
			+ "odunuci_vumir2 VARCHAR(15), type_sous VARCHAR(255), mass_sous INT, vuhid INT)";
	
	public void starting(){
		Scanner sc = new Scanner(System.in);
	try(Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD)){
			boolean isTrue=true;
			System.out.println("1. Create database.");
			System.out.println("2. Insert table.");
			System.out.println("3. Print stravu.");
			System.out.println("4. Delet blydo.");
			System.out.println("5. Chang recept.");
			System.out.println("0. Exit");
			while(isTrue){
				switch (sc.next()) {
				case "1":
					createDatabase(connection, sc);
					break;
				case "2":
					insertTable(connection, sc);;
					break;
				case "3":
					printTable(connection, sc);
					break;
				case "4":
					deletRecept(connection, sc);
					break;
				case "5":
					updateDatabase(connection, sc);
					break;
				case "0":
					isTrue=false;
					break;
				default:
					break;
				
			}
		}
	}catch(SQLException e){
		e.printStackTrace();
	}
}
	public void createDatabase(Connection con, Scanner sc) throws SQLException{
		Statement statement = con.createStatement();
		statement.execute(CREATE_TABLE_RECEPT_QUERY);
		statement.close();
	}
	public void insertTable(Connection con, Scanner sc) throws SQLException{
		System.out.println("Enter name: ");
		String name = sc.next();
		System.out.println("Enter ingredient 1: ");
		String ingredient1 = sc.next();
		System.out.println("Enter mas 1: ");
		int mas1 = sc.nextInt();
		System.out.println("Enter odunuci_vumir 1: ");
		String odunuci_vumir1 = sc.next();
		System.out.println("Enter ingredient 2: ");
		String ingredient2 = sc.next();
		System.out.println("Enter mas 2: ");
		int mas2 = sc.nextInt();
		System.out.println("Enter odunuci_vumir 2: ");
		String odunuci_vumir2 = sc.next();
		System.out.println("Enter name_sous : ");
		String  type_sous = sc.next();
		System.out.println("Enter mas sous: ");
		int mass_sous = sc.nextInt();
		System.out.println("Enter mas stravu: ");
		int vuhid = sc.nextInt();
		String ADD_RECEPT="INSERT INTO recept (name,  ingredient1, mas1, odunuci_vumir1, ingredient2, mas2, odunuci_vumir2, type_sous,"
				+ " mass_sous, vuhid) VALUES(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement statement = con.prepareStatement(ADD_RECEPT);
		statement.setString(1, name);
		statement.setString(2, ingredient1);
		statement.setInt(3, mas1);
		statement.setString(4, odunuci_vumir1);
		statement.setString(5, ingredient2);
		statement.setInt(6, mas2);
		statement.setString(7, odunuci_vumir2);
		statement.setString(8, type_sous);
		statement.setInt(9, mass_sous);
		statement.setInt(10, vuhid);
		statement.executeUpdate();
		statement.close();
		
		
	}
	
	
	public void printTable(Connection con, Scanner sc) throws SQLException{
		PreparedStatement statement = con.prepareStatement( "SELECT name FROM recept");
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString("name")+" ");
		}
	}
	public void deletRecept(Connection con, Scanner sc) throws SQLException{
		System.out.println("Enter typ of soys from delet: ");
		String t = sc.next();
		PreparedStatement statement = con.prepareStatement("DELETE FROM recept WHERE type_sous=?");
		statement.setString(1, t);
		statement.executeUpdate();
		statement.close();
	}
	
	public void updateDatabase(Connection con, Scanner sc) throws SQLException{
		System.out.println("Enter name");
		String name = sc.next();
		System.out.println("Enter ingredient 1");
		String ing1 = sc.next();
		PreparedStatement statement = con.prepareStatement("UPDATE recept SET ingredient1=?  WHERE name=?");
		statement.setString(1, ing1);
		statement.setString(2, name);
		statement.executeUpdate();
		statement.close();
	}
}
	

