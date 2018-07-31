package es.uca.gii.csi16.barbanegra.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uca.gii.csi16.barbanegra.data.Data;
import es.uca.gii.csi16.barbanegra.data.Faccion;
import es.uca.gii.csi16.barbanegra.data.Residente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResidenteTest {

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
		ResultSet rs = null;
		
		try {
			
			Residente r1 = new Residente(2);
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT Codigo, Nombre, Apellido, Edad FROM Residente "
					+ "WHERE Id = 2;");
			rs.next();
			
			Assert.assertEquals(r1.getCodigo(), rs.getString("Codigo") );
			Assert.assertEquals(r1.getNombre(), rs.getString("Nombre"));
			Assert.assertEquals(r1.getApellido(), rs.getString("Apellido"));
			Assert.assertEquals(r1.getEdad(), rs.getInt("Edad"));
			
		}
		catch(SQLException e) { throw e; }
		finally {
			
			if(rs != null) rs.close();
			if(con != null) con.close();
		}
		
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void testCreate() throws Exception {
		
		Connection con = null;
		ResultSet rs = null;
		
		try {
			
			Residente r2 = Residente.Create("221488", "Juan", "Rodríguez", 79, new Faccion(1));
			con = Data.Connection();
			rs = con.createStatement().executeQuery(
				"SELECT Codigo, Nombre, Apellido, Edad FROM Residente WHERE Id = "
				+ r2.getId() + ";");
			rs.next();
			
			Assert.assertEquals(r2.getCodigo(), rs.getString("Codigo"));
			Assert.assertEquals(r2.getNombre(), rs.getString("Nombre"));
			Assert.assertEquals(r2.getApellido(), rs.getString("Apellido"));
			Assert.assertEquals(r2.getEdad(), rs.getInt("Edad"));
			
		}
		catch(SQLException e) {
			
			System.out.println("Mensaje: " + e.getMessage());
			System.out.println("Estado SQL: " + e.getSQLState());
			System.out.println("Codigo de error SQL: " + e.getErrorCode());
		}
		finally {
			
			if(rs != null) rs.close();
			if(con != null) con.close();
		}
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void testSelect() throws Exception {
		
		Connection con = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ArrayList<Residente> Residentes1;
		ArrayList<Residente> Residentes2;
		
		try {
			
			con = Data.Connection();
			rs1 = con.createStatement().executeQuery(
					"SELECT Codigo, Nombre, Apellido, Edad FROM Residente " 
					+ "WHERE Edad = 50");
			rs2 = con.createStatement().executeQuery(
					"SELECT Codigo, Nombre, Apellido, Edad FROM Residente " 
					+ "WHERE Nombre LIKE 'J%' AND Apellido LIKE '%r'");
			Residentes1 = Residente.Select(null, null, null, Integer.valueOf(50), null);
			Residentes2 = Residente.Select(null, "J%", "%r", null, null);
			
			int iIndice = 0;
			while(rs1.next() && iIndice != Residentes1.size()) {
				
				Assert.assertEquals(Residentes1.get(iIndice).getCodigo(), rs1.getString("Codigo"));
				Assert.assertEquals(Residentes1.get(iIndice).getNombre(), rs1.getString("Nombre"));
				Assert.assertEquals(Residentes1.get(iIndice).getApellido(), rs1.getString("Apellido"));
				Assert.assertEquals(Residentes1.get(iIndice).getEdad(), rs1.getInt("Edad"));
				iIndice++;
			}
			
			iIndice = 0;
			while(rs2.next() && iIndice != Residentes2.size()) {
				
				Assert.assertEquals(Residentes2.get(iIndice).getCodigo(), rs2.getString("Codigo"));
				Assert.assertEquals(Residentes2.get(iIndice).getNombre(), rs2.getString("Nombre"));
				Assert.assertEquals(Residentes2.get(iIndice).getApellido(), rs2.getString("Apellido"));
				Assert.assertEquals(Residentes2.get(iIndice).getEdad(), rs2.getInt("Edad"));
				iIndice++;
			}
			
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
	public void testUpdate() throws Exception {
		
		Connection con = null;
		ResultSet rs = null;
		
		try {
			
			Residente r = new Residente(6);
			
			r.setNombre("Miguel");
			r.setApellido("Ratón");
			r.setEdad(90);
			
			r.Update();
			
			con = Data.Connection();
			rs = con.createStatement().executeQuery(
					"SELECT Nombre, Apellido, Edad FROM Residente " 
					+ "WHERE Id = 6");
			
			rs.next();
			
			Assert.assertEquals(r.getNombre(), rs.getString("Nombre"));
			Assert.assertEquals(r.getApellido(), rs.getString("Apellido"));
			Assert.assertEquals(r.getEdad(), rs.getInt("Edad"));	
		}
		catch(SQLException e) { throw e; }
		finally {
			
			if(rs != null) rs.close();
			if(con != null) con.close();
		}
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void testDelete() throws Exception {
		
		Connection con = null;
		ResultSet rs = null;
		
		try {
			
			con = Data.Connection();
			Residente r = new Residente(3);
			
			r.Delete();
			
			rs = con.createStatement().executeQuery(
					"SELECT Codigo, Nombre, Apellido, Edad FROM Residente"
					+ " WHERE Id = 3");
			
			rs.next();
			
			Assert.assertEquals(rs.getRow(),0);
			Assert.assertEquals(r.getIsDeleted(),true);
		}
		catch(SQLException e) { throw e; }
		finally {
			
			if(con != null) con.close();
		}
	}
}
