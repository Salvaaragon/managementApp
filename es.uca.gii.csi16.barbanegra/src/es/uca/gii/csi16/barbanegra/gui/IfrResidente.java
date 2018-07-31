package es.uca.gii.csi16.barbanegra.gui;


import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import es.uca.gii.csi16.barbanegra.data.Faccion;
import es.uca.gii.csi16.barbanegra.data.Residente;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IfrResidente extends JInternalFrame {
	
	/**
	 *	Hemos puesto esto para arreglar el warning, pero no sabemos si se arregla así
	 */
	private static final long serialVersionUID = 1L;
	private JTextField _txtCodigo;
	private JTextField _txtNombre;
	private JTextField _txtApellido;
	private JTextField _txtEdad;
	private Residente _residente = null;
	private JComboBox<Faccion> _cmbFaccion; 
	
	/**
	 * Constructor del frame para Residente
	 * @param residente residente que se va a crear en la tabla
	 * @throws Exception 
	 */
	public IfrResidente(Residente residente) throws Exception {
		
		setClosable(true);
		setResizable(true);
		setTitle("Residente");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setBounds(108, 42, 46, 14);
		getContentPane().add(lblCodigo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(108, 83, 46, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(108, 127, 46, 14);
		getContentPane().add(lblApellido);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(108, 171, 46, 14);
		getContentPane().add(lblEdad);
		
		JLabel lblFaccion = new JLabel("Facci\u00F3n");
		lblFaccion.setBounds(108, 216, 46, 14);
		getContentPane().add(lblFaccion);
		
		_txtCodigo = new JTextField();
		_txtCodigo.setBounds(164, 42, 65, 20);
		getContentPane().add(_txtCodigo);
		_txtCodigo.setColumns(10);
		
		_txtNombre = new JTextField();
		_txtNombre.setBounds(164, 83, 123, 20);
		getContentPane().add(_txtNombre);
		_txtNombre.setColumns(10);
		
		_txtApellido = new JTextField();
		_txtApellido.setBounds(164, 130, 123, 20);
		getContentPane().add(_txtApellido);
		_txtApellido.setColumns(10);
		
		_txtEdad = new JTextField();
		_txtEdad.setBounds(164, 171, 30, 20);
		getContentPane().add(_txtEdad);
		_txtEdad.setColumns(10);
		
		_cmbFaccion = new JComboBox<Faccion>();
		_cmbFaccion.setModel(new FaccionListModel(Faccion.Select()));
		_cmbFaccion.setBounds(166, 213, 121, 20);
		getContentPane().add(_cmbFaccion);
		
		JButton butGuardar = new JButton("Guardar");
		butGuardar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Integer iEdadValida = null;
					String sCodigoValido = null;
					
					if(_residente == null) { 
						
						// Hacemos esto ya que no se puede convertir un nulo
						// a entero por medio del metodo parseInt
						if(!_txtEdad.getText().equals("")) 
							iEdadValida = Integer.parseInt(_txtEdad.getText());
						
						if(!_txtCodigo.getText().equals(""))
							sCodigoValido = _txtCodigo.getText();
						
						if(_cmbFaccion.getSelectedItem() == null) 
							throw new Exception("La facción ha de estar seleccionada.");
						
						_residente = Residente.Create(sCodigoValido, 
								_txtNombre.getText(), _txtApellido.getText(), iEdadValida, 
								(Faccion) _cmbFaccion.getModel().getSelectedItem());
					}
					
					else {
						
						if(!_txtEdad.getText().equals("")) 
							iEdadValida = Integer.parseInt(_txtEdad.getText());
						
						if(!_txtCodigo.getText().equals(""))
							sCodigoValido = _txtCodigo.getText();
						
						if(_cmbFaccion.getSelectedItem() == null) 
							throw new Exception("La facción ha de estar seleccionada.");
						
						_residente.setCodigo(sCodigoValido);
						_residente.setNombre(_txtNombre.getText());
						_residente.setApellido(_txtApellido.getText());
						_residente.setEdad(iEdadValida);
						_residente.setFaccion(
								(Faccion) _cmbFaccion.getModel().getSelectedItem());
						
						_residente.Update();
					}
	
				}
				catch(Exception ee) { JOptionPane.showMessageDialog(null, ee.getMessage()); }
			}
		});
		
		butGuardar.setBounds(335, 236, 89, 23);
		getContentPane().add(butGuardar);
		
		_residente = residente;
		
		if(_residente != null) {
			
			_txtCodigo.setText(_residente.getCodigo());
			_txtNombre.setText(_residente.getNombre());
			_txtApellido.setText(_residente.getApellido());
			_txtEdad.setText(String.valueOf(_residente.getEdad()));
			_cmbFaccion.getModel().setSelectedItem(_residente.getFaccion());
		}
	}
}
