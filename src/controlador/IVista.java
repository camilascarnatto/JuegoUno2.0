package controlador;

import java.util.ArrayList;
import modelo.EstadosVista;
import modelo.Jugador;

public interface IVista {

/*
	void nuevasCartasJugadores(ArrayList<Jugador> jugadores);
	void nuevoEstadoJuego(String estado);
	void nuevoTurnoJugador(int numeroJugador);
	void nuevaCartaDescartada(Carta cartaDescartada);
	void terminarJuego(int ganador);
	void unJugadorDijoCubo(String nombre);
	void error(String string,int indiceJugadorError);
	void actualizarListaJugadores(ArrayList<Jugador> jugadores);
	void puedeVerCarta();
	void puedeIntercambiarCarta();
	void iniciar();
	void nuevasCartasJugadorAMostrarCartas(int jugadorAMostrarCartas);
	void seleccionarJugador(ArrayList<Jugador> jugadores);
	void comenzoJuego();
 */

	void actualizarListaJugadores(ArrayList<Jugador> jugadores);
	void setEstadoVista(EstadosVista estado);
	void comenzar();
	void setControlador(Controlador controlador);
	void error(String error);
	void salirJuego();
}
