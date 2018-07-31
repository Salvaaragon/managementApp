package es.uca.gii.csi16.barbanegra.data;

import java.sql.Connection;
import java.sql.Types;

public abstract class Entidad {
	
	private int _iId;
	private String _sTabla;
	private boolean _bIsDeleted;
	
	public int getId() { return _iId; }
	public boolean getIsDeleted() { return _bIsDeleted; }
	
	/**
	 * @param iId
	 * @param sTabla
	 */
	public Entidad(int iId, String sTabla) {
		
		_iId = iId;
		_sTabla = sTabla;
		_bIsDeleted = false;
	}
	
	/**
	 * Método general para hacer un update en la base de datos
	 * @param sQuery Consulta de update
	 * @throws Exception
	 */
	protected void Update(String sQuery) throws Exception {
		
		Connection con = null;
		
		try {
			
			if(!_bIsDeleted) {
				
				con = Data.Connection();
				
				con.createStatement().executeUpdate(sQuery);			
			}
			else
				throw new Exception(_sTabla + toString() + " no se encuentra "
						+ "en la base de datos");
		}
		catch(Exception e) { System.out.println("Mensaje: " + e.getMessage()); }
		finally {
			
			if(con != null) con.close();
		}
	}
	
	/**
	 * Método general para hacer un delete en la base de datos
	 * @throws Exception
	 */
	public void Delete() throws Exception{
		
		Connection con = null;
		
		try {
			
			if(!_bIsDeleted) {
				
				con = Data.Connection();
				con.createStatement().executeUpdate(
						"DELETE FROM " + _sTabla + " WHERE Id = " + String.valueOf(_iId));
				
				_bIsDeleted = true;
			}
			else 
				throw new Exception(_sTabla + " " + toString() + " no se encuentra"
					+ " en la base de datos");
		}
		catch(Exception e) { System.out.println("Mensaje: " + e.getMessage()); }
		finally {
			
			if(con != null) con.close();
		}
	}
	
	/**
	 * Método general where con el que hacer un select con condiciones
	 * @param asCampo Campos de la tabla por los que buscar
	 * @param aiTipo Tipo de cada campo
	 * @param aoValor Valores de cada campo
	 * @return devuelve el String con el where y las condiciones
	 */
	protected static String Where(String [] asCampo, int [] aiTipo, 
			Object [] aoValor) {
		
		String sWhere = "WHERE";
		
		for(int i = 0; i < asCampo.length; i++)
			if(aoValor[i] != null) {
				if(aiTipo[i] == Types.INTEGER)
					sWhere = sWhere.concat(" " + asCampo[i] + " = " 
							+ String.valueOf(aoValor[i]) 
							+ " AND ");
				else
					sWhere = sWhere.concat(" " + asCampo[i] + " LIKE '" 
							+ aoValor[i] + "' AND ");
			}
		
		sWhere = sWhere.substring(0, sWhere.length() - 5);

		return sWhere;
	}
}
