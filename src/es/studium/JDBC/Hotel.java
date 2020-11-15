/**
 * 
 */
package es.studium.JDBC;

/**
 * @author Alvca
 *
 */
public class Hotel {
	public static void main(String[] args) {
		int id = ClientePersistencia.createCliente("María José", "Martínez", "mjmartinez@grupostudium.com", "12345678Z","Studium2020");
		System.out.println(ClientePersistencia.readCliente(id, "apellidos"));
 
		ClientePersistencia.updateCliente(id, "apellidos", "Martínez Navas");
		System.out.println(ClientePersistencia.readCliente(id, "apellidos"));
		
		System.out.println("Borrando: "+ClientePersistencia.readCliente(id, "apellidos"));
		ClientePersistencia.deleteCliente(id);
		
 
	}
}
