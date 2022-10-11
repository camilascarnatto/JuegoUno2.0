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
	private ArrayList<IObservador> observadores;
	private ArrayList<Jugador> jugadores = new ArrayList<>();
	private Mazo mazo = new Mazo();
	private int jugadorActual;
	private estadoJuego estado = estadoJuego.INICIAL;
	
	/**
	 * SINGLETON
	 */
	private static Juego instancia = new Juego();
	
	public static Juego getInstance () {
		return instancia;
	}
	
	private Juego() {
		
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
			Jugador nuevoJugador = new Jugador(nombre, jugadores.size());
			jugadores.add(nuevoJugador);
			nroJugador = jugadores.indexOf(nuevoJugador);
			notificarObservadores(posiblesCambios.ACTUALIZAR_LISTA_JUGADORES);
		}else
			System.out.println("Solo se permite hasta cuatro jugadores!");
		
		if(jugadores.size() >= 2) {
			cambiarEstado(estadoJuego.JUGABLE);
		}
		
		return nroJugador;
	}

	private void cambiarEstado(estadoJuego estado) {
		/*switch (estado) {
		case JUGANDO:
			
		}*/
		notificarObservadores(posiblesCambios.ESTADO_JUEGO);
	}

	@Override
	public ArrayList<Jugador> getJugadores() {
		ArrayList<Jugador> jugadoresDuplicados = new ArrayList<>();
		for (Jugador jugador:jugadores) 
			jugadoresDuplicados.add(jugador);
		return jugadoresDuplicados;
	}
	
	public String getEstado() {
		return estado.name();
	}
	
	public int getCantidadJugadores () {
		return jugadores.size();
	}

}
