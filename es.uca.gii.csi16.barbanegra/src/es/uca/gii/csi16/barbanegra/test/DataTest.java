package es.uca.gii.csi16.barbanegra.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uca.gii.csi16.barbanegra.data.Data;

public class DataTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Data.LoadDriver();
	}
	
	@Ignore @Test
	public void testTableAccess() throws Exception {
		
		Connection con = null;
		ResultSet rs1 = null; // Para obtener el número de elementos de la tabla
		ResultSet rs2 = null; // Para obtener todos los registros de la tabla
		int iNumElementos = 0, iContador = 0;
		
		try {
			
			con = Data.Connection();
			rs1 = con.createStatement().executeQuery("SELECT COUNT(*) Cuenta FROM Residente;");
			rs1.next(); // Nos ponemos en el interior de la tabla obtenida en la consulta
			iNumElementos = rs1.getInt("Cuenta"); // Almacenamos el número de elementos de la tabla obtenido con COUNT(*)
			
			rs2 = con.createStatement().executeQuery("SELECT Codigo, Nombre, Apellido, Edad FROM Residente;");
			
			while(rs2.next()) {
				
				System.out.println(rs2.getString("Codigo") + " " +rs2.getString("Nombre")
						+ " " +rs2.getString("Apellido") + " " +rs2.getInt("Edad"));
				iContador++;
			}
			
			Assert.assertEquals(iContador, iNumElementos);
		}
		catch (SQLException e) { throw e; }
		finally {
			
			if (rs1 != null) rs1.close();
			if (rs2 != null) rs2.close();
			if (con != null) con.close();
        }
	}
	
	@Test
	public void testString2Sql() throws Exception {
		
		Assert.assertEquals(Data.String2Sql("hola", false, false), "hola");
		Assert.assertEquals(Data.String2Sql("hola", true, false), "'hola'");
		Assert.assertEquals(Data.String2Sql("hola", false, true), "%hola%");
		Assert.assertEquals(Data.String2Sql("hola", true, true), "'%hola%'");
		Assert.assertEquals(Data.String2Sql("O'Connell", false, false), "O''Connell");
		Assert.assertEquals(Data.String2Sql("O'Connell", true, false), "'O''Connell'");
		Assert.assertEquals(Data.String2Sql("'Smith '", false, true), "%''Smith ''%");
		Assert.assertEquals(Data.String2Sql("'Smith '", true, false), "'''Smith '''");
		Assert.assertEquals(Data.String2Sql("'Smith '", true, true), "'%''Smith ''%'");	
	}
	
	@Test
	public void testBoolean2Sql() throws Exception {
		
		Assert.assertEquals(Data.Boolean2Sql(true), 1);
		Assert.assertEquals(Data.Boolean2Sql(false), 0);
	}
}
