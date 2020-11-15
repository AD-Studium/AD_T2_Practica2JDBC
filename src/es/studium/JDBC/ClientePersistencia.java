/**
 * 
 */
package es.studium.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Alvca
 *
 */
public class ClientePersistencia {
	static String driver = "com.mysql.jdbc.Driver";
	static String url ="jdbc:mysql://localhost:3306/hotel?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&useSSL=false";
	static String login = "root";
	static String password = "Studium2018;";
	
	public static int createCliente(String nombre, String apellidos, String email, String dni, String clave) {
		/* Devuelve el valor de la columna "campo" del cliente identificado por "idCliente" */
		int idCliente = 0;
		String crearCliente = "INSERT INTO clientes (nombre, apellidos, email, dni, clave) VALUES('" + nombre + "', '" + apellidos + "', '" + email + "', '" + dni + "', '" + clave + "');";
		String ultimoIdCliente = "SELECT LAST_INSERT_ID();";
		try {
			//Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			//Establecer la conexión con la BD hotel
			Connection connect = DriverManager.getConnection(url, login, password);
			//Crear una sentencia
			Statement stm = connect.createStatement();
			//Ejecutamos la sentencia
			stm.executeUpdate(crearCliente);
			//Guardamos el resultado de la sentencia en un ResultSet
			ResultSet rs = stm.executeQuery(ultimoIdCliente);
			//Recorremos el ResultSet
			rs.next();
			//Optenemos el idCliente del ResultSet
			idCliente = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idCliente;
	}
	public static String readCliente(int idCliente, String campo) {
		/* Devuelve el valor de la columna "campo" del cliente identificado por "idCliente" */
		String datoARecuperar = "SELECT "+ campo +" FROM clientes WHERE idCliente = '" + idCliente + "';";
		String datos = "";

		try {
			//Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			//Establecer la conexión con la BD hotel
			Connection connect = DriverManager.getConnection(url, login, password);
			//Crear una sentencia
			Statement stm = connect.createStatement();
			//Guardamos el resultado de la sentencia en un ResultSet
			ResultSet rs = stm.executeQuery(datoARecuperar);
			//Recorremos el ResultSet
			rs.next();
			//Optenemos los datos del ResultSet
			datos = rs.getString(1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Cliente erroneo, debe seleccionar un cliente valido");
		}
		return datos;
	}
	public static boolean updateCliente(int idCliente, String campo, String nuevoValor) {
		/* Actualiza el valor de la columna "campo" del cliente identificado por "idCliente". Devuelve true si se ha logrado actualizar. */
		String datoAModificar = "UPDATE clientes SET " + campo +"='"+nuevoValor+"' WHERE idCliente = '" + idCliente + "';";
		Boolean modificacionRealizada = false;

		try {
			//Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			//Establecer la conexión con la BD hotel
			Connection connect = DriverManager.getConnection(url, login, password);
			//Crear una sentencia
			Statement stm = connect.createStatement();
			//Ejecutamos la sentencia
			stm.executeUpdate(datoAModificar);
			//Si no da error cambiamos el valor del campo modificacionRealizada
			modificacionRealizada = true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modificacionRealizada;
	}
	public static boolean deleteCliente(int idCliente) {
		/* Elimina el cliente identificado por "idCliente". Devuelve true si se ha logrado eliminar. */
		String borrarCliente = "DELETE FROM clientes WHERE (idCliente ='"+idCliente+"');";
		Boolean borradooRealizado = false;
		try {
			//Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			//Establecer la conexión con la BD hotel
			Connection connect = DriverManager.getConnection(url, login, password);
			//Crear una sentencia
			Statement stm = connect.createStatement();
			//Ejecutamos la sentencia
			stm.executeUpdate(borrarCliente);
			//Si no da error cambiamos el valor del campo borradooRealizado
			borradooRealizado = true; 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return borradooRealizado;
	}
}
