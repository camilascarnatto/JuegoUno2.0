package controlador;

import java.rmi.RemoteException;
import java.util.ArrayList;

import controlador.IVista;
import modelo.JuegoPublico;
import modelo.Jugador;
import modelo.estadoJuego;
import modelo_controlador.IObservador;
import modelo_controlador.posiblesCambios;
import modelo.EstadosVista;

/*
 * JuegoPublico es para RMI pero se puede implementar ahora
 */

public class Controlador implements IObservador/*implements IControladorRemoto CON RMI*/{

	private JuegoPublico juego;
	private IVista vista;
	private EstadosVista estadoVista;

	public Controlador(IVista vista, JuegoPublico juego) {
		
		this.vista = vista;
		this.juego = juego;
		vista.setControlador(this);
		juego.agregarObservador(this);
		vista.setEstadoVista(EstadosVista.SETEANDO);
	}

	public void iniciar() {
		
		vista.comenzar();
	}
	
	public void setVista(IVista vista) {
		this.vista =  vista;
	}

	public int agregarJugador(String nombre){
		int numeroJugador = -1;
		
		numeroJugador = juego.agregarJugador(nombre);
		
		return numeroJugador;
	}

	@Override
	public void actualizar(posiblesCambios cambio) {
		switch (cambio) {
		case ACTUALIZAR_LISTA_JUGADORES:
			vista.actualizarListaJugadores(juego.getJugadores());
			break;
		case COMENZO_JUEGO:
			vista.comenzoJuego();
		case ERROR:
			vista.error(juego.getError());
			break;
		case SALIR_JUEGO:
			vista.salirJuego();
			break;
		case ESTADO_JUEGO:
			vista.nuevoEstadoJuego(juego.getEstado().toString());
		default:
			break;
		}
		
	}
	
	public int cantidadJugadores() {
		return juego.getCantidadJugadores();
	}

	public ArrayList<Jugador> getJugadores() {
		return juego.getJugadores();
	}
	
	public String getEstadoJuego() {
		return juego.getEstado().toString();
		
	}
		
	
}
