package controlador;

import java.rmi.RemoteException;

import controlador.IVista;
import modelo.JuegoPublico;
import modelo_controlador.IObservador;
import modelo_controlador.posiblesCambios;

/*
 * JuegoPublico es para RMI pero se puede implementar ahora
 */

public class Controlador implements IObservador/*implements IControladorRemoto CON RMI*/{

	private JuegoPublico juego;
	private IVista vista;

	public Controlador() {

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


}
