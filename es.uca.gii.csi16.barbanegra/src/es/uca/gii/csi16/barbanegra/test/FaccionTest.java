package es.uca.gii.csi16.barbanegra.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uca.gii.csi16.barbanegra.data.Data;
import es.uca.gii.csi16.barbanegra.data.Faccion;

public class FaccionTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Data.LoadDriver();
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void testConstructor() throws Exception {
		
		Connection con = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		
		try {
			
			Faccion f1 = new Faccion(1);
			Faccion f2 = new Faccion(2);
			
			con = Data.Connection();
			rs1 = con.createStatement().executeQuery("SELECT Nombre FROM Faccion "
					+ "WHERE Id = 1;");
			rs2 = con.createStatement().executeQuery("SELECT Nombre FROM Faccion "
					+ "WHERE Id = 2;");
			
			rs1.next();
			rs2.next();
			
			Assert.assertEquals(f1.getNombre(), rs1.getString("Nombre"));
			Assert.assertEquals(f2.getNombre(), rs2.getString("Nombre"));
			
		}
		catch(SQLException e) { throw e; }
		finally {
			
			if(rs1 != null) rs1.close();
			if(rs2 != null) rs2.close();
			if(con != null) con.close();
		}
		
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void testSelect() throws Exception {
		
		Connection con = null;
		ResultSet rs = null;
		ArrayList<Faccion> Facciones;
		
		try {
			
			con = Data.Connection();
			rs = con.createStatement().executeQuery(
					"SELECT Nombre FROM Faccion");
			
			Facciones = Faccion.Select();
			
			int iIndice = 0;
			
			while(rs.next() && iIndice != Facciones.size()) {
				
				Assert.assertEquals(Facciones.get(iIndice).getNombre(), rs.getString("Nombre"));
				iIndice++;
			}
			
		}
		catch(SQLException sqlEx) {
			throw sqlEx;
		}
		finally {
			
			if(rs != null) rs.close();
			if(con != null) con.close();
		}
	}
}
