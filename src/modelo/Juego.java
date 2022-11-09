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
	private int jugadorQueDijoUno = -1;
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
		}else 
			if(jugadores.size() >= 2) {
				cambiarEstado(estadoJuego.JUGABLE);
			}
		
		return nroJugador;
	}

	private void cambiarEstado(estadoJuego nuevoEstado) {
		switch (nuevoEstado) {
		case JUGANDO:
			break;
		default:
			this.estado = nuevoEstado;
		}
		notificarObservadores(posiblesCambios.ESTADO_JUEGO);
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

	@Override
	public int getJugadorEnTurno() {
		return this.jugadorEnTurno;
	}

	@Override
	public void uno(int numeroJugador) {
		if(jugadores.get(numeroJugador).cantidadCartas() == 1) {
			jugadorQueDijoUno = numeroJugador;
			notificarObservadores(posiblesCambios.UN_JUGADOR_DIJO_UNO);
		}else {
			this.errorMsj = "Para decir UNO necesitas tener una sola carta!";
			notificarObservadores(posiblesCambios.ERROR);
			
		}
		
	}
	
	/**
	 *  Dado un indice de jugador, indica el indice del proximo jugador que se encuentra en estado JUGANDO 
	 * @param jugadorActivoActual
	 * @return
	 */
	private int siguienteJugadorActivo(int jugadorActivoActual) {
		
		int activo = -1;
		int i;
		if (jugadorActivoActual == -1)
			i = 0;
		else
			i = jugadorActivoActual+1;

		while ((activo == -1) && (i < jugadores.size())){
			if (jugadores.get(i).isJugando())
				activo = i;
			else
				i++;
		}
		i = 0;
		while ((activo == -1) && ( i <= jugadorEnTurno)) {
			if (jugadores.get(i).isJugando())
				activo = i;
			else
				i++;
		}
		return activo;
	}
	
	private int cantidadJugadoresActivos() {
		int activos = 0;
		for (Jugador jugador:jugadores) {
			if (jugador.isJugando())
				activos++;
		}
		return activos;
	}

	@Override
	public String getEstado() {
		return estado.name();
	}
	
	public void comenzarJuego() {
		CartaUno cartaInicial;
		notificarObservadores(posiblesCambios.COMENZO_JUEGO);
		mazo.mezclar();
		cartaInicial = mazo.getUltimaCarta();
		mazoDescarte.setUltimaCarta(cartaInicial);
		cambiarEstado(estadoJuego.MOSTRANDO_CARTA_INICIAL);
		repartirCartas();
	}

	/* (Modificar) 
	 * Metodo para saber si la carta que el jugador descarta es compatible
	 * 
	public boolean compatible(CartaUno c) {
	return this.getPalo() == ColoresBarajaUno.NEGRO
			|| this.getPalo() == c.getPalo()
			|| (this.getNumero() == c.getNumero() && !this.isEspecial() && !c.isEspecial())
			|| (this.isEspecial() && c.isEspecial() && this.efecto == c.efecto);
	}
	*/
	
	
	@Override
	public void repartirCartas() {
		
		
	}
	
	
	
	
	

}
