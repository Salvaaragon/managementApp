package es.uca.gii.csi16.barbanegra.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Faccion extends Entidad {

	private String _sNombre;
	
	public String getNombre() { return _sNombre; }
	public void setNombre(String sNombre) {	_sNombre = sNombre; }
	
	/**
	 * @param iId Clave primaria del registro de la tabla Residente
	 * @throws Exception Lanza excepción si no hay ningún registro cuyo Id sea iId
	 */
	public Faccion(int iId) throws Exception {
		
		super(iId, "Faccion");
		Connection con = null;
		try {
			con = Data.Connection();
			Initialize(iId, con);
		}
		catch(Exception e) { throw e; }
		finally {
			if(con != null) con.close();
		}
	}
	
	/**
	 * @param iId Clave primaria del registro de la tabla Residente
	 * @param con
	 * @throws Exception Lanza excepción si no hay ningún registro cuyo Id sea iId
	 */
	public Faccion(int iId, Connection con) throws Exception {
		
		super(iId, "Faccion");
		try {
			con = Data.Connection();
			Initialize(iId, con);
		}
		catch(Exception e) { throw e; }
		finally {
			if(con != null) con.close();
		}
	}
	
	/**
	 * @throws Exception Lanza excepción si la Facción no existe en la base de datos
	 */
	public void Update() throws Exception {
		
		super.Update("UPDATE Faccion SET Nombre ='" + _sNombre +
				"' WHERE Id = " + String.valueOf(getId()));			
	}
	
	/**
	 * Devuelve un ArrayList con todas las facciones de la tabla
	 * @return ArrayList con todas las facciones de la tabla
	 * @throws Exception
	 */
	public static ArrayList<Faccion> Select() throws Exception {
		
		Connection con = null;
		ResultSet rs = null;
		
		try {
			
			con = Data.Connection();
			ArrayList<Faccion> faccion = new ArrayList<Faccion>();
			
			rs = con.createStatement().executeQuery(
					"SELECT Id FROM Faccion ORDER BY Nombre");
			
			while(rs.next())
				faccion.add(new Faccion(rs.getInt("Id")));
						
			return faccion;
		}
		catch(Exception e) { throw e; }
		finally {
			
			if(rs != null) rs.close();
			if(con != null) con.close();
		}
	}
	
	public void Initialize(int iId, Connection con) throws Exception {
		
		ResultSet rs = null;
		
		try{
			
			con = Data.Connection();
			rs = con.createStatement().executeQuery(
					"SELECT Nombre FROM Faccion where Id = "
					+ String.valueOf(iId));
			
			rs.next();
			
			if(rs.getRow() == 0) 
				throw new Exception("El id " +String.valueOf(iId) + 
						" no se encuentra en la tabla");
			
			_sNombre = rs.getString("Nombre");
		}
		catch (Exception e) { throw e; }
		finally {
			
			if (rs != null) rs.close();
        }
	}
	
	/**
	 * Metodo toString de Faccion
	 */
	public String toString() {
		return _sNombre;
	}
}