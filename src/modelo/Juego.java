package modelo;

import java.io.Serializable;
import java.util.ArrayList;

import modelo.JuegoPublico;
import modelo_controlador.IObservador;
import modelo_controlador.posiblesCambios;

public class Juego /*extends ObservableRemoto*/ implements JuegoPublico/*,Serializable*/ {
	
	/* ObservableRemoto implementa el array de observadores internamente
	 * , cuando implentemos RMI, va a haber que borrarlo de aca */
	private ArrayList<IObservador> observadores;
	
	private void notificarObservadores(posiblesCambios cambio){
		
	}

	@Override
	public int agregarJugador(String nombre) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getJugadores() {
		// TODO Auto-generated method stub
		return null;
	}

}
