package es.uca.gii.csi16.barbanegra.gui;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import es.uca.gii.csi16.barbanegra.data.Faccion;

public class FaccionListModel 
	extends AbstractListModel<Faccion> 
	implements ComboBoxModel<Faccion> {
	
	private static final long serialVersionUID = 1L;
	
	private List<Faccion> _aData;
	private Object _selection = null;
	
	/**
	 * Constructor de FaccionListModel
	 * @param aData
	 */
	public FaccionListModel(List<Faccion> aData) {
		
		_aData = aData;
	}
	
	@Override
	/**
	 * Devuelve el elemento de la posición iIndex
	 * @return elemento de la posición iIndex
	 */
	public Faccion getElementAt(int iIndex) {
		
		return _aData.get(iIndex);
	}
	
	/**
	 * Devuelve el número de elementos existentes
	 * @return número de elementos existentes
	 */
	public int getSize() {
		return _aData.size();
	}

	@Override
	/**
	 * Devuelve el elemento seleccionado
	 * @return elemento seleccionado
	 */
	public Object getSelectedItem() {
		
		return _selection;
	}

	@Override
	/**
	 * El objeto dado pasa a ser el elemento seleccionado
	 * @param o objeto que pasa a ser el elemento seleccionado
	 */
	public void setSelectedItem(Object o) {
		_selection = o;
	}
	
}
