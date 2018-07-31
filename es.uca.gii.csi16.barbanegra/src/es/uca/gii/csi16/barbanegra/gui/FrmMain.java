package es.uca.gii.csi16.barbanegra.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmMain {

	private JFrame _frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
					
					FrmMain frmWindow = new FrmMain();
					frmWindow._frame.setVisible(true);
					
				} catch (Exception e) { 
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrmMain() throws Exception{
		
		try {
			
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
			initialize();
		}
		catch(Exception e){ throw e; }
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		_frame = new JFrame();
		_frame.setTitle("Asilo de Jedis y Siths");
		_frame.setBounds(100, 100, 420, 365);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		_frame.setJMenuBar(menuBar);
		
		JMenu mitNew = new JMenu("Nuevo");
		menuBar.add(mitNew);
		
		JMenuItem mitNewResidente = new JMenuItem("Residente");
		mitNewResidente.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				
				IfrResidente ifrResidente;
				try {
					ifrResidente = new IfrResidente(null);
					ifrResidente.setBounds(0, 0, 400, 300);
					_frame.getContentPane().add(ifrResidente);
					ifrResidente.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		mitNew.add(mitNewResidente);
		
		JMenu mitSearch = new JMenu("Buscar");
		menuBar.add(mitSearch);
		
		JMenuItem mitSearchResidente = new JMenuItem("Residente");
		mitSearchResidente.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				
				IfrResidentes ifrResidentes;
				try {
					ifrResidentes = new IfrResidentes(_frame);
					ifrResidentes.setBounds(0, 0, 400, 300);
					// El segundo parámetro es para que siempre aparezca delante
					_frame.getContentPane().add(ifrResidentes, 0);
					ifrResidentes.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		mitSearch.add(mitSearchResidente);
		_frame.getContentPane().setLayout(null);
	}
}