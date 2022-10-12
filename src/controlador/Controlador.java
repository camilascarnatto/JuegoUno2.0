package controlador;

import java.rmi.RemoteException;
import java.util.ArrayList;

import controlador.IVista;
import modelo.JuegoPublico;
import modelo.Jugador;
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

	public Controlador(IVista vista) {
		this.vista = vista;
		juego.agregarObservador(this);
		vista.setEstadoVista(estadoVista.SETEANDO);
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
		}
		
	}
	
	public int cantidadJugadores() {
		return juego.getCantidadJugadores();
	}

	public ArrayList<Jugador> getJugadores() {
		return juego.getJugadores();
	}
		
	
}
