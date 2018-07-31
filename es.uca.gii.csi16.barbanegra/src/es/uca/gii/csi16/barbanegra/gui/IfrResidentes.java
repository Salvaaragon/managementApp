package es.uca.gii.csi16.barbanegra.gui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JTextField;

import es.uca.gii.csi16.barbanegra.data.Faccion;
import es.uca.gii.csi16.barbanegra.data.Residente;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IfrResidentes extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField _txtCodigo;
	private JTextField _txtNombre;
	private JTextField _txtApellido;
	private JTextField _txtEdad;
	private JTable _tabResult;
	private Container _pnlParent;
	private JComboBox<Faccion> _cmbFaccion;

	/**
	 * Constructor del frame para buscar residentes
	 * @param pnlParent
	 * @throws Exception 
	 */
	public IfrResidentes(Container pnlParent) throws Exception {
		
		_pnlParent = pnlParent;
		setResizable(true);
		setClosable(true);
		setTitle("Residentes");
		setBounds(100, 100, 450, 300);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel lblCodigo = new JLabel("C\u00F3digo");
		panel.add(lblCodigo);
		
		_txtCodigo = new JTextField();
		panel.add(_txtCodigo);
		_txtCodigo.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		panel.add(lblNombre);
		
		_txtNombre = new JTextField();
		panel.add(_txtNombre);
		_txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		panel.add(lblApellido);
		
		_txtApellido = new JTextField();
		panel.add(_txtApellido);
		_txtApellido.setColumns(10);
		
		JLabel lblEdad = new JLabel("Edad");
		panel.add(lblEdad);
		
		_txtEdad = new JTextField();
		panel.add(_txtEdad);
		_txtEdad.setColumns(10);
		
		JLabel lblFaccion = new JLabel("Facci\u00F3n");
		panel.add(lblFaccion);
		
		_cmbFaccion = new JComboBox<Faccion>();
		_cmbFaccion.setModel(
				 new FaccionListModel(Faccion.Select()));
		panel.add(_cmbFaccion);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				try {
					
					String sCodigo = null;
					String sNombre = null;
					String sApellido = null;
					Integer iEdad = null;
					String sFaccion = null;
					
					if(!_txtCodigo.getText().isEmpty()) 
						sCodigo = _txtCodigo.getText();
					
					if(!_txtNombre.getText().isEmpty()) 
						sNombre = _txtNombre.getText();
					
					if(!_txtApellido.getText().isEmpty()) 
						sApellido = _txtApellido.getText();
					
					if(!_txtEdad.getText().isEmpty()) 
						iEdad = Integer.parseInt(_txtEdad.getText());
					
					if(_cmbFaccion.getSelectedItem() != null) 
						sFaccion = _cmbFaccion.getSelectedItem().toString();
					
					_tabResult.setModel(
							new ResidentesTableModel(Residente.Select(sCodigo, sNombre, 
									sApellido, iEdad, sFaccion)));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		panel.add(btnBuscar);
		
		_tabResult = new JTable();
		_tabResult.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent event) {
				
				// Se activa con doble clic sobre una fila
				if (event.getClickCount() == 2) {
					int iRow = ((JTable)event.getSource()).getSelectedRow();
					
					Residente residente =
							((ResidentesTableModel) 
									_tabResult.getModel()).getData(iRow);
					
					if (residente != null) {
						IfrResidente ifrResidente;
						try {
							ifrResidente = new IfrResidente(residente);
							ifrResidente.setBounds(0, 0, 400, 300);
							_pnlParent.add(ifrResidente, 0);
							ifrResidente.setVisible(true);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
		
		getContentPane().add(_tabResult, BorderLayout.CENTER);
	}
}