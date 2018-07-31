package es.uca.gii.csi16.barbanegra.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

public class Residente extends Entidad {
	
	private String _sCodigo;
	private String _sNombre;
	private String _sApellido;
	private int _iEdad;
	private Faccion _faccion;
	
	// Métodos observadores
	public String getCodigo() { return _sCodigo; }
	public String getNombre() { return _sNombre; }
	public String getApellido() { return _sApellido; }
	public int getEdad() { return _iEdad; }
	public Faccion getFaccion() { return _faccion; }
	
	// Métodos modificadores
	public void setCodigo(String sCodigo) { _sCodigo = sCodigo; }
	public void setNombre(String sNombre) { _sNombre = sNombre; }
	public void setApellido(String sApellido) { _sApellido = sApellido; }
	public void setEdad(int iEdad) { _iEdad = iEdad; }
	public void setFaccion(Faccion faccion) { _faccion = faccion; }
	
	/**
	 * @param iId Clave primaria del registro de la tabla Residente
	 * @throws Exception Lanza excepción si no hay ningún registro cuyo Id sea iId
	 */
	public Residente(int iId) throws Exception {
		
		super(iId, "Residente");
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
	 * @param con Conexión con la base de datos
	 * @throws Exception Lanza excepción si no hay ningún registro cuyo Id sea iId
	 */
	public Residente(int iId, Connection con) throws Exception {
		
		super(iId,"Residente");
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
	 * @param iId Identificador de cada residente en la tabla
	 * @param sCodigo Identificador del residente
	 * @param sNombre Nombre del residente
	 * @param sApellido Apellido del residente
	 * @param iEdad Edad del residente
	 * @param faccion Faccion del residente
	 * @return se devuelve un nuevo residente con los datos dados
	 */
	private Residente(int iId, String sCodigo, String sNombre, 
			String sApellido, int iEdad, Faccion faccion) {
		
		super(iId, "Residente");
		_sCodigo = sCodigo;
		_sNombre = sNombre;
		_sApellido = sApellido;
		_iEdad = iEdad;
		_faccion = faccion;
	}
	
	/**
	 * @param sCodigo Identificador del residente
	 * @param sNombre Nombre del residente
	 * @param sApellido Apellido del residente
	 * @param iEdad Edad del residente
	 * @param faccion Faccion del residente
	 * @return se devuelve un nuevo residente con los datos dados
	 * @throws Exception Lanza excepción si sNombre, sApellido o iEdad son nulos
	 */
	public static Residente Create(String sCodigo, String sNombre, 
			String sApellido, Integer iEdad, Faccion faccion) throws Exception {
		
		Connection con = null;
	
		try{
			
			con = Data.Connection();
			
			con.createStatement().executeUpdate(
					"INSERT INTO Residente (Codigo, Nombre, Apellido, Edad, " 
					 + "Id_Faccion) VALUES (" 
					 + sCodigo + ", '"+ sNombre + "', '" + sApellido + "', " 
					 + String.valueOf(iEdad) + ", " 
					 + String.valueOf(faccion.getId()) + ");");
			
			return new Residente(Data.LastId(con));
			
		}
		catch(Exception e) { throw e; }
		finally {
			
			if(con != null) con.close();
		}
	}
	
	/**
	 * Método para obtener la representación de un Residente como un String
	 */
	public String toString() {
		return super.toString() + ":" + _sCodigo + ":" + _sNombre 
				+ ":" + _sApellido + ":" + String.valueOf(_iEdad) + ";" 
				+ _faccion.toString();
	}
	
	/**
	 * @throws Exception Lanza excepción si el Residente no existe en la base de datos
	 */
	public void Update() throws Exception {
		
		super.Update("UPDATE Residente SET Codigo ='" + _sCodigo + "', Nombre ='" 
			+ _sNombre + "', Apellido ='" + _sApellido + "', Edad = " 
			+ String.valueOf(_iEdad) + ", Id_Faccion = " + _faccion.getId() 
			+ " WHERE Id = " + String.valueOf(getId()));
	}
	
	/**
	 * @param sCodigo Identificador del residente
	 * @param sNombre Nombre del residente
	 * @param sApellido Apellido del residente
	 * @param iEdad Edad del residente
	 * @param sFaccion Faccion del residente
	 * @return devuelve un conjunto de residentes con los datos dados
	 * @throws Exception
	 */
	public static ArrayList<Residente> Select(String sCodigo, String sNombre, 
			String sApellido, Integer iEdad, String sFaccion) throws Exception {
		
		Connection con = null;
		ResultSet rs = null;
		
		try {
			
			con = Data.Connection();
			ArrayList<Residente> residente = new ArrayList<Residente>();
			
			String sQuery = "SELECT R.Id, R.Codigo, R.Nombre, R.Apellido, R.Edad, R.Id_Faccion " 
					+ "FROM Residente R ";
			
			if(sFaccion != null)
				sQuery = sQuery.concat("INNER JOIN Faccion F ON R.Id_Faccion = F.Id ");
			
			sQuery = sQuery.concat(Where(new String[] { "R.Codigo", "R.Nombre", 
										"R.Apellido", "R.Edad", 
										"F.Nombre"}, 
								new int[] { Types.VARCHAR, Types.VARCHAR, 
										Types.VARCHAR, Types.INTEGER, 
										Types.VARCHAR }, 
								new Object[] { sCodigo, sNombre, sApellido, iEdad, 
										sFaccion }));
			
			rs = con.createStatement().executeQuery(sQuery);
			
			while(rs.next())
				residente.add(new Residente(rs.getInt("Id"), rs.getString("Codigo"), 
						rs.getString("Nombre"), rs.getString("Apellido"), 
						rs.getInt("Edad"), new Faccion(rs.getInt("Id_Faccion"))));
						
			return residente;
		}
		catch(Exception e) { throw e; }
		finally {
			
			if(rs != null) rs.close();
			if(con != null) con.close();
		}
	}
	
	/**
	 * @param iId Clave primaria del registro de la tabla Residente
	 * @param con Conexión con la base de datos
	 * @throws Exception Lanza excepción si no hay ningún registro cuyo Id sea iId
	 */
	void Initialize(int iId, Connection con) throws Exception {
		
		ResultSet rs = null;
		
		try {
			
			rs = con.createStatement().executeQuery(
					"SELECT Codigo, Nombre, Apellido, Edad, Id_Faccion " 
					+ "FROM Residente where Id = " 
					+ String.valueOf(iId));
			
			rs.next();
			
			if(rs.getRow() == 0) 
				throw new Exception("El id " +String.valueOf(iId) + 
						" no se encuentra en la tabla");
			
			_sCodigo = rs.getString("Codigo");
			_sNombre = rs.getString("Nombre");
			_sApellido = rs.getString("Apellido");
			_iEdad = rs.getInt("Edad");
			_faccion = new Faccion(rs.getInt("Id_Faccion"));
		}
		catch (Exception e) { throw e; }
		finally {
			
			if (rs != null) rs.close();
        }
	}
}
