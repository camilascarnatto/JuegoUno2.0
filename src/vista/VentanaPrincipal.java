package vista;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import controlador.IVista;
import modelo.EstadosVista;
import modelo.Jugador;

public class VentanaPrincipal extends JFrame implements IVista {

	private static final long serialVersionUID = 1L;
	
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

	@Override
	public void actualizarListaJugadores(ArrayList<Jugador> jugadores) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void setEstadoVista(EstadosVista estado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void comenzar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setControlador(Controlador controlador) {
		// TODO Auto-generated method stub
		
	}

}
