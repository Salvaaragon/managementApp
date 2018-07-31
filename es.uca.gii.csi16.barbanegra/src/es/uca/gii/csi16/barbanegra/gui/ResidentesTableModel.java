package es.uca.gii.csi16.barbanegra.gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import es.uca.gii.csi16.barbanegra.data.Residente;

public class ResidentesTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Residente> _aData;
	
	/**
	 * Constructor de ResidentesTableModel
	 * @param aData ArrayList con los Residentes
	 */
	public ResidentesTableModel(ArrayList<Residente> aData) {
		
		_aData = aData;
	}
	
	/**
	 * 
	 * @param iRow fila del registro al que queremos acceder
	 * @return devuelve el residente que se encuentra en la posición iRow del ArrayList
	 */
	public Residente getData(int iRow) {
		return _aData.get(iRow);
	}
	
	@Override
	/**
	 * @return Devuelve el numero de columnas de la tabla
	 */
	public int getColumnCount() {
		return 5;
	}

	@Override
	/**
	 * @return Devuelve el numero de Residentes del ArrayList
	 */
	public int getRowCount() {
		return _aData.size();
	}

	@Override
	/**
	 * @param iRow posicion del ArrayList a la que queremos acceder
	 * @param iCol atributo en concreto al que queremos acceder de 
	 * 	dicho Residente
	 * @return Devuelve el campo iCol del Residente 
	 * 	que se encuentra en la posicion iRow del ArrayList
	 */
	public Object getValueAt(int iRow, int iCol) {
		
		switch(iCol) {
			case 0: return _aData.get(iRow).getCodigo();
			case 1: return _aData.get(iRow).getNombre();
			case 2: return _aData.get(iRow).getApellido();
			case 3: return _aData.get(iRow).getEdad();
			case 4: return _aData.get(iRow).getFaccion();
			default: throw new IllegalStateException(""
					+ "Número de columnas mayor" 
					+ " que número parámetros");
		}
	}
}