package visitante;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Visitante {
	
	public static final String DB_URL = "jdbc:mysql://localhost:3306/kamila?useTimezone=true&serverTimezone=UTC";
        
        //select * from departamento, visitante, interacao where departamento.iddepartamento = interacao.iddepartamento and visitante.idvisitante = interacao.idvisitante;

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		String query = JOptionPane.showInputDialog("Insira a query");
		
		try {
			connection= DriverManager.getConnection(DB_URL, "root", "aluno123");
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int number = resultSetMetaData.getColumnCount();
			
			System.out.println("Table query");
			
			for(int i = 1; i<=number; i++) {
				System.out.printf("%-20s\t", resultSetMetaData.getColumnName(i));
			}
			System.out.println();
			
			while(resultSet.next()) {
				for(int i = 1; i<=number; i++) {
					System.out.printf("%-20s\t", resultSet.getObject(i));
				}
				System.out.println();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				statement.close();
				resultSet.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
