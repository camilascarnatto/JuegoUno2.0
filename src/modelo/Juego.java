package modelo;

import java.io.Serializable;
import java.util.ArrayList;

import modelo.Jugador;
import modelo.JuegoPublico;
import modelo_controlador.IObservador;
import modelo_controlador.posiblesCambios;

public class Juego /*extends ObservableRemoto*/ implements JuegoPublico/*,Serializable*/ {
	
	/* ObservableRemoto implementa el array de observadores internamente
	 * Cuando se implemente RMI va a haber que borrarlo de aca 
	 */
	private ArrayList<IObservador> observadores = new ArrayList<>();
	private ArrayList<Jugador> jugadores = new ArrayList<>();
	private MazoUno mazo = new MazoUno();
	private MazoUno mazoDescarte = new MazoUno();
	private int jugadorEnTurno = -1;
	private String errorMsj = "";
	private estadoJuego estado = estadoJuego.INICIAL;
	
	
	
	public Juego() {
		
	}
	
	/**
	 * Implementacion de observer
	 * @param cambio
	 */
	private void notificarObservadores(posiblesCambios cambio){
		for(IObservador observador: observadores) {
			observador.actualizar(cambio);
			
		}
	}

	@Override
	public void agregarObservador(IObservador observador) {
		observadores.add(observador);
	}
	
	
	@Override
	public int agregarJugador(String nombre) {
		int nroJugador = -1;
		if(jugadores.size() != 4) {
			Jugador nuevoJugador = new Jugador(nombre);
			jugadores.add(nuevoJugador);
			nroJugador = jugadores.indexOf(nuevoJugador);
			notificarObservadores(posiblesCambios.ACTUALIZAR_LISTA_JUGADORES);
		}else {
			msjError("SOLO SE PERMITE HASTA 4 JUGADORES!");			
		}
		
		if(jugadores.size() == 1) {
			cambiarEstado(estadoJuego.SETEANDO);
			estado = estadoJuego.SETEANDO;
		}else 
			if(jugadores.size() >= 2) {
				cambiarEstado(estadoJuego.JUGABLE);
				estado = estadoJuego.JUGABLE;
			}
		
		return nroJugador;
	}

	private void cambiarEstado(estadoJuego estado) {
		/*switch (estado) {
		case JUGANDO:
			
		}*/
		notificarObservadores(posiblesCambios.ESTADO_JUEGO);
	}

	
	public String getEstado() {
		return estado.name();
	}
	
	public int getCantidadJugadores () {
		return jugadores.size();
	}


	private void msjError(String errorMessage) {
		this.errorMsj = errorMessage;
		notificarObservadores(posiblesCambios.ERROR);
	}
	
	public String getError() {
		return errorMsj;
	}
	
	@Override
	public ArrayList<Jugador> getJugadores(){
		ArrayList<Jugador> jugadoresDuplicados = new ArrayList<>();
		for (Jugador jugador:jugadores) 
			jugadoresDuplicados.add(jugador.duplicar());
		return jugadoresDuplicados;
	}
	

}
