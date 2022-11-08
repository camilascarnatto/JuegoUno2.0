package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import controlador.IVista;
import modelo.EstadosVista;
import modelo.Jugador;
import net.miginfocom.swing.MigLayout;

import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JList;

public class VentanaPrincipal extends JFrame implements IVista, ActionListener {

	private static final long serialVersionUID = 1L;
	
	private Controlador controlador;
	private ArrayList<Jugador> jugadores = new ArrayList<>();
	
	private int vistaDelJugadorNro = -1;
	
	private JFrame frame;

	private JLabel etiqueta1;
	private JLabel lblEstadoDeJuego;
	private JLabel lblInformativo;
	
	private JButton btnUno;
	private JButton btnComenzarJuego;
	private JButton btnAgregarJugador;
	private JButton boton2;
	private JButton boton3;
	private JButton boton4;
		
	private JPanel panelPrincipal;
	private JPanel panelCentral;
	private JPanel panelConfiguracion;
	private JPanel panelSuperior;
	private JPanel panelDerecho;
	private JPanel panelIzquierdo;
	private JPanel panelInferior;
	
	private JMenuBar menuGeneral;
	private JMenu menuArchivo;
	private JMenu menuJuego;
	
	private JMenuItem menuSalir;
	
	private JTable playerTable;
	private JScrollPane playerScroll;
	

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		
		frame = new JFrame("Titulo de la ventana");
		frame.setSize(627, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelPrincipal = (JPanel) frame.getContentPane();
		panelPrincipal.setLayout(new BorderLayout());
		
		menuGeneral = new JMenuBar();
		menuArchivo = new JMenu("Archivo");
		menuJuego = new JMenu("Juego");
		
		menuGeneral.add(menuArchivo);
		menuGeneral.add(menuJuego);
		
		menuSalir = new JMenuItem("Salir");
		menuArchivo.add(menuSalir);
		
		frame.setJMenuBar(menuGeneral);
		
		
		panelDerecho = new JPanel();
		panelPrincipal.add(panelDerecho, BorderLayout.EAST);
		panelDerecho.setLayout(new MigLayout("", "[center,grow]", "[center][grow]"));

		panelIzquierdo = new JPanel();
		panelPrincipal.add(panelIzquierdo, BorderLayout.WEST);
		panelIzquierdo.setLayout(new MigLayout("", "[]", "[]"));
		
		
		panelSuperior = new JPanel();
		panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new MigLayout("", "[][][][][][][]", "[]"));
		
		panelCentral = new JPanel();
		panelCentral.setLayout(new MigLayout("center , center"));
		
		panelInferior = new JPanel();
		panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setLayout(new MigLayout("", "[]", "[]"));
		
				
		// Defino el panel derecho
		playerTable = new JTable();
		playerScroll = new JScrollPane(playerTable);
		playerScroll.setMaximumSize(new Dimension(250,7000));
		playerScroll.setMinimumSize(new Dimension(100,100));
		playerTable.disable();
		playerTable.getTableHeader().setResizingAllowed(false);
		playerTable.getTableHeader().setReorderingAllowed(false);
		panelDerecho.add(new JLabel("Jugadores"), "cell 0 0,center");
		panelDerecho.add(playerScroll, "cell 0 1,grow");
		
		//panel superior
		lblEstadoDeJuego = new JLabel("Estado de Juego:");
		panelSuperior.add(lblEstadoDeJuego, "cell 0 0");
		lblEstadoDeJuego.setVisible(true);
		
		//panel inferior
		btnUno = new JButton("Uno");
		panelInferior.add(btnUno, "cell 1 0");
		btnUno.setVisible(false);
		
		//panel central
		panelConfiguracion = new JPanel();
		panelPrincipal.add(panelConfiguracion,BorderLayout.CENTER);
		panelConfiguracion.setLayout(new MigLayout("", "[grow,center]", "[grow][grow][grow][grow]"));

		JLabel lblBienvenida = new JLabel("Bienvenido al UNO");
		panelConfiguracion.add(lblBienvenida,"cell 0 0");

		lblInformativo = new JLabel("Debe agregar al menos 2 jugadores para poder jugar");
		panelConfiguracion.add(lblInformativo,"cell 0 1");

		btnAgregarJugador = new JButton ("Agregar Jugador");
		panelConfiguracion.add(btnAgregarJugador,"cell 0 2");

		btnComenzarJuego = new JButton ("Comenzar Juego");
		btnComenzarJuego.setVisible(false); //Lo hago visible cuando el juego sea JUGABLE
		panelConfiguracion.add(btnComenzarJuego,"cell 0 3");
		
		frame.setVisible(true);
		
		btnAgregarJugador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombreJugador = JOptionPane.showInputDialog("Ingrese nombre del jugador");
				if(nombreJugador != null) {
					controlador.agregarJugador(nombreJugador);
				}
				
			}
		});
				
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando.equals("Boton 1")) {
			etiqueta1.setText("Usted eligio el boton 1");
		}else if (comando.equals("Boton 2")) {
			etiqueta1.setText("Usted eligio el boton 2");
		}else etiqueta1.setText("No se que paso");
	}

	@Override
	public void actualizarListaJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
		Object[] columnNames = new Object[]{"Nombre", "Estado", "Puntaje", "En Turno"};
		Object[][] data = new Object[jugadores.size()][columnNames.length];
		int row = 0;
		for (Jugador jugador:jugadores) {
			data[row][0] = jugador.getNombre();
			data[row][1] = jugador.getEstado();
			data[row][2] = jugador.getPuntos();
			data[row][3] = jugador.enTurno()?"X":"";
			row++;
		}
		DefaultTableModel model = new DefaultTableModel(data,columnNames);
		playerTable.setModel(model);
		playerTable.getColumn("Nombre").setMaxWidth(75);;
		playerTable.getColumn("Estado").setMaxWidth(75);
		playerTable.getColumn("Puntaje").setMaxWidth(55);
		playerTable.getColumn("En Turno").setMaxWidth(65);
		
	}

	

	@Override
	public void setEstadoVista(EstadosVista estado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void comenzar() {
		
	}

	@Override
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	

	@Override
	public void error(String error) {
		
	}
	
	
	
	
	@Override
	public void salirJuego() {
		// TODO Auto-generated method stub
		
	}

}
