package modelo;

import java.util.ArrayList;

import modelo_controlador.IObservador;

public interface JuegoPublico /* extends IObservableRemoto */{

	int agregarJugador(String nombre);

	ArrayList<Jugador> getJugadores();
	
	void agregarObservador(IObservador observador);
	
	int getCantidadJugadores();
	
	String getError();
		
	int getJugadorEnTurno();
	
	void uno(int numeroJugador);
	
	String getEstado();
	
	void repartirCartas();
	
	void jugar();

	void configurarJuego();
	
	void jugarMano();
	
	/*
	void gestionarEspejito(int numeroJugador, int cartaAHacerEspejito,long diferencia) throws RemoteException;
	
	GestorTiempos getGestorTiempos() throws RemoteException;
	
	void configurarJuego() throws RemoteException;

	void repartirCartas() throws RemoteException;

	void jugar() throws RemoteException;

	void jugarMano() throws RemoteException;

	int getJugadorEnTurno()throws RemoteException;

	void verificarFinTurno(Jugador jugador) throws RemoteException;

	void levantarDeDescartadas() throws RemoteException;

	void levantarDeMazo() throws RemoteException;

	int agregarJugador(String nombre) throws RemoteException;

	String getEstado()throws RemoteException;

	Carta getCartaDescartada()throws RemoteException;

	boolean isJugable()throws RemoteException;

	ArrayList<Jugador> getJugadores()throws RemoteException;

	String getNombre(int numeroJugador)throws RemoteException;

	void descartarCarta(int numeroJugador, int numeroCartaADescartar) throws RemoteException;

	int cantidadDeCartas(int numeroJugador)throws RemoteException;

	void cubo(int numeroJugador) throws RemoteException;

	int getJugadorCubo()throws RemoteException;

	String getErrorMessage() throws RemoteException;

	boolean puedoDecirCubo()throws RemoteException;

	int getGanador()throws RemoteException;

	void arrojarError(String errorMessage, int indiceJugadorError) throws RemoteException;

	void espejito(int numeroJugador, int numeroCarta) throws RemoteException;

	int cantidadJugadores()throws RemoteException;

	boolean jugadorDeseaVerCarta(int numeroJugador) throws RemoteException;

	void mostrarCarta(int numeroJugador, int indiceCartaAMostrar) throws RemoteException;

	boolean intercambiarCartas(int indiceJugadorOrigen, int indiceCartaOrigen, int indiceJugadorDestino, int indiceCartaDestino)throws RemoteException;
	
	public void cartasMostradas(int vistaDelJugadorNro) throws RemoteException;
	
	int getJugadorAMostrarCarta() throws RemoteException;

	void guardarPartida()throws RemoteException;
	
	void cargarPartida() throws RemoteException;
	
	int getIndiceJugadorError() throws RemoteException;

	boolean vistaCargada(int vistaDelJugadorNro) throws RemoteException;

	ArrayList<Jugador> getJugadoresSinAsignarVista() throws RemoteException;
	 */

}
